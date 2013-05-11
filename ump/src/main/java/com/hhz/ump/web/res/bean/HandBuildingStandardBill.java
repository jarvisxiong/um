package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**交楼标准审批表
 * @author huangj
 * 2010-12-23
 */
public class HandBuildingStandardBill extends BaseTemplate {
	private String projectName;//项目名称
	private String projectCd;//项目Cd
	private String propertyScope;//物业范围
	private String contentDesc;//内容简述
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	} 
	
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return propertyScope;
	}
	public String getPropertyScope() {
		return propertyScope;
	}
	public void setPropertyScope(String propertyScope) {
		this.propertyScope = propertyScope;
	}
}
