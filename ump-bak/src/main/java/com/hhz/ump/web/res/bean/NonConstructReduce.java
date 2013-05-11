package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 另行委托/未施工扣减审批表
 * @author lujunyun 2010-12-21
 */
public class NonConstructReduce extends BaseTemplate {
	private String projectName;	//项目名称
	private String projectCd;	//项目名称
	private String projectPlace;	//项目地点
	private String engineeringName;	//工程名称
	private String reduceCompany;	//扣减单位名称
	private String reduceContractSN;	//扣减合同编号
	private String reduceMoney;	//扣减费用（元）
	private String otherTrustCompany;	//另行委托单位
	private String otherTrustSN;	//另行委托单位
	private String costMoney;	//费用支出（元）
	private String reduceReason;	//扣减内容、原因
	private String drawingSN;	//注：附图纸，编号
	private String instruction;	//核价编制说明
	private String contentAndReason;	//变更内容及原因
	private String clauseNumber;	//1、	变更合同价款按合同    条款
	private String mothodNumber;	//第     种方法
	private String calculate;	//其中工程量按      计量
	private String calculatePrice;	//单价按     确定
	
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

	public String getProjectPlace() {
		return projectPlace;
	}

	public void setProjectPlace(String projectPlace) {
		this.projectPlace = projectPlace;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getReduceCompany() {
		return reduceCompany;
	}

	public void setReduceCompany(String reduceCompany) {
		this.reduceCompany = reduceCompany;
	}

	public String getReduceContractSN() {
		return reduceContractSN;
	}

	public void setReduceContractSN(String reduceContractSN) {
		this.reduceContractSN = reduceContractSN;
	}

	public String getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getOtherTrustCompany() {
		return otherTrustCompany;
	}

	public void setOtherTrustCompany(String otherTrustCompany) {
		this.otherTrustCompany = otherTrustCompany;
	}

	public String getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(String costMoney) {
		this.costMoney = costMoney;
	}

	public String getReduceReason() {
		return reduceReason;
	}

	public void setReduceReason(String reduceReason) {
		this.reduceReason = reduceReason;
	}

	public String getDrawingSN() {
		return drawingSN;
	}

	public void setDrawingSN(String drawingSN) {
		this.drawingSN = drawingSN;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getClauseNumber() {
		return clauseNumber;
	}

	public void setClauseNumber(String clauseNumber) {
		this.clauseNumber = clauseNumber;
	}

	public String getMothodNumber() {
		return mothodNumber;
	}

	public void setMothodNumber(String mothodNumber) {
		this.mothodNumber = mothodNumber;
	}

	public String getCalculate() {
		return calculate;
	}

	public void setCalculate(String calculate) {
		this.calculate = calculate;
	}

	public String getCalculatePrice() {
		return calculatePrice;
	}

	public void setCalculatePrice(String calculatePrice) {
		this.calculatePrice = calculatePrice;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return engineeringName;
	}

	public String getOtherTrustSN() {
		return otherTrustSN;
	}

	public void setOtherTrustSN(String otherTrustSN) {
		this.otherTrustSN = otherTrustSN;
	}

	public String getContentAndReason() {
		return contentAndReason;
	}

	public void setContentAndReason(String contentAndReason) {
		this.contentAndReason = contentAndReason;
	}
}
