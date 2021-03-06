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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

/**
 * PlasOrg generated by hbm2java
 */
@Entity
@Table(name="PLAS_ORG"
    
)
public class PlasOrg  implements java.io.Serializable {


     private String plasOrgId;
     private String orgCd;
     private String orgBizCd;
     private String orgName;
     private String shortOrgName;
     private String phoneDesc;
     private String faxDesc;
     private String orgMgrId;
     private Boolean activeBl;
     private String orgTypeCd;
     private Long sequenceNo;
     private String creator;
     private String createdCenterCd;
     private String createdPositionCd;
     private Date createdDate;
     private String updator;
     private String updatedCenterCd;
     private String updatedPositionCd;
     private Date updatedDate;
     private long recordVersion;
     private String remark;
     private String createdDeptCd;
     private String updatedDeptCd;
     private String nodeLevelCd;
     private String orgKindCd;
     private String orgMgrSysPosId;

     private Boolean visableFlg;
     
     private List<PlasSysPosition> plasSysPositions = new ArrayList<PlasSysPosition>();
     private List<PlasUser> plasUsers = new ArrayList<PlasUser>();
     private List<PlasOrgMgrRel> plasOrgMgrRels = new ArrayList<PlasOrgMgrRel>();
     private List<PlasDimeOrgRel> plasDimeOrgRels = new ArrayList<PlasDimeOrgRel>();

    public PlasOrg() {
    }

	
    public PlasOrg(String plasOrgId, String orgCd, String orgName, long recordVersion) {
        this.plasOrgId = plasOrgId;
        this.orgCd = orgCd;
        this.orgName = orgName;
        this.recordVersion = recordVersion;
    }
    public PlasOrg(String plasOrgId, String orgCd, String orgBizCd, String orgName, String shortOrgName, String phoneDesc, String faxDesc, String orgMgrId, Boolean activeBl, String orgTypeCd, Long sequenceNo, String creator, String createdCenterCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedPositionCd, Date updatedDate, long recordVersion, String remark, String createdDeptCd, String updatedDeptCd, String nodeLevelCd, String orgKindCd, String orgMgrSysPosId, Boolean visableFlg, List<PlasSysPosition> plasSysPositions, List<PlasUser> plasUsers, List<PlasOrgMgrRel> plasOrgMgrRels, List<PlasDimeOrgRel> plasDimeOrgRels) {
       this.plasOrgId = plasOrgId;
       this.orgCd = orgCd;
       this.orgBizCd = orgBizCd;
       this.orgName = orgName;
       this.shortOrgName = shortOrgName;
       this.phoneDesc = phoneDesc;
       this.faxDesc = faxDesc;
       this.orgMgrId = orgMgrId;
       this.activeBl = activeBl;
       this.orgTypeCd = orgTypeCd;
       this.sequenceNo = sequenceNo;
       this.creator = creator;
       this.createdCenterCd = createdCenterCd;
       this.createdPositionCd = createdPositionCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedCenterCd = updatedCenterCd;
       this.updatedPositionCd = updatedPositionCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
       this.remark = remark;
       this.createdDeptCd = createdDeptCd;
       this.updatedDeptCd = updatedDeptCd;
       this.nodeLevelCd = nodeLevelCd;
       this.orgKindCd = orgKindCd;
       this.orgMgrSysPosId = orgMgrSysPosId;
       this.visableFlg = visableFlg;
       this.plasSysPositions = plasSysPositions;
       this.plasUsers = plasUsers;
       this.plasOrgMgrRels = plasOrgMgrRels;
       this.plasDimeOrgRels = plasDimeOrgRels;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="PLAS_ORG_ID", unique=true, nullable=false, length=50)
    public String getPlasOrgId() {
        return this.plasOrgId;
    }
    
    public void setPlasOrgId(String plasOrgId) {
        this.plasOrgId = plasOrgId;
    }
    
