/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>人事审批表(自营酒店)</p>
 * @author huangjian
 * @create 2011-9-22
 */
public class HrApproveZyjd extends HrApproveSheet {
	private String positionLevel1;//总经理
	private String positionLevel2;//副总经理
	private String positionLevel3;//垂直管理
	private String positionLevel4;//其他人员
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
