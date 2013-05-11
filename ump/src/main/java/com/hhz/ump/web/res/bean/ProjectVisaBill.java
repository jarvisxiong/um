package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/*
 * 工程签证审批表
 */
public class ProjectVisaBill extends BaseTemplate{

	private String projectName;
	private String builder;
	private String projectNo;
	private String signCard;
	private String projectAddress;
	private String contractNo;
	private String contractAmount;
	private String signScale;
	private String signContent;
	private String updateMoney;
	private String addOrMinusFee;//预计增加或减少的费用
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contractNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getSignCard() {
		return signCard;
	}

	public void setSignCard(String signCard) {
		this.signCard = signCard;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getSignScale() {
		return signScale;
	}

	public void setSignScale(String signScale) {
		this.signScale = signScale;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public String getUpdateMoney() {
		return updateMoney;
	}

	public void setUpdateMoney(String updateMoney) {
		this.updateMoney = updateMoney;
	}

	public String getAddOrMinusFee() {
		return addOrMinusFee;
	}

	public void setAddOrMinusFee(String addOrMinusFee) {
		this.addOrMinusFee = addOrMinusFee;
	}

}
