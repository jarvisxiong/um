package com.hhz.ump.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BisShopTenantUtil {
  private String tentantId;
  private String shopSort;
  private String tentantName;
  private String storeName;
  private BigDecimal shopArea;
  private List<TenantTypeReportUtil> tenantTypeReportUtils =new ArrayList<TenantTypeReportUtil>();
  
	public String getShopSort() {
		return shopSort;
	}
	public void setShopSort(String shopSort) {
		this.shopSort = shopSort;
	}
	public String getTentantName() {
		return tentantName;
	}
	public void setTentantName(String tentantName) {
		this.tentantName = tentantName;
	}
	public List<TenantTypeReportUtil> getTenantTypeReportUtils() {
		return tenantTypeReportUtils;
	}
	public void setTenantTypeReportUtils(
			List<TenantTypeReportUtil> tenantTypeReportUtils) {
		this.tenantTypeReportUtils = tenantTypeReportUtils;
	}
	public BigDecimal getShopArea() {
		return shopArea;
	}
	public void setShopArea(BigDecimal shopArea) {
		this.shopArea = shopArea;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getTentantId() {
		return tentantId;
	}
	public void setTentantId(String tentantId) {
		this.tentantId = tentantId;
	}
}
