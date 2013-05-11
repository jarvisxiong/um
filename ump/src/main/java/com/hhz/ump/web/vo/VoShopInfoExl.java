package com.hhz.ump.web.vo;

import com.hhz.ump.entity.bis.BisShop;


public class VoShopInfoExl extends BisShop{
	private String no;//序号
	private String sortName;//类别
	private String nameCn;//品牌中文
	private String nameEn;//品牌英文
	private String brandLevelCd;//品牌级别
	private String brandCradleland;//品牌发源地
	private String brandDesc;//品牌简介
	
	private String companyName;//公司名称
	private String hqAddr;//公司地址
	private String postCode;//邮编
	private String principal;//联系人
	private String principalPos;//职务
	private String principalPhone;//公司电话
	private String principalTel;//手机
	private String fax;//传真
	private String cooperatedShop;//已合作门店
	private String cooperatedShop1;//已合作门店
	private String toCooperateShop;//一般合作条件
	private String remark;//备注
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@Override
	public String getNameCn() {
		return nameCn;
	}
	@Override
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	@Override
	public String getNameEn() {
		return nameEn;
	}
	@Override
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	@Override
	public String getBrandLevelCd() {
		return brandLevelCd;
	}
	@Override
	public void setBrandLevelCd(String brandLevelCd) {
		this.brandLevelCd = brandLevelCd;
	}

	@Override
	public String getBrandCradleland() {
		return brandCradleland;
	}
	@Override
	public void setBrandCradleland(String brandCradleland) {
		this.brandCradleland = brandCradleland;
	}
	@Override
	public String getBrandDesc() {
		return brandDesc;
	}
	@Override
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	@Override
	public String getCompanyName() {
		return companyName;
	}
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String getHqAddr() {
		return hqAddr;
	}
	@Override
	public void setHqAddr(String hqAddr) {
		this.hqAddr = hqAddr;
	}
	@Override
	public String getPostCode() {
		return postCode;
	}
	@Override
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Override
	public String getPrincipal() {
		return principal;
	}
	@Override
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Override
	public String getPrincipalPos() {
		return principalPos;
	}
	@Override
	public void setPrincipalPos(String principalPos) {
		this.principalPos = principalPos;
	}
	@Override
	public String getPrincipalPhone() {
		return principalPhone;
	}
	@Override
	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}
	@Override
	public String getPrincipalTel() {
		return principalTel;
	}
	@Override
	public void setPrincipalTel(String principalTel) {
		this.principalTel = principalTel;
	}
	@Override
	public String getFax() {
		return fax;
	}
	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Override
	public String getCooperatedShop() {
		return cooperatedShop;
	}
	@Override
	public void setCooperatedShop(String cooperatedShop) {
		this.cooperatedShop = cooperatedShop;
	}
	public String getCooperatedShop1() {
		return cooperatedShop1;
	}
	public void setCooperatedShop1(String cooperatedShop1) {
		this.cooperatedShop1 = cooperatedShop1;
	}
	@Override
	public String getToCooperateShop() {
		return toCooperateShop;
	}
	@Override
	public void setToCooperateShop(String toCooperateShop) {
		this.toCooperateShop = toCooperateShop;
	}
	@Override
	public String getRemark() {
		return remark;
	}
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	
}

