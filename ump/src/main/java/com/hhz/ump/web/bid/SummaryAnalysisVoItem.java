/**  
 * SummaryAnalysisVoItem.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-30        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.bid;  

import java.math.BigDecimal;

/**  
 * ClassName:SummaryAnalysisVoItem  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-30        下午07:05:54  
 *   
 */
public class SummaryAnalysisVoItem  implements java.io.Serializable {

	private String supId;
	private String supName;
	private BigDecimal total;
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
  
