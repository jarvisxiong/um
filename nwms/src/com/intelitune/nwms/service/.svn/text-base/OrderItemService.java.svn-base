package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.OrderItem;

/**
 * @author wzz
 * @lastModify Jan 14, 2009 11:40:44 AM
 */
public interface OrderItemService
{

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:23 AM
	 * @param orderItem
	 */
	void saveOrderItem(OrderItem orderItem);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:25 AM
	 * @param orderItem
	 */

	void updateOrderItem(OrderItem orderItem);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:31 AM
	 * @param id
	 * @return
	 */
	OrderItem findById(String id);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:33 AM
	 * @param str
	 * @return
	 */
	OrderItem findByHql(String str);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:35 AM
	 * @param str
	 * @return
	 */
	List<OrderItem> queryOrderItem(String str);

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:38 AM
	 * @param str
	 * @return
	 */
	List queryObj(String str);

	/**
	 *	@author     wzz
	 *	@lastModify Jan 16, 2009  12:21:23 PM
	 * @param s
	 * @return
	 */
	
	List<OrderItem> findOrderItemByOrder(String str);
	
	void modifyNum(String orderItemId,float num);
	
	/**
	 *	@author     wzz
	 *	@lastModify Jan 19, 2009  10:19:57 AM
	 * @param s
	 * @return
	 */
	List findOrderItemsByOrderId(String s);

	/**
	 *	@author     wzz
	 *	@lastModify Jan 19, 2009  10:19:49 AM
	 * @param s
	 * @return
	 */
	List<OrderItem> findByJobId(String jobId);
	
	public int NormalItemsQtyOfOrder(int orderId);
	
	public int findItemNumByOrderId(String order_id);
}
