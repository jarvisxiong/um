package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_checkingItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class CheckingItem extends WarehouseItem {
	
	public Float arrivedQty;
	
	public Float destroyedQty;
	
	public Float putawayQty;
	
	public Float errorBinQty;
	
	/**
	 * @hibernate.property
	 * column = "intt_errorBinQty"
	 * @return
	 */
	public Float getErrorBinQty() {
		return errorBinQty;
	}

	public void setErrorBinQty(Float errorBinQty) {
		this.errorBinQty = errorBinQty;
	}

	/**
	 * @hibernate.property
	 * column = "intt_putawayQty"
	 * @return
	 */
	public Float getPutawayQty() {
		return putawayQty;
	}

	public void setPutawayQty(Float putawayQty) {
		this.putawayQty = putawayQty;
	}

	/**
	 * @hibernate.property
	 * column = "arrived_qty"
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
	 * column = "destroyed_qty"
	 * @return
	 */
	public Float getDestroyedQty() {
		return destroyedQty;
	}

	public void setDestroyedQty(Float destroyedQty) {
		this.destroyedQty = destroyedQty;
	}
	
	

}
