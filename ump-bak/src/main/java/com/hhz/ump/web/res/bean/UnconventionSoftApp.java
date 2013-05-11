package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 非常规软件使用清单
 * 2010-12-21
 */
public class UnconventionSoftApp extends BaseTemplate {

	//申请人
	private String applicant;
	//所属部门
	private String appDept;
	//申请软件
	private String appSoft;
	//申请事宜说明
	private String appDescribe;
	//申请理由
	private String appReason;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return appSoft;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return appSoft;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getAppDept() {
		return appDept;
	}

	public void setAppDept(String appDept) {
		this.appDept = appDept;
	}

	public String getAppSoft() {
		return appSoft;
	}

	public void setAppSoft(String appSoft) {
		this.appSoft = appSoft;
	}

	public String getAppDescribe() {
		return appDescribe;
	}

	public void setAppDescribe(String appDescribe) {
		this.appDescribe = appDescribe;
	}

	public String getAppReason() {
		return appReason;
	}

	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}

}
