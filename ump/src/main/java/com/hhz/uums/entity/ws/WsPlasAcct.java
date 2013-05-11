package com.hhz.uums.entity.ws;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WsPlasAcct implements Serializable {

	private static final long serialVersionUID = 431625935900735494L;

	private String plasAcctId;

	// plasUser
	private String userId;
	private String userCd;
	private String userName;
	private String orgId;
	private String orgCd;
	private String orgName;
	private String realPositonName;
	private String idno;

	private String uiid;
	private String acctSeqNo;
	private String custLoginName;
	private String statusCd;
	private String email;
	private String authenticTypeCd;
	private String loginInPassword;
	private Date lockedDate;
	private Date lastLoginDate;
	private Date lastLogoutDate;
	private String lastLoginIp;
	private Date effectDate;
	private Date invalidDate;
	private BigDecimal failureTimes;
	private String macAddress;
	private String macLockedFlg;
	private String easFlg;
	private String easPasswordSetFlg;
	private String emailFlg;
	private String emailPasswordSetFlg;
	private String mysoftFlg;
	private String mysoftPasswordSetFlg;
	private String cmailFlg;
	private String cmailPasswordSetFlg;
	private String userTypeCd;
	
	private Date pwdLastModDate;
	private String pwdStrategyCd;

	private Boolean activeBl;
	private BigDecimal sequenceNo;
	private String remark;
	public String getPlasAcctId() {
		return plasAcctId;
	}
	public void setPlasAcctId(String plasAcctId) {
		this.plasAcctId = plasAcctId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRealPositonName() {
		return realPositonName;
	}
	public void setRealPositonName(String realPositonName) {
		this.realPositonName = realPositonName;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public String getAcctSeqNo() {
		return acctSeqNo;
	}
	public void setAcctSeqNo(String acctSeqNo) {
		this.acctSeqNo = acctSeqNo;
	}
	public String getCustLoginName() {
		return custLoginName;
	}
	public void setCustLoginName(String custLoginName) {
		this.custLoginName = custLoginName;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthenticTypeCd() {
		return authenticTypeCd;
	}
	public void setAuthenticTypeCd(String authenticTypeCd) {
		this.authenticTypeCd = authenticTypeCd;
	}
	public String getLoginInPassword() {
		return loginInPassword;
	}
	public void setLoginInPassword(String loginInPassword) {
		this.loginInPassword = loginInPassword;
	}
	public Date getLockedDate() {
		return lockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastLogoutDate() {
		return lastLogoutDate;
	}
	public void setLastLogoutDate(Date lastLogoutDate) {
		this.lastLogoutDate = lastLogoutDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public Date getInvalidDate() {
		return invalidDate;
	}
	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}
	public BigDecimal getFailureTimes() {
		return failureTimes;
	}
	public void setFailureTimes(BigDecimal failureTimes) {
		this.failureTimes = failureTimes;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getMacLockedFlg() {
		return macLockedFlg;
	}
	public void setMacLockedFlg(String macLockedFlg) {
		this.macLockedFlg = macLockedFlg;
	}
	public String getEasFlg() {
		return easFlg;
	}
	public void setEasFlg(String easFlg) {
		this.easFlg = easFlg;
	}
	public String getEasPasswordSetFlg() {
		return easPasswordSetFlg;
	}
	public void setEasPasswordSetFlg(String easPasswordSetFlg) {
		this.easPasswordSetFlg = easPasswordSetFlg;
	}
	public String getEmailFlg() {
		return emailFlg;
	}
	public void setEmailFlg(String emailFlg) {
		this.emailFlg = emailFlg;
	}
	public String getEmailPasswordSetFlg() {
		return emailPasswordSetFlg;
	}
	public void setEmailPasswordSetFlg(String emailPasswordSetFlg) {
		this.emailPasswordSetFlg = emailPasswordSetFlg;
	}
	public String getMysoftFlg() {
		return mysoftFlg;
	}
	public void setMysoftFlg(String mysoftFlg) {
		this.mysoftFlg = mysoftFlg;
	}
	public String getMysoftPasswordSetFlg() {
		return mysoftPasswordSetFlg;
	}
	public void setMysoftPasswordSetFlg(String mysoftPasswordSetFlg) {
		this.mysoftPasswordSetFlg = mysoftPasswordSetFlg;
	}
	public String getCmailFlg() {
		return cmailFlg;
	}
	public void setCmailFlg(String cmailFlg) {
		this.cmailFlg = cmailFlg;
	}
	public String getCmailPasswordSetFlg() {
		return cmailPasswordSetFlg;
	}
	public void setCmailPasswordSetFlg(String cmailPasswordSetFlg) {
		this.cmailPasswordSetFlg = cmailPasswordSetFlg;
	}
	public String getUserTypeCd() {
		return userTypeCd;
	}
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	public Date getPwdLastModDate() {
		return pwdLastModDate;
	}
	public void setPwdLastModDate(Date pwdLastModDate) {
		this.pwdLastModDate = pwdLastModDate;
	}
	public String getPwdStrategyCd() {
		return pwdStrategyCd;
	}
	public void setPwdStrategyCd(String pwdStrategyCd) {
		this.pwdStrategyCd = pwdStrategyCd;
	}
	public Boolean getActiveBl() {
		return activeBl;
	}
	public void setActiveBl(Boolean activeBl) {
		this.activeBl = activeBl;
	}
	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
