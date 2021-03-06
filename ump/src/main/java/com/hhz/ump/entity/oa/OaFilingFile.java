// 
package com.hhz.ump.entity.oa;
// Generated 2010-8-11 13:25:01 by Hibernate Tools 3.2.4.GA


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * OaFilingFile generated by hbm2java
 */
@Entity
@Table(name="OA_FILING_FILE"
    
)
public class OaFilingFile  implements java.io.Serializable {


     private String oaFilingFileId;
     private OaFilingFolder oaFilingFolder;
     private String fileName;
     private String disabledFlg;
     private String deletedFlg;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;

    public OaFilingFile() {
    }

	
    public OaFilingFile(String oaFilingFileId, OaFilingFolder oaFilingFolder, long recordVersion) {
        this.oaFilingFileId = oaFilingFileId;
        this.oaFilingFolder = oaFilingFolder;
        this.recordVersion = recordVersion;
    }
    public OaFilingFile(String oaFilingFileId, OaFilingFolder oaFilingFolder, String fileName, String disabledFlg, String deletedFlg, String remark, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion) {
       this.oaFilingFileId = oaFilingFileId;
       this.oaFilingFolder = oaFilingFolder;
       this.fileName = fileName;
       this.disabledFlg = disabledFlg;
       this.deletedFlg = deletedFlg;
       this.remark = remark;
       this.creator = creator;
       this.createdDeptCd = createdDeptCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 

    
    @Column(name="OA_FILING_FILE_ID", unique=true, nullable=false, length=50)
    public String getOaFilingFileId() {
        return this.oaFilingFileId;
    }
    
    public void setOaFilingFileId(String oaFilingFileId) {
        this.oaFilingFileId = oaFilingFileId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OA_FILING_FOLDER_ID", nullable=false)
    public OaFilingFolder getOaFilingFolder() {
        return this.oaFilingFolder;
    }
    
    public void setOaFilingFolder(OaFilingFolder oaFilingFolder) {
        this.oaFilingFolder = oaFilingFolder;
    }

    
    @Column(name="FILE_NAME", length=200)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    @Column(name="DISABLED_FLG", length=1)
    public String getDisabledFlg() {
        return this.disabledFlg;
    }
    
    public void setDisabledFlg(String disabledFlg) {
        this.disabledFlg = disabledFlg;
    }

    
    @Column(name="DELETED_FLG", length=1)
    public String getDeletedFlg() {
        return this.deletedFlg;
    }
    
    public void setDeletedFlg(String deletedFlg) {
        this.deletedFlg = deletedFlg;
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

    
    @Column(name="CREATED_DEPT_CD", length=20)
    public String getCreatedDeptCd() {
        return this.createdDeptCd;
    }
    
    public void setCreatedDeptCd(String createdDeptCd) {
        this.createdDeptCd = createdDeptCd;
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

    
    @Column(name="UPDATED_DEPT_CD", length=20)
    public String getUpdatedDeptCd() {
        return this.updatedDeptCd;
    }
    
    public void setUpdatedDeptCd(String updatedDeptCd) {
        this.updatedDeptCd = updatedDeptCd;
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


    private String createdCenterCd;
    private String createdPositionCd;
    private String updatedCenterCd;
    private String updatedPositionCd;
    @Column(name="CREATED_CENTER_CD", length=20)
    public String getCreatedCenterCd() {
        return this.createdCenterCd;
    }
    
    public void setCreatedCenterCd(String createdCenterCd) {
        this.createdCenterCd = createdCenterCd;
    }
    
    @Column(name="CREATED_POSITION_CD", length=20)
    public String getCreatedPositionCd() {
        return this.createdPositionCd;
    }
    
    public void setCreatedPositionCd(String createdPositionCd) {
        this.createdPositionCd = createdPositionCd;
    }
    
    @Column(name="UPDATED_CENTER_CD", length=20)
    public String getUpdatedCenterCd() {
        return this.updatedCenterCd;
    }
    
    public void setUpdatedCenterCd(String updatedCenterCd) {
        this.updatedCenterCd = updatedCenterCd;
    }
    
    @Column(name="UPDATED_POSITION_CD", length=20)
    public String getUpdatedPositionCd() {
        return this.updatedPositionCd;
    }
    
    public void setUpdatedPositionCd(String updatedPositionCd) {
        this.updatedPositionCd = updatedPositionCd;
    }



}


