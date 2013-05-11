package com.intelitune.nwms.model;

import java.sql.Timestamp;
/**
 * @hibernate.class
 * table = "intt_order"
 * @author chase
 *
 */
public class Order implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public Timestamp lastModifiedTime;
 
/**
 * Getter of the property <tt>LastModifiedTime</tt>
 *@hibernate.property
 * @return Returns the LastModifiedTime.
 * 
 */

public Timestamp getLastModifiedTime()
{
	return lastModifiedTime;
}

/**
 * Setter of the property <tt>LastModifiedTime</tt>
 *
 * @param LastModifiedTime The LastModifiedTime to set.
 *
 */
public void setLastModifiedTime(Timestamp LastModifiedTime ){
	this.lastModifiedTime = LastModifiedTime;
}

/*
 * (non-javadoc)
 */
public Timestamp createTime;
 
/**
 * Getter of the property <tt>createTime</tt>
 *@hibernate.property
 * @return Returns the createTime.
 * 
 */

public Timestamp getCreateTime()
{
	return createTime;
}

/**
 * Setter of the property <tt>createTime</tt>
 *
 * @param createTime The createTime to set.
 *
 */
public void setCreateTime(Timestamp createTime ){
	this.createTime = createTime;
}

/*
 * (non-javadoc)
 */
public String code;
 
/**
 * Getter of the property <tt>code</tt>
 *@hibernate.property
 * @return Returns the code.
 * 
 */

public String getCode()
{
	return code;
}

/**
 * Setter of the property <tt>code</tt>
 *
 * @param code The code to set.
 *
 */
public void setCode(String code ){
	this.code = code;
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

/*
 * (non-javadoc)
 */
public Owner owner;
 
/**
 * Getter of the property <tt>Owner</tt>
 *@hibernate.many-to-one
 * @return Returns the Owner.
 * 
 */

public Owner getOwner()
{
	return owner;
}

/**
 * Setter of the property <tt>Owner</tt>
 *
 * @param Owner The Owner to set.
 *
 */
public void setOwner(Owner Owner ){
	this.owner = Owner;
}

/*
 * (non-javadoc)
 */
public String id;
 
/**
 * Getter of the property <tt>id</tt>
 *@hibernate.id
 *   generator-class = "uuid"
 * @return Returns the id.
 * 
 */

public String getId()
{
	return id;
}

/**
 * Setter of the property <tt>id</tt>
 *
 * @param id The id to set.
 *
 */
public void setId(String id ){
	this.id = id;
}

}
