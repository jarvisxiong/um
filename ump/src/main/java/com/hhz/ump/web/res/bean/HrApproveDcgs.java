/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>人事审批表(地产公司)</p>
 * @author huangjian
 * @create 2011-9-22
 */
public class HrApproveDcgs extends HrApproveSheet {
	private String positionLevel1;//垂直管理人员
	private String positionLevel2;//经理级-垂直管理人员除外
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
