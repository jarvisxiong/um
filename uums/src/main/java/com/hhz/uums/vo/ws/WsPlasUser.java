// 
package com.hhz.uums.vo.ws;

// Generated 2010-1-13 13:53:48 by Hibernate Tools 3.2.4.GA

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WsPlasUser implements Serializable {

	private static final long serialVersionUID = 1430573889375216119L;

	private String plasUserId;

	// plasOrg
	private String orgId;
	private String orgCd;
	private String orgBizCd;
	private String orgName;

	private String uiid;
	private String userCd;
	private String userBizCd;
	private String userName;
	private String serviceStatusCd;
	private String sexCd;
	private Date birthday;
	private String idno;
	private String nationCd;
	private String nativeProvinceDesc;
	private String nativePlaceDesc;
	private String marrigeStatusCd;
	private String schoolRecordCd;
	private String gradSchoolDesc;
	private String majorDesc;
	private Date attendWorkDate;
	private String memberTypeCd;
	private String workDutyDesc;
	private String responsibility;
	private String realPosCd;
	private String professionTypeCd;
	private String politicsCd;
	private String otherTypeCd;
	private String email;
	private String fixedPhone;
	private String mobilePhone;
	private String mobilePhone2;
	private String idCardTypeCd;
	private String specialUserFlg;
	private String userTypeCd;
	private String sourceTypeCd;
	private String defaultCredenc;
	private String permissionLevelCd;
	private String emailSignContent;
	private Boolean activeBl;
	private BigDecimal sequenceNo;
	private String remark;

	private String plasCenterId;

	public String getPlasUserId() {
		return plasUserId;
	}

	public void setPlasUserId(String plasUserId) {
		this.plasUserId = plasUserId;
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

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
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

	public String getServiceStatusCd() {
		return serviceStatusCd;
	}

	public void setServiceStatusCd(String serviceStatusCd) {
		this.serviceStatusCd = serviceStatusCd;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getNationCd() {
		return nationCd;
	}

	public void setNationCd(String nationCd) {
		this.nationCd = nationCd;
	}

	public String getNativeProvinceDesc() {
		return nativeProvinceDesc;
	}

	public void setNativeProvinceDesc(String nativeProvinceDesc) {
		this.nativeProvinceDesc = nativeProvinceDesc;
	}

	public String getNativePlaceDesc() {
		return nativePlaceDesc;
	}

	public void setNativePlaceDesc(String nativePlaceDesc) {
		this.nativePlaceDesc = nativePlaceDesc;
	}

	public String getMarrigeStatusCd() {
		return marrigeStatusCd;
	}

	public void setMarrigeStatusCd(String marrigeStatusCd) {
		this.marrigeStatusCd = marrigeStatusCd;
	}

	public String getSchoolRecordCd() {
		return schoolRecordCd;
	}

	public void setSchoolRecordCd(String schoolRecordCd) {
		this.schoolRecordCd = schoolRecordCd;
	}

	public String getGradSchoolDesc() {
		return gradSchoolDesc;
	}

	public void setGradSchoolDesc(String gradSchoolDesc) {
		this.gradSchoolDesc = gradSchoolDesc;
	}

	public String getMajorDesc() {
		return majorDesc;
	}

	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}

	public Date getAttendWorkDate() {
		return attendWorkDate;
	}

	public void setAttendWorkDate(Date attendWorkDate) {
		this.attendWorkDate = attendWorkDate;
	}

	public String getMemberTypeCd() {
		return memberTypeCd;
	}

	public void setMemberTypeCd(String memberTypeCd) {
		this.memberTypeCd = memberTypeCd;
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

	public String getProfessionTypeCd() {
		return professionTypeCd;
	}

	public void setProfessionTypeCd(String professionTypeCd) {
		this.professionTypeCd = professionTypeCd;
	}

	public String getPoliticsCd() {
		return politicsCd;
	}

	public void setPoliticsCd(String politicsCd) {
		this.politicsCd = politicsCd;
	}

	public String getOtherTypeCd() {
		return otherTypeCd;
	}

	public void setOtherTypeCd(String otherTypeCd) {
		this.otherTypeCd = otherTypeCd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getIdCardTypeCd() {
		return idCardTypeCd;
	}

	public void setIdCardTypeCd(String idCardTypeCd) {
		this.idCardTypeCd = idCardTypeCd;
	}

	public String getSpecialUserFlg() {
		return specialUserFlg;
	}

	public void setSpecialUserFlg(String specialUserFlg) {
		this.specialUserFlg = specialUserFlg;
	}

	public String getUserTypeCd() {
		return userTypeCd;
	}

	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}

	public String getSourceTypeCd() {
		return sourceTypeCd;
	}

	public void setSourceTypeCd(String sourceTypeCd) {
		this.sourceTypeCd = sourceTypeCd;
	}

	public String getDefaultCredenc() {
		return defaultCredenc;
	}

	public void setDefaultCredenc(String defaultCredenc) {
		this.defaultCredenc = defaultCredenc;
	}

	public String getPermissionLevelCd() {
		return permissionLevelCd;
	}

	public void setPermissionLevelCd(String permissionLevelCd) {
		this.permissionLevelCd = permissionLevelCd;
	}

	public String getEmailSignContent() {
		return emailSignContent;
	}

	public void setEmailSignContent(String emailSignContent) {
		this.emailSignContent = emailSignContent;
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

	public String getPlasCenterId() {
		return plasCenterId;
	}

	public void setPlasCenterId(String plasCenterId) {
		this.plasCenterId = plasCenterId;
	}

	public String getOrgBizCd() {
		return orgBizCd;
	}

	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	} 
}
