package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.ItemStateHome;
import com.intelitune.nwms.model.ItemState;

public class ItemStateServiceImp implements ItemStateService {
	private final static ItemStateServiceImp instance =new ItemStateServiceImp();
	
	private ItemStateServiceImp(){
		
	}
	
	public static final ItemStateServiceImp getInstance() {
		return instance;
	}
	ItemStateHome ish=ItemStateHome.getInstance();
	public ItemState findItemStateByCode(int code) {
		return ish.findItemStateByCode(code);
	}
	
}
