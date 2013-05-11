package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 工程结算审批表
 * @author shixy
 * 2010-12-22
 */
public class ProjectSettlementSheet extends BaseTemplate {
	
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目名称Cd
	 */
	private String projectCd;
	/**
	 * 工程地点
	 */
	private String projectPlace;
	/**
	 * 工程名称
	 */
	private String engineeringName;
	/**
	 * 施工单位
	 */
	private String builder;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同金额
	 */
	private String contractAmount;
	/**
	 * 结算内容
	 */
	private String settlementContent;
	/**
	 * 承包方式
	 */
	private String contractWay;
	/**
	 * 施工单位报送金额（元）
	 */
	private String builderAmount;
	/**
	 * 地产公司审核金额（元）
	 */
	private String estateAmount;
	/**
	 * 面积（m2）
	 */
	private String acreage;
	/**
	 * 单位指标（元/m2）
	 */
	private String indicator;
	/**
	 * 工程结算额及说明
	 */
	private String info;
	
	/**
	 * 初审金额汇总表
	 */
	private String initAmountFileId;
	/**
	 * 结算资料明细表
	 */
	private String settDetailFileId;

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public String getInitAmountFileId() {
		return initAmountFileId;
	}

	public void setInitAmountFileId(String initAmountFileId) {
		this.initAmountFileId = initAmountFileId;
	}

	public String getSettDetailFileId() {
		return settDetailFileId;
	}

	public void setSettDetailFileId(String settDetailFileId) {
		this.settDetailFileId = settDetailFileId;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return engineeringName;
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

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
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

	public String getSettlementContent() {
		return settlementContent;
	}

	public void setSettlementContent(String settlementContent) {
		this.settlementContent = settlementContent;
	}

	public String getContractWay() {
		return contractWay;
	}

	public void setContractWay(String contractWay) {
		this.contractWay = contractWay;
	}


	public String getBuilderAmount() {
		return builderAmount;
	}

	public void setBuilderAmount(String builderAmount) {
		this.builderAmount = builderAmount;
	}

	public String getEstateAmount() {
		return estateAmount;
	}

	public void setEstateAmount(String estateAmount) {
		this.estateAmount = estateAmount;
	}

	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
