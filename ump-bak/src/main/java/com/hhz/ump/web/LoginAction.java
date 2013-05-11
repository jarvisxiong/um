/**
 * 
 */
package com.hhz.ump.web;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj 2010-4-20
 */
@Namespace("/")
@Results({ 
		@Result(name = "changePwd", location = "/plaspd/plas-user-change!password.action", type = "redirect", params={"pwdExpiredFlag","1"}),
		@Result(name = "login", location = "/index/", type = "redirect"),
		@Result(name = "loginMobile", location = "/wap/wap-approve-info.action", type = "redirect"),
		@Result(name = "mobileLogin", location = "/login!mobile.action", type = "redirect"),
		@Result(name = "logout", location = "/j_spring_security_logout", type = "redirect"),
		@Result(name = "reLogin", location = "/login.action", type = "redirect"),
		@Result(name = "error", location = "/j_spring_security_logout", type = "redirect") 
		})
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 297729703743152666L;

	private Logger logger = Logger.getLogger(LoginAction.class);

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Boolean loginMobile = LoginUtil.isLoginMobile(null);
		if (loginMobile)
			return "mobileLogin";//跳转到手机登陆界面
		return SUCCESS;
	}

	// 简单窗口登录
	public String mobile() {
		LoginUtil.setLoginMobile(null, "1");
		return "mobile";
	}

	/**
	 * 登录系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
//		String emailFlg = acct.getEmailFlg();
//		String emailPwdFlg = acct.getEmailPasswordSetFlg();
//		String easFlg = acct.getEasFlg();
//		String easPwdFlg = acct.getEasPasswordSetFlg();
		 String mysoftFlg = acct.getMysoftFlg();
		 String mysoftPwdFlg =acct.getMysoftPasswordSetFlg();
		 String cmailFlg = acct.getCmailFlg();
		 String cmailPwdFlg =acct.getCmailPasswordSetFlg();

		// 非开发环境
		String ctx = ServletActionContext.getRequest().getContextPath();
		if (!ctx.equals("/PowerDesk")) {
			
			StringBuffer sb = new StringBuffer();
			// 若已开通邮箱且未设置密码
//			if (Util.emailEnable(emailFlg) && DictContants.UAAP_EMAIL_PASSWORD_SET_FLG_NO.equals(emailPwdFlg)){
//				sb.append("email,");
//			}
//			不同步eas密码 2011-08-17 hidden by huangbijin
//			if (Util.easEnable(easFlg) && DictContants.UAAP_EAS_PASSWORD_SET_FLG_NO.equals(easPwdFlg)){
//				sb.append("eas,");
//			}
			if (Util.emailEnable(mysoftFlg) && DictContants.UAAP_MYSOFT_PASSWORD_SET_FLG_NO.equals(mysoftPwdFlg)){
				sb.append("mysoft,");
			} 
			if (Util.emailEnable(cmailFlg) && DictContants.CMAIL_PASSWORD_SET_FLG_NO.equals(cmailPwdFlg)){
				sb.append("cmail,");
			} 

			try {
				if(StringUtils.isNotBlank(sb.toString())){
					String pwd = LoginUtil.getPwd(null);
					Util.getPlasService().synPwdChange(acct.getPlasAcctId(), pwd, sb.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// **********start**MAC校验*******************/
		String macAddr = acct.getMacAddress();
		String macAddrRemot = LoginUtil.getMac(null);
		Boolean macMatch = LoginUtil.isMacMatch(null);
		if ("1".equals(acct.getMacLockedFlg()) && !macMatch) {
			if (StringUtils.isNotEmpty(StringUtils.trim(macAddrRemot)) && (StringUtils.isEmpty(macAddr))) {
				// 如果是第一次登录，自动绑定mac
				StringBuilder sbTmp = new StringBuilder(StringUtils.trimToEmpty(macAddr));
				sbTmp.append(macAddrRemot + ",");
				acct.setMacAddress(sbTmp.toString());
				if (Util.getPlasService().updateAcct(acct)) {
					SpringSecurityUtils.setCurrentPlasUser(acct);
				}

			}
			if (!validateMac(macAddrRemot, acct))
				return "error";
		}
		// **********end**MAC校验*******************/
		
		//账号有效性
		String valid = validateAcct();
		if (valid != null)
			return valid;
		
		// 如果是第一次登录要求进行身份证校验
		// if (user.getLastLoginDate() == null)
		// return "idNo";
		// 设置已登录

		loginSuccess();
		
		//若用户是"3-邮箱用户",则直接跳到修改密码页面.
		if("3".equals(acct.getUserTypeCd()))
			return "changePwd";
		
		// 密码过期
