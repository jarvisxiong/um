package com.hhz.uums.vo.vw;

import java.math.BigDecimal;

public class VoUser {

	private String userId;
	private String parentOrgId;
	private String userCd;
	private String userName;
	private String uiid;
	private String serviceCd;
	private String sexCd;
	private String userBizCd;
	private BigDecimal sequenceNo;
	private String phone;			//分机号
	private String mobilePhone;		//手机号
	private String mobilePhone2;    //备用手机号
	private String workDutyDesc;	//职位
	private String responsibility;
	
	private String positionNames;

	public VoUser(){
		
	}
	
	public VoUser(String userId, String parentOrgId, String userCd, String userName, String uiid, String serviceCd,
			String sexCd, String userBizCd, BigDecimal sequenceNo, String positionNames) {
		super();
		this.userId = userId;
		this.parentOrgId = parentOrgId;
		this.userCd = userCd;
		this.userName = userName;
		this.uiid = uiid;
		this.serviceCd = serviceCd;
		this.sexCd = sexCd;
		this.userBizCd = userBizCd;
		this.sequenceNo = sequenceNo;
		this.positionNames = positionNames;
	}
	

	public VoUser(String userId, String parentOrgId, String userCd, String userName, String uiid, String serviceCd,
			String sexCd, String userBizCd, BigDecimal sequenceNo) {
		super();
		this.userId = userId;
		this.parentOrgId = parentOrgId;
		this.userCd = userCd;
		this.userName = userName;
		this.uiid = uiid;
		this.serviceCd = serviceCd;
		this.sexCd = sexCd;
		this.userBizCd = userBizCd;
		this.sequenceNo = sequenceNo;
	}
	
	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkDutyDesc() {
		return workDutyDesc;
	}

	public void setWorkDutyDesc(String workDutyDesc) {
		this.workDutyDesc = workDutyDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceCd() {
		return serviceCd;
	}

	public void setServiceCd(String serviceCd) {
		this.serviceCd = serviceCd;
	}

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getUserBizCd() {
		return userBizCd;
	}

	public void setUserBizCd(String userBizCd) {
		this.userBizCd = userBizCd;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getPositionNames() {
		return positionNames;
	}

	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
  
}
