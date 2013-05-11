package com.hhz.ump.web.vo;

import com.hhz.ump.entity.bis.BisTenant;

public class BisTenantVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8954905852304892518L;
	
	private BisTenant bisTenant;
	private String shopName;
	private String shopConnName;
	private String bisStoreIds;
	private String bisStoreNos;
	
	public BisTenant getBisTenant() {
		return bisTenant;
	}
	public void setBisTenant(BisTenant bisTenant) {
		this.bisTenant = bisTenant;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopConnName() {
		return shopConnName;
	}
	public void setShopConnName(String shopConnName) {
		this.shopConnName = shopConnName;
	}
	public String getBisStoreIds() {
		return bisStoreIds;
	}
	public void setBisStoreIds(String bisStoreIds) {
		this.bisStoreIds = bisStoreIds;
	}
	public String getBisStoreNos() {
		return bisStoreNos;
	}
	public void setBisStoreNos(String bisStoreNos) {
		this.bisStoreNos = bisStoreNos;
	}
	
	
	
}
