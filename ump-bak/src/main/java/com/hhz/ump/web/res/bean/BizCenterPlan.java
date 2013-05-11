package com.hhz.ump.web.res.bean;
import com.hhz.ump.web.res.baseBean.BaseTemplate;

//各中心工作计划审批表
public class BizCenterPlan extends BaseTemplate {

	private String fileName;//文件名称
	private String fileTypeByYear;//年度工作计划
	private String fileTypeByMonth;//阅读工作计划
	private String contentDesc;//内容表述
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileTypeByYear() {
		return fileTypeByYear;
	}
	public void setFileTypeByYear(String fileTypeByYear) {
		this.fileTypeByYear = fileTypeByYear;
	}
	public String getFileTypeByMonth() {
		return fileTypeByMonth;
	}
	public void setFileTypeByMonth(String fileTypeByMonth) {
		this.fileTypeByMonth = fileTypeByMonth;
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
