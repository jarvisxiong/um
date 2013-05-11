/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 换房审批表
 * 
 */
public class RoomChangeBill extends BaseTemplatePay {

	private String projectName;//项目名称
	private String projectCd;//项目Cd
	private String owner;//业主姓名
	private String idno;//业主身份证
	private String phoneNo;//业主联系电话
	private String address;//业主地址
	private String houseNameOri;//栋
	private String buildNameOri;//退购单元：层
	private String unitNameOri;//单元
	private String storeNameOri;//住宅/店面
	private String houseNameCur;//栋
	private String buildNameCur;//换购单元：层
	private String unitNameCur;//单元
	private String storeNameCur;//住宅/店面
	private String buildSquareOri;//原建筑面积
	private String buildSquareCur;//现建筑面积
	private String unitPriceOri;//原建筑单价
	private String unitPriceCur;//现建筑单价
	private String substribeDate;//认购时间
	private String differPrice;//差价
	private String totalPriceOri;//原总价
	private String totalPriceCur;//现总价
	private String prePayDate;//预付时间
	private String prePayAmt;//预付金额
	private String firstPayDate;//首付时间
	private String firstPayAmt;//首付金额
	private String repayDate;//续付时间
	private String repayAmt;//续付金额
	//********************物业现状************************/
	private String selectReseBook;//预订书
	private String selectSignContract;//签定买卖合同
	private String selectMortgageLoan;//办理按揭
	//***************************************//
	private String procedureChangeAmt;//原房屋成交总价1％的手续费用
	private String owerSign;//业务签名
	private String contentDesc;//备注
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
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
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
		return owner;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public String getBuildNameOri() {
		return buildNameOri;
	}
	public void setBuildNameOri(String buildNameOri) {
		this.buildNameOri = buildNameOri;
	}
	public String getUnitNameOri() {
		return unitNameOri;
	}
	public void setUnitNameOri(String unitNameOri) {
		this.unitNameOri = unitNameOri;
	}
	public String getStoreNameOri() {
		return storeNameOri;
	}
	public void setStoreNameOri(String storeNameOri) {
		this.storeNameOri = storeNameOri;
	}
	public String getBuildNameCur() {
		return buildNameCur;
	}
	public void setBuildNameCur(String buildNameCur) {
		this.buildNameCur = buildNameCur;
	}
	public String getUnitNameCur() {
		return unitNameCur;
	}
	public void setUnitNameCur(String unitNameCur) {
		this.unitNameCur = unitNameCur;
	}
	public String getStoreNameCur() {
		return storeNameCur;
	}
	public void setStoreNameCur(String storeNameCur) {
		this.storeNameCur = storeNameCur;
	}
	public String getBuildSquareOri() {
		return buildSquareOri;
	}
	public void setBuildSquareOri(String buildSquareOri) {
		this.buildSquareOri = buildSquareOri;
	}
	public String getBuildSquareCur() {
		return buildSquareCur;
	}
	public void setBuildSquareCur(String buildSquareCur) {
		this.buildSquareCur = buildSquareCur;
	}
	public String getUnitPriceOri() {
		return unitPriceOri;
	}
	public void setUnitPriceOri(String unitPriceOri) {
		this.unitPriceOri = unitPriceOri;
	}
	public String getUnitPriceCur() {
		return unitPriceCur;
	}
	public void setUnitPriceCur(String unitPriceCur) {
		this.unitPriceCur = unitPriceCur;
	}
	public String getSubstribeDate() {
		return substribeDate;
	}
	public void setSubstribeDate(String substribeDate) {
		this.substribeDate = substribeDate;
	}
	public String getDifferPrice() {
		return differPrice;
	}
	public void setDifferPrice(String differPrice) {
		this.differPrice = differPrice;
	}
	public String getTotalPriceOri() {
		return totalPriceOri;
	}
	public void setTotalPriceOri(String totalPriceOri) {
		this.totalPriceOri = totalPriceOri;
	}
	public String getTotalPriceCur() {
		return totalPriceCur;
	}
	public void setTotalPriceCur(String totalPriceCur) {
		this.totalPriceCur = totalPriceCur;
	}
	public String getOwerSign() {
		return owerSign;
	}
	public void setOwerSign(String owerSign) {
		this.owerSign = owerSign;
	}
	public String getHouseNameOri() {
		return houseNameOri;
	}
	public void setHouseNameOri(String houseNameOri) {
		this.houseNameOri = houseNameOri;
	}
	public String getHouseNameCur() {
		return houseNameCur;
	}
	public void setHouseNameCur(String houseNameCur) {
		this.houseNameCur = houseNameCur;
	}
}
