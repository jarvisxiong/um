package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 地产公司月预算外支出申请表（集团拨款支付）
 * @author baolm
 *
 * 2011-04-12
 */
public class ApplicationMonthOffBudgetExpend extends BaseTemplate {
	
	/**
	 * 收款单位
	 */
	private String payee;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 预算支付日期
	 */
	private String budgetPayDate;
	
	/**
	 * 合同总金额（元）
	 */
	private String contractTotalAmount;
	
	/**
	 * 合同已付金额（元）
	 */
	private String contractPayedAmount;
	
	/**
	 * 申请支付金额（元）
	 */
	private String applicationPayedAmount;
	
	/**
	 * 批准支付金额（元）
	 */
	private String approvedPayedAmount;
	
	/**
	 * □付款审批手续已完成（需后附）
	 */
	private String paymentApproveState1;
	
	/**
	 * □付款审批手续未完成（需同时报）
	 */
	private String paymentApproveState2;
	
	/**
	 * 支出用途及预算外原因
	 */
	private String offBudgetExpendUseAndReason;
	
	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	@Override
	public String getCompanyName() {
		return companyName;
	}

	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBudgetPayDate() {
		return budgetPayDate;
	}

	public void setBudgetPayDate(String budgetPayDate) {
		this.budgetPayDate = budgetPayDate;
	}

	public String getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(String contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public String getContractPayedAmount() {
		return contractPayedAmount;
	}

	public void setContractPayedAmount(String contractPayedAmount) {
		this.contractPayedAmount = contractPayedAmount;
	}

	public String getApplicationPayedAmount() {
		return applicationPayedAmount;
	}

	public void setApplicationPayedAmount(String applicationPayedAmount) {
		this.applicationPayedAmount = applicationPayedAmount;
	}

	public String getApprovedPayedAmount() {
		return approvedPayedAmount;
	}

	public void setApprovedPayedAmount(String approvedPayedAmount) {
		this.approvedPayedAmount = approvedPayedAmount;
	}

	public String getPaymentApproveState1() {
		return paymentApproveState1;
	}

	public void setPaymentApproveState1(String paymentApproveState1) {
		this.paymentApproveState1 = paymentApproveState1;
	}

	public String getPaymentApproveState2() {
		return paymentApproveState2;
	}

	public void setPaymentApproveState2(String paymentApproveState2) {
		this.paymentApproveState2 = paymentApproveState2;
	}

	public String getOffBudgetExpendUseAndReason() {
		return offBudgetExpendUseAndReason;
	}

	public void setOffBudgetExpendUseAndReason(String offBudgetExpendUseAndReason) {
		this.offBudgetExpendUseAndReason = offBudgetExpendUseAndReason;
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
		return payee;
	}

}
