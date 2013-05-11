// 
package com.hhz.uums.vo.vw;
// Generated 2010-1-13 13:53:48 by Hibernate Tools 3.2.4.GA

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WsAppAttachFile implements Serializable {
	private static final long serialVersionUID = -1963611331252272358L;
	private String appAttachFileId;
	private String bizEntityId;
	private String bizModuleCd;
	private String fileName;
	private String realFileName;
	private String filePath;
	private String fileTypeName;
	private BigDecimal fileSize;
	private String statusCd;
	private String remark;
	private String creator;
	private String createdDeptCd;
	private Date createdDate;
	private String updator;
	private String updatedDeptCd;
	private Date updatedDate;
	private long recordVersion;

	public String getAppAttachFileId() {
		return appAttachFileId;
	}

	public void setAppAttachFileId(String appAttachFileId) {
		this.appAttachFileId = appAttachFileId;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public BigDecimal getFileSize() {
		return fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
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
	public String getCreatedDeptCd() {
		return createdDeptCd;
	}
	public void setCreatedDeptCd(String createdDeptCd) {
		this.createdDeptCd = createdDeptCd;
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
	public String getUpdatedDeptCd() {
		return updatedDeptCd;
	}
	public void setUpdatedDeptCd(String updatedDeptCd) {
		this.updatedDeptCd = updatedDeptCd;
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

}

