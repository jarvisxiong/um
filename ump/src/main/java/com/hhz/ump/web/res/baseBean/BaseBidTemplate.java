/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * 定标审批表基础模板无需导入合同台账
 * 
 * @author qilb 6/6/2012
 * 
 */
public class BaseBidTemplate extends BaseTemplate {
	/**
	 * 供应商id
	 */
	private String supBasicId;// supBasicId

	public String getSupBasicId() {
		return supBasicId;
	}

	public void setSupBasicId(String supBasicId) {
		this.supBasicId = supBasicId;
	}

	// 合同标准库 start -===============================
	// 标准合同
	private String standard;

	// 非标准合同
	private String nonstandard;

	// 使用合同库
	private String contlib;
	// 不使用合同库
	private String noncontlib;

	private String contractTempletInfoId;
	// 合同历史版id
	private String contractTempletHisId;

	// 合同编号
	private String contractNo;
	// 合同名称
	private String contractName;

	// 合同标准库 end -===============================

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @return the nonstandard
	 */
	public String getNonstandard() {
		return nonstandard;
	}

	/**
	 * @return the contractTempletInfoId
	 */
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @return the contractTempletHisId
	 */
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param standard
	 *            the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @param nonstandard
	 *            the nonstandard to set
	 */
	public void setNonstandard(String nonstandard) {
		this.nonstandard = nonstandard;
	}

	/**
	 * @param contractTempletInfoId
	 *            the contractTempletInfoId to set
	 */
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @param contractTempletHisId
	 *            the contractTempletHisId to set
	 */
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	/**
	 * @param contractNo
	 *            the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @param contractName
	 *            the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the contlib
	 */
	public String getContlib() {
		return contlib;
	}

	/**
	 * @return the noncontlib
	 */
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param contlib
	 *            the contlib to set
	 */
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @param noncontlib
	 *            the noncontlib to set
	 */
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}
}
