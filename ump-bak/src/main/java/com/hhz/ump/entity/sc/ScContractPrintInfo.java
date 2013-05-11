/**
 * 
 */
package com.hhz.ump.entity.sc;

/**
 * 合同打印信息
 * @author qlb 2/16/2012 version:1.0.0
 * 
 */
public class ScContractPrintInfo {
	/**
	 * 合同ID
	 */
	private String contractId;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 合同编号
	 */
	
	private String contractNo;
	/**
	 * 当前用户
	 */
	private String printUser;
	/**
	 * 随机打印编号 randPrinSn
	 */
	private String randPrintSN;
	/**
	 * 打印日期
	 */
	private String printDate;

	/**
	 * 创建人姓名
	 * */
	private String creatorUserName;
	/**
	 * 打印的版本记录
	 */
	private String printRecordVersion;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 合同创建日期
	 */
	private String createdDate;
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
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the printUser
	 */
	public String getPrintUser() {
		return printUser;
	}
	/**
	 * @param printUser the printUser to set
	 */
	public void setPrintUser(String printUser) {
		this.printUser = printUser;
	}
	/**
	 * @return the randPrintSN
	 */
	public String getRandPrintSN() {
		return randPrintSN;
	}
	/**
	 * @param randPrintSN the randPrintSN to set
	 */
	public void setRandPrintSN(String randPrintSN) {
		this.randPrintSN = randPrintSN;
	}
	/**
	 * @return the printDate
	 */
	public String getPrintDate() {
		return printDate;
	}
	/**
	 * @param printDate the printDate to set
	 */
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	/**
	 * @return the creatorUserName
	 */
	public String getCreatorUserName() {
		return creatorUserName;
	}
	/**
	 * @param creatorUserName the creatorUserName to set
	 */
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}
	/**
	 * @return the printRecordVersion
	 */
	public String getPrintRecordVersion() {
		return printRecordVersion;
	}
	/**
	 * @param printRecordVersion the printRecordVersion to set
	 */
	public void setPrintRecordVersion(String printRecordVersion) {
		this.printRecordVersion = printRecordVersion;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}
