/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 限额设计图纸复核审批表
 * 
 */
public class DesignLimitedDrawExamSheet extends BaseTemplatePay {
	
	//	工程名称	projectName	专项名称 specItemName	
	//	概算指标	budgetIndexName	设计单位	designOrgCd,designOrgName
	//	实际设计指标（技术研发中心填写）realDesignIndexDesc
	//	对比分析（成本控制中心填写） compareDesc
	//	技术研发中心总经理：techGenMgrCd,techGenMgrName

	private String projectName;
	private String projectCd;
	private String specItemName;
	private String budgetIndexName;
	private String designOrgCd;
	private String designOrgName;
	private String realDesignIndexDesc;
	private String compareDesc;

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
	public String getSpecItemName() {
		return specItemName;
	}
	public void setSpecItemName(String specItemName) {
		this.specItemName = specItemName;
	}
	public String getBudgetIndexName() {
		return budgetIndexName;
	}
	public void setBudgetIndexName(String budgetIndexName) {
		this.budgetIndexName = budgetIndexName;
	}
	public String getDesignOrgCd() {
		return designOrgCd;
	}
	public void setDesignOrgCd(String designOrgCd) {
		this.designOrgCd = designOrgCd;
	}
	public String getDesignOrgName() {
		return designOrgName;
	}
	public void setDesignOrgName(String designOrgName) {
		this.designOrgName = designOrgName;
	}
	public String getRealDesignIndexDesc() {
		return realDesignIndexDesc;
	}
	public void setRealDesignIndexDesc(String realDesignIndexDesc) {
		this.realDesignIndexDesc = realDesignIndexDesc;
	}
	public String getCompareDesc() {
		return compareDesc;
	}
	public void setCompareDesc(String compareDesc) {
		this.compareDesc = compareDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.projectName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.projectCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return specItemName;
	}
}
