// 
package com.hhz.ump.entity.oa;
// Generated 2011-1-7 10:36:45 by Hibernate Tools 3.2.4.GA


import java.sql.Clob;
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
 * OaPublicAffairs generated by hbm2java
 */
@Entity
@Table(name="OA_PUBLIC_AFFAIRS"
    
)
public class OaPublicAffairs  implements java.io.Serializable {


     private String oaPublicAffairsId;
     private String subject;
     private Clob content;
     private String provideUserCds;
     private Date newsTime;
     private Long clickCount;
     private Clob readers;
     private String uploadFlg;
     private String typeCd;
     private String enabledFlg;
     private String deleteFlg;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;
     private List<OaPubAffaComment> oaPubAffaComments = new ArrayList<OaPubAffaComment>();

    public OaPublicAffairs() {
    }

	
    public OaPublicAffairs(String oaPublicAffairsId, long recordVersion) {
        this.oaPublicAffairsId = oaPublicAffairsId;
        this.recordVersion = recordVersion;
    }
    public OaPublicAffairs(String oaPublicAffairsId, String subject, Clob content, String provideUserCds, Date newsTime, Long clickCount, Clob readers, String uploadFlg, String typeCd, String enabledFlg,String deleteFlg, String remark, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion, List<OaPubAffaComment> oaPubAffaComments) {
       this.oaPublicAffairsId = oaPublicAffairsId;
       this.subject = subject;
       this.content = content;
       this.provideUserCds = provideUserCds;
       this.newsTime = newsTime;
       this.clickCount = clickCount;
       this.readers = readers;
       this.uploadFlg = uploadFlg;
       this.typeCd = typeCd;
       this.enabledFlg = enabledFlg;
       this.deleteFlg = deleteFlg;
       this.remark = remark;
       this.creator = creator;
       this.createdDeptCd = createdDeptCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
       this.oaPubAffaComments = oaPubAffaComments;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 

    
    @Column(name="OA_PUBLIC_AFFAIRS_ID", unique=true, nullable=false, length=50)
    public String getOaPublicAffairsId() {
        return this.oaPublicAffairsId;
    }
    
    public void setOaPublicAffairsId(String oaPublicAffairsId) {
        this.oaPublicAffairsId = oaPublicAffairsId;
    }

    
    @Column(name="SUBJECT", length=500)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    @Column(name="CONTENT")
    public Clob getContent() {
        return this.content;
    }
    
    public void setContent(Clob content) {
        this.content = content;
    }

    
    @Column(name="PROVIDE_USER_CDS", length=200)
    public String getProvideUserCds() {
        return this.provideUserCds;
    }
    
    public void setProvideUserCds(String provideUserCds) {
        this.provideUserCds = provideUserCds;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NEWS_TIME", length=7)
    public Date getNewsTime() {
        return this.newsTime;
    }
    
    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    
    @Column(name="CLICK_COUNT", precision=10, scale=0)
    public Long getClickCount() {
        return this.clickCount;
    }
    
    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    
    @Column(name="READERS")
    public Clob getReaders() {
        return this.readers;
    }
    
    public void setReaders(Clob readers) {
        this.readers = readers;
    }

    
    @Column(name="UPLOAD_FLG", length=1)
    public String getUploadFlg() {
        return this.uploadFlg;
    }
    
    public void setUploadFlg(String uploadFlg) {
        this.uploadFlg = uploadFlg;
    }

    
    @Column(name="TYPE_CD", length=20)
    public String getTypeCd() {
        return this.typeCd;
    }
    
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    
    @Column(name="ENABLED_FLG", length=1)
    public String getEnabledFlg() {
        return this.enabledFlg;
    }
    
    public void setEnabledFlg(String enabledFlg) {
        this.enabledFlg = enabledFlg;
    }
    
    @Column(name="DELETE_FLG", length=1)
    public String getDeleteFlg() {
    	return this.deleteFlg;
    }
    
    public void setDeleteFlg(String deleteFlg) {
    	this.deleteFlg = deleteFlg;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="oaPublicAffairs")
    @BatchSize(size=10)
public List<OaPubAffaComment> getOaPubAffaComments() {
        return this.oaPubAffaComments;
    }
    
    public void setOaPubAffaComments(List<OaPubAffaComment> oaPubAffaComments) {
        this.oaPubAffaComments = oaPubAffaComments;
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


