package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 接待审批表
 * @author shixy
 *
 * 2010-12-25
 */
public class ReceiveApplSheet extends BaseTemplate {

	/**
	 * 申请中心：  xxx   
	 */
	private String applCenter;
	private String applCenterCd;
	
	/**
	 * 招待对象： 天津市土地局xxx专员
	 */
	private String receivePartner;
	
	/**
	 * 招待事由
	 */
	private String receiveCause;
	/**
	 * 招待费用承担单位： 企                
	 */
	private String amountPayCenter;
	/**
	 * 招待费用承担单位： 企                
	 */
	private String amountPayCenterCd;
	/**
	 * 住宿酒店：                          
	 */
	private String hotel;
	/**
	 * 住宿标准：
	 */
	private String hotelStandard;

	
	/**
	 * 入住时间
	 */
	private String checkInDate;
	/**
	 * 离店时间
	 */
	private String checkOutDate;
	
	/**
	 * 单人间数量
	 */
	private String singleRoomNum;
	/**
	 * 双人间数量
	 */
	private String doubleRoomNum;
	
	/**
	 * □	午餐    
	 */
	private String lunch;
	/**
	 * 用餐日期：
	 */
	private String lunchDate;
	
	/**
	 * □ 晚餐	
	 */
	private String dinner;
	/**
	 * 用餐日期：
	 */
	private String dinnerDate;
	/**
	 * 
	 * 用餐人数：                             	
	 */
	private String dinnerPartner;
	/**
	 * 宾客：           人 
	 */
	private String lunchGuestNum;
	/**
	 * 陪同：        人
	 */
	private String lunchGuideNum;
	/**
	 * 宾客：           人 
	 */
	private String dinnerGuestNum;
	/**
	 * 陪同：        人
	 */
	private String dinnerGuideNum;
	/**
	 * 餐厅
	 */
	private String dinnerPlace;
	/**
	 * 用餐标准：                          
	 */
	private String dinnerStandard;
	
	/**
	 * 白酒
	 */
	private String wine;
	/**
	 * 白酒数量
	 */
	private String wineNum;
	
	/**
	 * 红酒
	 */
	private String redWine;
	/**
	 * 红酒数量
	 */
	private String redWineNum;
	
	/**
	 * 酒水标准：
	 */
	private String drinkStandard;
	/**
	 * 娱乐             
	 */
	private String entertainment;
	
	private String remark;

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applCenter;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return applCenterCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return "招待:"+receivePartner;
	}

	public String getApplCenter() {
		return applCenter;
	}

	public void setApplCenter(String applCenter) {
		this.applCenter = applCenter;
	}

	public String getApplCenterCd() {
		return applCenterCd;
	}

	public void setApplCenterCd(String applCenterCd) {
		this.applCenterCd = applCenterCd;
	}

	public String getReceivePartner() {
		return receivePartner;
	}

	public void setReceivePartner(String receivePartner) {
		this.receivePartner = receivePartner;
	}

	public String getAmountPayCenter() {
		return amountPayCenter;
	}

	public void setAmountPayCenter(String amountPayCenter) {
		this.amountPayCenter = amountPayCenter;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getHotelStandard() {
		return hotelStandard;
	}

	public void setHotelStandard(String hotelStandard) {
		this.hotelStandard = hotelStandard;
	}


	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public String getDinnerDate() {
		return dinnerDate;
	}

	public void setDinnerDate(String dinnerDate) {
		this.dinnerDate = dinnerDate;
	}

	public String getDinnerPartner() {
		return dinnerPartner;
	}

	public void setDinnerPartner(String dinnerPartner) {
		this.dinnerPartner = dinnerPartner;
	}

	public String getDinnerGuestNum() {
		return dinnerGuestNum;
	}

	public void setDinnerGuestNum(String dinnerGuestNum) {
		this.dinnerGuestNum = dinnerGuestNum;
	}

	public String getDinnerGuideNum() {
		return dinnerGuideNum;
	}

	public void setDinnerGuideNum(String dinnerGuideNum) {
		this.dinnerGuideNum = dinnerGuideNum;
	}

	public String getDinnerPlace() {
		return dinnerPlace;
	}

	public void setDinnerPlace(String dinnerPlace) {
		this.dinnerPlace = dinnerPlace;
	}

	public String getDinnerStandard() {
		return dinnerStandard;
	}

	public void setDinnerStandard(String dinnerStandard) {
		this.dinnerStandard = dinnerStandard;
	}


	public String getWine() {
		return wine;
	}

	public void setWine(String wine) {
		this.wine = wine;
	}

	public String getWineNum() {
		return wineNum;
	}

	public void setWineNum(String wineNum) {
		this.wineNum = wineNum;
	}

	public String getRedWine() {
		return redWine;
	}

	public void setRedWine(String redWine) {
		this.redWine = redWine;
	}

	public String getRedWineNum() {
		return redWineNum;
	}

	public void setRedWineNum(String redWineNum) {
		this.redWineNum = redWineNum;
	}

	public String getDrinkStandard() {
		return drinkStandard;
	}

	public void setDrinkStandard(String drinkStandard) {
		this.drinkStandard = drinkStandard;
	}

	public String getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(String entertainment) {
		this.entertainment = entertainment;
	}

	public String getAmountPayCenterCd() {
		return amountPayCenterCd;
	}

	public void setAmountPayCenterCd(String amountPayCenterCd) {
		this.amountPayCenterCd = amountPayCenterCd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLunchDate() {
		return lunchDate;
	}

	public void setLunchDate(String lunchDate) {
		this.lunchDate = lunchDate;
	}

	public String getReceiveCause() {
		return receiveCause;
	}

	public void setReceiveCause(String receiveCause) {
		this.receiveCause = receiveCause;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getSingleRoomNum() {
		return singleRoomNum;
	}

	public void setSingleRoomNum(String singleRoomNum) {
		this.singleRoomNum = singleRoomNum;
	}

	public String getDoubleRoomNum() {
		return doubleRoomNum;
	}

	public void setDoubleRoomNum(String doubleRoomNum) {
		this.doubleRoomNum = doubleRoomNum;
	}

	public String getLunchGuestNum() {
		return lunchGuestNum;
	}

	public void setLunchGuestNum(String lunchGuestNum) {
		this.lunchGuestNum = lunchGuestNum;
	}

	public String getLunchGuideNum() {
		return lunchGuideNum;
	}

	public void setLunchGuideNum(String lunchGuideNum) {
		this.lunchGuideNum = lunchGuideNum;
	}

	
}
