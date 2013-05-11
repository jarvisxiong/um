package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.BinStateHome;
import com.intelitune.nwms.model.BinState;

public class BinStateServiceImp implements BinStateService {
	
	private final static BinStateServiceImp instance =new BinStateServiceImp();
	
	private BinStateServiceImp(){
		
	}
	
	public static final BinStateServiceImp getInstance() {
		return instance;
	}
	public BinStateHome bsh=BinStateHome.getInstance();
	public BinState findBinStateByCode(int code) {
		
		return bsh.findBinStateByCode(code);
	}
	public List<BinState> findBinStateList() {
		return bsh.findBinStateList();
	}

}
