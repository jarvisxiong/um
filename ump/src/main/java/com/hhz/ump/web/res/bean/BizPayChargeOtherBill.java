package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

//商业付款审批表(eg: 商业公司其它费用审批表)
public class BizPayChargeOtherBill extends BaseTemplatePay {
	private String hasLandCompany = "true";// 有地产公司,默认有
	// 合同编号 合同名称
	private String contactNo;
	private String contactName;
	// 项目名称
	private String projectName;
	private String projectCd;
	// 付款单位
	private String payUnitName;
	private String payUnitCd;
	// 收款人（乙方）信息
	// 收款人名称
	// 收款人开户行
	// 收款人账号
	private String receName;
	private String receOpenBankName;
	private String receAcctNo;
	// 付款方式
	private String payTypeDesc;

	// 合同总价 已确认合同总价
	private String contractTotalAmt;
	private String confirmTotalAmt;
	//已付合同款（元）
	private String paidTotalAmt;
	
	
	
	private String sendOrgName;
	private String sendOrgCd;
	
	private String orgName;
	private String contractNo;
	private String currentPayDate;//本次付款时间
	private String contractPaiedAmt;//已付合同款(元)
	private String contentDesc;
	
	
	/**
	 * @return the paidTotalAmt
	 */
	public String getPaidTotalAmt() {
		return paidTotalAmt;
	}

	/**
	 * @param paidTotalAmt the paidTotalAmt to set
	 */
	public void setPaidTotalAmt(String paidTotalAmt) {
		this.paidTotalAmt = paidTotalAmt;
	}

	// 本次付款申请金额（元）
	private String applyAmt;

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getPayUnitName() {
		return payUnitName;
	}

	public void setPayUnitName(String payUnitName) {
		this.payUnitName = payUnitName;
	}

	public String getPayUnitCd() {
		return payUnitCd;
	}

	public void setPayUnitCd(String payUnitCd) {
		this.payUnitCd = payUnitCd;
	}

	public String getReceName() {
		return receName;
	}

	public void setReceName(String receName) {
		this.receName = receName;
	}

	public String getReceOpenBankName() {
		return receOpenBankName;
	}

	public void setReceOpenBankName(String receOpenBankName) {
		this.receOpenBankName = receOpenBankName;
	}

	public String getReceAcctNo() {
		return receAcctNo;
	}

	public void setReceAcctNo(String receAcctNo) {
		this.receAcctNo = receAcctNo;
	}

	public String getPayTypeDesc() {
		return payTypeDesc;
	}

	public void setPayTypeDesc(String payTypeDesc) {
		this.payTypeDesc = payTypeDesc;
	}

	public String getContractTotalAmt() {
		return contractTotalAmt;
	}

	public void setContractTotalAmt(String contractTotalAmt) {
		this.contractTotalAmt = contractTotalAmt;
	}

	public String getConfirmTotalAmt() {
		return confirmTotalAmt;
	}

	public void setConfirmTotalAmt(String confirmTotalAmt) {
		this.confirmTotalAmt = confirmTotalAmt;
	}

	public String getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}

	public String getHasLandCompany() {
		return hasLandCompany;
	}

	public void setHasLandCompany(String hasLandCompany) {
		this.hasLandCompany = hasLandCompany;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contactName;
	}

	/**
	 * @return the sendOrgName
	 */
	public String getSendOrgName() {
		return sendOrgName;
	}

	/**
	 * @param sendOrgName the sendOrgName to set
	 */
	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}

	/**
	 * @return the sendOrgCd
	 */
	public String getSendOrgCd() {
		return sendOrgCd;
	}

	/**
	 * @param sendOrgCd the sendOrgCd to set
	 */
	public void setSendOrgCd(String sendOrgCd) {
		this.sendOrgCd = sendOrgCd;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the currentPayDate
	 */
	public String getCurrentPayDate() {
		return currentPayDate;
	}

	/**
	 * @param currentPayDate the currentPayDate to set
	 */
	public void setCurrentPayDate(String currentPayDate) {
		this.currentPayDate = currentPayDate;
	}

	/**
	 * @return the contractPaiedAmt
	 */
	public String getContractPaiedAmt() {
		return contractPaiedAmt;
	}

	/**
	 * @param contractPaiedAmt the contractPaiedAmt to set
	 */
	public void setContractPaiedAmt(String contractPaiedAmt) {
		this.contractPaiedAmt = contractPaiedAmt;
	}

	/**
	 * @return the contentDesc
	 */
	public String getContentDesc() {
		return contentDesc;
	}

	/**
	 * @param contentDesc the contentDesc to set
	 */
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
}
