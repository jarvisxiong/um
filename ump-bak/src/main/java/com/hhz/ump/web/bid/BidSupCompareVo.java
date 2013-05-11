package com.hhz.ump.web.bid;

import java.math.BigDecimal;


public class BidSupCompareVo  implements java.io.Serializable {

	private String supId;
	private String supName;
	private String headName;
	private String headType;
	private BigDecimal quantitie;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	
	
	
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getHeadType() {
		return headType;
	}
	public void setHeadType(String headType) {
		this.headType = headType;
	}
	public BigDecimal getQuantitie() {
		return quantitie;
	}
	public void setQuantitie(BigDecimal quantitie) {
		this.quantitie = quantitie;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	
	
	
	

}
