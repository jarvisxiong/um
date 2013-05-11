package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 请假单
 * @author shixy
 *
 * 2010-12-24
 */
public class RequestHolidaySheet extends BaseTemplate {
	 
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
	/**************年假**************/
	//由
	private String beginDate1;
	//至	
	private String endDate1;
	//合计请假天数	
	private String totalDays1;
	//法定假日
	private String offiHolidays1;
	//本休日
	private String holidays1;
	//注
	private String remark1;
	
	/**************婚假**************/
	//由
	private String beginDate2;
	//至	
	private String endDate2;
	//合计请假天数	
	private String totalDays2;
	//法定假日
	private String offiHolidays2;
	//本休日
	private String holidays2;
	//注
	private String remark2;
	
	/**************产  假**************/
	//由
	private String beginDate3;
	//至	
	private String endDate3;
	//合计请假天数	
	private String totalDays3;
	//法定假日
	private String offiHolidays3;
	//本休日
	private String holidays3;
	//注
	private String remark3;
	
	/**************丧  假**************/
	//由
	private String beginDate4;
	//至	
	private String endDate4;
	//合计请假天数	
	private String totalDays4;
	//法定假日
	private String offiHolidays4;
	//本休日
	private String holidays4;
	//注
	private String remark4;
	
	/**************病  假**************/
	//由
	private String beginDate5;
	//至	
	private String endDate5;
	//合计请假天数	
	private String totalDays5;
	//法定假日
	private String offiHolidays5;
	//本休日
	private String holidays5;
	//注
	private String remark5;
	
	/**************事  假**************/
	//由
	private String beginDate6;
	//至	
	private String endDate6;
	//合计请假天数	
	private String totalDays6;
	//法定假日
	private String offiHolidays6;
	//本休日
	private String holidays6;
	//注
	private String remark6;
	
	/**************其  他**************/
	//由
	private String beginDate7;
	//至	
	private String endDate7;
	//合计请假天数	
	private String totalDays7;
	//法定假日
	private String offiHolidays7;
	//本休日
	private String holidays7;
	//注
	private String remark7;
	
	//请假总天数:         天，
	private String totalDays;
	
	//由:
	private String beginDate;
	//至：                  ，
	private String endDate;
	//上班日期:             
	private String workDate;
		
	//请假期间联系地址/电话 ：
	private String contactWay;
	
	//请假原因
	private String holidayReason;

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
		return null;
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

	public String getBeginDate1() {
		return beginDate1;
	}

	public void setBeginDate1(String beginDate1) {
		this.beginDate1 = beginDate1;
	}

	public String getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	public String getTotalDays1() {
		return totalDays1;
	}

	public void setTotalDays1(String totalDays1) {
		this.totalDays1 = totalDays1;
	}

	public String getOffiHolidays1() {
		return offiHolidays1;
	}

	public void setOffiHolidays1(String offiHolidays1) {
		this.offiHolidays1 = offiHolidays1;
	}

	public String getHolidays1() {
		return holidays1;
	}

