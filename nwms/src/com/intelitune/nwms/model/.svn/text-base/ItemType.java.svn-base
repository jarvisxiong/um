package com.intelitune.nwms.model;

/**
 * @hibernate.class
 * table = "intt_itemType"
 * @author chase
 *
 */
public class ItemType implements java.io.Serializable{
	
	public String id;
	
	public String code;
	
	public String alias;
	
	public ItemTypeState state;
	
	public String remark;

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
	 * column = "intt_alias"
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @hibernate.many-to-one
	 * column = "intt_itemTypeState"
	 * @return
	 */
	public ItemTypeState getState() {
		return state;
	}

	public void setState(ItemTypeState state) {
		this.state = state;
	}

	/**
	 * @hibernate.property
	 * column = "intt_remark"
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
