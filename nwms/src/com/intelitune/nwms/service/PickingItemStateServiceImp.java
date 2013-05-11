package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.PickingItemStateHome;
import com.intelitune.nwms.model.PickingItemState;

public class PickingItemStateServiceImp implements PickingItemStateService {
	
	private final static PickingItemStateServiceImp instance =new PickingItemStateServiceImp();
	
	private PickingItemStateServiceImp(){
		
	}
	
	public static final PickingItemStateServiceImp getInstance() {
		return instance;
	}
	public PickingItemStateHome ph=PickingItemStateHome.getInstance();
	public PickingItemState findStateByCode(int code){
		return ph.findStateByCode(code);
	}
}
