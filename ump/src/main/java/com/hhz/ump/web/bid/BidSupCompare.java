/**  
 * BidSupCompare.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-9-16        Administrator  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
/**  
 * BidSupCompare.java  
 * com.hhz.ump.web.bid  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-9-16 Administrator  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
  
package com.hhz.ump.web.bid;  

import com.hhz.ump.entity.bid.BidSup;

/**  
 * ClassName:BidSupCompare  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyb  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-9-16        下午12:57:44  
 *  
 * @see        
 * @deprecated   
 */
/**  
 * ClassName:BidSupCompare  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   Administrator  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011    2011-9-16     下午12:57:44  
 *  
 * @see        
 * 
 */



public class BidSupCompare implements Comparable<BidSup>{


	public int compareTo(BidSup o1,BidSup o2) {		  
		if(o1.getDispOrderNo()>o2.getDispOrderNo())
			return 0;
		else
			return 1;
		
		
	}
	@Override
	public int compareTo(BidSup o) {
		  
		// TODO Auto-generated method stub  
		return 0;  
		
	}

}
  
