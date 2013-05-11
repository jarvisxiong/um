/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 未刷卡原因确认单(新)
 * 
 */
public class NoSlotCardBill extends BaseTemplate {

	
	private String centerOrgName;
	private String centerOrgCd;
	private String deptOrgName;
	private String deptOrgCd;
	private String userName;
	private String userCd;
	private String positionName;
	private String positionCd;

	// 日期 班次 实际打卡时间 未刷卡原因 编辑
	private String slotDate;//日期
	private String slotTime1;
	private String slotTime2;
	private String slotTime3;
	private String slotTime4;
	private String rsGoOut;//公事外出
	private String rsForget;//忘记刷卡
	private String rsBug;//机器故障
	private String selfOut;//私事外出
	private String remark;//备注
	
	
	public String getCenterOrgName() {
		return centerOrgName;
	}

	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}

	public String getCenterOrgCd() {
		return centerOrgCd;
	}

	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}

	public String getDeptOrgName() {
		return deptOrgName;
	}

	public void setDeptOrgName(String deptOrgName) {
		this.deptOrgName = deptOrgName;
	}

	public String getDeptOrgCd() {
		return deptOrgCd;
	}

	public void setDeptOrgCd(String deptOrgCd) {
		this.deptOrgCd = deptOrgCd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionCd() {
		return positionCd;
	}

	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}
 
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.centerOrgName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.centerOrgCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.deptOrgName;
	}

	public String getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}

	public String getSlotTime1() {
		return slotTime1;
	}

	public void setSlotTime1(String slotTime1) {
		this.slotTime1 = slotTime1;
	}

	public String getSlotTime2() {
		return slotTime2;
	}

	public void setSlotTime2(String slotTime2) {
		this.slotTime2 = slotTime2;
	}

	public String getSlotTime3() {
		return slotTime3;
	}

	public void setSlotTime3(String slotTime3) {
		this.slotTime3 = slotTime3;
	}

	public String getSlotTime4() {
		return slotTime4;
	}

	public void setSlotTime4(String slotTime4) {
		this.slotTime4 = slotTime4;
	}

	public String getRsGoOut() {
		return rsGoOut;
	}

	public void setRsGoOut(String rsGoOut) {
		this.rsGoOut = rsGoOut;
	}

	public String getRsForget() {
		return rsForget;
	}

	public void setRsForget(String rsForget) {
		this.rsForget = rsForget;
	}

	public String getRsBug() {
		return rsBug;
	}

	public void setRsBug(String rsBug) {
		this.rsBug = rsBug;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSelfOut() {
		return selfOut;
	}

	public void setSelfOut(String selfOut) {
		this.selfOut = selfOut;
	}

}
