package com.intelitune.nwms.service;

import com.intelitune.nwms.model.TierState;

public interface TierStateService {
	public TierState findTierStateByCode(int code);
}
