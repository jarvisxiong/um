package com.intelitune.nwms.model;
/**
 * @hibernate.class
 * table = "intt_rate"
 * @author chase
 *
 */
public class Rate implements java.io.Serializable{

	public RateState state;
	
	/*
 * (non-javadoc)
 */
public Integer priority;
 
/**
 * Getter of the property <tt>attribute</tt>
 *@hibernate.property
 * @return Returns the attribute.
 * 
 */

public Integer getPriority()
{
	return priority;
}

/**
 * Setter of the property <tt>attribute</tt>
 *
 * @param attribute The attribute to set.
 *
 */
public void setPriority(Integer attribute ){
	this.priority = attribute;
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

/**
 * @hibernate.many-to-one
 * @return
 */
public RateState getState() {
	return state;
}

public void setState(RateState state) {
	this.state = state;
}

}
