/**  
 * SummaryAnalysis.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-9-17        Administrator  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.bid;  

import java.util.List;

/**  
 * ClassName:SummaryAnalysis  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyb  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-9-17        下午02:33:46  
 *  
 * @see        
 *   
 */

public class SummaryAnalysisVo  implements java.io.Serializable {
	
	private String itemName;
	private String supTotalPrice;
	private  List<String> totalValues;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	public String getSupTotalPrice() {
		return supTotalPrice;
	}
	public void setSupTotalPrice(String supTotalPrice) {
		this.supTotalPrice = supTotalPrice;
	}
	public List<String> getTotalValues() {
		return totalValues;
	}
	public void setTotalValues(List<String> totalValues) {
		this.totalValues = totalValues;
	}
	
	
	
	

}
  
