/**
 * 
 */
package com.hhz.ump.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

/**
 * @author huangj 2010-5-25
 */
public class LoginUtil {
	private static final String LOGIN_ERRORINFO = "errorInfo";
	private static final String LOGIN_ID = "loginId";
	private static final String LOGIN_FIRSTLOGIN = "firstLogin";
	private static final String LOGIN_IP = "loginIp";
	private static final String LOGIN_MAC = "loginMac";
	private static final String LOGIN_MACMATCH = "loginMacMatch";
	private static final String LOGIN_UIID = "loginUiid";
	private static final String LOGIN_USERNAME = "loginUserName";
	private static final String LOGIN_PWD = "loginPwd";
	private static final String LOGIN_ACCTID = "loginAcctId";
	private static final String LOGIN_IDNO = "loginIdNo";
	private static final String LOGIN_IDNOMATCH = "loginIdNoMatch";
	private static final String LOGIN_EMAILPWDFLG = "loginEmailPwdFlg";
	private static final String LOGIN_MOBILE = "loginMobile";

	private static HttpSession getSession(HttpServletRequest request) {
		HttpSession session = null;
		if (request != null) {
			session = request.getSession(false);
		} else {
			session = ServletActionContext.getRequest().getSession(false);
		}
		return session;
	}
	public static void clear(){
		HttpSession session =getSession(null);
		session.removeAttribute(LOGIN_ERRORINFO);
		session.removeAttribute(LOGIN_ID);
		session.removeAttribute(LOGIN_FIRSTLOGIN);
		session.removeAttribute(LOGIN_IP);
		session.removeAttribute(LOGIN_MAC);
		session.removeAttribute(LOGIN_MACMATCH);
		session.removeAttribute(LOGIN_UIID);
		session.removeAttribute(LOGIN_USERNAME);
		session.removeAttribute(LOGIN_PWD);
		session.removeAttribute(LOGIN_ACCTID);
		session.removeAttribute(LOGIN_IDNO);
		session.removeAttribute(LOGIN_IDNOMATCH);
		session.removeAttribute(LOGIN_EMAILPWDFLG);
		//session.removeAttribute(LOGIN_MOBILE);
	}
	public static void removeAttribute(HttpServletRequest request,String attribute){
		HttpSession session = getSession(request);
		if (session != null) {
			session.removeAttribute(attribute);
		}
	}
	public static Boolean isLoginMobile(HttpServletRequest request) {
		String loginMobile = null;
		HttpSession session = getSession(request);
		if (session != null) {
			loginMobile = (String) session.getAttribute(LOGIN_MOBILE);
		}
		if(StringUtils.isNotBlank(loginMobile))
			return true;
		return false;
	}

