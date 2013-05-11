// 
package com.hhz.ump.entity.jbpm;
// Generated 2010-8-4 11:22:38 by Hibernate Tools 3.2.4.GA


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
 * JbpmTaskCandidate generated by hbm2java
 */
@Entity
@Table(name="JBPM_TASK_CANDIDATE"
    
)
public class JbpmTaskCandidate  implements java.io.Serializable {


     private String jbpmTaskCandidateId;
     private JbpmTask jbpmTask;
     private String userCd;
     private String userName;
     private String statusCd;
     private String extFlg1;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;

    public JbpmTaskCandidate() {
    }

	
    public JbpmTaskCandidate(String jbpmTaskCandidateId, JbpmTask jbpmTask, long recordVersion) {
        this.jbpmTaskCandidateId = jbpmTaskCandidateId;
        this.jbpmTask = jbpmTask;
        this.recordVersion = recordVersion;
    }
    public JbpmTaskCandidate(String jbpmTaskCandidateId, JbpmTask jbpmTask, String userCd, String userName, String remark, String extFlg1, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion) {
       this.jbpmTaskCandidateId = jbpmTaskCandidateId;
       this.jbpmTask = jbpmTask;
       this.userCd = userCd;
       this.userName = userName;
       this.extFlg1 = extFlg1;
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

    
    @Column(name="JBPM_TASK_CANDIDATE_ID", unique=true, nullable=false, length=50)
    public String getJbpmTaskCandidateId() {
        return this.jbpmTaskCandidateId;
    }
    
    public void setJbpmTaskCandidateId(String jbpmTaskCandidateId) {
        this.jbpmTaskCandidateId = jbpmTaskCandidateId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JBPM_TASK_ID", nullable=false)
    public JbpmTask getJbpmTask() {
        return this.jbpmTask;
    }
    
    public void setJbpmTask(JbpmTask jbpmTask) {
        this.jbpmTask = jbpmTask;
    }

    
    @Column(name="USER_CD", length=200)
    public String getUserCd() {
        return this.userCd;
    }
    
    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    
    @Column(name="USER_NAME", length=50)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
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


    @Column(name="STATUS_CD", length=20)
    public String getStatusCd() {
        return this.statusCd;
    }
    
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }


    @Column(name="EXT_FLG1", length=20)
    public String getExtFlg1() {
        return this.extFlg1;
    }
    
    public void setExtFlg1(String extFlg1) {
        this.extFlg1 = extFlg1;
    }

}

