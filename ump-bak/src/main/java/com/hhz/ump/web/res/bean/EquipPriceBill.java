package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

public class EquipPriceBill extends BaseTemplatePay {

	private String projectName;//项目名称
	private String projectCd;
	private String contactName;//合同名称（合同编号）

	private String partA;//甲方
	private String partB;//乙方
	private String partC;//丙方-Add by zhuxj on 2012.3.31
	private String partBName;
	
	private String priceKind1;//甲定乙供 
	private String priceKind2;//甲供合同增补

	private String materialName;//材料名称
	private String useCoverage;//使用区域

	private String purchasePreiod;//采购周期
	private String enterDate;//进场时间

	private String oriMaterialBrand;//原合同约定的材料品牌
	private String oriPrice;//原合同暂定价
	private String recommendProduct;//推荐品牌
	private String curPrice;//现报价（总价）
	private String standardCount;//规格/数量
	private String payType;//付款方式
	private String remark;//备注

	private String materialListFileId;//材料清单或图纸
	private String techParaFileId;//相关技术参数要求
	private String offerPriceFileId;//乙方报价清单

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getPartBName() {
		return partBName;
	}

	public void setPartBName(String partBName) {
		this.partBName = partBName;
	}
	
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getUseCoverage() {
		return useCoverage;
	}

	public void setUseCoverage(String useCoverage) {
		this.useCoverage = useCoverage;
	}

	public String getPurchasePreiod() {
		return purchasePreiod;
	}

	public void setPurchasePreiod(String purchasePreiod) {
		this.purchasePreiod = purchasePreiod;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}


	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		if (contactName!=null)
			return contactName+"合同";
		return null;
	}

	public String getPriceKind1() {
		return priceKind1;
	}

	public void setPriceKind1(String priceKind1) {
		this.priceKind1 = priceKind1;
	}

	public String getPriceKind2() {
		return priceKind2;
	}

	public void setPriceKind2(String priceKind2) {
		this.priceKind2 = priceKind2;
	}

	public String getOriMaterialBrand() {
		return oriMaterialBrand;
	}

	public void setOriMaterialBrand(String oriMaterialBrand) {
		this.oriMaterialBrand = oriMaterialBrand;
	}

	public String getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(String oriPrice) {
		this.oriPrice = oriPrice;
	}

	public String getRecommendProduct() {
		return recommendProduct;
	}

	public void setRecommendProduct(String recommendProduct) {
		this.recommendProduct = recommendProduct;
	}

	public String getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}

	public String getStandardCount() {
		return standardCount;
	}

	public void setStandardCount(String standardCount) {
		this.standardCount = standardCount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaterialListFileId() {
		return materialListFileId;
	}

	public void setMaterialListFileId(String materialListFileId) {
		this.materialListFileId = materialListFileId;
	}

	public String getTechParaFileId() {
		return techParaFileId;
	}

	public void setTechParaFileId(String techParaFileId) {
		this.techParaFileId = techParaFileId;
	}

	public String getOfferPriceFileId() {
		return offerPriceFileId;
	}

	public void setOfferPriceFileId(String offerPriceFileId) {
		this.offerPriceFileId = offerPriceFileId;
	}
}
