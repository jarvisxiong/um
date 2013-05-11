package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 合约规划审批表
 * 
 * @author baolm 2011-04-12
 */
public class ContractPlanningApprove extends BaseTemplate {
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 开发计划
	 */
	private String totalPeriodTimes;
	
	/**
	 * 开发周期
	 */
	private List<ContractPlanningOtherProperty> otherProperties = new ArrayList<ContractPlanningOtherProperty>();

	/**
	 * 总用地面积
	 */
	private String totalAreaIndex;
	private String totalAreaDesc;
	
	/**
	 * 可开发用地面积
	 */
	private String developAreaIndex;
	private String developAreaDesc;
	
	/**
	 * 规划容积率
	 */
	private String pcRateIndex;
	private String pcRateDesc;
	
	/**
	 * 地上总建筑面积(计容积率)
	 */
	private String totalBuildAreaIndex;
	private String totalBuildAreaDesc;
	
	/**
	 * 建筑密度
	 */
	private String buildDensityIndex;
	private String buildDensityDesc;
	
	/**
	 * 绿化率
	 */
	private String greenRateIndex;
	private String greenRateDesc;
	
	/**
	 * 住宅
	 */
	private String houseArea;
	private String houseRate;
	private String houseDesc;
	
	/**
	 * 酒店(星级)
	 */
	private String starHotelArea;
	private String starHotelRate;
	private String starHotelDesc;
	
	/**
	 * 度假公寓
	 */
	private String holidayFlatArea;
	private String holidayFlatRate;
	private String holidayFlatDesc;
	
	/**
	 * 企业公馆
	 */
	private String enteMansionArea;
	private String enteMansionRate;
	private String enteMansionDesc;
	
	/**
	 * 餐饮休闲街
	 */
	private String foodStreetArea;
	private String foodStreetRate;
	private String foodStreetDesc;
	
	/**
	 * 商业mall
	 */
	private String mallArea;
	private String mallRate;
	private String mallDesc;
	
	/**
	 * 合计
	 */
	private String totalArea;
	private String totalRate;
	private String totalDesc;
	
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

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getTotalPeriodTimes() {
		return totalPeriodTimes;
	}

	public void setTotalPeriodTimes(String totalPeriodTimes) {
		this.totalPeriodTimes = totalPeriodTimes;
	}

