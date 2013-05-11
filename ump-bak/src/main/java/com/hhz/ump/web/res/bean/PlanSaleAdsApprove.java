package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 营销活动方案审批表
 * @author baolm
 *
 * 2011-05-31
 */
public class PlanSaleAdsApprove extends BaseTemplate {
	
	private String name; // 名称
	private String type; // 广告类别
	private String yearSalePlan; // □年度营销活动方案
	private String yearSalePlanOther; // □年度营销活动方案外营销活动方案
	private String openShopPlanl; // □开店活动方案
	private String majorEventPlan; // □重大活动方案
	private String desc; // 内容简述(详细内容附后)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYearSalePlan() {
		return yearSalePlan;
	}

	public void setYearSalePlan(String yearSalePlan) {
		this.yearSalePlan = yearSalePlan;
	}

	public String getYearSalePlanOther() {
		return yearSalePlanOther;
	}

	public void setYearSalePlanOther(String yearSalePlanOther) {
		this.yearSalePlanOther = yearSalePlanOther;
	}

	public String getOpenShopPlanl() {
		return openShopPlanl;
	}

	public void setOpenShopPlanl(String openShopPlanl) {
		this.openShopPlanl = openShopPlanl;
	}

	public String getMajorEventPlan() {
		return majorEventPlan;
	}

	public void setMajorEventPlan(String majorEventPlan) {
		this.majorEventPlan = majorEventPlan;
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
