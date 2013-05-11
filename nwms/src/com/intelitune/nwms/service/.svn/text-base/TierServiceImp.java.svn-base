package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.TierHome;
import com.intelitune.nwms.model.Tier;
import com.intelitune.nwms.model.TierState;

public class TierServiceImp implements TierService {
	
	private final static TierServiceImp instance =new TierServiceImp();
	
	private TierServiceImp(){
		
	}
	
	public static final TierServiceImp getInstance() {
		return instance;
	}
	public TierHome th=TierHome.getInstance();
	public TierStateService tss=TierStateServiceImp.getInstance();
	 
	public void addTier(Tier t) {
		th.persist(t);
		
	}

	public Tier findTierByCode(String row_id, String tier) {
		return th.findTierByCode(row_id, tier);
	}

	public void delTierById(String id) {
		Tier t=th.findById(id);
		t.setState(tss.findTierStateByCode(TierState.DELETE));
		th.attachDirty(t);
	}

	public int countTierByRowId(String row_id) {
		return th.countTierByRowId(row_id);
	}
	
	

}
