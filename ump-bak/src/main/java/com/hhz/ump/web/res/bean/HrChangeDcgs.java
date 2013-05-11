/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>人事变动表(地产公司)</p>
 * @author huangjian
 * @create 2011-9-22
 */
public class HrChangeDcgs extends HrChangeBill {
	private String positionLevel1;//总经理级及以上
	private String positionLevel2;//副总经理级
	private String positionLevel3;//经理级-垂直管理人员除外
	private String positionLevel4;//财务双线管理人员部门第一负责人
	private String positionLevel5;//其他人员
	private String positionLevel6;//人力、成本双线管理人员部门第一负责人
	
	private String positionType1;//是否为双线管理部门
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
	public String getPositionLevel6() {
		return positionLevel6;
	}
	public void setPositionLevel6(String positionLevel6) {
		this.positionLevel6 = positionLevel6;
	}
	public String getPositionType1() {
		return positionType1;
	}
	public void setPositionType1(String positionType1) {
		this.positionType1 = positionType1;
	}
}
