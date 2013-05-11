package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.LogStockMoveItem;

public interface LogStockMoveItemService {
	public void addLog(LogStockMoveItem log);
	
	public List<LogStockMoveItem> findLogByWarehouseAndOwnerAndDate(String owner_id,String warehouse_id,String date1,String date2);

}
