package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_InboundRePick"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class InboundRePickItem extends WarehouseItem{
	
	public String tempJobId;

	/**
	 * @hibernate.property
	 * @return
	 */
	public String getTempJobId() {
		return tempJobId;
	}

	public void setTempJobId(String tempJobId) {
		this.tempJobId = tempJobId;
	}
	
	
}
