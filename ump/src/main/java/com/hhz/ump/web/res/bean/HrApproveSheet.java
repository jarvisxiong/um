package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 人事审批表
 * @author shixy
 *
 * 2010-12-23
 */
public class HrApproveSheet extends BaseTemplate {
	/**
	 * 姓    名
	 */
	private String userName;
	private String userCd;
	/**
	 * 岗位名称	
	 */
	private String position;
	/**
	 * 所在部门		
	 */
	private String dept;
	private String deptCd;
	/**
	 * 职    级	
	 */
	private String positionLevel;
	/**
	 * 入职日期	
	 */
	private String entryDate;
	/**
	 * 员工编号	
	 */
	private String jobNumber;
	/**
	 * 审核种类	□试用   
	 */
	private String auditType1;
	/**
	 * □转正  
	 */
	private String auditType2;
	/**
	 * □任免   
	 */
	private String auditType3;
	/**
	 * □调薪   
	 */
	private String auditType4;
	/**
	 * □奖励   
	 */
	private String auditType5;
	/**
	 * □处罚   
	 */
	private String auditType6;
	/**
	 * □辞退
	 */
	private String auditType7;
	/**
	 * □离职
	 */
	private String auditType8;
	/**
	 * 简要说明：
	 */
	private String simpleDesc;

	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return userName;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getAuditType1() {
		return auditType1;
	}

	public void setAuditType1(String auditType1) {
		this.auditType1 = auditType1;
	}

	public String getAuditType2() {
		return auditType2;
	}

	public void setAuditType2(String auditType2) {
		this.auditType2 = auditType2;
	}

	public String getAuditType3() {
		return auditType3;
	}

	public void setAuditType3(String auditType3) {
		this.auditType3 = auditType3;
	}

	public String getAuditType4() {
		return auditType4;
	}

	public void setAuditType4(String auditType4) {
		this.auditType4 = auditType4;
	}

	public String getAuditType5() {
		return auditType5;
	}

	public void setAuditType5(String auditType5) {
		this.auditType5 = auditType5;
	}

	public String getAuditType6() {
		return auditType6;
	}

	public void setAuditType6(String auditType6) {
		this.auditType6 = auditType6;
	}

	public String getAuditType7() {
		return auditType7;
	}

	public void setAuditType7(String auditType7) {
		this.auditType7 = auditType7;
	}

	public String getSimpleDesc() {
		return simpleDesc;
	}

	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}

	public String getAuditType8() {
		return auditType8;
	}

	public void setAuditType8(String auditType8) {
		this.auditType8 = auditType8;
	}

}
