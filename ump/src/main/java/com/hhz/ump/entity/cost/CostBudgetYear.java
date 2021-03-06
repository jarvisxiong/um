// 
package com.hhz.ump.entity.cost;
// Generated 2012-2-20 20:44:07 by Hibernate Tools 3.2.2.GA


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

import org.hibernate.annotations.GenericGenerator;

/**
 * CostBudgetYear generated by hbm2java
 */
@Entity
@Table(name="COST_BUDGET_YEAR"
    
)
public class CostBudgetYear  implements java.io.Serializable {


     private String costBudgetYearId;
     private CostProjectSection costProjectSection;
     private Long budgetYear;
     private BigDecimal sectionTotalAmt;
     private BigDecimal targetCostAmt;
     private BigDecimal preYearPaiedAmt;
     private BigDecimal areaTotalAmt;
     private BigDecimal groupTotalAmt;
     private String suggestDesc;
     private String memoDesc;
     private BigDecimal planAmtM01;
     private BigDecimal planAmtM02;
     private BigDecimal planAmtM03;
     private BigDecimal planAmtM04;
     private BigDecimal planAmtM05;
     private BigDecimal planAmtM06;
     private BigDecimal planAmtM07;
     private BigDecimal planAmtM08;
     private BigDecimal planAmtM09;
     private BigDecimal planAmtM10;
     private BigDecimal planAmtM11;
     private BigDecimal planAmtM12;
     private String basesDescM01;
     private String basesDescM02;
     private String basesDescM03;
     private String basesDescM04;
     private String basesDescM05;
     private String basesDescM06;
     private String basesDescM07;
     private String basesDescM08;
     private String basesDescM09;
     private String basesDescM10;
     private String basesDescM11;
     private String basesDescM12;
     private String cfmPreYearAmtFlg;
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
     

    public CostBudgetYear() {
    }

	
    public CostBudgetYear(String costBudgetYearId, CostProjectSection costProjectSection, long recordVersion) {
        this.costBudgetYearId = costBudgetYearId;
        this.costProjectSection = costProjectSection;
        this.recordVersion = recordVersion;
    }
    public CostBudgetYear(String costBudgetYearId, CostProjectSection costProjectSection, Long budgetYear, BigDecimal sectionTotalAmt, BigDecimal targetCostAmt, BigDecimal preYearPaiedAmt, BigDecimal areaTotalAmt, BigDecimal groupTotalAmt, String suggestDesc, String memoDesc, BigDecimal planAmtM01, BigDecimal planAmtM02, BigDecimal planAmtM03, BigDecimal planAmtM04, BigDecimal planAmtM05, BigDecimal planAmtM06, BigDecimal planAmtM07, BigDecimal planAmtM08, BigDecimal planAmtM09, BigDecimal planAmtM10, BigDecimal planAmtM11, BigDecimal planAmtM12, String basesDescM01, String basesDescM02, String basesDescM03, String basesDescM04, String basesDescM05, String basesDescM06, String basesDescM07, String basesDescM08, String basesDescM09, String basesDescM10, String basesDescM11, String basesDescM12, String cfmPreYearAmtFlg, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion) {
       this.costBudgetYearId = costBudgetYearId;
       this.costProjectSection = costProjectSection;
       this.budgetYear = budgetYear;
       this.sectionTotalAmt = sectionTotalAmt;
       this.targetCostAmt = targetCostAmt;
       this.preYearPaiedAmt = preYearPaiedAmt;
       this.areaTotalAmt = areaTotalAmt;
       this.groupTotalAmt = groupTotalAmt;
       this.suggestDesc = suggestDesc;
       this.memoDesc = memoDesc;
       this.planAmtM01 = planAmtM01;
       this.planAmtM02 = planAmtM02;
       this.planAmtM03 = planAmtM03;
       this.planAmtM04 = planAmtM04;
       this.planAmtM05 = planAmtM05;
       this.planAmtM06 = planAmtM06;
       this.planAmtM07 = planAmtM07;
       this.planAmtM08 = planAmtM08;
       this.planAmtM09 = planAmtM09;
       this.planAmtM10 = planAmtM10;
       this.planAmtM11 = planAmtM11;
       this.planAmtM12 = planAmtM12;
       this.basesDescM01 = basesDescM01;
       this.basesDescM02 = basesDescM02;
       this.basesDescM03 = basesDescM03;
       this.basesDescM04 = basesDescM04;
       this.basesDescM05 = basesDescM05;
       this.basesDescM06 = basesDescM06;
       this.basesDescM07 = basesDescM07;
       this.basesDescM08 = basesDescM08;
       this.basesDescM09 = basesDescM09;
       this.basesDescM10 = basesDescM10;
       this.basesDescM11 = basesDescM11;
       this.basesDescM12 = basesDescM12;
       this.cfmPreYearAmtFlg = cfmPreYearAmtFlg;
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
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="COST_BUDGET_YEAR_ID", unique=true, nullable=false, length=50)
    public String getCostBudgetYearId() {
        return this.costBudgetYearId;
    }
    
