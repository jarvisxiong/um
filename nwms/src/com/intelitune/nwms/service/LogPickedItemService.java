package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.LogPickedItem;

public interface LogPickedItemService {
	public void addLogPickedItem(LogPickedItem li);
	
	public List<LogPickedItem> findLogPickedItemByWarehouseAndClient(String owner_id,String warehouse_id,String date1,String date2);
}
