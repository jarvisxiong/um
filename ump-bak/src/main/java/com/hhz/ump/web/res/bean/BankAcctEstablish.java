package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * 银行账户开户审批表
 * @author Administrator
 *
 */
public class BankAcctEstablish extends BaseTemplate {

	private String approveCompany;//申请公司全称
	private String estaAcctName;//开户银行全称
	private String basicAccount;//基本户
	private String commonAccount;//一般户
	private String loanAccount;//贷款户
	private String earnestMoneyAccount;//保证金户
	private String fixedDepositAccount;//定期存款户
	private String verificationAccount;//验资户
	private String currencyType;//币别
	private String bankAddr;//银行地址
	private String bankContactor;//银行联系人
	private String contactorType;//联系方式
	private String approveReason;//申请事由
	public String getApproveCompany() {
		return approveCompany;
	}
	public void setApproveCompany(String approveCompany) {
		this.approveCompany = approveCompany;
	}
	public String getEstaAcctName() {
		return estaAcctName;
	}
	public void setEstaAcctName(String estaAcctName) {
		this.estaAcctName = estaAcctName;
	}
	public String getBasicAccount() {
		return basicAccount;
	}
	public void setBasicAccount(String basicAccount) {
		this.basicAccount = basicAccount;
	}
	public String getCommonAccount() {
		return commonAccount;
	}
	public void setCommonAccount(String commonAccount) {
		this.commonAccount = commonAccount;
	}
	public String getLoanAccount() {
		return loanAccount;
	}
	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}
	public String getEarnestMoneyAccount() {
		return earnestMoneyAccount;
	}
	public void setEarnestMoneyAccount(String earnestMoneyAccount) {
		this.earnestMoneyAccount = earnestMoneyAccount;
	}
	public String getFixedDepositAccount() {
		return fixedDepositAccount;
	}
	public void setFixedDepositAccount(String fixedDepositAccount) {
		this.fixedDepositAccount = fixedDepositAccount;
	}
	public String getVerificationAccount() {
		return verificationAccount;
	}
	public void setVerificationAccount(String verificationAccount) {
		this.verificationAccount = verificationAccount;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getBankAddr() {
		return bankAddr;
	}
	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}
	public String getBankContactor() {
		return bankContactor;
	}
	public void setBankContactor(String bankContactor) {
		this.bankContactor = bankContactor;
	}
	public String getContactorType() {
		return contactorType;
	}
	public void setContactorType(String contactorType) {
		this.contactorType = contactorType;
	}
	public String getApproveReason() {
		return approveReason;
	}
	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return approveCompany;
	}
}
