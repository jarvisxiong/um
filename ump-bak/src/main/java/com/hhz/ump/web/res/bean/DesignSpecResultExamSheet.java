/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 专项设计成果审批表
 * 
 */
public class DesignSpecResultExamSheet extends BaseTemplatePay {
	
	//  成果名称 resultName 项目名称	projectName	论证次数	demonNum
	//	方案编号	schemeSerialNo	设计单位	designOrgName,designOrgCd
	//	内容简述 contentDesc
	//	技术研发中心总经理：techGenMgrCd,techGenMgrName

	private String resultName;
	private String projectName;
	private String projectCd;
	private String examNum;
	private String schemeSerialNo;
	private String designOrgName;
	private String designOrgCd;
	private String contentDesc;
	private String techGenMgrCd;
	private String techGenMgrName;
	
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
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
	public String getExamNum() {
		return examNum;
	}
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}
	public String getSchemeSerialNo() {
		return schemeSerialNo;
	}
	public void setSchemeSerialNo(String schemeSerialNo) {
		this.schemeSerialNo = schemeSerialNo;
	}
	public String getDesignOrgName() {
		return designOrgName;
	}
	public void setDesignOrgName(String designOrgName) {
		this.designOrgName = designOrgName;
	}
	public String getDesignOrgCd() {
		return designOrgCd;
	}
	public void setDesignOrgCd(String designOrgCd) {
		this.designOrgCd = designOrgCd;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
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
		return resultName;
	}
}
