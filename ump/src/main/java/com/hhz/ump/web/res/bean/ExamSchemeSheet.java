/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 样板房方案审批表(eg:定价)
 * 
 */
public class ExamSchemeSheet extends BaseTemplatePay {
	
	//	项目名称	
	//	样板房位置	　层　　　单元　　　住宅/店面
	//	内容简述 （详细内容附后）	 
		 
	private String projectName;
	private String projectCd;
	private String buildName;
	private String floorName;
	private String unitName;
	private String storeName;
	private String contentDesc;
	
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
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.projectName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.projectCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.projectName;
	}
}
