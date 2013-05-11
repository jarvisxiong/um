package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.ZoneHome;
import com.intelitune.nwms.model.Zone;

public class ZoneServiceImp implements ZoneService {
	private final static ZoneServiceImp instance =new ZoneServiceImp();
	
	private ZoneServiceImp(){
		
	}
	
	public static final ZoneServiceImp getInstance() {
		return instance;
	}
	public ZoneHome zh=ZoneHome.getInstance();
	public List<Zone> findZoneList() {
		return zh.findZoneList();
	}
	public Zone findZoneById(String id) {
		return zh.findById(id);
	}

}
