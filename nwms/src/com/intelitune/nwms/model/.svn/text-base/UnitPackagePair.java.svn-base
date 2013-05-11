package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_unitPackagePair"
 * @author chase
 *
 */
public class UnitPackagePair implements java.io.Serializable{

	public UnitPackagePairState state;
	
	
	/*
 * (non-javadoc)
 */
public Float denomination;
 /**
  * @hibernate.many-to-one
  * @return
  */
public UnitPackagePairState getState() {
	return state;
}

public void setState(UnitPackagePairState state) {
	this.state = state;
}

/**
 * Getter of the property <tt>denomination</tt>
 *@hibernate.property
 * @return Returns the denomination.
 * 
 */

public Float getDenomination()
{
	return denomination;
}

/**
 * Setter of the property <tt>denomination</tt>
 *
 * @param denomination The denomination to set.
 *
 */
public void setDenomination(Float denomination ){
	this.denomination = denomination;
}

/*
 * (non-javadoc)
 */
public UnitPackage otherUnitPackage;
 
/**
 * Getter of the property <tt>otherUnitPackge</tt>
 *@hibernate.many-to-one
 * @return Returns the otherUnitPackge.
 * 
 */

public UnitPackage getOtherUnitPackage()
{
	return otherUnitPackage;
}

/**
 * Setter of the property <tt>otherUnitPackge</tt>
 *
 * @param otherUnitPackge The otherUnitPackge to set.
 *
 */
public void setOtherUnitPackage(UnitPackage otherUnitPackge ){
	this.otherUnitPackage = otherUnitPackge;
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

public UnitPackage unitPackage;

/**
 * @hibernate.many-to-one
 * column = "unitPackageId"
 * @return
 */
public UnitPackage getUnitPackage() {
	return unitPackage;
}

public void setUnitPackage(UnitPackage unitPackage) {
	this.unitPackage = unitPackage;
}

public String bkt_state;
public String getBkt_state() {
	return bkt_state;
}

public void setBkt_state(String bkt_state) {
	this.bkt_state = bkt_state;
}

}
