// 
package com.hhz.ump.entity.plan;
// Generated 2011-2-16 16:39:45 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
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
 * PlanWork2Year generated by hbm2java
 */
@Entity
@Table(name="PLAN_WORK2_YEAR"
    
)
public class PlanWork2Year  implements java.io.Serializable {


     private String planWork2YearId;
     private BigDecimal planYear;
     private String serialNumber;
     private BigDecimal serialOrder;
     private String centerCd;
     private String accordingType1;
     private String accordingType2;
     private String accordingType3;
     private String accordingType4;
     private String workTarget;
     private String detailStep;
     private Date targetDate;
     private Date endDate;
     private String statusCd;
     private String coordinateCenterCds;
     private String coordinateCenterNames;
     private Long weightPoint1;
     private Long weightPoint2;
     private Long weightPoint3;
     private Long weightPoint4;
     private Long weightPoint5;
     private Long weightPoint6;
     private Long weightPoint7;
     private Long weightPoint8;
     private Long weightPoint9;
     private Long weightPoint10;
     private Long weightPoint11;
     private Long weightPoint12;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private String remark;
     private long recordVersion;

    public PlanWork2Year() {
    }

	
    public PlanWork2Year(String planWork2YearId, BigDecimal planYear, long recordVersion) {
        this.planWork2YearId = planWork2YearId;
        this.planYear = planYear;
        this.recordVersion = recordVersion;
    }
    public PlanWork2Year(String planWork2YearId, BigDecimal planYear, String serialNumber, BigDecimal serialOrder, String centerCd, String accordingType1, String accordingType2, String accordingType3, String accordingType4, String workTarget, String detailStep, Date targetDate, Date endDate, String statusCd, String coordinateCenterCds, String coordinateCenterNames, Long weightPoint1, Long weightPoint2, Long weightPoint3, Long weightPoint4, Long weightPoint5, Long weightPoint6, Long weightPoint7, Long weightPoint8, Long weightPoint9, Long weightPoint10, Long weightPoint11, Long weightPoint12, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, String remark, long recordVersion) {
       this.planWork2YearId = planWork2YearId;
       this.planYear = planYear;
       this.serialNumber = serialNumber;
       this.serialOrder = serialOrder;
       this.centerCd = centerCd;
       this.accordingType1 = accordingType1;
       this.accordingType2 = accordingType2;
       this.accordingType3 = accordingType3;
       this.accordingType4 = accordingType4;
       this.workTarget = workTarget;
       this.detailStep = detailStep;
       this.targetDate = targetDate;
       this.endDate = endDate;
       this.statusCd = statusCd;
       this.coordinateCenterCds = coordinateCenterCds;
       this.coordinateCenterNames = coordinateCenterNames;
       this.weightPoint1 = weightPoint1;
       this.weightPoint2 = weightPoint2;
       this.weightPoint3 = weightPoint3;
       this.weightPoint4 = weightPoint4;
       this.weightPoint5 = weightPoint5;
       this.weightPoint6 = weightPoint6;
       this.weightPoint7 = weightPoint7;
       this.weightPoint8 = weightPoint8;
       this.weightPoint9 = weightPoint9;
       this.weightPoint10 = weightPoint10;
       this.weightPoint11 = weightPoint11;
       this.weightPoint12 = weightPoint12;
       this.creator = creator;
       this.createdDeptCd = createdDeptCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedDate = updatedDate;
       this.remark = remark;
       this.recordVersion = recordVersion;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="PLAN_WORK2_YEAR_ID", unique=true, nullable=false, length=50)
    public String getPlanWork2YearId() {
        return this.planWork2YearId;
    }
    
    public void setPlanWork2YearId(String planWork2YearId) {
        this.planWork2YearId = planWork2YearId;
    }
    
    @Column(name="PLAN_YEAR", nullable=false, precision=38, scale=0)
    public BigDecimal getPlanYear() {
        return this.planYear;
    }
    
    public void setPlanYear(BigDecimal planYear) {
        this.planYear = planYear;
    }
    
    @Column(name="SERIAL_NUMBER", length=50)
    public String getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    @Column(name="SERIAL_ORDER", precision=38, scale=0)
    public BigDecimal getSerialOrder() {
        return this.serialOrder;
    }
    
    public void setSerialOrder(BigDecimal serialOrder) {
        this.serialOrder = serialOrder;
    }
    
    @Column(name="CENTER_CD", length=50)
    public String getCenterCd() {
        return this.centerCd;
    }
    
    public void setCenterCd(String centerCd) {
        this.centerCd = centerCd;
    }

    @Column(name="ACCORDING_TYPE1", length=50)
    public String getAccordingType1() {
        return this.accordingType1;
    }
    
    public void setAccordingType1(String accordingType1) {
        this.accordingType1 = accordingType1;
    }

    @Column(name="ACCORDING_TYPE2", length=50)
    public String getAccordingType2() {
        return this.accordingType2;
    }
    
    public void setAccordingType2(String accordingType2) {
        this.accordingType2 = accordingType2;
    }

