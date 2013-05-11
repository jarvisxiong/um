package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>地产、行业、商业车辆购置审批表</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-23
 */

public class LandCarBuyApplySheet extends BaseTemplate {
	
	//标准
	private String carStandard1;
	
	//非标准
	private String carStandard2;

	//选购车型
	private String selectCarType ;
	
	//申购日期
	private String applyDateTime;
	
	//当地报价
	private String localPrice;
	
	//已购车辆
	private String yetBuyCars;
	
	//购车理由
	private String buyCarReason;

	/**
	 * @return the selectCarType
	 */
	public String getSelectCarType() {
		return selectCarType;
	}

	/**
	 * @param selectCarType the selectCarType to set
	 */
	public void setSelectCarType(String selectCarType) {
		this.selectCarType = selectCarType;
	}

	/**
	 * @return the applyDateTime
	 */
	public String getApplyDateTime() {
		return applyDateTime;
	}

	/**
	 * @param applyDateTime the applyDateTime to set
	 */
	public void setApplyDateTime(String applyDateTime) {
		this.applyDateTime = applyDateTime;
	}

	/**
	 * @return the localPrice
	 */
	public String getLocalPrice() {
		return localPrice;
	}

	/**
	 * @param localPrice the localPrice to set
	 */
	public void setLocalPrice(String localPrice) {
		this.localPrice = localPrice;
	}

	/**
	 * @return the yetBuyCars
	 */
	public String getYetBuyCars() {
		return yetBuyCars;
	}

	/**
	 * @param yetBuyCars the yetBuyCars to set
	 */
	public void setYetBuyCars(String yetBuyCars) {
		this.yetBuyCars = yetBuyCars;
	}

	/**
	 * @return the buyCarReason
	 */
	public String getBuyCarReason() {
		return buyCarReason;
	}

	/**
	 * @param buyCarReason the buyCarReason to set
	 */
	public void setBuyCarReason(String buyCarReason) {
		this.buyCarReason = buyCarReason;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return selectCarType;
	}

	/**
	 * @return the carStandard1
	 */
	public String getCarStandard1() {
		return carStandard1;
	}

	/**
	 * @param carStandard1 the carStandard1 to set
	 */
	public void setCarStandard1(String carStandard1) {
		this.carStandard1 = carStandard1;
	}

	/**
	 * @return the carStandard2
	 */
	public String getCarStandard2() {
		return carStandard2;
	}

	/**
	 * @param carStandard2 the carStandard2 to set
	 */
	public void setCarStandard2(String carStandard2) {
		this.carStandard2 = carStandard2;
	}

	
	
	
}
