package com.hhz.ump.web.vo;

import java.math.BigDecimal;


public class VoFlatCount {
	
	//公寓收费记录，合计费用值
	private BigDecimal annualYsCount; //应收总计
	private BigDecimal annualSsCount; //实收总计
	private BigDecimal annualYusCount; //预收总计
	private BigDecimal annualQsCount; //欠收总计
	
	public BigDecimal getAnnualYsCount() {
		return annualYsCount;
	}
	public void setAnnualYsCount(BigDecimal annualYsCount) {
		this.annualYsCount = annualYsCount;
	}
	public BigDecimal getAnnualSsCount() {
		return annualSsCount;
	}
	public void setAnnualSsCount(BigDecimal annualSsCount) {
		this.annualSsCount = annualSsCount;
	}
	public BigDecimal getAnnualYusCount() {
		return annualYusCount;
	}
	public void setAnnualYusCount(BigDecimal annualYusCount) {
		this.annualYusCount = annualYusCount;
	}
	public BigDecimal getAnnualQsCount() {
		return annualQsCount;
	}
	public void setAnnualQsCount(BigDecimal annualQsCount) {
		this.annualQsCount = annualQsCount;
	}
	
}

