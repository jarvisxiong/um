package com.hhz.ump.web.vo;

import java.math.BigDecimal;

/**
 * 项目报表VO
 * 
 * @author baolm 2011-7-11
 */
public class BisProjectReportVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1913360409162254505L;

	private String chargeTypeCd;
	private String chargeTypeName;
	
	private BigDecimal mustYear;
	private BigDecimal mustQuarter;
	private BigDecimal mustMonthOne;
	private BigDecimal mustMonthTwo;
	private BigDecimal mustMonthThree;
	private BigDecimal factYear;
	private BigDecimal factQuarter;
	private BigDecimal factMonthOne;
	private BigDecimal factMonthTwo;
	private BigDecimal factMonthThree;
	private BigDecimal rateYear;
	private BigDecimal rateQuarter;
	private BigDecimal rateMonthOne;
	private BigDecimal rateMonthTwo;
	private BigDecimal rateMonthThree;
	
	public BisProjectReportVo() {
		
	}
	
	public BisProjectReportVo(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}
	
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}
	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	public String getChargeTypeName() {
		return chargeTypeName;
	}
	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}
	public BigDecimal getMustYear() {
		return mustYear;
	}
	public void setMustYear(BigDecimal mustYear) {
		this.mustYear = mustYear;
	}
	public BigDecimal getMustQuarter() {
		return mustQuarter;
	}
	public void setMustQuarter(BigDecimal mustQuarter) {
		this.mustQuarter = mustQuarter;
	}
	public BigDecimal getMustMonthOne() {
		return mustMonthOne;
	}
	public void setMustMonthOne(BigDecimal mustMonthOne) {
		this.mustMonthOne = mustMonthOne;
	}
	public BigDecimal getMustMonthTwo() {
		return mustMonthTwo;
	}
	public void setMustMonthTwo(BigDecimal mustMonthTwo) {
		this.mustMonthTwo = mustMonthTwo;
	}
	public BigDecimal getMustMonthThree() {
		return mustMonthThree;
	}
	public void setMustMonthThree(BigDecimal mustMonthThree) {
		this.mustMonthThree = mustMonthThree;
	}
	public BigDecimal getFactYear() {
		return factYear;
	}
	public void setFactYear(BigDecimal factYear) {
		this.factYear = factYear;
	}
	public BigDecimal getFactQuarter() {
		return factQuarter;
	}
	public void setFactQuarter(BigDecimal factQuarter) {
		this.factQuarter = factQuarter;
	}
	public BigDecimal getFactMonthOne() {
		return factMonthOne;
	}
	public void setFactMonthOne(BigDecimal factMonthOne) {
		this.factMonthOne = factMonthOne;
	}
	public BigDecimal getFactMonthTwo() {
		return factMonthTwo;
	}
	public void setFactMonthTwo(BigDecimal factMonthTwo) {
		this.factMonthTwo = factMonthTwo;
	}
	public BigDecimal getFactMonthThree() {
		return factMonthThree;
	}
	public void setFactMonthThree(BigDecimal factMonthThree) {
		this.factMonthThree = factMonthThree;
	}
	public BigDecimal getRateYear() {
		return rateYear;
	}
	public void setRateYear(BigDecimal rateYear) {
		this.rateYear = rateYear;
	}
	public BigDecimal getRateQuarter() {
		return rateQuarter;
	}
	public void setRateQuarter(BigDecimal rateQuarter) {
		this.rateQuarter = rateQuarter;
	}
	public BigDecimal getRateMonthOne() {
		return rateMonthOne;
	}
	public void setRateMonthOne(BigDecimal rateMonthOne) {
		this.rateMonthOne = rateMonthOne;
	}
	public BigDecimal getRateMonthTwo() {
		return rateMonthTwo;
	}
	public void setRateMonthTwo(BigDecimal rateMonthTwo) {
		this.rateMonthTwo = rateMonthTwo;
	}
	public BigDecimal getRateMonthThree() {
		return rateMonthThree;
	}
	public void setRateMonthThree(BigDecimal rateMonthThree) {
		this.rateMonthThree = rateMonthThree;
	}
	
}
