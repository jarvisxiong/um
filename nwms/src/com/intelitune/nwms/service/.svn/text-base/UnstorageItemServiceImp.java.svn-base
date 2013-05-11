package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnstorageItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.UnstorageItem;

public class UnstorageItemServiceImp implements UnstorageItemService {

	private final static UnstorageItemServiceImp instance =new UnstorageItemServiceImp();
	
	private UnstorageItemServiceImp(){
		
	}
	
	public static final UnstorageItemServiceImp getInstance() {
		return instance;
	}
	public UnstorageItemHome uih = UnstorageItemHome.getInstance();
	public List<UnstorageItem> findNormalByJobId(String jobId) {
		return uih.findByHql(" from UnstorageItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL + "'");
	}

	public void saveUnstorageItem(UnstorageItem unstorageItem) {
		uih.persist(unstorageItem);
	}

	public void updateUnstorageItem(UnstorageItem unstorageItem) {
		uih.merge(unstorageItem);
	}

	public List<UnstorageItem> findCuo(String jobId) {
		return uih.findByHql(" from UnstorageItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL + "' and i.qty<>0");
	}

	public List<UnstorageItem> findDuo(String jobId) {
		return uih.findByHql(" from UnstorageItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL + "' and i.unwantedQty<>0");
	}

	public List<UnstorageItem> findQue(String jobId) {
		return uih.findByHql(" from UnstorageItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL + "' and i.wantQty<>0");
	}

}
