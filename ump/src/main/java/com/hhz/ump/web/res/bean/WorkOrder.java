package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 工作指令单
 * @author lujunyun 2010-12-21
 */
public class WorkOrder extends BaseTemplate {
	private String orderEndDate;	//要求完成时间
	private String orderContent;	//工作指令
	private String orderCondition;	//指令完成情况
	private String receiveUserNames;//接单责任人
	private String receiveUserCds;
	public String getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public String getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
	
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return orderEndDate;
	}
	public String getReceiveUserNames() {
		return receiveUserNames;
	}
	public void setReceiveUserNames(String receiveUserNames) {
		this.receiveUserNames = receiveUserNames;
	}
	public String getReceiveUserCds() {
		return receiveUserCds;
	}
	public void setReceiveUserCds(String receiveUserCds) {
		this.receiveUserCds = receiveUserCds;
	}
}
