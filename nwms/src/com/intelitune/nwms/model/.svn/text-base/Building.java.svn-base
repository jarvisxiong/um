package com.intelitune.nwms.model;

import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_building"
 * @author chase
 *
 */
public class Building implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public Set<Bin> bins;
 
/**
 * Getter of the property <tt>bins</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "buildingId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Bin"
 * @return Returns the bins.
 * 
 */

public Set<Bin> getBins()
{
	return bins;
}

/**
 * Setter of the property <tt>bins</tt>
 *
 * @param bins The bins to set.
 *
 */
public void setBins(Set<Bin> bins ){
	this.bins = bins;
}

/*
 * (non-javadoc)
 */
public BuildingState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public BuildingState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(BuildingState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public Set<Floor> floors;
 
/**
 * Getter of the property <tt>floors</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "buildingId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Floor"
 * @return Returns the floors.
 * 
 */

public Set<Floor> getFloors()
{
	return floors;
}

/**
 * Setter of the property <tt>floors</tt>
 *
 * @param floors The floors to set.
 *
 */
public void setFloors(Set<Floor> floors ){
	this.floors = floors;
}

/*
 * (non-javadoc)
 */
public Warehouse warehouse;
 
/**
 * Getter of the property <tt>warehouse</tt>
 *@hibernate.many-to-one
 *column = "warehouseId"
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
