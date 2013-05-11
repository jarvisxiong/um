/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**上海总部车辆补贴报销申请单
 * @author huangj
 * 2010-12-21
 */
public class ShCarSubsidyBill  extends BaseTemplate {
	private String centerName;//中心名称
	private String centerCd;//中心Cd
	private String deptName;//部门
	private String userName;//申请人姓名
	private String positionName;//职务
	private String applyLimit;//申请额度
	private String startDate;//开始报销日期
	private String applyReason;//申请理由
	private String travelFileId;//行驶证复印件
	private String drivingFileId;//驾驶证复印件
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getApplyLimit() {
		return applyLimit;
	}
	public void setApplyLimit(String applyLimit) {
		this.applyLimit = applyLimit;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
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
		return userName;
	}
	public String getTravelFileId() {
		return travelFileId;
	}
	public void setTravelFileId(String travelFileId) {
		this.travelFileId = travelFileId;
	}
	public String getDrivingFileId() {
		return drivingFileId;
	}
	public void setDrivingFileId(String drivingFileId) {
		this.drivingFileId = drivingFileId;
	}
}
