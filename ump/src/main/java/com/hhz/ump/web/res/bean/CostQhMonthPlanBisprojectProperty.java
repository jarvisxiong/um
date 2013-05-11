package com.hhz.ump.web.res.bean;

//月度企划计划审批表(商业公司)
public class CostQhMonthPlanBisprojectProperty {

	// 项目
	private String projectCd;
	private String projectName;
	// 活动主题
	private String actionSubject;
	private String actionDate;//活动时间
	private String actionPlace;//活动地点
	// 活动组织
	private String typeCd1; //宝龙自行组织
	private String typeCd2; //联合商户组织，参与商户
	private String typeCd2Info; //
	private String typeCd3; //其他
	private String typeCd3Info; //其他
	// 预计金额(元)
	private String totalPrice;

	private String lxfaFileId;//活动方案说明
	private String ysfyFileId;//预算费用审批表
	private String zbfaFileId;//活动组织分工及费用明细
	
	// 活动内容
	private String remark;

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getTypeCd1() {
		return typeCd1;
	}

	public void setTypeCd1(String typeCd1) {
		this.typeCd1 = typeCd1;
	}

	public String getTypeCd2() {
		return typeCd2;
	}

	public void setTypeCd2(String typeCd2) {
		this.typeCd2 = typeCd2;
	}

	public String getTypeCd3() {
		return typeCd3;
	}

	public void setTypeCd3(String typeCd3) {
		this.typeCd3 = typeCd3;
	}


	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLxfaFileId() {
		return lxfaFileId;
	}

	public void setLxfaFileId(String lxfaFileId) {
		this.lxfaFileId = lxfaFileId;
	}

	public String getYsfyFileId() {
		return ysfyFileId;
	}

	public void setYsfyFileId(String ysfyFileId) {
		this.ysfyFileId = ysfyFileId;
	}

	public String getZbfaFileId() {
		return zbfaFileId;
	}

	public void setZbfaFileId(String zbfaFileId) {
		this.zbfaFileId = zbfaFileId;
	}

	public String getActionSubject() {
		return actionSubject;
	}

	public void setActionSubject(String actionSubject) {
		this.actionSubject = actionSubject;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPlace() {
		return actionPlace;
	}

	public void setActionPlace(String actionPlace) {
		this.actionPlace = actionPlace;
	}

	public String getTypeCd2Info() {
		return typeCd2Info;
	}

	public void setTypeCd2Info(String typeCd2Info) {
		this.typeCd2Info = typeCd2Info;
	}

	public String getTypeCd3Info() {
		return typeCd3Info;
	}

	public void setTypeCd3Info(String typeCd3Info) {
		this.typeCd3Info = typeCd3Info;
	}



}
