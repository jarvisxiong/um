package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Floor;

public interface FloorService {
	public void addFloor(Floor f);
	
	public List<Floor> findFloorListByBuilding_id(String building_id);
	
	public void delFloorById(String id);
	
	public Floor findFloorById(String id);
	
	public void modifyFloor(Floor f);
	
	public String findNameById(String id);
		
	
}
