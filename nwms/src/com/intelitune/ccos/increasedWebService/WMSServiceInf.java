package com.intelitune.ccos.increasedWebService;

import java.util.List;

import com.intelitune.ccos.model.WmsOrder;

public interface WMSServiceInf 
{
   public WmsOrder findWmsOrderById(int in0) throws java.rmi.RemoteException;
   public WmsOrder findByJobId(java.lang.String in0) throws java.rmi.RemoteException;
   
   public List findAllWmsOrder() throws java.rmi.RemoteException;
   public List queryStr(String s)throws java.rmi.RemoteException;
  
   
	 public List findOutboundType()throws java.rmi.RemoteException;
	  public List  findInboundType() throws java.rmi.RemoteException;
	 
	  public List findNotOutboundOrder()throws java.rmi.RemoteException;
	  public List findAlreadyOutboundOrder()throws java.rmi.RemoteException;
	  
	  
	  /**
	 *	@author     wzz
	 *	@lastModify Jan 20, 2009  5:45:20 PM
	 * @return list
	 * 查询所有处于下架中的出库订单;
	 */
	public List<WmsOrder> findOutboundInPicking();
	  public void addItemToOrder(WmsOrder wmsOrder,String itemId);
	  public void deleteItemfromOrder(WmsOrder wmsOrder, String itemId);
	  /**
	 *	@author     wzz
	 *	@lastModify Jan 20, 2009  4:55:37 PM
	 * @param wmsOrderId
	 * 改变订单状态为"下架中";
	 */
	public void alterToOutboundInPicking(int  wmsOrderId);
	  /**
	 *	@author     wzz
	 *	@lastModify Jan 20, 2009  4:55:39 PM
	 * @param wmsOrderId
	 * 改变订单状态为"已出库";
	 */
	public void alterToOutboundComplete(int wmsOrderId);
	
	/**
	 *	@author     wzz
	 *	@lastModify Jan 20, 2009  5:44:14 PM
	 * @param wmsOrderId
	 * 改变订单状态为"已验货"
	 */
	public void alterToInboundChecked(int wmsOrderId);
	/**
	 *	@author     wzz
	 *	@lastModify Jan 20, 2009  5:44:15 PM
	 * @param wmsOrderId
	 * 改变订单状态为"已进库"
	 */
	public void alterToInboundComplete(int wmsOrderId);
	public void saveOrUpdate(Object obj);
}
 