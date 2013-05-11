package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 酒店客房样板房验收报告审批表
 * @author Administrator
 *
 */
public class HotelRooms extends BaseTemplate {

	private String projectName;//项目名称 
	private String propertyScope;//物业范围
	private String contentBriefly;//内容简述
	
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return super.getResProjectCd();
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
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPropertyScope() {
		return propertyScope;
	}
	public void setPropertyScope(String propertyScope) {
		this.propertyScope = propertyScope;
	}
	public String getContentBriefly() {
		return contentBriefly;
	}
	public void setContentBriefly(String contentBriefly) {
		this.contentBriefly = contentBriefly;
	}
	
	
	
	
}
