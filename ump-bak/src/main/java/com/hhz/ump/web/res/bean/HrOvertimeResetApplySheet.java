/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 加班补休申请表
 * 
 */
public class HrOvertimeResetApplySheet extends BaseTemplatePay {
	
	//	中心：centerOrgName, centerOrgCd 部门 : deptOrgName,deptOrgCd
	//	姓 名：userName,userCd	职 位：positionName,positionCd	职 级：positionLevelCd,positionLevelName
	//	补休日期：resetDate 从（时）： startHour  至（时）：endHour   	时数：restHours
	
	//  补偿替代：
	//  加班日期	 overTimeDate  加班时数 overTimeHours 上次补休后剩余时数 lastLeaveHours (3行)
	
	// 累计时数     totalOverTimeHours,totalLeaveHours
	// 补休原因     restReasonDesc
	
	
	private String centerOrgName;
	private String centerOrgCd;
	
	private String deptOrgName;
	private String deptOrgCd;
	
	private String userName;
	private String userCd;
	
	private String positionName;
	private String positionCd;
	
	private String positionLevelName;
	private String positionLevelCd;

	private String resetDate;
	private String resetStartHour;
	private String resetEndHour;
	private String restHours;

	private String overTimeDate1;
	private String overTimeHours1;
	private String lastLeaveHours1;
	
	private String overTimeDate2;
	private String overTimeHours2;
	private String lastLeaveHours2;
	
	private String overTimeDate3;
	private String overTimeHours3;
	private String lastLeaveHours3;
	
	private String totalOverTimeHours;
	private String totalLeaveHours;
	private String restReasonDesc;
	 
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
	public String getPositionLevelName() {
		return positionLevelName;
	}
	public void setPositionLevelName(String positionLevelName) {
		this.positionLevelName = positionLevelName;
	}
	public String getPositionLevelCd() {
		return positionLevelCd;
	}
	public void setPositionLevelCd(String positionLevelCd) {
		this.positionLevelCd = positionLevelCd;
	}
	public String getResetDate() {
		return resetDate;
	}
	public void setResetDate(String resetDate) {
		this.resetDate = resetDate;
	}
	public String getResetStartHour() {
		return resetStartHour;
	}
	public void setResetStartHour(String resetStartHour) {
		this.resetStartHour = resetStartHour;
	}
	public String getResetEndHour() {
		return resetEndHour;
	}
	public void setResetEndHour(String resetEndHour) {
		this.resetEndHour = resetEndHour;
	}
	public String getRestHours() {
		return restHours;
	}
	public void setRestHours(String restHours) {
		this.restHours = restHours;
	}
	public String getOverTimeDate1() {
		return overTimeDate1;
	}
	public void setOverTimeDate1(String overTimeDate1) {
		this.overTimeDate1 = overTimeDate1;
	}
	public String getOverTimeHours1() {
		return overTimeHours1;
	}
	public void setOverTimeHours1(String overTimeHours1) {
		this.overTimeHours1 = overTimeHours1;
	}
	public String getLastLeaveHours1() {
		return lastLeaveHours1;
	}
	public void setLastLeaveHours1(String lastLeaveHours1) {
		this.lastLeaveHours1 = lastLeaveHours1;
	}
	public String getOverTimeDate2() {
		return overTimeDate2;
	}
	public void setOverTimeDate2(String overTimeDate2) {
		this.overTimeDate2 = overTimeDate2;
	}
	public String getOverTimeHours2() {
		return overTimeHours2;
	}
	public void setOverTimeHours2(String overTimeHours2) {
		this.overTimeHours2 = overTimeHours2;
	}
	public String getLastLeaveHours2() {
		return lastLeaveHours2;
	}
	public void setLastLeaveHours2(String lastLeaveHours2) {
		this.lastLeaveHours2 = lastLeaveHours2;
	}
	public String getOverTimeDate3() {
		return overTimeDate3;
	}
	public void setOverTimeDate3(String overTimeDate3) {
		this.overTimeDate3 = overTimeDate3;
	}
	public String getOverTimeHours3() {
		return overTimeHours3;
	}
	public void setOverTimeHours3(String overTimeHours3) {
		this.overTimeHours3 = overTimeHours3;
	}
	public String getLastLeaveHours3() {
		return lastLeaveHours3;
	}
	public void setLastLeaveHours3(String lastLeaveHours3) {
		this.lastLeaveHours3 = lastLeaveHours3;
	}
	public String getTotalOverTimeHours() {
		return totalOverTimeHours;
	}
	public void setTotalOverTimeHours(String totalOverTimeHours) {
		this.totalOverTimeHours = totalOverTimeHours;
	}
	public String getTotalLeaveHours() {
		return totalLeaveHours;
	}
	public void setTotalLeaveHours(String totalLeaveHours) {
		this.totalLeaveHours = totalLeaveHours;
	}
	public String getRestReasonDesc() {
		return restReasonDesc;
	}
	public void setRestReasonDesc(String restReasonDesc) {
		this.restReasonDesc = restReasonDesc;
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
		return this.centerOrgName;
	}
}
