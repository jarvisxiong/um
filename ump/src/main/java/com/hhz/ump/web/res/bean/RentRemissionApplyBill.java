package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//租金减免审批表(eg:租金减免审批表) 
public class RentRemissionApplyBill extends BaseTemplate {
	//项目名称	
	
	//商户名称		联系人	
	//联系地址		联系电话	
	
	//商户分类	□主力店   □次主力店   □中型店   □品牌店 
	
	//租金减免金额		
	//租金减免 起止期	
	
	//租金减免内容

	private String projectName;
	private String projectCd;
	
	private String storeName;
	private String storeLinkman;
	private String storeLinkAddr;
	private String storeLinkPhone;
	
	private String storeTypeCd1;
	private String storeTypeCd2;
	private String storeTypeCd3;
	private String storeTypeCd4;
	
	private String remissionAmt;
	private String dateRankDesc;
	
	//不用
	private String startDate;
	private String endDate;
	
	
	private String rentDesc;
	
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreLinkman() {
		return storeLinkman;
	}
	public void setStoreLinkman(String storeLinkman) {
		this.storeLinkman = storeLinkman;
	}
	public String getStoreLinkAddr() {
		return storeLinkAddr;
	}
	public void setStoreLinkAddr(String storeLinkAddr) {
		this.storeLinkAddr = storeLinkAddr;
	}
	public String getStoreLinkPhone() {
		return storeLinkPhone;
	}
	public void setStoreLinkPhone(String storeLinkPhone) {
		this.storeLinkPhone = storeLinkPhone;
	}
	public String getStoreTypeCd1() {
		return storeTypeCd1;
	}
	public void setStoreTypeCd1(String storeTypeCd1) {
		this.storeTypeCd1 = storeTypeCd1;
	}
	public String getStoreTypeCd2() {
		return storeTypeCd2;
	}
	public void setStoreTypeCd2(String storeTypeCd2) {
		this.storeTypeCd2 = storeTypeCd2;
	}
	public String getStoreTypeCd3() {
		return storeTypeCd3;
	}
	public void setStoreTypeCd3(String storeTypeCd3) {
		this.storeTypeCd3 = storeTypeCd3;
	}
	public String getStoreTypeCd4() {
		return storeTypeCd4;
	}
	public void setStoreTypeCd4(String storeTypeCd4) {
		this.storeTypeCd4 = storeTypeCd4;
	}
	public String getRemissionAmt() {
		return remissionAmt;
	}
	public void setRemissionAmt(String remissionAmt) {
		this.remissionAmt = remissionAmt;
	} 
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDateRankDesc() {
		return dateRankDesc;
	}
	public void setDateRankDesc(String dateRankDesc) {
		this.dateRankDesc = dateRankDesc;
	}
	public String getRentDesc() {
		return rentDesc;
	}
	public void setRentDesc(String rentDesc) {
		this.rentDesc = rentDesc;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return storeName;
	}
 
}
