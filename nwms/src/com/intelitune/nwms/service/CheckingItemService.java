package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.CheckingItem;
/**
 * @author Louis
 */
public interface CheckingItemService {
	/**
	 * @param String jobId
	 * @return List<CheckingItem>
	 * 通过JobId来寻找相应的CheckingItem
	 */
	List<CheckingItem>findByJobId(String jobId);
	/**
	 * @param CheckingItem checkingItem
	 * 保存CheckingItem
	 */
	void saveCheckItem(CheckingItem checkingItem);
	/**
	 * @param CheckingItem checkingItem
	 * 更新CheckingItem
	 */
	void updateCheckItem(CheckingItem checkingItem);
	/**
	 * @param String jobId
	 * @return List<CheckingItem>
	 * 通过JobId来寻找正常状态的CheckingItem
	 */
	List<CheckingItem> findNormalByJobId(String jobId);
	
	List<CheckingItem> findNotComplete(String jobId);
}
