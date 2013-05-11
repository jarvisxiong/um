package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.UncheckedItem;

public interface UncheckedItemService {
	
	public List<UncheckedItem> findNormalByJobId(String jobId);
	public void updateUncheckedItem(UncheckedItem uncheckedItem);
	public void saveUncheckedItem(UncheckedItem unCheckedItem);
	public List<UncheckedItem> findBad(String jobId);
	public List<UncheckedItem> findQue(String jobId);
	public List<UncheckedItem> findDuo(String jobId);
}
