/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 限额设计指标审批表
 * 
 */
public class DesignLimitedIndexExamSheet extends BaseTemplatePay {
	
	//工程名称	projectName	专项名称 specItemName	
	//概算指标	budgetIndexName	设计单位	 designOrgCd,designOrgName
	//限额设计指标 designLimitedIndexDesc
	//技术研发中心总经理：techGenMgrCd,techGenMgrName

	private String projectName;
	private String projectCd;
	private String specItemName;
	private String budgetIndexName;
	private String designOrgCd;
	private String designOrgName;
	private String designLimitedIndexDesc;
	private String techGenMgrCd;
	private String techGenMgrName;
	 
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
	public String getDesignLimitedIndexDesc() {
		return designLimitedIndexDesc;
	}
	public void setDesignLimitedIndexDesc(String designLimitedIndexDesc) {
		this.designLimitedIndexDesc = designLimitedIndexDesc;
	}
	public String getTechGenMgrCd() {
		return techGenMgrCd;
	}
	public void setTechGenMgrCd(String techGenMgrCd) {
		this.techGenMgrCd = techGenMgrCd;
	}
	public String getTechGenMgrName() {
		return techGenMgrName;
	}
	public void setTechGenMgrName(String techGenMgrName) {
		this.techGenMgrName = techGenMgrName;
	}
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
		return specItemName;
	}
}
