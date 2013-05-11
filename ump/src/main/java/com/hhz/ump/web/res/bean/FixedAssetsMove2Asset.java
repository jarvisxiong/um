/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * 固定资产调拨单（2）字表
 * @author lujunyun 2010-12-21
 */
public class FixedAssetsMove2Asset {
	private String assetName;// 设备名称

	private String model;// 型号
	
	private String assetCode;// 资产编码

	private String assetValue;// 资产现值

	private String moveDate;// 调拨日期

	private String remark;// 注

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(String assetValue) {
		this.assetValue = assetValue;
	}

	public String getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
