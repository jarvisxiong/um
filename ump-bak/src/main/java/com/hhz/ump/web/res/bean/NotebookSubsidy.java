package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * A类笔记本补贴申请审批表
 * @author Administrator
 *
 */
public class NotebookSubsidy extends BaseTemplate {
	
	private String userName;//申请人
	private String userCd;//申请人
	
	private String  centerOrCompanyName;
	private String  centerOrCompanyCd;
	
	private String position;//职位
	
	private String documentContent;//文件内容
	
	private String applyUser;//使用人
	
	private String notebookType;//A类笔记本型号
	
	private String subsidyMoney;//补贴金额
	
	private String subsidyStartDate;//补贴生效日期
	
	private String subsidyEndDate;//补贴终止日期
	
	private String remark;//备注
	
	

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return notebookType;
	}

	public String getCenterOrCompanyName() {
		return centerOrCompanyName;
	}

	public void setCenterOrCompanyName(String centerOrCompanyName) {
		this.centerOrCompanyName = centerOrCompanyName;
	}

	public String getCenterOrCompanyCd() {
		return centerOrCompanyCd;
	}

	public void setCenterOrCompanyCd(String centerOrCompanyCd) {
		this.centerOrCompanyCd = centerOrCompanyCd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getNotebookType() {
		return notebookType;
	}

	public void setNotebookType(String notebookType) {
		this.notebookType = notebookType;
	}

	public String getSubsidyMoney() {
		return subsidyMoney;
	}

	public void setSubsidyMoney(String subsidyMoney) {
		this.subsidyMoney = subsidyMoney;
	}

	public String getSubsidyStartDate() {
		return subsidyStartDate;
	}

	public void setSubsidyStartDate(String subsidyStartDate) {
		this.subsidyStartDate = subsidyStartDate;
	}

	public String getSubsidyEndDate() {
		return subsidyEndDate;
	}

	public void setSubsidyEndDate(String subsidyEndDate) {
		this.subsidyEndDate = subsidyEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

}
