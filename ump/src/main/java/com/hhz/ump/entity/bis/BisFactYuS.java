// 
package com.hhz.ump.entity.bis;
// Generated 2011-11-4 16:55:53 by Hibernate Tools 3.2.2.GA


import com.hhz.ump.util.CodeNameUtil;
import com.mchange.v2.codegen.CodegenUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BisFactYuS generated by hbm2java
 */
@Entity
@Table(name = "BIS_FACT_YU_S"
        
)
public class BisFactYuS implements java.io.Serializable {


    private String bisFactId;
    private String bisStoreId;
    private String bisProjectId;
    private String bisContId;
    private Date factDate;
    private BigDecimal money;
    private String checkUserCd;
    private Date checkDate;
    private String statusCd;
    private Long sequenceNo;
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
    private String bisTenantId;
    private BigDecimal surplusMoney;

    public BisFactYuS() {
    }


    public BisFactYuS(String bisFactId, long recordVersion) {
        this.bisFactId = bisFactId;
        this.recordVersion = recordVersion;
    }

    public BisFactYuS(String bisFactId, String bisStoreId, String bisProjectId, String bisContId, Date factDate, BigDecimal money, String checkUserCd, Date checkDate, String statusCd, Long sequenceNo, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion, String bisTenantId) {
        this.bisFactId = bisFactId;
        this.bisStoreId = bisStoreId;
        this.bisProjectId = bisProjectId;
        this.bisContId = bisContId;
        this.factDate = factDate;
        this.money = money;
        this.checkUserCd = checkUserCd;
        this.checkDate = checkDate;
        this.statusCd = statusCd;
        this.sequenceNo = sequenceNo;
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
        this.bisTenantId = bisTenantId;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")

    @Column(name = "BIS_FACT_ID", unique = true, nullable = false, length = 50)
    public String getBisFactId() {
        return this.bisFactId;
    }

    public void setBisFactId(String bisFactId) {
        this.bisFactId = bisFactId;
    }

    private BisCont bisCont;

    /*BIS_CONT_ID*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIS_STORE_ID")
    public BisCont getBisCont() {
        return this.bisCont;
    }

    public void setBisCont(BisCont bisCont) {
        this.bisCont = bisCont;
    }

    /*@Column(name = "BIS_STORE_ID", length = 300)
    public String getBisStoreId() {
        return this.bisStoreId;
    }
    public void setBisStoreId(String bisStoreId) {
        this.bisStoreId = bisStoreId;
    }*/

    @Column(name = "BIS_PROJECT_ID", length = 50)
    public String getBisProjectId() {
        return this.bisProjectId;
    }

    public void setBisProjectId(String bisProjectId) {
        this.bisProjectId = bisProjectId;
    }

    @Column(name = "BIS_CONT_ID", length = 50)
    public String getBisContId() {
        return this.bisContId;
    }

    public void setBisContId(String bisContId) {
        this.bisContId = bisContId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FACT_DATE", length = 7)
    public Date getFactDate() {
        return this.factDate;
    }

    public void setFactDate(Date factDate) {
        this.factDate = factDate;
    }

    @Column(name = "SURPLUS_MONEY", precision = 10)
    public BigDecimal getSurplusMoney() {
        return this.surplusMoney;
    }

    public void setSurplusMoney(BigDecimal surplusMoney) {
        this.surplusMoney = surplusMoney;
    }

    @Column(name = "MONEY", precision = 10)
    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Column(name = "CHECK_USER_CD", length = 20)
    public String getCheckUserCd() {
        return this.checkUserCd;
    }

    public void setCheckUserCd(String checkUserCd) {
        this.checkUserCd = checkUserCd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECK_DATE", length = 7)
    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    @Column(name = "STATUS_CD", length = 20)
    public String getStatusCd() {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    @Column(name = "SEQUENCE_NO", precision = 10, scale = 0)
    public Long getSequenceNo() {
        return this.sequenceNo;
    }

    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    @Column(name = "REMARK", length = 200)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "CREATOR", length = 20)
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "CREATED_CENTER_CD", length = 20)
    public String getCreatedCenterCd() {
        return this.createdCenterCd;
    }

    public void setCreatedCenterCd(String createdCenterCd) {
        this.createdCenterCd = createdCenterCd;
    }

    @Column(name = "CREATED_DEPT_CD", length = 20)
    public String getCreatedDeptCd() {
        return this.createdDeptCd;
    }

    public void setCreatedDeptCd(String createdDeptCd) {
        this.createdDeptCd = createdDeptCd;
    }

    @Column(name = "CREATED_POSITION_CD", length = 20)
    public String getCreatedPositionCd() {
        return this.createdPositionCd;
    }

    public void setCreatedPositionCd(String createdPositionCd) {
        this.createdPositionCd = createdPositionCd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", length = 7)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "UPDATOR", length = 20)
    public String getUpdator() {
        return this.updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    @Column(name = "UPDATED_CENTER_CD", length = 20)
    public String getUpdatedCenterCd() {
        return this.updatedCenterCd;
    }

    public void setUpdatedCenterCd(String updatedCenterCd) {
        this.updatedCenterCd = updatedCenterCd;
    }

    @Column(name = "UPDATED_DEPT_CD", length = 20)
    public String getUpdatedDeptCd() {
        return this.updatedDeptCd;
    }

    public void setUpdatedDeptCd(String updatedDeptCd) {
        this.updatedDeptCd = updatedDeptCd;
    }

    @Column(name = "UPDATED_POSITION_CD", length = 20)
    public String getUpdatedPositionCd() {
        return this.updatedPositionCd;
    }

    public void setUpdatedPositionCd(String updatedPositionCd) {
        this.updatedPositionCd = updatedPositionCd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", length = 7)
    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "RECORD_VERSION", nullable = false, precision = 10, scale = 0)
    @Version
    public long getRecordVersion() {
        return this.recordVersion;
    }

    public void setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
    }

    @Column(name = "BIS_TENANT_ID", length = 50)
    public String getBisTenantId() {
        return this.bisTenantId;
    }

    public void setBisTenantId(String bisTenantId) {
        this.bisTenantId = bisTenantId;
    }


}

