package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 百货商家租金、扣点、补贴、借款审批表
 * @author baolm
 *
 */
public class ManaStoreInviteApprove extends BaseTemplate {
	
	private String inPolicy; // □ 政策内
	private String outBelow10; // □ 超政策≤10%
	private String outAbove10; // □ 超政策＞10%
	
	private String inPlan; // □ 计划总额内
	private String outPlan; // □ 超计划总额
	private String name; // 名称
	private String desc; // 内容简述(详细内容附后)

	public String getInPolicy() {
		return inPolicy;
	}

	public void setInPolicy(String inPolicy) {
		this.inPolicy = inPolicy;
	}

	public String getOutBelow10() {
		return outBelow10;
	}

	public void setOutBelow10(String outBelow10) {
		this.outBelow10 = outBelow10;
	}

	public String getOutAbove10() {
		return outAbove10;
	}

	public void setOutAbove10(String outAbove10) {
		this.outAbove10 = outAbove10;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getInPlan() {
		return inPlan;
	}

	public void setInPlan(String inPlan) {
		this.inPlan = inPlan;
	}

	public String getOutPlan() {
		return outPlan;
	}

	public void setOutPlan(String outPlan) {
		this.outPlan = outPlan;
	}

}
