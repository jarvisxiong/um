package com.intelitune.nwms.model;

/**
 * @hibernate.class
 * table = "intt_itemTypeState"
 * @author chase
 *
 */
public class ItemTypeState implements java.io.Serializable{

	public final static Integer NORMAL = 0;
	public final static Integer DELETE = 1;
	public final static Integer LOCKED = 2;
	
	public String description;
	 
	/**
	 *@hibernate.property
	 *column = "intt_description"
	 * @return Returns the description.
	 * 
	 */
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description ){
		this.description = description;
	}
	
	public String code;
	 
	/**
	 *@hibernate.property
	 *column = "intt_code"
	 * @return Returns the code.
	 * 
	 */
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code ){
		this.code = code;
	}
	
	public String id;
	 
	/**
	 *@hibernate.id
	 *   generator-class = "uuid"
	 *   column = "intt_id"
	 * @return Returns the id.
	 * 
	 */
	
	public String getId()
	{
		return id;
	}
	
	
	public void setId(String id ){
		this.id = id;
	}

}
