package com.intelitune.nwms.service;

import com.intelitune.nwms.model.BuildingState;

public interface BuildingStateService {
	public BuildingState findBuildingStateByCode(int code);
}
