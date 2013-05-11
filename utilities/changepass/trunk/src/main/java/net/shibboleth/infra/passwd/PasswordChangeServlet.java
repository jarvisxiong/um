/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may 
 * not use this file except in compliance with the License.  You may obtain 
 * a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.shibboleth.infra.passwd;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shibboleth.utilities.java.support.primitive.StringSupport;
import net.shibboleth.utilities.java.support.xml.DomTypeSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.vt.middleware.crypt.digest.SHA1;
import edu.vt.middleware.crypt.util.Base64Converter;
import edu.vt.middleware.crypt.util.Convert;
import edu.vt.middleware.dictionary.FileWordList;
import edu.vt.middleware.dictionary.TernaryTreeDictionary;
import edu.vt.middleware.ldap.AttributesFactory;
import edu.vt.middleware.ldap.Ldap;
import edu.vt.middleware.ldap.Ldap.AttributeModification;
import edu.vt.middleware.ldap.LdapConfig.SearchScope;
import edu.vt.middleware.ldap.auth.Authenticator;
import edu.vt.middleware.ldap.auth.AuthenticatorConfig;
import edu.vt.middleware.ldap.handler.EntryDnSearchResultHandler;
import edu.vt.middleware.ldap.handler.FqdnSearchResultHandler;
import edu.vt.middleware.ldap.handler.SearchResultHandler;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DictionarySubstringRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.RuleList;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.SequenceRule;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.WhitespaceRule;

/** Simple webapp that allows an LDAP password to be changed. */
public class PasswordChangeServlet extends HttpServlet {

    /** Name of the request parameter that carries the username. */
    public static final String USERID_PARAM = "user";

    /** Name of the request parameter that carries the current password. */
    public static final String CURRENT_PASS_PARM = "password";

    /** Name of the request parameter that carries the new password. */
    public static final String NEW_PASS_PARM = "newpass";

    /** Name of the request attribute that carries informative messages. */
    public static final String INFO_ATTRIB = "info";

    /** Name of the request attribute that carries error messages. */
    public static final String ERROR_ATTRIB = "error";

    /** Name of the initialization parameter that carries context-relative path to the change password page. */
    public static final String CHANGE_PASS_PATH_PARAM = "changePassPagePath";

    /** Name of the initialization parameter that carries filesystem path to the dictionary file. */
    public static final String DICTIONARY_PATH_PARAM = "dictionaryPath";

    /** Name of the initialization parameter that carries LDAP URL. */
    public static final String LDAP_URL_PARAM = "ldapUrl";

    /** Name of the initialization parameter that carries LDAP base DN. */
    public static final String LDAP_BASE_PARAM = "ldapBaseDN";

    /** Name of the initialization parameter that carries user identifier attribute name. */
    public static final String LDAP_UID_PARAM = "ldapUserAttribute";

    /**
     * Name of the initialization parameter that gives the amount of time, as an ISO8601 duration, to "remember" a
     * failed authentication attempt.
     */
    public static final String FAILED_AUTHN_RETRY_WINDOW_PARAM = "failedAuthnRetryWindow";

    /**
     * Name of the initialization parameter that gives the number of times a user can fail to authenticate, with the
     * retry window, before they are locked out.
     */
    public static final String FAILED_AUTHN_RETRY_ATTEMPTS_PARAM = "failedAuthnRetryAttempts";

    /**
     * Name of the initialization parameter that gives the amount of time, as an ISO8601 duration, that a user is locked
     * out.
     */
    public static final String LOCKOUT_DURATION_PARAM = "lockoutDuration";

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(PasswordChangeServlet.class);

    /** Servlet-context relative path to the change password page. */
    private String changePassPage;

    /** Rules which new passwords must meet. */
    private RuleList passwordRules;

    /** Object used to search the LDAP and set new passwords. */
    private Ldap ldap;

    /** Object used to authenticate users with existing password. */
    private Authenticator authenticator;

    /** Name of the field used to identifier the user. */
    private String ldapUserIdField;

    /** Amount of time, in milliseconds, to "remember" a failed authentication attempt. */
    private long retryWindow;

