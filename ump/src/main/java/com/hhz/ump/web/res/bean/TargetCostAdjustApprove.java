package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 目标成本调整审批表
 * @author baolm
 *
 * 2011-04-21
 */
public class TargetCostAdjustApprove extends BaseTemplate {
	
	/**
	 * □目标成本(可研版)
	 */
	private String checkType1;
	
	/**
	 * □目标成本
	 */
	private String checkType2;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 合同( )期
	 */
	private String projectPeriod;
	
	/**
	 * 开工时间
	 */
	private String startDate;
	
	/**
	 * 交房时间
	 */
	private String submitDate;
	
	/**
	 * 总建筑面积(㎡)
	 */
	private String totalBuildingArea;
	
	/**
	 * 计入容积率建筑面积(㎡)
	 */
	private String capacityRatioBuildingArea;
	
	/**
	 * 原目标成本总额(元)
	 */
	private String oriTotalTargetCost;
	
	/**
	 * 原容积率面积单方造价(元/㎡)
	 */
	private String oriCapacityRatioUnitCost;
	
	/**
	 * 调整后目标成本总额(元)
	 */
	private String totalTargetCost;
	
	/**
	 * 调整后容积率面积单方造价(元/㎡)
	 */
	private String capacityRatioUnitCost;
	
	/**
	 * 调整子项列表
	 */
	private List<TargetCostAdjustOtherProperty> otherProperties = new ArrayList<TargetCostAdjustOtherProperty>();
	
	/**
	 * 调整子项成本明细表
	 */
	private String subItemTargetCostDetailId;
	
	public String getCheckType1() {
		return checkType1;
	}

	public void setCheckType1(String checkType1) {
		this.checkType1 = checkType1;
	}

	public String getCheckType2() {
		return checkType2;
	}

	public void setCheckType2(String checkType2) {
		this.checkType2 = checkType2;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getTotalBuildingArea() {
		return totalBuildingArea;
	}

	public void setTotalBuildingArea(String totalBuildingArea) {
		this.totalBuildingArea = totalBuildingArea;
	}

	public String getCapacityRatioBuildingArea() {
		return capacityRatioBuildingArea;
	}

	public void setCapacityRatioBuildingArea(String capacityRatioBuildingArea) {
		this.capacityRatioBuildingArea = capacityRatioBuildingArea;
	}

	public String getTotalTargetCost() {
		return totalTargetCost;
	}

	public void setTotalTargetCost(String totalTargetCost) {
		this.totalTargetCost = totalTargetCost;
	}

	public String getCapacityRatioUnitCost() {
		return capacityRatioUnitCost;
	}

	public void setCapacityRatioUnitCost(String capacityRatioUnitCost) {
		this.capacityRatioUnitCost = capacityRatioUnitCost;
	}

	public String getOriTotalTargetCost() {
		return oriTotalTargetCost;
	}

	public void setOriTotalTargetCost(String oriTotalTargetCost) {
		this.oriTotalTargetCost = oriTotalTargetCost;
	}

	public String getOriCapacityRatioUnitCost() {
		return oriCapacityRatioUnitCost;
	}

	public void setOriCapacityRatioUnitCost(String oriCapacityRatioUnitCost) {
		this.oriCapacityRatioUnitCost = oriCapacityRatioUnitCost;
	}

	public List<TargetCostAdjustOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(
			List<TargetCostAdjustOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getSubItemTargetCostDetailId() {
		return subItemTargetCostDetailId;
	}

	public void setSubItemTargetCostDetailId(String subItemTargetCostDetailId) {
		this.subItemTargetCostDetailId = subItemTargetCostDetailId;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

}
