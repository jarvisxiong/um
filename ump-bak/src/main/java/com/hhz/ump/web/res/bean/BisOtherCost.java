package com.hhz.ump.web.res.bean;

/***
 * 其它费用
 * @author liwei
 * 
 * 2012-07-03
 */
public class BisOtherCost {

	/***
	 * 其它费用#费用说明
	 */
	private String costDirection;
	/***
	 * 其它费用#费用金额
	 */
	private String costAmount;
	/***
	 * 其它费用#附件
	 */
	private String otherAttach;
	
	/**
	 * @return the costDirection
	 */
	public String getCostDirection() {
		return costDirection;
	}
	/**
	 * @param costDirection the costDirection to set
	 */
	public void setCostDirection(String costDirection) {
		this.costDirection = costDirection;
	}
	/**
	 * @return the costAmount
	 */
	public String getCostAmount() {
		return costAmount;
	}
	/**
	 * @param costAmount the costAmount to set
	 */
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}
	/**
	 * @return the otherAttach
	 */
	public String getOtherAttach() {
		return otherAttach;
	}
	/**
	 * @param otherAttach the otherAttach to set
	 */
	public void setOtherAttach(String otherAttach) {
		this.otherAttach = otherAttach;
	}

	
}
