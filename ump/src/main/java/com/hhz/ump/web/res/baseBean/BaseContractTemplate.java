package com.hhz.ump.web.res.baseBean;

public abstract class BaseContractTemplate extends BaseTemplate{
	
	private String projectName;//项目名称
	private String projectCd;//项目Cd

	private String contactName;//合同名称
	

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
