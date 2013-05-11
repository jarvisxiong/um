/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**加班申请表
 * @author huangj
 * 2010-12-24
 */
public class HrOvertimeProperty{
	private String applyUserName;//姓名
	private String applyUserCd;//姓名Cd
	private String positionName;//职位
	private String positionLevel;//职级
	private String overtimeDate;//加班日期
	private String fromTime;//从（时）
	private String toTime;//至（时）
	private String totalHour;//时数
	private String sendType1;//休息日加班
	private String sendType2;//法定假日加班
	
	private String sendType3;//旧属性
	private String sendType4;//旧属性
	
	private String subsidy1;//安排调休
	private String subsidy2;//支付工资
	
	private String reason;//加班原因
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public String getOvertimeDate() {
		return overtimeDate;
	}
	public void setOvertimeDate(String overtimeDate) {
		this.overtimeDate = overtimeDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(String totalHour) {
		this.totalHour = totalHour;
	}
	public String getSendType1() {
		return sendType1;
	}
	public void setSendType1(String sendType1) {
		this.sendType1 = sendType1;
	}
	public String getSendType2() {
		return sendType2;
	}
	public void setSendType2(String sendType2) {
		this.sendType2 = sendType2;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getApplyUserCd() {
		return applyUserCd;
	}
	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}
	public String getSubsidy1() {
		return subsidy1;
	}
	public void setSubsidy1(String subsidy1) {
		this.subsidy1 = subsidy1;
	}
	public String getSubsidy2() {
		return subsidy2;
	}
	public void setSubsidy2(String subsidy2) {
		this.subsidy2 = subsidy2;
	}
	public String getSendType3() {
		return sendType3;
	}
	public void setSendType3(String sendType3) {
		this.sendType3 = sendType3;
	}
	public String getSendType4() {
		return sendType4;
	}
	public void setSendType4(String sendType4) {
		this.sendType4 = sendType4;
	}
}
