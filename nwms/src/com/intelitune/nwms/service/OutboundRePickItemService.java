package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.OutboundRePickItem;

public interface OutboundRePickItemService {
	public List<OutboundRePickItem> findOutboundRePickItemByTempJobId(String tempjobId);
	
	public void delAllByJobId(String job_id);
	
	public void addOutboundRePickItem(OutboundRePickItem oi);
	
	public List<OutboundRePickItem> findOutboundRePickItemByJobId(String jobId) ;
	
	public void save(OutboundRePickItem oi);
}
