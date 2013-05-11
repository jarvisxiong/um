/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 财务软件升级申请表
 * 
 */
public class FinanceSoftUpgradeApplySeet extends BaseTemplatePay {

	// 申请单位    申请人
	// 申请日期 预计升级日期
	// 升级原因
	// 费用预算
	
	private String applyOrgName;
	private String applyOrgCd;
	private String applyUserName;
	private String applyUserCd;
	private String applyDate;
	private String planUpGradeDate;
	private String upGradedReasonDesc;
	private String expensesBudgetDesc;
	  
	
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	public String getApplyOrgCd() {
		return applyOrgCd;
	}
	public void setApplyOrgCd(String applyOrgCd) {
		this.applyOrgCd = applyOrgCd;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getApplyUserCd() {
		return applyUserCd;
	}
	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getPlanUpGradeDate() {
		return planUpGradeDate;
	}
	public void setPlanUpGradeDate(String planUpGradeDate) {
		this.planUpGradeDate = planUpGradeDate;
	}  
	public String getUpGradedReasonDesc() {
		return upGradedReasonDesc;
	}
	public void setUpGradedReasonDesc(String upGradedReasonDesc) {
		this.upGradedReasonDesc = upGradedReasonDesc;
	}
	public String getExpensesBudgetDesc() {
		return expensesBudgetDesc;
	}
	public void setExpensesBudgetDesc(String expensesBudgetDesc) {
		this.expensesBudgetDesc = expensesBudgetDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.applyOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.applyOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applyOrgName;
	}
}
