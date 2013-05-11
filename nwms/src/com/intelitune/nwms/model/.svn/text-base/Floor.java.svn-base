package com.intelitune.nwms.model;

import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_floor"
 * @author chase
 *
 */
public class Floor implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public Set<Bin> bins;
 
/**
 * Getter of the property <tt>bins</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "floorId"
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
public Set<Row> rows;
 
/**
 * Getter of the property <tt>rows</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "floorId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Row"
 * @return Returns the rows.
 * 
 */

public Set<Row> getRows()
{
	return rows;
}

/**
 * Setter of the property <tt>rows</tt>
 *
 * @param rows The rows to set.
 *
 */
public void setRows(Set<Row> rows ){
	this.rows = rows;
}

/*
 * (non-javadoc)
 */
public FloorState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public FloorState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(FloorState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public Building building;
 
/**
 * Getter of the property <tt>building</tt>
 *@hibernate.many-to-one
 *column = "buildingId"
 * @return Returns the building.
 * 
 */

public Building getBuilding()
{
	return building;
}

/**
 * Setter of the property <tt>building</tt>
 *
 * @param building The building to set.
 *
 */
public void setBuilding(Building building ){
	this.building = building;
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
