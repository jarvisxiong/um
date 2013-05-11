package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.PickedItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundRePickItem;
import com.intelitune.nwms.model.PickedItem;

public class PickedItemServiceImp implements PickedItemService {
	private final static PickedItemServiceImp instance =new PickedItemServiceImp();
	
	private PickedItemServiceImp(){
		
	}
	
	public static final PickedItemServiceImp getInstance() {
		return instance;
	}
	public PickedItemHome pih=PickedItemHome.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	
	public List<PickedItem> findPickedItemByTempJobId(String job_id) {
		return pih.findPickedItemByTempJobId(job_id);
	}
	
	public List<PickedItem> findPickedItemByJobId(String job_id) {
		return pih.findPickedItemByJobId(job_id);
	}

	public void delAllByJobId(String job_id) {
		List<PickedItem> list=pih.findPickedItemByJobId(job_id);
		for(int i=0;i<list.size();i++){
			PickedItem pi=list.get(i);
			pi.setState(iss.findItemStateByCode(ItemState.DELETE));
			pih.attachDirty(pi);
		}
		
	}

	public void addPickedItem(PickedItem pi) {
		pih.persist(pi);
	}

	public void save(PickedItem pi) {
		pih.attachDirty(pi);
	}

	public String createUuid() {
		String uuid;
		PickedItem pi=new PickedItem();
		pih.persist(pi);
		uuid=pi.getId();
		pih.delete(pi);
		return uuid;
	}

	public List<PickedItem> findPickedItemByOrder_id(String order_id) {
		return pih.findPickedItemByOrder_id(order_id);
	}


	
	
	
	
}