	public void setHolidays1(String holidays1) {
		this.holidays1 = holidays1;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getBeginDate2() {
		return beginDate2;
	}

	public void setBeginDate2(String beginDate2) {
		this.beginDate2 = beginDate2;
	}

	public String getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(String endDate2) {
		this.endDate2 = endDate2;
	}

	public String getTotalDays2() {
		return totalDays2;
	}

	public void setTotalDays2(String totalDays2) {
		this.totalDays2 = totalDays2;
	}

	public String getOffiHolidays2() {
		return offiHolidays2;
	}

	public void setOffiHolidays2(String offiHolidays2) {
		this.offiHolidays2 = offiHolidays2;
	}

	public String getHolidays2() {
		return holidays2;
	}

	public void setHolidays2(String holidays2) {
		this.holidays2 = holidays2;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getBeginDate3() {
		return beginDate3;
	}

	public void setBeginDate3(String beginDate3) {
		this.beginDate3 = beginDate3;
	}

	public String getEndDate3() {
		return endDate3;
	}

	public void setEndDate3(String endDate3) {
		this.endDate3 = endDate3;
	}

	public String getTotalDays3() {
		return totalDays3;
	}

	public void setTotalDays3(String totalDays3) {
		this.totalDays3 = totalDays3;
	}

	public String getOffiHolidays3() {
		return offiHolidays3;
	}

	public void setOffiHolidays3(String offiHolidays3) {
		this.offiHolidays3 = offiHolidays3;
	}

	public String getHolidays3() {
		return holidays3;
	}

	public void setHolidays3(String holidays3) {
		this.holidays3 = holidays3;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getBeginDate4() {
		return beginDate4;
	}

	public void setBeginDate4(String beginDate4) {
		this.beginDate4 = beginDate4;
	}

	public String getEndDate4() {
		return endDate4;
	}

	public void setEndDate4(String endDate4) {
		this.endDate4 = endDate4;
	}

	public String getTotalDays4() {
		return totalDays4;
	}

	public void setTotalDays4(String totalDays4) {
		this.totalDays4 = totalDays4;
	}

	public String getOffiHolidays4() {
		return offiHolidays4;
	}

	public void setOffiHolidays4(String offiHolidays4) {
		this.offiHolidays4 = offiHolidays4;
	}

	public String getHolidays4() {
		return holidays4;
	}

	public void setHolidays4(String holidays4) {
		this.holidays4 = holidays4;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getBeginDate5() {
		return beginDate5;
	}

	public void setBeginDate5(String beginDate5) {
		this.beginDate5 = beginDate5;
	}

	public String getEndDate5() {
		return endDate5;
	}

	public void setEndDate5(String endDate5) {
		this.endDate5 = endDate5;
	}

	public String getTotalDays5() {
		return totalDays5;
	}

	public void setTotalDays5(String totalDays5) {
		this.totalDays5 = totalDays5;
	}

	public String getOffiHolidays5() {
		return offiHolidays5;
	}

	public void setOffiHolidays5(String offiHolidays5) {
		this.offiHolidays5 = offiHolidays5;
	}

	public String getHolidays5() {
		return holidays5;
	}

	public void setHolidays5(String holidays5) {
		this.holidays5 = holidays5;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getBeginDate6() {
		return beginDate6;
	}

	public void setBeginDate6(String beginDate6) {
		this.beginDate6 = beginDate6;
	}

	public String getEndDate6() {
		return endDate6;
	}

	public void setEndDate6(String endDate6) {
		this.endDate6 = endDate6;
	}

	public String getTotalDays6() {
		return totalDays6;
	}

	public void setTotalDays6(String totalDays6) {
		this.totalDays6 = totalDays6;
	}

	public String getOffiHolidays6() {
		return offiHolidays6;
	}

	public void setOffiHolidays6(String offiHolidays6) {
		this.offiHolidays6 = offiHolidays6;
	}

	public String getHolidays6() {
		return holidays6;
	}

	public void setHolidays6(String holidays6) {
		this.holidays6 = holidays6;
	}

	public String getRemark6() {
		return remark6;
	}

	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}

	public String getBeginDate7() {
		return beginDate7;
	}

	public void setBeginDate7(String beginDate7) {
		this.beginDate7 = beginDate7;
	}

	public String getEndDate7() {
		return endDate7;
	}

	public void setEndDate7(String endDate7) {
		this.endDate7 = endDate7;
	}

	public String getTotalDays7() {
		return totalDays7;
	}

	public void setTotalDays7(String totalDays7) {
		this.totalDays7 = totalDays7;
	}

	public String getOffiHolidays7() {
		return offiHolidays7;
	}

	public void setOffiHolidays7(String offiHolidays7) {
		this.offiHolidays7 = offiHolidays7;
	}

	public String getHolidays7() {
		return holidays7;
	}

	public void setHolidays7(String holidays7) {
		this.holidays7 = holidays7;
	}

	public String getRemark7() {
		return remark7;
	}

	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

}
