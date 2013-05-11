/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**人员申请表
 * @author huangj
 * 2010-12-24
 */
public class HrUserApplyBill  extends BaseTemplate {
	private String positionName;//职位
	private String positionLevel;//级别
	private String centerName;//中心
	private String centerCd;//中心
	private String salary;//薪金
	private String sex;//性别
	private String age;//年龄
	private String marriage;//婚否
	private String selectMarriageYes;
	private String selectMarriageNo;
	private String checkDate;//到岗日期
	private String applyNum;//申请人数
	private String isAdd;//增加
	private String isRepair;//补缺
	private String userKind1;//正式工
	private String userKind2;//临时工
	private String workDuty;//工作职责
	private String jobRequire;//任职要求
	private String applyReason;//人员申请理由
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
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
		return positionName;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
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
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getSelectMarriageYes() {
		return selectMarriageYes;
	}
	public void setSelectMarriageYes(String selectMarriageYes) {
		this.selectMarriageYes = selectMarriageYes;
	}
	public String getSelectMarriageNo() {
		return selectMarriageNo;
	}
	public void setSelectMarriageNo(String selectMarriageNo) {
		this.selectMarriageNo = selectMarriageNo;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}
	public String getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	public String getIsRepair() {
		return isRepair;
	}
	public void setIsRepair(String isRepair) {
		this.isRepair = isRepair;
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
	public String getWorkDuty() {
		return workDuty;
	}
	public void setWorkDuty(String workDuty) {
		this.workDuty = workDuty;
	}
	public String getJobRequire() {
		return jobRequire;
	}
	public void setJobRequire(String jobRequire) {
		this.jobRequire = jobRequire;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
}
