package com.intelitune.nwms.service;

import com.intelitune.nwms.model.ZoneState;

public interface ZoneStateService {
	public ZoneState findZoneStateByCode(int code);
}
