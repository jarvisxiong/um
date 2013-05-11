/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 
 * 行政费用付款审批表
 * @author huangbj
 */
public class AdminPaymentBill extends BaseTemplatePay {
 
	private String sendOrgName;
	private String sendOrgCd;
	
	private String orgName;
	private String contractNo;
	private String currentPayDate;//本次付款时间
	private String contractTotalAmt;//合同总金额(元)
	private String contractPaiedAmt;//已付合同款(元)
	private String contentDesc;
	
	/**
	 * 付款依据附件
	 */
	private String payDependenceId;
	  
	public String getSendOrgName() {
		return sendOrgName;
	}
	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}
	public String getSendOrgCd() {
		return sendOrgCd;
	}
	public void setSendOrgCd(String sendOrgCd) {
		this.sendOrgCd = sendOrgCd;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCurrentPayDate() {
		return currentPayDate;
	}
	public void setCurrentPayDate(String currentPayDate) {
		this.currentPayDate = currentPayDate;
	}
	public String getContractTotalAmt() {
		return contractTotalAmt;
	}
	public void setContractTotalAmt(String contractTotalAmt) {
		this.contractTotalAmt = contractTotalAmt;
	}
	public String getContractPaiedAmt() {
		return contractPaiedAmt;
	}
	public void setContractPaiedAmt(String contractPaiedAmt) {
		this.contractPaiedAmt = contractPaiedAmt;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return orgName;
	}
	/**
	 * @return the payDependenceId
	 */
	public String getPayDependenceId() {
		return payDependenceId;
	}
	/**
	 * @param payDependenceId the payDependenceId to set
	 */
	public void setPayDependenceId(String payDependenceId) {
		this.payDependenceId = payDependenceId;
	}

}
