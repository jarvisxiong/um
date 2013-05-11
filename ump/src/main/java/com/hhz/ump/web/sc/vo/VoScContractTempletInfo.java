/**
 * 
 */
package com.hhz.ump.web.sc.vo;

import java.util.Date;

/**
 * @author qlb 02/10/2012 version 1.0.0 合同
 */
public class VoScContractTempletInfo {
	private String contractTempletInfoId;
	private String contractTempletId;
	private String contractTempletName;
	private String contractName;
	private String resApproveInfoId;
	private String statusCd;
	private String contractNo;
	private String remark;
	private String creator;
	private String creatorName;
	private String isstandard;
    private String projectCd;   
	private String projectName;
    private String contractTempletHisId;
    private String conNo;
    private String contLedgerid;
    private String contractPrice;
    //增加合同合新时间  2012/6/4
    private Date  updatedDate;
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the conNo
	 */
	public String getConNo() {
		return conNo;
	}

	/**
	 * @param conNo the conNo to set
	 */
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	/**
	 * @return the contLedgerid
	 */
	public String getContLedgerid() {
		return contLedgerid;
	}

	/**
	 * @param contLedgerid the contLedgerid to set
	 */
	public void setContLedgerid(String contLedgerid) {
		this.contLedgerid = contLedgerid;
	}

	/**
	 * @return the isstandard
	 */
	public String getIsstandard() {
		return isstandard;
	}

	/**
	 * @param isstandard
	 *            the isstandard to set
	 */
	public void setIsstandard(String isstandard) {
		this.isstandard = isstandard;
	}

	/**
	 * @return the creatorName
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * @param creatorName
	 *            the creatorName to set
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * @return the contractTempletId
	 */
	public String getContractTempletId() {
		return contractTempletId;
	}

	/**
	 * @param contractTempletId
	 *            the contractTempletId to set
	 */
	public void setContractTempletId(String contractTempletId) {
		this.contractTempletId = contractTempletId;
	}

	/**
	 * @return the contractTempletName
	 */
	public String getContractTempletName() {
		return contractTempletName;
	}

	/**
	 * @param contractTempletName
	 *            the contractTempletName to set
	 */
	public void setContractTempletName(String contractTempletName) {
		this.contractTempletName = contractTempletName;
	}

	/**
	 * @return the contractTempletInfoId
	 */
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @param contractTempletInfoId
	 *            the contractTempletInfoId to set
	 */
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @return the contractTempletHis
	 */

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName
	 *            the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the resApproveInfoId
	 */
	public String getResApproveInfoId() {
		return resApproveInfoId;
	}

	/**
	 * @param resApproveInfoId
	 *            the resApproveInfoId to set
	 */
	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd
	 *            the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo
	 *            the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	 /**
	 * @return the projectCd
	 */
	public String getProjectCd() {
		return projectCd;
	}

	/**
	 * @param projectCd the projectCd to set
	 */
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the contractTempletHisId
	 */
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @param contractTempletHisId the contractTempletHisId to set
	 */
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	/**
	 * @return the contractPrice
	 */
	public String getContractPrice() {
		return contractPrice;
	}

	/**
	 * @param contractPrice the contractPrice to set
	 */
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

}
