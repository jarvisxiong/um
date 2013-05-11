// 
package com.hhz.ump.entity.app;
// Generated 2010-8-4 13:27:58 by Hibernate Tools 3.2.4.GA


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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * AppDictData generated by hbm2java
 */
@Entity
@Table(name="APP_DICT_DATA"
    
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppDictData  implements java.io.Serializable {


     private String appDictDataId;
     private AppDictType appDictType;
     private String dictCd;
     private String dictName;
     private BigDecimal dispOrderNo;
     private String defaultFlg;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;

    public AppDictData() {
    }

	
    public AppDictData(String appDictDataId, AppDictType appDictType, long recordVersion) {
        this.appDictDataId = appDictDataId;
        this.appDictType = appDictType;
        this.recordVersion = recordVersion;
    }
    public AppDictData(String appDictDataId, AppDictType appDictType, String dictCd, String dictName, BigDecimal dispOrderNo, String defaultFlg, String remark, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion) {
       this.appDictDataId = appDictDataId;
       this.appDictType = appDictType;
       this.dictCd = dictCd;
       this.dictName = dictName;
       this.dispOrderNo = dispOrderNo;
       this.defaultFlg = defaultFlg;
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

    
    @Column(name="APP_DICT_DATA_ID", unique=true, nullable=false, length=50)
    public String getAppDictDataId() {
        return this.appDictDataId;
    }
    
    public void setAppDictDataId(String appDictDataId) {
        this.appDictDataId = appDictDataId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="APP_DICT_TYPE_ID", nullable=false)
    public AppDictType getAppDictType() {
        return this.appDictType;
    }
    
    public void setAppDictType(AppDictType appDictType) {
        this.appDictType = appDictType;
    }

    
    @Column(name="DICT_CD", length=50)
    public String getDictCd() {
        return this.dictCd;
    }
    
    public void setDictCd(String dictCd) {
        this.dictCd = dictCd;
    }

    
    @Column(name="DICT_NAME", length=50)
    public String getDictName() {
        return this.dictName;
    }
    
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    
    @Column(name="DISP_ORDER_NO", precision=38, scale=0)
    public BigDecimal getDispOrderNo() {
        return this.dispOrderNo;
    }
    
    public void setDispOrderNo(BigDecimal dispOrderNo) {
        this.dispOrderNo = dispOrderNo;
    }

    
    @Column(name="DEFAULT_FLG", length=1)
    public String getDefaultFlg() {
        return this.defaultFlg;
    }
    
    public void setDefaultFlg(String defaultFlg) {
        this.defaultFlg = defaultFlg;
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

