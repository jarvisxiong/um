package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.ItemStateHome;
import com.intelitune.nwms.model.ItemState;

public class OrderItemStateServiceImp implements OrderItemStateService
{
	private final static OrderItemStateServiceImp instance =new OrderItemStateServiceImp();
	
	private OrderItemStateServiceImp(){
		
	}
	
	public static final OrderItemStateServiceImp getInstance() {
		return instance;
	}
	ItemStateHome home=ItemStateHome.getInstance();
	public ItemState findByHql(String str)
	{
	 List<ItemState> li=	home.query(str);
		return li.get(0);
	}
	
	
	public ItemState findByCode(String code)
	{
		List<ItemState> li= home.query("from ItemState i where i.code='"+code+"'");
		return li.get(0);
	}


	public ItemState findById(String id)
	{
		home.findById(id);
		return null;
	}

	public List<ItemState> queryItemState(String str)
	{
	List	li=home.query(str);
		return li;
	}

	public void saveItemState(ItemState st)
	{
		home.persist(st);
	}

	public void updateItemState(ItemState st)
	{
		home.merge(st);
	}
	
}
