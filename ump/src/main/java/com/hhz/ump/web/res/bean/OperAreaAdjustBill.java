package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//开业后业态面积调整审批表(eg:开业后业态面积调整审批表)	 
public class OperAreaAdjustBill extends BaseTemplate {

	
	private String areaRate1;//面积占比≤10%
	private String areaRate2;//10%<面积占比≤20%
	private String areaRate3;//面积占比>20%
	private String rentMoney1;//租金收益高于标准水平
	private String rentMoney2;//租金收益低于标准水平
	
	// 文件标题 关于xx 业态面积调整的审批 □绝密 □机密/□保密 □内部公开 □急
	// 店面类别 : □主力店（百货、超市）□次主力店 □品牌店 □其他
	// 发起中心/公司 经办人 校核人

	private String projectName;
	private String projectCd;

	private String securityCd1;
	private String securityCd2;
	private String securityCd3;
	private String securityCd4;

	private String storeTypeCd1;
	private String storeTypeCd2;
	private String storeTypeCd3;
	private String storeTypeCd4;
	private String storeTypeCd4Desc;

	private String urgencyCd1;
	private String urgencyCd2;

	private String centerName;
	private String centerCd;
	private String operatorName;
	private String operatorCd;
	private String checkerName;
	private String checkerCd;
	
	private String adjustDesc;

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

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public String getSecurityCd1() {
		return securityCd1;
	}

	public void setSecurityCd1(String securityCd1) {
		this.securityCd1 = securityCd1;
	}

	public String getSecurityCd2() {
		return securityCd2;
	}

	public void setSecurityCd2(String securityCd2) {
		this.securityCd2 = securityCd2;
	}

	public String getSecurityCd3() {
		return securityCd3;
	}

	public void setSecurityCd3(String securityCd3) {
		this.securityCd3 = securityCd3;
	}

	public String getSecurityCd4() {
		return securityCd4;
	}

	public void setSecurityCd4(String securityCd4) {
		this.securityCd4 = securityCd4;
	}

	public String getStoreTypeCd1() {
		return storeTypeCd1;
	}

	public void setStoreTypeCd1(String storeTypeCd1) {
		this.storeTypeCd1 = storeTypeCd1;
	}

	public String getStoreTypeCd2() {
		return storeTypeCd2;
	}

	public void setStoreTypeCd2(String storeTypeCd2) {
		this.storeTypeCd2 = storeTypeCd2;
	}

	public String getStoreTypeCd3() {
		return storeTypeCd3;
	}

	public void setStoreTypeCd3(String storeTypeCd3) {
		this.storeTypeCd3 = storeTypeCd3;
	}

	public String getStoreTypeCd4() {
		return storeTypeCd4;
	}

	public void setStoreTypeCd4(String storeTypeCd4) {
		this.storeTypeCd4 = storeTypeCd4;
	} 

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorCd() {
		return operatorCd;
	}

	public void setOperatorCd(String operatorCd) {
		this.operatorCd = operatorCd;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCheckerCd() {
		return checkerCd;
	}

	public void setCheckerCd(String checkerCd) {
		this.checkerCd = checkerCd;
	}

	public String getAdjustDesc() {
		return adjustDesc;
	}

	public void setAdjustDesc(String adjustDesc) {
		this.adjustDesc = adjustDesc;
	}

	public String getUrgencyCd1() {
		return urgencyCd1;
	}

	public void setUrgencyCd1(String urgencyCd1) {
		this.urgencyCd1 = urgencyCd1;
	}

	public String getUrgencyCd2() {
		return urgencyCd2;
	}

	public void setUrgencyCd2(String urgencyCd2) {
		this.urgencyCd2 = urgencyCd2;
	}

	public String getStoreTypeCd4Desc() {
		return storeTypeCd4Desc;
	}

	public void setStoreTypeCd4Desc(String storeTypeCd4Desc) {
		this.storeTypeCd4Desc = storeTypeCd4Desc;
	}

	public String getAreaRate1() {
		return areaRate1;
	}

	public void setAreaRate1(String areaRate1) {
		this.areaRate1 = areaRate1;
	}

	public String getAreaRate2() {
		return areaRate2;
	}

	public void setAreaRate2(String areaRate2) {
		this.areaRate2 = areaRate2;
	}

	public String getAreaRate3() {
		return areaRate3;
	}

	public void setAreaRate3(String areaRate3) {
		this.areaRate3 = areaRate3;
	}

	public String getRentMoney1() {
		return rentMoney1;
	}

	public void setRentMoney1(String rentMoney1) {
		this.rentMoney1 = rentMoney1;
	}

	public String getRentMoney2() {
		return rentMoney2;
	}

	public void setRentMoney2(String rentMoney2) {
		this.rentMoney2 = rentMoney2;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}
 
}
