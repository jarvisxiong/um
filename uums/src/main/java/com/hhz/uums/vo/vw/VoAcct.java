package com.hhz.uums.vo.vw;

import java.math.BigDecimal;

public class VoAcct {

	private String acctId;
	private String parentOrgId;
	private String userId;
	private String userName;
	private String custAcctName;
	private String statusCd;
	private String uiid;
	private String workDutyDesc;
	private String realPosCd;//abandon
	private String fixedPhone;
	private String mobilePhone;
	private String email;
	private String sexCd;
	
	private String orgCd;
	private String orgName;
	private String serviceStatusCd;
	private String relPositionSize;
	
	private String posOrgId;
	private String posOrgCd;
	private String posOrgName;
	
	private String applyStatusCd;

	private String creator;
	private String createDate;
	
	private String sysPosName;
	private String sysPositionId;
	
	//coremail convert 
	private BigDecimal sequenceNo;
	private String md5password;
	private String mobilePhone2;
	private String birthday;//yyyy-mm-dd
	private String orgBizCd;
	
	//未加密密码
	private String oriPwd;
	
	
	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkDutyDesc() {
		return workDutyDesc;
	}

	public void setWorkDutyDesc(String workDutyDesc) {
		this.workDutyDesc = workDutyDesc;
	}

	public String getRealPosCd() {
		return realPosCd;
	}

	public void setRealPosCd(String realPosCd) {
		this.realPosCd = realPosCd;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public String getCustAcctName() {
		return custAcctName;
	}

	public void setCustAcctName(String custAcctName) {
		this.custAcctName = custAcctName;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
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

	public String getServiceStatusCd() {
		return serviceStatusCd;
	}

	public void setServiceStatusCd(String serviceStatusCd) {
		this.serviceStatusCd = serviceStatusCd;
	}

	public String getRelPositionSize() {
		return relPositionSize;
	}

	public void setRelPositionSize(String relPositionSize) {
		this.relPositionSize = relPositionSize;
	}

	public String getPosOrgId() {
		return posOrgId;
	}

	public void setPosOrgId(String posOrgId) {
		this.posOrgId = posOrgId;
	}

	public String getPosOrgCd() {
		return posOrgCd;
	}

	public void setPosOrgCd(String posOrgCd) {
		this.posOrgCd = posOrgCd;
	}

	public String getPosOrgName() {
		return posOrgName;
	}

	public void setPosOrgName(String posOrgName) {
		this.posOrgName = posOrgName;
	}

	public String getApplyStatusCd() {
		return applyStatusCd;
	}

	public void setApplyStatusCd(String applyStatusCd) {
		this.applyStatusCd = applyStatusCd;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSysPosName() {
		return sysPosName;
	}

	public void setSysPosName(String sysPosName) {
		this.sysPosName = sysPosName;
	}

	public String getSysPositionId() {
		return sysPositionId;
	}

	public void setSysPositionId(String sysPositionId) {
		this.sysPositionId = sysPositionId;
	}

	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getMd5password() {
		return md5password;
	}

	public void setMd5password(String md5password) {
		this.md5password = md5password;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getOrgBizCd() {
		return orgBizCd;
	}

	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}

	public String getOriPwd() {
		return oriPwd;
	}

	public void setOriPwd(String oriPwd) {
		this.oriPwd = oriPwd;
	}

}
