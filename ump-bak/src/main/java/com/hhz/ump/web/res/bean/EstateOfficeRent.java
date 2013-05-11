package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * 地产公司办公室租凭审批表
 * @author Administrator
 *
 */
public class EstateOfficeRent extends BaseTemplate {

	private String approveCompany;//申请公司
	private String rentAddr;//租凭地点
	private String rentArea;//面积大小
	private String rentDate;//租凭期
	private String fundNo;//资金数额
	private String costDeclare;//费用说明
	public String getApproveCompany() {
		return approveCompany;
	}
	public void setApproveCompany(String approveCompany) {
		this.approveCompany = approveCompany;
	}
	public String getRentAddr() {
		return rentAddr;
	}
	public void setRentAddr(String rentAddr) {
		this.rentAddr = rentAddr;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getCostDeclare() {
		return costDeclare;
	}
	public void setCostDeclare(String costDeclare) {
		this.costDeclare = costDeclare;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return approveCompany;
	}
}
