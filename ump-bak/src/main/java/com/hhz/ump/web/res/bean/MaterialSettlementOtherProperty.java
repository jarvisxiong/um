/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-21
 */
public class MaterialSettlementOtherProperty {
	
	/**
	 * 领货单位名称
	 */
	private String receiveUnitName;
	
	/**
	 * 相应合同编号
	 */
	private String receiveContNo;
	
	/**
	 * 金额
	 */
	private String receiveAmount;

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceiveContNo() {
		return receiveContNo;
	}

	public void setReceiveContNo(String receiveContNo) {
		this.receiveContNo = receiveContNo;
	}

	public String getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(String receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

}
