/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>人事变动表(宝龙地产)</p>
 * @author huangjian
 * @create 2011-9-22
 */
public class HrChangeBldc extends HrChangeBill {
	private String positionLevel1;//总经理级及以上
	private String positionLevel2;//副总经理级及以下人员
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
