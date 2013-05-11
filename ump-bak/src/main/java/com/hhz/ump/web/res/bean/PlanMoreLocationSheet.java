package com.hhz.ump.web.res.bean;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

/**
 * <p>
 * 计划内外多经点位审批表
 * </p>
 * 
 * @author hy
 * @version 1.00 2012-2-1
 */

public class PlanMoreLocationSheet extends BaseTemplate implements IStatusModifyListener {
	//项目名称
	private String projectName;
	/**
	 * 项目Cd
	 */
	private String projectCd;

	// 场内商户
	private String inCustomer;

	// 场外
	private String outCustomer;

	// 商户名称
	private String customerName;

	// 区域
	private String area;

	// 点位类型
	private String locationType;

	// 点位编号
	private String locationNum;

	// 经营业态标准、审批条件、批注
	private String manageType1;
	private String manageType2;
	private String manageType3;

	// 租凭面积签标准、审批条件、批注
	private String rentType1;
	private String rentType2;
	private String rentType3;

	// 装修标准、审批条件、批注
	private String fillerType1;
	private String fillerType2;
	private String fillerType3;

	// 合约期限 标准、审批条件、批注
	private String contDateType1;
	private String contDateType2;
	private String contDateType3;

	// 免租期 标准、审批条件、批注

	private String rentFreePeriodType1;
	private String rentFreePeriodType2;
	private String rentFreePeriodType3;

	// 租金标准（元/月） 标准、审批条件、批注

	private String rentStandrdType1;
	private String rentStandrdType2;
	private String rentStandrdType3;

	// 租赁期总收益（元）标准、审批条件、批注

	private String rentIncomeType1;
	private String rentIncomeType2;
	private String rentIncomeType3;
	// 付款方式 标准、审批条件、批注
	private String methodPaymentType1;
	private String methodPaymentType2;
	private String methodPaymentType3;

	// 运营保证金 标准、审批条件、批注
	private String operatingMarginType1;
	private String operatingMarginType2;
	private String operatingMarginType3;

	// 交付时间 标准、审批条件、批注
	private String handOverDateType1;
	private String handOverDateType2;
	private String handOverDateType3;

	
	//标准合同
	private String standard;
	
	//非标准合同
	private String nonstandard;
	
	private String contractTempletInfoId;
	//合同历史版id
	private String contractTempletHisId;
	
	//使用合同库
	private String contlib;
	//不使用合同库
	private String noncontlib;
	//合同编号
	private String contractNo;
	//合同名称
	private String contractName;
	
	// 装修期 标准、审批条件、批注

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the nonstandard
	 */
	public String getNonstandard() {
		return nonstandard;
	}

	/**
	 * @param nonstandard the nonstandard to set
	 */
	public void setNonstandard(String nonstandard) {
		this.nonstandard = nonstandard;
	}

	/**
	 * @return the contractTempletInfoId
	 */
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @param contractTempletInfoId the contractTempletInfoId to set
	 */
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @return the contractTempletHisId
	 */
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @param contractTempletHisId the contractTempletHisId to set
	 */
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	/**
	 * @return the contlib
	 */
	public String getContlib() {
		return contlib;
	}

