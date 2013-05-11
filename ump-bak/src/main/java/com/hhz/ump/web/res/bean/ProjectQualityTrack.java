package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/*
 * 工程质量巡查报告审批表
 */
public class ProjectQualityTrack extends BaseTemplate{

	//项目名称
	private String projectName;
	private String projectCd;
	//巡查工程
	private String trackProject;
	//施工单位
	private String builder;
	//巡查日期
	private String trackDate;
	//巡查主要结论
	private String trackResult;
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return trackProject;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTrackProject() {
		return trackProject;
	}

	public void setTrackProject(String trackProject) {
		this.trackProject = trackProject;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getTrackDate() {
		return trackDate;
	}

	public void setTrackDate(String trackDate) {
		this.trackDate = trackDate;
	}

	public String getTrackResult() {
		return trackResult;
	}

	public void setTrackResult(String trackResult) {
		this.trackResult = trackResult;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

}