    @Column(name="ORG_CD", nullable=false, length=20)
    public String getOrgCd() {
        return this.orgCd;
    }
    
    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }
    
    @Column(name="ORG_BIZ_CD", length=20)
    public String getOrgBizCd() {
        return this.orgBizCd;
    }
    
    public void setOrgBizCd(String orgBizCd) {
        this.orgBizCd = orgBizCd;
    }
    
    @Column(name="ORG_NAME", nullable=false, length=50)
    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    @Column(name="SHORT_ORG_NAME", length=50)
    public String getShortOrgName() {
        return this.shortOrgName;
    }
    
    public void setShortOrgName(String shortOrgName) {
        this.shortOrgName = shortOrgName;
    }
    
    @Column(name="PHONE_DESC", length=100)
    public String getPhoneDesc() {
        return this.phoneDesc;
    }
    
    public void setPhoneDesc(String phoneDesc) {
        this.phoneDesc = phoneDesc;
    }
    
    @Column(name="FAX_DESC", length=100)
    public String getFaxDesc() {
        return this.faxDesc;
    }
    
    public void setFaxDesc(String faxDesc) {
        this.faxDesc = faxDesc;
    }
    
    @Column(name="ORG_MGR_ID", length=50)
    public String getOrgMgrId() {
        return this.orgMgrId;
    }
    
    public void setOrgMgrId(String orgMgrId) {
        this.orgMgrId = orgMgrId;
    }
    
    @Column(name="ACTIVE_BL", precision=1, scale=0)
    public Boolean getActiveBl() {
        return this.activeBl;
    }
    
    public void setActiveBl(Boolean activeBl) {
        this.activeBl = activeBl;
    }
    
    @Column(name="ORG_TYPE_CD", length=20)
    public String getOrgTypeCd() {
        return this.orgTypeCd;
    }
    
    public void setOrgTypeCd(String orgTypeCd) {
        this.orgTypeCd = orgTypeCd;
    }
    
    @Column(name="SEQUENCE_NO", precision=10, scale=0)
    public Long getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
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
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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
    
    @Column(name="NODE_LEVEL_CD", length=20)
    public String getNodeLevelCd() {
        return this.nodeLevelCd;
    }
    
    public void setNodeLevelCd(String nodeLevelCd) {
        this.nodeLevelCd = nodeLevelCd;
    }
    
    @Column(name="ORG_KIND_CD", length=20)
    public String getOrgKindCd() {
        return this.orgKindCd;
    }
    
    public void setOrgKindCd(String orgKindCd) {
        this.orgKindCd = orgKindCd;
    }

    @Column(name="ORG_MGR_SYS_POS_ID", length=50)
    public String getOrgMgrSysPosId() {
        return this.orgMgrSysPosId;
    }
    
    public void setOrgMgrSysPosId(String orgMgrSysPosId) {
        this.orgMgrSysPosId = orgMgrSysPosId;
    }
    
    @Column(name="VISABLE_FLG", precision=1, scale=0)
    public Boolean getVisableFlg() {
        return this.visableFlg;
    }
    
    public void setVisableFlg(Boolean visableFlg) {
        this.visableFlg = visableFlg;
    }
    
    
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasOrg")
    @BatchSize(size=10)
public List<PlasSysPosition> getPlasSysPositions() {
        return this.plasSysPositions;
    }
    
    public void setPlasSysPositions(List<PlasSysPosition> plasSysPositions) {
        this.plasSysPositions = plasSysPositions;
    }
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasOrg")
    @BatchSize(size=10)
public List<PlasUser> getPlasUsers() {
        return this.plasUsers;
    }
    
    public void setPlasUsers(List<PlasUser> plasUsers) {
        this.plasUsers = plasUsers;
    }
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasOrg")
    @BatchSize(size=10)
public List<PlasOrgMgrRel> getPlasOrgMgrRels() {
        return this.plasOrgMgrRels;
    }
    
    public void setPlasOrgMgrRels(List<PlasOrgMgrRel> plasOrgMgrRels) {
        this.plasOrgMgrRels = plasOrgMgrRels;
    }
@OneToMany(fetch=FetchType.LAZY, mappedBy="plasOrg")
    @BatchSize(size=10)
public List<PlasDimeOrgRel> getPlasDimeOrgRels() {
        return this.plasDimeOrgRels;
    }
    
    public void setPlasDimeOrgRels(List<PlasDimeOrgRel> plasDimeOrgRels) {
        this.plasDimeOrgRels = plasDimeOrgRels;
    }




}


