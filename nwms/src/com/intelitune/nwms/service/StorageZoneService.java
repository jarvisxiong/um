package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.StorageZone;

public interface StorageZoneService {
	public void addStorageZone(StorageZone sz);
	
	public List<StorageZone> findStorageZoneList();
	
	public void delStorageZoneById(String id);
	
	public StorageZone findStorageZoneById(String id);
	
	public void modifyStorageZone(StorageZone sz);
	
	
}
