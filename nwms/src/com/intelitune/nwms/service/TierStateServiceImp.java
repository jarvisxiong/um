package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.TierStateHome;
import com.intelitune.nwms.model.TierState;

public class TierStateServiceImp implements TierStateService {
	
	private final static TierStateServiceImp instance =new TierStateServiceImp();
	
	private TierStateServiceImp(){
		
	}
	
	public static final TierStateServiceImp getInstance() {
		return instance;
	}
	public TierStateHome tsh=TierStateHome.getInstance();
	public TierState findTierStateByCode(int code) {
		return tsh.findTierStateByCode(code);
	}

}
