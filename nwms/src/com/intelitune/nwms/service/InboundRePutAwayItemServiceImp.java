package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InboundRePutAwayItemHome;
import com.intelitune.nwms.model.InboundRePutAwayItem;
import com.intelitune.nwms.model.ItemState;

public class InboundRePutAwayItemServiceImp implements
		InboundRePutAwayItemService {
	
	private final static InboundRePutAwayItemServiceImp instance =new InboundRePutAwayItemServiceImp();
	
	private InboundRePutAwayItemServiceImp(){
		
	}
	
	public static final InboundRePutAwayItemServiceImp getInstance() {
		return instance;
	}
	private InboundRePutAwayItemHome irpai = InboundRePutAwayItemHome.getInstance();

	public List<InboundRePutAwayItem> findByJobId(String jobId) {
		String hql = " from InboundRePutAwayItem i where i.jobId='" + jobId +"'";
		return irpai.findByHql(hql);
	}

	public List<InboundRePutAwayItem> findByOrderId(String orderId) {
		String hql = " from InboundRePutAwayItem i where i.orderId='" + orderId +"'";
		return irpai.findByHql(hql);
	}

	public List<InboundRePutAwayItem> findNormalByJobId(String jobId) {
		String hql = " from InboundRePutAwayItem i where i.jobId='" + jobId +"' and i.state.code='" + ItemState.NORMAL +"'";
		return irpai.findByHql(hql);
	}

	public void saveInboundRePutAwayItem(
			InboundRePutAwayItem inboundRePutAwayItem) {
		irpai.persist(inboundRePutAwayItem);		
	}

	public void updateInboundRePutAwayItem(
			InboundRePutAwayItem InboundRePutAwayItem) {
		irpai.merge(InboundRePutAwayItem);
	}

}
