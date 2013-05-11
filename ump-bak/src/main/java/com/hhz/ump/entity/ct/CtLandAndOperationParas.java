/**
 * 
 */
package com.hhz.ump.entity.ct;

/**
 *生成目标成本的参数
 * @author qlb 12/12/2011
 *
 */
public class CtLandAndOperationParas {
private String ctLandId;
private String ctLedgerId;
private String ctOperationId;
private String landName;
private String operName;
private String targetAmt;
private String ctItemId;


private String itemName;
private String parentId;

/**
 * @return the landName
 */
public String getLandName() {
	return landName;
}

/**
 * @param landName the landName to set
 */
public void setLandName(String LANDNAME) {
	this.landName = LANDNAME;
}

/**
 * @return the operName
 */
public String getOperName() {
	return operName;
}

/**
 * @param operName the operName to set
 */
public void setOperName(String operName) {
	this.operName = operName;
}

/**
 * @return the ctLandId
 */
public String getCtLandId() {
	return ctLandId;
}

/**
 * @param ctLandId the ctLandId to set
 */
public void setCtLandId(String ctLandId) {
	this.ctLandId = ctLandId;
}
/**
 * @return the ctLedgerId
 */
public String getCtLedgerId() {
	return ctLedgerId;
}
/**
 * @param ctLedgerId the ctLedgerId to set
 */
public void setCtLedgerId(String ctLedgerId) {
	this.ctLedgerId = ctLedgerId;
}
/**
 * @return the ctOperationId
 */
public String getCtOperationId() {
	return ctOperationId;
}
/**
 * @param ctOperationId the ctOperationId to set
 */
public void setCtOperationId(String ctOperationId) {
	this.ctOperationId = ctOperationId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getTargetAmt() {
	return targetAmt;
}


public void setTargetAmt(String targetAmt) {
	this.targetAmt = targetAmt;
}

public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}
public String getCtItemId() {
	return ctItemId;
}

public void setCtItemId(String ctItemId) {
	this.ctItemId = ctItemId;
}

}
