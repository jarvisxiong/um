package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 办公资产出售/报废/遗失申请单
 * @author shixy
 *
 * 2011-1-13
 */
public class AssetsSaleSheet extends BaseTemplate {

	/**
	 * 	设备名称
	 */
	private String assetName;
	/**
	 * 分类编号
	 */
	private String typeNo;
	/**
	 * 	使用部门
	 */
	private String useDept;
	/**
	 * 	使用责任人
	 */
	private String useUser;
	/**
	 * 	规格型号
	 */
	private String modelNo;
	/**
	 * 	数量
	 */
	private String assetNum;
	/**
	 * 	购置时间
	 */
	private String buyDate;
	/**
	 * 	使用年限
	 */
	private String userYear;
	/**
	 * 	出售/报废/遗失原因
	 */
	private String saleCause;

	private String totalMoney;//金额
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getUseDept() {
		return useDept;
	}
	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}
	public String getUseUser() {
		return useUser;
	}
	public void setUseUser(String useUser) {
		this.useUser = useUser;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getUserYear() {
		return userYear;
	}
	public void setUserYear(String userYear) {
		this.userYear = userYear;
	}
	public String getSaleCause() {
		return saleCause;
	}
	public void setSaleCause(String saleCause) {
		this.saleCause = saleCause;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return assetName;
	}
	
	
}
