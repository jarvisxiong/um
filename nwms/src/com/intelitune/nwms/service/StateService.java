package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.State;

public interface StateService {
	public State findStateByCode(Long code);
	
	public List<State> findStateList();
	
	public State findStateById(String id);
}
