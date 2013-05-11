/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>
 * 办公资产出售/报废/遗失申请单(商业公司/总部)
 * </p>
 * 
 * @author huangjian
 * @create 2012-7-24
 */
public class AssetsSaleSheetSyProp {

	private String assetCode;// 资产编码
	private String assmAccountId;// 资产编码ID

	private String assetName;// 设备名称

	private String model;// 型号
	private String assmCode;// 型号编码
	private String useDept;// 使用部门

	private String useDate;// 购置日期
	private String srcValue;// 原值
	private String remainVal;// 净值
	private String currNum;// 当前配置
	private String stanNum;// 标准配置
	
	private String usePersonName;// 使用责任人
	private String usePersonCd;// 使用责任人
	private String useYear;// 使用年限
	private String remark;// 情况说明
	// 类型：出售，报废，遗失
	private String saleType1;
	private String saleType2;
	private String saleType3;

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

	public String getAssmCode() {
		return assmCode;
	}

	public void setAssmCode(String assmCode) {
		this.assmCode = assmCode;
	}

	public String getUseDept() {
		return useDept;
	}

	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}

	public String getCurrNum() {
		return currNum;
	}

	public void setCurrNum(String currNum) {
		this.currNum = currNum;
	}

	public String getStanNum() {
		return stanNum;
	}

	public void setStanNum(String stanNum) {
		this.stanNum = stanNum;
	}


	public String getUseYear() {
		return useYear;
	}

	public void setUseYear(String useYear) {
		this.useYear = useYear;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getUsePersonName() {
		return usePersonName;
	}

	public void setUsePersonName(String usePersonName) {
		this.usePersonName = usePersonName;
	}

	public String getUsePersonCd() {
		return usePersonCd;
	}

	public void setUsePersonCd(String usePersonCd) {
		this.usePersonCd = usePersonCd;
	}

	public String getSaleType1() {
		return saleType1;
	}

	public void setSaleType1(String saleType1) {
		this.saleType1 = saleType1;
	}

	public String getSaleType2() {
		return saleType2;
	}

	public void setSaleType2(String saleType2) {
		this.saleType2 = saleType2;
	}

	public String getSaleType3() {
		return saleType3;
	}

	public void setSaleType3(String saleType3) {
		this.saleType3 = saleType3;
	}
}
