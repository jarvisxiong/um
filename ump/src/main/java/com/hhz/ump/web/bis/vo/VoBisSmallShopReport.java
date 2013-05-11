package com.hhz.ump.web.bis.vo;

public class VoBisSmallShopReport {
	/**
	 * 主键编号
	 */
	private String bisSmallShopReportId;
	/**
	 * 开始日期
	 */
	private String startDay;
	/**
	 * 结束日期
	 */
	private String endDay;
	/**
	 * 项目编号
	 */
	private String bisProjectId;
	/**
	 * 项目类型编号
	 */
	private String bisItemCategoryId;
	/**
	 * 项目类型名称
	 */
	private String bisItemCategoryName;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 城市名称
	 */
	private String city;
	/***
	 * 2012年招商面积
	 */
	private String merchantsArea2012;
	/***
	 * 总招商面积
	 */
	private String merchantsArea;
	/**
	 * 该区域本周新增签约面积
	 */
	private String contractAreaNew;
	/**
	 * 总签约面积
	 */
	private String contractArea;
	/**
	 * 该区域签约率
	 */
	private String signingRate;
	/**
	 * 本月计划签约率
	 */
	private String planSigningRate;
	/**
	 * 小商铺开业率
	 */
	private String openedRateShop;
	/**
	 * 总开业率
	 */
	private String openedRate;
	/**
	 * 已收对方盖章合同即将完成招商面积
	 */
	private String investmentArea;
	/**
	 * 小商铺状态
	 */
	private String smallShopStatus;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 负责人签字
	 */
	private String headerSign;

	public VoBisSmallShopReport() {
		super();
	}

	public String getBisSmallShopReportId() {
		return bisSmallShopReportId;
	}

	public void setBisSmallShopReportId(String bisSmallShopReportId) {
		this.bisSmallShopReportId = bisSmallShopReportId;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisItemCategoryId() {
		return bisItemCategoryId;
	}

	public void setBisItemCategoryId(String bisItemCategoryId) {
		this.bisItemCategoryId = bisItemCategoryId;
	}

	public String getBisItemCategoryName() {
		return bisItemCategoryName;
	}

	public void setBisItemCategoryName(String bisItemCategoryName) {
		this.bisItemCategoryName = bisItemCategoryName;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMerchantsArea2012() {
		return merchantsArea2012;
	}

	public void setMerchantsArea2012(String merchantsArea2012) {
		this.merchantsArea2012 = merchantsArea2012;
	}

	public String getMerchantsArea() {
		return merchantsArea;
	}

	public void setMerchantsArea(String merchantsArea) {
		this.merchantsArea = merchantsArea;
	}

	public String getContractAreaNew() {
		return contractAreaNew;
	}

	public void setContractAreaNew(String contractAreaNew) {
		this.contractAreaNew = contractAreaNew;
	}

	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public String getSigningRate() {
		return signingRate;
	}

	public void setSigningRate(String signingRate) {
		this.signingRate = signingRate;
	}

	public String getPlanSigningRate() {
		return planSigningRate;
	}

	public void setPlanSigningRate(String planSigningRate) {
		this.planSigningRate = planSigningRate;
	}

	public String getOpenedRateShop() {
		return openedRateShop;
	}

	public void setOpenedRateShop(String openedRateShop) {
		this.openedRateShop = openedRateShop;
	}

	public String getOpenedRate() {
		return openedRate;
	}

	public void setOpenedRate(String openedRate) {
		this.openedRate = openedRate;
	}

	public String getInvestmentArea() {
		return investmentArea;
	}

	public void setInvestmentArea(String investmentArea) {
		this.investmentArea = investmentArea;
	}

	public String getSmallShopStatus() {
		return smallShopStatus;
	}

	public void setSmallShopStatus(String smallShopStatus) {
		this.smallShopStatus = smallShopStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHeaderSign() {
		return headerSign;
	}

	public void setHeaderSign(String headerSign) {
		this.headerSign = headerSign;
	}

}
