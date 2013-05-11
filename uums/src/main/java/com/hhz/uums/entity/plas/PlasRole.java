// 
package com.hhz.uums.entity.plas;
// Generated 2012-2-1 11:15:13 by Hibernate Tools 3.2.2.GA


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

/**
 * PlasRole generated by hbm2java
 */
@Entity
@Table(name="PLAS_ROLE"
    
)
public class PlasRole  implements java.io.Serializable {


     private String plasRoleId;
     private PlasApp plasApp;
     private PlasRoleGroup plasRoleGroup;
     private String roleCd;
     private String roleBizCd;
     private String roleName;
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
     private List<PlasRolePackRel> plasRolePackRels = new ArrayList<PlasRolePackRel>();
     private List<PlasSysPosRoleRel> plasSysPosRoleRels = new ArrayList<PlasSysPosRoleRel>();

    public PlasRole() {
    }

	
    public PlasRole(String plasRoleId, PlasApp plasApp, PlasRoleGroup plasRoleGroup, long recordVersion) {
        this.plasRoleId = plasRoleId;
        this.plasApp = plasApp;
        this.plasRoleGroup = plasRoleGroup;
        this.recordVersion = recordVersion;
    }
    public PlasRole(String plasRoleId, PlasApp plasApp, PlasRoleGroup plasRoleGroup, String roleCd, String roleBizCd, String roleName, Long sequenceNo, String remark, String creator, String createdCenterCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedPositionCd, Date updatedDate, long recordVersion, String createdDeptCd, String updatedDeptCd, List<PlasRolePackRel> plasRolePackRels, List<PlasSysPosRoleRel> plasSysPosRoleRels) {
       this.plasRoleId = plasRoleId;
       this.plasApp = plasApp;
       this.plasRoleGroup = plasRoleGroup;
       this.roleCd = roleCd;
       this.roleBizCd = roleBizCd;
       this.roleName = roleName;
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
       this.plasRolePackRels = plasRolePackRels;
       this.plasSysPosRoleRels = plasSysPosRoleRels;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="PLAS_ROLE_ID", unique=true, nullable=false, length=50)
    public String getPlasRoleId() {
        return this.plasRoleId;
    }
    
    public void setPlasRoleId(String plasRoleId) {
        this.plasRoleId = plasRoleId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PLAS_APP_ID", nullable=false)
    public PlasApp getPlasApp() {
        return this.plasApp;
    }
    
    public void setPlasApp(PlasApp plasApp) {
        this.plasApp = plasApp;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PLAS_ROLE_GROUP_ID", nullable=false)
    public PlasRoleGroup getPlasRoleGroup() {
        return this.plasRoleGroup;
    }
    
    public void setPlasRoleGroup(PlasRoleGroup plasRoleGroup) {
        this.plasRoleGroup = plasRoleGroup;
    }
    
    @Column(name="ROLE_CD", length=20)
    public String getRoleCd() {
        return this.roleCd;
    }
    
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
    @Column(name="ROLE_BIZ_CD", length=20)
    public String getRoleBizCd() {
        return this.roleBizCd;
    }
    
    public void setRoleBizCd(String roleBizCd) {
        this.roleBizCd = roleBizCd;
    }
    
    @Column(name="ROLE_NAME", length=50)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasRole")
    @BatchSize(size=10)
public List<PlasRolePackRel> getPlasRolePackRels() {
        return this.plasRolePackRels;
    }
    
    public void setPlasRolePackRels(List<PlasRolePackRel> plasRolePackRels) {
        this.plasRolePackRels = plasRolePackRels;
    }
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasRole")
    @BatchSize(size=10)
public List<PlasSysPosRoleRel> getPlasSysPosRoleRels() {
        return this.plasSysPosRoleRels;
    }
    
    public void setPlasSysPosRoleRels(List<PlasSysPosRoleRel> plasSysPosRoleRels) {
        this.plasSysPosRoleRels = plasSysPosRoleRels;
    }




}

