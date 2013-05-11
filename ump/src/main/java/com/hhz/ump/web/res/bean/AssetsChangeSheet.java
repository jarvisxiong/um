package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 办公资产维修/更换申请单
 * @author shixy
 *
 * 2011-1-13
 */
public class AssetsChangeSheet extends BaseTemplate {
	/**
	 * 申请日期
	 */
	private String applDate;
	/**
	 * 单号
	 */
	private String applNo;
	/**
	 * 申请人姓名
	 */
	private String userName;
	/**
	 * 所在部门
	 */
	private String dept;
	/**
	 * 资产编号
	 */
	private String assetNo;
	/**
	 * 购置时间
	 */
	private String buyDate;
	/**
	 * 申请原因
	 */
	private String applCause;

	private String totalMoney;//金额
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return super.getResProjectCd();
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return dept;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applNo;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getApplNo() {
		return applNo;
	}
	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getApplCause() {
		return applCause;
	}
	public void setApplCause(String applCause) {
		this.applCause = applCause;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
}
