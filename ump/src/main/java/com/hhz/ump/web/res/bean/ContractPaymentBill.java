/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 3、 合同付款审批表
 * 
 * @author huangbj
 */
public class ContractPaymentBill extends BaseTemplatePay {

	// 项目名称 project_name
	// 合同编号 contract_no
	// 总价款 (元) total_payment_amt
	// 已付款 (元) paid_amt
	// 本次支付金额 (元) cur_payment_amt
	// 需说明事项 content_desc

	private String contractArea1;//合同规定范围内
	private String contractArea2;//超出合同规定
	private String projectName;
	private String projectCd;
	private String contractNo;
	private String totalPaymentAmt;
	private String paidAmt;
	private String contentDesc;
	private String contractName;//合同名称:
	private String contractTotalAmt;//合同总金额(元):
	private String contractPaidAmt;//已付合同款(元):
	
	//结算审批表
	private String settAppFileId;
	
	//质保金
	private String appKeepFile;
	
	//结算付款
	private String appCloseFile;
	
	public String getSettAppFileId() {
		return settAppFileId;
	}
	public void setSettAppFileId(String settAppFileId) {
		this.settAppFileId = settAppFileId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTotalPaymentAmt() {
		return totalPaymentAmt;
	}

	public void setTotalPaymentAmt(String totalPaymentAmt) {
		this.totalPaymentAmt = totalPaymentAmt;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	/**
	 * @return the appKeepFile
	 */
	public String getAppKeepFile() {
		return appKeepFile;
	}
	/**
	 * @param appKeepFile the appKeepFile to set
	 */
	public void setAppKeepFile(String appKeepFile) {
		this.appKeepFile = appKeepFile;
	}
	/**
	 * @return the appCloseFile
	 */
	public String getAppCloseFile() {
		return appCloseFile;
	}
	/**
	 * @param appCloseFile the appCloseFile to set
	 */
	public void setAppCloseFile(String appCloseFile) {
		this.appCloseFile = appCloseFile;
	}
	public String getContractArea1() {
		return contractArea1;
	}
	public void setContractArea1(String contractArea1) {
		this.contractArea1 = contractArea1;
	}
	public String getContractArea2() {
		return contractArea2;
	}
	public void setContractArea2(String contractArea2) {
		this.contractArea2 = contractArea2;
	}

}
