package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.InboundRePutAwayItem;

public interface InboundRePutAwayItemService {

	void saveInboundRePutAwayItem(InboundRePutAwayItem inboundRePutAwayItem);
	void updateInboundRePutAwayItem(InboundRePutAwayItem InboundRePutAwayItem);
	List<InboundRePutAwayItem> findByJobId(String jobId);
	List<InboundRePutAwayItem> findByOrderId(String orderId);
	List<InboundRePutAwayItem> findNormalByJobId(String jobId);
}
