// 
package com.hhz.ump.web.vo;

import java.util.Date;

public class CostBudgetSectionVo implements java.io.Serializable {

	private String costProjectSectionId;
	private String costBudgetAuthId;
	private String authUiids;
	private String authUserNames;
	private String authSysIds;
	private String authSysNames;
	private String remark;
	private String creator;
	private String createdCenterCd;
	private String createdDeptCd;
	private String createdPositionCd;
	private Date createdDate;
	private String updator;
	private String updatedCenterCd;
	private String updatedDeptCd;
	private String updatedPositionCd;
	private Date updatedDate;
	private long recordVersion;
	private String projectCd;
	private String projectName;
	private String sectionCd;
	private String sectionName;
	private String briefName;
 
	public String getCostProjectSectionId() {
		return costProjectSectionId;
	}

	public void setCostProjectSectionId(String costProjectSectionId) {
		this.costProjectSectionId = costProjectSectionId;
	}

	public String getCostBudgetAuthId() {
		return costBudgetAuthId;
	}

	public void setCostBudgetAuthId(String costBudgetAuthId) {
		this.costBudgetAuthId = costBudgetAuthId;
	}

	public String getAuthUiids() {
		return authUiids;
	}

	public void setAuthUiids(String authUiids) {
		this.authUiids = authUiids;
	}
 

	public String getAuthSysIds() {
		return authSysIds;
	}

	public void setAuthSysIds(String authSysIds) {
		this.authSysIds = authSysIds;
	}

	public String getAuthSysNames() {
		return authSysNames;
	}

	public void setAuthSysNames(String authSysNames) {
		this.authSysNames = authSysNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedCenterCd() {
		return createdCenterCd;
	}

	public void setCreatedCenterCd(String createdCenterCd) {
		this.createdCenterCd = createdCenterCd;
	}

	public String getCreatedDeptCd() {
		return createdDeptCd;
	}

	public void setCreatedDeptCd(String createdDeptCd) {
		this.createdDeptCd = createdDeptCd;
	}

	public String getCreatedPositionCd() {
		return createdPositionCd;
	}

	public void setCreatedPositionCd(String createdPositionCd) {
		this.createdPositionCd = createdPositionCd;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getUpdatedCenterCd() {
		return updatedCenterCd;
	}

	public void setUpdatedCenterCd(String updatedCenterCd) {
		this.updatedCenterCd = updatedCenterCd;
	}

	public String getUpdatedDeptCd() {
		return updatedDeptCd;
	}

	public void setUpdatedDeptCd(String updatedDeptCd) {
		this.updatedDeptCd = updatedDeptCd;
	}

	public String getUpdatedPositionCd() {
		return updatedPositionCd;
	}

	public void setUpdatedPositionCd(String updatedPositionCd) {
		this.updatedPositionCd = updatedPositionCd;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public String getAuthUserNames() {
		return authUserNames;
	}

	public void setAuthUserNames(String authUserNames) {
		this.authUserNames = authUserNames;
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

	public String getSectionCd() {
		return sectionCd;
	}

	public void setSectionCd(String sectionCd) {
		this.sectionCd = sectionCd;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	} 
	
}
