package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 百货业态规划审批表
 * @author baolm
 *
 * 2011-05-31
 */
public class ManaStorePlanApprove extends BaseTemplate {
	
	private String name; // 名称
	private String marketResearch; // □ 市场调查报告及分析
	private String categoryPlan; // □ 品类规划方案
	private String desc; // 内容简述(详细内容附后)
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarketResearch() {
		return marketResearch;
	}

	public void setMarketResearch(String marketResearch) {
		this.marketResearch = marketResearch;
	}

	public String getCategoryPlan() {
		return categoryPlan;
	}

	public void setCategoryPlan(String categoryPlan) {
		this.categoryPlan = categoryPlan;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
