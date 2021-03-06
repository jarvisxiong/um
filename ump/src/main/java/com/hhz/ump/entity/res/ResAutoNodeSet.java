// 
package com.hhz.ump.entity.res;
// Generated 2011-7-11 16:35:13 by Hibernate Tools 3.2.2.GA


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
 * ResAutoNodeSet generated by hbm2java
 */
@Entity
@Table(name="RES_AUTO_NODE_SET"
    
)
public class ResAutoNodeSet  implements java.io.Serializable {


     private String resAutoNodeSetId;
     private ResAuthType resAuthType;
     private String approveNodeCd;
     private String approveUserCd;
     private String otherCondtion;
     private String extraNodeCd;
     private String extraUserCd;
     private Long levelOffset;
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
     private Long sequenceNo;
     private String conditionCd;
     private Boolean active;
     private Long approveRank;
     
     
     @Column(name="CONDITION_CD", length=20)
     public String getConditionCd() {
         return this.conditionCd;
     }
     
     public void setConditionCd(String conditionCd) {
         this.conditionCd = conditionCd;
     }
     @Column(name="APPROVE_RANK", length=20)
     public Long getApproveRank() {
		return approveRank;
	}

	public void setApproveRank(Long approveRank) {
		this.approveRank = approveRank;
	}

	@Column(name="SEQUENCE_NO", precision=10, scale=0)
     public Long getSequenceNo() {
         return this.sequenceNo;
     }
     
     public void setSequenceNo(Long sequenceNo) {
         this.sequenceNo = sequenceNo;
     }
    public ResAutoNodeSet() {
    }

	
    public ResAutoNodeSet(String resAutoNodeSetId, long recordVersion) {
        this.resAutoNodeSetId = resAutoNodeSetId;
        this.recordVersion = recordVersion;
    }
    public ResAutoNodeSet(String resAutoNodeSetId, String approveNodeCd, String approveUserCd, String extraNodeCd, String extraUserCd, Long levelOffset, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion,long approveRank) {
       this.resAutoNodeSetId = resAutoNodeSetId;
       this.approveNodeCd = approveNodeCd;
       this.approveUserCd = approveUserCd;
       this.extraNodeCd = extraNodeCd;
       this.extraUserCd = extraUserCd;
       this.levelOffset = levelOffset;
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
       this.approveRank =  approveRank;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="RES_AUTO_NODE_SET_ID", unique=true, nullable=false, length=50)
    public String getResAutoNodeSetId() {
        return this.resAutoNodeSetId;
    }
    
    public void setResAutoNodeSetId(String resAutoNodeSetId) {
        this.resAutoNodeSetId = resAutoNodeSetId;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RES_AUTH_TYPE_ID", nullable=false)
    public ResAuthType getResAuthType() {
        return this.resAuthType;
    }
    
    public void setResAuthType(ResAuthType resAuthType) {
        this.resAuthType = resAuthType;
    }
    
    @Column(name="APPROVE_NODE_CD", length=20)
    public String getApproveNodeCd() {
        return this.approveNodeCd;
    }
    
    public void setApproveNodeCd(String approveNodeCd) {
        this.approveNodeCd = approveNodeCd;
    }
    
    @Column(name="APPROVE_USER_CD", length=20)
    public String getApproveUserCd() {
        return this.approveUserCd;
    }
    
    public void setApproveUserCd(String approveUserCd) {
        this.approveUserCd = approveUserCd;
    }
    
    @Column(name="EXTRA_NODE_CD", length=20)
    public String getExtraNodeCd() {
        return this.extraNodeCd;
    }
    
    public void setExtraNodeCd(String extraNodeCd) {
        this.extraNodeCd = extraNodeCd;
    }
    
    @Column(name="EXTRA_USER_CD", length=20)
    public String getExtraUserCd() {
        return this.extraUserCd;
    }
    
    public void setExtraUserCd(String extraUserCd) {
        this.extraUserCd = extraUserCd;
    }
    
    @Column(name="LEVEL_OFFSET", precision=10, scale=0)
    public Long getLevelOffset() {
        return this.levelOffset;
    }
    
    public void setLevelOffset(Long levelOffset) {
        this.levelOffset = levelOffset;
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

    @Column(name="OTHER_CONDTION", length=20)
	public String getOtherCondtion() {
		return otherCondtion;
	}


	public void setOtherCondtion(String otherCondtion) {
		this.otherCondtion = otherCondtion;
	}
    
    @Column(name="ACTIVE", precision=1, scale=0)
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }



}


