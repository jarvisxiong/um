/**  
 * PriceIndex.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-10        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**  
 * ClassName:PriceIndex  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-10        下午04:18:33  
 *
 */
public class PriceIndexVo  implements Serializable{

	//年月+区域+业态+价格指数
	
	private static final long serialVersionUID = -2747568426166069958L;
	
	//private String rowHeader;
	private String bussinessCd;//产品业态
	private String bussinessDesc;	
	private BigDecimal weight;//权重
	private BigDecimal initTotal;//初始小计
	private BigDecimal currentMonthTotal;//当月小计 	
	private BigDecimal priceIndex;//价格指数
	private BigDecimal basicEstimatePrice;//基准单位估算指标
	private List <PriceIndexCellVo> cells;

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
	public List<PriceIndexCellVo> getCells() {
		return cells;
	}
	public void setCells(List<PriceIndexCellVo> cells) {
		this.cells = cells;
	}

	
	public String getBussinessCd() {
		return bussinessCd;
	}
	public void setBussinessCd(String bussinessCd) {
		this.bussinessCd = bussinessCd;
	}
	public String getBussinessDesc() {
		return bussinessDesc;
	}
	public void setBussinessDesc(String bussinessDesc) {
		this.bussinessDesc = bussinessDesc;
	}
	public BigDecimal getBasicEstimatePrice() {
		return basicEstimatePrice;
	}
	public void setBasicEstimatePrice(BigDecimal basicEstimatePrice) {
		this.basicEstimatePrice = basicEstimatePrice;
	}
	
	
}
  
