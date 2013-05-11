package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.UnstorageItem;

public interface UnstorageItemService {

	public void saveUnstorageItem(UnstorageItem unstorageItem);
	public void updateUnstorageItem(UnstorageItem unstorageItem);
	public List<UnstorageItem> findNormalByJobId(String jobId);
	public List<UnstorageItem> findCuo(String jobId);
	public List<UnstorageItem> findQue(String jobId);
	public List<UnstorageItem> findDuo(String jobId);
}
