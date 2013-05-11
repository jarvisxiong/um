package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_unstorageItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class UnstorageItem extends WarehouseItem {
	
	public Float wantQty;
	
	public Float unwantedQty;
	
	/**
	 * @hibernate.property
	 * @return
	 */
	public Float getUnwantedQty() {
		return unwantedQty;
	}

	public void setUnwantedQty(Float unwantedQty) {
		this.unwantedQty = unwantedQty;
	}

	/**
	 * @hibernate.property
	 * @return
	 */
	public Float getWantQty() {
		return wantQty;
	}

	public void setWantQty(Float wantQty) {
		this.wantQty = wantQty;
	}

}
