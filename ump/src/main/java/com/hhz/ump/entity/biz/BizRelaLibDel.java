// 
package com.hhz.ump.entity.biz;
// Generated 2011-8-25 16:09:15 by Hibernate Tools 3.2.2.GA


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * BizRelaLibDel generated by hbm2java
 */
@Entity
@Table(name="BIZ_RELA_LIB_DEL"
    
)
public class BizRelaLibDel  implements java.io.Serializable {


     private String bizRelaLibDelId;
     private String relaProvince;
     private String relaArea;
     private String relaName;
     private String relaUnit;
     private String relaPos;
     private String relaAddress;
     private String relaPhone;
     private String relaMobile;
     private String relaFax;
     private String submitCenterCd;
     private String relaTypeCd;
     private Long sequenceNo;
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
     private String relaLevelCd;
     private String submitPersion;
     private String submitCenterName;
     private String relaProvinceName;

    public BizRelaLibDel() {
    }

	
    public BizRelaLibDel(String bizRelaLibDelId, long recordVersion) {
        this.bizRelaLibDelId = bizRelaLibDelId;
        this.recordVersion = recordVersion;
    }
    public BizRelaLibDel(String bizRelaLibDelId, String relaProvince, String relaArea, String relaName, String relaUnit, String relaPos, String relaAddress, String relaPhone, String relaMobile, String relaFax, String submitCenterCd, String relaTypeCd, Long sequenceNo, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion, String relaLevelCd, String submitPersion, String submitCenterName, String relaProvinceName) {
       this.bizRelaLibDelId = bizRelaLibDelId;
       this.relaProvince = relaProvince;
       this.relaArea = relaArea;
       this.relaName = relaName;
       this.relaUnit = relaUnit;
       this.relaPos = relaPos;
       this.relaAddress = relaAddress;
       this.relaPhone = relaPhone;
       this.relaMobile = relaMobile;
       this.relaFax = relaFax;
       this.submitCenterCd = submitCenterCd;
       this.relaTypeCd = relaTypeCd;
       this.sequenceNo = sequenceNo;
       this.remark = remark;
       this.creator = creator;
       this.createdCenterCd = createdCenterCd;
       this.createdDeptCd = createdDeptCd;
       this.createdPositionCd = createdPositionCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedCenterCd = updatedCenterCd;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedPositionCd = updatedPositionCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
       this.relaLevelCd = relaLevelCd;
       this.submitPersion = submitPersion;
       this.submitCenterName = submitCenterName;
       this.relaProvinceName = relaProvinceName;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="BIZ_RELA_LIB_DEL_ID", unique=true, nullable=false, length=50)
    public String getBizRelaLibDelId() {
        return this.bizRelaLibDelId;
    }
    
    public void setBizRelaLibDelId(String bizRelaLibDelId) {
        this.bizRelaLibDelId = bizRelaLibDelId;
    }
    
    @Column(name="RELA_PROVINCE", length=100)
    public String getRelaProvince() {
        return this.relaProvince;
    }
    
    public void setRelaProvince(String relaProvince) {
        this.relaProvince = relaProvince;
    }
    
    @Column(name="RELA_AREA", length=200)
    public String getRelaArea() {
        return this.relaArea;
    }
    
    public void setRelaArea(String relaArea) {
        this.relaArea = relaArea;
    }
    
    @Column(name="RELA_NAME", length=100)
    public String getRelaName() {
        return this.relaName;
    }
    
    public void setRelaName(String relaName) {
        this.relaName = relaName;
    }
    
    @Column(name="RELA_UNIT", length=1000)
    public String getRelaUnit() {
        return this.relaUnit;
    }
    
    public void setRelaUnit(String relaUnit) {
        this.relaUnit = relaUnit;
    }
    
    @Column(name="RELA_POS", length=100)
    public String getRelaPos() {
        return this.relaPos;
    }
    
    public void setRelaPos(String relaPos) {
        this.relaPos = relaPos;
    }
    
    @Column(name="RELA_ADDRESS", length=1000)
    public String getRelaAddress() {
        return this.relaAddress;
    }
    
    public void setRelaAddress(String relaAddress) {
        this.relaAddress = relaAddress;
    }
    
    @Column(name="RELA_PHONE", length=100)
    public String getRelaPhone() {
        return this.relaPhone;
    }
    
