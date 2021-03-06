// 
package com.hhz.ump.entity.oa;
// Generated 2010-8-11 13:25:01 by Hibernate Tools 3.2.4.GA


import java.sql.Clob;
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
 * OaEmailGroup generated by hbm2java
 */
@Entity
@Table(name="OA_EMAIL_GROUP"
    
)
public class OaEmailGroup  implements java.io.Serializable {


     private String oaEmailGroupId;
     private String groupName;
     private Clob groupMemberNames;
     private Clob groupMemberIds;
     private long dispOrderNo;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;

    public OaEmailGroup() {
    }

	
    public OaEmailGroup(String oaEmailGroupId, long recordVersion) {
        this.oaEmailGroupId = oaEmailGroupId;
        this.recordVersion = recordVersion;
    }
    public OaEmailGroup(String oaEmailGroupId, String groupName, Clob groupMemberNames, Clob groupMemberIds, long dispOrderNo, String remark, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion) {
       this.oaEmailGroupId = oaEmailGroupId;
       this.groupName = groupName;
       this.groupMemberNames = groupMemberNames;
       this.groupMemberIds = groupMemberIds;
       this.dispOrderNo = dispOrderNo;
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

    
    @Column(name="OA_EMAIL_GROUP_ID", unique=true, nullable=false, length=50)
    public String getOaEmailGroupId() {
        return this.oaEmailGroupId;
    }
    
    public void setOaEmailGroupId(String oaEmailGroupId) {
        this.oaEmailGroupId = oaEmailGroupId;
    }

    
    @Column(name="GROUP_NAME", length=50)
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    
    @Column(name="GROUP_MEMBER_NAMES")
    public Clob getGroupMemberNames() {
        return this.groupMemberNames;
    }
    
    public void setGroupMemberNames(Clob groupMemberNames) {
        this.groupMemberNames = groupMemberNames;
    }

    
    @Column(name="GROUP_MEMBER_IDS")
    public Clob getGroupMemberIds() {
        return this.groupMemberIds;
    }
    
    public void setGroupMemberIds(Clob groupMemberIds) {
        this.groupMemberIds = groupMemberIds;
    }
    
    @Column(name="DISP_ORDER_NO", nullable=false, precision=10, scale=0)
    @Version 
    public long getDispOrderNo() {
        return this.dispOrderNo;
    }
    
    public void setDispOrderNo(long dispOrderNo) {
        this.dispOrderNo = dispOrderNo;
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


