package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Warehouse;


public interface WarehouseService {
	public List<Warehouse> findWarehouseList();
	
	
	public void addWarehouse(Warehouse w);
	
	public Warehouse findWarehouseById(String id);
	
	public void modifyWarehouse(Warehouse w);
	
	public String findNameById(String id);
	
	public void delWarehouseById(String id);
	

}

