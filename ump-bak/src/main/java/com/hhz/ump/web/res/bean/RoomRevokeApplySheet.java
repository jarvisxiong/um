/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 退房审批表
 * 
 */
public class RoomRevokeApplySheet extends BaseTemplatePay {

	// 项目名称  projectName
	// 业主姓名  owerName 身份证 idno
	// 护照号 passportNo
	// 联系电话 phoneNo通讯地址 address
	// 退购单元   buildName栋  unitName 单元   floorName层 storeName住宅/店面    建筑面积 buildSquare
	// 原建筑单价   oldConsUnitPrice 元      新建筑单价  newConsUnitPrice 元
	// 原 总 价  oldTotalPrice 元 新 总 价     newTotalPrice   元
	// 认购时间  substribeDate     调价比例 priceAdjustProp%
	// 已付款项 
	// 时间 prePayDate   预订金：  prePayAmt元
	// 时间 firstPayDate 首付款： firstPayAmt元
	// 时间 repayDate    续     款： repayAmt元
	// 物业现状  selectReseBook 预订书      selectSignContract 签定买卖合同      selectMortgageLoan 办理按揭
	// 声明： 
	// 本人申请上述房产的退购，并愿意支付以下费用：                    
	// 原房屋成交总价1％的手续费用，即  procedureChangeAmt 元。                            
	// 业主签名：owerSignName
	// 备 注：contentDesc

	private String projectName;
	private String projectCd;
	private String owerName;
	private String idno;
	private String phoneNo;
	private String address;
	private String buildName;
	private String unitName;
	private String floorName;
	private String storeName;
	private String buildSquare;
	private String oldConsUnitPrice;
	private String newConsUnitPrice;
	private String oldTotalPrice;
	private String newTotalPrice;
	private String substribeDate;
	private String priceAdjustProp;
	private String prePayDate;
	private String prePayAmt;
	private String firstPayDate;
	private String firstPayAmt;
	private String repayDate;
	private String repayAmt;
	private String selectReseBook;
	private String selectSignContract;
	private String selectMortgageLoan;
	private String procedureChangeAmt;
	private String owerSignName;
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
	public String getOwerName() {
		return owerName;
	}
	public void setOwerName(String owerName) {
		this.owerName = owerName;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBuildSquare() {
		return buildSquare;
	}
	public void setBuildSquare(String buildSquare) {
		this.buildSquare = buildSquare;
	}
	public String getOldConsUnitPrice() {
		return oldConsUnitPrice;
	}
	public void setOldConsUnitPrice(String oldConsUnitPrice) {
		this.oldConsUnitPrice = oldConsUnitPrice;
	}
	public String getNewConsUnitPrice() {
		return newConsUnitPrice;
	}
	public void setNewConsUnitPrice(String newConsUnitPrice) {
		this.newConsUnitPrice = newConsUnitPrice;
	}
	public String getOldTotalPrice() {
		return oldTotalPrice;
	}
	public void setOldTotalPrice(String oldTotalPrice) {
		this.oldTotalPrice = oldTotalPrice;
	}
	public String getNewTotalPrice() {
		return newTotalPrice;
	}
	public void setNewTotalPrice(String newTotalPrice) {
		this.newTotalPrice = newTotalPrice;
	}
	public String getSubstribeDate() {
		return substribeDate;
	}
	public void setSubstribeDate(String substribeDate) {
		this.substribeDate = substribeDate;
	}
	public String getPriceAdjustProp() {
		return priceAdjustProp;
	}
	public void setPriceAdjustProp(String priceAdjustProp) {
		this.priceAdjustProp = priceAdjustProp;
	}
	public String getPrePayDate() {
		return prePayDate;
	}
	public void setPrePayDate(String prePayDate) {
		this.prePayDate = prePayDate;
	}
	public String getPrePayAmt() {
		return prePayAmt;
	}
	public void setPrePayAmt(String prePayAmt) {
		this.prePayAmt = prePayAmt;
	}
	public String getFirstPayDate() {
		return firstPayDate;
	}
	public void setFirstPayDate(String firstPayDate) {
		this.firstPayDate = firstPayDate;
	}
	public String getFirstPayAmt() {
		return firstPayAmt;
	}
	public void setFirstPayAmt(String firstPayAmt) {
		this.firstPayAmt = firstPayAmt;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(String repayAmt) {
		this.repayAmt = repayAmt;
	}
	public String getSelectReseBook() {
		return selectReseBook;
	}
	public void setSelectReseBook(String selectReseBook) {
		this.selectReseBook = selectReseBook;
	}
	public String getSelectSignContract() {
		return selectSignContract;
	}
	public void setSelectSignContract(String selectSignContract) {
		this.selectSignContract = selectSignContract;
	}
	public String getSelectMortgageLoan() {
		return selectMortgageLoan;
	}
	public void setSelectMortgageLoan(String selectMortgageLoan) {
		this.selectMortgageLoan = selectMortgageLoan;
	}
	public String getProcedureChangeAmt() {
		return procedureChangeAmt;
	}
	public void setProcedureChangeAmt(String procedureChangeAmt) {
		this.procedureChangeAmt = procedureChangeAmt;
	}
	public String getOwerSignName() {
		return owerSignName;
	}
	public void setOwerSignName(String owerSignName) {
		this.owerSignName = owerSignName;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.projectName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.projectCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}
}
