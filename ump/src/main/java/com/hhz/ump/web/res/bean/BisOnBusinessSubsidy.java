package com.hhz.ump.web.res.bean;

/***
 * 出差补助
 * @author liwei
 *
 * 2012-07-03
 */
public class BisOnBusinessSubsidy {

	/***
	 * 出差补助#开始日期
	 */
	private String startDate;
	/***
	 * 出差补助#结束日期
	 */
	private String endDate;
	/***
	 * 出差补助#合计天数
	 */
	private String sumDays;
	/***
	 * 出差补助#出差地点
	 */
	private String place;
	/***
	 * 出差补助#日补助
	 */
	private String daySubsidy;
	/***
	 * 出差补助#共计补助
	 */
	private String sumSubsidy;
	
	
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
	 * @return the daySubsidy
	 */
	public String getDaySubsidy() {
		return daySubsidy;
	}
	/**
	 * @param daySubsidy the daySubsidy to set
	 */
	public void setDaySubsidy(String daySubsidy) {
		this.daySubsidy = daySubsidy;
	}
	/**
	 * @return the sumSubsidy
	 */
	public String getSumSubsidy() {
		return sumSubsidy;
	}
	/**
	 * @param sumSubsidy the sumSubsidy to set
	 */
	public void setSumSubsidy(String sumSubsidy) {
		this.sumSubsidy = sumSubsidy;
	}
	

}
