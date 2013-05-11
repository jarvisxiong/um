package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.OutboundRePutAwayItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundRePickItem;
import com.intelitune.nwms.model.OutboundRePutAwayItem;

public class OutboundRePutAwayItemServiceImp implements
		OutboundRePutAwayItemService {
	private final static OutboundRePutAwayItemServiceImp instance =new OutboundRePutAwayItemServiceImp();
	
	private OutboundRePutAwayItemServiceImp(){
		
	}
	
	public static final OutboundRePutAwayItemServiceImp getInstance() {
		return instance;
	}
	public OutboundRePutAwayItemHome oh=OutboundRePutAwayItemHome.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	
	
	public List<OutboundRePutAwayItem> findOutboundRePutAwayItemByTempJobId(
			String jobId) {
		return oh.findOutboundRePutAwayItemByTempJobId(jobId);
	}
	
	public List<OutboundRePutAwayItem> findOutboundRePutAwayItemByJobId(
			String jobId){
		return oh.findOutboundRePutAwayItemByJobId(jobId);
	}
	
	public void delAllByJobId(String job_id) {
		List<OutboundRePutAwayItem> list=oh.findOutboundRePutAwayItemByJobId(job_id);
		for(int i=0;i<list.size();i++){
			OutboundRePutAwayItem orpai=list.get(i);
			orpai.setState(iss.findItemStateByCode(ItemState.DELETE));
			oh.attachDirty(orpai);
		}
		
	}


	public void addOutboundRePutAwayItem(OutboundRePutAwayItem oi) {
		oh.persist(oi);
	}


	public void save(OutboundRePutAwayItem oi) {
		oh.attachDirty(oi);
	}

}
