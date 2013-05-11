package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.FloorHome;
import com.intelitune.nwms.model.Floor;
import com.intelitune.nwms.model.FloorState;

public class FloorServiceImp implements FloorService {
	private final static FloorServiceImp instance =new FloorServiceImp();
	
	private FloorServiceImp(){
		
	}
	
	public static final FloorServiceImp getInstance() {
		return instance;
	}
	public FloorHome fh=FloorHome.getInstance();
	public FloorStateService fss=FloorStateServiceImp.getInstance();
	
	public void addFloor(Floor f) {
		fh.persist(f);
		
	}
 
	public List<Floor> findFloorListByBuilding_id(String building_id) {
		return fh.findFloorList(building_id);
	}

	public void delFloorById(String id){
		Floor f=fh.findById(id);
		FloorState s=fss.findFloorStateByCode(FloorState.DELETE);
		f.setState(s);
		fh.attachDirty(f);
	}
	
	public Floor findFloorById(String id){
		return fh.findById(id);
	}

	public void modifyFloor(Floor f) {
		fh.attachDirty(f);
	}

	public String findNameById(String id) {
		return fh.findById(id).getAlias();
	}
	
}
