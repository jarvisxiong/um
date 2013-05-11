package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.OutboundAssigned;

public interface OutboundAssignedService {
	public List<OutboundAssigned> findAssignedByOrderId(String orderId);
	
	public List<OutboundAssigned> findAssignedByOrderIdAndProductCode(String orderId,String productcode);
		
	public void addOutboundAssigned(OutboundAssigned oa);
	
	public List<OutboundAssigned> findByOrderId(String order_id);
	
	public void queryStr(String hql);
	
	public List<OutboundAssigned> findByItemAndOrder(String order_id,String item_id);
	
	public void delOutboundAssigned(String order_id,String item_id);
}
