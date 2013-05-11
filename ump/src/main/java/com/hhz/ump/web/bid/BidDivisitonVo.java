package com.hhz.ump.web.bid;


import java.util.List;

import com.hhz.ump.entity.bid.BidDivisionSupRel;
import com.hhz.ump.entity.bid.BidDivisiton;

public class BidDivisitonVo implements java.io.Serializable{
	
	
	//项目编号
	private String itemNo;
	//项目名称
	private String itemName;
	//项目描述
	private String itemDesc;
	//单位
	private String measurement;
	//供应商数
	private Integer supNumber;
	//轮次
	private Integer batchNo;
	//各个供应商数据
	private List<BidSupCompareVo> bidSupCompareVos;
	//工程量
	 private String quantitie;
	 //单价
     private String compUnitAmt;
     //合价
     private String totalAmt;
	
	
	BidDivisiton bidDivisiton;
	BidDivisionSupRel bidDivisionSupRel;
	public BidDivisiton getBidDivisiton() {
		return bidDivisiton;
	}
	public void setBidDivisiton(BidDivisiton bidDivisiton) {
		this.bidDivisiton = bidDivisiton;
	}
	public BidDivisionSupRel getBidDivisionSupRel() {
		return bidDivisionSupRel;
	}
	public void setBidDivisionSupRel(BidDivisionSupRel bidDivisionSupRel) {
		this.bidDivisionSupRel = bidDivisionSupRel;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public Integer getSupNumber() {
		return supNumber;
	}
	public void setSupNumber(Integer supNumber) {
		this.supNumber = supNumber;
	}
	public List<BidSupCompareVo> getBidSupCompareVos() {
		return bidSupCompareVos;
	}
	public void setBidSupCompareVos(List<BidSupCompareVo> bidSupCompareVos) {
		this.bidSupCompareVos = bidSupCompareVos;
	}
	public Integer getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}
	public String getQuantitie() {
		return quantitie;
	}
	public void setQuantitie(String quantitie) {
		this.quantitie = quantitie;
	}
	public String getCompUnitAmt() {
		return compUnitAmt;
	}
	public void setCompUnitAmt(String compUnitAmt) {
		this.compUnitAmt = compUnitAmt;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	
	
	
	
	
	
	
	

}
