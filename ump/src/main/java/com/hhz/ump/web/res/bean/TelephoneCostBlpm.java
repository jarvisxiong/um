/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>电话费用报销单(宝龙拍卖)</p>
 * @author huangjian
 * @create 2011-9-22
 */
public class TelephoneCostBlpm extends TelephoneCostApp {
	private String positionLevel1;//总经理级
	private String positionLevel2;//副总级及以下
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
