package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.PutAwayItem;

public interface PutAwayItemService {

	void savePutAwayItem(PutAwayItem putAwayItem);
	void updatePutAwayItem(PutAwayItem putAwayItem);
	List<PutAwayItem> findByJobId(String jobId);
	List<PutAwayItem> findNormalByJobId(String jobId);
	void deletePutAwayItem(PutAwayItem putAwayItem);
}
