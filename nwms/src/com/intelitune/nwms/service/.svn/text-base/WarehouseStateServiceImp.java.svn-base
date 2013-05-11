package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.WarehouseStateHome;
import com.intelitune.nwms.model.WarehouseState;

public class WarehouseStateServiceImp implements WarehouseStateService {
	private final static WarehouseStateServiceImp instance =new WarehouseStateServiceImp();
	
	private WarehouseStateServiceImp(){
		
	}
	
	public static final WarehouseStateServiceImp getInstance() {
		return instance;
	}
	public WarehouseStateHome wsh=WarehouseStateHome.getInstance();
	
	public WarehouseState findWarehouseStateByCode(int code) {
		return wsh.findWarehouseStateByCode(code);
	}
	

}
