package com.intelitune.nwms.service;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import com.intelitune.nwms.dao.OrderItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OrderItem;

/**
 * @author wzz
 * @lastModify Jan 14, 2009 11:41:56 AM
 */
public class OrderItemServiceImp implements OrderItemService
{
	private final static OrderItemServiceImp instance =new OrderItemServiceImp();
	
	private OrderItemServiceImp(){
		
	}
	
	public static final OrderItemServiceImp getInstance() {
		return instance;
	}

	OrderItemHome home= OrderItemHome.getInstance();

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:41:58 AM
	 * @param str
	 * @return
	 */
	public OrderItem findByHql(String str)
	{
		List<OrderItem> li= home.query(str);
		return li.get(0);
	}

	public List findOrderItemsByOrderId(String s)
	{
		List<OrderItem> li= home.query("from OrderItem i where i.orderId='" + s + "' and i.state.code='"+ItemState.NORMAL+"'");
		return li;
	}

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:42:02 AM
	 * @param id
	 * @return
	 */
	public OrderItem findById(String id)
	{
		
		return home.findById(id);
	}

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:42:05 AM
	 * @param str
	 * @return
	 */
	public List queryObj(String str)
	{
		List li= home.query(str);
		return li;
	}

	
	public List<OrderItem> findOrderItemByOrder(String str)
	{
		List<OrderItem> li= home.findOrderItemByOrder(str);
		return li;
	}
	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:42:08 AM
	 * @param str
	 * @return
	 */
	public List<OrderItem> queryOrderItem(String str)
	{
		List<OrderItem> li= home.query(str);
		return li;
	}

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:42:10 AM
	 * @param orderItem
	 */
	public void updateOrderItem(OrderItem orderItem)
	{
		home.merge(orderItem);
	}

	/**
	 * @author wzz
	 * @lastModify Jan 14, 2009 11:42:13 AM
	 * @param orderItem
	 */
	public void saveOrderItem(OrderItem orderItem)
	{
		home.persist(orderItem);
	}

	public List<OrderItem> findByJobId(String jobId)
	{
		String hql= " from OrderItem";
		// " i where i.jobId='" + jobId + "'";
		return home.findByHql(hql);
	}

	public int NormalItemsQtyOfOrder(int orderId)
	{
		String str= "select count(*) from  OrderItem o where  o.orderId='" + orderId
		+ "'  and  o.state.code='" + ItemState.NORMAL.toString() + "' group by o.orderId";
		List li= null;

		li= home.query(str);

		Iterator itq= li.iterator();
		if (itq.hasNext())
		{
			Object[] result= (Object[]) itq.next();
			return Integer.valueOf( result[1].toString());
		}
		return 0;
	}
	
	public int findItemNumByOrderId(String order_id){
		return home.findItemNumByOrderId(order_id);
	}

	public void modifyNum(String orderItemId, float num) {
		OrderItem oi=home.findById(orderItemId);
		oi.setQty(num);
		home.attachDirty(oi);
	}

}
