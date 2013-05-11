package com.hhz.ump.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BisFlatReportUtil {
   private String bisFlatId;
   private String flatNo;
   private BigDecimal square;
   private BigDecimal innerSquare;
   private BigDecimal publicSquare;
   private List<TenantTypeReportUtil> tenantTypeReportUtils =new ArrayList<TenantTypeReportUtil>();
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
public List<TenantTypeReportUtil> getTenantTypeReportUtils() {
	return tenantTypeReportUtils;
}
public void setTenantTypeReportUtils(
		List<TenantTypeReportUtil> tenantTypeReportUtils) {
	this.tenantTypeReportUtils = tenantTypeReportUtils;
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
}
