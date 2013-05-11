package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_warehouseItem"
 * @hibernate.joined-subclass-key
 * column = "itemId"
 * @author chase
 *
 */
public class WarehouseItem extends Item {
	
	public String crn;

	public String jobId;
	
	public String orderId;
	
	
	/*
 * (non-javadoc)
 */
public Bin bin;
 
/**
 * Getter of the property <tt>bin</tt>
 *@hibernate.many-to-one
 * @return Returns the bin.
 * 
 */

public Bin getBin()
{
	return bin;
}

/**
 * Setter of the property <tt>bin</tt>
 *
 * @param bin The bin to set.
 *
 */
public void setBin(Bin bin ){
	this.bin = bin;
}

/*
 * (non-javadoc)
 */
public Warehouse warehouse;
 
/**
 * Getter of the property <tt>warehouse</tt>
 *@hibernate.many-to-one
 * @return Returns the warehouse.
 * 
 */

public Warehouse getWarehouse()
{
	return warehouse;
}

/**
 * Setter of the property <tt>warehouse</tt>
 *
 * @param warehouse The warehouse to set.
 *
 */
public void setWarehouse(Warehouse warehouse ){
	this.warehouse = warehouse;
}
/**
 * @hibernate.property
 * column = "job_id"
 * @return
 */
public String getJobId() {
	return jobId;
}

public void setJobId(String jobId) {
	this.jobId = jobId;
}
/**
 * @hibernate.property
 * column = "order_id"
 * @return
 */
public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}

/**
 * @hibernate.property
 * column = "intt_crn"
 * @return
 */
public String getCrn() {
	return crn;
}

public void setCrn(String crn) {
	this.crn = crn;
}

}
