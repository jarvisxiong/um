/**  
 * PriceIndexVotwo.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-29        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.util.List;

/**  
 * ClassName:PriceIndexVotwo  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-29        下午04:04:56  
 *    
 */
public class PriceIndexVotwo {
	
	private String rowName;
	private String yearCd;
	private String monthCd;
	private String materailCd;
	private String bussinessCd;	
	private String ym;//yearCd-monthCd
	private String rowType;//基准-1,其他-0
	private Integer lineNo;//行号
	private String areaCd;//基准的时候为空
	//工料范围及器相应的值
	private List<PriItemVo> itemVos;
	private  String mateTotalPrice;//工料加权价(元)
	private String mateTotalDifference;//工料加权差额
	private String basiConstructUnitPrice;//基准建安单价造价(元)
	private String constructUnitPrice;//建安单价造价(元)
	private String priceIndex;//工料价格指数
	private String startMerge;
	
	
	
	public String getYearCd() {
		return yearCd;
	}
	public void setYearCd(String yearCd) {
		this.yearCd = yearCd;
	}
	public String getMonthCd() {
		return monthCd;
	}
	public void setMonthCd(String monthCd) {
		this.monthCd = monthCd;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}
	public List<PriItemVo> getItemVos() {
		return itemVos;
	}
	public void setItemVos(List<PriItemVo> itemVos) {
		this.itemVos = itemVos;
	}
	public String getMateTotalPrice() {
		return mateTotalPrice;
	}
	public void setMateTotalPrice(String mateTotalPrice) {
		this.mateTotalPrice = mateTotalPrice;
	}
	public String getMateTotalDifference() {
		return mateTotalDifference;
	}
	public void setMateTotalDifference(String mateTotalDifference) {
		this.mateTotalDifference = mateTotalDifference;
	}
	public String getConstructUnitPrice() {
		return constructUnitPrice;
	}
	public void setConstructUnitPrice(String constructUnitPrice) {
		this.constructUnitPrice = constructUnitPrice;
	}

	public String getPriceIndex() {
		return priceIndex;
	}
	public void setPriceIndex(String priceIndex) {
		this.priceIndex = priceIndex;
	}
	public String getMaterailCd() {
		return materailCd;
	}
	public void setMaterailCd(String materailCd) {
		this.materailCd = materailCd;
	}
	public String getBussinessCd() {
		return bussinessCd;
	}
	public void setBussinessCd(String bussinessCd) {
		this.bussinessCd = bussinessCd;
	}
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public String getStartMerge() {
		return startMerge;
	}
	public void setStartMerge(String startMerge) {
		this.startMerge = startMerge;
	}
	public String getBasiConstructUnitPrice() {
		return basiConstructUnitPrice;
	}
	public void setBasiConstructUnitPrice(String basiConstructUnitPrice) {
		this.basiConstructUnitPrice = basiConstructUnitPrice;
	}
	
	
	
	

}
  
