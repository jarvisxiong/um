// 
package com.hhz.ump.entity.ct;
// Generated 2012-1-11 10:24:05 by Hibernate Tools 3.2.2.GA


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
 * CtLedgerHismark generated by hbm2java
 */
@Entity
@Table(name="CT_LEDGER_HISMARK"
    
)
public class CtLedgerHismark  implements java.io.Serializable {


     private String ctLedgerHismarkId;
     private CtLedger ctLedger;
     private Long sequenceNo;
     private String remark;
     private String creator;
     private String createdCenterCd;
     private String createdPositionCd;
     private Date createdDate;
     private String updator;
     private String updatedCenterCd;
     private String updatedPositionCd;
     private Date updatedDate;
     private long recordVersion;
     private String createdDeptCd;
     private String updatedDeptCd;

    public CtLedgerHismark() {
    }

	
    public CtLedgerHismark(String ctLedgerHismarkId, CtLedger ctLedger, long recordVersion) {
        this.ctLedgerHismarkId = ctLedgerHismarkId;
        this.ctLedger = ctLedger;
        this.recordVersion = recordVersion;
    }
    public CtLedgerHismark(String ctLedgerHismarkId, CtLedger ctLedger, Long sequenceNo, String remark, String creator, String createdCenterCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedPositionCd, Date updatedDate, long recordVersion, String createdDeptCd, String updatedDeptCd) {
       this.ctLedgerHismarkId = ctLedgerHismarkId;
       this.ctLedger = ctLedger;
       this.sequenceNo = sequenceNo;
       this.remark = remark;
       this.creator = creator;
       this.createdCenterCd = createdCenterCd;
       this.createdPositionCd = createdPositionCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedCenterCd = updatedCenterCd;
       this.updatedPositionCd = updatedPositionCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
       this.createdDeptCd = createdDeptCd;
       this.updatedDeptCd = updatedDeptCd;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="CT_LEDGER_HISMARK_ID", unique=true, nullable=false, length=50)
    public String getCtLedgerHismarkId() {
        return this.ctLedgerHismarkId;
    }
    
    public void setCtLedgerHismarkId(String ctLedgerHismarkId) {
        this.ctLedgerHismarkId = ctLedgerHismarkId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CT_LEDGER_ID", nullable=false)
    public CtLedger getCtLedger() {
        return this.ctLedger;
    }
    
    public void setCtLedger(CtLedger ctLedger) {
        this.ctLedger = ctLedger;
    }
    
    @Column(name="SEQUENCE_NO", precision=18, scale=0)
    public Long getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
    
    @Column(name="REMARK", length=4000)
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
    
    @Column(name="CREATED_DEPT_CD", length=20)
    public String getCreatedDeptCd() {
        return this.createdDeptCd;
    }
    
    public void setCreatedDeptCd(String createdDeptCd) {
        this.createdDeptCd = createdDeptCd;
    }
    
    @Column(name="UPDATED_DEPT_CD", length=20)
    public String getUpdatedDeptCd() {
        return this.updatedDeptCd;
    }
    
    public void setUpdatedDeptCd(String updatedDeptCd) {
        this.updatedDeptCd = updatedDeptCd;
    }




}


