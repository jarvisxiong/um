package com.hhz.ump.entity.bis;

import java.math.BigDecimal;
import java.util.Date;


public class ShopStoreVo implements java.io.Serializable{

	private String bisShopId;
	private String bisFloorId;
	private String bisTenantId;
    private String nameEn;
    private String nameCn;//租户名称【中文】
    private String manageCd;//经营性质（主力店等）
    private String shopTypeCd;//租户性质
    private Date rentStartDate;//租赁开始时间
    private Date rentEndDate;//租赁结束时间
    private String floorNum;//楼层号
    private String buildingNum;//楼号
    private String storeNo;//商铺编号  
    private String uniqueId;//楼号   
    private String layoutCd;//业态
    private String equityNature;	//产权性质
    private String statusCd;		//欠费标志

	private BigDecimal powerSquare ;//产权面积
    private BigDecimal factSquare ;//实际租赁面积
    
    public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getBisShopId() {
		return bisShopId;
	}
	public void setBisShopId(String bisShopId) {
		this.bisShopId = bisShopId;
	}
	public String getBisFloorId() {
		return bisFloorId;
	}
	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}
	public String getBisTenantId() {
		return bisTenantId;
	}
	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getManageCd() {
		return manageCd;
	}
	public void setManageCd(String manageCd) {
		this.manageCd = manageCd;
	}
	public String getShopTypeCd() {
		return shopTypeCd;
	}
	public void setShopTypeCd(String shopTypeCd) {
		this.shopTypeCd = shopTypeCd;
	}
	public Date getRentStartDate() {
		return rentStartDate;
	}
	public void setRentStartDate(Date rentStartDate) {
		this.rentStartDate = rentStartDate;
	}
	public Date getRentEndDate() {
		return rentEndDate;
	}
	public void setRentEndDate(Date rentEndDate) {
		this.rentEndDate = rentEndDate;
	}
	public String getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public BigDecimal getPowerSquare() {
		return powerSquare;
	}
	public void setPowerSquare(BigDecimal powerSquare) {
		this.powerSquare = powerSquare;
	}
	public BigDecimal getFactSquare() {
		return factSquare;
	}
	public void setFactSquare(BigDecimal factSquare) {
		this.factSquare = factSquare;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getLayoutCd() {
		return layoutCd;
	}
	public void setLayoutCd(String layoutCd) {
		this.layoutCd = layoutCd;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getEquityNature() {
		return equityNature;
	}
	public void setEquityNature(String equityNature) {
		this.equityNature = equityNature;
	}
	
}