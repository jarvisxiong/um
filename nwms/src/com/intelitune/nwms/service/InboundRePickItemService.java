package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.InboundRePickItem;

public interface InboundRePickItemService {

	void saveInboundRePickItem(InboundRePickItem inboundRePickItem);
	void updateInboundRePickItem(InboundRePickItem inboundRePickItem);
	List<InboundRePickItem> findByOrderId(String orderId);
	List<InboundRePickItem> findByJobId(String jobId);
	List<InboundRePickItem> findNormalByJobId(String jobId);
}
