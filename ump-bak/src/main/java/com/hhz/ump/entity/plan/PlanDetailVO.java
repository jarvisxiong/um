package com.hhz.ump.entity.plan;

import java.util.Date;

public class PlanDetailVO {

	private String nodeName;
	private String resOrgName;
	private String executionPlanName;
	private Date scheduleEndDate;
	private Integer nowStatus;
	private String status;
	private String projectName;
	private String detailId;
	private String projNodeId;
	private String projPlanCd;
	private String planTypeCd;
	private String costCtrlId;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getResOrgName() {
		return resOrgName;
	}

	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}

	public String getExecutionPlanName() {
		return executionPlanName;
	}

	public void setExecutionPlanName(String executionPlanName) {
		this.executionPlanName = executionPlanName;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}


	public Integer getNowStatus() {
		return nowStatus;
	}

	public void setNowStatus(Integer nowStatus) {
		this.nowStatus = nowStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getProjNodeId() {
		return projNodeId;
	}

	public void setProjNodeId(String projNodeId) {
		this.projNodeId = projNodeId;
	}

	public String getProjPlanCd() {
		return projPlanCd;
	}

	public void setProjPlanCd(String projPlanCd) {
		this.projPlanCd = projPlanCd;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}

	public String getCostCtrlId() {
		return costCtrlId;
	}

	public void setCostCtrlId(String costCtrlId) {
		this.costCtrlId = costCtrlId;
	}
}
