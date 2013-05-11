package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//商品工程改造审批单(eg:商业工程改造审批表（商业公司承担费用） 
public class GoodProjectReformBill extends BaseTemplate {
	private String hasLandCompany = "true";// 有地产公司,默认有
	private String checkCd1;//零星改造（单项金额≤5000元，月度总额≤2万元）
	private String checkCd2;//涉及使用功能性改造或其他方面改造
	private String checkCd3;//涉及外观、效果改造
	// 项目名称
	// 工程名称  预估工程费用
	// 工程改造范围
	// 工程改造内容及原因

	private String projectName;
	private String projectCd;

	private String programName;
	private String programCd;

	private String planFeeAmt;

	private String areaDesc;
	private String reasonDesc;

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

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramCd() {
		return programCd;
	}

	public void setProgramCd(String programCd) {
		this.programCd = programCd;
	}

	public String getPlanFeeAmt() {
		return planFeeAmt;
	}

	public void setPlanFeeAmt(String planFeeAmt) {
		this.planFeeAmt = planFeeAmt;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getCheckCd1() {
		return checkCd1;
	}

	public void setCheckCd1(String checkCd1) {
		this.checkCd1 = checkCd1;
	}

	public String getCheckCd2() {
		return checkCd2;
	}

	public void setCheckCd2(String checkCd2) {
		this.checkCd2 = checkCd2;
	}

	public String getCheckCd3() {
		return checkCd3;
	}

	public void setCheckCd3(String checkCd3) {
		this.checkCd3 = checkCd3;
	}

	public String getHasLandCompany() {
		return hasLandCompany;
	}

	public void setHasLandCompany(String hasLandCompany) {
		this.hasLandCompany = hasLandCompany;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return programName;
	}

}
