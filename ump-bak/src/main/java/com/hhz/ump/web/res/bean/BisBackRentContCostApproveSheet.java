/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;



/**
 * <p>商业公司返租合同费用付款审批表</p>
 * 
 * @author  qlb
 * @version 1.00 2023-01-13
 */

public class BisBackRentContCostApproveSheet extends BaseTemplate {
	
	private String backRentNo;
	private String year;
	private String month;
	private String day;
	private String bisProjectId;
	private String bisProjectName;
	
	/**
	 * 商业公司
	 */
	private String centerOrgName;
	/**
	 *商业公司CD
	 */
	private String centerOrgCd;
	//项目名称
	private String projectName;
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	private List<BusinessBackRentContract> businessBackRentCons=new ArrayList<BusinessBackRentContract>();
	/**
	 * 支付总金额
	 */
	private String paymentTotalAmt;
	/**
	 * 内容描述
	 */
	private String remark;
	/**
	 * @return the bisProjectId
	 */
	public String getBisProjectId() {
		return bisProjectId;
	}
	/**
	 * @param bisProjectId the bisProjectId to set
	 */
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	/**
	 * @return the bisProjectName
	 */
	public String getBisProjectName() {
		return bisProjectName;
	}
	/**
	 * @param bisProjectName the bisProjectName to set
	 */
	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	/**
	 * @return the centerOrgName
	 */
	public String getCenterOrgName() {
		return centerOrgName;
	}
	/**
	 * @param centerOrgName the centerOrgName to set
	 */
	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}
	/**
	 * @return the centerOrgCd
	 */
	public String getCenterOrgCd() {
		return centerOrgCd;
	}
	/**
	 * @param centerOrgCd the centerOrgCd to set
	 */
	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}
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
	 * @return the businessBackRentCons
	 */
	public List<BusinessBackRentContract> getBusinessBackRentCons() {
		return businessBackRentCons;
	}
	/**
	 * @param businessBackRentCons the businessBackRentCons to set
	 */
	public void setBusinessBackRentCons(List<BusinessBackRentContract> businessBackRentCons) {
		this.businessBackRentCons = businessBackRentCons;
	}
	/**
	 * @return the paymentTotalAmt
	 */
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @return the backRentNo
	 */
	public String getBackRentNo() {
		return backRentNo;
	}
	/**
	 * @param backRentNo the backRentNo to set
	 */
	public void setBackRentNo(String backRentNo) {
		this.backRentNo = backRentNo;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the paymentTotalAmt
	 */
	public String getPaymentTotalAmt() {
		return paymentTotalAmt;
	}
	/**
	 * @param paymentTotalAmt the paymentTotalAmt to set
	 */
	public void setPaymentTotalAmt(String paymentTotalAmt) {
		this.paymentTotalAmt = paymentTotalAmt;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
