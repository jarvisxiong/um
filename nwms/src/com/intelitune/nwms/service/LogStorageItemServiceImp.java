package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.LogStorageItemHome;
import com.intelitune.nwms.model.LogStorageItem;

public class LogStorageItemServiceImp implements LogStorageItemService {
	public LogStorageItemHome lh=LogStorageItemHome.getInstance();
	
	
	private final static LogStorageItemServiceImp instance =new LogStorageItemServiceImp();
	
	private LogStorageItemServiceImp(){
		
	}
	
	public static final LogStorageItemServiceImp getInstance() {
		return instance;
	}
	
	public void addLogStorageItem(LogStorageItem li) {
		lh.persist(li);
	}

	public List<LogStorageItem> findLogStorageItemByWarehouseAndClient(
			String owner_id, String warehouse_id,String date1,String date2) {
		return lh.findLogStorageItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
	}

}
