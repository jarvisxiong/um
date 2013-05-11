/**
 * 
 */
package com.hhz.ump.entity.ct;

import java.util.Date;

/**
 * 地块所应的业态JSON实体
 * @author qlb 12/12/2011
 *
 */
public class CtOperationJson {

    private String ctOperationId;
    private String ctLandId;    
    private String operTypeCd;
    private String operTypeName;
    private String contentDesc;
    private String remark;
    private String creator;
    private String createdCenterCd;
    private String createdPositionCd;
    private Date createdDate;
    private String updator;
    private String updatedCenterCd;
    private String updatedPositionCd;
    private Date updatedDate;
    private boolean isBaseOperation;
    /**
	 * @return the isBaseOperation
	 */
	public boolean isBaseOperation() {
		return isBaseOperation;
	}
	/**
	 * @param isBaseOperation the isBaseOperation to set
	 */
	public void setBaseOperation(boolean isBaseOperation) {
		this.isBaseOperation = isBaseOperation;
	}
	private long recordVersion;
    
    /**
	 * @return the ctOperationId
	 */
	public String getCtOperationId() {
		return ctOperationId;
	}
	/**
	 * @param ctOperationId the ctOperationId to set
	 */
	public void setCtOperationId(String ctOperationId) {
		this.ctOperationId = ctOperationId;
	}
	/**
	 * @return the ctLandId
	 */
	public String getCtLandId() {
		return ctLandId;
	}
	/**
	 * @param ctLandId the ctLandId to set
	 */
	public void setCtLandId(String ctLandId) {
		this.ctLandId = ctLandId;
	}
	/**
	 * @return the operTypeCd
	 */
	public String getOperTypeCd() {
		return operTypeCd;
	}
	/**
	 * @param operTypeCd the operTypeCd to set
	 */
	public void setOperTypeCd(String operTypeCd) {
		this.operTypeCd = operTypeCd;
	}
	/**
	 * @return the operTypeName
	 */
	public String getOperTypeName() {
		return operTypeName;
	}
	/**
	 * @param operTypeName the operTypeName to set
	 */
	public void setOperTypeName(String operTypeName) {
		this.operTypeName = operTypeName;
	}
	/**
	 * @return the contentDesc
	 */
	public String getContentDesc() {
		return contentDesc;
	}
	/**
	 * @param contentDesc the contentDesc to set
	 */
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the createdCenterCd
	 */
	public String getCreatedCenterCd() {
		return createdCenterCd;
	}
	/**
	 * @param createdCenterCd the createdCenterCd to set
	 */
	public void setCreatedCenterCd(String createdCenterCd) {
		this.createdCenterCd = createdCenterCd;
	}
	/**
	 * @return the createdPositionCd
	 */
	public String getCreatedPositionCd() {
		return createdPositionCd;
	}
	/**
	 * @param createdPositionCd the createdPositionCd to set
	 */
	public void setCreatedPositionCd(String createdPositionCd) {
		this.createdPositionCd = createdPositionCd;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updator
	 */
	public String getUpdator() {
		return updator;
	}
	/**
	 * @param updator the updator to set
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	/**
	 * @return the updatedCenterCd
	 */
	public String getUpdatedCenterCd() {
		return updatedCenterCd;
	}
	/**
	 * @param updatedCenterCd the updatedCenterCd to set
	 */
	public void setUpdatedCenterCd(String updatedCenterCd) {
		this.updatedCenterCd = updatedCenterCd;
	}
	/**
	 * @return the updatedPositionCd
	 */
	public String getUpdatedPositionCd() {
		return updatedPositionCd;
	}
	/**
	 * @param updatedPositionCd the updatedPositionCd to set
	 */
	public void setUpdatedPositionCd(String updatedPositionCd) {
		this.updatedPositionCd = updatedPositionCd;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the recordVersion
	 */
	public long getRecordVersion() {
		return recordVersion;
	}
	/**
	 * @param recordVersion the recordVersion to set
	 */
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}
	
}
