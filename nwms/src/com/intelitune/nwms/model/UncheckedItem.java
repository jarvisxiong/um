package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_uncheckedItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class UncheckedItem extends WarehouseItem {
	
	public Float arrivedQty;
	
	public Float destroyedQty;
	
	public Float putawayQty;
	
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

	public void setWantQty(Float deficiencyQty) {
		this.wantQty = deficiencyQty;
	}

	/**
	 * @hibernate.property
	 * @return
	 */
	public Float getArrivedQty() {
		return arrivedQty;
	}

	public void setArrivedQty(Float arrivedQty) {
		this.arrivedQty = arrivedQty;
	}

	/**
	 * @hibernate.property
	 * @return
	 */
	public Float getDestroyedQty() {
		return destroyedQty;
	}

	public void setDestroyedQty(Float destroyedQty) {
		this.destroyedQty = destroyedQty;
	}

	/**
	 * @hibernate.property
	 * @return
	 */
	public Float getPutawayQty() {
		return putawayQty;
	}

	public void setPutawayQty(Float putawayQty) {
		this.putawayQty = putawayQty;
	}
	
	

}
