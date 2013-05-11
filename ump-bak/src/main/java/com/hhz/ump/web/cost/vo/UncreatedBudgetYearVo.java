package com.hhz.ump.web.cost.vo;

public class UncreatedBudgetYearVo {
	
	//年月
	 private String year;
	 //项目信息
	 private String costProjectSectionId;
     private String projectCd;
     private String projectName;
     private String sectionBizCd;
     private String sectionName;
     private Long sequenceNo;
     private String remark;
     
     
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCostProjectSectionId() {
		return costProjectSectionId;
	}
	public void setCostProjectSectionId(String costProjectSectionId) {
		this.costProjectSectionId = costProjectSectionId;
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
	public String getSectionBizCd() {
		return sectionBizCd;
	}
	public void setSectionBizCd(String sectionBizCd) {
		this.sectionBizCd = sectionBizCd;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	} 
     
     

}
