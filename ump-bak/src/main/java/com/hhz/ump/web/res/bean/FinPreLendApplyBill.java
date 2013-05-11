/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 *  预借款申请单（个人）
 * 
 */
public class FinPreLendApplyBill extends BaseTemplatePay {
	
	// 借款人 lendUserName,lendUserCd 部门lendDeptOrgName,lendDeptOrgCd  职务 positionName,positionCd 预计归还期限 planRepaymentDate
	// 借款事由 lendReasonDesc （用途） 借款类别 ,如  （请打勾) 临时借款 selectTempLend 备用金 selectBackUp
	// 现金借款 金额（大写）： ￥ cashLendCapitalAmt cashLendSmallAmt
	// 转账支票 金额（大写）： ￥ tranCheckCapitalAmt tranCheckSmallAmt
	// 收款单位名称：receiveUnitName,receiveUnitCd
	// 账号： accountNo 开户银行：bankName,bankCd
	
	
	
	private String lendUserName;
	private String lendUserCd;
	private String lendDeptOrgName;
	private String lendDeptOrgCd;
	private String positionName;
	private String positionCd;
	private String planRepaymentDate;
	private String lendReasonDesc;
	private String selectTempLend;
	private String selectBackUp;
	private String cashLendCapitalAmt;
	private String tranCheckCapitalAmt;
	private String cashLendSmallAmt;
	private String tranCheckSmallAmt;
	
	
	private String receiveUnitName;
	private String accountNo;
	private String bankName;
	private String bankCd;
	
	

	public String getLendUserName() {
		return lendUserName;
	}
	public void setLendUserName(String lendUserName) {
		this.lendUserName = lendUserName;
	}
	public String getLendUserCd() {
		return lendUserCd;
	}
	public void setLendUserCd(String lendUserCd) {
		this.lendUserCd = lendUserCd;
	}
	public String getLendDeptOrgName() {
		return lendDeptOrgName;
	}
	public void setLendDeptOrgName(String lendDeptOrgName) {
		this.lendDeptOrgName = lendDeptOrgName;
	}
	public String getLendDeptOrgCd() {
		return lendDeptOrgCd;
	}
	public void setLendDeptOrgCd(String lendDeptOrgCd) {
		this.lendDeptOrgCd = lendDeptOrgCd;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}
	public String getPlanRepaymentDate() {
		return planRepaymentDate;
	}
	public void setPlanRepaymentDate(String planRepaymentDate) {
		this.planRepaymentDate = planRepaymentDate;
	}
	public String getLendReasonDesc() {
		return lendReasonDesc;
	}
	public void setLendReasonDesc(String lendReasonDesc) {
		this.lendReasonDesc = lendReasonDesc;
	}
	public String getSelectTempLend() {
		return selectTempLend;
	}
	public void setSelectTempLend(String selectTempLend) {
		this.selectTempLend = selectTempLend;
	}
	public String getSelectBackUp() {
		return selectBackUp;
	}
	public void setSelectBackUp(String selectBackUp) {
		this.selectBackUp = selectBackUp;
	}
	public String getCashLendCapitalAmt() {
		return cashLendCapitalAmt;
	}
	public void setCashLendCapitalAmt(String cashLendCapitalAmt) {
		this.cashLendCapitalAmt = cashLendCapitalAmt;
	}
	public String getTranCheckCapitalAmt() {
		return tranCheckCapitalAmt;
	}
	public void setTranCheckCapitalAmt(String tranCheckCapitalAmt) {
		this.tranCheckCapitalAmt = tranCheckCapitalAmt;
	}
	public String getCashLendSmallAmt() {
		return cashLendSmallAmt;
	}
	public void setCashLendSmallAmt(String cashLendSmallAmt) {
		this.cashLendSmallAmt = cashLendSmallAmt;
	}
	public String getTranCheckSmallAmt() {
		return tranCheckSmallAmt;
	}
	public void setTranCheckSmallAmt(String tranCheckSmallAmt) {
		this.tranCheckSmallAmt = tranCheckSmallAmt;
	}
	public String getReceiveUnitName() {
		return receiveUnitName;
	}
	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.lendDeptOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.lendDeptOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.lendUserName;
	}
	 
}
