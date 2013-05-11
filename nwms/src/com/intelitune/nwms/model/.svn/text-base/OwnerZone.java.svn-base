package com.intelitune.nwms.model;

//import com.intelitune.mcs.model.InttClientDetailWS;
//import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.service.OwnerZoneServiceImp;

/**
 * 
 * @hibernate.joined-subclass
 * table = "intt_ownerZone"
 * @hibernate.joined-subclass-key
 * column = "zoneId"
 * @author chase
 *
 */
public class OwnerZone extends Zone {

	/*
 * (non-javadoc)
 */
public String inttClientDetailWSId;
 
/**
 * Getter of the property <tt>InttClientDetailWSId</tt>
 *@hibernate.property
 * @return Returns the InttClientDetailWSId.
 * 
 */

public String getInttClientDetailWSId()
{
	return inttClientDetailWSId;
}

/**
 * Setter of the property <tt>InttClientDetailWSId</tt>
 *
 * @param InttClientDetailWSId The InttClientDetailWSId to set.
 *
 */
public void setInttClientDetailWSId(String inttClientDetailWSId ){
	this.inttClientDetailWSId = inttClientDetailWSId;
}

/*
 * (non-javadoc)
 */
//public InttClientDetailWS owner;
 
/**
 * Getter of the property <tt>owner</tt>
 * @return Returns the owner.
 * 
 */

public InttClientDetailWS getOwner()
{
	return  OwnerZoneServiceImp.getInstance().findOwnerById(Integer.parseInt(inttClientDetailWSId));
}

/**
 * Setter of the property <tt>owner</tt>
 *
 * @param owner The owner to set.
 *
 */
public void setOwner(InttClientDetailWS owner ){
	this.inttClientDetailWSId=owner.getId()+""; 
}

}
