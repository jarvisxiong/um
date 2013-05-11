/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * 固定资产调拨单（1）字表
 * @author lujunyun 2010-12-21
 */
public class FixedAssetsMove1Asset {
	private String assetCode;// 资产编码

	private String assetName;// 设备名称

	private String model;// 型号

	private String outDeptName;// 调出部门
	private String outDeptCd;// 调出部门

	private String inDeptName;// 调入部门
	private String inDeptCd;// 调入部门

	private String moveDate;// 调拨日期
	private String unitPrice;// 单价

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

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

	public String getOutDeptName() {
		return outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptCd() {
		return outDeptCd;
	}

	public void setOutDeptCd(String outDeptCd) {
		this.outDeptCd = outDeptCd;
	}

	public String getInDeptCd() {
		return inDeptCd;
	}

	public void setInDeptCd(String inDeptCd) {
		this.inDeptCd = inDeptCd;
	}

	public String getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

}
