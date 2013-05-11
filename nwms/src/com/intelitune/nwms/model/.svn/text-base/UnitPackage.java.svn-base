package com.intelitune.nwms.model;

import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_unitPackage"
 * @author chase
 *
 */
public class UnitPackage implements java.io.Serializable{

	public UnitPackageState state;
	
	/*
 * (non-javadoc)
 */
public Float weight;
 /**
  * @hibernate.many-to-one
  * @return
  */
public UnitPackageState getState() {
	return state;
}

public void setState(UnitPackageState state) {
	this.state = state;
}

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
public Set<UnitPackagePair> unitPackagePairs;
 
/**
 * Getter of the property <tt>unitPackagePairs</tt>
 *@hibernate.set lazy = "true"
 *@hibernate.collection-key
 *   column = "unitPackageId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.UnitPackagePair"
 * @return Returns the unitPackagePairs.
 * 
 */

public Set<UnitPackagePair> getUnitPackagePairs()
{
	return unitPackagePairs;
}

/*
 * (non-javadoc)
 */
public Unit unit;
 
/**
 * Getter of the property <tt>unit</tt>
 *@hibernate.many-to-one
 * @return Returns the unit.
 * 
 */

public Unit getUnit()
{
	return unit;
}

/**
 * Setter of the property <tt>unit</tt>
 *
 * @param unit The unit to set.
 *
 */
public void setUnit(Unit unit ){
	this.unit = unit;
}

/*
 * (non-javadoc)
 */
public Material material;
 
/**
 * Getter of the property <tt>material</tt>
 *@hibernate.many-to-one
 *column = "materialId"
 * @return Returns the material.
 * 
 */

public Material getMaterial()
{
	return material;
}

/**
 * Setter of the property <tt>material</tt>
 *
 * @param material The material to set.
 *
 */
public void setMaterial(Material material ){
	this.material = material;
}

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
public String description;
 
/**
 * Getter of the property <tt>description</tt>
 *@hibernate.property
 * @return Returns the description.
 * 
 */

public String getDescription()
{
	return description;
}

/**
 * Setter of the property <tt>description</tt>
 *
 * @param description The description to set.
 *
 */
public void setDescription(String description ){
	this.description = description;
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

public void setUnitPackagePairs(Set<UnitPackagePair> unitPackagePairs) {
	this.unitPackagePairs = unitPackagePairs;
}


private Integer indexId;

/**
 * @hibernate.property
 * column = "indexId"
 */
public Integer getIndexId() {
	return indexId;
}

public void setIndexId(Integer indexId) {
	this.indexId = indexId;
}



}
