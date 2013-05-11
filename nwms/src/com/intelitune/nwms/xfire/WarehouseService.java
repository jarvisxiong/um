package com.intelitune.nwms.xfire;

import com.intelitune.nwms.model.Warehouse;

public interface WarehouseService {
	public Warehouse[] findWarehouseList();
	
	public Warehouse findWarehouseById(String id);
}
