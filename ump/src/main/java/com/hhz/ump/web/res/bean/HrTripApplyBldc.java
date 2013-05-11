/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 出差申请单(宝龙地产)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HrTripApplyBldc extends BusinessTripPlanSheet {
	private String positionLevel1;//副总裁级
	private String positionLevel2;//中心第一负责人
	private String positionLevel3;//其他人员
	private String submitDay;//提单日
	private String unSubmitDay;//非提单日
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
	public String getSubmitDay() {
		return submitDay;
	}
	public void setSubmitDay(String submitDay) {
		this.submitDay = submitDay;
	}
	public String getUnSubmitDay() {
		return unSubmitDay;
	}
	public void setUnSubmitDay(String unSubmitDay) {
		this.unSubmitDay = unSubmitDay;
	}
}
