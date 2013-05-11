package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 材料设备结算审批表
 * @author shixy
 *
 * 2010-12-22
 */
public class MaterialSettlementSheet extends BaseTemplate {

	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目CD
	 */
	private String projectCd;
	
	/**
	 * 工程地点
	 */
	private String engineeringPlace;
	
	/**
	 * 供货合同名称
	 */
	private String contractName;
	
	/**
	 *供货单位 
	 */
	private String vendor;
	
	/**
	 * 合同编号
	 */
	private String contractNo;
	
	/**
	 * 合同金额
	 */
	private String contractAmount;
	
	/**
	 * 供货单位报送金额（元）
	 */
	private String renderAmount;
	
	/**
	 * 承包方式
	 */
	private String contractWay;
	
	/**
	 * 供货单位（元）
	 */
	private String vendorAmount;
	
	/**
	 * （领货单位名称1）（元） 
	 */
	private String receiveAmount1;
	
	/**
	 * （领货单位名称2）（元）
	 */
	private String receiveAmount2;
	
	/**
	 * 库存（元）
	 */
	private String stockAmount;
	
	/**
	 * 供货/领货情况说明
	 */
	private String info;
	
	
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
		return contractName;
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

	public String getEngineeringPlace() {
		return engineeringPlace;
	}

	public void setEngineeringPlace(String engineeringPlace) {
		this.engineeringPlace = engineeringPlace;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public String getRenderAmount() {
		return renderAmount;
	}

	public void setRenderAmount(String renderAmount) {
		this.renderAmount = renderAmount;
	}

	public String getContractWay() {
		return contractWay;
	}

	public void setContractWay(String contractWay) {
		this.contractWay = contractWay;
	}

	public String getVendorAmount() {
		return vendorAmount;
	}

	public void setVendorAmount(String vendorAmount) {
		this.vendorAmount = vendorAmount;
	}

	public String getReceiveAmount1() {
		return receiveAmount1;
	}

	public void setReceiveAmount1(String receiveAmount1) {
		this.receiveAmount1 = receiveAmount1;
	}

	public String getReceiveAmount2() {
		return receiveAmount2;
	}

	public void setReceiveAmount2(String receiveAmount2) {
		this.receiveAmount2 = receiveAmount2;
	}

	public String getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
