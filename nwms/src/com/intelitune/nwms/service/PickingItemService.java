package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.PickingItem;

public interface PickingItemService {
	public void addPickingItem(PickingItem pi);
	
	public PickingItem findPickingItemById(String id);
	
	public void delPickingItem(String id);
	
	public List<PickingItem> findPickingItemByOrderId(String id);
	public List<PickingItem> findPickingItemByOrderIdIngoreStatus(String id);
	
	public List<PickingItem> findPickingItemByOrderIdhebing(String id);
	
	public void transferPickingItemByAssigned(String order_id,String job_id) throws  Exception;
}
