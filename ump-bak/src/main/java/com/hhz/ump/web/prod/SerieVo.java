/**  
 * SerieVo.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-24        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**  
 * ClassName:SerieVo  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-24        下午03:39:40  
 *    
 */
public class SerieVo  implements Serializable{

		
	private static final long serialVersionUID = 2558097705609397157L;
	private String name;
	private List<BigDecimal> data;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BigDecimal> getData() {
		return data;
	}
	public void setData(List<BigDecimal> data) {
		this.data = data;
	}
	
	
	

}
  