	/**
	 * @param contlib the contlib to set
	 */
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @return the noncontlib
	 */
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param noncontlib the noncontlib to set
	 */
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}



	private String fillerPeriodType1;

	// 其他条件
	private String otherCondType;


	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectCd
	 */
	public String getProjectCd() {
		return projectCd;
	}

	/**
	 * @param projectCd the projectCd to set
	 */
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	/**
	 * @return the inCustomer
	 */
	public String getInCustomer() {
		return inCustomer;
	}

	/**
	 * @param inCustomer
	 *            the inCustomer to set
	 */
	public void setInCustomer(String inCustomer) {
		this.inCustomer = inCustomer;
	}

	/**
	 * @return the outCustomer
	 */
	public String getOutCustomer() {
		return outCustomer;
	}

	/**
	 * @param outCustomer
	 *            the outCustomer to set
	 */
	public void setOutCustomer(String outCustomer) {
		this.outCustomer = outCustomer;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @param locationType
	 *            the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the locationNum
	 */
	public String getLocationNum() {
		return locationNum;
	}

	/**
	 * @param locationNum
	 *            the locationNum to set
	 */
	public void setLocationNum(String locationNum) {
		this.locationNum = locationNum;
	}

	/**
	 * @return the manageType1
	 */
	public String getManageType1() {
		return manageType1;
	}

	/**
	 * @param manageType1
	 *            the manageType1 to set
	 */
	public void setManageType1(String manageType1) {
		this.manageType1 = manageType1;
	}

	/**
	 * @return the manageType2
	 */
	public String getManageType2() {
		return manageType2;
	}

	/**
	 * @param manageType2
	 *            the manageType2 to set
	 */
	public void setManageType2(String manageType2) {
		this.manageType2 = manageType2;
	}

	/**
	 * @return the manageType3
	 */
	public String getManageType3() {
		return manageType3;
	}

	/**
	 * @param manageType3
	 *            the manageType3 to set
	 */
	public void setManageType3(String manageType3) {
		this.manageType3 = manageType3;
	}

	/**
	 * @return the rentType1
	 */
	public String getRentType1() {
		return rentType1;
	}

	/**
	 * @param rentType1
	 *            the rentType1 to set
	 */
	public void setRentType1(String rentType1) {
		this.rentType1 = rentType1;
	}

	/**
	 * @return the rentType2
	 */
	public String getRentType2() {
		return rentType2;
	}

	/**
	 * @param rentType2
	 *            the rentType2 to set
	 */
	public void setRentType2(String rentType2) {
		this.rentType2 = rentType2;
	}

	/**
	 * @return the rentType3
	 */
	public String getRentType3() {
		return rentType3;
	}

	/**
	 * @param rentType3
	 *            the rentType3 to set
	 */
	public void setRentType3(String rentType3) {
		this.rentType3 = rentType3;
	}

	/**
	 * @return the fillerType1
	 */
	public String getFillerType1() {
		return fillerType1;
	}

	/**
	 * @param fillerType1
	 *            the fillerType1 to set
	 */
	public void setFillerType1(String fillerType1) {
		this.fillerType1 = fillerType1;
	}

	/**
	 * @return the fillerType2
	 */
	public String getFillerType2() {
		return fillerType2;
	}

	/**
	 * @param fillerType2
	 *            the fillerType2 to set
	 */
	public void setFillerType2(String fillerType2) {
		this.fillerType2 = fillerType2;
	}

	/**
	 * @return the fillerType3
	 */
	public String getFillerType3() {
		return fillerType3;
	}

	/**
	 * @param fillerType3
	 *            the fillerType3 to set
	 */
	public void setFillerType3(String fillerType3) {
		this.fillerType3 = fillerType3;
	}

	/**
	 * @return the contDateType1
	 */
	public String getContDateType1() {
		return contDateType1;
	}

	/**
	 * @param contDateType1
	 *            the contDateType1 to set
	 */
	public void setContDateType1(String contDateType1) {
		this.contDateType1 = contDateType1;
	}

	/**
	 * @return the contDateType2
	 */
	public String getContDateType2() {
		return contDateType2;
	}

	/**
	 * @param contDateType2
	 *            the contDateType2 to set
	 */
	public void setContDateType2(String contDateType2) {
		this.contDateType2 = contDateType2;
	}

	/**
	 * @return the contDateType3
	 */
	public String getContDateType3() {
		return contDateType3;
	}

	/**
	 * @param contDateType3
	 *            the contDateType3 to set
	 */
	public void setContDateType3(String contDateType3) {
		this.contDateType3 = contDateType3;
	}

	/**
	 * @return the rentFreePeriodType1
	 */
	public String getRentFreePeriodType1() {
		return rentFreePeriodType1;
	}

	/**
	 * @param rentFreePeriodType1
	 *            the rentFreePeriodType1 to set
	 */
	public void setRentFreePeriodType1(String rentFreePeriodType1) {
		this.rentFreePeriodType1 = rentFreePeriodType1;
	}

	/**
	 * @return the rentFreePeriodType2
	 */
	public String getRentFreePeriodType2() {
		return rentFreePeriodType2;
	}

	/**
	 * @param rentFreePeriodType2
	 *            the rentFreePeriodType2 to set
	 */
	public void setRentFreePeriodType2(String rentFreePeriodType2) {
		this.rentFreePeriodType2 = rentFreePeriodType2;
	}

	/**
	 * @return the rentFreePeriodType3
	 */
	public String getRentFreePeriodType3() {
		return rentFreePeriodType3;
	}

	/**
	 * @param rentFreePeriodType3
	 *            the rentFreePeriodType3 to set
	 */
	public void setRentFreePeriodType3(String rentFreePeriodType3) {
		this.rentFreePeriodType3 = rentFreePeriodType3;
	}

	/**
	 * @return the rentStandrdType1
	 */
	public String getRentStandrdType1() {
		return rentStandrdType1;
	}

	/**
	 * @param rentStandrdType1
	 *            the rentStandrdType1 to set
	 */
	public void setRentStandrdType1(String rentStandrdType1) {
		this.rentStandrdType1 = rentStandrdType1;
	}

	/**
	 * @return the rentStandrdType2
	 */
	public String getRentStandrdType2() {
		return rentStandrdType2;
	}

	/**
	 * @param rentStandrdType2
	 *            the rentStandrdType2 to set
	 */
	public void setRentStandrdType2(String rentStandrdType2) {
		this.rentStandrdType2 = rentStandrdType2;
	}

	/**
	 * @return the rentStandrdType3
	 */
	public String getRentStandrdType3() {
		return rentStandrdType3;
	}

	/**
	 * @param rentStandrdType3
	 *            the rentStandrdType3 to set
	 */
	public void setRentStandrdType3(String rentStandrdType3) {
		this.rentStandrdType3 = rentStandrdType3;
	}

	/**
	 * @return the rentIncomeType1
	 */
	public String getRentIncomeType1() {
		return rentIncomeType1;
	}

	/**
	 * @param rentIncomeType1
	 *            the rentIncomeType1 to set
	 */
	public void setRentIncomeType1(String rentIncomeType1) {
		this.rentIncomeType1 = rentIncomeType1;
	}

	/**
	 * @return the rentIncomeType2
	 */
	public String getRentIncomeType2() {
		return rentIncomeType2;
	}

	/**
	 * @param rentIncomeType2
	 *            the rentIncomeType2 to set
	 */
	public void setRentIncomeType2(String rentIncomeType2) {
		this.rentIncomeType2 = rentIncomeType2;
	}

	/**
	 * @return the rentIncomeType3
	 */
	public String getRentIncomeType3() {
		return rentIncomeType3;
	}

	/**
	 * @param rentIncomeType3
	 *            the rentIncomeType3 to set
	 */
	public void setRentIncomeType3(String rentIncomeType3) {
		this.rentIncomeType3 = rentIncomeType3;
	}

	/**
	 * @return the methodPaymentType1
	 */
	public String getMethodPaymentType1() {
		return methodPaymentType1;
	}

	/**
	 * @param methodPaymentType1
	 *            the methodPaymentType1 to set
	 */
	public void setMethodPaymentType1(String methodPaymentType1) {
		this.methodPaymentType1 = methodPaymentType1;
	}

	/**
	 * @return the methodPaymentType2
	 */
	public String getMethodPaymentType2() {
		return methodPaymentType2;
	}

	/**
	 * @param methodPaymentType2
	 *            the methodPaymentType2 to set
	 */
	public void setMethodPaymentType2(String methodPaymentType2) {
		this.methodPaymentType2 = methodPaymentType2;
	}

	/**
	 * @return the methodPaymentType3
	 */
	public String getMethodPaymentType3() {
		return methodPaymentType3;
	}

	/**
	 * @param methodPaymentType3
	 *            the methodPaymentType3 to set
	 */
	public void setMethodPaymentType3(String methodPaymentType3) {
		this.methodPaymentType3 = methodPaymentType3;
	}

	/**
	 * @return the operatingMarginType1
	 */
	public String getOperatingMarginType1() {
		return operatingMarginType1;
	}

	/**
	 * @param operatingMarginType1
	 *            the operatingMarginType1 to set
	 */
	public void setOperatingMarginType1(String operatingMarginType1) {
		this.operatingMarginType1 = operatingMarginType1;
	}

	/**
	 * @return the operatingMarginType2
	 */
	public String getOperatingMarginType2() {
		return operatingMarginType2;
	}

	/**
	 * @param operatingMarginType2
	 *            the operatingMarginType2 to set
	 */
	public void setOperatingMarginType2(String operatingMarginType2) {
		this.operatingMarginType2 = operatingMarginType2;
	}

	/**
	 * @return the operatingMarginType3
	 */
	public String getOperatingMarginType3() {
		return operatingMarginType3;
	}

	/**
	 * @param operatingMarginType3
	 *            the operatingMarginType3 to set
	 */
	public void setOperatingMarginType3(String operatingMarginType3) {
		this.operatingMarginType3 = operatingMarginType3;
	}

	/**
	 * @return the handOverDateType1
	 */
	public String getHandOverDateType1() {
		return handOverDateType1;
	}

	/**
	 * @param handOverDateType1
	 *            the handOverDateType1 to set
	 */
	public void setHandOverDateType1(String handOverDateType1) {
		this.handOverDateType1 = handOverDateType1;
	}

	/**
	 * @return the handOverDateType2
	 */
	public String getHandOverDateType2() {
		return handOverDateType2;
	}

	/**
	 * @param handOverDateType2
	 *            the handOverDateType2 to set
	 */
	public void setHandOverDateType2(String handOverDateType2) {
		this.handOverDateType2 = handOverDateType2;
	}

	/**
	 * @return the handOverDateType3
	 */
	public String getHandOverDateType3() {
		return handOverDateType3;
	}

	/**
	 * @param handOverDateType3
	 *            the handOverDateType3 to set
	 */
	public void setHandOverDateType3(String handOverDateType3) {
		this.handOverDateType3 = handOverDateType3;
	}

	/**
	 * @return the fillerPeriodType1
	 */
	public String getFillerPeriodType1() {
		return fillerPeriodType1;
	}

	/**
	 * @param fillerPeriodType1
	 *            the fillerPeriodType1 to set
	 */
	public void setFillerPeriodType1(String fillerPeriodType1) {
		this.fillerPeriodType1 = fillerPeriodType1;
	}

	/**
	 * @return the fillerPeriodType2
	 */
	public String getFillerPeriodType2() {
		return fillerPeriodType2;
	}

	/**
	 * @param fillerPeriodType2
	 *            the fillerPeriodType2 to set
	 */
	public void setFillerPeriodType2(String fillerPeriodType2) {
		this.fillerPeriodType2 = fillerPeriodType2;
	}

	/**
	 * @return the fillerPeriodType3
	 */
	public String getFillerPeriodType3() {
		return fillerPeriodType3;
	}

	/**
	 * @param fillerPeriodType3
	 *            the fillerPeriodType3 to set
	 */
	public void setFillerPeriodType3(String fillerPeriodType3) {
		this.fillerPeriodType3 = fillerPeriodType3;
	}

	/**
	 * @return the otherCondType2
	 */
	public String getOtherCondType() {
		return otherCondType;
	}

	/**
	 * @param otherCondType2
	 *            the otherCondType2 to set
	 */
	public void setOtherCondType(String otherCondType) {
		this.otherCondType = otherCondType;
	}



	private String fillerPeriodType2;
	private String fillerPeriodType3;
	
	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}
	
	@Override
	public String getResTitleName() {
		return customerName;
	}


	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "PlanMoreLocationSheet");
	}

}
