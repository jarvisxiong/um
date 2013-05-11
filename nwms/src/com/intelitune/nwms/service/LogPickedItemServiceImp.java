package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.LogPickedItemHome;
import com.intelitune.nwms.model.LogPickedItem;

public class LogPickedItemServiceImp  implements LogPickedItemService  {
	
	public LogPickedItemHome lh=LogPickedItemHome.getInstance();
	
	private final static LogPickedItemServiceImp instance =new LogPickedItemServiceImp();
	
	private LogPickedItemServiceImp(){
		
	}
	
	public static final LogPickedItemServiceImp getInstance() {
		return instance;
	}
	
	public void addLogPickedItem(LogPickedItem li) {
		lh.persist(li);
	}

	public List<LogPickedItem> findLogPickedItemByWarehouseAndClient(
			String owner_id, String warehouse_id,String date1,String date2) {
		return lh.findLogPickedItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
	}
	
}
