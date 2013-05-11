package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.RowStateHome;
import com.intelitune.nwms.model.RowState;

public class RowStateServiceImp implements RowStateService {
	
	private final static RowStateServiceImp instance =new RowStateServiceImp();
	
	private RowStateServiceImp(){
		
	}
	
	public static final RowStateServiceImp getInstance() {
		return instance;
	}
	public RowStateHome rsh=RowStateHome.getInstance();
	public RowState findRowStateByCode(int code) {
		return rsh.findRowStateByCode(code);
	}

}
