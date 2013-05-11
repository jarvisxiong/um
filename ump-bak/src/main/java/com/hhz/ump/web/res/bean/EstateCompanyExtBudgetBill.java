package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 
 * 类名 EstateCompanyExtBudgetBill 创建者 李劲 创建日期 2010-12-3 描述 地产公司月预算外资金申请表
 */
public class EstateCompanyExtBudgetBill extends BaseTemplatePay {

	// 收款单位 payee

	// 公司名称 projectName
	// 预计支付日期 planPayDate

	// 合同总金额(元) contactTotalAmt
	// 合同已付金额(元) contactPayedAmd

	// 申请支付金额(元) applyPayAmt
	// 批准支付金额(元) approvePayAmt

	// 资金用途及支付理由(地产公司填写) useDesc

	private String payee;

	private String projectName;
	private String projectCd;
	private String planPayDate;

	private String contactTotalAmt;
	private String contactPayedAmd;

	private String applyPayAmt;
	private String approvePayAmt;

	private String useDesc;

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPlanPayDate() {
		return planPayDate;
	}

	public void setPlanPayDate(String planPayDate) {
		this.planPayDate = planPayDate;
	}

	public String getContactTotalAmt() {
		return contactTotalAmt;
	}

	public void setContactTotalAmt(String contactTotalAmt) {
		this.contactTotalAmt = contactTotalAmt;
	}

	public String getContactPayedAmd() {
		return contactPayedAmd;
	}

	public void setContactPayedAmd(String contactPayedAmd) {
		this.contactPayedAmd = contactPayedAmd;
	}

	public String getApplyPayAmt() {
		return applyPayAmt;
	}

	public void setApplyPayAmt(String applyPayAmt) {
		this.applyPayAmt = applyPayAmt;
	}

	public String getApprovePayAmt() {
		return approvePayAmt;
	}

	public void setApprovePayAmt(String approvePayAmt) {
		this.approvePayAmt = approvePayAmt;
	}

	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return payee;
	}
}
