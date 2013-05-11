/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-21
 */
public class TargetCostAdjustOtherProperty {
	
	/**
	 * 调整子项内容
	 */
	private String subItemContent;
	
	/**
	 * 调整原因
	 */
	private String adjustReason;

	/**
	 * 原子项目标成本
	 */
	private String oriSubItemTargetCost;

	/**
	 * 调整后子项目标成本
	 */
	private String nowSubItemTargetCost;

	public String getSubItemContent() {
		return subItemContent;
	}

	public void setSubItemContent(String subItemContent) {
		this.subItemContent = subItemContent;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public String getOriSubItemTargetCost() {
		return oriSubItemTargetCost;
	}

	public void setOriSubItemTargetCost(String oriSubItemTargetCost) {
		this.oriSubItemTargetCost = oriSubItemTargetCost;
	}

	public String getNowSubItemTargetCost() {
		return nowSubItemTargetCost;
	}

	public void setNowSubItemTargetCost(String nowSubItemTargetCost) {
		this.nowSubItemTargetCost = nowSubItemTargetCost;
	}

}
