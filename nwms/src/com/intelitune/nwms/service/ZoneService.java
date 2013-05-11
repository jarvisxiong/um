package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Zone;

public interface ZoneService {
	public List<Zone> findZoneList();
	
	
	public Zone findZoneById(String id);
}
