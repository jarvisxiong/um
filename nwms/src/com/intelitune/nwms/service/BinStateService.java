package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.BinState;

public interface BinStateService {
	public BinState findBinStateByCode(int code);
	
	public List<BinState> findBinStateList();
		
	
}
