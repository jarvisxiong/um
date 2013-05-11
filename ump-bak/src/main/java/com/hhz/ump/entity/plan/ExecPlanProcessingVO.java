package com.hhz.ump.entity.plan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExecPlanProcessingVO {
	private String execPlanProcessingId;
	private String projectCd;
	private String projectName;
	private String resOrgName;
	private String noConfirmStatus;
	private String goingStatus;
	private String suspendStatus;
	private String completeStatus;
	private String noActiveStatus;
	private String noReplyStatus;
	private Date createdDate;
	private String remark;
	private List<ExecPlanProcessing> execPlanProcessing = new ArrayList<ExecPlanProcessing>();

	public String getExecPlanProcessingId() {
		return execPlanProcessingId;
	}
	public void setExecPlanProcessingId(String execPlanProcessingId) {
		this.execPlanProcessingId = execPlanProcessingId;
	}
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
	public String getResOrgName() {
		return resOrgName;
	}
	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}
	public String getNoConfirmStatus() {
		return noConfirmStatus;
	}
	public void setNoConfirmStatus(String noConfirmStatus) {
		this.noConfirmStatus = noConfirmStatus;
	}
	public String getGoingStatus() {
		return goingStatus;
	}
	public void setGoingStatus(String goingStatus) {
		this.goingStatus = goingStatus;
	}
	public String getSuspendStatus() {
		return suspendStatus;
	}
	public void setSuspendStatus(String suspendStatus) {
		this.suspendStatus = suspendStatus;
	}
	public String getCompleteStatus() {
		return completeStatus;
	}
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}
	public String getNoActiveStatus() {
		return noActiveStatus;
	}
	public void setNoActiveStatus(String noActiveStatus) {
		this.noActiveStatus = noActiveStatus;
	}
	public String getNoReplyStatus() {
		return noReplyStatus;
	}
	public void setNoReplyStatus(String noReplyStatus) {
		this.noReplyStatus = noReplyStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ExecPlanProcessing> getExecPlanProcessing() {
		return execPlanProcessing;
	}
	public void setExecPlanProcessing(List<ExecPlanProcessing> execPlanProcessing) {
		this.execPlanProcessing = execPlanProcessing;
	}
}
