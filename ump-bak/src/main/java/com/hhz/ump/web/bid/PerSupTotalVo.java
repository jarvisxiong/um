/**  
 * PerSupTotalVo.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-10-14        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.bid;  

import java.math.BigDecimal;

/**  
 * ClassName:PerSupTotalVo  
 * Function:  单个供应商工程汇总显示模型
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-10-14        上午09:18:52  
 *   
 */
public class PerSupTotalVo  implements java.io.Serializable {
	private Integer lineNo;
	private String tableName;
	private BigDecimal totalValue;

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	

}
  
