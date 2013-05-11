package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_orderItem"
 * @hibernate.joined-subclass-key
 * column = "itemId"
 * @author chase
 *
 */
public class OrderItem extends Item {

	/*
 * (non-javadoc)
 */
public Order order;

public String orderId;

public Warehouse warehouse;

public String jobId;

public Float arrivedQty;

public Float unArrivedQty;

public Float destoryedQty;

public Float unit_price;
public Float unit_weight;
public Float gross_price;
public Float gross_weight;
public String currency;

/**
 * @hibernate.property
 * column = "intt_ArrivedQty"
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
 * column = "intt_UnArrivedQty"
 * @return
 */
public Float getUnArrivedQty() {
	return unArrivedQty;
}

public void setUnArrivedQty(Float unArrivedQty) {
	this.unArrivedQty = unArrivedQty;
}

/**
 * @hibernate.property
 * column = "intt_destoryedQty"
 * @return
 */
public Float getDestoryedQty() {
	return destoryedQty;
}

public void setDestoryedQty(Float destoryedQty) {
	this.destoryedQty = destoryedQty;
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
 * @hibernate.many-to-one
 * column = "warehouse_id"
 * @return
 */
public Warehouse getWarehouse() {
	return warehouse;
}

public void setWarehouse(Warehouse warehouse) {
	this.warehouse = warehouse;
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
 * Getter of the property <tt>order</tt>
 * @return Returns the order.
 * 
 */

public Order getOrder()
{
	return order;
}

/**
 * Setter of the property <tt>order</tt>
 *
 * @param order The order to set.
 *
 */
public void setOrder(Order order ){
	this.order = order;
}

public Float getUnit_price() {
	return unit_price;
}

public void setUnit_price(Float unit_price) {
	this.unit_price = unit_price;
}

public Float getUnit_weight() {
	return unit_weight;
}

public void setUnit_weight(Float unit_weight) {
	this.unit_weight = unit_weight;
}

public Float getGross_price() {
	return gross_price;
}

public void setGross_price(Float gross_price) {
	this.gross_price = gross_price;
}

public Float getGross_weight() {
	return gross_weight;
}

public void setGross_weight(Float gross_weight) {
	this.gross_weight = gross_weight;
}

public String getCurrency() {
	return currency;
}

public void setCurrency(String currency) {
	this.currency = currency;
}

}
