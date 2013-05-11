package com.intelitune.nwms.service;

import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.Tier;

public interface TierService {
	public void addTier(Tier t);
	
	public Tier findTierByCode(String row_id,String tier);
		
	public void delTierById(String id); 
	
	public int countTierByRowId(String row_id);
}
