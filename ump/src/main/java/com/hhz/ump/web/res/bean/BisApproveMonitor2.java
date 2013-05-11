package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 总经理费用报销审批表2(细化版)
 * @author liwei
 *
 * 2012-07-03
 */
public class BisApproveMonitor2 extends BaseTemplate {
	
	/***
	 * 职级
	 */
	private String positionGrade;
	/**
	 * 发起公司
	 */
	private String companyName;
	/**
	 * 公司CD
	 */
	private String companyCd;
	/**
	 * 是否为紧急
	 */
	private String isUrgency;
	/***
	 * 总金额
	 */
	private String totalAmount;

	/***
	 * 出差补贴
	 */
	private List<BisOnBusinessSubsidy> onBusiness = new ArrayList<BisOnBusinessSubsidy>();
	
	/***
	 * 住宿费
	 */
	private List<BisStopAtCost> stopAt = new ArrayList<BisStopAtCost>();
	
	/***
	 * 差旅费
	 */
	private List<BisTravelCost> travel = new ArrayList<BisTravelCost>();
	
	/***
	 * 其它费用
	 */
	private List<BisOtherCost> other = new ArrayList<BisOtherCost>();

	
	/**
	 * @return the positionGrade
	 */
	public String getPositionGrade() {
		return positionGrade;
	}

	/**
	 * @param positionGrade the positionGrade to set
	 */
	public void setPositionGrade(String positionGrade) {
		this.positionGrade = positionGrade;
	}

	/**
	 * @return the companyName
	 */
	@Override
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd the companyCd to set
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return the isUrgency
	 */
	public String getIsUrgency() {
		return isUrgency;
	}

	/**
	 * @param isUrgency the isUrgency to set
	 */
	public void setIsUrgency(String isUrgency) {
		this.isUrgency = isUrgency;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the onBusiness
	 */
	public List<BisOnBusinessSubsidy> getOnBusiness() {
		return onBusiness;
	}

	/**
	 * @param onBusiness the onBusiness to set
	 */
	public void setOnBusiness(List<BisOnBusinessSubsidy> onBusiness) {
		this.onBusiness = onBusiness;
	}

	/**
	 * @return the stopAt
	 */
	public List<BisStopAtCost> getStopAt() {
		return stopAt;
	}

	/**
	 * @param stopAt the stopAt to set
	 */
	public void setStopAt(List<BisStopAtCost> stopAt) {
		this.stopAt = stopAt;
	}

	/**
	 * @return the travel
	 */
	public List<BisTravelCost> getTravel() {
		return travel;
	}

	/**
	 * @param travel the travel to set
	 */
	public void setTravel(List<BisTravelCost> travel) {
		this.travel = travel;
	}

	/**
	 * @return the other
	 */
	public List<BisOtherCost> getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(List<BisOtherCost> other) {
		this.other = other;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return totalAmount;
	}
	
}
