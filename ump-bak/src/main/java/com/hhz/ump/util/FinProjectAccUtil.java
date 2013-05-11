package com.hhz.ump.util;

import java.math.BigDecimal;

public class FinProjectAccUtil {

	private String bankName;
	private String accountNo;
	private BigDecimal befAmount;
	private BigDecimal inAmount;
	private BigDecimal outAmount;
	private BigDecimal aftAmount;
	private String finProjectAcctRelId;
	private String accountTypeCd;
	private String enableFlg;
	private String currencyCd;

	public FinProjectAccUtil() {
	}

	public FinProjectAccUtil(String bankName, String accountNo,
			BigDecimal befAmount, BigDecimal inAmount, BigDecimal outAmount,
			BigDecimal aftAmount, String finProjectAcctRelId,
			String accountTypeCd, String enableFlg, String currencyCd) {
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.befAmount = befAmount;
		this.inAmount = inAmount;
		this.outAmount = outAmount;
		this.aftAmount = aftAmount;
		this.finProjectAcctRelId = finProjectAcctRelId;
		this.accountTypeCd = accountTypeCd;
		this.enableFlg = enableFlg;
		this.currencyCd = currencyCd;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getBefAmount() {
		return befAmount;
	}

	public void setBefAmount(BigDecimal befAmount) {
		this.befAmount = befAmount;
	}

	public BigDecimal getInAmount() {
		return inAmount;
	}

	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public BigDecimal getAftAmount() {
		return aftAmount;
	}

	public void setAftAmount(BigDecimal aftAmount) {
		this.aftAmount = aftAmount;
	}

	public String getFinProjectAcctRelId() {
		return finProjectAcctRelId;
	}

	public void setFinProjectAcctRelId(String finProjectAcctRelId) {
		this.finProjectAcctRelId = finProjectAcctRelId;
	}

	public String getAccountTypeCd() {
		return accountTypeCd;
	}

	public void setAccountTypeCd(String accountTypeCd) {
		this.accountTypeCd = accountTypeCd;
	}

	public String getEnableFlg() {
		return enableFlg;
	}

	public void setEnableFlg(String enableFlg) {
		this.enableFlg = enableFlg;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
}
