package com.hhz.ump.web.res.bean;

//人事变动表(商业公司)
public class BizPersonnelChangeSygs extends BizPersonnelChange {
	private String positionLevel1;// 总经理
	private String positionLevel2;// 副总经理
	private String positionLevel3;// 高级经理级(除垂直管理人员外)及以上
	private String positionLevel4;// 双线管理部门负责人(财务、成本)
	private String positionLevel7;// 双线管理部门负责人(人资)
	private String positionLevel5;// 其他人员
	private String changeType1;// 薪资、岗位确定及调整
	private String changeType2;// 仅政策内补贴申请

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

	public String getPositionLevel7() {
		return positionLevel7;
	}

	public void setPositionLevel7(String positionLevel7) {
		this.positionLevel7 = positionLevel7;
	}

	public String getChangeType1() {
		return changeType1;
	}

	public void setChangeType1(String changeType1) {
		this.changeType1 = changeType1;
	}

	public String getChangeType2() {
		return changeType2;
	}

	public void setChangeType2(String changeType2) {
		this.changeType2 = changeType2;
	}

}
