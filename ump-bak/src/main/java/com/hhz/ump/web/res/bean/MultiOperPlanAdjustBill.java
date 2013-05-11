package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//多经调整审批表(eg:多经点位规划及调整审批表)	
public class MultiOperPlanAdjustBill extends BaseTemplate {

	// 文件标题 关于xx 多经点位规划及调整的审批
	// □绝密 □机密/□保密 □内部公开
	// □急
	// 多经类别 : 路演(  )  促销 （  ） 其他（ ） 
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

	private String urgencyCd1;
	private String urgencyCd2;
	
	private String multiTypeCd1;
	private String multiTypeCd2;
	private String multiTypeCd3;
	
	private String multiTypeCd1Desc;
	private String multiTypeCd2Desc;
	private String multiTypeCd3Desc;

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

	public String getMultiTypeCd1() {
		return multiTypeCd1;
	}

	public void setMultiTypeCd1(String multiTypeCd1) {
		this.multiTypeCd1 = multiTypeCd1;
	}

	public String getMultiTypeCd2() {
		return multiTypeCd2;
	}

	public void setMultiTypeCd2(String multiTypeCd2) {
		this.multiTypeCd2 = multiTypeCd2;
	}

	public String getMultiTypeCd3() {
		return multiTypeCd3;
	}

	public void setMultiTypeCd3(String multiTypeCd3) {
		this.multiTypeCd3 = multiTypeCd3;
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

	public String getMultiTypeCd3Desc() {
		return multiTypeCd3Desc;
	}

	public void setMultiTypeCd3Desc(String multiTypeCd3Desc) {
		this.multiTypeCd3Desc = multiTypeCd3Desc;
	}

	public String getMultiTypeCd1Desc() {
		return multiTypeCd1Desc;
	}

	public void setMultiTypeCd1Desc(String multiTypeCd1Desc) {
		this.multiTypeCd1Desc = multiTypeCd1Desc;
	}

	public String getMultiTypeCd2Desc() {
		return multiTypeCd2Desc;
	}

	public void setMultiTypeCd2Desc(String multiTypeCd2Desc) {
		this.multiTypeCd2Desc = multiTypeCd2Desc;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}
 
}
