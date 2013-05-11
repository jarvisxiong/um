/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 12、 工程/材料设备结算
 * 
 * @author tengwei
 */
public class ProjectEquipSettlementBill extends BaseTemplatePay {

	// 项目名称 project_name
	// 合同编号 contract_no
	// 总价款 (元) total_payment_amt
	// 已付款 (元) paid_amt
	// 本次支付金额 (元) cur_payment_amt
	// 需说明事项 content_desc
	// 合同结算金额（元） contract_settle_amt

	private String projectName;
	private String projectCd;
	private String contractNo;
	private String contractName;
	private String totalPaymentAmt;
	private String paidAmt;
	private String curPaymentAmt;
	private String contractSettleAmt;
	
	public String getContractSettleAmt() {
		return contractSettleAmt;
	}
	public void setContractSettleAmt(String contractSettleAmt) {
		this.contractSettleAmt = contractSettleAmt;
	}

	private String contentDesc;
	
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

	@Override
	public String getCurPaymentAmt() {
		return curPaymentAmt;
	}

	@Override
	public void setCurPaymentAmt(String curPaymentAmt) {
		this.curPaymentAmt = curPaymentAmt;
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
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
}
