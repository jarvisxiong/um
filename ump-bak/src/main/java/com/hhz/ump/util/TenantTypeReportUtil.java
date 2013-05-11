package com.hhz.ump.util;

import java.math.BigDecimal;

public class TenantTypeReportUtil {
 
	private String yearMoneyRel;
	private String monthMoneyRel;
	private BigDecimal yearMoneySub;
	private BigDecimal monthMoneySub;
	private BigDecimal befYearMoney;
	private String chargeTypeCdRel;
	public String getYearMoneyRel() {
		return yearMoneyRel;
	}
	public void setYearMoneyRel(String yearMoneyRel) {
		this.yearMoneyRel = yearMoneyRel;
	}
	public String getMonthMoneyRel() {
		return monthMoneyRel;
	}
	public void setMonthMoneyRel(String monthMoneyRel) {
		this.monthMoneyRel = monthMoneyRel;
	}
	public BigDecimal getYearMoneySub() {
		return yearMoneySub;
	}
	public void setYearMoneySub(BigDecimal yearMoneySub) {
		this.yearMoneySub = yearMoneySub;
	}
	public BigDecimal getMonthMoneySub() {
		return monthMoneySub;
	}
	public void setMonthMoneySub(BigDecimal monthMoneySub) {
		this.monthMoneySub = monthMoneySub;
	}
	public BigDecimal getBefYearMoney() {
		return befYearMoney;
	}
	public void setBefYearMoney(BigDecimal befYearMoney) {
		this.befYearMoney = befYearMoney;
	}
	public String getChargeTypeCdRel() {
		return chargeTypeCdRel;
	}
	public void setChargeTypeCdRel(String chargeTypeCdRel) {
		this.chargeTypeCdRel = chargeTypeCdRel;
	}
}
