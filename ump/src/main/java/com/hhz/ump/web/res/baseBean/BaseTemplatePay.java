/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

import javax.persistence.MappedSuperclass;

/**
 * 付款管理
 * 
 * @author huangj 2010-9-13
 */
@MappedSuperclass
public abstract class BaseTemplatePay extends BaseTemplate {
	
	private String payer;// 付款人

	private String payerAccount;// 付款人帐号

	private String payerBank;// 付款人开户行
	
	private String curPaymentAmt; // 本次付款金额

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	public String getPayerBank() {
		return payerBank;
	}

	public void setPayerBank(String payerBank) {
		this.payerBank = payerBank;
	}

	public String getCurPaymentAmt() {
		return curPaymentAmt;
	}

	public void setCurPaymentAmt(String curPaymentAmt) {
		this.curPaymentAmt = curPaymentAmt;
	}
	
	
}