package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 材料设备批定价格审批表（甲定乙供）
 * @author baolm
 *
 * 2011-04-13
 */
public class MaterialsEquipmentPriceApprove extends BaseTemplate {
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 甲方
	 */
	private String sideA;
	
	/**
	 * 乙方
	 */
	private String sideB;
	
	/**
	 * □甲定乙供
	 */
	private String provideB="true";
	
	/**
	 * □甲供合同增补
	 */
	private String provideA;
	
	/**
	 * 材料名称
	 */
	private String equipMaterialName;
	
	/**
	 * 进场时间
	 */
	private String approachDate;
	
	/**
	 * 原合同价
	 */
	private String oriContractPrice;
	
	/**
	 * 上报总价
	 */
	private String reportTotalPrice;
	
	/**
	 * 备注：增补原因说明
	 */
	private String addReason;
	
	/**
	 * 原合同约定的品牌
	 */
	private String oriContractBrand;
	
	/**
	 * 原合同单价
	 */
	private String oriContractUnitPrice;
	
	/**
	 * 原合同总价
	 */
	private String oriContractTotalPrice;
	
	/**
	 * 推荐品牌
	 */
	private String commendBrand;
	
	/**
	 * 推荐品牌单价
	 */
	private String commendBrandUnitPrice;
	
	/**
	 * 推荐品牌总价
	 */
	private String commendBrandTotalPrice;
	
	/**
	 * 备注：需批定价格原因说明
	 */
	private String approvePriceReason;
	
	/**
	 * 备注：推荐改品牌的理由
	 */
	private String commendBrandReason;
	
	/**
	 * 材料数量清单
	 */
	private String meterialQuantityListId;
	
	/**
	 * 乙方报价清单
	 */
	private String sideBPriceListId;
	
	/**
	 * 技术参数要求（非比传项）
	 */
	private String teckParamRequireId;
	
	/**
	 * 相关图纸（非比传项）
	 */
	private String relatedDrawingId;
	
	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSideA() {
		return sideA;
	}

	public void setSideA(String sideA) {
		this.sideA = sideA;
	}

	public String getSideB() {
		return sideB;
	}

	public void setSideB(String sideB) {
		this.sideB = sideB;
	}

	public String getProvideB() {
		return provideB;
	}

	public void setProvideB(String provideB) {
		this.provideB = provideB;
	}

	public String getProvideA() {
		return provideA;
	}

	public void setProvideA(String provideA) {
		this.provideA = provideA;
	}

	public String getEquipMaterialName() {
		return equipMaterialName;
	}

	public void setEquipMaterialName(String equipMaterialName) {
		this.equipMaterialName = equipMaterialName;
	}

	public String getApproachDate() {
		return approachDate;
	}

	public void setApproachDate(String approachDate) {
		this.approachDate = approachDate;
	}

	public String getOriContractBrand() {
		return oriContractBrand;
	}

	public void setOriContractBrand(String oriContractBrand) {
		this.oriContractBrand = oriContractBrand;
	}

	public String getOriContractUnitPrice() {
		return oriContractUnitPrice;
	}

	public void setOriContractUnitPrice(String oriContractUnitPrice) {
		this.oriContractUnitPrice = oriContractUnitPrice;
	}

	public String getOriContractTotalPrice() {
		return oriContractTotalPrice;
	}

	public void setOriContractTotalPrice(String oriContractTotalPrice) {
		this.oriContractTotalPrice = oriContractTotalPrice;
	}

	public String getCommendBrand() {
		return commendBrand;
	}

	public void setCommendBrand(String commendBrand) {
		this.commendBrand = commendBrand;
	}

	public String getCommendBrandUnitPrice() {
		return commendBrandUnitPrice;
	}

	public void setCommendBrandUnitPrice(String commendBrandUnitPrice) {
		this.commendBrandUnitPrice = commendBrandUnitPrice;
	}

	public String getCommendBrandTotalPrice() {
		return commendBrandTotalPrice;
	}

	public void setCommendBrandTotalPrice(String commendBrandTotalPrice) {
		this.commendBrandTotalPrice = commendBrandTotalPrice;
	}

	public String getApprovePriceReason() {
		return approvePriceReason;
	}

	public void setApprovePriceReason(String approvePriceReason) {
		this.approvePriceReason = approvePriceReason;
	}

	public String getCommendBrandReason() {
		return commendBrandReason;
	}

	public void setCommendBrandReason(String commendBrandReason) {
		this.commendBrandReason = commendBrandReason;
	}

	public String getMeterialQuantityListId() {
		return meterialQuantityListId;
	}

	public void setMeterialQuantityListId(String meterialQuantityListId) {
		this.meterialQuantityListId = meterialQuantityListId;
	}

	public String getSideBPriceListId() {
		return sideBPriceListId;
	}

	public void setSideBPriceListId(String sideBPriceListId) {
		this.sideBPriceListId = sideBPriceListId;
	}

	public String getTeckParamRequireId() {
		return teckParamRequireId;
	}

	public void setTeckParamRequireId(String teckParamRequireId) {
		this.teckParamRequireId = teckParamRequireId;
	}

	public String getRelatedDrawingId() {
		return relatedDrawingId;
	}

	public void setRelatedDrawingId(String relatedDrawingId) {
		this.relatedDrawingId = relatedDrawingId;
	}

	public String getOriContractPrice() {
		return oriContractPrice;
	}

	public void setOriContractPrice(String oriContractPrice) {
		this.oriContractPrice = oriContractPrice;
	}

	public String getReportTotalPrice() {
		return reportTotalPrice;
	}

	public void setReportTotalPrice(String reportTotalPrice) {
		this.reportTotalPrice = reportTotalPrice;
	}

	public String getAddReason() {
		return addReason;
	}

	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		return engineeringName;
	}

}
