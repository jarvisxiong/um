/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 开发项目目标成本审批表
 * 
 */
public class DevelopTargetCostExamSheet extends BaseTemplatePay {
	
//	工程名称	 projectName	专业或项目名称 specItemName
//	开竣工时间	startDate,endDate	目标成本总额 （万元）	targetCostAmt
//	主要内容及说明（目标成本明细附后）：contentDesc
//	目标成本编制人： targetCostEditorCd,targetCostEditorName, 目标成本审核人：targetCostExamCd,targetCostExamName
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	private String specItemName;
	private String startDate;
	private String endDate;
	private String targetCostAmt;
	private String contentDesc;
	private String targetCostEditorCd;
	private String targetCostEditorName;
	private String targetCostExamCd;
	private String targetCostExamName;

	public String getSpecItemName() {
		return specItemName;
	}
	public void setSpecItemName(String specItemName) {
		this.specItemName = specItemName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTargetCostAmt() {
		return targetCostAmt;
	}
	public void setTargetCostAmt(String targetCostAmt) {
		this.targetCostAmt = targetCostAmt;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getTargetCostEditorCd() {
		return targetCostEditorCd;
	}
	public void setTargetCostEditorCd(String targetCostEditorCd) {
		this.targetCostEditorCd = targetCostEditorCd;
	}
	public String getTargetCostEditorName() {
		return targetCostEditorName;
	}
	public void setTargetCostEditorName(String targetCostEditorName) {
		this.targetCostEditorName = targetCostEditorName;
	}
	public String getTargetCostExamCd() {
		return targetCostExamCd;
	}
	public void setTargetCostExamCd(String targetCostExamCd) {
		this.targetCostExamCd = targetCostExamCd;
	}
	public String getTargetCostExamName() {
		return targetCostExamName;
	}
	public void setTargetCostExamName(String targetCostExamName) {
		this.targetCostExamName = targetCostExamName;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return engineeringName;
	}
	public String getEngineeringName() {
		return engineeringName;
	}
	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}
}
