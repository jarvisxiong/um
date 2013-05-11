package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 履约保证金付款审批表
 * @author baolm
 *
 * 2011-04-26
 */
public class PerformBondPayment extends BaseTemplate {
	
	/**
	 * 合同编号
	 */
	private String contNo;
	
	/**
	 * 合同名称
	 */
	private String contName;
	
	/**
	 * 项目cd
	 */
	private String projectCd;

	/**
	 * 乙方(收款人)
	 */
	private String partB;
	
	/**
	 * 实际开工日期
	 */
	private String realBeginDate;
	
	/**
	 * 实际竣工日期
	 */
	private String realEndDate;
	
	/**
	 * 合同工期(天)
	 */
	private String period;
	
	/**
	 * 保修期开始日期
	 */
	private String guarBeginDate;
	
	/**
	 * 保修期结束日期
	 */
	private String guarEndDate;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * ( )期
	 */
	private String projectPeriod;
	
	/**
	 * 付款单位
	 */
	private String paymentUnit;
	
	/**
	 * 收款人开户行
	 */
	private String payeeOpenBankNo;
	
	/**
	 * 收款人账号
	 */
	private String payeeAccountNo;
	
	/**
	 * 实际工期(天)
	 */
	private String realPeriod;
	
	/**
	 * 工期延误说明
	 */
	private String durationDelaysDesc;
	
	/**
	 * 应扣工期违约金
	 */
	private String durationPenalty;

	/**
	 * 质量验收说明
	 */
	private String qualityAcceptDesc;
	
	/**
	 * 应扣质量违约金
	 */
	private String qualityPenalty;
	
	/**
	 * 其他违约说明
	 */
	private String otherRenegeDesc;
	
	/**
	 * 应扣其他违约金
	 */
	private String otherRenegePenalty;
	
	/**
	 * 实际缴纳履约保证金(元)
	 */
	private String realPerformBond;
	
	/**
	 * 应付履约保证金金额
	 */
	private String payPerformBond;
	
	/**
	 * 工程竣工遗留问题确认单
	 */
	private String leaveProblemsConformId;
	
	/**
	 * 收据凭证
	 */
	private String receiptVerifyId;
	
	/**
	 * 保修协议书
	 */
	private String warrantyAgreementId;
	
	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getGuarBeginDate() {
		return guarBeginDate;
	}

	public void setGuarBeginDate(String guarBeginDate) {
		this.guarBeginDate = guarBeginDate;
	}

	public String getGuarEndDate() {
		return guarEndDate;
	}

	public void setGuarEndDate(String guarEndDate) {
		this.guarEndDate = guarEndDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public String getPayeeOpenBankNo() {
		return payeeOpenBankNo;
	}

	public void setPayeeOpenBankNo(String payeeOpenBankNo) {
		this.payeeOpenBankNo = payeeOpenBankNo;
	}

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getRealBeginDate() {
		return realBeginDate;
	}

	public void setRealBeginDate(String realBeginDate) {
		this.realBeginDate = realBeginDate;
	}

	public String getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(String realEndDate) {
		this.realEndDate = realEndDate;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRealPeriod() {
		return realPeriod;
	}

	public void setRealPeriod(String realPeriod) {
		this.realPeriod = realPeriod;
	}

	public String getDurationDelaysDesc() {
		return durationDelaysDesc;
	}

	public void setDurationDelaysDesc(String durationDelaysDesc) {
		this.durationDelaysDesc = durationDelaysDesc;
	}

	public String getDurationPenalty() {
		return durationPenalty;
	}

	public void setDurationPenalty(String durationPenalty) {
		this.durationPenalty = durationPenalty;
	}

	public String getQualityAcceptDesc() {
		return qualityAcceptDesc;
	}

	public void setQualityAcceptDesc(String qualityAcceptDesc) {
		this.qualityAcceptDesc = qualityAcceptDesc;
	}

	public String getQualityPenalty() {
		return qualityPenalty;
	}

	public void setQualityPenalty(String qualityPenalty) {
		this.qualityPenalty = qualityPenalty;
	}

	public String getOtherRenegeDesc() {
		return otherRenegeDesc;
	}

	public void setOtherRenegeDesc(String otherRenegeDesc) {
		this.otherRenegeDesc = otherRenegeDesc;
	}

	public String getOtherRenegePenalty() {
		return otherRenegePenalty;
	}

	public void setOtherRenegePenalty(String otherRenegePenalty) {
		this.otherRenegePenalty = otherRenegePenalty;
	}

	public String getRealPerformBond() {
		return realPerformBond;
	}

	public void setRealPerformBond(String realPerformBond) {
		this.realPerformBond = realPerformBond;
	}

	public String getPayPerformBond() {
		return payPerformBond;
	}

	public void setPayPerformBond(String payPerformBond) {
		this.payPerformBond = payPerformBond;
	}

	public String getLeaveProblemsConformId() {
		return leaveProblemsConformId;
	}

	public void setLeaveProblemsConformId(String leaveProblemsConformId) {
		this.leaveProblemsConformId = leaveProblemsConformId;
	}

	public String getReceiptVerifyId() {
		return receiptVerifyId;
	}

	public void setReceiptVerifyId(String receiptVerifyId) {
		this.receiptVerifyId = receiptVerifyId;
	}

	public String getWarrantyAgreementId() {
		return warrantyAgreementId;
	}

	public void setWarrantyAgreementId(String warrantyAgreementId) {
		this.warrantyAgreementId = warrantyAgreementId;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return getProjectCd();
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contName;
	}

}