	public static void setLoginMobile(HttpServletRequest request, String LoginMobile) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_MOBILE, LoginMobile);
		}
	}
	public static String getEmailpwdflg(HttpServletRequest request) {
		String emailpwdflg = null;
		HttpSession session = getSession(request);
		if (session != null) {
			emailpwdflg = (String) session.getAttribute(LOGIN_EMAILPWDFLG);
		}
		return emailpwdflg;
	}
	
	public static void setEmailpwdflg(HttpServletRequest request, String emailpwdflg) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_EMAILPWDFLG, emailpwdflg);
		}
	}
	
	public static void setUserName(HttpServletRequest request, String userName) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_USERNAME, userName);
		}
	}
	public static String getUserName(HttpServletRequest request) {
		String uiid = null;
		HttpSession session = getSession(request);
		if (session != null) {
			uiid = (String) session.getAttribute(LOGIN_USERNAME);
		}
		return uiid;
	}
	public static void setPwd(HttpServletRequest request, String pwd) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_PWD, pwd);
		}
	}
	public static String getPwd(HttpServletRequest request) {
		String pwd = null;
		HttpSession session = getSession(request);
		if (session != null) {
			pwd = (String) session.getAttribute(LOGIN_PWD);
		}
		return pwd;
	}
	public static void setIdNo(HttpServletRequest request, String idNo) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_IDNO, idNo);
		}
	}
	public static String getIdNo(HttpServletRequest request) {
		String idNo = null;
		HttpSession session = getSession(request);
		if (session != null) {
			idNo = (String) session.getAttribute(LOGIN_IDNO);
		}
		return idNo;
	}
	public static void setIp(HttpServletRequest request, String ip) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_IP, ip);
		}
	}
	public static String getIp(HttpServletRequest request) {
		String ip = null;
		HttpSession session = getSession(request);
		if (session != null) {
			ip = (String) session.getAttribute(LOGIN_IP);
		}
		return ip;
	}
	public static void setMac(HttpServletRequest request, String mac) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_MAC, mac);
		}
	}
	public static String getMac(HttpServletRequest request) {
		String mac = null;
		HttpSession session = getSession(request);
		if (session != null) {
			mac = (String) session.getAttribute(LOGIN_MAC);
		}
		return mac;
	}
	public static void setAcctId(HttpServletRequest request, String acctId) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_ACCTID, acctId);
		}
	}
	public static String getAcctId(HttpServletRequest request) {
		String acctId = null;
		HttpSession session = getSession(request);
		if (session != null) {
			acctId = (String) session.getAttribute(LOGIN_ACCTID);
		}
		return acctId;
	}
	public static String getUiid(HttpServletRequest request) {
		String uiid = null;
		HttpSession session = getSession(request);
		if (session != null) {
			uiid = (String) session.getAttribute(LOGIN_UIID);
		}
		return uiid;
	}
	
	public static void setUiid(HttpServletRequest request, String uiid) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_UIID, uiid);
		}
	}

	public static String getErrorInfo(HttpServletRequest request) {
		String errorInfo = null;
		HttpSession session = getSession(request);
		if (session != null) {
			errorInfo = (String) session.getAttribute(LOGIN_ERRORINFO);
		}
		return errorInfo;
	}

	public static void setErrorInfo(HttpServletRequest request, String errorInfo) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_ERRORINFO, errorInfo);
		}
	}

	public static String getId(HttpServletRequest request) {
		String id = null;
		HttpSession session = getSession(request);
		if (session != null) {
			id = (String) session.getAttribute(LOGIN_ID);
		}
		return id;
	}

	public static void setId(HttpServletRequest request, String id) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_ID, id);
		}
	}

	public static Boolean isFirstlogin(HttpServletRequest request) {
		Boolean isFirstlogin = false;
		HttpSession session = getSession(request);
		if (session != null) {
			isFirstlogin = (Boolean) session.getAttribute(LOGIN_FIRSTLOGIN);
		}
		isFirstlogin = isFirstlogin == null ? false : isFirstlogin;
		return isFirstlogin;
	}

	public static void setFirstlogin(HttpServletRequest request, Boolean firstlogin) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_FIRSTLOGIN, firstlogin);
		}
	}
	public static Boolean isMacMatch(HttpServletRequest request) {
		Boolean macMatch = false;
		HttpSession session = getSession(request);
		if (session != null) {
			macMatch = (Boolean) session.getAttribute(LOGIN_MACMATCH);
		}
		macMatch = macMatch == null ? false : macMatch;
		return macMatch;
	}
	
	public static void setMacMatch(HttpServletRequest request, Boolean macMatch) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_MACMATCH, macMatch);
		}
	}
	public static Boolean isIdnoMatch(HttpServletRequest request) {
		Boolean idnoMatch = false;
		HttpSession session = getSession(request);
		if (session != null) {
			idnoMatch = (Boolean) session.getAttribute(LOGIN_IDNOMATCH);
		}
		idnoMatch = idnoMatch == null ? false : idnoMatch;
		return idnoMatch;
	}
	
	public static void setIdnoMatch(HttpServletRequest request, Boolean idnoMatch) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.setAttribute(LOGIN_IDNOMATCH, idnoMatch);
		}
	}
}
