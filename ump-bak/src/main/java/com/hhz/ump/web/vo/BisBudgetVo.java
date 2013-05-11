package com.hhz.ump.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BisBudgetVo implements Serializable{

	 /**
	 * 
	 */
	 private static final long serialVersionUID = -6331483572083214961L;
	 private String bisBudgetId;
     private BigDecimal multiTotal;
     private BigDecimal inviteMerchant;
     private BigDecimal payTotal;
     private BigDecimal incomeTotal;
     private BigDecimal bisProfit;    //营业利润BIS_PROFIT
     private BigDecimal profitTotal; //利润总额PROFIT_TOTAL
     private BigDecimal saleChargeTotal; //销售费用
     private BigDecimal netProfit; //净利润
     private BigDecimal carParking;//停车场收入
     private BigDecimal otherPay; //其他费用
     private BigDecimal welfare; //人工福利费
     private BigDecimal propManage; //公寓物业管理费
     
	public String getBisBudgetId() {
		return bisBudgetId; 
	}
	public void setBisBudgetId(String bisBudgetId) {
		this.bisBudgetId = bisBudgetId;
	}
	public BigDecimal getMultiTotal() {
		return multiTotal;
	}
	public void setMultiTotal(BigDecimal multiTotal) {
		this.multiTotal = multiTotal;
	}
	public BigDecimal getInviteMerchant() {
		return inviteMerchant;
	}
	public void setInviteMerchant(BigDecimal inviteMerchant) {
		this.inviteMerchant = inviteMerchant;
	}
	public BigDecimal getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}
	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	public BigDecimal getBisProfit() {
		return bisProfit;
	}
	public void setBisProfit(BigDecimal bisProfit) {
		this.bisProfit = bisProfit;
	}
	public BigDecimal getProfitTotal() {
		return profitTotal;
	}
	public void setProfitTotal(BigDecimal profitTotal) {
		this.profitTotal = profitTotal;
	}
	public BigDecimal getSaleChargeTotal() {
		return saleChargeTotal;
	}
	public void setSaleChargeTotal(BigDecimal saleChargeTotal) {
		this.saleChargeTotal = saleChargeTotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}
	
	public BigDecimal getCarParking() {
		return carParking;
	}
	public void setCarParking(BigDecimal carParking) {
		this.carParking = carParking;
	}
	
	public BigDecimal getOtherPay() {
		return otherPay;
	}
	public void setOtherPay(BigDecimal otherPay) {
		this.otherPay = otherPay;
	}
	
	public BigDecimal getWelfare() {
		return welfare;
	}
	public void setWelfare(BigDecimal welfare) {
		this.welfare = welfare;
	}
	
	public BigDecimal getPropManage() {
		return propManage;
	}
	public void setPropManage(BigDecimal propManage) {
		this.propManage = propManage;
	}
	@Override
	public String toString() {
		return "BisBudgetVo [bisBudgetId=" + bisBudgetId + ", bisProfit="
				+ bisProfit + ", incomeTotal=" + incomeTotal
				+ ", inviteMerchant=" + inviteMerchant + ", multiTotal="
				+ multiTotal + ", payTotal=" + payTotal + ", profitTotal="
				+ profitTotal + ", saleChargeTotal=" + saleChargeTotal + "]";
	}
     
}
