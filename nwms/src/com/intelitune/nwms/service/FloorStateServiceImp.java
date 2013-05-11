package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.FloorStateHome;
import com.intelitune.nwms.model.FloorState;

public class FloorStateServiceImp implements FloorStateService {
	private final static FloorStateServiceImp instance =new FloorStateServiceImp();
	
	private FloorStateServiceImp(){
		
	}
	
	public static final FloorStateServiceImp getInstance() {
		return instance;
	}
	public FloorStateHome fsh=FloorStateHome.getInstance();
	public FloorState findFloorStateByCode(int code) {
		return fsh.findFloorStateByCode(code);
	}

}
