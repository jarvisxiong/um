/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>年假延期申请单(地产公司)</p>
 * @author huangjian
 * @create 2012-4-19
 */
public class HrVocationDelayDcgs extends HrVocationDelayBase {
	private String positionLevel1;//总经理
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