    /** Number of times a user can fail to authenticate, with the retry window, before they are locked out. */
    private short retryAttempts;

    /** Amount of time, in milliseconds, that a user is locked out. */
    private long lockoutDuration;

    /** Failed authentication attempts. Map key is the userid, values are failed authn times in milliseconds. */
    private Map<String, AuthenticationFailureTracker> failedAuthnAttempts;

    /** {@inheritDoc} */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.debug("Initializing password change servlet");

        changePassPage = StringSupport.trimOrNull(config.getInitParameter(changePassPage));
        if (changePassPage == null) {
            changePassPage = "/index.jsp";
        }
        log.debug("Change Password JSP page: {}", changePassPage);

        log.debug("Initializing password rules");
        initializePasswordRules(config);

        log.debug("Initializing LDAP connection");
        initializeLdap(config);

        log.debug("Initialized account lockout configuration");
        initializeLockoutConfig(config);

        failedAuthnAttempts = new ConcurrentHashMap<String, AuthenticationFailureTracker>();
        log.debug("Password change servlet initialized");
    }

    /**
     * Initializes the rules that new passwords must meet.
     * 
     * @param servletConfig configuration for this Servlet
     * 
     * @throws ServletException thrown if the dictionary file can not be read
     */
    protected void initializePasswordRules(final ServletConfig servletConfig) throws ServletException {
        final String dictionaryPath =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(DICTIONARY_PATH_PARAM));
        if (dictionaryPath == null) {
            throw new ServletException("No dictionary file was not specified");
        }
        log.debug("Dictionary path: {}", dictionaryPath);

        DictionarySubstringRule dictionaryRule;
        try {
            final RandomAccessFile dictionaryFile = new RandomAccessFile(dictionaryPath, "r");
            final FileWordList dictionaryWords = new FileWordList(dictionaryFile, false);
            final TernaryTreeDictionary dictionary = new TernaryTreeDictionary(dictionaryWords, true);
            dictionaryRule = new DictionarySubstringRule(dictionary);
            dictionaryRule.setWordLength(4);
            dictionaryRule.setMatchBackwards(true);
        } catch (IOException e) {
            throw new ServletException("Unable to read dictionary file", e);
        }

        final CharacterCharacteristicsRule characterRules = new CharacterCharacteristicsRule();
        characterRules.getRules().add(new DigitCharacterRule(1));
        characterRules.getRules().add(new NonAlphanumericCharacterRule(1));
        characterRules.getRules().add(new UppercaseCharacterRule(1));
        characterRules.getRules().add(new LowercaseCharacterRule(1));
        characterRules.setNumberOfCharacteristics(3);

        passwordRules = new RuleList();
        passwordRules.getRules().add(new LengthRule(8, 16));
        passwordRules.getRules().add(new WhitespaceRule());
        passwordRules.getRules().add(new SequenceRule(true, true));
        passwordRules.getRules().add(characterRules);
        passwordRules.getRules().add(dictionaryRule);
    }

    /**
     * Initializes the LDAP libraries.
     * 
     * @param servletConfig configuration for this Servlet
     * 
     * @throws ServletException thrown if {@link #LDAP_URL_PARAM} or {@link #LDAP_BASE_PARAM} is not set
     */
    protected void initializeLdap(final ServletConfig servletConfig) throws ServletException {
        final String ldapUrl =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(LDAP_URL_PARAM));
        if (ldapUrl == null) {
            throw new ServletException("No LDAP URL was specified");
        }

        final String ldapBaseDN =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(LDAP_BASE_PARAM));
        if (ldapBaseDN == null) {
            throw new ServletException("No LDAP baseDN was specified");
        }

        ldapUserIdField = StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(LDAP_UID_PARAM));
        if (ldapUserIdField == null) {
            ldapUserIdField = "uid";
        }

        log.debug("LDAP configuration. URL: {}, BaseDN: {}, UseID Field: {}", new Object[] {ldapUrl, ldapBaseDN,
                ldapUserIdField,});
        final AuthenticatorConfig ldapConfig = new AuthenticatorConfig(ldapUrl, ldapBaseDN);
        ldapConfig.setBatchSize(1);
        ldapConfig.setUserField(new String[] {ldapUserIdField});
        ldapConfig.setSearchScope(SearchScope.ONELEVEL);
        ldapConfig.setSearchResultHandlers(new SearchResultHandler[] {new FqdnSearchResultHandler(),
                new EntryDnSearchResultHandler(),});

        ldap = new Ldap(ldapConfig);
        authenticator = new Authenticator(ldapConfig);
    }

    /**
     * Initializes the account lockout configuration.
     * 
     * @param servletConfig configuration for this Servlet
     */
    protected void initializeLockoutConfig(final ServletConfig servletConfig) {
        final String retryWindowStr =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(
                        FAILED_AUTHN_RETRY_WINDOW_PARAM));
        if (retryWindowStr == null) {
            retryWindow = TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
        } else {
            retryWindow = DomTypeSupport.durationToLong(retryWindowStr);
        }
        log.debug("Failed password change retry window: {}ms", retryWindow);

        final String retryAttemptsStr =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(
                        FAILED_AUTHN_RETRY_ATTEMPTS_PARAM));
        if (retryAttemptsStr == null) {
            retryAttempts = 3;
        } else {
            retryAttempts = Short.parseShort(retryAttemptsStr);
        }
        log.debug("Failed password change attempts: {}", retryAttempts);

        final String lockoutDurationStr =
                StringSupport.trimOrNull(servletConfig.getServletContext().getInitParameter(LOCKOUT_DURATION_PARAM));
        if (lockoutDurationStr == null) {
            lockoutDuration = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
        } else {
            lockoutDuration = DomTypeSupport.durationToLong(lockoutDurationStr);
        }
        log.debug("Change password lockout duration: {}ms", lockoutDuration);
    }

    /** {@inheritDoc} */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String userid = StringSupport.trimOrNull(request.getParameter(USERID_PARAM));
        if (userid == null) {
            forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB, "UserID was null or empty");
            return;
        }
        log.debug("Attempting to change password for {}", userid);

        final String currentPass = StringSupport.trimOrNull(request.getParameter(CURRENT_PASS_PARM));
        if (currentPass == null) {
            forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB,
                    "Current password was null or empty");
            return;
        }

        final String newPass = StringSupport.trimOrNull(request.getParameter(NEW_PASS_PARM));
        if (newPass == null) {
            forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB, "New password was null or empty");
            return;
        }

        log.debug("Evaluating new password for {}", userid);
        final PasswordData passwordData = new PasswordData(new Password(newPass));
        passwordData.setUsername(userid);
        final RuleResult result = PasswordValidator.validate(passwordRules, passwordData);
        if (!result.isValid()) {
            log.debug("New password for {} does not meet password requirement rules", userid);
            forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB,
                    "New password does not meet password requirements");
            return;
        }

        try {
            log.debug("Authenticating user {}", userid);
            if (!isAuthenticated(userid, currentPass)) {
                forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB, "Invalid username/password");
                return;
            }

            log.debug("Setting new password for user {}", userid);
            setNewPassword(userid, newPass);
            forwardToChangePasswordPageWithMessage(request, response, INFO_ATTRIB, "Password change complete");
            log.debug("New password set for {}", userid);
        } catch (NamingException e) {
            log.error("Error contacting LDAP server for user " + userid, e);
            forwardToChangePasswordPageWithMessage(request, response, ERROR_ATTRIB,
                    "Internal error, unable to set new password");
        }

        return;
    }

    /**
     * Authenticates the user.
     * 
     * @param userid username of the user
     * @param password password of the user
     * 
     * @return true if the user is authenticated, false if not
     * 
     * @throws NamingException thrown if there is a problem communicating with the LDAP server
     */
    private boolean isAuthenticated(final String userid, final String password) throws NamingException {
        AuthenticationFailureTracker tracker = failedAuthnAttempts.get(userid);
        if (tracker != null && tracker.isLockedOut()) {
            log.debug("User {} is locked out", userid);
            return false;
        }

        if (authenticator.authenticate(userid, password)) {
            log.debug("User {} successfully authenticated", userid);
            failedAuthnAttempts.remove(userid);
            return true;
        } else {
            log.debug("User {} failed authenticated", userid);
            if (tracker == null) {
                tracker = new AuthenticationFailureTracker();
                failedAuthnAttempts.put(userid, tracker);
            }
            tracker.recordFailedAttempt();
            return false;
        }
    }

    /**
     * Sets a new password for the given user.
     * 
     * @param userid ID of the user
     * @param newPassword new password for the user
     * 
     * @throws NamingException thrown if there is a problem communicating with the LDAP server
     */
    private void setNewPassword(final String userid, final String newPassword) throws NamingException {
        final SearchResult results =
                ldap.searchAttributes(AttributesFactory.createAttributes(ldapUserIdField, userid),
                        new String[] {"entryDN"}).next();
        final String userDn = results.getAttributes().get("entryDN").get().toString();
        log.debug("DN for user {} is {}", userid, userDn);

        final String shaPassword = "{SHA}" + new SHA1().digest(Convert.toBytes(newPassword), new Base64Converter());
        ldap.modifyAttributes(userDn, AttributeModification.REPLACE,
                AttributesFactory.createAttributes("userPassword", shaPassword));

    }

    /**
     * Forwards the request to the change password page with an optional message set in a given message attribute. This
     * method will cause the response to be committed so no changes should be made to the response after this method
     * returns.
     * 
     * @param request current request
     * @param response current response
     * @param messageAttribute name of the attribute that will carry the message, may be null
     * @param message the message to be sent
     * 
     * @throws ServletException thrown if there is a problem forwarding the request
     */
    private void forwardToChangePasswordPageWithMessage(final HttpServletRequest request,
            final HttpServletResponse response, final String messageAttribute, final String message)
            throws ServletException {
        if (messageAttribute != null) {
            request.setAttribute(messageAttribute, message);
        }

        final RequestDispatcher dispathcher = request.getRequestDispatcher(changePassPage);
        try {
            dispathcher.forward(request, response);
        } catch (IOException e) {
            throw new ServletException("Unable to forward to change password page", e);
        }
    }

    /** Class used to track failed authentication attempts and determine if if an account is locked out. */
    private class AuthenticationFailureTracker {

        /** Time, in milliseconds, when the account will become unlock or 0 if the account is not locked. */
        private long accountLockoutExpiration;

        /** Instants, in milliseconds since the epoch, of failed authentication attempts. */
        private final LinkedList<Long> failedAttempts;

        /** Constructor. */
        public AuthenticationFailureTracker() {
            accountLockoutExpiration = 0;
            failedAttempts = new LinkedList<Long>();
        }

        /**
         * Gets whether the account is currently locked out.
         * 
         * @return true if the account is locked out, false if not
         */
        public boolean isLockedOut() {
            if (accountLockoutExpiration == 0) {
                return false;
            }

            long now = System.currentTimeMillis();
            if (accountLockoutExpiration < now) {
                accountLockoutExpiration = 0;
                return false;
            }

            return true;
        }

        /** Records an authentication failure. */
        public void recordFailedAttempt() {
            long now = System.currentTimeMillis();

            // clean out any failed attempts that happened too long ago
            long retryWindowStart = now - retryWindow;
            Iterator<Long> failedAttemptsItr = failedAttempts.descendingIterator();
            long failedAttempt;
            while (failedAttemptsItr.hasNext()) {
                failedAttempt = failedAttemptsItr.next();
                if (failedAttempt < retryWindowStart) {
                    failedAttemptsItr.remove();
                }
            }

            // make sure we have an open slot to add our current failed attempt
            while (failedAttempts.size() > retryAttempts - 1) {
                failedAttempts.removeLast();
            }

            // add current failed attempt
            failedAttempts.add(now);

            // if the account isn't locked out and we just hit the max total retry attempts, lock the account
            if (accountLockoutExpiration == 0 && failedAttempts.size() == retryAttempts) {
                accountLockoutExpiration = now + lockoutDuration;
            }
        }
    }
}