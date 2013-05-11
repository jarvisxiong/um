package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 概念规划设计成果审批表
 * 
 * @author huangj 2010-9-17
 */
public class IdeaDesignTaskBill extends BaseTemplate {
	private String projectName;//项目名称
	private String projectCd;//项目Cd
	private String designUnit;//设计单位
	private String remark;//设计任务书提要
	private String bidBillId;//定标审批表
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
	public String getDesignUnit() {
		return designUnit;
	}
	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return designUnit;
	}
	public String getBidBillId() {
		return bidBillId;
	}
	public void setBidBillId(String bidBillId) {
		this.bidBillId = bidBillId;
	}
}
