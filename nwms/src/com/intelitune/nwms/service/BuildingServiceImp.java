package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.BuildingHome;
import com.intelitune.nwms.model.Building;
import com.intelitune.nwms.model.BuildingState;

public class BuildingServiceImp implements BuildingService {
	private final static BuildingServiceImp instance =new BuildingServiceImp();
	
	private BuildingServiceImp(){
		
	}
	
	public static final BuildingServiceImp getInstance() {
		return instance;
	}
	public BuildingHome bh=BuildingHome.getInstance();
	
	public BuildingStateService bss=BuildingStateServiceImp.getInstance();
	
	public void addBuilding(Building b) {
		bh.persist(b);

	}

	public List<Building> findBuildingListByWarehouse_id(String Warehouse_id) {
		List<Building> list=bh.findBuildingListByWarehouse_id(Warehouse_id);
		return list;
	}

	public Building findBuildingById(String id) {
		Building b=bh.findById(id);
		return b;
	}

	public void modifyBuilding(Building b) {
		bh.attachDirty(b);
		
	}

	public void delBuilding(String id) {
		Building b=bh.findById(id);
		BuildingState bs=bss.findBuildingStateByCode(BuildingState.DELETE);
		b.setState(bs);
		bh.attachDirty(b);
	}
	
	public String fingNameById(String id){
		return bh.findById(id).getAlias();
	}

}
