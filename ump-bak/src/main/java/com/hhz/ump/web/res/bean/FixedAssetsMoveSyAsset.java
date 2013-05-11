/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * 办公资产调拨单-商业 子表
 */
public class FixedAssetsMoveSyAsset {
	private String assetCode;// 资产编码
	private String assmAccountId;// 资产编码ID

	private String assetName;// 设备名称

	private String model;// 型号

	private String outDeptName;// 调出部门
	private String outDeptCd;// 调出部门

	private String inDeptName;// 调入部门
	private String inDeptCd;// 调入部门
	private String addWay;// 调入方式

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

	public String getAssmAccountId() {
		return assmAccountId;
	}

	public void setAssmAccountId(String assmAccountId) {
		this.assmAccountId = assmAccountId;
	}

	public String getAddWay() {
		return addWay;
	}

	public void setAddWay(String addWay) {
		this.addWay = addWay;
	}

}
