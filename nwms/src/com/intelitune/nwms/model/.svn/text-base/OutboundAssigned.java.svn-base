package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_outboundAssigned"
 * @author chase
 *
 */
public class OutboundAssigned {
	
	public String id;
	
	public OutboundOrderItem ooi;
	
	public StorageItem si;
	
	public Float assignedQty;
	
	public Integer isDelete;
	
	public String hawb;
	
	public String mawb;

	/**
	 * @hibernate.id
 *   generator-class = "uuid"
 *   column = "intt_id"
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @hibernate.many-to-one
	 * column = "intt_outOrderItemId"
	 * @return
	 */
	public OutboundOrderItem getOoi() {
		return ooi;
	}

	public void setOoi(OutboundOrderItem ooi) {
		this.ooi = ooi;
	}

	/**
	 * @hibernate.many-to-one
	 * column = "intt_storageItemId"
	 * @return
	 */
	public StorageItem getSi() {
		return si;
	}

	public void setSi(StorageItem si) {
		this.si = si;
	}

	/**
	 * @hibernate.property
	 * column = "intt_assignedQty"
	 * @return
	 */
	public Float getAssignedQty() {
		return assignedQty;
	}

	public void setAssignedQty(Float assignedQty) {
		this.assignedQty = assignedQty;
	}

	/**
	 * 0-未删除
	 * 1-删除
	 * @hibernate.property
	 * column = "intt_isDelete"
	 * @return
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

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
