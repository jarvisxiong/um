package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.OutboundRePickItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundRePickItem;

public class OutboundRePickItemServiceImp implements
		OutboundRePickItemService {
	private final static OutboundRePickItemServiceImp instance =new OutboundRePickItemServiceImp();
	
	private OutboundRePickItemServiceImp(){
		
	}
	
	public static final OutboundRePickItemServiceImp getInstance() {
		return instance;
	}
	public OutboundRePickItemHome oh=OutboundRePickItemHome.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	
	public List<OutboundRePickItem> findOutboundRePickItemByTempJobId(String tempjobId) {
		return oh.findOutboundRePickItemByTempJobId(tempjobId);
	}
	
	public List<OutboundRePickItem> findOutboundRePickItemByJobId(String jobId) {
		return oh.findOutboundRePickItemByJobId(jobId);
	}

	
	public void delAllByJobId(String job_id) {
		List<OutboundRePickItem> list=oh.findOutboundRePickItemByJobId(job_id);
		for(int i=0;i<list.size();i++){
			OutboundRePickItem orpi=list.get(i);
			orpi.setState(iss.findItemStateByCode(ItemState.DELETE));
			oh.attachDirty(orpi);
		}
	}



	public void addOutboundRePickItem(OutboundRePickItem oi) {
		oh.persist(oi);
	}



	public void save(OutboundRePickItem oi) {
		oh.attachDirty(oi);
	}

}
