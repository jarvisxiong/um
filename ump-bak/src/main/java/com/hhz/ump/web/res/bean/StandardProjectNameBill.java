package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 标准审批表-项目名称
 * @author Administrator
 *
 */
public class StandardProjectNameBill extends BaseTemplate {
	private String isOutFace;//涉及外立面
	public String getIsOutFace() {
		return isOutFace;
	}

	public void setIsOutFace(String isOutFace) {
		this.isOutFace = isOutFace;
	}

	private String titleName;
	private String projectCd;//项目CD
	
	private String projectName;//项目名称
	
	private String contentBriefly;//内容简述
	
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
		return titleName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContentBriefly() {
		return contentBriefly;
	}

	public void setContentBriefly(String contentBriefly) {
		this.contentBriefly = contentBriefly;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
    
}