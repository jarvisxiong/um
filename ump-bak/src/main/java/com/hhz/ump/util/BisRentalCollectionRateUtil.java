package com.hhz.ump.util;

import java.math.BigDecimal;

/***
 * 租费收缴率累计 显示实体
 * @author Aspenn
 *
 */
public class BisRentalCollectionRateUtil {
	/***
	 * 项目编号
	 */
	private String bisProjectId;
	/**
	 * 累计当月	租金实收
	 */
	private String rentalRentSs;
	/**
	 * 累计当月	租金应收
	 */
	private String rentalRentYs;
	/**
	 * 累计当月	物业实收
	 */
	private String rentalPropertySs;
	/**
	 * 累计当月	物业应收
	 */
	private String rentalPropertyYs;
	/***
	 * 当月	租金实收
	 */
	private String rentalRentSsCur;
	/***
	 * 当月	租金应收
	 */
	private String rentalRentYsCur;
	/**
	 * 当月	物业实收
	 */
	private String rentalPropertySsCur;
	/**
	 * 当月	物业应收
	 */
	private String rentalPropertyYsCur;
	/***
	 * 上月	租金实收
	 */
	private String lastRentalRentSs;
	/**
	 * 上月	租金应收
	 */
	private String lastRentalRentYs;
	/***
	 * 上月	物业实收
	 */
	private String lastRentalPropertySs;
	/***
	 * 上月	物业应收
	 */
	private String lastRentalPropertyYs;
	/***
	 * 前月	租金实收
	 */
	private String beforeRentalRentSs;
	/***
	 * 前月	租金应收
	 */
	private String beforeRentalRentYs;
	/***
	 * 前月	物业实收
	 */
	private String beforeRentalPropertySs;
	/***
	 * 前月	物业应收
	 */
	private String beforeRentalPropertyYs;

	//能耗相关字段 -add by liuzhihui 2012-06-26
	/**
	 * 累计当月	能耗实收
	 */
	private BigDecimal rentalEnergySs;
	/**
	 * 累计当月	能耗应收
	 */
	private BigDecimal rentalEnergyYs;
	/***
	 * 当月	能耗实收
	 */
	private BigDecimal rentalEnergySsCur;
	/***
	 * 当月	能耗应收
	 */
	private BigDecimal rentalEnergyYsCur;
	/***
	 * 上月	能耗实收
	 */
	private BigDecimal lastRentalEnergySs;
	/**
	 * 上月	能耗应收
	 */
	private BigDecimal lastRentalEnergyYs;
	/***
	 * 前月	能耗实收
	 */
	private BigDecimal beforeRentalEnergySs;
	/***
	 * 前月	能耗应收
	 */
	private BigDecimal beforeRentalEnergyYs;
	
	
	
	public BisRentalCollectionRateUtil() {
		super();
	}
	public BisRentalCollectionRateUtil(String bisProjectId) {
		super();
		this.bisProjectId = bisProjectId;
	}
	
	public BisRentalCollectionRateUtil(String rentalRentSs,
			String rentalRentYs, String rentalPropertySs,
			String rentalPropertyYs, String rentalRentSsCur,
			String rentalRentYsCur, String rentalPropertySsCur,
			String rentalPropertyYsCur, String lastRentalRentSs,
			String lastRentalRentYs, String lastRentalPropertySs,
			String lastRentalPropertyYs, String beforeRentalRentSs,
			String beforeRentalRentYs, String beforeRentalPropertySs,
			String beforeRentalPropertyYs) {
		super();
		this.rentalRentSs = rentalRentSs;
		this.rentalRentYs = rentalRentYs;
		this.rentalPropertySs = rentalPropertySs;
		this.rentalPropertyYs = rentalPropertyYs;
		this.rentalRentSsCur = rentalRentSsCur;
		this.rentalRentYsCur = rentalRentYsCur;
		this.rentalPropertySsCur = rentalPropertySsCur;
		this.rentalPropertyYsCur = rentalPropertyYsCur;
		this.lastRentalRentSs = lastRentalRentSs;
		this.lastRentalRentYs = lastRentalRentYs;
		this.lastRentalPropertySs = lastRentalPropertySs;
		this.lastRentalPropertyYs = lastRentalPropertyYs;
		this.beforeRentalRentSs = beforeRentalRentSs;
		this.beforeRentalRentYs = beforeRentalRentYs;
		this.beforeRentalPropertySs = beforeRentalPropertySs;
		this.beforeRentalPropertyYs = beforeRentalPropertyYs;
	}


