/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseSupTemplate;

/**
 * <p>考察安排	</p>
 * 
 * @author  qlb
 * @version 1.00 2011-11-30
 */
public class ReviewArrange  extends BaseSupTemplate{
    //序号
	private String serialNumber;
	//单位
	private String unitName;
	/**
	 * 供应商id
	 */

	
	//所属区域
	private String blongArea;

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the blongArea
	 */
	public String getBlongArea() {
		return blongArea;
	}
	/**
	 * @param blongArea the blongArea to set
	 */
	public void setBlongArea(String blongArea) {
		this.blongArea = blongArea;
	}
	
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public void doImport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}
}
