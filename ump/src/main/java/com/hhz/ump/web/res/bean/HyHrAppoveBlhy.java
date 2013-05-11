/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 宝龙行业人事审批表(宝龙行业)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HyHrAppoveBlhy extends HyHrApproveBill {
	private String positionLevel1;//总经理及以上
	private String positionLevel2;//副总经理级及以下
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
