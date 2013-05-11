/**  
 * PriceIndexCell.java  
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

/**  
 * ClassName:PriceIndexCell  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-10        下午03:08:28  
 *  
 */
public class PriceIndexCellVo implements Serializable{


	
	private static final long serialVersionUID = 6399302509416917585L;
	
	private String bussinessCd;
	private String bussinessName;
	private int col;
	private int row;
	private String firstHeader;
	private String secHeader;
	private String propertyName;
	private BigDecimal value;
	private BigDecimal weight;
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
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getFirstHeader() {
		return firstHeader;
	}
	public void setFirstHeader(String firstHeader) {
		this.firstHeader = firstHeader;
	}
	public String getSecHeader() {
		return secHeader;
	}
	public void setSecHeader(String secHeader) {
		this.secHeader = secHeader;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
	
}
  
