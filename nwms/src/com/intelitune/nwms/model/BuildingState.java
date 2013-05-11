package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_buildingState"
 * @author chase
 *
 */
public class BuildingState implements java.io.Serializable{

	/*
 * (non-javadoc)
 */
public final static Integer NORMAL = 0;public final static Integer DELETE = 1;public final static Integer LOCKED = 2;public String description;
 
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
