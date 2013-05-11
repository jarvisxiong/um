package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * EAS用户变更申请表
 * @author shixy
 *
 * 2010-12-23
 */
public class EasUpdateUserSheet extends BaseTemplate {
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
	 * 变更类型	
	 */
	private String changeType;
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
	 * 变更原因-□ 岗位变动
	 */
	private String reason1;
	/**
	 * 变更原因-□ 离职
	 */
	private String reason2;
	/**
	 * 变更原因-□ 密码遗失
	 */
	private String reason3;
	/**
	 * 变更原因-□ 其他（说明）：
	 */
	private String reason4;
	/**
	 * 其他（说明）
	 */
	private String reasonOther;
	/**
	 * 变更说明	
	 */
	private String changeDesc;

	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applicant;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return applAccount;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return changeDesc;
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

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
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

	public String getReason1() {
		return reason1;
	}

	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}

	public String getReason2() {
		return reason2;
	}

	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	public String getReason3() {
		return reason3;
	}

	public void setReason3(String reason3) {
		this.reason3 = reason3;
	}

	public String getReason4() {
		return reason4;
	}

	public void setReason4(String reason4) {
		this.reason4 = reason4;
	}

	public String getReasonOther() {
		return reasonOther;
	}

	public void setReasonOther(String reasonOther) {
		this.reasonOther = reasonOther;
	}

	public String getChangeDesc() {
		return changeDesc;
	}

	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}

}
