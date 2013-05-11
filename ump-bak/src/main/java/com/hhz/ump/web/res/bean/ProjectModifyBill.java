package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

public class ProjectModifyBill extends BaseTemplatePay {

	//商业工程改造审批表
	//项目名称
	private String itemName;
	//工程名称
	private String projectName;
	//预估工程费用
	private String preProjectFee;
	//工程改造内容及原因
	private String transformCause;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return itemName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPreProjectFee() {
		return preProjectFee;
	}

	public void setPreProjectFee(String preProjectFee) {
		this.preProjectFee = preProjectFee;
	}

	public String getTransformCause() {
		return transformCause;
	}

	public void setTransformCause(String transformCause) {
		this.transformCause = transformCause;
	}

}
