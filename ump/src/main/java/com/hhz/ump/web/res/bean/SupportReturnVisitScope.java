package com.hhz.ump.web.res.bean;

/**
 * <p>供方履约情况回访范围</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-23
 */

public class SupportReturnVisitScope {

	//供方类型
	private String supportType;
	
	//供方单位名称
	private String supportUnitName;
	
	//承接项目
	private String acceptProject;
	
	//分期or标段
	private String stages;
	
	//对接人
	private String joinPeople;
	
	//电话号码
	private String telNo;
	
	
	/**
	 * @return the supportType
	 */
	public String getSupportType() {
		return supportType;
	}

	/**
	 * @param supportType the supportType to set
	 */
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}

	/**
	 * @return the supportUnitName
	 */
	public String getSupportUnitName() {
		return supportUnitName;
	}

	/**
	 * @param supportUnitName the supportUnitName to set
	 */
	public void setSupportUnitName(String supportUnitName) {
		this.supportUnitName = supportUnitName;
	}

	/**
	 * @return the acceptProject
	 */
	public String getAcceptProject() {
		return acceptProject;
	}

	/**
	 * @param acceptProject the acceptProject to set
	 */
	public void setAcceptProject(String acceptProject) {
		this.acceptProject = acceptProject;
	}

	/**
	 * @return the joinPeople
	 */
	public String getJoinPeople() {
		return joinPeople;
	}

	/**
	 * @param joinPeople the joinPeople to set
	 */
	public void setJoinPeople(String joinPeople) {
		this.joinPeople = joinPeople;
	}

	

	/**
	 * @return the stages
	 */
	public String getStages() {
		return stages;
	}

	/**
	 * @param stages the stages to set
	 */
	public void setStages(String stages) {
		this.stages = stages;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	
	
	
	
}
