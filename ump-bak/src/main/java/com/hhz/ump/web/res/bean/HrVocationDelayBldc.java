/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>年假延期申请单(宝龙地产)</p>
 * @author huangjian
 * @create 2012-4-19
 */
public class HrVocationDelayBldc extends HrVocationDelayBase {
	private String positionLevel1;//副总裁级
	private String positionLevel2;//中心第一负责人
	private String positionLevel3;//其他人员
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
}
