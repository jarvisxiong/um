package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 融资类合同审批表
 */
public class FinancingAuditBill extends BaseTemplate{

	private String partA;
	private String partB;
	private String partC;       // 丙方 -Add for part C by zhuxj on 2012.3.31
	private String partASign;
	private String partBSign;
	private String partCSign;   // 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	private String mainContent;
	private String contractPrice;
	private String payWay;
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return partB;
	}

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getPartCSign() {
		return partCSign;
	}

	public void setPartCSign(String partCSign) {
		this.partCSign = partCSign;
	}

	public String getPartASign() {
		return partASign;
	}

	public void setPartASign(String partASign) {
		this.partASign = partASign;
	}

	public String getPartBSign() {
		return partBSign;
	}

	public void setPartBSign(String partBSign) {
		this.partBSign = partBSign;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

}
