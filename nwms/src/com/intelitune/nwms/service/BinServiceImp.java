package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.BinHome;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.BinState;

public class BinServiceImp implements BinService {
	
	private final static BinServiceImp instance =new BinServiceImp();
	
	private BinServiceImp(){
		
	}
	
	public static final BinServiceImp getInstance() {
		return instance;
	}
	
	public BinHome bh=BinHome.getInstance();
	public BinStateService bss=BinStateServiceImp.getInstance();

	public void addBin(Bin b) {
		//添加row对象
		bh.persist(b);
	}


	public void delBinById(String id) {
		Bin b=bh.findById(id);
		b.setState(bss.findBinStateByCode(BinState.DELETE));
		bh.attachDirty(b);
	}


	public List<Bin> findBinByRow_id(String row_id) {	
		return bh.findBinByRow_id(row_id);
	}


	public void modifyBin(Bin b) {
		bh.attachDirty(b);
		
	}


	public Bin findBinById(String id) {
		return bh.findById(id);
	}


	public Bin findBinByCode(String warehouse_id, String code) {
		return bh.findBinByCode(warehouse_id, code.toUpperCase());
	}

		
	
	

}
