package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.CheckingItemHome;
import com.intelitune.nwms.model.CheckingItem;
import com.intelitune.nwms.model.ItemState;
/**
 * 
 * @author Louis
 *
 */
public class CheckingItemServiceImpl implements CheckingItemService {
	private final static CheckingItemServiceImpl instance =new CheckingItemServiceImpl();
	
	private CheckingItemServiceImpl(){
		
	}
	
	public static final CheckingItemServiceImpl getInstance() {
		return instance;
	}
	
	public CheckingItemHome cih = CheckingItemHome.getInstance();

	public List<CheckingItem> findByJobId(String jobId) {
		String hql = " from CheckingItem i where i.jobId='" + jobId + "'";
		return (List<CheckingItem>)cih.findByHql(hql);	
	}

	public void saveCheckItem(CheckingItem checkingItem) {
		cih.saveCheckingItem(checkingItem);
	}

	public void updateCheckItem(CheckingItem checkingItem){
		cih.merge(checkingItem);
	}

	public List<CheckingItem> findNormalByJobId(String jobId) {
		String hql = " from CheckingItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL+"'";
		return (List<CheckingItem>)cih.findByHql(hql);
	}
	
	public List<CheckingItem> findNotComplete(String jobId) {
		String hql = " from CheckingItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL +"' and i.arrivedQty<>i.putawayQty";
		return cih.findByHql(hql);
	}
}
