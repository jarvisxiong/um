// 
package com.hhz.ump.entity.fin;
// Generated 2010-8-4 11:02:15 by Hibernate Tools 3.2.4.GA


import java.math.BigDecimal;
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
 * FinInOutDetail generated by hbm2java
 */
@Entity
@Table(name="FIN_IN_OUT_DETAIL"
    
)
public class FinInOutDetail  implements java.io.Serializable {


     private String finInOutDetailId;
     private FinProjectAcctRel finProjectAcctRel;
     private String finItemCd;
     private BigDecimal inAmount;
     private BigDecimal outAmount;
     private BigDecimal balance;
     private String summaryDesc;
     private String statusCd;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;
     private String remark;

    public FinInOutDetail() {
    }

	
    public FinInOutDetail(String finInOutDetailId, FinProjectAcctRel finProjectAcctRel, long recordVersion) {
        this.finInOutDetailId = finInOutDetailId;
        this.finProjectAcctRel = finProjectAcctRel;
        this.recordVersion = recordVersion;
    }
    public FinInOutDetail(String finInOutDetailId, FinProjectAcctRel finProjectAcctRel, String finItemCd, BigDecimal inAmount, BigDecimal outAmount, BigDecimal balance, String summaryDesc, String statusCd, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion, String remark) {
       this.finInOutDetailId = finInOutDetailId;
       this.finProjectAcctRel = finProjectAcctRel;
       this.finItemCd = finItemCd;
       this.inAmount = inAmount;
       this.outAmount = outAmount;
       this.balance = balance;
       this.summaryDesc = summaryDesc;
       this.statusCd = statusCd;
       this.creator = creator;
       this.createdDeptCd = createdDeptCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
       this.remark = remark;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 

    
    @Column(name="FIN_IN_OUT_DETAIL_ID", unique=true, nullable=false, length=50)
    public String getFinInOutDetailId() {
        return this.finInOutDetailId;
    }
    
    public void setFinInOutDetailId(String finInOutDetailId) {
        this.finInOutDetailId = finInOutDetailId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIN_PROJECT_ACCT_REL_ID", nullable=false)
    public FinProjectAcctRel getFinProjectAcctRel() {
        return this.finProjectAcctRel;
    }
    
    public void setFinProjectAcctRel(FinProjectAcctRel finProjectAcctRel) {
        this.finProjectAcctRel = finProjectAcctRel;
    }

    
    @Column(name="FIN_ITEM_CD", length=20)
    public String getFinItemCd() {
        return this.finItemCd;
    }
    
    public void setFinItemCd(String finItemCd) {
        this.finItemCd = finItemCd;
    }

    
    @Column(name="IN_AMOUNT", precision=18, scale=4)
    public BigDecimal getInAmount() {
        return this.inAmount;
    }
    
    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    
    @Column(name="OUT_AMOUNT", precision=18, scale=4)
    public BigDecimal getOutAmount() {
        return this.outAmount;
    }
    
    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    
    @Column(name="BALANCE", precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    
    @Column(name="SUMMARY_DESC", length=100)
    public String getSummaryDesc() {
        return this.summaryDesc;
    }
    
    public void setSummaryDesc(String summaryDesc) {
        this.summaryDesc = summaryDesc;
    }

    
    @Column(name="STATUS_CD", length=20)
    public String getStatusCd() {
        return this.statusCd;
    }
    
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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

    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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


