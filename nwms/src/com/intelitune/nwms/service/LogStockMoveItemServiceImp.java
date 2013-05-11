package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.LogStockMoveItemHome;
import com.intelitune.nwms.model.LogStockMoveItem;

public class LogStockMoveItemServiceImp implements LogStockMoveItemService {
private final static LogStockMoveItemServiceImp instance =new LogStockMoveItemServiceImp();
	
	private LogStockMoveItemServiceImp(){
		
	}
	
	public static final LogStockMoveItemServiceImp getInstance() {
		return instance;
	}

	public LogStockMoveItemHome ls=LogStockMoveItemHome.getInstance();
	public void addLog(LogStockMoveItem log) {
		ls.persist(log);
	}

	public List<LogStockMoveItem> findLogByWarehouseAndOwnerAndDate(
			String owner_id, String warehouse_id, String date1, String date2) {
		return ls.findLogByWarehouseAndOwnerAndDate(owner_id, warehouse_id, date1, date2);
	}
}
