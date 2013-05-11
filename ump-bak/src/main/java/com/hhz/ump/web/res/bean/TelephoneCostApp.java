package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/*
 * 电话费用报销单
 */
public class TelephoneCostApp extends BaseTemplate {

	//申请人姓名
	private String appName;
	//中心Cd
	private String appCenterCd;
	//中心名称
	private String appCenterName;
	//部门
	private String appDept;
	//级别
	private String appGrade;
	//申请报销电话费类别
	private String appTeleType;
	//申请报销电话费号码
	private String appTelephone;
	//入职时间
	private String entrantTime;
	//报销起始时间
	private String fromTime;
	//申请理由
	private String appReason;
	//审核额度
	private String approveLimit;
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
		return appGrade;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDept() {
		return appDept;
	}

	public void setAppDept(String appDept) {
		this.appDept = appDept;
	}

	public String getAppGrade() {
		return appGrade;
	}

	public void setAppGrade(String appGrade) {
		this.appGrade = appGrade;
	}

	public String getAppTeleType() {
		return appTeleType;
	}

	public void setAppTeleType(String appTeleType) {
		this.appTeleType = appTeleType;
	}

	public String getAppTelephone() {
		return appTelephone;
	}

	public void setAppTelephone(String appTelephone) {
		this.appTelephone = appTelephone;
	}

	public String getEntrantTime() {
		return entrantTime;
	}

	public void setEntrantTime(String entrantTime) {
		this.entrantTime = entrantTime;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getAppReason() {
		return appReason;
	}

	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}

	public String getApproveLimit() {
		return approveLimit;
	}

	public void setApproveLimit(String approveLimit) {
		this.approveLimit = approveLimit;
	}

	public String getAppCenterCd() {
		return appCenterCd;
	}

	public void setAppCenterCd(String appCenterCd) {
		this.appCenterCd = appCenterCd;
	}

	public String getAppCenterName() {
		return appCenterName;
	}

	public void setAppCenterName(String appCenterName) {
		this.appCenterName = appCenterName;
	}

}
