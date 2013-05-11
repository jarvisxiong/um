package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//宝龙商业年度工作计划审批表
public class BizYearProjectPlan extends BaseTemplate {

	private String fileName;//文件名称
	private String contentName;//内容简述
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fileName;
	}
}
