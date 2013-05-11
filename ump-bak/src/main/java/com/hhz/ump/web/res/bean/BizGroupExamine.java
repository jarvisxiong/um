package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//宝龙商业总部各中心考核审批表
public class BizGroupExamine extends BaseTemplate {

	private String fileName;//文件名称
	private String quarterExam;//季度考核
	private String yearExam;//年度考核
	private String contentDesc;//内容表述
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getQuarterExam() {
		return quarterExam;
	}
	public void setQuarterExam(String quarterExam) {
		this.quarterExam = quarterExam;
	}
	public String getYearExam() {
		return yearExam;
	}
	public void setYearExam(String yearExam) {
		this.yearExam = yearExam;
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
