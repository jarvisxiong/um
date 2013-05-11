package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 外派人员机票订购申请单
 * @author shixy
 *
 * 2010-12-28
 */
public class SendAirTicketsBookingSheet extends BaseTemplate {

	/**
	 * 出行人员
	 */
	private String userName;
	
	/**
	 * 出行人员cd
	 */
	private String userCd;
	
	/**
	 * 起迄地
	 */
	private String beginEndPlace;
	
	/**
	 * 身份证号
	 */
	private String idCardNo;
	
	/**
	 * 出行事由
	 */
	private String tripCause;
	
	/**
	 * 出行日期
	 */
	private String tripDate;
	
	/**
	 * 费用承担部门
	 */
	private String costDept;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return userCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return beginEndPlace;
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

	public String getBeginEndPlace() {
		return beginEndPlace;
	}

	public void setBeginEndPlace(String beginEndPlace) {
		this.beginEndPlace = beginEndPlace;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getTripCause() {
		return tripCause;
	}

	public void setTripCause(String tripCause) {
		this.tripCause = tripCause;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getCostDept() {
		return costDept;
	}

	public void setCostDept(String costDept) {
		this.costDept = costDept;
	}

	
}
