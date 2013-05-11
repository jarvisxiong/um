package com.hhz.ump.web.vo;

import java.math.BigDecimal;

public class PmMateVO {

	private String pmMateModuId;
	private String moduleName;
	private Long enableFlg;
    private String parentId;
	private String pmMateEntryId;
	private String activeTitle;
    private String activeContent;
    private String activePeriod;
    private BigDecimal expensesBudget;
	public String getPmMateModuId() {
		return pmMateModuId;
	}
	public void setPmMateModuId(String pmMateModuId) {
		this.pmMateModuId = pmMateModuId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Long getEnableFlg() {
		return enableFlg;
	}
	public void setEnableFlg(Long enableFlg) {
		this.enableFlg = enableFlg;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPmMateEntryId() {
		return pmMateEntryId;
	}
	public void setPmMateEntryId(String pmMateEntryId) {
		this.pmMateEntryId = pmMateEntryId;
	}
	public String getActiveTitle() {
		return activeTitle;
	}
	public void setActiveTitle(String activeTitle) {
		this.activeTitle = activeTitle;
	}
	public String getActiveContent() {
		return activeContent;
	}
	public void setActiveContent(String activeContent) {
		this.activeContent = activeContent;
	}
	public String getActivePeriod() {
		return activePeriod;
	}
	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}
	public BigDecimal getExpensesBudget() {
		return expensesBudget;
	}
	public void setExpensesBudget(BigDecimal expensesBudget) {
		this.expensesBudget = expensesBudget;
	}
	
}