    @Column(name="ACCORDING_TYPE3", length=50)
    public String getAccordingType3() {
        return this.accordingType3;
    }
    
    public void setAccordingType3(String accordingType3) {
        this.accordingType3 = accordingType3;
    }

    @Column(name="ACCORDING_TYPE4", length=50)
    public String getAccordingType4() {
        return this.accordingType4;
    }
    
    public void setAccordingType4(String accordingType4) {
        this.accordingType4 = accordingType4;
    }
    
    @Column(name="WORK_TARGET", length=2000)
    public String getWorkTarget() {
        return this.workTarget;
    }
    
    public void setWorkTarget(String workTarget) {
        this.workTarget = workTarget;
    }
    
    @Column(name="DETAIL_STEP", length=2000)
    public String getDetailStep() {
        return this.detailStep;
    }
    
    public void setDetailStep(String detailStep) {
        this.detailStep = detailStep;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TARGET_DATE", length=7)
    public Date getTargetDate() {
        return this.targetDate;
    }
    
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_DATE", length=7)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Column(name="STATUS_CD", length=20)
    public String getStatusCd() {
        return this.statusCd;
    }
    
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }
    
    @Column(name="COORDINATE_CENTER_CDS", length=1000)
    public String getCoordinateCenterCds() {
        return this.coordinateCenterCds;
    }
    
    public void setCoordinateCenterCds(String coordinateCenterCds) {
        this.coordinateCenterCds = coordinateCenterCds;
    }
    
    @Column(name="COORDINATE_CENTER_NAMES", length=1000)
    public String getCoordinateCenterNames() {
        return this.coordinateCenterNames;
    }
    
    public void setCoordinateCenterNames(String coordinateCenterNames) {
        this.coordinateCenterNames = coordinateCenterNames;
    }
    
    @Column(name="WEIGHT_POINT1", precision=10, scale=0)
    public Long getWeightPoint1() {
        return this.weightPoint1;
    }
    
    public void setWeightPoint1(Long weightPoint1) {
        this.weightPoint1 = weightPoint1;
    }
    
    @Column(name="WEIGHT_POINT2", precision=10, scale=0)
    public Long getWeightPoint2() {
        return this.weightPoint2;
    }
    
    public void setWeightPoint2(Long weightPoint2) {
        this.weightPoint2 = weightPoint2;
    }
    
    @Column(name="WEIGHT_POINT3", precision=10, scale=0)
    public Long getWeightPoint3() {
        return this.weightPoint3;
    }
    
    public void setWeightPoint3(Long weightPoint3) {
        this.weightPoint3 = weightPoint3;
    }
    
    @Column(name="WEIGHT_POINT4", precision=10, scale=0)
    public Long getWeightPoint4() {
        return this.weightPoint4;
    }
    
    public void setWeightPoint4(Long weightPoint4) {
        this.weightPoint4 = weightPoint4;
    }
    
    @Column(name="WEIGHT_POINT5", precision=10, scale=0)
    public Long getWeightPoint5() {
        return this.weightPoint5;
    }
    
    public void setWeightPoint5(Long weightPoint5) {
        this.weightPoint5 = weightPoint5;
    }
    
    @Column(name="WEIGHT_POINT6", precision=10, scale=0)
    public Long getWeightPoint6() {
        return this.weightPoint6;
    }
    
    public void setWeightPoint6(Long weightPoint6) {
        this.weightPoint6 = weightPoint6;
    }
    
    @Column(name="WEIGHT_POINT7", precision=10, scale=0)
    public Long getWeightPoint7() {
        return this.weightPoint7;
    }
    
    public void setWeightPoint7(Long weightPoint7) {
        this.weightPoint7 = weightPoint7;
    }
    
    @Column(name="WEIGHT_POINT8", precision=10, scale=0)
    public Long getWeightPoint8() {
        return this.weightPoint8;
    }
    
    public void setWeightPoint8(Long weightPoint8) {
        this.weightPoint8 = weightPoint8;
    }
    
    @Column(name="WEIGHT_POINT9", precision=10, scale=0)
    public Long getWeightPoint9() {
        return this.weightPoint9;
    }
    
    public void setWeightPoint9(Long weightPoint9) {
        this.weightPoint9 = weightPoint9;
    }
    
    @Column(name="WEIGHT_POINT10", precision=10, scale=0)
    public Long getWeightPoint10() {
        return this.weightPoint10;
    }
    
    public void setWeightPoint10(Long weightPoint10) {
        this.weightPoint10 = weightPoint10;
    }
    
    @Column(name="WEIGHT_POINT11", precision=10, scale=0)
    public Long getWeightPoint11() {
        return this.weightPoint11;
    }
    
    public void setWeightPoint11(Long weightPoint11) {
        this.weightPoint11 = weightPoint11;
    }
    
    @Column(name="WEIGHT_POINT12", precision=10, scale=0)
    public Long getWeightPoint12() {
        return this.weightPoint12;
    }
    
    public void setWeightPoint12(Long weightPoint12) {
        this.weightPoint12 = weightPoint12;
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
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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


