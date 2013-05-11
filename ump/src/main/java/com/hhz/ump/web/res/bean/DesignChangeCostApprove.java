package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 设计变更费用核定单
 * @author lujunyun 2010-12-21
 */
public class DesignChangeCostApprove extends BaseTemplate {
	private String projectName;	//项目名称
	private String projectCd;	//项目名称
	private String projectPlace;	//项目地点
	private String engineeringName;	//工程名称
	private String constructionCompany;	//施工单位
	private String contractSN;	//合同编号
	private String contractMoney;	//合同金额
	private String addUpMoney;	//累计变更额
	private String addUpRate;	//占合同比例
	private String changeTitle;	//设计变更主题
	private String changeSN;	//设计变更单编号
	private String reason1;	//设计遗漏
	private String reason2;	//招商原因
	private String reason3;	//施工原因
	private String reason4;	//甲方原因
	private String reasonOther;	//其他
	private String otherContent;//其他原因内容
	private String constructionCompanyMoney;	//施工单位上报金额
	private String dcMoney;	//地产公司审核金额
	private String content;	//核价编制说明
	private String changeMoney;	//增加或减少的费用(万元)
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

	public String getConstructionCompany() {
		return constructionCompany;
	}

	public void setConstructionCompany(String constructionCompany) {
		this.constructionCompany = constructionCompany;
	}

	public String getContractSN() {
		return contractSN;
	}

	public void setContractSN(String contractSN) {
		this.contractSN = contractSN;
	}

	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	public String getAddUpMoney() {
		return addUpMoney;
	}

	public void setAddUpMoney(String addUpMoney) {
		this.addUpMoney = addUpMoney;
	}

	public String getAddUpRate() {
		return addUpRate;
	}

	public void setAddUpRate(String addUpRate) {
		this.addUpRate = addUpRate;
	}

	public String getChangeTitle() {
		return changeTitle;
	}

	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}

	public String getChangeSN() {
		return changeSN;
	}

	public void setChangeSN(String changeSN) {
		this.changeSN = changeSN;
	}

	public String getConstructionCompanyMoney() {
		return constructionCompanyMoney;
	}

	public void setConstructionCompanyMoney(String constructionCompanyMoney) {
		this.constructionCompanyMoney = constructionCompanyMoney;
	}

	public String getDcMoney() {
		return dcMoney;
	}

	public void setDcMoney(String dCMoney) {
		dcMoney = dCMoney;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
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
		return changeTitle;
	}

	public String getReason1() {
		return reason1;
	}

	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}

	public String getReason2() {
		return reason2;
	}

	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	public String getReason3() {
		return reason3;
	}

	public void setReason3(String reason3) {
		this.reason3 = reason3;
	}

	public String getReason4() {
		return reason4;
	}

	public void setReason4(String reason4) {
		this.reason4 = reason4;
	}

	public String getReasonOther() {
		return reasonOther;
	}

	public void setReasonOther(String reasonOther) {
		this.reasonOther = reasonOther;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
}
