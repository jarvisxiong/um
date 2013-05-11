/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 样板房物资清单采购及调配建议审批表
 * 
 */
public class ExamMatePurcAllocSheet extends BaseTemplatePay {

	// 项目名称
	// 物业范围
	// 内容简述（详细内容附后）
	
	private String projectName;
	private String projectCd;
	private String propertyScopeDesc;
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
	public String getPropertyScopeDesc() {
		return propertyScopeDesc;
	}
	public void setPropertyScopeDesc(String propertyScopeDesc) {
		this.propertyScopeDesc = propertyScopeDesc;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
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
}
