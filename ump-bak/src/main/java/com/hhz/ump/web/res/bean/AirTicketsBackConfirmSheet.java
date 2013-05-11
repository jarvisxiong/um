package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseContractTemplate;

/**
 * <strong>机票退票确认单</strong>
 * @author shixy
 *
 * 2010-12-21
 */
public class AirTicketsBackConfirmSheet extends BaseContractTemplate {

	/**
	 * 出行人员
	 */
	private String userName;
	/**
	 * 出行人员cd
	 */
	private String userCd;
	/**
	 * 出行时间
	 */
	private String tripDate;
	/**
	 * 电子客票号
	 */
	private String eticketNo;
	/**
	 * 应退航程
	 */
	private String backVovage;
	/**
	 * 退票费用
	 */
	private String backAmount;
	/**
	 * 退票原因
	 */
	private String backCause;
	
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
		return "电子客票号："+ eticketNo;
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

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}


	public String getEticketNo() {
		return eticketNo;
	}

	public void setEticketNo(String eticketNo) {
		this.eticketNo = eticketNo;
	}

	public String getBackVovage() {
		return backVovage;
	}

	public void setBackVovage(String backVovage) {
		this.backVovage = backVovage;
	}

	public String getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(String backAmount) {
		this.backAmount = backAmount;
	}

	public String getBackCause() {
		return backCause;
	}

	public void setBackCause(String backCause) {
		this.backCause = backCause;
	}

	
}
