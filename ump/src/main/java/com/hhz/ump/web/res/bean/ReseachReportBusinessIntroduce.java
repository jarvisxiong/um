package com.hhz.ump.web.res.bean;

/**
 * <p>项目预可研报告审批Bean</p>
 * <p>主要商业介绍(大型超市、百货、综合体)</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-15
 */

public class ReseachReportBusinessIntroduce {

	private String name;				//名称
	
	private String scope;				//规模
	
	private String primaryService;		//主要业态
	
	private String startTime;			//开业时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getPrimaryService() {
		return primaryService;
	}

	public void setPrimaryService(String primaryService) {
		this.primaryService = primaryService;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
