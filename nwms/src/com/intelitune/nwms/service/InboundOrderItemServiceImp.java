package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InboundOrderItemHome;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.ItemState;

public class InboundOrderItemServiceImp implements InboundOrderItemService
{
	private final static InboundOrderItemServiceImp instance =new InboundOrderItemServiceImp();
	
	private InboundOrderItemServiceImp(){
		
	}
	
	public static final InboundOrderItemServiceImp getInstance() {
		return instance;
	}

	public InboundOrderItemHome home=InboundOrderItemHome.getInstance();
	public InboundOrderItem findByHql(String str)
	{
	   List<InboundOrderItem> li=	home.findByHql(str);
		return li.get(0);
	}

	public InboundOrderItem findById(String id)
	{
		return home.findById(id);
	}



	public List<InboundOrderItem> queryInboundOrderItem(String str)
	{
		List li=home.findByHql(str);
		return li;
	}

	public void saveInboundOrderItem(InboundOrderItem inboundOrderItem)
	{
		home.persist(inboundOrderItem);
	}

	public void updateInboundOrderItem(InboundOrderItem inboundOrderItem)
	{
		home.merge(inboundOrderItem);
	}

	public List<InboundOrderItem> findByOrderId(String orderId) {
		String hql = " from InboundOrderItem i where i.orderId='" + orderId+ "' and i.state.code='"+ItemState.NORMAL+"'";
		return home.findByHql(hql);	
	}
	
	public List<InboundOrderItem> findNormalByOrderId(String orderId) {
		String hql = " from InboundOrderItem i where i.orderId='" + orderId+ "' and i.state.code='" + ItemState.NORMAL+"'";
		return home.findByHql(hql);	
	}
	
	public InboundOrderItem findByProductCode(String code,String orderId){
		String hql = " from InboundOrderItem i where i.orderId='" + orderId+ "' and i.material.code='" + code + "'";
		List list = home.findByHql(hql);
		if(list.size()==0){
			return null;
		}else{
			return (InboundOrderItem)list.get(0);
		}
			
	}

}
