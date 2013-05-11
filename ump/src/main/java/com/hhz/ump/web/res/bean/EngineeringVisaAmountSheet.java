package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 工程签证费用核定单
 * @author shixy
 *
 * 2010-12-22
 */
public class EngineeringVisaAmountSheet extends BaseTemplate {
	
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目名称Cd
	 */
	private String projectCd;
	/**
	 * 项目地点
	 */
	private String projectPlace;
	/**
	 * 工程名称
	 */
	private String engineeringName;
	/**
	 * 施工单位
	 */
	private String builder;
	
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同金额
	 */
	private String contractAmount;
	/**
	 * 累计签证额
	 */
	private String totalVisaAmount;
	/**
	 * 占合同比例
	 */
	private String rateInCont;
	/**
	 * 签证主题
	 */
	private String visaSubject;
	/**
	 * 签证单编号
	 */
	private String visaNo;
	/**
	 * 签证原因-设计错漏 
	 */
	private String visaReason1;
	/**
	 * 签证原因-招商原因
	 */
	private String visaReason2;
	/**
	 * 签证原因-施工原因
	 */
	private String visaReason3;
	/**
	 * 签证原因-甲方原因
	 */
	private String visaReason4;
	/**
	 * 签证原因-其他
	 */
	private String visaReason5;
	/**
	 * 签证原因(other)
	 */
	private String otherReason;
	/**
	 * 施工单位报送金额（元）
	 */
	private String builderAmount;
	/**
	 * 地产公司审核金额（元）
	 */
	private String estateAmount;
	/**
	 * 集团审核金额（元）
	 */
	private String groupAmount;
	/**
	 * 附件《工程签证审批表》编号
	 */
	private String blank1;
	/**
	 * 《现场签证单》编号
	 */
	private String blank2;
	/**
	 * 合同    条款
	 */
	private String blank3;
	/**
	 * 方法
	 */
	private String blank4;
	/**
	 * 工程量
	 */
	private String blank5;
	/**
	 * 单价
	 */
	private String blank6;
	/**
	 * 合同约定下浮率
	 */
	private String blank7;
	/**
	 * 增加或者减少的费用
	 */
	private String changeAmount;
	
	/**
	 * 工程签证审批表
	 */
	private String engineeringVisaFileId;
	/**
	 * 合同变更条款扫描件
	 */
	private String contChangeFileId;
	/**
	 * 预算书
	 */
	private String budgetFileId;
	
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contractNo;
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

	public String getProjectPlace() {
		return projectPlace;
	}

	public void setProjectPlace(String projectPlace) {
		this.projectPlace = projectPlace;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getTotalVisaAmount() {
		return totalVisaAmount;
	}

	public void setTotalVisaAmount(String totalVisaAmount) {
		this.totalVisaAmount = totalVisaAmount;
	}

	public String getRateInCont() {
		return rateInCont;
	}

	public void setRateInCont(String rateInCont) {
		this.rateInCont = rateInCont;
	}

	public String getVisaSubject() {
		return visaSubject;
	}

	public void setVisaSubject(String visaSubject) {
		this.visaSubject = visaSubject;
	}

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getVisaReason1() {
		return visaReason1;
	}

	public void setVisaReason1(String visaReason1) {
		this.visaReason1 = visaReason1;
	}

	public String getVisaReason2() {
		return visaReason2;
	}

	public void setVisaReason2(String visaReason2) {
		this.visaReason2 = visaReason2;
	}

	public String getVisaReason3() {
		return visaReason3;
	}

	public void setVisaReason3(String visaReason3) {
		this.visaReason3 = visaReason3;
	}

	public String getVisaReason4() {
		return visaReason4;
	}

	public void setVisaReason4(String visaReason4) {
		this.visaReason4 = visaReason4;
	}

	public String getVisaReason5() {
		return visaReason5;
	}

	public void setVisaReason5(String visaReason5) {
		this.visaReason5 = visaReason5;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public String getBuilderAmount() {
		return builderAmount;
	}

	public void setBuilderAmount(String builderAmount) {
		this.builderAmount = builderAmount;
	}

	public String getEstateAmount() {
		return estateAmount;
	}

	public void setEstateAmount(String estateAmount) {
		this.estateAmount = estateAmount;
	}

	public String getBlank1() {
		return blank1;
	}

	public void setBlank1(String blank1) {
		this.blank1 = blank1;
	}

	public String getBlank2() {
		return blank2;
	}

	public void setBlank2(String blank2) {
		this.blank2 = blank2;
	}

	public String getBlank3() {
		return blank3;
	}

	public void setBlank3(String blank3) {
		this.blank3 = blank3;
	}

	public String getBlank4() {
		return blank4;
	}

	public void setBlank4(String blank4) {
		this.blank4 = blank4;
	}

	public String getBlank5() {
		return blank5;
	}

	public void setBlank5(String blank5) {
		this.blank5 = blank5;
	}

	public String getBlank6() {
		return blank6;
	}

	public void setBlank6(String blank6) {
		this.blank6 = blank6;
	}

	public String getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getGroupAmount() {
		return groupAmount;
	}

	public void setGroupAmount(String groupAmount) {
		this.groupAmount = groupAmount;
	}

	public String getBlank7() {
		return blank7;
	}

	public void setBlank7(String blank7) {
		this.blank7 = blank7;
	}

	public String getEngineeringVisaFileId() {
		return engineeringVisaFileId;
	}

	public void setEngineeringVisaFileId(String engineeringVisaFileId) {
		this.engineeringVisaFileId = engineeringVisaFileId;
	}

	public String getContChangeFileId() {
		return contChangeFileId;
	}

	public void setContChangeFileId(String contChangeFileId) {
		this.contChangeFileId = contChangeFileId;
	}

	public String getBudgetFileId() {
		return budgetFileId;
	}

	public void setBudgetFileId(String budgetFileId) {
		this.budgetFileId = budgetFileId;
	}

}
