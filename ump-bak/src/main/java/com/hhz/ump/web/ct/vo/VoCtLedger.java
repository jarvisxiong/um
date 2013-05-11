package com.hhz.ump.web.ct.vo;

import java.math.BigDecimal;
import java.util.Date;

public class VoCtLedger {

	private String ctLedgerId;// 主键ID
	private String projectCd;
	private String projectName;
	private String periods;
	private String approvePrivFlg;
	
	private String searchFlg;
	private Date startDate;// 开工日期
	private Date handDate;// 交房日期
	
	
	private BigDecimal totalConsArea;// 总建筑面积
	private BigDecimal plotRateArea;
	private BigDecimal costTargetTotalAmt;// 目标成本总额
	private BigDecimal plotRateUnitAmt;//
	private String mainContentDesc;
	private String resApproveInfoId;
	private String ledgerStatus;//台帐状态
	
	private BigDecimal totalDiviPlanContAmt;// 分解总额(实时汇总)
	private String attachFlg;//是否有附件
	
	


	public BigDecimal getTotalConsArea() {
		return totalConsArea;
	}

	public void setTotalConsArea(BigDecimal totalConsArea) {
		this.totalConsArea = totalConsArea;
	}

	public BigDecimal getCostTargetTotalAmt() {
		return costTargetTotalAmt;
	}

	public void setCostTargetTotalAmt(BigDecimal costTargetTotalAmt) {
		this.costTargetTotalAmt = costTargetTotalAmt;
	}

	public BigDecimal getTotalDiviPlanContAmt() {
		return totalDiviPlanContAmt;
	}

	public void setTotalDiviPlanContAmt(BigDecimal totalDiviPlanContAmt) {
		this.totalDiviPlanContAmt = totalDiviPlanContAmt;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getHandDate() {
		return handDate;
	}

	public void setHandDate(Date handDate) {
		this.handDate = handDate;
	}

	public String getAttachFlg() {
		return attachFlg;
	}

	public void setAttachFlg(String attachFlg) {
		this.attachFlg = attachFlg;
	}

	public String getCtLedgerId() {
		return ctLedgerId;
	}

	public void setCtLedgerId(String ctLedgerId) {
		this.ctLedgerId = ctLedgerId;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getApprovePrivFlg() {
		return approvePrivFlg;
	}

	public void setApprovePrivFlg(String approvePrivFlg) {
		this.approvePrivFlg = approvePrivFlg;
	}

	public String getSearchFlg() {
		return searchFlg;
	}

	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}

	public BigDecimal getPlotRateArea() {
		return plotRateArea;
	}

	public void setPlotRateArea(BigDecimal plotRateArea) {
		this.plotRateArea = plotRateArea;
	}

	public BigDecimal getPlotRateUnitAmt() {
		return plotRateUnitAmt;
	}

	public void setPlotRateUnitAmt(BigDecimal plotRateUnitAmt) {
		this.plotRateUnitAmt = plotRateUnitAmt;
	}

	public String getMainContentDesc() {
		return mainContentDesc;
	}

	public void setMainContentDesc(String mainContentDesc) {
		this.mainContentDesc = mainContentDesc;
	}

	public String getResApproveInfoId() {
		return resApproveInfoId;
	}

	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}

	/**
	 * @return the ledgerStatus
	 */
	public String getLedgerStatus() {
		return ledgerStatus;
	}

	/**
	 * @param ledgerStatus the ledgerStatus to set
	 */
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}

}
