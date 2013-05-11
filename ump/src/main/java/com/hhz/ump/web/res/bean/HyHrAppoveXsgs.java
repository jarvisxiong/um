/**
 * 
 */
package com.hhz.ump.web.res.bean;

/** 宝龙行业人事审批表(下属公司)
 * @author huangjian
 *
 * 2011-8-16
 */
public class HyHrAppoveXsgs extends HyHrApproveBill {
	private String positionLevel1;//总经理级
	private String positionLevel2;//副总经理级
	private String positionLevel3;//经理级-垂直管理人员除外
	private String positionLevel4;//财务负责人和出纳
	private String positionLevel5;//其他人员
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
	public String getPositionLevel5() {
		return positionLevel5;
	}
	public void setPositionLevel5(String positionLevel5) {
		this.positionLevel5 = positionLevel5;
	}
}
