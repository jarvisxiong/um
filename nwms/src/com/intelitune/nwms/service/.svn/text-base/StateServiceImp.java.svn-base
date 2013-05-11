package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.StateHome;
import com.intelitune.nwms.model.State;

public class StateServiceImp implements StateService {
	
	private final static StateServiceImp instance =new StateServiceImp();
	
	private StateServiceImp(){
		
	}
	
	public static final StateServiceImp getInstance() {
		return instance;
	}
	public StateHome sh=StateHome.getInstance();
	
	public State findStateByCode(Long code) {
		return sh.findStateByCode(code.toString());
	}

	public List<State> findStateList() {
		return sh.findStateList();
	}

	public State findStateById(String id) {
		return sh.findById(id);
	}
	

}
