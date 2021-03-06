// 
package com.hhz.ump.entity.bid;
// Generated 2012-6-27 11:01:25 by Hibernate Tools 3.2.2.GA


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
 * BidLedgAttaRel generated by hbm2java
 */
@Entity
@Table(name="BID_LEDG_ATTA_REL"
    
)
public class BidLedgAttaRel  implements java.io.Serializable {

	private static final long serialVersionUID = 8308787734057402014L;
	private String bidLedgAttaRelId;
     private BidLedger bidLedger;
     private BigDecimal batchNo;
     private String bidAttaEval1Flg = "0"; //邀标单位资质审批表（必填项）
     private String bidAttaEval2Flg = "0"; //中标单位报价清单
     private String bidAttaEval3Flg = "0"; //投标报价汇总表
     private String bidAttaEval4Flg = "0"; //预算和批准文件
     private String bidAttaEval5Flg = "0"; //招标文件、投标文件
     private String bidAttaEval6Flg = "0"; //招标图纸（非战略）
     private String bidAttaEval7Flg = "0"; //招标上会纪要（非战略）
     private String bidAttaEval9Flg = "0"; //其他文件
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

    public BidLedgAttaRel() {
    }

	
    public BidLedgAttaRel(String bidLedgAttaRelId, BidLedger bidLedger, long recordVersion) {
        this.bidLedgAttaRelId = bidLedgAttaRelId;
        this.bidLedger = bidLedger;
        this.recordVersion = recordVersion;
    }
    public BidLedgAttaRel(String bidLedgAttaRelId, BidLedger bidLedger, BigDecimal batchNo, String bidAttaEval1Flg, String bidAttaEval2Flg, String bidAttaEval3Flg, String bidAttaEval4Flg, String bidAttaEval5Flg, String bidAttaEval9Flg, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion, String bidAttaEval6Flg, String bidAttaEval7Flg) {
       this.bidLedgAttaRelId = bidLedgAttaRelId;
       this.bidLedger = bidLedger;
       this.batchNo = batchNo;
       this.bidAttaEval1Flg = bidAttaEval1Flg;
       this.bidAttaEval2Flg = bidAttaEval2Flg;
       this.bidAttaEval3Flg = bidAttaEval3Flg;
       this.bidAttaEval4Flg = bidAttaEval4Flg;
       this.bidAttaEval5Flg = bidAttaEval5Flg;
       this.bidAttaEval9Flg = bidAttaEval9Flg;
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
       this.bidAttaEval6Flg = bidAttaEval6Flg;
       this.bidAttaEval7Flg = bidAttaEval7Flg;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="BID_LEDG_ATTA_REL_ID", unique=true, nullable=false, length=50)
    public String getBidLedgAttaRelId() {
        return this.bidLedgAttaRelId;
    }
    
    public void setBidLedgAttaRelId(String bidLedgAttaRelId) {
        this.bidLedgAttaRelId = bidLedgAttaRelId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BID_LEDGER_ID", nullable=false)
    public BidLedger getBidLedger() {
        return this.bidLedger;
    }
    
    public void setBidLedger(BidLedger bidLedger) {
        this.bidLedger = bidLedger;
    }
    
    @Column(name="BATCH_NO", precision=18)
    public BigDecimal getBatchNo() {
        return this.batchNo;
    }
    
    public void setBatchNo(BigDecimal batchNo) {
        this.batchNo = batchNo;
    }
    
    @Column(name="BID_ATTA_EVAL1_FLG", length=1)
    public String getBidAttaEval1Flg() {
        return this.bidAttaEval1Flg;
    }
    
    public void setBidAttaEval1Flg(String bidAttaEval1Flg) {
        this.bidAttaEval1Flg = bidAttaEval1Flg;
    }
    
    @Column(name="BID_ATTA_EVAL2_FLG", length=1)
    public String getBidAttaEval2Flg() {
        return this.bidAttaEval2Flg;
    }
    
    public void setBidAttaEval2Flg(String bidAttaEval2Flg) {
        this.bidAttaEval2Flg = bidAttaEval2Flg;
    }
    
    @Column(name="BID_ATTA_EVAL3_FLG", length=1)
    public String getBidAttaEval3Flg() {
        return this.bidAttaEval3Flg;
    }
    
    public void setBidAttaEval3Flg(String bidAttaEval3Flg) {
        this.bidAttaEval3Flg = bidAttaEval3Flg;
    }
    
    @Column(name="BID_ATTA_EVAL4_FLG", length=1)
    public String getBidAttaEval4Flg() {
        return this.bidAttaEval4Flg;
    }
    
    public void setBidAttaEval4Flg(String bidAttaEval4Flg) {
        this.bidAttaEval4Flg = bidAttaEval4Flg;
    }
    
    @Column(name="BID_ATTA_EVAL5_FLG", length=1)
    public String getBidAttaEval5Flg() {
        return this.bidAttaEval5Flg;
    }
    
    public void setBidAttaEval5Flg(String bidAttaEval5Flg) {
        this.bidAttaEval5Flg = bidAttaEval5Flg;
    }
    
    @Column(name="BID_ATTA_EVAL9_FLG", length=1)
    public String getBidAttaEval9Flg() {
        return this.bidAttaEval9Flg;
    }
    
    public void setBidAttaEval9Flg(String bidAttaEval9Flg) {
        this.bidAttaEval9Flg = bidAttaEval9Flg;
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
    
    @Column(name="BID_ATTA_EVAL6_FLG", length=1)
    public String getBidAttaEval6Flg() {
        return this.bidAttaEval6Flg;
    }
    
    public void setBidAttaEval6Flg(String bidAttaEval6Flg) {
        this.bidAttaEval6Flg = bidAttaEval6Flg;
    }
    
    @Column(name="BID_ATTA_EVAL7_FLG", length=1)
    public String getBidAttaEval7Flg() {
        return this.bidAttaEval7Flg;
    }
    
    public void setBidAttaEval7Flg(String bidAttaEval7Flg) {
        this.bidAttaEval7Flg = bidAttaEval7Flg;
    }




}


