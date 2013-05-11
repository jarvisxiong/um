package com.hhz.ump.entity.plan;

import java.util.Date;

public class CostDetailVO {

	private String monthDesc;//本月、下月
	private String delayDesc;//近期、延期描述
	private String planType;//计划类型，执行计划、半年计划
	private String content;//事项
	private Date planCompleteTime;//计划完成时间
	private Date realCompleteTime;//实际完成时间
	private String status;//状态，已推送、未推送
	private String costCtrlBidPurcId;//推送到成本字段
	private String detailId;//执行计划或成本计划的DETAIL ID
	private String projectCd;//project CD
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCostCtrlBidPurcId() {
		return costCtrlBidPurcId;
	}
	public void setCostCtrlBidPurcId(String costCtrlBidPurcId) {
		this.costCtrlBidPurcId = costCtrlBidPurcId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getMonthDesc() {
		return monthDesc;
	}
	public void setMonthDesc(String monthDesc) {
		this.monthDesc = monthDesc;
	}
	public String getDelayDesc() {
		return delayDesc;
	}
	public void setDelayDesc(String delayDesc) {
		this.delayDesc = delayDesc;
	}
	public Date getPlanCompleteTime() {
		return planCompleteTime;
	}
	public void setPlanCompleteTime(Date planCompleteTime) {
		this.planCompleteTime = planCompleteTime;
	}
	public Date getRealCompleteTime() {
		return realCompleteTime;
	}
	public void setRealCompleteTime(Date realCompleteTime) {
		this.realCompleteTime = realCompleteTime;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	
}
