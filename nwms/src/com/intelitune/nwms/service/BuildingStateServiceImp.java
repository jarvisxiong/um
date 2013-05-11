package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.BuildingStateHome;
import com.intelitune.nwms.model.BuildingState;

public class BuildingStateServiceImp implements BuildingStateService {
	private final static BuildingStateServiceImp instance =new BuildingStateServiceImp();
	
	private BuildingStateServiceImp(){
		
	}
	
	public static final BuildingStateServiceImp getInstance() {
		return instance;
	}
	public BuildingStateHome bsh=BuildingStateHome.getInstance();
	public BuildingState findBuildingStateByCode(int code) {
		BuildingState bs=bsh.findBuildingStateByCode(code);
		return bs;
	}
	
}
