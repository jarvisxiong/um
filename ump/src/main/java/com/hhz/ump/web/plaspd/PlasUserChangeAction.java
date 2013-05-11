package com.hhz.ump.web.plaspd;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Md5Encrypt;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasUser;

@Namespace("/plaspd")
public class PlasUserChangeAction extends CrudActionSupport<WsPlasAcct> {

	private static final long serialVersionUID = -3445152342227169047L;

	private static Log log = LogFactory.getLog(PlasUserChangeAction.class);

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	// 修改密码
	private String oldPassword;
	private String newPassword;
	private String newPassword2;

	private String infoMsg;
	
	//密码是否过期 1-是 0/其他-否
	private String pwdExpiredFlag;
	
	private String pwdStrategyCd;
	
	// 修改资料
	private WsPlasAcct entity;
	private WsPlasUser entityUser;

	public WsPlasUser getEntityUser() {
		return entityUser;
	}

	public void setEntityUser(WsPlasUser entityUser) {
		this.entityUser = entityUser;
	}

	public WsPlasAcct getEntity() {
		return entity;
	}

	public void setEntity(WsPlasAcct entity) {
		this.entity = entity;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String password() {
		entity = SpringSecurityUtils.getCurrentPlasUser();
		oldPassword = LoginUtil.getPwd(null);
		
		pwdExpiredFlag = SpringSecurityUtils.isPwdModFlag();
		pwdStrategyCd = entity.getPwdStrategyCd();
		
		return "password";
	}
	/**
	 * 修改密码(弹出窗口)
	 * 
	 * @return
	 */
	public String passwordDlg() {
		entity = SpringSecurityUtils.getCurrentPlasUser();
		oldPassword =LoginUtil.getPwd(null);
		return "passwordDlg";
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	public String info() {
		entity = SpringSecurityUtils.getCurrentPlasUser();
		entityUser = Util.getPlasService().getUserByUiid(entity.getUiid());
		return "info";
	}

	/**
	 * 检查输入的旧密码是否正确
	 * 
	 * @return
	 */
	public String isOldPasswordCorrect() {

		WsPlasAcct user = SpringSecurityUtils.getCurrentPlasUser();
		String md5Password = user.getLoginInPassword();
		HttpServletRequest request = ServletActionContext.getRequest();
		String unEncryptOldPassword = request.getParameter("oldPassword").trim();

		if (md5Password.equals(Md5Encrypt.md5(unEncryptOldPassword))) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public String isSamePassword() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String noEncryNewPassword = request.getParameter("newPassword").trim();
		String noEncryNewPassword2 = request.getParameter("newPassword2").trim();

		if (noEncryNewPassword.equals(noEncryNewPassword2)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public void preparePassword() {
		if (entity == null) {
			entity = SpringSecurityUtils.getCurrentPlasUser();
		}
	}

	/**
	 * ajax调用 保存密码修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareSavePaswordChange() throws Exception {
		try {
			entity = Util.getPlasService().getAcctByUiid(SpringSecurityUtils.getCurrentUiid());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改用户资料，获取用户信息异常!" + e.getMessage());
		}
	}

	public String savePaswordChange() {

		String acctId = SpringSecurityUtils.getCurrentAcctId();
		try {
			boolean flag = Util.getPlasService().savePwdChange(acctId, oldPassword, newPassword);

			if (flag) {
				setInfoMsg("您的密码已修改成功!请重新登录。");
				WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
				acct.setLoginInPassword(md5PasswordEncoder.encodePassword(newPassword, ""));
				LoginUtil.setPwd(null, newPassword);
				//很重要
				acct.setPwdLastModDate(new Date());
				if(DictContants.PLAS_PWD_STRATEGY_9.equals(acct.getPwdStrategyCd())){
					acct.setPwdStrategyCd(DictContants.PLAS_PWD_STRATEGY_3);
				}
			} else {
				setInfoMsg("密码修改不成功!");
			}
			
		} catch (Exception e) {
			logger.error("账号["+SpringSecurityUtils.getCurrentUiid()+"]修改密码失败：" + e.getMessage());
			setInfoMsg("密码修改不成功!");
		}
		return "result";
	}

	public void prepareSaveUserInfoChange() throws Exception {
		try {
			entityUser = Util.getPlasService().getUserByUiid(SpringSecurityUtils.getCurrentUiid());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改用户资料，获取用户信息异常!" + e.getMessage());
		}
	}

	public String saveUserInfoChange() {

		try {
			boolean flag = Util.getPlasService().updateUser(entityUser);
			if (flag) {
				setInfoMsg("您的资料已修改成功!");
			} else {
				setInfoMsg("资料修改不成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改用户资料异常!" + e.getMessage());
			setInfoMsg("资料修改不成功!");
		}
		return "result";
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	@Override
	public WsPlasAcct getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (entity == null) {
			entity = SpringSecurityUtils.getCurrentPlasUser();
		}

	}

	@Override
	public String list() throws Exception {

		entity = SpringSecurityUtils.getCurrentPlasUser();
		return SUCCESS;
	}

	/**
	 * 功能:编辑用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String input() throws Exception {
		return SUCCESS;
	}

	@Override
	public void prepareInput() {

	}

	@Override
	public String save() throws Exception {

		// loadDTreeData();
		return RELOAD;
	}

	@Override
	public void prepareSave() {
	}

	/**
	 * 功能:删除用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		return SUCCESS;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getPwdExpiredFlag() {
		return pwdExpiredFlag;
	}

	public void setPwdExpiredFlag(String pwdExpiredFlag) {
		this.pwdExpiredFlag = pwdExpiredFlag;
	}

	public String getPwdStrategyCd() {
		return pwdStrategyCd;
	}

	public void setPwdStrategyCd(String pwdStrategyCd) {
		this.pwdStrategyCd = pwdStrategyCd;
	}
	
}
