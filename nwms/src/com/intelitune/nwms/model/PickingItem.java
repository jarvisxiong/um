package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_pickingItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class PickingItem extends WarehouseItem {
	private int isCorrect;

	public int getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
	private String hawb;
	private String mawb;

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}
	
	
}
