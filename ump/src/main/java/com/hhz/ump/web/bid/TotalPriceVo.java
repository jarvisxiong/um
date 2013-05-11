package com.hhz.ump.web.bid;

import java.math.BigDecimal;

public class TotalPriceVo implements java.io.Serializable{
	
	private String supName;
	private  BigDecimal refPrice;//参考价
	private BigDecimal supTotalPrice;//供应商总价
	private BigDecimal perSupTotalPrice;
	private String percent;
	private BigDecimal divisionTotalPrice;//分布分项
	private BigDecimal perMeterdivisionPrice;
	private BigDecimal divisionPercent;
	private BigDecimal measureOneTotalPrice;
	private BigDecimal perMeterMeasureOnePrice;
	private BigDecimal measureOnePercent;
	private BigDecimal measureTwoTotalPrice;
	private BigDecimal perMetermeasureTwoPrice;
	private BigDecimal measureTwoPercent;
	
	

	public String getPercent() {
		return percent;		
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	
	
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}

	public BigDecimal getSupTotalPrice() {
		return supTotalPrice;
	}
	public void setSupTotalPrice(BigDecimal supTotalPrice) {
		this.supTotalPrice = supTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getPerSupTotalPrice() {
		return perSupTotalPrice;
	}
	public void setPerSupTotalPrice(BigDecimal perSupTotalPrice) {
		this.perSupTotalPrice = perSupTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getDivisionTotalPrice() {
		return divisionTotalPrice;
	}
	public void setDivisionTotalPrice(BigDecimal divisionTotalPrice) {
		this.divisionTotalPrice = divisionTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getPerMeterdivisionPrice() {
		return perMeterdivisionPrice;
	}
	public void setPerMeterdivisionPrice(BigDecimal perMeterdivisionPrice) {
		this.perMeterdivisionPrice = perMeterdivisionPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getMeasureOneTotalPrice() {
		return measureOneTotalPrice;
	}
	public void setMeasureOneTotalPrice(BigDecimal measureOneTotalPrice) {
		this.measureOneTotalPrice = measureOneTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getPerMeterMeasureOnePrice() {
		return perMeterMeasureOnePrice;
	}
	public void setPerMeterMeasureOnePrice(BigDecimal perMeterMeasureOnePrice) {
		this.perMeterMeasureOnePrice = perMeterMeasureOnePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getMeasureTwoTotalPrice() {
		return measureTwoTotalPrice;
	}
	public void setMeasureTwoTotalPrice(BigDecimal measureTwoTotalPrice) {
		this.measureTwoTotalPrice = measureTwoTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getPerMetermeasureTwoPrice() {
		return perMetermeasureTwoPrice;
	}
	public void setPerMetermeasureTwoPrice(BigDecimal perMetermeasureTwoPrice) {
		this.perMetermeasureTwoPrice = perMetermeasureTwoPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getRefPrice() {
		return refPrice;
	}
	public void setRefPrice(BigDecimal refPrice) {
		this.refPrice = refPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getDivisionPercent() {
		return divisionPercent;
	}
	public void setDivisionPercent(BigDecimal divisionPercent) {
		this.divisionPercent = divisionPercent.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getMeasureOnePercent() {
		return measureOnePercent;
	}
	public void setMeasureOnePercent(BigDecimal measureOnePercent) {
		this.measureOnePercent = measureOnePercent.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getMeasureTwoPercent() {
		return measureTwoPercent;
	}
	public void setMeasureTwoPercent(BigDecimal measureTwoPercent) {
		this.measureTwoPercent = measureTwoPercent.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	
	
	
	
	

}
