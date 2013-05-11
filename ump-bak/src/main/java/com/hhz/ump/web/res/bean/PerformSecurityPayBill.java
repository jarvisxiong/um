/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 4、 履约保证金支付审批表(Contract_ Guar_Pay_Apply)
 * 
 * @author huangbj
 */
public class PerformSecurityPayBill extends BaseTemplatePay {

	// 项目名称 project_name
	// 合同编号 contract_no
	// 合同名称 contract_name
	// 履约保证金起始时间 guarantee_money_start_date
	// 保证金总额 (元) total_guarantee_amt
	// 已返还保证金 (元) Returned_gurantee_amt
	// 拟支付 (元) plan_pay_amt
	// 需说明事项 content_desc

	private String projectName;
	private String projectCd;
	private String contractNo;
	private String contractName;
	private String guaranteeMoneyStartDate;
	private String totalGuaranteeAmt;
	private String returnedGuranteeAmt;
	private String planPayAmt;
	private String contentDesc;

	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTotalGuaranteeAmt() {
		return totalGuaranteeAmt;
	}

	public void setTotalGuaranteeAmt(String totalGuaranteeAmt) {
		this.totalGuaranteeAmt = totalGuaranteeAmt;
	}

	public String getPlanPayAmt() {
		return planPayAmt;
	}

	public void setPlanPayAmt(String planPayAmt) {
		this.planPayAmt = planPayAmt;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGuaranteeMoneyStartDate() {
		return guaranteeMoneyStartDate;
	}

	public void setGuaranteeMoneyStartDate(String guaranteeMoneyStartDate) {
		this.guaranteeMoneyStartDate = guaranteeMoneyStartDate;
	}

	public String getReturnedGuranteeAmt() {
		return returnedGuranteeAmt;
	}

	public void setReturnedGuranteeAmt(String returnedGuranteeAmt) {
		this.returnedGuranteeAmt = returnedGuranteeAmt;
	}

	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
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
		if (contractName!=null)
			return contractName+"合同";
		return null;
	}
}
