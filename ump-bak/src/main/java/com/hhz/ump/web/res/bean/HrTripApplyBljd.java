/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 出差申请单(宝龙酒店)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HrTripApplyBljd extends BusinessTripPlanSheet {
	private String positionLevel1;//酒店开发中心-第一负责人
	private String positionLevel2;//酒店开发中心-其他人员
	private String positionLevel3;//委托管理酒店、自营酒店-总经理
	private String positionLevel4;//自营酒店-其他人员
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
