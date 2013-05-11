package com.intelitune.nwms.common;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.click.Page;

import com.axis2.services.MCSserviceStub;
import com.intelitune.nwms.model.InttUser;
import com.intelitune.nwms.model.InttUserCustomer;
import com.intelitune.nwms.model.InttUserRole;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;
import com.intelitune.nwms.service.UserCustomerService;
import com.intelitune.nwms.service.UserCustomerServiceImp;
import com.intelitune.nwms.service.UserRoleService;
import com.intelitune.nwms.service.UserRoleServiceImp;
import com.intelitune.nwms.service.UserService;
import com.intelitune.nwms.service.UserServiceImp;
import com.intelitune.rbac.impl.SimpleRole;

public class CommonAccount extends Page implements com.intelitune.rbac.base.CommonAccount {
	private final static String FIELD_NAME = CommonAccount.class.getName();
	public int accountId;
	public int accountUserRoleId;
	public String accountName;
	public int accountRole;
	public String accountRoleName;
	public String accountLocaleLanguage;
	public MCSserviceStub.InttClientDetailWS[] inttClientDetailWS;
	public boolean login = false;
	public String loginError;

//	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("ServiceContext.xml");
	public  static RoleService roleService = RoleServiceImp.getInstance();;
	public static  UserService userService = UserServiceImp.getInstance();
	public static UserRoleService userRoleService = UserRoleServiceImp.getInstance();;
	private static UserCustomerService userCustomerService = UserCustomerServiceImp.getInstance();;
	private static MCSService mcsService = MCSService.getInstance();

	private CommonAccount() {
	}

	/**
	 * get instance of the account from session, if none, null will be returned.
	 * 
	 * @param session
	 * @return
	 */
	public static CommonAccount getInstance(HttpSession session) {
		Object o = session.getAttribute(FIELD_NAME);
		return (CommonAccount) o;
	}

	public void accountLogout(HttpSession session) {
		session.removeAttribute(FIELD_NAME);
	}

	/**
	 * create a user object and put it in session.
	 * 
	 * @param session
	 * @param userName
	 * @param password
	 * @return the user object, if login failture, return null
	 */
	@SuppressWarnings("unchecked")
	public static CommonAccount accountLogin(HttpSession session, String accountName, String password) {
		if (password != null) {
			password = new MD5().getMD5ofStr(password);
		}
		CommonAccount commonAccount = new CommonAccount();
		try {
			InttUserRole account = new InttUserRole();
			List<InttUserRole> l = userRoleService.findByQuery("from InttUserRole as iur where iur.inttUser.userName = '" + accountName + "' ");
			if (l.size() == 0) {
				commonAccount.loginError = "The username is not found in the database!";
				return commonAccount;
			} else {
				account = l.get(0);
			}
			if (password != null) {
				if (!password.equals(account.getInttUser().getUserPassword())) {
					commonAccount.loginError = "The password for the username is invalid!";
					return commonAccount;
				}
			}
			if (account.getInttUser().getUserStatus() == UserConst.LOCKED) {
				commonAccount.loginError = "The user is locked, please contact the administrator!";
				return commonAccount;
			}

			commonAccount.accountId = account.getInttUser().getUserId();
			commonAccount.accountUserRoleId = account.getUserRoleId();
			commonAccount.accountName = account.getInttUser().getUserName();
			commonAccount.accountRole = account.getInttRole().getRoleId();
			commonAccount.accountRoleName = account.getInttRole().getRoleName();
			commonAccount.accountLocaleLanguage = account.getInttUser().getLanguage();

			// set customer id
			List<InttUserCustomer> list = userCustomerService.findByQuery("from InttUserCustomer as iuc where iuc.inttUser.userId = " + commonAccount.accountId);
			if (list != null) {
				MCSserviceStub.InttClientDetailWS[] a = new MCSserviceStub.InttClientDetailWS[list.size()];
				for (int i = 0; i < list.size(); i++) {
					a[i] = mcsService.findClientById(list.get(i).getClientId());
				}
				commonAccount.inttClientDetailWS = a;
			}

			// add role name
			commonAccount.setRole = new HashSet();
			commonAccount.setRole.add(new SimpleRole(account.getInttRole().getRoleName()));

			InttUser user = new InttUser();
			user = (InttUser) userService.findById(account.getInttUser().getUserId());
			user.setLastLogin(new Date());

			userService.update(user);
			userRoleService.update(account);

			commonAccount.login = true;
			session.setAttribute(FIELD_NAME, commonAccount);
		} catch (Exception e) {
			e.printStackTrace();
			commonAccount.loginError = "Connect Database Error!";
		}
		return commonAccount;
	}

	public String getName() {
		return accountName;
	}

	@SuppressWarnings("unchecked")
	private Set setRole = null;

	@SuppressWarnings("unchecked")
	public Set getRole() {
		return setRole;
	}

	@SuppressWarnings("unchecked")
	public void setRole(Set set) {
		this.setRole = set;
	}
}