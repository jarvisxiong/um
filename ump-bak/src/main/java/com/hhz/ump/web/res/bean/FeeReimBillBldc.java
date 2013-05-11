/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>费用报销审批表(宝龙地产)</p>
 * @author huangjian
 * @create 2011-9-20
 */
public class FeeReimBillBldc extends FeeReimBill {
	private String positionLevel1;// 部门负责人以下
	private String positionLevel2;// 部门负责人
	private String positionLevel3;// 中心总经理
	private String positionLevel4;// 副总裁
	private String positionLevel5;// 行政副总裁
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
