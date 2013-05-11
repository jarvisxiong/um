package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 设计变更签证审批表
 * @author lujunyun 2010-12-21
 */
public class DesignChangeVisaApprove extends BaseTemplate {
	private String engineeringName;	//工程名称
	private String changeSN;	//设计变更单号
	private String constructionCompany;	//施工单位
	private String engineeringSN;	//工程代号
	private String visaNumber;	//拟签证号
	private String engineeringPlace;	//工程地点
	private String contractSn;	//合同编号
	private String contractMoney;	//合同金额
	private String visaRate;	//签证占合同金额比例
	private String contentAndReason;	//现场签证内容及原因
	private String changeMoney;	//增加或减少的费用(万元)
	
	
	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getChangeSN() {
		return changeSN;
	}

	public void setChangeSN(String changeSN) {
		this.changeSN = changeSN;
	}

	public String getConstructionCompany() {
		return constructionCompany;
	}

	public void setConstructionCompany(String constructionCompany) {
		this.constructionCompany = constructionCompany;
	}

	public String getEngineeringSN() {
		return engineeringSN;
	}

	public void setEngineeringSN(String engineeringSN) {
		this.engineeringSN = engineeringSN;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getEngineeringPlace() {
		return engineeringPlace;
	}

	public void setEngineeringPlace(String engineeringPlace) {
		this.engineeringPlace = engineeringPlace;
	}

	public String getContractSn() {
		return contractSn;
	}

	public void setContractSn(String contractSn) {
		this.contractSn = contractSn;
	}

	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	public String getVisaRate() {
		return visaRate;
	}

	public void setVisaRate(String visaRate) {
		this.visaRate = visaRate;
	}

	public String getContentAndReason() {
		return contentAndReason;
	}

	public void setContentAndReason(String contentAndReason) {
		this.contentAndReason = contentAndReason;
	}

	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return engineeringName;
	}
}
