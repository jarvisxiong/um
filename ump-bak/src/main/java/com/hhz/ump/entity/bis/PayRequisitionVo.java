package com.hhz.ump.entity.bis;

import java.math.BigDecimal;
import java.util.Date;

public class PayRequisitionVo {
	/**
	 * 缴费通知单
	 */
	private String bisProjectId;
	private String bisTenantId;
	private String bisContId;
	private String shopName;//商家名称 搜索条件
	private String storeNo;//商铺编号 搜索条件
	private String year;//搜索条件
	private String month;// 搜索条件
	private String yearMonth;
	private BigDecimal  rowno;//总记录数
	private BigDecimal  maxrowno;//最大记录
	private BigDecimal  pageNo;//当前页数
	private BigDecimal  pageSize;//当前页数
	private BigDecimal square;
	private String storeNos;
	private Date mustDate;
	private String floorNum;//
	private String chargeType1 = "4";//物业费
	private String chargeType2 = "7";//公摊费
	private String chargeType3 = "8";//空调费
	private String chargeType4 = "6";//电费
	private String chargeType5 = "5";//水费
	private BigDecimal chargeType1Must = new BigDecimal(0);//物业
	private BigDecimal chargeType2Must = new BigDecimal(0);//综合管理费
	private BigDecimal chargeType3Must = new BigDecimal(0);//公摊费
	private BigDecimal chargeType4Must = new BigDecimal(0);//空调费
	private BigDecimal chargeType5Must = new BigDecimal(0);//水费
	private BigDecimal chargeType6Must = new BigDecimal(0);//电费
	/**
	 * 尚欠费用
	 */
	private BigDecimal chargeType1Fact = new BigDecimal(0);//物业
	private BigDecimal chargeType2Fact = new BigDecimal(0);//综合管理费
	private BigDecimal chargeType3Fact = new BigDecimal(0);//公摊费
	private BigDecimal chargeType4Fact = new BigDecimal(0);//空调费
	private BigDecimal chargeType5Fact = new BigDecimal(0);//水费
	private BigDecimal chargeType6Fact = new BigDecimal(0);//电费
	private BigDecimal totalNum;
	private String chargetTypeMoney;
	public String getBisProjectId() {
		return bisProjectId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	public String getBisTenantId() {
		return bisTenantId;
	}
	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}
	public String getStoreNos() {
		return storeNos;
	}
	public void setStoreNos(String storeNos) {
		this.storeNos = storeNos;
	}
	public Date getMustDate() {
		return mustDate;
	}
	public void setMustDate(Date mustDate) {
		this.mustDate = mustDate;
	}
	public String getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getRowno() {
		return rowno;
	}
	public void setRowno(BigDecimal rowno) {
		this.rowno = rowno;
	}
	public BigDecimal getMaxrowno() {
		return maxrowno;
	}
	public void setMaxrowno(BigDecimal maxrowno) {
		this.maxrowno = maxrowno;
	}
	public BigDecimal getPageNo() {
		return pageNo;
	}
	public void setPageNo(BigDecimal pageNo) {
		this.pageNo = pageNo;
	}
	public BigDecimal getChargeType1Must() {
		return chargeType1Must;
	}
	public void setChargeType1Must(BigDecimal chargeType1Must) {
		this.chargeType1Must = chargeType1Must;
	}
	public BigDecimal getChargeType2Must() {
		return chargeType2Must;
	}
	public void setChargeType2Must(BigDecimal chargeType2Must) {
		this.chargeType2Must = chargeType2Must;
	}
	public BigDecimal getChargeType3Must() {
		return chargeType3Must;
	}
	public void setChargeType3Must(BigDecimal chargeType3Must) {
		this.chargeType3Must = chargeType3Must;
	}
	public BigDecimal getChargeType4Must() {
		return chargeType4Must;
	}
	public void setChargeType4Must(BigDecimal chargeType4Must) {
		this.chargeType4Must = chargeType4Must;
	}
	public BigDecimal getChargeType5Must() {
		return chargeType5Must;
	}
	public void setChargeType5Must(BigDecimal chargeType5Must) {
		this.chargeType5Must = chargeType5Must;
	}
	public BigDecimal getChargeType6Must() {
		return chargeType6Must;
	}
	public void setChargeType6Must(BigDecimal chargeType6Must) {
		this.chargeType6Must = chargeType6Must;
	}
	public BigDecimal getChargeType1Fact() {
		return chargeType1Fact;
	}
	public void setChargeType1Fact(BigDecimal chargeType1Fact) {
		this.chargeType1Fact = chargeType1Fact;
	}
	public BigDecimal getChargeType2Fact() {
		return chargeType2Fact;
	}
	public void setChargeType2Fact(BigDecimal chargeType2Fact) {
		this.chargeType2Fact = chargeType2Fact;
	}
	public BigDecimal getChargeType3Fact() {
		return chargeType3Fact;
	}
	public void setChargeType3Fact(BigDecimal chargeType3Fact) {
		this.chargeType3Fact = chargeType3Fact;
	}
	public BigDecimal getChargeType4Fact() {
		return chargeType4Fact;
	}
	public void setChargeType4Fact(BigDecimal chargeType4Fact) {
		this.chargeType4Fact = chargeType4Fact;
	}
	public BigDecimal getChargeType5Fact() {
		return chargeType5Fact;
	}
	public void setChargeType5Fact(BigDecimal chargeType5Fact) {
		this.chargeType5Fact = chargeType5Fact;
	}
	public BigDecimal getChargeType6Fact() {
		return chargeType6Fact;
	}
	public void setChargeType6Fact(BigDecimal chargeType6Fact) {
		this.chargeType6Fact = chargeType6Fact;
	}
	public BigDecimal getPageSize() {
		return pageSize;
	}
	public void setPageSize(BigDecimal pageSize) {
		this.pageSize = pageSize;
	}
	public String getBisContId() {
		return bisContId;
	}
	public void setBisContId(String bisContId) {
		this.bisContId = bisContId;
	}
	public String getChargetTypeMoney() {
		return chargetTypeMoney;
	}
	public void setChargetTypeMoney(String chargetTypeMoney) {
		this.chargetTypeMoney = chargetTypeMoney;
	}
	public BigDecimal getSquare() {
		return square;
	}
	public void setSquare(BigDecimal square) {
		this.square = square;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
}

