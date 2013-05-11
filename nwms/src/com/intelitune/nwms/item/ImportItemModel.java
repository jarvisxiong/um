package com.intelitune.nwms.item;

public class ImportItemModel {
	private String mcode;
	private String invoiceNo;
	private String po;
	private String itemTypeCode;
	private int number;
	private float unitPrice;
	private float unitWeight;
	private String currency;
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getItemTypeCode() {
		return itemTypeCode;
	}
	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(float unitWeight) {
		this.unitWeight = unitWeight;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public ImportItemModel(){
		
	}
	
	public ImportItemModel(String mcode, String invoiceNo, String po,
			String itemTypeCode, int number, float unitPrice, float unitWeight,
			String currency) {
		super();
		this.mcode = mcode;
		this.invoiceNo = invoiceNo;
		this.po = po;
		this.itemTypeCode = itemTypeCode;
		this.number = number;
		this.unitPrice = unitPrice;
		this.unitWeight = unitWeight;
		this.currency = currency;
	}
	
	
	
}
