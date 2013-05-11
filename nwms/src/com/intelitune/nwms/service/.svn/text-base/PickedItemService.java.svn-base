package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.PickedItem;

public interface PickedItemService {
	
	public List<PickedItem> findPickedItemByTempJobId(String job_id);
	
	public void delAllByJobId(String job_id);
	
	public void addPickedItem(PickedItem pi);
	
	public void save(PickedItem pi);
	
	public String createUuid();
	
	public List<PickedItem> findPickedItemByJobId(String job_id);
	
	public List<PickedItem> findPickedItemByOrder_id(String order_id);
}
