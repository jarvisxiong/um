package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * EAS用户增加申请表
 * @author shixy
 *
 * 2010-12-23
 */
public class EasAddUserSheet extends BaseTemplate {
	/**
	 * 申请人
	 */
	private String applicant;
	/**
	 * 申请人帐号	
	 */
	private String applAccount;
	/**
	 * 申请日期
	 */
	private String applDate;
	/**
	 * 所在单位/部门/职位	
	 */
	private String dept;
	/**
	 * 工作职责描述	
	 */
	private String jobDuties;
	/**
	 * 帐套范围	
	 */
	private String accountScope;
	/**
	 * 职位级别 是否为公司财务负责人、中心经理级以上人员： □ 是              □ 否
	 */
	private String postionLevel1;
	private String postionLevel2;
	/**
	 * 权限模块	□ 总  账     
	 */
	private String module1;
	/**
	 * □ 报  表    
	 */
	private String module2;
	/**
	 * □ 现金管理    
	 */
	private String module3;
	/**
	 * □ 固定资产管理    
	 */
	private String module4;
	/**
	 * □ 现金流量表
	 */
	private String module5;
	/**
	 * □ 合并报表  
	 */
	private String module6;
	/**
	 * □ 基础资料 
	 */
	private String module7;
	/**
	 * □ 初始化     
	 */
	private String module8;
	/**
	 * □ 系统设置
	 */
	private String module9;
	/**
	 * □ 预算管理
	 */
	private String module10;
	/**
	 * □ 其他
	 */
	private String module11;
	/**
	 * □ 其它内容
	 */
	private String moduleOther;
	/**
	 * 权限内容	
	 */
	private String permissionContent;

	
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
		return applicant;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplAccount() {
		return applAccount;
	}

	public void setApplAccount(String applAccount) {
		this.applAccount = applAccount;
	}

	public String getApplDate() {
		return applDate;
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJobDuties() {
		return jobDuties;
	}

	public void setJobDuties(String jobDuties) {
		this.jobDuties = jobDuties;
	}

	public String getAccountScope() {
		return accountScope;
	}

	public void setAccountScope(String accountScope) {
		this.accountScope = accountScope;
	}


	public String getPostionLevel1() {
		return postionLevel1;
	}

	public void setPostionLevel1(String postionLevel1) {
		this.postionLevel1 = postionLevel1;
	}

	public String getPostionLevel2() {
		return postionLevel2;
	}

	public void setPostionLevel2(String postionLevel2) {
		this.postionLevel2 = postionLevel2;
	}

	public String getModule1() {
		return module1;
	}

	public void setModule1(String module1) {
		this.module1 = module1;
	}

	public String getModule2() {
		return module2;
	}

	public void setModule2(String module2) {
		this.module2 = module2;
	}

	public String getModule3() {
		return module3;
	}

	public void setModule3(String module3) {
		this.module3 = module3;
	}

	public String getModule4() {
		return module4;
	}

	public void setModule4(String module4) {
		this.module4 = module4;
	}

	public String getModule5() {
		return module5;
	}

	public void setModule5(String module5) {
		this.module5 = module5;
	}

	public String getModule6() {
		return module6;
	}

	public void setModule6(String module6) {
		this.module6 = module6;
	}

	public String getModule7() {
		return module7;
	}

	public void setModule7(String module7) {
		this.module7 = module7;
	}

	public String getModule8() {
		return module8;
	}

	public void setModule8(String module8) {
		this.module8 = module8;
	}

	public String getModule9() {
		return module9;
	}

	public void setModule9(String module9) {
		this.module9 = module9;
	}

	public String getModule11() {
		return module11;
	}

	public void setModule11(String module11) {
		this.module11 = module11;
	}

	public String getModuleOther() {
		return moduleOther;
	}

	public void setModuleOther(String moduleOther) {
		this.moduleOther = moduleOther;
	}

	public String getPermissionContent() {
		return permissionContent;
	}

	public void setPermissionContent(String permissionContent) {
		this.permissionContent = permissionContent;
	}

	public String getModule10() {
		return module10;
	}

	public void setModule10(String module10) {
		this.module10 = module10;
	}

}
