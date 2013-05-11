package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//宝龙商业人员申请表
public class BizEmployeeApplyBill extends BaseTemplate{
	//职位		
	private String positionName;
	private String positionCd;
	//级别		
	private String positionLevelName;
	private String positionLevelCd;
	//中心	
	private String centerName;
	private String centerCd;
	
	
	//薪金		
	private String salary;
	//性别		
	private String sexCd1;
	private String sexCd2;
	//年龄		
	private String age;
	//婚否	
	private String marryCd1;
	private String marryCd2;
	
	
	//到岗日期		
	private String planWorkDate;
	//申请人数		
	private String applyCount;
	//□增加         □补缺
	private String applyTypeCd1;
	private String applyTypeCd2;
	
	
	//员工类别：                 □正式工                    □临时工
	private String employeeTypeCd1;
	private String employeeTypeCd2;
	
	
	//工作职责简述：
	private String workDutyDesc;
	//任职要求：
	private String workRequireDesc;
	//人员申请理由：
	private String applyReasonDesc;

	//招聘渠道：
	//□公司内部提升
	//□公司内部调动
	//□从外部招聘	
	private String recruitDitchCd1;
	private String recruitDitchCd2;
	private String recruitDitchCd3;
	
	//招聘费用：
	private String recruitFeeAmt;
	//实际到岗时间：
	private String realWorkDate;
	
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
	public String getSexCd1() {
		return sexCd1;
	}
	public void setSexCd1(String sexCd1) {
		this.sexCd1 = sexCd1;
	}
	public String getSexCd2() {
		return sexCd2;
	}
	public void setSexCd2(String sexCd2) {
		this.sexCd2 = sexCd2;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMarryCd1() {
		return marryCd1;
	}
	public void setMarryCd1(String marryCd1) {
		this.marryCd1 = marryCd1;
	}
	public String getMarryCd2() {
		return marryCd2;
	}
	public void setMarryCd2(String marryCd2) {
		this.marryCd2 = marryCd2;
	}
	public String getPlanWorkDate() {
		return planWorkDate;
	}
	public void setPlanWorkDate(String planWorkDate) {
		this.planWorkDate = planWorkDate;
	}
	public String getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}
	public String getApplyTypeCd1() {
		return applyTypeCd1;
	}
	public void setApplyTypeCd1(String applyTypeCd1) {
		this.applyTypeCd1 = applyTypeCd1;
	}
	public String getApplyTypeCd2() {
		return applyTypeCd2;
	}
	public void setApplyTypeCd2(String applyTypeCd2) {
		this.applyTypeCd2 = applyTypeCd2;
	}
	public String getEmployeeTypeCd1() {
		return employeeTypeCd1;
	}
	public void setEmployeeTypeCd1(String employeeTypeCd1) {
		this.employeeTypeCd1 = employeeTypeCd1;
	}
	public String getEmployeeTypeCd2() {
		return employeeTypeCd2;
	}
	public void setEmployeeTypeCd2(String employeeTypeCd2) {
		this.employeeTypeCd2 = employeeTypeCd2;
	}
	public String getWorkDutyDesc() {
		return workDutyDesc;
	}
	public void setWorkDutyDesc(String workDutyDesc) {
		this.workDutyDesc = workDutyDesc;
	}
	public String getWorkRequireDesc() {
		return workRequireDesc;
	}
	public void setWorkRequireDesc(String workRequireDesc) {
		this.workRequireDesc = workRequireDesc;
	}
	public String getApplyReasonDesc() {
		return applyReasonDesc;
	}
	public void setApplyReasonDesc(String applyReasonDesc) {
		this.applyReasonDesc = applyReasonDesc;
	}
	public String getRecruitDitchCd1() {
		return recruitDitchCd1;
	}
	public void setRecruitDitchCd1(String recruitDitchCd1) {
		this.recruitDitchCd1 = recruitDitchCd1;
	}
	public String getRecruitDitchCd2() {
		return recruitDitchCd2;
	}
	public void setRecruitDitchCd2(String recruitDitchCd2) {
		this.recruitDitchCd2 = recruitDitchCd2;
	}
	public String getRecruitDitchCd3() {
		return recruitDitchCd3;
	}
	public void setRecruitDitchCd3(String recruitDitchCd3) {
		this.recruitDitchCd3 = recruitDitchCd3;
	}
	public String getRecruitFeeAmt() {
		return recruitFeeAmt;
	}
	public void setRecruitFeeAmt(String recruitFeeAmt) {
		this.recruitFeeAmt = recruitFeeAmt;
	}
	public String getRealWorkDate() {
		return realWorkDate;
	}
	public void setRealWorkDate(String realWorkDate) {
		this.realWorkDate = realWorkDate;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return positionName;
	}

}