    public void setRelaPhone(String relaPhone) {
        this.relaPhone = relaPhone;
    }
    
    @Column(name="RELA_MOBILE", length=100)
    public String getRelaMobile() {
        return this.relaMobile;
    }
    
    public void setRelaMobile(String relaMobile) {
        this.relaMobile = relaMobile;
    }
    
    @Column(name="RELA_FAX", length=100)
    public String getRelaFax() {
        return this.relaFax;
    }
    
    public void setRelaFax(String relaFax) {
        this.relaFax = relaFax;
    }
    
    @Column(name="SUBMIT_CENTER_CD", length=100)
    public String getSubmitCenterCd() {
        return this.submitCenterCd;
    }
    
    public void setSubmitCenterCd(String submitCenterCd) {
        this.submitCenterCd = submitCenterCd;
    }
    
    @Column(name="RELA_TYPE_CD", length=20)
    public String getRelaTypeCd() {
        return this.relaTypeCd;
    }
    
    public void setRelaTypeCd(String relaTypeCd) {
        this.relaTypeCd = relaTypeCd;
    }
    
    @Column(name="SEQUENCE_NO", precision=10, scale=0)
    public Long getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="CREATOR", length=20)
    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Column(name="CREATED_CENTER_CD", length=20)
    public String getCreatedCenterCd() {
        return this.createdCenterCd;
    }
    
    public void setCreatedCenterCd(String createdCenterCd) {
        this.createdCenterCd = createdCenterCd;
    }
    
    @Column(name="CREATED_DEPT_CD", length=20)
    public String getCreatedDeptCd() {
        return this.createdDeptCd;
    }
    
    public void setCreatedDeptCd(String createdDeptCd) {
        this.createdDeptCd = createdDeptCd;
    }
    
    @Column(name="CREATED_POSITION_CD", length=20)
    public String getCreatedPositionCd() {
        return this.createdPositionCd;
    }
    
    public void setCreatedPositionCd(String createdPositionCd) {
        this.createdPositionCd = createdPositionCd;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", length=7)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Column(name="UPDATOR", length=20)
    public String getUpdator() {
        return this.updator;
    }
    
    public void setUpdator(String updator) {
        this.updator = updator;
    }
    
    @Column(name="UPDATED_CENTER_CD", length=20)
    public String getUpdatedCenterCd() {
        return this.updatedCenterCd;
    }
    
    public void setUpdatedCenterCd(String updatedCenterCd) {
        this.updatedCenterCd = updatedCenterCd;
    }
    
    @Column(name="UPDATED_DEPT_CD", length=20)
    public String getUpdatedDeptCd() {
        return this.updatedDeptCd;
    }
    
    public void setUpdatedDeptCd(String updatedDeptCd) {
        this.updatedDeptCd = updatedDeptCd;
    }
    
    @Column(name="UPDATED_POSITION_CD", length=20)
    public String getUpdatedPositionCd() {
        return this.updatedPositionCd;
    }
    
    public void setUpdatedPositionCd(String updatedPositionCd) {
        this.updatedPositionCd = updatedPositionCd;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE", length=7)
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Column(name="RECORD_VERSION", nullable=false, precision=10, scale=0)
    @Version 
    public long getRecordVersion() {
        return this.recordVersion;
    }
    
    public void setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
    }
    
    @Column(name="RELA_LEVEL_CD", length=20)
    public String getRelaLevelCd() {
        return this.relaLevelCd;
    }
    
    public void setRelaLevelCd(String relaLevelCd) {
        this.relaLevelCd = relaLevelCd;
    }
    
    @Column(name="SUBMIT_PERSION", length=50)
    public String getSubmitPersion() {
        return this.submitPersion;
    }
    
    public void setSubmitPersion(String submitPersion) {
        this.submitPersion = submitPersion;
    }
    
    @Column(name="SUBMIT_CENTER_NAME", length=100)
    public String getSubmitCenterName() {
        return this.submitCenterName;
    }
    
    public void setSubmitCenterName(String submitCenterName) {
        this.submitCenterName = submitCenterName;
    }
    
    @Column(name="RELA_PROVINCE_NAME", length=100)
    public String getRelaProvinceName() {
        return this.relaProvinceName;
    }
    
    public void setRelaProvinceName(String relaProvinceName) {
        this.relaProvinceName = relaProvinceName;
    }




}


