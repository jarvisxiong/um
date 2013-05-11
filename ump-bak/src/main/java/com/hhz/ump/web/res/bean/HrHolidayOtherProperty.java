/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-20
 */
public class HrHolidayOtherProperty {
	
	//假期类别
	private String holidayType;
	//单位
	private String unit;
	//由
	private String beginDate;
	//至	
	private String endDate;
	//合计请假天数	
	private String totalDays;
	//法定假日
	private String offiHolidays;
	//本休日
	private String holidays;
	
	//注
	private String remark;

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public String getOffiHolidays() {
		return offiHolidays;
	}

	public void setOffiHolidays(String offiHolidays) {
		this.offiHolidays = offiHolidays;
	}

	public String getHolidays() {
		return holidays;
	}

	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
