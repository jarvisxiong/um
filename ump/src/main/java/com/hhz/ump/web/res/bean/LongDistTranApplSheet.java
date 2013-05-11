package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 长途用车申请单
 * @author shixy
 *
 * 2010-12-21
 */
public class LongDistTranApplSheet extends BaseTemplate {

	/**
	 * 驾驶员姓名
	 */
	private String driverName;
	/**
	 * 驾驶员cd
	 */
	private String driverCd;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 职位代码
	 */
	private String positionCd;
	/**
	 * 所属单位
	 */
	private String dept;
	
	/**
	 * 单位代码
	 */
	private String deptCd;
	/**
	 * 行程安排
	 */
	private String scheduling;
	/**
	 * 发车时间
	 */
	private String departTime;
	/**
	 * 返回时间
	 */
	private String returnTime;
	/**
	 * 随行人员
	 */
	private String entourage;
	/**
	 * 随行人员cd
	 */
	private String entourageCd;
	/**
	 * 事由
	 */
	private String reason;
	/**
	 * 公里数
	 */
	private String mileage;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return dept;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return deptCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return "行程安排："+scheduling;
	}
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverCd() {
		return driverCd;
	}
	public void setDriverCd(String driverCd) {
		this.driverCd = driverCd;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getScheduling() {
		return scheduling;
	}
	public void setScheduling(String scheduling) {
		this.scheduling = scheduling;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getEntourage() {
		return entourage;
	}
	public void setEntourage(String entourage) {
		this.entourage = entourage;
	}
	public String getEntourageCd() {
		return entourageCd;
	}
	public void setEntourageCd(String entourageCd) {
		this.entourageCd = entourageCd;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
	
}
