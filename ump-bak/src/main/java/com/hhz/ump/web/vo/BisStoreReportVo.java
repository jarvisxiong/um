package com.hhz.ump.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BisStoreReportVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tentantId;
	private String shopSort;
	private String tentantName;
	private String storeName;
	private BigDecimal shopArea;
	private List<BisReportTypeDetailVo> detailVoList = new ArrayList<BisReportTypeDetailVo>();

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

	public List<BisReportTypeDetailVo> getDetailVoList() {
		return detailVoList;
	}

	public void setDetailVoList(List<BisReportTypeDetailVo> detailVoList) {
		this.detailVoList = detailVoList;
	}
}
