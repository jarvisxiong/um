package com.intelitune.nwms.model;


//import com.intelitune.mcs.model.InttClientDetailWS;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @hibernate.class
 * table = "intt_material"
 * @author chase
 *
 */
public class Material implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public String inttClientDetailWSId;
 
/**
 * Getter of the property <tt>inttClientDetailWSId</tt>
 *@hibernate.property
 * @return Returns the inttClientDetailWSId.
 * 
 */

public String getInttClientDetailWSId()
{
	return inttClientDetailWSId;
}

/**
 * Setter of the property <tt>inttClientDetailWSId</tt>
 *
 * @param inttClientDetailWSId The inttClientDetailWSId to set.
 *
 */
public void setInttClientDetailWSId(String inttClientDetailWSId ){
	this.inttClientDetailWSId = inttClientDetailWSId;
}

/*
 * (non-javadoc)
 */
public Float value;
 
/**
 * Getter of the property <tt>value</tt>
 *@hibernate.property
 * @return Returns the value.
 * 
 */

public Float getValue()
{
	return value;
}

/**
 * Setter of the property <tt>value</tt>
 *
 * @param value The value to set.
 *
 */
public void setValue(Float value ){
	this.value = value;
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
 *   column = "materialId"
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
public MaterialState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public MaterialState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(MaterialState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public Timestamp LastModifiedTime;
 
/**
 * Getter of the property <tt>LastModifiedTime</tt>
 *@hibernate.property
 * @return Returns the LastModifiedTime.
 * 
 */

public Timestamp getLastModifiedTime()
{
	return LastModifiedTime;
}

/**
 * Setter of the property <tt>LastModifiedTime</tt>
 *
 * @param LastModifiedTime The LastModifiedTime to set.
 *
 */
public void setLastModifiedTime(Timestamp LastModifiedTime ){
	this.LastModifiedTime = LastModifiedTime;
}

/*
 * (non-javadoc)
 */
public Timestamp creationTime;
 
/**
 * Getter of the property <tt>creationTime</tt>
 *@hibernate.property
 * @return Returns the creationTime.
 * 
 */

public Timestamp getCreationTime()
{
	return creationTime;
}

/**
 * Setter of the property <tt>creationTime</tt>
 *
 * @param creationTime The creationTime to set.
 *
 */
public void setCreationTime(Timestamp creationTime ){
	this.creationTime = creationTime;
}

/*
 * (non-javadoc)
 */
public List<Zone> zones;
 
/**
 * Getter of the property <tt>zones</tt>
 *@hibernate.list lazy = "true"
 *table = "intt_zones_material"
 *@hibernate.collection-key
 *   column = "materialId"
 *   @hibernate.collection-index
 *   column = "indexId"
 *   @hibernate.collection-many-to-many
 *   column = "zoneId"
 *   class = "com.intelitune.nwms.model.Zone"
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
public Shelflife shelflife;
 
/**
 * Getter of the property <tt>shelfLife</tt>
 *@hibernate.many-to-one
 * @return Returns the shelfLife.
 * 
 */

public Shelflife getShelflife()
{
	return shelflife;
}

/**
 * Setter of the property <tt>shelfLife</tt>
 *
 * @param shelfLife The shelfLife to set.
 *
 */
public void setShelflife(Shelflife shelfLife ){
	this.shelflife = shelfLife;
}

/*
 * (non-javadoc)
 */
//public InttClientDetailWS owner;
 
/**
 * Getter of the property <tt>owner</tt>
 *
 * @return Returns the owner.
 * 
 */

//public InttClientDetailWS getOwner()
//{
//	return owner;
//}

/**
 * Setter of the property <tt>owner</tt>
 *
 * @param owner The owner to set.
 *
 */
//public void setOwner(InttClientDetailWS owner ){
//	this.owner = owner;
//}

/*
 * (non-javadoc)
 */
public List<UnitPackage> unitPackages;
 
/**
 * Getter of the property <tt>materialUnitPackges</tt>
 *@hibernate.list lazy = "true"
 *inverse="true"
 *@hibernate.collection-key
 *   column = "materialId"
 *   not-null="true"
 *   @hibernate.collection-index
 *   column = "indexId"
 *   @hibernate.collection-one-to-many
 *   class = "com.intelitune.nwms.model.UnitPackage"
 * @return Returns the materialUnitPackges.
 * 
 */

public List<UnitPackage> getUnitPackages()
{
	return unitPackages;
}

/**
 * Setter of the property <tt>materialUnitPackges</tt>
 *
 * @param materialUnitPackges The materialUnitPackges to set.
 *
 */
public void setUnitPackages(List<UnitPackage> unitPackges ){
	this.unitPackages = unitPackges;
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
 * Getter of the property <tt>Alias</tt>
 *@hibernate.property
 * @return Returns the Alias.
 * 
 */

public String getAlias()
{
	return alias;
}

/**
 * Setter of the property <tt>Alias</tt>
 *
 * @param Alias The Alias to set.
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
