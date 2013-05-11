package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 出差申请计划单
 * @author shixy
 *
 * 2010-12-24
 */
public class BusinessTripPlanSheet extends BaseTemplate {

	/**
	 * 申请人
	 */
	private String userName;
	private String userCd;
	private String centerOrgName;
	private String centerOrgCd;
	/**
	 * 部门
	 */
	private String dept;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 随行人员
	 */
	private String entourageUserName;
	private String entourageUserCd;
	/**
	 * 出差地点
	 */
	private String tripPlace;
	/**
	 * 出差时间
	 */
	private String tripTimeBegin;
	private String tripaTimeEnd;
	/**
	 * 出差事由	
	 */
	private String tripReason;
	
	// 预计差旅借款
	private String lendMoneyAmt;
	
	/**
	 * 交通方式
	 */
	private String travelWay;
	/**
	 * 行程时间 计划安排
	 */
	private String tripDesc;
	
	//机票订购list
	private List<AirTicketsBookingSheet> airTicketsBookingSheet = new ArrayList<AirTicketsBookingSheet>();
	/**
	 * 合计出差时间(天)
	 */
	private String tripDay;
	/**
	 * 出差申请每日计划
	 */
	private List<BizTripApplyDayPlan> tripDayPlan = new ArrayList<BizTripApplyDayPlan>();

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return centerOrgName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return centerOrgCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return tripPlace;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public String getTripPlace() {
		return tripPlace;
	}

	public void setTripPlace(String tripPlace) {
		this.tripPlace = tripPlace;
	}

	public String getTripTimeBegin() {
		return tripTimeBegin;
	}

	public void setTripTimeBegin(String tripTimeBegin) {
		this.tripTimeBegin = tripTimeBegin;
	}

	public String getTripaTimeEnd() {
		return tripaTimeEnd;
	}

	public void setTripaTimeEnd(String tripaTimeEnd) {
		this.tripaTimeEnd = tripaTimeEnd;
	}

	public String getTripReason() {
		return tripReason;
	}

	public void setTripReason(String tripReason) {
		this.tripReason = tripReason;
	}

	public String getTripDesc() {
		return tripDesc;
	}

	public void setTripDesc(String tripDesc) {
		this.tripDesc = tripDesc;
	}

	public String getTravelWay() {
		return travelWay;
	}

	public void setTravelWay(String travelWay) {
		this.travelWay = travelWay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getCenterOrgName() {
		return centerOrgName;
	}

	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}

	public String getCenterOrgCd() {
		return centerOrgCd;
	}

	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}

	public String getLendMoneyAmt() {
		return lendMoneyAmt;
	}

	public void setLendMoneyAmt(String lendMoneyAmt) {
		this.lendMoneyAmt = lendMoneyAmt;
	}

	public String getEntourageUserName() {
		return entourageUserName;
	}

	public void setEntourageUserName(String entourageUserName) {
		this.entourageUserName = entourageUserName;
	}

	public String getEntourageUserCd() {
		return entourageUserCd;
	}

	public void setEntourageUserCd(String entourageUserCd) {
		this.entourageUserCd = entourageUserCd;
	}

	/**
	 * @return the airTicketsBookingSheet
	 */
	public List<AirTicketsBookingSheet> getAirTicketsBookingSheet() {
		return airTicketsBookingSheet;
	}

	/**
	 * @param airTicketsBookingSheet the airTicketsBookingSheet to set
	 */
	public void setAirTicketsBookingSheet(List<AirTicketsBookingSheet> airTicketsBookingSheet) {
		this.airTicketsBookingSheet = airTicketsBookingSheet;
	}

	/**
	 * @return the tripDay
	 */
	public String getTripDay() {
		return tripDay;
	}

	/**
	 * @param tripDay the tripDay to set
	 */
	public void setTripDay(String tripDay) {
		this.tripDay = tripDay;
	}

	/**
	 * @return the tripDayPlan
	 */
	public List<BizTripApplyDayPlan> getTripDayPlan() {
		return tripDayPlan;
	}

	/**
	 * @param tripDayPlan the tripDayPlan to set
	 */
	public void setTripDayPlan(List<BizTripApplyDayPlan> tripDayPlan) {
		this.tripDayPlan = tripDayPlan;
	}

}
