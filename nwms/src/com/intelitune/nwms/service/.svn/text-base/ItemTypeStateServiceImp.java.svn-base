package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.ItemTypeStateHome;
import com.intelitune.nwms.model.ItemTypeState;

public class ItemTypeStateServiceImp implements ItemTypeStateService {
	public ItemTypeStateHome ih=ItemTypeStateHome.getInstance();
	
	private final static ItemTypeStateServiceImp instance =new ItemTypeStateServiceImp();
	
	private ItemTypeStateServiceImp(){
		
	}
	
	public static final ItemTypeStateServiceImp getInstance() {
		return instance;
	}

	public ItemTypeState findItemTypeStateByCode(int code) {
		return ih.findItemTypeStateByCode(code);
	}
	
	
}
