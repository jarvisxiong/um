package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UncheckedItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.UncheckedItem;

public class UncheckedItemServiceImp implements UncheckedItemService {
	
	private final static UncheckedItemServiceImp instance =new UncheckedItemServiceImp();
	
	private UncheckedItemServiceImp(){
		
	}
	
	public static final UncheckedItemServiceImp getInstance() {
		return instance;
	}
	
	public UncheckedItemHome uih = UncheckedItemHome.getInstance();

	public List<UncheckedItem> findNormalByJobId(String jobId) {
		return uih.findByHql(" from UncheckedItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"'");
	}

	public void saveUncheckedItem(UncheckedItem unCheckedItem) {
		uih.persist(unCheckedItem);
	}

	public void updateUncheckedItem(UncheckedItem uncheckedItem) {
		uih.merge(uncheckedItem);
	}

	public List<UncheckedItem> findBad(String jobId) {
		return uih.findByHql(" from UncheckedItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"' and i.destroyedQty<>0" );
	}

	public List<UncheckedItem> findQue(String jobId) {
		return uih.findByHql(" from UncheckedItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"' and i.wantQty<>0");
	}

	public List<UncheckedItem> findDuo(String jobId) {
		return uih.findByHql(" from UncheckedItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"' and i.unwantedQty<>0");
	}

}
