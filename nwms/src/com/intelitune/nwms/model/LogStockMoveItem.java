package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_logStockMoveItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class LogStockMoveItem extends WarehouseItem{
	
	public StorageItem formStorageItem;

	/**
	 * @hibernate.many-to-one
	 * column = "intt_fromId"
	 * @return
	 */
	public StorageItem getFormStorageItem() {
		return formStorageItem;
	}

	public void setFormStorageItem(StorageItem formStorageItem) {
		this.formStorageItem = formStorageItem;
	}
	
	private String hawb;
	private String mawb;
	public String getHawb() {
		return hawb;
	}
	public void setHawb(String hawb) {
		this.hawb = hawb;
	}
	public String getMawb() {
		return mawb;
	}
	public void setMawb(String mawb) {
		this.mawb = mawb;
	}
	
}
