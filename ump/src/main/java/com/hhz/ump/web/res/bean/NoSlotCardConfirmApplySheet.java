/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 未刷卡原因确认单
 * 
 */
public class NoSlotCardConfirmApplySheet extends BaseTemplatePay {

	// 中心 centerOrgName,centerOrgCd 部门：deptOrgName,deptOrgCd
	// 姓 名 userName, userCd 职 位：positionName,positionCd
	// 年月 yourMonthDate
	
	private String centerOrgName;
	private String centerOrgCd;
	private String deptOrgName;
	private String deptOrgCd;
	private String userName;
	private String userCd;
	private String positionName;
	private String positionCd;
	private String yourMonthDate;
	//未打卡记录清单
	private List<SlotCardProperty> slotCardProperties = new ArrayList<SlotCardProperty>();

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

	public List<SlotCardProperty> getSlotCardProperties() {
		return slotCardProperties;
	}

	public void setSlotCardProperties(List<SlotCardProperty> slotCardProperties) {
		this.slotCardProperties = slotCardProperties;
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

	public String getYourMonthDate() {
		return yourMonthDate;
	}

	public void setYourMonthDate(String yourMonthDate) {
		this.yourMonthDate = yourMonthDate;
	}

}
