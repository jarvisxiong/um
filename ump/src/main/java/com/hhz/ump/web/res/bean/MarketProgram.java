package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 营销方案审批表
 * @author Administrator
 *
 */
public class MarketProgram extends BaseTemplate {
	private String titleName;
	private String projectCd;//项目CD
	
	private String projectName;//项目名称
	
	private String totalMarketProgram;//整体营销方案
	
	private String stageMarketProgram;//阶段销售方案
	
	private String subscriptionProgram;//认购方案
	
	private String openProgram;//开盘方案
	
	private String additionPush;//房源加推
	
	private String extendProgram;//推广方案
	
	private String sellPlan;//销售计划
	
	private String planProgram;//策划方案
	
	private String programContent;//方案简要

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

	public String getTotalMarketProgram() {
		return totalMarketProgram;
	}

	public void setTotalMarketProgram(String totalMarketProgram) {
		this.totalMarketProgram = totalMarketProgram;
	}

	public String getStageMarketProgram() {
		return stageMarketProgram;
	}

	public void setStageMarketProgram(String stageMarketProgram) {
		this.stageMarketProgram = stageMarketProgram;
	}

	public String getSubscriptionProgram() {
		return subscriptionProgram;
	}

	public void setSubscriptionProgram(String subscriptionProgram) {
		this.subscriptionProgram = subscriptionProgram;
	}

	public String getOpenProgram() {
		return openProgram;
	}

	public void setOpenProgram(String openProgram) {
		this.openProgram = openProgram;
	}

	public String getAdditionPush() {
		return additionPush;
	}

	public void setAdditionPush(String additionPush) {
		this.additionPush = additionPush;
	}

	public String getExtendProgram() {
		return extendProgram;
	}

	public void setExtendProgram(String extendProgram) {
		this.extendProgram = extendProgram;
	}

	public String getSellPlan() {
		return sellPlan;
	}

	public void setSellPlan(String sellPlan) {
		this.sellPlan = sellPlan;
	}

	public String getPlanProgram() {
		return planProgram;
	}

	public void setPlanProgram(String planProgram) {
		this.planProgram = planProgram;
	}

	public String getProgramContent() {
		return programContent;
	}

	public void setProgramContent(String programContent) {
		this.programContent = programContent;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
