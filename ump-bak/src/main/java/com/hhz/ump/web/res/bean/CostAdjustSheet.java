package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 开发项目目标成本调整审批表
 * @author shixy
 *
 * 2010-12-22
 */
public class CostAdjustSheet extends BaseTemplate {
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 原目标成本（元）
	 */
	private String origTargetCost;
	
	/**
	 * 调整子项
	 */
	private String adjustSubKey;
	
	/**
	 * 调整后（元）
	 */
	private String afterAdjust;
	
	/**
	 * 调整原因
	 */
	private String adjustCause;

	/**
	 * 提出调整单位（部门）负责人
	 */
	private String adjustPrincipal;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return engineeringName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return adjustSubKey;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getOrigTargetCost() {
		return origTargetCost;
	}

	public void setOrigTargetCost(String origTargetCost) {
		this.origTargetCost = origTargetCost;
	}

	public String getAdjustSubKey() {
		return adjustSubKey;
	}

	public void setAdjustSubKey(String adjustSubKey) {
		this.adjustSubKey = adjustSubKey;
	}

	public String getAfterAdjust() {
		return afterAdjust;
	}

	public void setAfterAdjust(String afterAdjust) {
		this.afterAdjust = afterAdjust;
	}

	public String getAdjustCause() {
		return adjustCause;
	}

	public void setAdjustCause(String adjustCause) {
		this.adjustCause = adjustCause;
	}

	public String getAdjustPrincipal() {
		return adjustPrincipal;
	}

	public void setAdjustPrincipal(String adjustPrincipal) {
		this.adjustPrincipal = adjustPrincipal;
	}

}
