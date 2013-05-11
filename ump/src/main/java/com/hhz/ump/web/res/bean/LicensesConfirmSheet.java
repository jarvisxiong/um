package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 证照办理确认单
 * @author shixy
 *
 * 2010-12-24
 */
public class LicensesConfirmSheet extends BaseTemplate {
	private String titleName;
	/**
	 * □ 设立
	 */
	private String matters1;
	/**
	 * □ 基本信息变更（名称、地址、法人或负责人、类型、注册资金、经营范围、其他）
	 */
	private String matters2;
	/**
	 * □ 注销 
	 */
	private String matters3;
	/**
	 * □ 任职变更（董事长、总经理、股东、董事、监事） 
	 */
	private String matters4;
	/**
	 * □资质（新办、年检、升级）
	 */
	private String matters5;
	/**
	 * □项目手册（新办、年检）
	 */
	private String matters6;

	/**
	 * 申请内容（详细内容附后）
	 */
	private String applContent;
	/**
	 * 公司名称		
	 */
	private String companyName;
	/**
	 * 公司性质  内资		
	 */
	private String companyNatureN;
	/**
	 * 外资
	 */
	private String companyNatureW;
	/**
	 * 备用名称1	
	 */
	private String backupName1;
	/**
	 * 备用名称2		
	 */
	private String backupName2;
	/**
	 * 注册资本	
	 */
	private String registCapital;
	/**
	 * 法定代表人	
	 */
	private String legalPerson;
	/**
	 * 经营范围	
	 */
	private String businessScope;
	/**
	 * 股东及持股比例	
	 */
	private String shareProportion;
	/**
	 * 董事会成员或董事成员		
	 */
	private String bds;
	/**
	 * 监事	
	 */
	private String supervisors;
	/**
	 * 注册地址	
	 */
	private String registAddress;
	/**
	 * 其他要求	
	 */
	private String otherRequirements;
	
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
		return titleName;
	}

	public String getMatters1() {
		return matters1;
	}

	public void setMatters1(String matters1) {
		this.matters1 = matters1;
	}

	public String getMatters2() {
		return matters2;
	}

	public void setMatters2(String matters2) {
		this.matters2 = matters2;
	}

	public String getMatters3() {
		return matters3;
	}

	public void setMatters3(String matters3) {
		this.matters3 = matters3;
	}

	public String getMatters4() {
		return matters4;
	}

	public void setMatters4(String matters4) {
		this.matters4 = matters4;
	}

	public String getMatters5() {
		return matters5;
	}

	public void setMatters5(String matters5) {
		this.matters5 = matters5;
	}

	public String getMatters6() {
		return matters6;
	}

	public void setMatters6(String matters6) {
		this.matters6 = matters6;
	}

	public String getApplContent() {
		return applContent;
	}

	public void setApplContent(String applContent) {
		this.applContent = applContent;
	}

	@Override
	public String getCompanyName() {
		return companyName;
	}

	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNatureN() {
		return companyNatureN;
	}

	public void setCompanyNatureN(String companyNatureN) {
		this.companyNatureN = companyNatureN;
	}

	public String getCompanyNatureW() {
		return companyNatureW;
	}

	public void setCompanyNatureW(String companyNatureW) {
		this.companyNatureW = companyNatureW;
	}

	public String getBackupName1() {
		return backupName1;
	}

	public void setBackupName1(String backupName1) {
		this.backupName1 = backupName1;
	}

	public String getBackupName2() {
		return backupName2;
	}

	public void setBackupName2(String backupName2) {
		this.backupName2 = backupName2;
	}

	public String getRegistCapital() {
		return registCapital;
	}

	public void setRegistCapital(String registCapital) {
		this.registCapital = registCapital;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getShareProportion() {
		return shareProportion;
	}

	public void setShareProportion(String shareProportion) {
		this.shareProportion = shareProportion;
	}

	public String getBds() {
		return bds;
	}

	public void setBds(String bds) {
		this.bds = bds;
	}

	public String getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(String supervisors) {
		this.supervisors = supervisors;
	}

	public String getRegistAddress() {
		return registAddress;
	}

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	public String getOtherRequirements() {
		return otherRequirements;
	}

	public void setOtherRequirements(String otherRequirements) {
		this.otherRequirements = otherRequirements;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
