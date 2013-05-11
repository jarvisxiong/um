package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.PutAwayItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.PutAwayItem;

public class PutAwayItemServiceImp implements PutAwayItemService {
	
	private final static PutAwayItemServiceImp instance =new PutAwayItemServiceImp();
	
	private PutAwayItemServiceImp(){
		
	}
	
	public static final PutAwayItemServiceImp getInstance() {
		return instance;
	}

	private PutAwayItemHome paih = PutAwayItemHome.getInstance();
	public List<PutAwayItem> findByJobId(String jobId) {
		String hql = " from PutAwayItem i where i.jobId='" + jobId + "'";
		return paih.findByHql(hql);
	}

	public List<PutAwayItem> findNormalByJobId(String jobId) {
		String hql = " from PutAwayItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"'";
		return paih.findByHql(hql);
	}

	public void savePutAwayItem(PutAwayItem putAwayItem) {
		paih.persist(putAwayItem);
	}

	public void updatePutAwayItem(PutAwayItem putAwayItem) {
		paih.merge(putAwayItem);
	}
	
	public void deletePutAwayItem(PutAwayItem putAwayItem){
		paih.delete(putAwayItem);
	}

}
