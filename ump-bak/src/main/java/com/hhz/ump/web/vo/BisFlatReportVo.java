package com.hhz.ump.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BisFlatReportVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bisFlatId;
	private String flatNo;
	private BigDecimal square;
	private BigDecimal innerSquare;
	private BigDecimal publicSquare;
	private List<BisReportTypeDetailVo> detailVoList = new ArrayList<BisReportTypeDetailVo>();

	public String getBisFlatId() {
		return bisFlatId;
	}

	public void setBisFlatId(String bisFlatId) {
		this.bisFlatId = bisFlatId;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public BigDecimal getSquare() {
		return square;
	}

	public void setSquare(BigDecimal square) {
		this.square = square;
	}

	public BigDecimal getInnerSquare() {
		return innerSquare;
	}

	public void setInnerSquare(BigDecimal innerSquare) {
		this.innerSquare = innerSquare;
	}

	public BigDecimal getPublicSquare() {
		return publicSquare;
	}

	public void setPublicSquare(BigDecimal publicSquare) {
		this.publicSquare = publicSquare;
	}

	public List<BisReportTypeDetailVo> getDetailVoList() {
		return detailVoList;
	}

	public void setDetailVoList(List<BisReportTypeDetailVo> detailVoList) {
		this.detailVoList = detailVoList;
	}
}
