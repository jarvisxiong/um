package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>项目预可研报告审批表Bean</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-15
 */

public class PreProjectReseachReportSheet extends BaseTemplate {
	
	private String projectName;	//项目名称
	
	private String projectAddr;	//项目地址位置
	
	private String cityGDP;		//全市国内生产总值
	
	private String peopleGDP;	//全市人均生产总值
	
	private String inhabitant;	//区城市人口或区常住总人口
	
	private String averageWage;	//在岗职工平均工资
	
	private String disposablePersonalIncome;	//人均可支配收入
	
	private String consumptionPerPerson;		//人均生活消费支出
	
	private String consumerProductsSum;			//社会消费品零售总额
	
	private String majorIndustry;				//主要产业
	
	private String projectAreaState;			//项目区域概况
	
	private String businessPrice;				//沿街商业售价or租金
	
	private String cityAvgPrice;				//城市综合体的平均售价or租金
	
	private String preSuggest;					//业态规划初步建议
	
	private String otherState;					//其它说明
	
	//主要商业介绍（大型超市、百货、综合体）
	private List<ReseachReportBusinessIntroduce> reseachReportBusinessIntroduce = new ArrayList<ReseachReportBusinessIntroduce>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectAddr() {
		return projectAddr;
	}

	public void setProjectAddr(String projectAddr) {
		this.projectAddr = projectAddr;
	}

	public String getCityGDP() {
		return cityGDP;
	}

	public void setCityGDP(String cityGDP) {
		this.cityGDP = cityGDP;
	}

	public String getPeopleGDP() {
		return peopleGDP;
	}

	public void setPeopleGDP(String peopleGDP) {
		this.peopleGDP = peopleGDP;
	}

	public String getInhabitant() {
		return inhabitant;
	}

	public void setInhabitant(String inhabitant) {
		this.inhabitant = inhabitant;
	}

	public String getAverageWage() {
		return averageWage;
	}

	public void setAverageWage(String averageWage) {
		this.averageWage = averageWage;
	}

	public String getDisposablePersonalIncome() {
		return disposablePersonalIncome;
	}

	public void setDisposablePersonalIncome(String disposablePersonalIncome) {
		this.disposablePersonalIncome = disposablePersonalIncome;
	}

	public String getConsumptionPerPerson() {
		return consumptionPerPerson;
	}

	public void setConsumptionPerPerson(String consumptionPerPerson) {
		this.consumptionPerPerson = consumptionPerPerson;
	}

	public String getConsumerProductsSum() {
		return consumerProductsSum;
	}

	public void setConsumerProductsSum(String consumerProductsSum) {
		this.consumerProductsSum = consumerProductsSum;
	}

	public String getMajorIndustry() {
		return majorIndustry;
	}

	public void setMajorIndustry(String majorIndustry) {
		this.majorIndustry = majorIndustry;
	}

	public String getProjectAreaState() {
		return projectAreaState;
	}

	public void setProjectAreaState(String projectAreaState) {
		this.projectAreaState = projectAreaState;
	}

	public String getBusinessPrice() {
		return businessPrice;
	}

	public void setBusinessPrice(String businessPrice) {
		this.businessPrice = businessPrice;
	}

	public String getCityAvgPrice() {
		return cityAvgPrice;
	}

	public void setCityAvgPrice(String cityAvgPrice) {
		this.cityAvgPrice = cityAvgPrice;
	}

	public String getPreSuggest() {
		return preSuggest;
	}

	public void setPreSuggest(String preSuggest) {
		this.preSuggest = preSuggest;
	}

	public String getOtherState() {
		return otherState;
	}

	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}

	public List<ReseachReportBusinessIntroduce> getReseachReportBusinessIntroduce() {
		return reseachReportBusinessIntroduce;
	}

	public void setReseachReportBusinessIntroduce(
			List<ReseachReportBusinessIntroduce> reseachReportBusinessIntroduce) {
		this.reseachReportBusinessIntroduce = reseachReportBusinessIntroduce;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	
	
	
	
	
	

}
