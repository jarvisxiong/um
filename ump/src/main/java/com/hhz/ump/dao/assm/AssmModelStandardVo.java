package com.hhz.ump.dao.assm;

import java.math.BigDecimal;
import java.util.Date;

public class AssmModelStandardVo implements java.io.Serializable{

	 private String assmModelStandardId;
     private String assmName;
     private String assmModelId;
     private String projectCd;
     private String projectName;
     private String equipName;
     private String stanCode;
     private BigDecimal stanNum;//标准配置数
     private BigDecimal hasNum;//已经配置数
     
     private String stanDesc;
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
     
     
	public String getAssmModelStandardId() {
		return assmModelStandardId;
	}
	public void setAssmModelStandardId(String assmModelStandardId) {
		this.assmModelStandardId = assmModelStandardId;
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
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getStanCode() {
		return stanCode;
	}
	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}
	public BigDecimal getStanNum() {
		return stanNum;
	}
	public void setStanNum(BigDecimal stanNum) {
		this.stanNum = stanNum;
	}
	public String getStanDesc() {
		return stanDesc;
	}
	public void setStanDesc(String stanDesc) {
		this.stanDesc = stanDesc;
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
	public String getAssmName() {
		return assmName;
	}
	public void setAssmName(String assmName) {
		this.assmName = assmName;
	}
	public String getAssmModelId() {
		return assmModelId;
	}
	public void setAssmModelId(String assmModelId) {
		this.assmModelId = assmModelId;
	}
	public BigDecimal getHasNum() {
		return hasNum;
	}
	public void setHasNum(BigDecimal hasNum) {
		this.hasNum = hasNum;
	}
     
     
}
