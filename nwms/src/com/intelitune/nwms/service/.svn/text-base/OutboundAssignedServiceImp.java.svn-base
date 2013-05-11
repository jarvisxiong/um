package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.OutboundAssignedHome;
import com.intelitune.nwms.model.OutboundAssigned;

public class OutboundAssignedServiceImp implements OutboundAssignedService{

	private final static OutboundAssignedServiceImp instance =new OutboundAssignedServiceImp();
	
	private OutboundAssignedServiceImp(){
		
	}
	
	public static final OutboundAssignedServiceImp getInstance() {
		return instance;
	}
	
	
	public OutboundAssignedHome oah=OutboundAssignedHome.getInstance();
	public List<OutboundAssigned> findAssignedByOrderId(String orderId){
		return  oah.findAssignedByOrderId(orderId);
	}
	
	public List<OutboundAssigned> findAssignedByOrderIdAndProductCode(String orderId,String productcode){
		return oah.findAssignedByOrderIdAndProductCode(orderId, productcode);
	}

	public void addOutboundAssigned(OutboundAssigned oa) {
		oah.persist(oa);
	}
	
	public void queryStr(String hql) {
		oah.queryStr(hql);
	}

	public List<OutboundAssigned> findByOrderId(String order_id) {
		return oah.findAssignedByOrderId(order_id);
	}

	public List<OutboundAssigned> findByItemAndOrder(String order_id,
			String item_id) {
		return oah.findByItemAndOrder(order_id, item_id);
	}

	public void delOutboundAssigned(String order_id, String item_id) {
		List<OutboundAssigned> list=oah.findByItemAndOrder(order_id, item_id);
		for(int i=0;i<list.size();i++){
			OutboundAssigned oa=list.get(i);
			oa.setIsDelete(1);
			oah.attachDirty(oa);
		}
	}


}
