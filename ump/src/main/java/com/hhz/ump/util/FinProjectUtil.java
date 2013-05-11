package com.hhz.ump.util;

import java.math.BigDecimal;

public class FinProjectUtil {

	private String finProjectCd;
	private String projectName;
	private BigDecimal inAmount;
	private BigDecimal outAmount;
	private BigDecimal useableAmount;
	private BigDecimal unUseableAmount;
	private String statusCd;
	private String currencyCd;
	private String accTypeCd;
	private String publicFlg;

	public String getFinProjectCd() {
		return finProjectCd;
	}

	public void setFinProjectCd(String finProjectCd) {
		this.finProjectCd = finProjectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public BigDecimal getUseableAmount() {
		return useableAmount;
	}

	public void setUseableAmount(BigDecimal useableAmount) {
		this.useableAmount = useableAmount;
	}

	public BigDecimal getUnUseableAmount() {
		return unUseableAmount;
	}

	public void setUnUseableAmount(BigDecimal unUseableAmount) {
		this.unUseableAmount = unUseableAmount;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getAccTypeCd() {
		return accTypeCd;
	}

	public void setAccTypeCd(String accTypeCd) {
		this.accTypeCd = accTypeCd;
	}

	public String getPublicFlg() {
		return publicFlg;
	}

	public void setPublicFlg(String publicFlg) {
		this.publicFlg = publicFlg;
	}
}
