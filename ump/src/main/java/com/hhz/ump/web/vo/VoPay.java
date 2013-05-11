package com.hhz.ump.web.vo;

import java.math.BigDecimal;
import java.util.Date;

public class VoPay {
	private String bisPayId;
    private String chargeTypeCd;
    private String operationType;
    private String chargeTypeCdName;
    private String year;
    private String month;
    private BigDecimal budgetMoney;//预算
    private BigDecimal money;//支出
    private Date opearateDate;//操作日期
    private String checkUserCd;
    private Date checkDate;
    private String statusCd;
    private String remark;
    private String bisProjectId;
    private String projectName;
    private String creator;
	public String getBisPayId() {
		return bisPayId;
	}
	public void setBisPayId(String bisPayId) {
		this.bisPayId = bisPayId;
	}
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}
	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}
	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getOpearateDate() {
		return opearateDate;
	}
	public void setOpearateDate(Date opearateDate) {
		this.opearateDate = opearateDate;
	}
	public String getCheckUserCd() {
		return checkUserCd;
	}
	public void setCheckUserCd(String checkUserCd) {
		this.checkUserCd = checkUserCd;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getChargeTypeCdName() {
		return chargeTypeCdName;
	}
	public void setChargeTypeCdName(String chargeTypeCdName) {
		this.chargeTypeCdName = chargeTypeCdName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getBisProjectId() {
		return bisProjectId;
	}
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
    
}

