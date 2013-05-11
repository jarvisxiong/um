package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/*
 * 特殊费用申请表
 */
public class SpecialCostApply extends BaseTemplatePay {
	// 申请公司/总部中心 apply_org_name
	// 资金用途 funds_use_desc

	// 资金数额 (元) funds_amt
	// 列支科目 subject_desc

	// 用款时间 payment_date
	// 需说明事项 content_desc

	private String applyOrgName;
	private String applyOrgCd;
	private String fundsUseDesc;
	private String fundsAmt;

	private String subjectDesc;
	private String paymentDate;

	private String contentDesc;

	public String getApplyOrgName() {
		return applyOrgName;
	}

	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}

	public String getFundsUseDesc() {
		return fundsUseDesc;
	}

	public void setFundsUseDesc(String fundsUseDesc) {
		this.fundsUseDesc = fundsUseDesc;
	}

	public String getFundsAmt() {
		return fundsAmt;
	}

	public void setFundsAmt(String fundsAmt) {
		this.fundsAmt = fundsAmt;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getApplyOrgCd() {
		return applyOrgCd;
	}

	public void setApplyOrgCd(String applyOrgCd) {
		this.applyOrgCd = applyOrgCd;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return applyOrgCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applyOrgName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fundsUseDesc;
	}
}
