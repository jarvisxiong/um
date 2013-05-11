/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 请假单(宝龙商业)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HrHolidayBlsy extends HrHolidayApplySheet {
	private String dayType1;//短期
	private String dayType2;//长期
	private String positionLevel1;//总经理
	private String positionLevel2;//副总经理
	private String positionLevel3;//中心第一负责人
	private String positionLevel4;//其他人员
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
	public String getPositionLevel1() {
		return positionLevel1;
	}
	public void setPositionLevel1(String positionLevel1) {
		this.positionLevel1 = positionLevel1;
	}
	public String getPositionLevel2() {
		return positionLevel2;
	}
	public void setPositionLevel2(String positionLevel2) {
		this.positionLevel2 = positionLevel2;
	}
	public String getPositionLevel3() {
		return positionLevel3;
	}
	public void setPositionLevel3(String positionLevel3) {
		this.positionLevel3 = positionLevel3;
	}
	public String getPositionLevel4() {
		return positionLevel4;
	}
	public void setPositionLevel4(String positionLevel4) {
		this.positionLevel4 = positionLevel4;
	}
}
