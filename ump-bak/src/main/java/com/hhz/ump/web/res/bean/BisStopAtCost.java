package com.hhz.ump.web.res.bean;

/***
 * 住宿费
 * @author liwei
 *
 * 2012-07-03
 */
public class BisStopAtCost {

	/***
	 * 住宿费#开始日期
	 */
	private String startDate;
	/***
	 * 住宿费#结束日期
	 */
	private String endDate;
	/***
	 * 住宿费#共计天数
	 */
	private String sumDays;
	/***
	 * 住宿费#地点
	 */
	private String place;
	/***
	 * 住宿费#共计(元)
	 */
	private String sumMoney;
	/***
	 * 住宿费#平均每日(元)
	 */
	private String dayMoney;
	/***
	 * 住宿费#附件
	 */
	private String stopAtAttach;
	
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the sumDays
	 */
	public String getSumDays() {
		return sumDays;
	}
	/**
	 * @param sumDays the sumDays to set
	 */
	public void setSumDays(String sumDays) {
		this.sumDays = sumDays;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the sumMoney
	 */
	public String getSumMoney() {
		return sumMoney;
	}
	/**
	 * @param sumMoney the sumMoney to set
	 */
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	/**
	 * @return the dayMoney
	 */
	public String getDayMoney() {
		return dayMoney;
	}
	/**
	 * @param dayMoney the dayMoney to set
	 */
	public void setDayMoney(String dayMoney) {
		this.dayMoney = dayMoney;
	}
	/**
	 * @return the stopAtAttach
	 */
	public String getStopAtAttach() {
		return stopAtAttach;
	}
	/**
	 * @param stopAtAttach the stopAtAttach to set
	 */
	public void setStopAtAttach(String stopAtAttach) {
		this.stopAtAttach = stopAtAttach;
	}

	

}