	public List<ContractPlanningOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(
			List<ContractPlanningOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getTotalAreaIndex() {
		return totalAreaIndex;
	}

	public void setTotalAreaIndex(String totalAreaIndex) {
		this.totalAreaIndex = totalAreaIndex;
	}

	public String getTotalAreaDesc() {
		return totalAreaDesc;
	}

	public void setTotalAreaDesc(String totalAreaDesc) {
		this.totalAreaDesc = totalAreaDesc;
	}

	public String getDevelopAreaIndex() {
		return developAreaIndex;
	}

	public void setDevelopAreaIndex(String developAreaIndex) {
		this.developAreaIndex = developAreaIndex;
	}

	public String getDevelopAreaDesc() {
		return developAreaDesc;
	}

	public void setDevelopAreaDesc(String developAreaDesc) {
		this.developAreaDesc = developAreaDesc;
	}

	public String getPcRateIndex() {
		return pcRateIndex;
	}

	public void setPcRateIndex(String pcRateIndex) {
		this.pcRateIndex = pcRateIndex;
	}

	public String getPcRateDesc() {
		return pcRateDesc;
	}

	public void setPcRateDesc(String pcRateDesc) {
		this.pcRateDesc = pcRateDesc;
	}

	public String getTotalBuildAreaIndex() {
		return totalBuildAreaIndex;
	}

	public void setTotalBuildAreaIndex(String totalBuildAreaIndex) {
		this.totalBuildAreaIndex = totalBuildAreaIndex;
	}

	public String getTotalBuildAreaDesc() {
		return totalBuildAreaDesc;
	}

	public void setTotalBuildAreaDesc(String totalBuildAreaDesc) {
		this.totalBuildAreaDesc = totalBuildAreaDesc;
	}

	public String getBuildDensityIndex() {
		return buildDensityIndex;
	}

	public void setBuildDensityIndex(String buildDensityIndex) {
		this.buildDensityIndex = buildDensityIndex;
	}

	public String getBuildDensityDesc() {
		return buildDensityDesc;
	}

	public void setBuildDensityDesc(String buildDensityDesc) {
		this.buildDensityDesc = buildDensityDesc;
	}

	public String getGreenRateIndex() {
		return greenRateIndex;
	}

	public void setGreenRateIndex(String greenRateIndex) {
		this.greenRateIndex = greenRateIndex;
	}

	public String getGreenRateDesc() {
		return greenRateDesc;
	}

	public void setGreenRateDesc(String greenRateDesc) {
		this.greenRateDesc = greenRateDesc;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getHouseRate() {
		return houseRate;
	}

	public void setHouseRate(String houseRate) {
		this.houseRate = houseRate;
	}

	public String getHouseDesc() {
		return houseDesc;
	}

	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}

	public String getStarHotelArea() {
		return starHotelArea;
	}

	public void setStarHotelArea(String starHotelArea) {
		this.starHotelArea = starHotelArea;
	}

	public String getStarHotelRate() {
		return starHotelRate;
	}

	public void setStarHotelRate(String starHotelRate) {
		this.starHotelRate = starHotelRate;
	}

	public String getStarHotelDesc() {
		return starHotelDesc;
	}

	public void setStarHotelDesc(String starHotelDesc) {
		this.starHotelDesc = starHotelDesc;
	}

	public String getHolidayFlatArea() {
		return holidayFlatArea;
	}

	public void setHolidayFlatArea(String holidayFlatArea) {
		this.holidayFlatArea = holidayFlatArea;
	}

	public String getHolidayFlatRate() {
		return holidayFlatRate;
	}

	public void setHolidayFlatRate(String holidayFlatRate) {
		this.holidayFlatRate = holidayFlatRate;
	}

	public String getHolidayFlatDesc() {
		return holidayFlatDesc;
	}

	public void setHolidayFlatDesc(String holidayFlatDesc) {
		this.holidayFlatDesc = holidayFlatDesc;
	}

	public String getEnteMansionArea() {
		return enteMansionArea;
	}

	public void setEnteMansionArea(String enteMansionArea) {
		this.enteMansionArea = enteMansionArea;
	}

	public String getEnteMansionRate() {
		return enteMansionRate;
	}

	public void setEnteMansionRate(String enteMansionRate) {
		this.enteMansionRate = enteMansionRate;
	}

	public String getEnteMansionDesc() {
		return enteMansionDesc;
	}

	public void setEnteMansionDesc(String enteMansionDesc) {
		this.enteMansionDesc = enteMansionDesc;
	}

	public String getFoodStreetArea() {
		return foodStreetArea;
	}

	public void setFoodStreetArea(String foodStreetArea) {
		this.foodStreetArea = foodStreetArea;
	}

	public String getFoodStreetRate() {
		return foodStreetRate;
	}

	public void setFoodStreetRate(String foodStreetRate) {
		this.foodStreetRate = foodStreetRate;
	}

	public String getFoodStreetDesc() {
		return foodStreetDesc;
	}

	public void setFoodStreetDesc(String foodStreetDesc) {
		this.foodStreetDesc = foodStreetDesc;
	}

	public String getMallArea() {
		return mallArea;
	}

	public void setMallArea(String mallArea) {
		this.mallArea = mallArea;
	}

	public String getMallRate() {
		return mallRate;
	}

	public void setMallRate(String mallRate) {
		this.mallRate = mallRate;
	}

	public String getMallDesc() {
		return mallDesc;
	}

	public void setMallDesc(String mallDesc) {
		this.mallDesc = mallDesc;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(String totalRate) {
		this.totalRate = totalRate;
	}

	public String getTotalDesc() {
		return totalDesc;
	}

	public void setTotalDesc(String totalDesc) {
		this.totalDesc = totalDesc;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}
	
	@Override
	public String getResProjectName() {
		return projectName;
	}
	
	@Override
	public String getResTitleName() {
		return engineeringName;
	}
	
}
