package com.intelitune.nwms.model;

import java.util.List;
import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_bin"
 * @author chase
 *
 */
public class Bin implements java.io.Serializable{

/*
 * (non-javadoc)
 */
public String remark;
 
/**
 * Getter of the property <tt>remark</tt>
 *@hibernate.property
 * @return Returns the remark.
 * 
 */

public String getRemark()
{
	return remark;
}

/**
 * Setter of the property <tt>remark</tt>
 *
 * @param remark The remark to set.
 *
 */
public void setRemark(String remark ){
	this.remark = remark;
}

/*
 * (non-javadoc)
 */
public Float volume;
 
/**
 * Getter of the property <tt>volume</tt>
 *@hibernate.property
 * @return Returns the volume.
 * 
 */

public Float getVolume()
{
	return volume;
}

/**
 * Setter of the property <tt>volume</tt>
 *
 * @param volume The volume to set.
 *
 */
public void setVolume(Float volume ){
	this.volume = volume;
}

/*
 * (non-javadoc)
 */
public Float height;
 
/**
 * Getter of the property <tt>height</tt>
 *@hibernate.property
 * @return Returns the height.
 * 
 */

public Float getHeight()
{
	return height;
}

/**
 * Setter of the property <tt>height</tt>
 *
 * @param height The height to set.
 *
 */
public void setHeight(Float height ){
	this.height = height;
}

/*
 * (non-javadoc)
 */
public Float breadth;
 
/**
 * Getter of the property <tt>breadth</tt>
 *@hibernate.property
 * @return Returns the breadth.
 * 
 */

public Float getBreadth()
{
	return breadth;
}

/**
 * Setter of the property <tt>breadth</tt>
 *
 * @param breadth The breadth to set.
 *
 */
public void setBreadth(Float breadth ){
	this.breadth = breadth;
}

/*
 * (non-javadoc)
 */
public Float length;
 
/**
 * Getter of the property <tt>length</tt>
 *@hibernate.property
 * @return Returns the length.
 * 
 */

public Float getLength()
{
	return length;
}

/**
 * Setter of the property <tt>length</tt>
 *
 * @param length The length to set.
 *
 */
public void setLength(Float length ){
	this.length = length;
}

/*
 * (non-javadoc)
 */
public Float weight;
 
/**
 * Getter of the property <tt>weight</tt>
 *@hibernate.property
 * @return Returns the weight.
 * 
 */

public Float getWeight()
{
	return weight;
}

/**
 * Setter of the property <tt>weight</tt>
 *
 * @param weight The weight to set.
 *
 */
public void setWeight(Float weight ){
	this.weight = weight;
}

/*
 * (non-javadoc)
 */
public BinState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public BinState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(BinState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public Set<Rate> rates;
 
/**
 * Getter of the property <tt>rate</tt>
 *@hibernate.set lazy = "true"
 *table = "intt_rates"
 *@hibernate.collection-key
 *   column = "binId"
 *   @hibernate.collection-many-to-many
 *   column = "rateId"
 *   class = "com.intelitune.nwms.model.Rate"
 * @return Returns the rate.
 * 
 */

public Set<Rate> getRates()
{
	return rates;
}

/**
 * Setter of the property <tt>rate</tt>
 *
 * @param rate The rate to set.
 *
 */
public void setRates(Set<Rate> rate ){
	this.rates = rate;
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
public Column column;
 
/**
 * Getter of the property <tt>column</tt>
 *@hibernate.many-to-one
 *column = "columnId"
 * @return Returns the column.
 * 
 */

public Column getColumn()
{
	return column;
}

/**
 * Setter of the property <tt>column</tt>
 *
 * @param column The column to set.
 *
 */
public void setColumn(Column column ){
	this.column = column;
}

/*
 * (non-javadoc)
 */
public Tier tier;
 
/**
 * Getter of the property <tt>tier</tt>
 *@hibernate.many-to-one
 *column = "tierId"
 * @return Returns the tier.
 * 
 */

public Tier getTier()
{
	return tier;
}

/**
 * Setter of the property <tt>tier</tt>
 *
 * @param tier The tier to set.
 *
 */
public void setTier(Tier tier ){
	this.tier = tier;
}

/*
 * (non-javadoc)
 */
public Row row;
 
/**
 * Getter of the property <tt>row</tt>
 *@hibernate.many-to-one
 *column = "row_Id"
 * @return Returns the row.
 * 
 */

public Row getRow()
{
	return row;
}

/**
 * Setter of the property <tt>row</tt>
 *
 * @param row The row to set.
 *
 */
public void setRow(Row row ){
	this.row = row;
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
public List<Zone> zones;
 
/**
 * Getter of the property <tt>zones</tt>
 *@hibernate.list lazy = "true"
 *table = "intt_zones_bin"
 *@hibernate.collection-key
 *   column = "binId"
 *   @hibernate.collection-index
 *   column = "indexId"
 *   @hibernate.collection-many-to-many
 *   column = "zoneId"
 *   class = "com.intelitune.nwms.model.Zone"
 *   
 * @return Returns the zones.
 * 
 */

public List<Zone> getZones()
{
	return zones;
}

/**
 * Setter of the property <tt>zones</tt>
 *
 * @param zones The zones to set.
 *
 */
public void setZones(List<Zone> zones ){
	this.zones = zones;
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

