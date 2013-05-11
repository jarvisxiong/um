/**  
 * ChartVo.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-24        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.io.Serializable;
import java.util.List;

/**  
 * ClassName:ChartVo  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-24        下午03:37:50  
 *  
 */
public class ChartVo  implements Serializable{

	
	
	private static final long serialVersionUID = -1031882537868329058L;
	
	private List<String> categories;
	private List<SerieVo> series;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<SerieVo> getSeries() {
		return series;
	}
	public void setSeries(List<SerieVo> series) {
		this.series = series;
	}
	
	

}
  
