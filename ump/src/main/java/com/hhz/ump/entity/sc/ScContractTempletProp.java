// 
package com.hhz.ump.entity.sc;
// Generated 2012-2-13 10:23:34 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.BatchSize;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ScContractTempletProp generated by hbm2java
 */
@Entity
@Table(name="SC_CONTRACT_TEMPLET_PROP"
    
)
public class ScContractTempletProp  implements java.io.Serializable {


     private String scContractTempletPropId;
     private ScContractTemplet scContractTemplet;
     private String propName;
     private String isKey;
     private String isNum;
     private Long lenLimit;
     private long recordVersion;
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

    public ScContractTempletProp() {
    }

	
    public ScContractTempletProp(String scContractTempletPropId, ScContractTemplet scContractTemplet, long recordVersion) {
        this.scContractTempletPropId = scContractTempletPropId;
        this.scContractTemplet = scContractTemplet;
        this.recordVersion = recordVersion;
    }
    public ScContractTempletProp(String scContractTempletPropId, ScContractTemplet scContractTemplet, String propName, String isKey, String isNum, Long lenLimit, long recordVersion, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate) {
       this.scContractTempletPropId = scContractTempletPropId;
       this.scContractTemplet = scContractTemplet;
       this.propName = propName;
       this.isKey = isKey;
       this.isNum = isNum;
       this.lenLimit = lenLimit;
       this.recordVersion = recordVersion;
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
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="SC_CONTRACT_TEMPLET_PROP_ID", unique=true, nullable=false, length=50)
    public String getScContractTempletPropId() {
        return this.scContractTempletPropId;
    }
    
    public void setScContractTempletPropId(String scContractTempletPropId) {
        this.scContractTempletPropId = scContractTempletPropId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTRACT_TEMPLET_ID", nullable=false)
    public ScContractTemplet getScContractTemplet() {
        return this.scContractTemplet;
    }
    
    public void setScContractTemplet(ScContractTemplet scContractTemplet) {
        this.scContractTemplet = scContractTemplet;
    }
    
    @Column(name="PROP_NAME", length=50)
    public String getPropName() {
        return this.propName;
    }
    
    public void setPropName(String propName) {
        this.propName = propName;
    }
    
    @Column(name="IS_KEY", length=10)
    public String getIsKey() {
        return this.isKey;
    }
    
    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }
    
    @Column(name="IS_NUM", length=10)
    public String getIsNum() {
        return this.isNum;
    }
    
    public void setIsNum(String isNum) {
        this.isNum = isNum;
    }
    
    @Column(name="LEN_LIMIT", precision=10, scale=0)
    public Long getLenLimit() {
        return this.lenLimit;
    }
    
    public void setLenLimit(Long lenLimit) {
        this.lenLimit = lenLimit;
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




}


