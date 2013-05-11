package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//物业管理费/公摊费用减免审批表(eg:物业管理费/公摊费用减免审批表)
public class StrataFeeRentApplyBill extends BaseTemplate {
//	项目名称	
//	商户名称		联系人	
//	联系地址		联系电话	
//	减免类型	□物业管理费    □公摊费用	
//	物业性质	□自持    □销售
//	物业管理费
//	减免金额		物业管理费减免起止期	
//	公摊费用     
//	减免金额		公摊费用减免起止期	

	private String projectName;
	private String projectCd;
	
	private String storeName;
	private String storeLinkman;
	private String storeLinkAddr;
	private String storeLinkPhone;
	
	private String storeNo;//店面号
	private String area;//面积
	private String contractPeriod;//合同期
	
	private String feeTypeCd1;
	private String feeTypeCd2;
	private String feeTypeCd3;//违约金
	private String feeTypeCd4;//对商户补偿
	private String charactCd1;
	private String charactCd2;
		
	private String strataFeeAmt;
	private String strataStartDate;
	private String strataEndDate;
	private String publicFeeAmt;
	private String reduceAmt;//违约金减免金额(元)
	private String offsetAmt;//对商户补偿金额(元)
	private String publicStartDate;
	private String publicEndDate;
	
	private String contentDesc;
	
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
	public String getFeeTypeCd1() {
		return feeTypeCd1;
	}
	public void setFeeTypeCd1(String feeTypeCd1) {
		this.feeTypeCd1 = feeTypeCd1;
	}
	public String getCharactCd1() {
		return charactCd1;
	}
	public void setCharactCd1(String charactCd1) {
		this.charactCd1 = charactCd1;
	}
	public String getCharactCd2() {
		return charactCd2;
	}
	public void setCharactCd2(String charactCd2) {
		this.charactCd2 = charactCd2;
	}
	public String getStrataFeeAmt() {
		return strataFeeAmt;
	}
	public void setStrataFeeAmt(String strataFeeAmt) {
		this.strataFeeAmt = strataFeeAmt;
	}
	public String getStrataStartDate() {
		return strataStartDate;
	}
	public void setStrataStartDate(String strataStartDate) {
		this.strataStartDate = strataStartDate;
	}
	public String getPublicFeeAmt() {
		return publicFeeAmt;
	}
	public void setPublicFeeAmt(String publicFeeAmt) {
		this.publicFeeAmt = publicFeeAmt;
	}
	public String getPublicStartDate() {
		return publicStartDate;
	}
	public void setPublicStartDate(String publicStartDate) {
		this.publicStartDate = publicStartDate;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getStoreLinkman() {
		return storeLinkman;
	}
	public void setStoreLinkman(String storeLinkman) {
		this.storeLinkman = storeLinkman;
	}
	public String getStrataEndDate() {
		return strataEndDate;
	}
	public void setStrataEndDate(String strataEndDate) {
		this.strataEndDate = strataEndDate;
	}
	public String getPublicEndDate() {
		return publicEndDate;
	}
	public void setPublicEndDate(String publicEndDate) {
		this.publicEndDate = publicEndDate;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return storeName;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getContractPeriod() {
		return contractPeriod;
	}
	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}
	public String getReduceAmt() {
		return reduceAmt;
	}
	public void setReduceAmt(String reduceAmt) {
		this.reduceAmt = reduceAmt;
	}
	public String getOffsetAmt() {
		return offsetAmt;
	}
	public void setOffsetAmt(String offsetAmt) {
		this.offsetAmt = offsetAmt;
	}
	public String getFeeTypeCd2() {
		return feeTypeCd2;
	}
	public void setFeeTypeCd2(String feeTypeCd2) {
		this.feeTypeCd2 = feeTypeCd2;
	}
	public String getFeeTypeCd3() {
		return feeTypeCd3;
	}
	public void setFeeTypeCd3(String feeTypeCd3) {
		this.feeTypeCd3 = feeTypeCd3;
	}
	public String getFeeTypeCd4() {
		return feeTypeCd4;
	}
	public void setFeeTypeCd4(String feeTypeCd4) {
		this.feeTypeCd4 = feeTypeCd4;
	}
	
}
