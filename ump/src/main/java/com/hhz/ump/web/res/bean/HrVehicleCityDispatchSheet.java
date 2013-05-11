/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 *  市内派车单
 * 
 */
public class HrVehicleCityDispatchSheet extends BaseTemplatePay {
	
	// 用车人 userCd,userName
	// 职 位    positionCd,positionName
	// 部 门   deptOrgCd,deptOrgName
	// 中 心  centerOrgCd,centerOrgName
	// 起讫地 departureArrivalPlaces
	// 用车时间段 timeSlotDesc
	// 随行人数  menberDesc
	// 事 由    subjectDesc
	// 驾驶员 driverName
	// 车号  wagonNumber
	// 公里数 miles

	private String userCd;
	private String userName;
	private String positionCd;
	private String positionName;
	private String deptOrgCd;
	private String deptOrgName;
	private String centerOrgCd;
	private String centerOrgName;
	private String departureArrivalPlaces;
	private String timeSlotDesc;
	private String memberDesc;
	private String subjectDesc;
	private String driverName;
	private String wagonNumber;
	private String miles;
 
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
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDeptOrgCd() {
		return deptOrgCd;
	}
	public void setDeptOrgCd(String deptOrgCd) {
		this.deptOrgCd = deptOrgCd;
	}
	public String getDeptOrgName() {
		return deptOrgName;
	}
	public void setDeptOrgName(String deptOrgName) {
		this.deptOrgName = deptOrgName;
	}
	public String getCenterOrgCd() {
		return centerOrgCd;
	}
	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}
	public String getCenterOrgName() {
		return centerOrgName;
	}
	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}
	public String getDepartureArrivalPlaces() {
		return departureArrivalPlaces;
	}
	public void setDepartureArrivalPlaces(String departureArrivalPlaces) {
		this.departureArrivalPlaces = departureArrivalPlaces;
	}
	public String getTimeSlotDesc() {
		return timeSlotDesc;
	}
	public void setTimeSlotDesc(String timeSlotDesc) {
		this.timeSlotDesc = timeSlotDesc;
	} 
	public String getMemberDesc() {
		return memberDesc;
	}
	public void setMemberDesc(String memberDesc) {
		this.memberDesc = memberDesc;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getWagonNumber() {
		return wagonNumber;
	}
	public void setWagonNumber(String wagonNumber) {
		this.wagonNumber = wagonNumber;
	}
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.centerOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.centerOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.centerOrgName;
	}
}
