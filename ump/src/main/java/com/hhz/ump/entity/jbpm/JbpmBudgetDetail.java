// 
package com.hhz.ump.entity.jbpm;
// Generated 2010-8-4 11:22:38 by Hibernate Tools 3.2.4.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.BatchSize;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JbpmBudgetDetail generated by hbm2java
 */
@Entity
@Table(name="JBPM_BUDGET_DETAIL"
    
)
public class JbpmBudgetDetail  implements java.io.Serializable {


     private String jbpmBudgetDetailId;
     private JbpmBudget jbpmBudget;
     private BigDecimal budgetFee;
     private String remark;
     private String creator;
     private String createdDeptCd;
     private Date createdDate;
     private String updator;
     private String updatedDeptCd;
     private Date updatedDate;
     private long recordVersion;

    public JbpmBudgetDetail() {
    }

	
    public JbpmBudgetDetail(String jbpmBudgetDetailId, JbpmBudget jbpmBudget, long recordVersion) {
        this.jbpmBudgetDetailId = jbpmBudgetDetailId;
        this.jbpmBudget = jbpmBudget;
        this.recordVersion = recordVersion;
    }
    public JbpmBudgetDetail(String jbpmBudgetDetailId, JbpmBudget jbpmBudget, BigDecimal budgetFee, String remark, String creator, String createdDeptCd, Date createdDate, String updator, String updatedDeptCd, Date updatedDate, long recordVersion) {
       this.jbpmBudgetDetailId = jbpmBudgetDetailId;
       this.jbpmBudget = jbpmBudget;
       this.budgetFee = budgetFee;
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

    
    @Column(name="JBPM_BUDGET_DETAIL_ID", unique=true, nullable=false, length=50)
    public String getJbpmBudgetDetailId() {
        return this.jbpmBudgetDetailId;
    }
    
    public void setJbpmBudgetDetailId(String jbpmBudgetDetailId) {
        this.jbpmBudgetDetailId = jbpmBudgetDetailId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JBPM_BUDGET_ID", nullable=false)
    public JbpmBudget getJbpmBudget() {
        return this.jbpmBudget;
    }
    
    public void setJbpmBudget(JbpmBudget jbpmBudget) {
        this.jbpmBudget = jbpmBudget;
    }

    
    @Column(name="BUDGET_FEE", precision=18, scale=4)
    public BigDecimal getBudgetFee() {
        return this.budgetFee;
    }
    
    public void setBudgetFee(BigDecimal budgetFee) {
        this.budgetFee = budgetFee;
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




}


