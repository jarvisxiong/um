// 
package com.hhz.ump.entity.mate;
// Generated 2011-5-17 11:30:57 by Hibernate Tools 3.2.2.GA


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
 * MateOwnerType generated by hbm2java
 */
@Entity
@Table(name="MATE_OWNER_TYPE"
    
)
public class MateOwnerType  implements java.io.Serializable {


     private String mateOwnerTypeId;
     private String ownerMaterialType;
     private String projectCd;
     private String typeName;
     private String remark;
     private String ownerParMaterialType;
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

    public MateOwnerType() {
    }

	
    public MateOwnerType(String mateOwnerTypeId, long recordVersion) {
        this.mateOwnerTypeId = mateOwnerTypeId;
        this.recordVersion = recordVersion;
    }
    public MateOwnerType(String mateOwnerTypeId, String ownerMaterialType, String projectCd, String typeName, String remark, String ownerParMaterialType, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion) {
       this.mateOwnerTypeId = mateOwnerTypeId;
       this.ownerMaterialType = ownerMaterialType;
       this.projectCd = projectCd;
       this.typeName = typeName;
       this.remark = remark;
       this.ownerParMaterialType = ownerParMaterialType;
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
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="MATE_OWNER_TYPE_ID", unique=true, nullable=false, length=50)
    public String getMateOwnerTypeId() {
        return this.mateOwnerTypeId;
    }
    
    public void setMateOwnerTypeId(String mateOwnerTypeId) {
        this.mateOwnerTypeId = mateOwnerTypeId;
    }
    
    @Column(name="OWNER_MATERIAL_TYPE", length=10)
    public String getOwnerMaterialType() {
        return this.ownerMaterialType;
    }
    
    public void setOwnerMaterialType(String ownerMaterialType) {
        this.ownerMaterialType = ownerMaterialType;
    }
    
    @Column(name="PROJECT_CD", length=10)
    public String getProjectCd() {
        return this.projectCd;
    }
    
    public void setProjectCd(String projectCd) {
        this.projectCd = projectCd;
    }
    
    @Column(name="TYPE_NAME", length=20)
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="OWNER_PAR_MATERIAL_TYPE", length=10)
    public String getOwnerParMaterialType() {
        return this.ownerParMaterialType;
    }
    
    public void setOwnerParMaterialType(String ownerParMaterialType) {
        this.ownerParMaterialType = ownerParMaterialType;
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




}


