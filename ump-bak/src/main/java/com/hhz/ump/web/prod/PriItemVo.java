/**  
 * PriItemVo.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-29        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.io.Serializable;
import java.math.BigDecimal;

/**  
 * ClassName:PriItemVo  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-29        下午04:16:06  
 *    
 */
public class PriItemVo  implements Serializable{
	
	private String materialCd;
	private String areaCd;
	private String titleType;
	private BigDecimal itemVal;
	private BigDecimal matePriceIndex;
	
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}
	public String getMaterialCd() {
		return materialCd;
	}
	public void setMaterialCd(String materialCd) {
		this.materialCd = materialCd;
	}
	public BigDecimal getItemVal() {
		return itemVal;
	}
	public void setItemVal(BigDecimal itemVal) {
		this.itemVal = itemVal;
	}
	public String getTitleType() {
		return titleType;
	}
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	public BigDecimal getMatePriceIndex() {
		return matePriceIndex;
	}
	public void setMatePriceIndex(BigDecimal matePriceIndex) {
		this.matePriceIndex = matePriceIndex;
	}
	
	
}
  
