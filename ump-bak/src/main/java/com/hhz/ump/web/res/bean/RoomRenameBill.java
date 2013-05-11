/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 更名审批表
 * 
 */
public class RoomRenameBill extends BaseTemplatePay {

	private String projectName;//项目名称
	private String projectCd;//项目Cd
	private String typeMinus;//减名
	private String typeAdd;//增名
	private String typeChange;//换名
	private String ownerOri;//原业主姓名
	private String ownerCur;//原业主姓名
	private String idnoOri;//原业主身份证
	private String idnoCur;//现业主身份证
	private String phoneNoOri;//原业主联系电话
	private String phoneNoCur;//现业主联系电话
	private String addressOri;//原业主地址
	private String addressCur;//现业主地址
	private String houseName;//认购单元：栋
	private String floorName;//认购单元：层
	private String unitName;//单元
	private String storeName;//住宅/店面
	private String unitPrice;//单价
	private String privilegeValue;//优惠比率
	private String isPrivilegeTrue;//是否已额外申请优惠：是
	private String isPrivilegeFalse;//是否已额外申请优惠：否
	private String substribeDate;//认购时间
	private String totalPrice;//总价
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
	private String procedureChangeAmt;//成交总价1％的手续费用
	private String owerSignOri;//原业主签名
	private String owerSignCur;//现业务签名
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
	public String getTypeMinus() {
		return typeMinus;
	}
	public void setTypeMinus(String typeMinus) {
		this.typeMinus = typeMinus;
	}
	public String getTypeAdd() {
		return typeAdd;
	}
	public void setTypeAdd(String typeAdd) {
		this.typeAdd = typeAdd;
	}
	public String getTypeChange() {
		return typeChange;
	}
	public void setTypeChange(String typeChange) {
		this.typeChange = typeChange;
	}
	public String getOwnerOri() {
		return ownerOri;
	}
	public void setOwnerOri(String ownerOri) {
		this.ownerOri = ownerOri;
	}
	public String getOwnerCur() {
		return ownerCur;
	}
	public void setOwnerCur(String ownerCur) {
		this.ownerCur = ownerCur;
	}
	public String getIdnoOri() {
		return idnoOri;
	}
	public void setIdnoOri(String idnoOri) {
		this.idnoOri = idnoOri;
	}
	public String getIdnoCur() {
		return idnoCur;
	}
	public void setIdnoCur(String idnoCur) {
		this.idnoCur = idnoCur;
	}
	public String getPhoneNoOri() {
		return phoneNoOri;
	}
	public void setPhoneNoOri(String phoneNoOri) {
		this.phoneNoOri = phoneNoOri;
	}
	public String getPhoneNoCur() {
		return phoneNoCur;
	}
	public void setPhoneNoCur(String phoneNoCur) {
		this.phoneNoCur = phoneNoCur;
	}
	public String getAddressOri() {
		return addressOri;
	}
	public void setAddressOri(String addressOri) {
		this.addressOri = addressOri;
	}
	public String getAddressCur() {
		return addressCur;
	}
	public void setAddressCur(String addressCur) {
		this.addressCur = addressCur;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String buildName) {
		this.floorName = buildName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getIsPrivilegeTrue() {
		return isPrivilegeTrue;
	}
	public void setIsPrivilegeTrue(String isPrivilegeTrue) {
		this.isPrivilegeTrue = isPrivilegeTrue;
	}
	public String getIsPrivilegeFalse() {
		return isPrivilegeFalse;
	}
	public void setIsPrivilegeFalse(String isPrivilegeFalse) {
		this.isPrivilegeFalse = isPrivilegeFalse;
	}
	public String getSubstribeDate() {
		return substribeDate;
	}
	public void setSubstribeDate(String substribeDate) {
		this.substribeDate = substribeDate;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
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
		return null;
	}
	public String getOwerSignOri() {
		return owerSignOri;
	}
	public void setOwerSignOri(String owerSignOri) {
		this.owerSignOri = owerSignOri;
	}
	public String getOwerSignCur() {
		return owerSignCur;
	}
	public void setOwerSignCur(String owerSignCur) {
		this.owerSignCur = owerSignCur;
	}
	public String getPrivilegeValue() {
		return privilegeValue;
	}
	public void setPrivilegeValue(String privilegeValue) {
		this.privilegeValue = privilegeValue;
	}
}
