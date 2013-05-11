package com.hhz.ump.util;

import java.math.BigDecimal;
import java.util.Date;

public class FinDetailUtil {

	private String finInOutDetailId;
	private String finItemCd;
	private BigDecimal inAmount;
	private BigDecimal outAmount;
	private BigDecimal balance;
	private String summaryDesc;
	private Date createdDate;
	private int colspanNum;
	private String finItemName;

	public FinDetailUtil() {

	}

	public FinDetailUtil(String finInOutDetailId, String finItemCd,
			BigDecimal inAmount, BigDecimal outAmount, BigDecimal balance,
			String summaryDesc, Date createdDate, int colspanNum,
			String finItemName) {
		this.finInOutDetailId = finInOutDetailId;
		this.finItemCd = finItemCd;
		this.inAmount = inAmount;
		this.outAmount = outAmount;
		this.balance = balance;
		this.summaryDesc = summaryDesc;
		this.createdDate = createdDate;
		this.colspanNum = colspanNum;
		this.finItemName = finItemName;
	}

	public String getFinInOutDetailId() {
		return finInOutDetailId;
	}

	public void setFinInOutDetailId(String finInOutDetailId) {
		this.finInOutDetailId = finInOutDetailId;
	}

	public String getFinItemCd() {
		return finItemCd;
	}

	public void setFinItemCd(String finItemCd) {
		this.finItemCd = finItemCd;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getSummaryDesc() {
		return summaryDesc;
	}

	public void setSummaryDesc(String summaryDesc) {
		this.summaryDesc = summaryDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getColspanNum() {
		return colspanNum;
	}

	public void setColspanNum(int colspanNum) {
		this.colspanNum = colspanNum;
	}

	public String getFinItemName() {
		return finItemName;
	}

	public void setFinItemName(String finItemName) {
		this.finItemName = finItemName;
	}
}
