package com.hhz.ump.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BisMultiReportVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bisMultiId;
	private String multiName;
	private String operationArea;
	private BigDecimal square;
	private List<BisReportTypeDetailVo> detailVoList = new ArrayList<BisReportTypeDetailVo>();

	public String getBisMultiId() {
		return bisMultiId;
	}

	public void setBisMultiId(String bisMultiId) {
		this.bisMultiId = bisMultiId;
	}

	public String getMultiName() {
		return multiName;
	}

	public void setMultiName(String multiName) {
		this.multiName = multiName;
	}

	public String getOperationArea() {
		return operationArea;
	}

	public void setOperationArea(String operationArea) {
		this.operationArea = operationArea;
	}

	public BigDecimal getSquare() {
		return square;
	}

	public void setSquare(BigDecimal square) {
		this.square = square;
	}

	public List<BisReportTypeDetailVo> getDetailVoList() {
		return detailVoList;
	}

	public void setDetailVoList(List<BisReportTypeDetailVo> detailVoList) {
		this.detailVoList = detailVoList;
	}
}
