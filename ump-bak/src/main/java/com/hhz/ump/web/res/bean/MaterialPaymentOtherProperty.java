/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-21
 */
public class MaterialPaymentOtherProperty {
	
	/**
	 * 施工单位
	 */
	private String constructionUnit;
	
	/**
	 * 已累计
	 */
	private String receiveAmountBefore;
	
	/**
	 * 本次
	 */
	private String receiveAmountThis;

	/**
	 * 小计
	 */
	private String receiveAmountTotal;

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getReceiveAmountBefore() {
		return receiveAmountBefore;
	}

	public void setReceiveAmountBefore(String receiveAmountBefore) {
		this.receiveAmountBefore = receiveAmountBefore;
	}

	public String getReceiveAmountThis() {
		return receiveAmountThis;
	}

	public void setReceiveAmountThis(String receiveAmountThis) {
		this.receiveAmountThis = receiveAmountThis;
	}

	public String getReceiveAmountTotal() {
		return receiveAmountTotal;
	}

	public void setReceiveAmountTotal(String receiveAmountTotal) {
		this.receiveAmountTotal = receiveAmountTotal;
	}

}
