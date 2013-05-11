package com.hhz.uums.vo.vw;

import java.math.BigDecimal;
import java.util.Date;

public class AcctUserVo {

	//uiid,email,activeBl
	private String uiid;
	private String userBizCd;
	private String userName;
	private String email;
	private String sexCd;
    private String plasAcctId;
    private String acctSeqNo;
    private String custLoginName;
    private String realPosCd;
    private String statusCd;//账号状态
    private String serviceStatusCd;//任职状态
    private String authenticTypeCd;
    private String loginInPassword;
    private String lastLoginIp;
    private BigDecimal failureTimes;
    
    private String physicalOrgName;
    private String logicalOrgName;
    
    private String macAddress;
    private String macLockedFlg;
    private String easFlg;
    private String easPasswordSetFlg;
    private String emailFlg;
    private String emailPasswordSetFlg;
    
    private Date effectDate;
    private Date invalidDate;
    private Date lockedDate;
    private Date lastLoginDate;
    private Date lastLogoutDate;
    private Date UpdateDate;
    
	public String getPlasAcctId() {
		return plasAcctId;
	}
	public void setPlasAcctId(String plasAcctId) {
		this.plasAcctId = plasAcctId;
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
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public String getUserBizCd() {
		return userBizCd;
	}
	public void setUserBizCd(String userBizCd) {
		this.userBizCd = userBizCd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getRealPosCd() {
		return realPosCd;
	}
	public void setRealPosCd(String realPosCd) {
		this.realPosCd = realPosCd;
	}
	public String getServiceStatusCd() {
		return serviceStatusCd;
	}
	public void setServiceStatusCd(String serviceStatusCd) {
		this.serviceStatusCd = serviceStatusCd;
	}
	public String getPhysicalOrgName() {
		return physicalOrgName;
	}
	public void setPhysicalOrgName(String physicalOrgName) {
		this.physicalOrgName = physicalOrgName;
	}
	public String getLogicalOrgName() {
		return logicalOrgName;
	}
	public void setLogicalOrgName(String logicalOrgName) {
		this.logicalOrgName = logicalOrgName;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date UpdateDate) {
		this.UpdateDate = UpdateDate;
	} 
}
