package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_zone"
 * @author chase
 *
 */
public class Zone implements java.io.Serializable{

	public ZoneState state;
	
	/*
 * (non-javadoc)
 */
public String alias;
 /**
  * @hibernate.many-to-one
  * @return
  */
public ZoneState getState() {
	return state;
}

public void setState(ZoneState state) {
	this.state = state;
}

/**
 * Getter of the property <tt>alias</tt>
 *@hibernate.property
 * @return Returns the alias.
 * 
 */

public String getAlias()
{
	return alias;
}

/**
 * Setter of the property <tt>alias</tt>
 *
 * @param alias The alias to set.
 *
 */
public void setAlias(String alias ){
	this.alias = alias;
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
