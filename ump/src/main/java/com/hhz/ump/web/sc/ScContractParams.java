/**
 * 
 */
package com.hhz.ump.web.sc;

/**
 * 合同前台参数实体
 * 
 * @author qlb 2/22/2012 version 1.0.0
 * 
 */
public class ScContractParams {

	// 合同内容
	private String scContractHtml;
	// 合同填空数据
	private String conFillJson;
	// 标准模板ID
	private String scContractTemletId;

	// 非标模板ID
	private String scNonContractTempletId;

	// 模板是否有效
	private String isValid;
	// 模板类别0非标，1标准
	private String isStandard;
	// 调用合同库FRAME ID
	private String frameId;
	//合同三级编号
	private String thirdSn;


	/**
	 * 合同类型编号，QQ：设计类，YX：营销类...
	 */
	private String sn;
	//模板类型CD
	private String templetTypeCd;
	//是否仅能查看合同文本，默认只能查看
	private String isOnlySee="1";
	//从网批中带出自动填空的字段用值 Json
	private String resFields;
	//网批ID
	private String resNo;
	//网批中的附件更表及ID 中标单位报价清单:4028298f375438710137544ed3a30001  Json
	private String resRela;
	/**
	 * 从定标中导入合同台账字段 JSON
	 */
	private String resContLedgerParams;
	//合同台账ID
	private String contLedgerId;
	//合同台账编号
	private String contLedgerNo;
	
	//删除状态
	private String isDel;
	
	public String getContLedgerNo() {
		return contLedgerNo;
	}

	public void setContLedgerNo(String contLedgerNo) {
		this.contLedgerNo = contLedgerNo;
	}

	public String getContLedgerId() {
		return contLedgerId;
	}

	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}

	public String getResContLedgerParams() {
		return resContLedgerParams;
	}

	public void setResContLedgerParams(String resContLedgerParams) {
		this.resContLedgerParams = resContLedgerParams;
	}

	/**
	 * @return the resFields
	 */
	public String getResFields() {
		return resFields;
	}

	/**
	 * @param resFields the resFields to set
	 */
	public void setResFields(String resFields) {
		this.resFields = resFields;
	}

	/**
	 * @return the resNo
	 */
	public String getResNo() {
		return resNo;
	}

	/**
	 * @param resNo the resNo to set
	 */
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	/**
	 * @return the resRela
	 */
	public String getResRela() {
		return resRela;
	}

	/**
	 * @param resRela the resRela to set
	 */
	public void setResRela(String resRela) {
		this.resRela = resRela;
	}

	/**
	 * @return the isOnlySee
	 */
	public String getIsOnlySee() {
		return isOnlySee;
	}

	/**
	 * @param isOnlySee the isOnlySee to set
	 */
	public void setIsOnlySee(String isOnlySee) {
		this.isOnlySee = isOnlySee;
	}

	/**
	 * @return the templetTypeCd
	 */
	public String getTempletTypeCd() {
		return templetTypeCd;
	}

	/**
	 * @param templetTypeCd the templetTypeCd to set
	 */
	public void setTempletTypeCd(String templetTypeCd) {
		this.templetTypeCd = templetTypeCd;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the thirdSn
	 */
	public String getThirdSn() {
		return thirdSn;
	}

	/**
	 * @param thirdSn the thirdSn to set
	 */
	public void setThirdSn(String thirdSn) {
		this.thirdSn = thirdSn;
	}

	/**
	 * @return the frameId
	 */
	public String getFrameId() {
		return frameId;
	}

	/**
	 * @param frameId
	 *            the frameId to set
	 */
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	/**
	 * @return the isStandard
	 */
	public String getIsStandard() {
		return isStandard;
	}

	/**
	 * @param isStandard
	 *            the isStandard to set
	 */
	public void setIsStandard(String isStandard) {
		this.isStandard = isStandard;
	}

	private String curLoginUser = "";
	// 历史最新合同模板ID
	private String maxHisContId;

	private String responsiblePersons;

	private String responsiblePersonCds;

	/**
	 * @return the conFillJson
	 */
	public String getConFillJson() {
		return conFillJson;
	}

	/**
	 * @param conFillJson
	 *            the conFillJson to set
	 */
	public void setConFillJson(String conFillJson) {
		this.conFillJson = conFillJson;
	}

	/**
	 * @return the isValid
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            the isValid to set
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the curLoginUser
	 */
	public String getCurLoginUser() {
		return curLoginUser;
	}

	/**
	 * @param curLoginUser
	 *            the curLoginUser to set
	 */
	public void setCurLoginUser(String curLoginUser) {
		this.curLoginUser = curLoginUser;
	}

	/**
	 * @return the maxHisContId
	 */
	public String getMaxHisContId() {
		return maxHisContId;
	}

	/**
	 * @param maxHisContId
	 *            the maxHisContId to set
	 */
	public void setMaxHisContId(String maxHisContId) {
		this.maxHisContId = maxHisContId;
	}

	/**
	 * @return the responsiblePersons
	 */
	public String getResponsiblePersons() {
		return responsiblePersons;
	}

	/**
	 * @param responsiblePersons
	 *            the responsiblePersons to set
	 */
	public void setResponsiblePersons(String responsiblePersons) {
		this.responsiblePersons = responsiblePersons;
	}

	/**
	 * @return the responsiblePersonCds
	 */
	public String getResponsiblePersonCds() {
		return responsiblePersonCds;
	}

	/**
	 * @param responsiblePersonCds
	 *            the responsiblePersonCds to set
	 */
	public void setResponsiblePersonCds(String responsiblePersonCds) {
		this.responsiblePersonCds = responsiblePersonCds;
	}

	/**
	 * @return the scContractHtml
	 */
	public String getScContractHtml() {
		return scContractHtml;
	}

	/**
	 * @param scContractHtml
	 *            the scContractHtml to set
	 */
	public void setScContractHtml(String scContractHtml) {
		this.scContractHtml = scContractHtml;
	}

	/**
	 * @return the scContractTemletId
	 */
	public String getScContractTemletId() {
		return scContractTemletId;
	}

	/**
	 * @param scContractTemletId
	 *            the scContractTemletId to set
	 */
	public void setScContractTemletId(String scContractTemletId) {
		this.scContractTemletId = scContractTemletId;
	}

	/**
	 * @return the scNonContractTempletId
	 */
	public String getScNonContractTempletId() {
		return scNonContractTempletId;
	}

	/**
	 * @param scNonContractTempletId
	 *            the scNonContractTempletId to set
	 */
	public void setScNonContractTempletId(String scNonContractTempletId) {
		this.scNonContractTempletId = scNonContractTempletId;
	}

	/**
	 * @return the isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel the isDel to set
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	
}
