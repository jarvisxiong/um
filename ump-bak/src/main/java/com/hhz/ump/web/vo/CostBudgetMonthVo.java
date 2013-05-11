// 
package com.hhz.ump.web.vo;

import java.math.BigDecimal;


public class CostBudgetMonthVo implements java.io.Serializable {
	private static final long serialVersionUID = 4096614773014988502L;
	
	private int orderNo;//显示序号
	private String projectId;//项目ID（即sectionId)
	private String projectName;//项目名称
	private String costBudgetYearId; //年度表ID
	private BigDecimal groupTotalAmt;//年度预算合计
	private BigDecimal planAmtMCount;//至本期的年度预算合计
	private BigDecimal cumuRealPayTotalAmt;//本年度至本期累计实付 	
	private BigDecimal curPeriodFundBudgetAmt3;//本期资金预算
	private BigDecimal deviationAmt;//预算偏差
	private String desc; //偏差说明
	private String statusCd; //状态
	private String costMonthId; //月度主键ID
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public BigDecimal getGroupTotalAmt() {
		return groupTotalAmt;
	}
	public void setGroupTotalAmt(BigDecimal groupTotalAmt) {
		this.groupTotalAmt = groupTotalAmt;
	}
	public BigDecimal getPlanAmtMCount() {
		return planAmtMCount;
	}
	public void setPlanAmtMCount(BigDecimal planAmtMCount) {
		this.planAmtMCount = planAmtMCount;
	}
	public BigDecimal getCumuRealPayTotalAmt() {
		return cumuRealPayTotalAmt;
	}
	public void setCumuRealPayTotalAmt(BigDecimal cumuRealPayTotalAmt) {
		this.cumuRealPayTotalAmt = cumuRealPayTotalAmt;
	}
	public BigDecimal getCurPeriodFundBudgetAmt3() {
		return curPeriodFundBudgetAmt3;
	}
	public void setCurPeriodFundBudgetAmt3(BigDecimal curPeriodFundBudgetAmt3) {
		this.curPeriodFundBudgetAmt3 = curPeriodFundBudgetAmt3;
	}
	public BigDecimal getDeviationAmt() {
		return deviationAmt;
	}
	public void setDeviationAmt(BigDecimal deviationAmt) {
		this.deviationAmt = deviationAmt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCostBudgetYearId() {
		return costBudgetYearId;
	}
	public void setCostBudgetYearId(String costBudgetYearId) {
		this.costBudgetYearId = costBudgetYearId;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getCostMonthId() {
		return costMonthId;
	}
	public void setCostMonthId(String costMonthId) {
		this.costMonthId = costMonthId;
	} 
	
	
}
