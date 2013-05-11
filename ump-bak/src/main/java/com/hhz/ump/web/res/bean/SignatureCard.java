package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/*
 * 印签会签单
 */
public class SignatureCard  extends BaseTemplate {
	//申请日期
	private String appDate;
	//申请人
	private String applicant;
	//直属部门、公司
	private String appDept;
	//事由
	private String origin;
	//文件名称
	private String fileName;
	//份数
	private String quantity;
	//用章种类  集团
	private String typeGroup;
	//用章种类  合同专用章
	private String typeSpecial;
	//用章种类  人事部用章
	private String typePersonnel;
	//财务专用章
	private String typeFinance;
	//法人章
	private String typeLegalPerson;
	
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
		return null;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getAppDept() {
		return appDept;
	}

	public void setAppDept(String appDept) {
		this.appDept = appDept;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTypeGroup() {
		return typeGroup;
	}

	public String getTypeSpecial() {
		return typeSpecial;
	}

	public void setTypeSpecial(String typeSpecial) {
		this.typeSpecial = typeSpecial;
	}

	public String getTypePersonnel() {
		return typePersonnel;
	}

	public void setTypePersonnel(String typePersonnel) {
		this.typePersonnel = typePersonnel;
	}

	public String getTypeFinance() {
		return typeFinance;
	}

	public void setTypeFinance(String typeFinance) {
		this.typeFinance = typeFinance;
	}

	public String getTypeLegalPerson() {
		return typeLegalPerson;
	}

	public void setTypeLegalPerson(String typeLegalPerson) {
		this.typeLegalPerson = typeLegalPerson;
	}

	public void setTypeGroup(String typeGroup) {
		this.typeGroup = typeGroup;
	}

}
