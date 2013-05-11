/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>费用报销审批表(宝龙商业)</p>
 * @author huangjian
 * @create 2011-9-20
 */
public class FeeReimBillBlsy extends FeeReimBill {
//	private String positionLevel1;// 部门负责人以下
//	private String positionLevel2;// 部门负责人
	private String positionLevel3;// 中心总经理及以下
	private String positionLevel4;// 集团副总经理
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
