package com.intelitune.nwms.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


/**
 * 
 * @hibernate.class
 * table = "intt_invoice"
 * @author chase
 *
 */
public class Invoice implements Serializable {
	
	public String id;
	
	public String code;
	
	public Integer orderId;
	
	public Set<Item> items;
	
	public Timestamp createTime;
	
	public Timestamp lastTime;
	
	//michael_wang 2009 1030
	public String so;
	public String po;
	
	/**
	 * @hibernate.property
	 * @return
	 */
public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
/**
 * @hibernate.property
 * @return
 */
	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

/**
 * @hibernate.id
 * column = "intt_id"
 *   generator-class = "uuid"
 * @return
 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
/**
 * @hibernate.property
 * column = "intt_code"
 * @return
 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
/**
 * @hibernate.property
 * @return
 */
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 *@hibernate.set lazy = "true"
	 *@hibernate.collection-key
	 *   column = "invoice_id"
	 *   @hibernate.collection-one-to-many
	 *   class = "com.intelitune.nwms.model.Item"
	 * 
	 */
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public InvoiceState invoiveState;
	/**
	 * @hibernate.many-to-one
	 * 
	 * @return
	 */
	public InvoiceState getInvoiveState() {
		return invoiveState;
	}

	public void setInvoiveState(InvoiceState invoiveState) {
		this.invoiveState = invoiveState;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}
	
	
	

}
