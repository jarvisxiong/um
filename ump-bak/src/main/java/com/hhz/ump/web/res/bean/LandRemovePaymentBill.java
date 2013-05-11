/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 1.土地、拆迁付款审批单
 * 
 * @author huangj 2010-5-31
 */
public class LandRemovePaymentBill extends BaseTemplatePay {

	// 项目名称 Project_Name
	// 合同名称 Contract_Name
	// 合同编号 Contract_No
	// 合同总金额(元) Contract_Total_amt
	// 已付合同款(元) Contract_Paid_Amt
	// 需说明事项 Content_Desc
	

	String projectName;
	String projectCd;
	String contractName;
	String contractNo;
	String contractTotalAmt;
	String contractPaidAmt;

	String contentDesc;

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractTotalAmt() {
		return contractTotalAmt;
	}
	public void setContractTotalAmt(String contractTotalAmt) {
		this.contractTotalAmt = contractTotalAmt;
	}

	public String getContractPaidAmt() {
		return contractPaidAmt;
	}

	public void setContractPaidAmt(String contractPaidAmt) {
		this.contractPaidAmt = contractPaidAmt;
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
