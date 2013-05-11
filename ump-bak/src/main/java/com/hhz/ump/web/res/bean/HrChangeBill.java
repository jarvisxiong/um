/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**人事变动表
 * @author huangj
 * 2010-12-21
 */
public class HrChangeBill  extends BaseTemplate {
	private String userName;//姓名
	private String centerName;//中心
	private String centerCd;//中心
	private String deptName;//部门
	private String positionName;//职位
	private String enterDate;//入职日期
	private String effectDate;//生效日期
	private String userKind1;//正式工
	private String userKind2;//临时工
	private String userKind3;//实习生
	private String userKind4;//外聘
	private String userKind5;//外派
	private String userKind6;//外籍
	private String adjustItem2;//通过试用期
	private String adjustItem3;//升职
	private String adjustItem4;//降职
	private String adjustItem5;//内部调动
	private String adjustItem6;//工资变动
	private String adjustItem7;//其他
	private String adjustItem8;//临时借调
	
	//--------huangyong start 2011-10-10 新增奖励与惩罚选项
	private String adjustItem9;  //奖励
	private String adjustItem10; //惩罚
	private String adjustItem11; //新聘
	private String adjustItem12; //离职
	//--------huangyong end
	
	private String adjustOther;//其他内容
	private String centerBefore;//中心调整前
	private String centerAfter;//中心调整后
	private String deptBefore;//部门调整前
	private String deptAfter;//部门调整后
	private String positionBefore;//职位调整前
	private String positionAfter;//职位调整后
	private String levelBefore;//职级调整前
	private String levelAfter;//职级调整后
	private String salaryBefore;//工资调整前
	private String salaryAfter;//后工资调整后
	private String foreSubsidyBefore;//外派补贴调整前
	private String foreSubsidyAfter;//外派补贴调整后
	private String houseSubsidyBefore;//住房补贴调整前
	private String houseSubsidyAfter;//住房补贴调整后
	private String trafficSubsidyBefore;//交通补贴调整前
	private String trafficSubsidyAfter;//交通补贴调整后
	private String commuSubsidyBefore;//通讯补贴调整前
	private String commuSubsidyAfter;//通讯补贴调整后
	private String specialSubsidyBefore;//特殊补贴调整前
	private String specialSubsidyAfter;//特殊补贴调整后
	private String otherBefore;//其他调整前
	private String otherAfter;//其他调整后
	private String remark;//备注
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return centerCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return centerName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return userName+"<"+positionName+">";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCd() {
		return centerCd;
	}
	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getUserKind1() {
		return userKind1;
	}
	public void setUserKind1(String userKind1) {
		this.userKind1 = userKind1;
	}
	public String getUserKind2() {
		return userKind2;
	}
	public void setUserKind2(String userKind2) {
		this.userKind2 = userKind2;
	}
	public String getUserKind3() {
		return userKind3;
	}
	public void setUserKind3(String userKind3) {
		this.userKind3 = userKind3;
	}
	public String getUserKind4() {
		return userKind4;
	}
	public void setUserKind4(String userKind4) {
		this.userKind4 = userKind4;
	}
	public String getUserKind5() {
		return userKind5;
	}
	public void setUserKind5(String userKind5) {
		this.userKind5 = userKind5;
	}
	public String getUserKind6() {
		return userKind6;
	}
	public void setUserKind6(String userKind6) {
		this.userKind6 = userKind6;
	}
	public String getAdjustItem2() {
		return adjustItem2;
	}
	public void setAdjustItem2(String adjustItem2) {
		this.adjustItem2 = adjustItem2;
	}
	public String getAdjustItem3() {
		return adjustItem3;
	}
	public void setAdjustItem3(String adjustItem3) {
		this.adjustItem3 = adjustItem3;
	}
	public String getAdjustItem4() {
		return adjustItem4;
	}
	public void setAdjustItem4(String adjustItem4) {
		this.adjustItem4 = adjustItem4;
	}
	public String getAdjustItem5() {
		return adjustItem5;
	}
	public void setAdjustItem5(String adjustItem5) {
		this.adjustItem5 = adjustItem5;
	}
	public String getAdjustItem6() {
		return adjustItem6;
	}
	public void setAdjustItem6(String adjustItem6) {
		this.adjustItem6 = adjustItem6;
	}
	public String getAdjustItem7() {
		return adjustItem7;
	}
	public void setAdjustItem7(String adjustItem7) {
		this.adjustItem7 = adjustItem7;
	}
	public String getAdjustItem8() {
		return adjustItem8;
	}
	public void setAdjustItem8(String adjustItem8) {
		this.adjustItem8 = adjustItem8;
	}
	public String getAdjustOther() {
		return adjustOther;
	}
	public void setAdjustOther(String adjustOther) {
		this.adjustOther = adjustOther;
	}
	public String getCenterBefore() {
		return centerBefore;
	}
	public void setCenterBefore(String centerBefore) {
		this.centerBefore = centerBefore;
	}
	public String getCenterAfter() {
		return centerAfter;
	}
	public void setCenterAfter(String centerAfter) {
		this.centerAfter = centerAfter;
	}
	public String getDeptBefore() {
		return deptBefore;
	}
	public void setDeptBefore(String deptBefore) {
		this.deptBefore = deptBefore;
	}
	public String getDeptAfter() {
		return deptAfter;
	}
	public void setDeptAfter(String deptAfter) {
		this.deptAfter = deptAfter;
	}
	public String getPositionBefore() {
		return positionBefore;
	}
	public void setPositionBefore(String positionBefore) {
		this.positionBefore = positionBefore;
	}
	public String getPositionAfter() {
		return positionAfter;
	}
	public void setPositionAfter(String positionAfter) {
		this.positionAfter = positionAfter;
	}
	public String getLevelBefore() {
		return levelBefore;
	}
	public void setLevelBefore(String levelBefore) {
		this.levelBefore = levelBefore;
	}
	public String getLevelAfter() {
		return levelAfter;
	}
	public void setLevelAfter(String levelAfter) {
		this.levelAfter = levelAfter;
	}
	public String getSalaryBefore() {
		return salaryBefore;
	}
	public void setSalaryBefore(String salaryBefore) {
		this.salaryBefore = salaryBefore;
	}
	public String getSalaryAfter() {
		return salaryAfter;
	}
	public void setSalaryAfter(String salaryAfter) {
		this.salaryAfter = salaryAfter;
	}
	public String getForeSubsidyBefore() {
		return foreSubsidyBefore;
	}
	public void setForeSubsidyBefore(String foreSubsidyBefore) {
		this.foreSubsidyBefore = foreSubsidyBefore;
	}
	public String getForeSubsidyAfter() {
		return foreSubsidyAfter;
	}
	public void setForeSubsidyAfter(String foreSubsidyAfter) {
		this.foreSubsidyAfter = foreSubsidyAfter;
	}
	public String getHouseSubsidyBefore() {
		return houseSubsidyBefore;
	}
	public void setHouseSubsidyBefore(String houseSubsidyBefore) {
		this.houseSubsidyBefore = houseSubsidyBefore;
	}
	public String getHouseSubsidyAfter() {
		return houseSubsidyAfter;
	}
	public void setHouseSubsidyAfter(String houseSubsidyAfter) {
		this.houseSubsidyAfter = houseSubsidyAfter;
	}
	public String getTrafficSubsidyBefore() {
		return trafficSubsidyBefore;
	}
	public void setTrafficSubsidyBefore(String trafficSubsidyBefore) {
		this.trafficSubsidyBefore = trafficSubsidyBefore;
	}
	public String getTrafficSubsidyAfter() {
		return trafficSubsidyAfter;
	}
	public void setTrafficSubsidyAfter(String trafficSubsidyAfter) {
		this.trafficSubsidyAfter = trafficSubsidyAfter;
	}
	public String getCommuSubsidyBefore() {
		return commuSubsidyBefore;
	}
	public void setCommuSubsidyBefore(String commuSubsidyBefore) {
		this.commuSubsidyBefore = commuSubsidyBefore;
	}
	public String getCommuSubsidyAfter() {
		return commuSubsidyAfter;
	}
	public void setCommuSubsidyAfter(String commuSubsidyAfter) {
		this.commuSubsidyAfter = commuSubsidyAfter;
	}
	public String getSpecialSubsidyBefore() {
		return specialSubsidyBefore;
	}
	public void setSpecialSubsidyBefore(String specialSubsidyBefore) {
		this.specialSubsidyBefore = specialSubsidyBefore;
	}
	public String getSpecialSubsidyAfter() {
		return specialSubsidyAfter;
	}
	public void setSpecialSubsidyAfter(String specialSubsidyAfter) {
		this.specialSubsidyAfter = specialSubsidyAfter;
	}
	public String getOtherBefore() {
		return otherBefore;
	}
	public void setOtherBefore(String otherBefore) {
		this.otherBefore = otherBefore;
	}
	public String getOtherAfter() {
		return otherAfter;
	}
	public void setOtherAfter(String otherAfter) {
		this.otherAfter = otherAfter;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the adjustItem9
	 */
	public String getAdjustItem9() {
		return adjustItem9;
	}
	/**
	 * @param adjustItem9 the adjustItem9 to set
	 */
	public void setAdjustItem9(String adjustItem9) {
		this.adjustItem9 = adjustItem9;
	}
	/**
	 * @return the adjustItem10
	 */
	public String getAdjustItem10() {
		return adjustItem10;
	}
	/**
	 * @param adjustItem10 the adjustItem10 to set
	 */
	public void setAdjustItem10(String adjustItem10) {
		this.adjustItem10 = adjustItem10;
	}
	public String getAdjustItem11() {
		return adjustItem11;
	}
	public void setAdjustItem11(String adjustItem11) {
		this.adjustItem11 = adjustItem11;
	}
	/**
	 * @return the adjustItem12
	 */
	public String getAdjustItem12() {
		return adjustItem12;
	}
	/**
	 * @param adjustItem12 the adjustItem12 to set
	 */
	public void setAdjustItem12(String adjustItem12) {
		this.adjustItem12 = adjustItem12;
	}
}
