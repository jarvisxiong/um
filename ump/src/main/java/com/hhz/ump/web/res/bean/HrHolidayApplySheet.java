package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 请假单(新)
 *
 */
public class HrHolidayApplySheet extends BaseTemplate {
	 
	//姓  名
	private String userName;
	private String userCd;
	// 中  心：                   
	private String center;
	private String centerCd;
	// 部  门：                             
	private String dept;
	//职  位：
	private String position;
	//职  级：                     
	private String positionLevel;
	//入职日期：                         
	private String joinDate;
	
	//假期
	private List<HrHolidayOtherProperty> otherProperties = new ArrayList<HrHolidayOtherProperty>();

	//上班日期:             
	private String workDate;
		
	//请假期间联系地址/电话 ：
	private String contactWay;
	
	//请假原因
	private String holidayReason;

	/**
	 * @return the holidayReason
	 */
	public String getHolidayReason() {
		return holidayReason;
	}

	/**
	 * @param holidayReason the holidayReason to set
	 */
	public void setHolidayReason(String holidayReason) {
		this.holidayReason = holidayReason;
	}

	/***************人事部填写********************/
	//应得年假 ：	
	private String shouldHoliday;
	//已休年假 ：
	private String pastHoliday;
	//剩余年假 ：
	private String leftHoliday;
	//其他说明 :
	private String otherRemark;

	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return position;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
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

	public String getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getShouldHoliday() {
		return shouldHoliday;
	}

	public void setShouldHoliday(String shouldHoliday) {
		this.shouldHoliday = shouldHoliday;
	}

	public String getPastHoliday() {
		return pastHoliday;
	}

	public void setPastHoliday(String pastHoliday) {
		this.pastHoliday = pastHoliday;
	}

	public String getLeftHoliday() {
		return leftHoliday;
	}

	public void setLeftHoliday(String leftHoliday) {
		this.leftHoliday = leftHoliday;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public List<HrHolidayOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<HrHolidayOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

}
