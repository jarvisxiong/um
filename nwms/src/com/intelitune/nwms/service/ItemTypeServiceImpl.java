package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.ItemTypeHome;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.ItemTypeState;

public class ItemTypeServiceImpl implements ItemTypeService {
	public ItemTypeStateService its=ItemTypeStateServiceImp.getInstance();
	public ItemTypeHome ith=ItemTypeHome.getInstance();
	private final static ItemTypeServiceImpl instance =new ItemTypeServiceImpl();
	
	private ItemTypeServiceImpl(){
		
	}
	
	public static final ItemTypeServiceImpl getInstance() {
		return instance;
	}

	public List<ItemType> findItemTypeList() {
		return ith.findItemTypeList();
	}

	public void addItemType(ItemType it) {
		ith.persist(it);
	}

	public void delItemTypeById(String id) {
		ItemType it=ith.findById(id);
		it.setState(its.findItemTypeStateByCode(ItemTypeState.DELETE));
		ith.attachDirty(it);
	}

	public ItemType findItemTypeById(String id) {
		return ith.findById(id);
	}

	public void modifyItemType(ItemType it) {
		ith.attachDirty(it);
	}
	
	
}
