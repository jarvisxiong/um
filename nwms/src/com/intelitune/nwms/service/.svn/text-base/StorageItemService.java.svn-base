package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.model.StorageItem;
/**
 * 
 * @author Louis
 *
 */
public interface StorageItemService {
	/**
	 * @param WmsOrder wo
	 * @return List<StorageItem>
	 * 通过WmsOrder 来寻找StorageItem
	 */
	public List<StorageItem> findStorageItemByWmsOrder(WmsOrder wo,String productcode);
	
	public List<StorageItem> findStorageItemByWmsOrderAndInvoice(WmsOrder wo,String productcode,String invoicecode);
	/**
	 * @param String id
	 * 通过Id将StorageItem置为删除状态
	 */
	public void delStorageItemById(String id);
	/**
	 * @param StorageItem si
	 * 增加StorageItem
	 */
	public void addStorageItem(StorageItem si);
	/**
	 * @param String id
	 * @return StorageItem
	 * 通过Id寻找StorageItem
	 */
	public StorageItem findStorageItemById(String id);
	/**
	 * @param StorageItem si
	 * 更新StorageItem
	 */
	public void modifyStorageItem(StorageItem si);
	/**
	 * @param String hql
	 * @return List<StorageItem>
	 * 通过HQL寻找StorageItem
	 */
	public List<StorageItem> findByHql(String hql);
	
	public List<StorageItem> findNormalByJobId(String jobId);
	
	
	public List<StorageItem> findStorageItemByBincodeAndProductCode(String bincode,String productcode,String sn);
	
	
	public List<StorageItem> findStorageItemByProductCodeAndClient_id(String hql);
	
	public List<StorageItem> findStorageItemByWarehouseAndClient(String owner_id,String warehouse_id);
	
	public List<StorageItem> findStorageItembyWarehouseAndProductAndClient(String warehouse_id,String productcode,String owner_id);
}
