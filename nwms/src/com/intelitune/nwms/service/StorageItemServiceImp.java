package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.dao.StorageItemHome;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.StorageItem;

public class StorageItemServiceImp implements StorageItemService {
	
	private final static StorageItemServiceImp instance =new StorageItemServiceImp();
	
	private StorageItemServiceImp(){
		
	}
	
	public static final StorageItemServiceImp getInstance() {
		return instance;
	}
	public StorageItemHome sih=StorageItemHome.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	public List<StorageItem> findStorageItemByWmsOrder(WmsOrder wo,String productcode) {
		return sih.findStorageItemByWmsOrder(wo,productcode);
	}
	
	public void delStorageItemById(String id) {
		StorageItem si=sih.findById(id);
		si.setState(iss.findItemStateByCode(ItemState.DELETE));
		sih.attachDirty(si);
	}

	public void addStorageItem(StorageItem si) {
		sih.persist(si);
	}

	public StorageItem findStorageItemById(String id) {
		return sih.findById(id);
	}

	public void modifyStorageItem(StorageItem si) {
		sih.attachDirty(si);
	}

	public List<StorageItem> findByHql(String hql) {
		return sih.findByHql(hql);
	}
	
	public List<StorageItem> findNormalByJobId(String jobId){
		String hql = " from StorageItem i where i.jobId='" + jobId + "' and i.state.code='" + ItemState.NORMAL +"'";
		return sih.findByHql(hql);
	}

	public List<StorageItem> findStorageItemByBincodeAndProductCode(String bincode,
			String productcode,String sn) {
		return sih.findStorageItemByBincodeAndProductCode(bincode, productcode, sn);
		
	}

	public List<StorageItem> findStorageItemByProductCodeAndClient_id(
			String hql) {
		return sih.findStorageItemByProductCodeAndClient_id(hql);
	}
	
	
	public List<StorageItem> findStorageItemByWarehouseAndClient(String owner_id,String warehouse_id){
		return sih.findStorageItemByWarehouseAndClient(owner_id, warehouse_id);
	}

	public List<StorageItem> findStorageItemByWmsOrderAndInvoice(WmsOrder wo,
			String productcode, String invoicecode) {
		return sih.findStorageItemByWmsOrderAndInvoice(wo, productcode, invoicecode);
	}

	public List<StorageItem> findStorageItembyWarehouseAndProductAndClient(
			String warehouse_id, String productcode, String owner_id) {
		return sih.findStorageItembyWarehouseAndProductAndClient(warehouse_id, productcode, owner_id);
	}

}
