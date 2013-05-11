package com.hhz.ump.web.cost.vo;

import java.math.BigDecimal;

import com.hhz.ump.entity.cost.CostBudgetMonth;

public class CostBudgetMonthDetailVo implements java.io.Serializable{

	private static final long serialVersionUID = -6384390620309603358L;
	
	private Integer orderNo;
	private String costBudgetMonthDetailId;
    private CostBudgetMonth costBudgetMonth;
    private String subjectCd;
    private String subjectName;
    private String contactId;
    private String contactNo;
    private String contactName;
    private String partb;
    private BigDecimal contactTotalAmt;
    private BigDecimal contactRealTotalAmt;
    private BigDecimal settleAmt;
    private BigDecimal finishProdTotalAmt;
    private BigDecimal nailFeedWorthAmt;
    private BigDecimal cumuMustPayTotalAmt;
    private BigDecimal cumuRealPayTotalAmt;
    private BigDecimal cumuPaiedRate;
    private BigDecimal preYearCumuPaiedAmt;
    private BigDecimal curYearPrePeriodPayAmt;
    private BigDecimal curPeriodPlanConfmAmt;
    private BigDecimal curPeriodNailFeedAmt;
    private BigDecimal curPeriodFundMpayAmt;
    private BigDecimal cumuMustNoPayAmt;
    private BigDecimal curPeriodFundBudgetAmt;
    private String memoDesc;
    private String remark;
    private String creator; 
    private Long sequenceNo;
    private BigDecimal curPeriodFundBudgetAmt1;
    private BigDecimal curPeriodFundBudgetAmt2;
    private BigDecimal curPeriodFundBudgetAmt3;
    private String strageFlg;
    private String projectName;
    private String ym;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getCostBudgetMonthDetailId() {
		return costBudgetMonthDetailId;
	}
	public void setCostBudgetMonthDetailId(String costBudgetMonthDetailId) {
		this.costBudgetMonthDetailId = costBudgetMonthDetailId;
	}
	public CostBudgetMonth getCostBudgetMonth() {
		return costBudgetMonth;
	}
	public void setCostBudgetMonth(CostBudgetMonth costBudgetMonth) {
		this.costBudgetMonth = costBudgetMonth;
	}
	public String getSubjectCd() {
		return subjectCd;
	}
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPartb() {
		return partb;
	}
	public void setPartb(String partb) {
		this.partb = partb;
	}
	public BigDecimal getContactTotalAmt() {
		return contactTotalAmt;
	}
	public void setContactTotalAmt(BigDecimal contactTotalAmt) {
		this.contactTotalAmt = contactTotalAmt;
	}
	public BigDecimal getContactRealTotalAmt() {
		return contactRealTotalAmt;
	}
	public void setContactRealTotalAmt(BigDecimal contactRealTotalAmt) {
		this.contactRealTotalAmt = contactRealTotalAmt;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}
	public BigDecimal getFinishProdTotalAmt() {
		return finishProdTotalAmt;
	}
	public void setFinishProdTotalAmt(BigDecimal finishProdTotalAmt) {
		this.finishProdTotalAmt = finishProdTotalAmt;
	}
	public BigDecimal getNailFeedWorthAmt() {
		return nailFeedWorthAmt;
	}
	public void setNailFeedWorthAmt(BigDecimal nailFeedWorthAmt) {
		this.nailFeedWorthAmt = nailFeedWorthAmt;
	}
	public BigDecimal getCumuMustPayTotalAmt() {
		return cumuMustPayTotalAmt;
	}
	public void setCumuMustPayTotalAmt(BigDecimal cumuMustPayTotalAmt) {
		this.cumuMustPayTotalAmt = cumuMustPayTotalAmt;
	}
	public BigDecimal getCumuRealPayTotalAmt() {
		return cumuRealPayTotalAmt;
	}
	public void setCumuRealPayTotalAmt(BigDecimal cumuRealPayTotalAmt) {
		this.cumuRealPayTotalAmt = cumuRealPayTotalAmt;
	}
	public BigDecimal getCumuPaiedRate() {
		return cumuPaiedRate;
	}
	public void setCumuPaiedRate(BigDecimal cumuPaiedRate) {
		this.cumuPaiedRate = cumuPaiedRate;
	}
	public BigDecimal getPreYearCumuPaiedAmt() {
		return preYearCumuPaiedAmt;
	}
	public void setPreYearCumuPaiedAmt(BigDecimal preYearCumuPaiedAmt) {
		this.preYearCumuPaiedAmt = preYearCumuPaiedAmt;
	}
	public BigDecimal getCurYearPrePeriodPayAmt() {
		return curYearPrePeriodPayAmt;
	}
	public void setCurYearPrePeriodPayAmt(BigDecimal curYearPrePeriodPayAmt) {
		this.curYearPrePeriodPayAmt = curYearPrePeriodPayAmt;
	}
	public BigDecimal getCurPeriodPlanConfmAmt() {
		return curPeriodPlanConfmAmt;
	}
	public void setCurPeriodPlanConfmAmt(BigDecimal curPeriodPlanConfmAmt) {
		this.curPeriodPlanConfmAmt = curPeriodPlanConfmAmt;
	}
	public BigDecimal getCurPeriodNailFeedAmt() {
		return curPeriodNailFeedAmt;
	}
	public void setCurPeriodNailFeedAmt(BigDecimal curPeriodNailFeedAmt) {
		this.curPeriodNailFeedAmt = curPeriodNailFeedAmt;
	}
	public BigDecimal getCurPeriodFundMpayAmt() {
		return curPeriodFundMpayAmt;
	}
	public void setCurPeriodFundMpayAmt(BigDecimal curPeriodFundMpayAmt) {
		this.curPeriodFundMpayAmt = curPeriodFundMpayAmt;
	}
	public BigDecimal getCumuMustNoPayAmt() {
		return cumuMustNoPayAmt;
	}
	public void setCumuMustNoPayAmt(BigDecimal cumuMustNoPayAmt) {
		this.cumuMustNoPayAmt = cumuMustNoPayAmt;
	}
	public BigDecimal getCurPeriodFundBudgetAmt() {
		return curPeriodFundBudgetAmt;
	}
	public void setCurPeriodFundBudgetAmt(BigDecimal curPeriodFundBudgetAmt) {
		this.curPeriodFundBudgetAmt = curPeriodFundBudgetAmt;
	}
	public String getMemoDesc() {
		return memoDesc;
	}
	public void setMemoDesc(String memoDesc) {
		this.memoDesc = memoDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public BigDecimal getCurPeriodFundBudgetAmt2() {
		return curPeriodFundBudgetAmt2;
	}
	public void setCurPeriodFundBudgetAmt2(BigDecimal curPeriodFundBudgetAmt2) {
		this.curPeriodFundBudgetAmt2 = curPeriodFundBudgetAmt2;
	}
	public BigDecimal getCurPeriodFundBudgetAmt3() {
		return curPeriodFundBudgetAmt3;
	}
	public void setCurPeriodFundBudgetAmt3(BigDecimal curPeriodFundBudgetAmt3) {
		this.curPeriodFundBudgetAmt3 = curPeriodFundBudgetAmt3;
	}
	public BigDecimal getCurPeriodFundBudgetAmt1() {
		return curPeriodFundBudgetAmt1;
	}
	public void setCurPeriodFundBudgetAmt1(BigDecimal curPeriodFundBudgetAmt1) {
		this.curPeriodFundBudgetAmt1 = curPeriodFundBudgetAmt1;
	}
	public String getStrageFlg() {
		return strageFlg;
	}
	public void setStrageFlg(String strageFlg) {
		this.strageFlg = strageFlg;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
    
    
}
