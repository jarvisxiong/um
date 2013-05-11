/**  
 * UnitEstimateVo.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-23        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.io.Serializable;
import java.math.BigDecimal;

/**  
 * ClassName:UnitEstimateVo  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-23        下午04:18:28  
 *  
 */
public class UnitEstimateVo  implements Serializable{	
	
	private static final long serialVersionUID = -3222264794297123679L;
	
	private String bussinessCd;
	private String bussinessName;
	private BigDecimal weight;
	private BigDecimal initTotal;//初始基准工料价格小计
	private BigDecimal currentMonthTotal;//当月基准工料价格小计
	private BigDecimal priceIndex;//当月工料价格指数Pi
	private BigDecimal basicEstimate;//基准单位估算指标（W0）
	private BigDecimal addPart;//某时点、地区单方指标增加指标ΔW i
	private BigDecimal estimatePrice;//某时点、地区的单位估算指标

	
	public String getBussinessCd() {
		return bussinessCd;
	}
	public void setBussinessCd(String bussinessCd) {
		this.bussinessCd = bussinessCd;
	}
	public String getBussinessName() {
		return bussinessName;
	}
	public void setBussinessName(String bussinessName) {
		this.bussinessName = bussinessName;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getInitTotal() {
		return initTotal;
	}
	public void setInitTotal(BigDecimal initTotal) {
		this.initTotal = initTotal;
	}
	public BigDecimal getCurrentMonthTotal() {
		return currentMonthTotal;
	}
	public void setCurrentMonthTotal(BigDecimal currentMonthTotal) {
		this.currentMonthTotal = currentMonthTotal;
	}
	public BigDecimal getPriceIndex() {
		return priceIndex;
	}
	public void setPriceIndex(BigDecimal priceIndex) {
		this.priceIndex = priceIndex;
	}
	public BigDecimal getBasicEstimate() {
		return basicEstimate;
	}
	public void setBasicEstimate(BigDecimal basicEstimate) {
		this.basicEstimate = basicEstimate;
	}
	public BigDecimal getAddPart() {
		return addPart;
	}
	public void setAddPart(BigDecimal addPart) {
		this.addPart = addPart;
	}
	public BigDecimal getEstimatePrice() {
		return estimatePrice;
	}
	public void setEstimatePrice(BigDecimal estimatePrice) {
		this.estimatePrice = estimatePrice;
	}
	
	
	

}
  
