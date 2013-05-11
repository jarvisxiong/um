package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InboundRePickItemHome;
import com.intelitune.nwms.model.InboundRePickItem;
import com.intelitune.nwms.model.ItemState;

public class InboundRePickItemServiceImp implements InboundRePickItemService {

	private final static InboundRePickItemServiceImp instance =new InboundRePickItemServiceImp();
	
	private InboundRePickItemServiceImp(){
		
	}
	
	public static final InboundRePickItemServiceImp getInstance() {
		return instance;
	}
	private InboundRePickItemHome irpih = InboundRePickItemHome.getInstance();
	public List<InboundRePickItem> findByJobId(String jobId) {
		String hql = " from InboundRePickItem i where i.jobId='" + jobId +"'";
		return irpih.findByHql(hql);
	}

	public List<InboundRePickItem> findByOrderId(String orderId) {
		String hql = " from InboundRePickItem i where i.orderId='" + orderId +"'";
		return irpih.findByHql(hql);
	}

	public List<InboundRePickItem> findNormalByJobId(String jobId) {
		String hql = " from InboundRePickItem i where i.jobId='" + jobId +"' and i.state.code='" + ItemState.NORMAL +"'";
		return irpih.findByHql(hql);
	}

	public void saveInboundRePickItem(InboundRePickItem inboundRePickItem) {
		irpih.persist(inboundRePickItem);
	}

	public void updateInboundRePickItem(InboundRePickItem inboundRePickItem) {
		irpih.merge(inboundRePickItem);
	}

}
