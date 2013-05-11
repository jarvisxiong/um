/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 出差申请单(宝龙拍卖)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HrTripApplyBlpm extends BusinessTripPlanSheet {
	private String positionLevel1;//副总经理级
	private String positionLevel2;//其他人员
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
}
