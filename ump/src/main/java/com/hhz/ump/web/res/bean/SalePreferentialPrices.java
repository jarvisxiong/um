package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/*
 * 销售价格优惠审批表
 */
public class SalePreferentialPrices extends BaseTemplatePay {
	//项目名称
	private String projectName;
	//客户姓名
	private String customerName;
	//联系人
	private String linkman;
	//联系地址
	private String contactAddr;
	//联系电话
	private String contactTel;
	//物业范围:商业销售
	private String commerceSale;
	//物业范围:住宅销售
	private String houseSale;
	//物业范围:酒店式公寓
	private String flatSale;
	//客户类别：关系户
	private String relation;
	//客户类别:大客户
	private String heavyBuyer;
	//付款方式:一次性付款
	private String payForOne;
	//按揭付款
	private String mortgagePay;
	//分期付款
	private String instalment;
	//申请折扣
	private String discount;
	//销售状态：销控
	private String saleControl;
	//销售状态：未售
	private String willSale;
	//销售状态：认购
	private String subscribe;
	//购买用途：自营
	private String selfSupport;
	//购买用途：投资
	private String investment;
	//申购房源明细
	private String saleDetail;
	//面积
	private String area;
	//调整前单价
	private String price1;
	//调整前总价
	private String totalPrice1;
	//调整后单价
	private String price2;
	//调整后总价
	private String totalPrice2;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getCommerceSale() {
		return commerceSale;
	}

	public void setCommerceSale(String commerceSale) {
		this.commerceSale = commerceSale;
	}

	public String getHouseSale() {
		return houseSale;
	}

	public void setHouseSale(String houseSale) {
		this.houseSale = houseSale;
	}

	public String getFlatSale() {
		return flatSale;
	}

	public void setFlatSale(String flatSale) {
		this.flatSale = flatSale;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getHeavyBuyer() {
		return heavyBuyer;
	}

	public void setHeavyBuyer(String heavyBuyer) {
		this.heavyBuyer = heavyBuyer;
	}

	public String getPayForOne() {
		return payForOne;
	}

	public void setPayForOne(String payForOne) {
		this.payForOne = payForOne;
	}

	public String getMortgagePay() {
		return mortgagePay;
	}

	public void setMortgagePay(String mortgagePay) {
		this.mortgagePay = mortgagePay;
	}

	public String getInstalment() {
		return instalment;
	}

	public void setInstalment(String instalment) {
		this.instalment = instalment;
	}

	public String getSaleControl() {
		return saleControl;
	}

	public void setSaleControl(String saleControl) {
		this.saleControl = saleControl;
	}

	public String getWillSale() {
		return willSale;
	}

	public void setWillSale(String willSale) {
		this.willSale = willSale;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getSelfSupport() {
		return selfSupport;
	}

	public void setSelfSupport(String selfSupport) {
		this.selfSupport = selfSupport;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public String getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(String saleDetail) {
		this.saleDetail = saleDetail;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getTotalPrice1() {
		return totalPrice1;
	}

	public void setTotalPrice1(String totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getTotalPrice2() {
		return totalPrice2;
	}

	public void setTotalPrice2(String totalPrice2) {
		this.totalPrice2 = totalPrice2;
	}

}
