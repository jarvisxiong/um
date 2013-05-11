/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 请假单(晟合数码)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HrHolidayShsm extends HrHolidayApplySheet {
	private String dayType1;//短期
	private String dayType2;//长期
	public String getDayType1() {
		return dayType1;
	}
	public void setDayType1(String dayType1) {
		this.dayType1 = dayType1;
	}
	public String getDayType2() {
		return dayType2;
	}
	public void setDayType2(String dayType2) {
		this.dayType2 = dayType2;
	}
}
