package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//开业前商业四级计划
public class BizPreOpenFour extends BaseTemplate {

	private String projectName;//项目名称
	private String versionNo;//版本
	private String contentDesc;//内容
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return versionNo;
	}
	
}
