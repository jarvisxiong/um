package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/*
 * 销售价格审批表
 */
public class SalePriceApp extends BaseTemplate {
	//项目名称
	private String projectName;
	//物业范围
	private String tenementRange;
	//价格类别:住宅
	private String pricTypeHouse;
	//价格类别：酒店式公寓
	private String pricTypeFlat;
	//价格类别：城市广场商业
	private String pricTypeBusiness;
	//价格类别：住宅底商
	private String pricTypeMultiple;
	//定价阶段：初次定价
	private String priceMomentFirst;
	//定价阶段：修订价格
	private String priceMomentSeveral;
	//第几次修订
	private String several;
	//定价阶段：房源再售
	private String priceMomentResell;
	//房源再售：价格上调
	private String upPrice;
	//房源再售：价格不变
	private String samePrice;
	//价格标准
	private String priceStandard;
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPriceStandard() {
		return priceStandard;
	}

	public void setPriceStandard(String priceStandard) {
		this.priceStandard = priceStandard;
	}

	public String getTenementRange() {
		return tenementRange;
	}

	public void setTenementRange(String tenementRange) {
		this.tenementRange = tenementRange;
	}

	public String getPricTypeHouse() {
		return pricTypeHouse;
	}

	public void setPricTypeHouse(String pricTypeHouse) {
		this.pricTypeHouse = pricTypeHouse;
	}

	public String getPricTypeFlat() {
		return pricTypeFlat;
	}

	public void setPricTypeFlat(String pricTypeFlat) {
		this.pricTypeFlat = pricTypeFlat;
	}

	public String getPricTypeBusiness() {
		return pricTypeBusiness;
	}

	public void setPricTypeBusiness(String pricTypeBusiness) {
		this.pricTypeBusiness = pricTypeBusiness;
	}

	public String getPricTypeMultiple() {
		return pricTypeMultiple;
	}

	public void setPricTypeMultiple(String pricTypeMultiple) {
		this.pricTypeMultiple = pricTypeMultiple;
	}

	public String getPriceMomentFirst() {
		return priceMomentFirst;
	}

	public void setPriceMomentFirst(String priceMomentFirst) {
		this.priceMomentFirst = priceMomentFirst;
	}

	public String getPriceMomentSeveral() {
		return priceMomentSeveral;
	}

	public void setPriceMomentSeveral(String priceMomentSeveral) {
		this.priceMomentSeveral = priceMomentSeveral;
	}

	public String getSeveral() {
		return several;
	}

	public void setSeveral(String several) {
		this.several = several;
	}

	public String getPriceMomentResell() {
		return priceMomentResell;
	}

	public void setPriceMomentResell(String priceMomentResell) {
		this.priceMomentResell = priceMomentResell;
	}

	public String getUpPrice() {
		return upPrice;
	}

	public void setUpPrice(String upPrice) {
		this.upPrice = upPrice;
	}

	public String getSamePrice() {
		return samePrice;
	}

	public void setSamePrice(String samePrice) {
		this.samePrice = samePrice;
	}

}
