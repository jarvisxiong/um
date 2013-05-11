package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.ColumnStateHome;
import com.intelitune.nwms.model.ColumnState;

public class ColumnStateServiceImp implements ColumnStateService {
	private final static ColumnStateServiceImp instance =new ColumnStateServiceImp();
	
	private ColumnStateServiceImp(){
		
	}
	
	public static final ColumnStateServiceImp getInstance() {
		return instance;
	}
	ColumnStateHome csh=ColumnStateHome.getInstance();
	public ColumnState findColumnStateByCode(int code) {
		return csh.findColumnStateByCode(code);
	}

}