	public BisRentalCollectionRateUtil(String bisProjectId,
			String rentalRentSs, String rentalRentYs, String rentalPropertySs,
			String rentalPropertyYs, String rentalRentSsCur,
			String rentalRentYsCur, String rentalPropertySsCur,
			String rentalPropertyYsCur, String lastRentalRentSs,
			String lastRentalRentYs, String lastRentalPropertySs,
			String lastRentalPropertyYs, String beforeRentalRentSs,
			String beforeRentalRentYs, String beforeRentalPropertySs,
			String beforeRentalPropertyYs) {
		super();
		this.bisProjectId = bisProjectId;
		this.rentalRentSs = rentalRentSs;
		this.rentalRentYs = rentalRentYs;
		this.rentalPropertySs = rentalPropertySs;
		this.rentalPropertyYs = rentalPropertyYs;
		this.rentalRentSsCur = rentalRentSsCur;
		this.rentalRentYsCur = rentalRentYsCur;
		this.rentalPropertySsCur = rentalPropertySsCur;
		this.rentalPropertyYsCur = rentalPropertyYsCur;
		this.lastRentalRentSs = lastRentalRentSs;
		this.lastRentalRentYs = lastRentalRentYs;
		this.lastRentalPropertySs = lastRentalPropertySs;
		this.lastRentalPropertyYs = lastRentalPropertyYs;
		this.beforeRentalRentSs = beforeRentalRentSs;
		this.beforeRentalRentYs = beforeRentalRentYs;
		this.beforeRentalPropertySs = beforeRentalPropertySs;
		this.beforeRentalPropertyYs = beforeRentalPropertyYs;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getRentalRentSs() {
		return rentalRentSs;
	}
	public void setRentalRentSs(String rentalRentSs) {
		this.rentalRentSs = rentalRentSs;
	}
	public String getRentalRentYs() {
		return rentalRentYs;
	}
	public void setRentalRentYs(String rentalRentYs) {
		this.rentalRentYs = rentalRentYs;
	}
	public String getRentalPropertySs() {
		return rentalPropertySs;
	}
	public void setRentalPropertySs(String rentalPropertySs) {
		this.rentalPropertySs = rentalPropertySs;
	}
	public String getRentalPropertyYs() {
		return rentalPropertyYs;
	}
	public void setRentalPropertyYs(String rentalPropertyYs) {
		this.rentalPropertyYs = rentalPropertyYs;
	}
	public String getRentalRentSsCur() {
		return rentalRentSsCur;
	}
	public void setRentalRentSsCur(String rentalRentSsCur) {
		this.rentalRentSsCur = rentalRentSsCur;
	}
	public String getRentalRentYsCur() {
		return rentalRentYsCur;
	}
	public void setRentalRentYsCur(String rentalRentYsCur) {
		this.rentalRentYsCur = rentalRentYsCur;
	}
	public String getRentalPropertySsCur() {
		return rentalPropertySsCur;
	}
	public void setRentalPropertySsCur(String rentalPropertySsCur) {
		this.rentalPropertySsCur = rentalPropertySsCur;
	}
	public String getRentalPropertyYsCur() {
		return rentalPropertyYsCur;
	}
	public void setRentalPropertyYsCur(String rentalPropertyYsCur) {
		this.rentalPropertyYsCur = rentalPropertyYsCur;
	}
	public String getLastRentalRentSs() {
		return lastRentalRentSs;
	}
	public void setLastRentalRentSs(String lastRentalRentSs) {
		this.lastRentalRentSs = lastRentalRentSs;
	}
	public String getLastRentalRentYs() {
		return lastRentalRentYs;
	}
	public void setLastRentalRentYs(String lastRentalRentYs) {
		this.lastRentalRentYs = lastRentalRentYs;
	}
	public String getLastRentalPropertySs() {
		return lastRentalPropertySs;
	}
	public void setLastRentalPropertySs(String lastRentalPropertySs) {
		this.lastRentalPropertySs = lastRentalPropertySs;
	}
	public String getLastRentalPropertyYs() {
		return lastRentalPropertyYs;
	}
	public void setLastRentalPropertyYs(String lastRentalPropertyYs) {
		this.lastRentalPropertyYs = lastRentalPropertyYs;
	}
	public String getBeforeRentalRentSs() {
		return beforeRentalRentSs;
	}
	public void setBeforeRentalRentSs(String beforeRentalRentSs) {
		this.beforeRentalRentSs = beforeRentalRentSs;
	}
	public String getBeforeRentalRentYs() {
		return beforeRentalRentYs;
	}
	public void setBeforeRentalRentYs(String beforeRentalRentYs) {
		this.beforeRentalRentYs = beforeRentalRentYs;
	}
	public String getBeforeRentalPropertySs() {
		return beforeRentalPropertySs;
	}
	public void setBeforeRentalPropertySs(String beforeRentalPropertySs) {
		this.beforeRentalPropertySs = beforeRentalPropertySs;
	}
	public String getBeforeRentalPropertyYs() {
		return beforeRentalPropertyYs;
	}
	public void setBeforeRentalPropertyYs(String beforeRentalPropertyYs) {
		this.beforeRentalPropertyYs = beforeRentalPropertyYs;
	}
	public BigDecimal getRentalEnergySs() {
		return rentalEnergySs;
	}
	public void setRentalEnergySs(BigDecimal rentalEnergySs) {
		this.rentalEnergySs = rentalEnergySs;
	}
	public BigDecimal getRentalEnergyYs() {
		return rentalEnergyYs;
	}
	public void setRentalEnergyYs(BigDecimal rentalEnergyYs) {
		this.rentalEnergyYs = rentalEnergyYs;
	}
	public BigDecimal getRentalEnergySsCur() {
		return rentalEnergySsCur;
	}
	public void setRentalEnergySsCur(BigDecimal rentalEnergySsCur) {
		this.rentalEnergySsCur = rentalEnergySsCur;
	}
	public BigDecimal getRentalEnergyYsCur() {
		return rentalEnergyYsCur;
	}
	public void setRentalEnergyYsCur(BigDecimal rentalEnergyYsCur) {
		this.rentalEnergyYsCur = rentalEnergyYsCur;
	}
	public BigDecimal getLastRentalEnergySs() {
		return lastRentalEnergySs;
	}
	public void setLastRentalEnergySs(BigDecimal lastRentalEnergySs) {
		this.lastRentalEnergySs = lastRentalEnergySs;
	}
	public BigDecimal getLastRentalEnergyYs() {
		return lastRentalEnergyYs;
	}
	public void setLastRentalEnergyYs(BigDecimal lastRentalEnergyYs) {
		this.lastRentalEnergyYs = lastRentalEnergyYs;
	}
	public BigDecimal getBeforeRentalEnergySs() {
		return beforeRentalEnergySs;
	}
	public void setBeforeRentalEnergySs(BigDecimal beforeRentalEnergySs) {
		this.beforeRentalEnergySs = beforeRentalEnergySs;
	}
	public BigDecimal getBeforeRentalEnergyYs() {
		return beforeRentalEnergyYs;
	}
	public void setBeforeRentalEnergyYs(BigDecimal beforeRentalEnergyYs) {
		this.beforeRentalEnergyYs = beforeRentalEnergyYs;
	}
	
}
