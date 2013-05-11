package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//PD通讯录架构变动确认表
public class AddrBookChange extends BaseTemplate {

	//填表人
	private String applicant;
	//填表日期
	private String applyDate;
	//所在中心总经理
	private String centerManager;
	//变动说明(可带附件)
	private String changeDesc;
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

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getCenterManager() {
		return centerManager;
	}

	public void setCenterManager(String centerManager) {
		this.centerManager = centerManager;
	}

	public String getChangeDesc() {
		return changeDesc;
	}

	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}

}
