package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.ZoneStateHome;
import com.intelitune.nwms.model.ZoneState;

public class ZoneStateServiceImp implements ZoneStateService {
	private final static ZoneStateServiceImp instance =new ZoneStateServiceImp();
	
	private ZoneStateServiceImp(){
		
	}
	
	public static final ZoneStateServiceImp getInstance() {
		return instance;
	}
	public ZoneStateHome zsh=ZoneStateHome.getInstance();
	public ZoneState findZoneStateByCode(int code) {
		return zsh.findZoneStateByCode(code);
	}

}