//		String tmpPwdStrategyCd = acct.getPwdStrategyCd();
//		if (StringUtils.isNotBlank(tmpPwdStrategyCd)){
//			Long tRegion = DateOperator.compareDays(acct.getPwdLastModDate(),new Date());
//			if("1".equals(tmpPwdStrategyCd)){
//				if(tRegion > 30)
//					return "changePwd";
//			}
//			if("2".equals(tmpPwdStrategyCd)){
//				if(tRegion > 60)
//					return "changePwd";
//			}
//			if("3".equals(tmpPwdStrategyCd)){
//				if(tRegion > 90)
//					return "changePwd";
//			}
//		}
		
		Boolean loginMobile = LoginUtil.isLoginMobile(null);
		if (loginMobile)
			return "loginMobile";
		return "login";
	}

	private String validateAcct() {
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();

//		// 邮箱用户,不能登录PD
//		if(DictContants.PLAS_USER_TYPE_3.equals(acct.getUserTypeCd())){
//			LoginUtil.setErrorInfo(null, "您是邮件用户,请登录邮件系统！");
//			return "error";
//		}
//		
//		List<WsPlasRole> lstRole = Util.getPlasService().getRoleListByUiid(acct.getUiid());
//		if(lstRole == null || lstRole.size() == 0){
//			LoginUtil.setErrorInfo(null, "您尚未授权,请联系管理员授权！");
//			return "error";
//		}
		
		String statusCd = acct.getStatusCd();
		// 已注销或已冻结
		if (DictContants.UAAP_USER_STATUS_CLOSED.equals(statusCd)
				|| DictContants.UAAP_USER_STATUS_FREEZE.equals(statusCd)) {
			String statusName = CodeNameUtil.getDictNameByCd(DictContants.UAAP_USER_STATUS, statusCd);
			LoginUtil.setErrorInfo(null, "该用户已 " + statusName + "，请联系管理员！");
			return "error";
		} 
		// 未入职
		else if (DictContants.UAAP_USER_STATUS_NOENTER.equals(statusCd)) {
			String statusName = CodeNameUtil.getDictNameByCd(DictContants.UAAP_USER_STATUS, statusCd);
			LoginUtil.setErrorInfo(null, "该用户 " + statusName + "，请联系管理员启用！");
			return "error";
		}

		// 如果是第一次登录要求进行身份证校验
		if (acct.getLastLoginDate() == null) {
			Boolean idNoMatch = LoginUtil.isIdnoMatch(null);
			String idVal = LoginUtil.getIdNo(null);
			if (!idNoMatch && StringUtils.isBlank(idVal)) {
				LoginUtil.setFirstlogin(null, true);
				return "reLogin";
			} else {

				String idNo = acct.getIdno();
				if (StringUtils.equals(StringUtils.trim(idVal), StringUtils.trim(idNo))) {
					// 设置已登录
					// loginSuccess();
					// LoginUtil.getLoginEmpInfo().setIdNoTrue(true);
					// return "login";
				} else {
					logger.error("用户:" + LoginUtil.getUserName(null) + ";idNo:" + idVal + ";登记idNo:" + idNo);
					LoginUtil.setErrorInfo(null, "身份证不匹配，请重试！");
					return "error";
				}
			}
		}
		return null;
	}

	/**
	 * 判断mac地址是否正确
	 * 
	 * @param macAddrRemot
	 * @param macAddr
	 * @return
	 */
	private boolean validateMac(String macAddrRemot, WsPlasAcct wsUaapUser) {
		boolean flag = false;
		String macAddr = wsUaapUser.getMacAddress();
		StringBuffer sbMsg = new StringBuffer();
		if (StringUtils.isEmpty(StringUtils.trim(macAddrRemot))) {
			sbMsg.append("未取到MAC地址，请稍等10秒再试一次，如还是无法登入，请联系管理员");
			logger.error("用户:" + wsUaapUser.getUserName() + ":" + sbMsg.toString());
		} else if (StringUtils.isEmpty(StringUtils.trim(macAddr))) {
			sbMsg.append("MAC地址未绑定，请联系管理员！");
		} else if (!isMatche(macAddrRemot, macAddr)) {
			logger.error("用户:" + wsUaapUser.getUserName() + ";mac:" + macAddrRemot + " ;登记mac:" + macAddr);
			sbMsg.append("MAC地址不匹配，请在您的电脑上登录！或联系管理员");
		}
		if (sbMsg.length() > 0) {
			LoginUtil.setErrorInfo(null, sbMsg.toString());
		} else {
			flag = true;
		}
		LoginUtil.setMacMatch(null, flag);
		return flag;
	}

	private boolean isMatche(String macAddrRemot, String macAddr) {
		boolean flag = false;
		if (macAddr != null) {
			String[] macs = macAddrRemot.split(",");
			for (String mac : macs) {
				if (macAddr.toUpperCase().contains(mac.toUpperCase())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 登出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		try {
			reduceUser();
			String loginlogId = LoginUtil.getId(null);
			Util.getPlasService().notifyLogout(SpringSecurityUtils.getCurrentPlasUser().getUiid(), loginlogId);
			LoginUtil.clear();
		} catch (Exception e) {
			logger.error(" 登出系统,出现异常!", e);
		}
		return "logout";
	}

	public String reduceUser() throws Exception {
		PlasCache.reduceOnlineCount();
		return null;
	}

	public String addUser() throws Exception {
		PlasCache.addOnlineCount();
		return null;
	}

	/**
	 * 密码错误
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logError() throws Exception {
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		// String macAddrRemot = LoginUtil.getLoginEmpInfo().getMacAddress();
		String uiid = LoginUtil.getUiid(null);
		int cnt = Util.getPlasService().notifyLoginError(uiid, ip);
		String desc = "";
		if (GlobalConstants.NONE_FAILURE_FROZEN == cnt) {
			desc = "对不起,密码错误次数过多,为了账号安全,已冻结用户,解冻请联系管理员!";
		} else if (GlobalConstants.NONE_FAILURE_NOTEXIST == cnt) {
			desc = "对不起,用户名不存在,请确认!";
		} else if (GlobalConstants.NONE_FAILURE_NOCHECK == cnt) {
			desc = "对不起,密码不正确,请重试!";
		} else {
			desc = "对不起,密码不正确,请重试! ( 还有 " + cnt + " 次 ) ";
		}
		LoginUtil.setErrorInfo(null, desc);
		return "error";
	}

	private void loginSuccess() {
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
		LoginUtil.setAcctId(null, acct.getPlasAcctId());
		LoginUtil.setIdNo(null, acct.getIdno());
		LoginUtil.setEmailpwdflg(null, acct.getEmailPasswordSetFlg());
		LoginUtil.setUserName(null, acct.getUserName());

		LoginUtil.setErrorInfo(null, null);
		String ip = LoginUtil.getIp(null);
		String uiid = LoginUtil.getUiid(null);
		String loginlogId = Util.getPlasService().notifyLogin(uiid, ip);
		LoginUtil.setId(null, loginlogId);
		LoginUtil.setFirstlogin(null, false);
		LoginUtil.setIdnoMatch(null, true);
	}

}
