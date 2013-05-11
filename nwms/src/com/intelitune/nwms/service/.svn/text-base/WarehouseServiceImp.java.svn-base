package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.dao.WarehouseHome;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.model.WarehouseState;

public class WarehouseServiceImp implements WarehouseService {

	private final static WarehouseServiceImp instance =new WarehouseServiceImp();
	
	private WarehouseServiceImp(){
		
	}
	
	public static final WarehouseServiceImp getInstance() {
		return instance;
	}
	public WarehouseHome wh=WarehouseHome.getInstance();
	public WarehouseStateService wss=WarehouseStateServiceImp.getInstance();
	public List<Warehouse> findWarehouseList() {
		List<Warehouse> list=wh.findWarehouseList();
		return list;
	}

	public void addWarehouse(Warehouse w) {
		wh.persist(w);
		
	}

	public Warehouse findWarehouseById(String id) {
		Warehouse w=wh.findById(id);
		return w;
	}

	public void modifyWarehouse(Warehouse w) {
		wh.attachDirty(w);
	}
	
	public String findNameById(String id){
		return wh.fingNameById(id);
	}

	public void delWarehouseById(String id) {
		Long i=Const.NOT_IN_USE;
		Warehouse w=wh.findById(id);
		WarehouseState ws=wss.findWarehouseStateByCode(WarehouseState.DELETE);
		w.setState(ws);
		wh.attachDirty(w);
	}



}
