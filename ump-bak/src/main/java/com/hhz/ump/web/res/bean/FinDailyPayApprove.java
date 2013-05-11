package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 下属公司日常业务支出审批表
 * @author baolm
 *
 * 2011-05-31
 */
public class FinDailyPayApprove extends BaseTemplate {
	
	private String name; // 公司名称
	private String contCd; // 合同编号
	private String currPay; // 本次支付金额(元)
	private String currDate; // 本次支付时间
	private String totalAmount; // 合同总金额(元)
	private String paidAmount; // 已付合同款(元)
	private String reason; // 付款原因

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContCd() {
		return contCd;
	}

	public void setContCd(String contCd) {
		this.contCd = contCd;
	}

	public String getCurrPay() {
		return currPay;
	}

	public void setCurrPay(String currPay) {
		this.currPay = currPay;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

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

}
