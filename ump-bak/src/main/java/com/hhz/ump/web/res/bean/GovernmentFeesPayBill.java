package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 
 * 类名 GovPaymentBill 创建者 李劲 创建日期 2010-7-7 描述 政府规费付款审批表
 */
public class GovernmentFeesPayBill extends BaseTemplatePay {
	// 项目名称 project_Name
	// 规费内容 feeContent
	// 收费部门/收款单位 Change_Org_Name
	// 办理何种工程手续 projectProcedure
	// 收费文件号 Charge_doc_no
	// 收费期限 Charge_Deadline_Desc
	// 是否有政策性减免 hasPolicyDerate
	// 需说明事项 contentDesc

	String projectName;
	String projectCd;
	String feeContent;
	String acceptOrgName;
	String projectProcedure;
	String chargeDocNo;
	String chargeDeadlineDesc;
	String hasPolicyDerate;
	String contentDesc;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getAcceptOrgName() {
		return acceptOrgName;
	}

	public void setAcceptOrgName(String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}

	public String getChargeDocNo() {
		return chargeDocNo;
	}

	public void setChargeDocNo(String chargeDocNo) {
		this.chargeDocNo = chargeDocNo;
	}

	public String getChargeDeadlineDesc() {
		return chargeDeadlineDesc;
	}

	public void setChargeDeadlineDesc(String chargeDeadlineDesc) {
		this.chargeDeadlineDesc = chargeDeadlineDesc;
	}

	public String getHasPolicyDerate() {
		return hasPolicyDerate;
	}

	public void setHasPolicyDerate(String hasPolicyDerate) {
		this.hasPolicyDerate = hasPolicyDerate;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getFeeContent() {
		return feeContent;
	}

	public void setFeeContent(String feeContent) {
		this.feeContent = feeContent;
	}

	public String getProjectProcedure() {
		return projectProcedure;
	}

	public void setProjectProcedure(String projectProcedure) {
		this.projectProcedure = projectProcedure;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return getCompanyName();
	}
}
