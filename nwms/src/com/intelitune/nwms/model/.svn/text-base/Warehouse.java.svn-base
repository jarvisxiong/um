package com.intelitune.nwms.model;

import java.util.Set;

/**
 * @hibernate.class
 * table = "intt_warehouse"
 * @author chase
 *
 */
public class Warehouse implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public Set<Bin> bins;
 
/**
 * Getter of the property <tt>Bins</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "warehouseId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Bin"
 * @return Returns the Bins.
 * 
 */

public Set<Bin> getBins()
{
	return bins;
}

/**
 * Setter of the property <tt>Bins</tt>
 *
 * @param Bins The Bins to set.
 *
 */
public void setBins(Set<Bin> Bins ){
	this.bins = Bins;
}

/*
 * (non-javadoc)
 */
public Set<Building> buildings;
 
/**
 * Getter of the property <tt>building</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "warehouseId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Building"
 * @return Returns the building.
 * 
 */

public Set<Building> getBuildings()
{
	return buildings;
}

/**
 * Setter of the property <tt>building</tt>
 *
 * @param building The building to set.
 *
 */
public void setBuildings(Set<Building> building ){
	this.buildings = building;
}

/*
 * (non-javadoc)
 */
public WarehouseState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public WarehouseState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(WarehouseState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public String city;
 
/**
 * 
 * Getter of the property <tt>city</tt>
 *@hibernate.property
 * @return Returns the city.
 * 
 */

public String getCity()
{
	return city;
}

/**
 * Setter of the property <tt>city</tt>
 *
 * @param city The city to set.
 *
 */
public void setCity(String city ){
	this.city = city;
}

/*
 * (non-javadoc)
 */
public String area;
 
/**
 * Getter of the property <tt>area</tt>
 *@hibernate.property
 * @return Returns the area.
 * 
 */

public String getArea()
{
	return area;
}

/**
 * Setter of the property <tt>area</tt>
 *
 * @param area The area to set.
 *
 */
public void setArea(String area ){
	this.area = area;
}

/*
 * (non-javadoc)
 */
public String zip;
 
/**
 * Getter of the property <tt>zip</tt>
 *@hibernate.property
 * @return Returns the zip.
 * 
 */

public String getZip()
{
	return zip;
}

/**
 * Setter of the property <tt>zip</tt>
 *
 * @param zip The zip to set.
 *
 */
public void setZip(String zip ){
	this.zip = zip;
}

/*
 * (non-javadoc)
 */
public String phone;
 
/**
 * Getter of the property <tt>phone</tt>
 *@hibernate.property
 * @return Returns the phone.
 * 
 */

public String getPhone()
{
	return phone;
}

/**
 * Setter of the property <tt>phone</tt>
 *
 * @param phone The phone to set.
 *
 */
public void setPhone(String phone ){
	this.phone = phone;
}

/*
 * (non-javadoc)
 */
public String address;
 
/**
 * Getter of the property <tt>address</tt>
 *@hibernate.property
 * @return Returns the address.
 * 
 */

public String getAddress()
{
	return address;
}

/**
 * Setter of the property <tt>address</tt>
 *
 * @param address The address to set.
 *
 */
public void setAddress(String address ){
	this.address = address;
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
public String alias;
 
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
