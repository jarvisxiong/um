package com.hhz.ump.web.vo;

import java.math.BigDecimal;
import java.util.Date;

public class BisMustAutoVo implements java.io.Serializable {

	private static final long serialVersionUID = -8528752702784619488L;
	
	private Long sequenceNo;
	private String chargeTypeCd;
	private Date collDate;
	private String mustYear;
	private String mustMonth;
	private BigDecimal money;
	private String describe;
	private String entityTmpId;
	
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}
	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	public Date getCollDate() {
		return collDate;
	}
	public void setCollDate(Date collDate) {
		this.collDate = collDate;
	}
	public String getMustYear() {
		return mustYear;
	}
	public void setMustYear(String mustYear) {
		this.mustYear = mustYear;
	}
	public String getMustMonth() {
		return mustMonth;
	}
	public void setMustMonth(String mustMonth) {
		this.mustMonth = mustMonth;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getEntityTmpId() {
		return entityTmpId;
	}
	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}
	
}
