package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//商业公司考核审批表
public class BizExamineApprove extends BaseTemplate {

	private String fileName;//文件名称
	private String yearExam;//年度考核
	private String monthExam;//月度考核
	private String contentDesc;//内容表述
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getYearExam() {
		return yearExam;
	}
	public void setYearExam(String yearExam) {
		this.yearExam = yearExam;
	}
	public String getMonthExam() {
		return monthExam;
	}
	public void setMonthExam(String monthExam) {
		this.monthExam = monthExam;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fileName;
	}
}
