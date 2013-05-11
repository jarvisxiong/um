package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.OutboundOrderItemHome;
import com.intelitune.nwms.model.OutboundOrderItem;

public class OutboundOrderItemServiceImp implements OutboundOrderItemService
{
	private final static OutboundOrderItemServiceImp instance =new OutboundOrderItemServiceImp();
	
	private OutboundOrderItemServiceImp(){
		
	}
	
	public static final OutboundOrderItemServiceImp getInstance() {
		return instance;
	}

	OutboundOrderItemHome home=OutboundOrderItemHome.getInstance();
	public OutboundOrderItem findByHql(String str)
	{
		List<OutboundOrderItem> li= home.findByHql(str);
		return li.get(0);
	}

	public OutboundOrderItem findById(String id)
	{
		return home.findById(id);
	}

	public List<OutboundOrderItem> queryOutboundOrderItem(String str)
	{
		List<OutboundOrderItem> li=home.findByHql(str);
		return li;
	}

	public void saveOutboundOrderItem(OutboundOrderItem outboundOrderItem)
	{
		home.persist(outboundOrderItem);
	}

	public void updateOutboundOrderItem(OutboundOrderItem outboundOrderItem)
	{
		home.merge(outboundOrderItem);
	}

}
