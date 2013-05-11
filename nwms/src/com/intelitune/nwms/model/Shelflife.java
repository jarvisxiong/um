package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_shelflife"
 * @author chase
 *
 */
public class Shelflife implements java.io.Serializable{

	public ShelflifeState state;
	
	
	/*
 * (non-javadoc)
 */
public Integer denomination;
 
/**
 * Getter of the property <tt>denomination</tt>
 *@hibernate.property
 * @return Returns the denomination.
 * 
 */

public Integer getDenomination()
{
	return denomination;
}

/**
 * Setter of the property <tt>denomination</tt>
 *
 * @param denomination The denomination to set.
 *
 */
public void setDenomination(Integer denomination ){
	this.denomination = denomination;
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

/**
 * @hibernate.many-to-one
 * @return
 */
public ShelflifeState getState() {
	return state;
}

public void setState(ShelflifeState state) {
	this.state = state;
}

}
