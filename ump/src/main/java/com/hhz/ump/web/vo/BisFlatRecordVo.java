package com.hhz.ump.web.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公寓收费记录vo
 */
public class BisFlatRecordVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bisFlatRecordId;
	private String costType;
	private Long recordYear;
	private Long recordMonth;
	private BigDecimal monthSs;
	private String chargeFlg;
	private BigDecimal waterTonnage;
	private BigDecimal elecDegree;
	private BigDecimal monthYs;
	private BigDecimal monthYis;
	private BigDecimal monthYus;
	private BigDecimal monthMonth;
	private BigDecimal currYearDebt;
	private BigDecimal agoYearDebt;
	private BigDecimal oweWaterRate;
	private BigDecimal monthYsTotal;
	private BigDecimal ysTotal;
	private BigDecimal monthSsTotal;
	private BigDecimal ssTotal;
	private BigDecimal currYearWaterRate;
	private BigDecimal currYearYusWaterRate;
	private BigDecimal agoYearOwnfeeTotal;
	private BigDecimal yusTotal;
	private BigDecimal currYearQsTotal;
	private BigDecimal qsTotal;
	private String remark;
	private String creator;
	private String createdCenterCd;
	private String createdDeptCd;
	private String createdPositionCd;
	private Date createdDate;
	private String updator;
	private String updatedCenterCd;
	private String updatedDeptCd;
	private String updatedPositionCd;
	private Date updatedDate;
	private long recordVersion;
	private String paymentIntertemporal;
	private BigDecimal owePropRate;
	private BigDecimal oweElecRate;
	private BigDecimal currYearPropRate;
	private BigDecimal currYearElecRate;
	private BigDecimal currYearYusPropRate;
	private BigDecimal currYearYusElecRate;
	private BigDecimal currYearYsTotal;
	private BigDecimal currYearSsTotal;

	public String getBisFlatRecordId() {
		return bisFlatRecordId;
	}

	public void setBisFlatRecordId(String bisFlatRecordId) {
		this.bisFlatRecordId = bisFlatRecordId;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public Long getRecordYear() {
		return recordYear;
	}

	public void setRecordYear(Long recordYear) {
		this.recordYear = recordYear;
	}

	public Long getRecordMonth() {
		return recordMonth;
	}

	public void setRecordMonth(Long recordMonth) {
		this.recordMonth = recordMonth;
	}

	public BigDecimal getMonthSs() {
		return monthSs;
	}

	public void setMonthSs(BigDecimal monthSs) {
		this.monthSs = monthSs;
	}

	public String getChargeFlg() {
		return chargeFlg;
	}

	public void setChargeFlg(String chargeFlg) {
		this.chargeFlg = chargeFlg;
	}

	public BigDecimal getWaterTonnage() {
		return waterTonnage;
	}

	public void setWaterTonnage(BigDecimal waterTonnage) {
		this.waterTonnage = waterTonnage;
	}

	public BigDecimal getElecDegree() {
		return elecDegree;
	}

	public void setElecDegree(BigDecimal elecDegree) {
		this.elecDegree = elecDegree;
	}

	public BigDecimal getMonthYs() {
		return monthYs;
	}

	public void setMonthYs(BigDecimal monthYs) {
		this.monthYs = monthYs;
	}

	public BigDecimal getMonthYis() {
		return monthYis;
	}

	public void setMonthYis(BigDecimal monthYis) {
		this.monthYis = monthYis;
	}

	public BigDecimal getMonthYus() {
		return monthYus;
	}

	public void setMonthYus(BigDecimal monthYus) {
		this.monthYus = monthYus;
	}

	public BigDecimal getMonthMonth() {
		return monthMonth;
	}

	public void setMonthMonth(BigDecimal monthMonth) {
		this.monthMonth = monthMonth;
	}

	public BigDecimal getCurrYearDebt() {
		return currYearDebt;
	}

	public void setCurrYearDebt(BigDecimal currYearDebt) {
		this.currYearDebt = currYearDebt;
	}

	public BigDecimal getAgoYearDebt() {
		return agoYearDebt;
	}

	public void setAgoYearDebt(BigDecimal agoYearDebt) {
		this.agoYearDebt = agoYearDebt;
	}

	public BigDecimal getOweWaterRate() {
		return oweWaterRate;
	}

	public void setOweWaterRate(BigDecimal oweWaterRate) {
		this.oweWaterRate = oweWaterRate;
	}

	public BigDecimal getMonthYsTotal() {
		return monthYsTotal;
	}

	public void setMonthYsTotal(BigDecimal monthYsTotal) {
		this.monthYsTotal = monthYsTotal;
	}

	public BigDecimal getYsTotal() {
		return ysTotal;
	}

	public void setYsTotal(BigDecimal ysTotal) {
		this.ysTotal = ysTotal;
	}

	public BigDecimal getMonthSsTotal() {
		return monthSsTotal;
	}

	public void setMonthSsTotal(BigDecimal monthSsTotal) {
		this.monthSsTotal = monthSsTotal;
	}

	public BigDecimal getSsTotal() {
		return ssTotal;
	}

	public void setSsTotal(BigDecimal ssTotal) {
		this.ssTotal = ssTotal;
	}

	public BigDecimal getCurrYearWaterRate() {
		return currYearWaterRate;
	}

	public void setCurrYearWaterRate(BigDecimal currYearWaterRate) {
		this.currYearWaterRate = currYearWaterRate;
	}

	public BigDecimal getCurrYearYusWaterRate() {
		return currYearYusWaterRate;
	}

	public void setCurrYearYusWaterRate(BigDecimal currYearYusWaterRate) {
		this.currYearYusWaterRate = currYearYusWaterRate;
	}

	public BigDecimal getAgoYearOwnfeeTotal() {
		return agoYearOwnfeeTotal;
	}

	public void setAgoYearOwnfeeTotal(BigDecimal agoYearOwnfeeTotal) {
		this.agoYearOwnfeeTotal = agoYearOwnfeeTotal;
	}

	public BigDecimal getYusTotal() {
		return yusTotal;
	}

	public void setYusTotal(BigDecimal yusTotal) {
		this.yusTotal = yusTotal;
	}

	public BigDecimal getCurrYearQsTotal() {
		return currYearQsTotal;
	}

	public void setCurrYearQsTotal(BigDecimal currYearQsTotal) {
		this.currYearQsTotal = currYearQsTotal;
	}

	public BigDecimal getQsTotal() {
		return qsTotal;
	}

	public void setQsTotal(BigDecimal qsTotal) {
		this.qsTotal = qsTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedCenterCd() {
		return createdCenterCd;
	}

	public void setCreatedCenterCd(String createdCenterCd) {
		this.createdCenterCd = createdCenterCd;
	}

	public String getCreatedDeptCd() {
		return createdDeptCd;
	}

	public void setCreatedDeptCd(String createdDeptCd) {
		this.createdDeptCd = createdDeptCd;
	}

	public String getCreatedPositionCd() {
		return createdPositionCd;
	}

	public void setCreatedPositionCd(String createdPositionCd) {
		this.createdPositionCd = createdPositionCd;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getUpdatedCenterCd() {
		return updatedCenterCd;
	}

	public void setUpdatedCenterCd(String updatedCenterCd) {
		this.updatedCenterCd = updatedCenterCd;
	}

	public String getUpdatedDeptCd() {
		return updatedDeptCd;
	}

	public void setUpdatedDeptCd(String updatedDeptCd) {
		this.updatedDeptCd = updatedDeptCd;
	}

	public String getUpdatedPositionCd() {
		return updatedPositionCd;
	}

	public void setUpdatedPositionCd(String updatedPositionCd) {
		this.updatedPositionCd = updatedPositionCd;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public String getPaymentIntertemporal() {
		return paymentIntertemporal;
	}

	public void setPaymentIntertemporal(String paymentIntertemporal) {
		this.paymentIntertemporal = paymentIntertemporal;
	}

	public BigDecimal getOwePropRate() {
		return owePropRate;
	}

	public void setOwePropRate(BigDecimal owePropRate) {
		this.owePropRate = owePropRate;
	}

	public BigDecimal getOweElecRate() {
		return oweElecRate;
	}

	public void setOweElecRate(BigDecimal oweElecRate) {
		this.oweElecRate = oweElecRate;
	}

	public BigDecimal getCurrYearPropRate() {
		return currYearPropRate;
	}

	public void setCurrYearPropRate(BigDecimal currYearPropRate) {
		this.currYearPropRate = currYearPropRate;
	}

	public BigDecimal getCurrYearElecRate() {
		return currYearElecRate;
	}

	public void setCurrYearElecRate(BigDecimal currYearElecRate) {
		this.currYearElecRate = currYearElecRate;
	}

	public BigDecimal getCurrYearYusPropRate() {
		return currYearYusPropRate;
	}

	public void setCurrYearYusPropRate(BigDecimal currYearYusPropRate) {
		this.currYearYusPropRate = currYearYusPropRate;
	}

	public BigDecimal getCurrYearYusElecRate() {
		return currYearYusElecRate;
	}

	public void setCurrYearYusElecRate(BigDecimal currYearYusElecRate) {
		this.currYearYusElecRate = currYearYusElecRate;
	}

	public BigDecimal getCurrYearYsTotal() {
		return currYearYsTotal;
	}

	public void setCurrYearYsTotal(BigDecimal currYearYsTotal) {
		this.currYearYsTotal = currYearYsTotal;
	}

	public BigDecimal getCurrYearSsTotal() {
		return currYearSsTotal;
	}

	public void setCurrYearSsTotal(BigDecimal currYearSsTotal) {
		this.currYearSsTotal = currYearSsTotal;
	}

}