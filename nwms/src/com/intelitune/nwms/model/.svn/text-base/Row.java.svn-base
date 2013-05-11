package com.intelitune.nwms.model;

import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_row"
 * @author chase
 *
 */
public class Row implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public Set<Bin> bins;
 
/**
 * Getter of the property <tt>bins</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "row_id"
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
public Set<Column> columns;
 
/**
 * Getter of the property <tt>columns</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "row_id"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Column"
 * @return Returns the columns.
 * 
 */

public Set<Column> getColumns()
{
	return columns;
}

/**
 * Setter of the property <tt>columns</tt>
 *
 * @param columns The columns to set.
 *
 */
public void setColumns(Set<Column> columns ){
	this.columns = columns;
}

/*
 * (non-javadoc)
 */
public Set<Tier> tiers;
 
/**
 * Getter of the property <tt>tier</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "row_id"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.Tier"
 * @return Returns the tier.
 * 
 */

public Set<Tier> getTiers()
{
	return tiers;
}

/**
 * Setter of the property <tt>tier</tt>
 *
 * @param tier The tier to set.
 *
 */
public void setTiers(Set<Tier> tier ){
	this.tiers = tier;
}

/*
 * (non-javadoc)
 */
public RowState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public RowState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(RowState state ){
	this.state = state;
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
public Floor floor;
 
/**
 * Getter of the property <tt>floor</tt>
 *@hibernate.many-to-one
 *column = "floorId"
 * @return Returns the floor.
 * 
 */

public Floor getFloor()
{
	return floor;
}

/**
 * Setter of the property <tt>floor</tt>
 *
 * @param floor The floor to set.
 *
 */
public void setFloor(Floor floor ){
	this.floor = floor;
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
