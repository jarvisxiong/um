package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.OrderItem;

/**
 * @author wzz
 * @lastModify Jan 14, 2009 11:40:44 AM
 */
public interface InboundOrderItemService
{

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:23 AM
	 * @param orderItem
	 */
	void saveInboundOrderItem(InboundOrderItem orderItem);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:25 AM
	 * @param orderItem
	 */

	void updateInboundOrderItem(InboundOrderItem orderItem);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:31 AM
	 * @param id
	 * @return
	 */
	InboundOrderItem findById(String id);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:33 AM
	 * @param str
	 * @return
	 */
	InboundOrderItem findByHql(String str);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:35 AM
	 * @param str
	 * @return
	 */
	List<InboundOrderItem> queryInboundOrderItem(String str);

	
	List<InboundOrderItem> findByOrderId(String orderId);
	
	InboundOrderItem findByProductCode(String code,String orderId);

	List<InboundOrderItem> findNormalByOrderId(String orderId);
	

	

	
}
