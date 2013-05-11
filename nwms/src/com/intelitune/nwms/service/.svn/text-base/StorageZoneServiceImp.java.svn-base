package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.BinHome;
import com.intelitune.nwms.dao.StorageZoneHome;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.ZoneState;

public class StorageZoneServiceImp implements StorageZoneService {
	

	private final static StorageZoneServiceImp instance =new StorageZoneServiceImp();
	
	private StorageZoneServiceImp(){
		
	}
	
	public static final StorageZoneServiceImp getInstance() {
		return instance;
	}
	
	
	public StorageZoneHome szh=StorageZoneHome.getInstance();
	public ZoneStateService zss=ZoneStateServiceImp.getInstance();
	
	public void addStorageZone(StorageZone sz) {
		szh.persist(sz);
		
	}

	public List<StorageZone> findStorageZoneList() {
		return szh.findStorageZoneList();
	}

	public void delStorageZoneById(String id) {
		StorageZone sz=szh.findById(id);
		sz.setState(zss.findZoneStateByCode(ZoneState.DELETE));
		szh.attachDirty(sz);
	}

	public StorageZone findStorageZoneById(String id) {
		return szh.findStorageZoneById(id);
	}

	public void modifyStorageZone(StorageZone sz) {
		szh.attachDirty(sz);
	}

}
