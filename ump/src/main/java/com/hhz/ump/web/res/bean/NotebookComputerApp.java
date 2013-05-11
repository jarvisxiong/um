package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

//笔记本购置申请单
public class NotebookComputerApp extends BaseTemplatePay {

	//笔记本购置申请单
	//申请人
	private String applicant;
	//所属中心
	private String appCenterName;
	private String appCenterCd;
	//职位
	private String position;
	//申购理由
    private String appReason;
    
    private List<NotebookComputer> notebookComputer = new ArrayList<NotebookComputer>();  
    //确认机型及供应商
    private String checkSupplier;    
    //使用人
    private String userName;
    //笔记本资产编码
    private String computerId;
    //笔记本价格
    private String computerPrice;
    //每月扣款金额
    private String payForMonth;
    //抵款起始日期
    private String payFromDate;
    //抵款终止日期
    private String payToDate;
    //笔记本返款日期
    private String inFromDate;
    //备注
    private String deptRemark;
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
		return computerId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
 

	public String getAppCenterName() {
		return appCenterName;
	}

	public void setAppCenterName(String appCenterName) {
		this.appCenterName = appCenterName;
	}

	public String getAppCenterCd() {
		return appCenterCd;
	}

	public void setAppCenterCd(String appCenterCd) {
		this.appCenterCd = appCenterCd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAppReason() {
		return appReason;
	}

	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}

	public String getCheckSupplier() {
		return checkSupplier;
	}

	public void setCheckSupplier(String checkSupplier) {
		this.checkSupplier = checkSupplier;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComputerId() {
		return computerId;
	}

	public void setComputerId(String computerId) {
		this.computerId = computerId;
	}

	public String getComputerPrice() {
		return computerPrice;
	}

	public void setComputerPrice(String computerPrice) {
		this.computerPrice = computerPrice;
	}

	public String getPayForMonth() {
		return payForMonth;
	}

	public void setPayForMonth(String payForMonth) {
		this.payForMonth = payForMonth;
	}

	public String getPayFromDate() {
		return payFromDate;
	}

	public void setPayFromDate(String payFromDate) {
		this.payFromDate = payFromDate;
	}

	public String getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(String payToDate) {
		this.payToDate = payToDate;
	}

	public String getInFromDate() {
		return inFromDate;
	}

	public void setInFromDate(String inFromDate) {
		this.inFromDate = inFromDate;
	}

	public String getDeptRemark() {
		return deptRemark;
	}

	public void setDeptRemark(String deptRemark) {
		this.deptRemark = deptRemark;
	}

	public List<NotebookComputer> getNotebookComputer() {
		return notebookComputer;
	}

	public void setNotebookComputer(List<NotebookComputer> notebookComputer) {
		this.notebookComputer = notebookComputer;
	}

}
