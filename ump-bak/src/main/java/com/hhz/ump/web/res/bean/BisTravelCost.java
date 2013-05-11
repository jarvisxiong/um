package com.hhz.ump.web.res.bean;

/***
 * 差旅费
 * @author liwei
 * 
 * 2012-07-03
 */
public class BisTravelCost {

	/***
	 * 差旅费#出发地
	 */
	private String travelFrom;
	/***
	 * 差旅费#目的地
	 */
	private String travelTo;
	/***
	 * 差旅费#交通方式
	 */
	private String trafficWay;
	/***
	 * 差旅费#费用
	 */
	private String costAmount;
	/***
	 * 差旅费#附件
	 */
	private String travelAttach;
	
	
	/**
	 * @return the travelFrom
	 */
	public String getTravelFrom() {
		return travelFrom;
	}
	/**
	 * @param travelFrom the travelFrom to set
	 */
	public void setTravelFrom(String travelFrom) {
		this.travelFrom = travelFrom;
	}
	/**
	 * @return the travelTo
	 */
	public String getTravelTo() {
		return travelTo;
	}
	/**
	 * @param travelTo the travelTo to set
	 */
	public void setTravelTo(String travelTo) {
		this.travelTo = travelTo;
	}
	/**
	 * @return the trafficWay
	 */
	public String getTrafficWay() {
		return trafficWay;
	}
	/**
	 * @param trafficWay the trafficWay to set
	 */
	public void setTrafficWay(String trafficWay) {
		this.trafficWay = trafficWay;
	}
	/**
	 * @return the costAmount
	 */
	public String getCostAmount() {
		return costAmount;
	}
	/**
	 * @param costAmount the costAmount to set
	 */
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}
	/**
	 * @return the travelAttach
	 */
	public String getTravelAttach() {
		return travelAttach;
	}
	/**
	 * @param travelAttach the travelAttach to set
	 */
	public void setTravelAttach(String travelAttach) {
		this.travelAttach = travelAttach;
	}

	
}
