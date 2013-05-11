/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>办公资产维修/更换审批单(商业公司/总部)</p>
 * @author huangjian
 * @create 2012-7-24
 */
public class AssetsChangeSheetSyProp {

	private String assetCode;// 资产编码
	private String assmAccountId;// 资产编码ID

	private String assetName;// 设备名称

	private String model;// 型号


	private String useDate;// 购置日期
	private String srcValue;// 原值
	private String remainVal;// 净值
	/**
	 * 	申请原因
	 */
	private String saleReason;

	private String inFlag;//预算内
	private String outFlag;//预算外
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAssmAccountId() {
		return assmAccountId;
	}
	public void setAssmAccountId(String assmAccountId) {
		this.assmAccountId = assmAccountId;
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
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getSrcValue() {
		return srcValue;
	}
	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}
	public String getRemainVal() {
		return remainVal;
	}
	public void setRemainVal(String remainVal) {
		this.remainVal = remainVal;
	}
	public String getSaleReason() {
		return saleReason;
	}
	public void setSaleReason(String saleReason) {
		this.saleReason = saleReason;
	}
	public String getInFlag() {
		return inFlag;
	}
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}
	public String getOutFlag() {
		return outFlag;
	}
	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}
}