    public void setCostBudgetYearId(String costBudgetYearId) {
        this.costBudgetYearId = costBudgetYearId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COST_PROJECT_SECTION_ID", nullable=false)
    public CostProjectSection getCostProjectSection() {
        return this.costProjectSection;
    }
    
    public void setCostProjectSection(CostProjectSection costProjectSection) {
        this.costProjectSection = costProjectSection;
    }
    
    @Column(name="BUDGET_YEAR", precision=10, scale=0)
    public Long getBudgetYear() {
        return this.budgetYear;
    }
    
    public void setBudgetYear(Long budgetYear) {
        this.budgetYear = budgetYear;
    }
    
    @Column(name="SECTION_TOTAL_AMT", precision=18)
    public BigDecimal getSectionTotalAmt() {
        return this.sectionTotalAmt;
    }
    
    public void setSectionTotalAmt(BigDecimal sectionTotalAmt) {
        this.sectionTotalAmt = sectionTotalAmt;
    }
    
    @Column(name="TARGET_COST_AMT", precision=18)
    public BigDecimal getTargetCostAmt() {
        return this.targetCostAmt;
    }
    
    public void setTargetCostAmt(BigDecimal targetCostAmt) {
        this.targetCostAmt = targetCostAmt;
    }
    
    @Column(name="PRE_YEAR_PAIED_AMT", precision=18)
    public BigDecimal getPreYearPaiedAmt() {
        return this.preYearPaiedAmt;
    }
    
    public void setPreYearPaiedAmt(BigDecimal preYearPaiedAmt) {
        this.preYearPaiedAmt = preYearPaiedAmt;
    }
    
    @Column(name="AREA_TOTAL_AMT", precision=18)
    public BigDecimal getAreaTotalAmt() {
        return this.areaTotalAmt;
    }
    
    public void setAreaTotalAmt(BigDecimal areaTotalAmt) {
        this.areaTotalAmt = areaTotalAmt;
    }
    
    @Column(name="GROUP_TOTAL_AMT", precision=18)
    public BigDecimal getGroupTotalAmt() {
        return this.groupTotalAmt;
    }
    
    public void setGroupTotalAmt(BigDecimal groupTotalAmt) {
        this.groupTotalAmt = groupTotalAmt;
    }
    
    @Column(name="SUGGEST_DESC", length=400)
    public String getSuggestDesc() {
        return this.suggestDesc;
    }
    
    public void setSuggestDesc(String suggestDesc) {
        this.suggestDesc = suggestDesc;
    }
    
    @Column(name="MEMO_DESC", length=400)
    public String getMemoDesc() {
        return this.memoDesc;
    }
    
    public void setMemoDesc(String memoDesc) {
        this.memoDesc = memoDesc;
    }
    
    @Column(name="PLAN_AMT_M01", precision=18)
    public BigDecimal getPlanAmtM01() {
        return this.planAmtM01;
    }
    
    public void setPlanAmtM01(BigDecimal planAmtM01) {
        this.planAmtM01 = planAmtM01;
    }
    
    @Column(name="PLAN_AMT_M02", precision=18)
    public BigDecimal getPlanAmtM02() {
        return this.planAmtM02;
    }
    
    public void setPlanAmtM02(BigDecimal planAmtM02) {
        this.planAmtM02 = planAmtM02;
    }
    
    @Column(name="PLAN_AMT_M03", precision=18)
    public BigDecimal getPlanAmtM03() {
        return this.planAmtM03;
    }
    
    public void setPlanAmtM03(BigDecimal planAmtM03) {
        this.planAmtM03 = planAmtM03;
    }
    
    @Column(name="PLAN_AMT_M04", precision=18)
    public BigDecimal getPlanAmtM04() {
        return this.planAmtM04;
    }
    
    public void setPlanAmtM04(BigDecimal planAmtM04) {
        this.planAmtM04 = planAmtM04;
    }
    
    @Column(name="PLAN_AMT_M05", precision=18)
    public BigDecimal getPlanAmtM05() {
        return this.planAmtM05;
    }
    
    public void setPlanAmtM05(BigDecimal planAmtM05) {
        this.planAmtM05 = planAmtM05;
    }
    
    @Column(name="PLAN_AMT_M06", precision=18)
    public BigDecimal getPlanAmtM06() {
        return this.planAmtM06;
    }
    
    public void setPlanAmtM06(BigDecimal planAmtM06) {
        this.planAmtM06 = planAmtM06;
    }
    
    @Column(name="PLAN_AMT_M07", precision=18)
    public BigDecimal getPlanAmtM07() {
        return this.planAmtM07;
    }
    
    public void setPlanAmtM07(BigDecimal planAmtM07) {
        this.planAmtM07 = planAmtM07;
    }
    
    @Column(name="PLAN_AMT_M08", precision=18)
    public BigDecimal getPlanAmtM08() {
        return this.planAmtM08;
    }
    
    public void setPlanAmtM08(BigDecimal planAmtM08) {
        this.planAmtM08 = planAmtM08;
    }
    
    @Column(name="PLAN_AMT_M09", precision=18)
    public BigDecimal getPlanAmtM09() {
        return this.planAmtM09;
    }
    
    public void setPlanAmtM09(BigDecimal planAmtM09) {
        this.planAmtM09 = planAmtM09;
    }
    
    @Column(name="PLAN_AMT_M10", precision=18)
    public BigDecimal getPlanAmtM10() {
        return this.planAmtM10;
    }
    
    public void setPlanAmtM10(BigDecimal planAmtM10) {
        this.planAmtM10 = planAmtM10;
    }
    
    @Column(name="PLAN_AMT_M11", precision=18)
    public BigDecimal getPlanAmtM11() {
        return this.planAmtM11;
    }
    
    public void setPlanAmtM11(BigDecimal planAmtM11) {
        this.planAmtM11 = planAmtM11;
    }
    
    @Column(name="PLAN_AMT_M12", precision=18)
    public BigDecimal getPlanAmtM12() {
        return this.planAmtM12;
    }
    
    public void setPlanAmtM12(BigDecimal planAmtM12) {
        this.planAmtM12 = planAmtM12;
    }
    
    @Column(name="BASES_DESC_M01", length=400)
    public String getBasesDescM01() {
        return this.basesDescM01;
    }
    
    public void setBasesDescM01(String basesDescM01) {
        this.basesDescM01 = basesDescM01;
    }
    
    @Column(name="BASES_DESC_M02", length=400)
    public String getBasesDescM02() {
        return this.basesDescM02;
    }
    
    public void setBasesDescM02(String basesDescM02) {
        this.basesDescM02 = basesDescM02;
    }
    
    @Column(name="BASES_DESC_M03", length=400)
    public String getBasesDescM03() {
        return this.basesDescM03;
    }
    
    public void setBasesDescM03(String basesDescM03) {
        this.basesDescM03 = basesDescM03;
    }
    
    @Column(name="BASES_DESC_M04", length=400)
    public String getBasesDescM04() {
        return this.basesDescM04;
    }
    
    public void setBasesDescM04(String basesDescM04) {
        this.basesDescM04 = basesDescM04;
    }
    
    @Column(name="BASES_DESC_M05", length=400)
    public String getBasesDescM05() {
        return this.basesDescM05;
    }
    
    public void setBasesDescM05(String basesDescM05) {
        this.basesDescM05 = basesDescM05;
    }
    
    @Column(name="BASES_DESC_M06", length=400)
    public String getBasesDescM06() {
        return this.basesDescM06;
    }
    
    public void setBasesDescM06(String basesDescM06) {
        this.basesDescM06 = basesDescM06;
    }
    
    @Column(name="BASES_DESC_M07", length=400)
    public String getBasesDescM07() {
        return this.basesDescM07;
    }
    
    public void setBasesDescM07(String basesDescM07) {
        this.basesDescM07 = basesDescM07;
    }
    
    @Column(name="BASES_DESC_M08", length=400)
    public String getBasesDescM08() {
        return this.basesDescM08;
    }
    
    public void setBasesDescM08(String basesDescM08) {
        this.basesDescM08 = basesDescM08;
    }
    
    @Column(name="BASES_DESC_M09", length=400)
    public String getBasesDescM09() {
        return this.basesDescM09;
    }
    
    public void setBasesDescM09(String basesDescM09) {
        this.basesDescM09 = basesDescM09;
    }
    
    @Column(name="BASES_DESC_M10", length=400)
    public String getBasesDescM10() {
        return this.basesDescM10;
    }
    
    public void setBasesDescM10(String basesDescM10) {
        this.basesDescM10 = basesDescM10;
    }
    
    @Column(name="BASES_DESC_M11", length=400)
    public String getBasesDescM11() {
        return this.basesDescM11;
    }
    
    public void setBasesDescM11(String basesDescM11) {
        this.basesDescM11 = basesDescM11;
    }
    
    @Column(name="BASES_DESC_M12", length=400)
    public String getBasesDescM12() {
        return this.basesDescM12;
    }
    
    public void setBasesDescM12(String basesDescM12) {
        this.basesDescM12 = basesDescM12;
    }
    

    @Column(name="CFM_PRE_YEAR_AMT_FLG", length=1)
    public String getCfmPreYearAmtFlg() {
		return cfmPreYearAmtFlg;
	}


	public void setCfmPreYearAmtFlg(String cfmPreYearAmtFlg) {
		this.cfmPreYearAmtFlg = cfmPreYearAmtFlg;
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




}


