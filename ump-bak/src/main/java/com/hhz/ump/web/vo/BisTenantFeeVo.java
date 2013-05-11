package com.hhz.ump.web.vo;

import java.math.BigDecimal;

public class BisTenantFeeVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String chargeTypeCd;
    private BigDecimal mustTotal;
    private BigDecimal factTotal;
    
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}
	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	public BigDecimal getMustTotal() {
		return mustTotal;
	}
	public void setMustTotal(BigDecimal mustTotal) {
		this.mustTotal = mustTotal;
	}
	public BigDecimal getFactTotal() {
		return factTotal;
	}
	public void setFactTotal(BigDecimal factTotal) {
		this.factTotal = factTotal;
	}
    
}
