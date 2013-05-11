/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 5、 工程、设备材料质保支付审批表(Warranty_Pay_Apply)
 * 
 * @author huangbj
 */
public class MaterialWarrantyPayBill extends BaseTemplatePay {

	// 项目名称project_name
	// 合同编号 contract_no
	// 合同名称 contract_name
	// 总价款 (元) total_amt
	// 应付质保金 (元) ought_pay_guarantee_amt
	// 质保期起始时间 warranty_period_start_date
	// 需说明事项 content_desc

	private String projectName;
	private String projectCd;
	private String contractNo;
	private String contractName;
	private String totalAmt;
	private String oughtPayGuaranteeAmt;
	private String warrantyPeriodStartDate;
	private String paidAmt;
	private String contentDesc;

	

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getOughtPayGuaranteeAmt() {
		return oughtPayGuaranteeAmt;
	}

	public void setOughtPayGuaranteeAmt(String oughtPayGuaranteeAmt) {
		this.oughtPayGuaranteeAmt = oughtPayGuaranteeAmt;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWarrantyPeriodStartDate() {
		return warrantyPeriodStartDate;
	}

	public void setWarrantyPeriodStartDate(String warrantyPeriodStartDate) {
		this.warrantyPeriodStartDate = warrantyPeriodStartDate;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
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
