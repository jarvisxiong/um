package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//宝龙商业总部组织管理文件审批表
public class BizGroupFile extends BaseTemplate {

	private String fileName;//文件名称
	private String groupArchitecture;//组织架构
	private String stationStandard;//岗位配置及编制标准
	private String contentDesc;//内容描述
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getGroupArchitecture() {
		return groupArchitecture;
	}
	public void setGroupArchitecture(String groupArchitecture) {
		this.groupArchitecture = groupArchitecture;
	}
	public String getStationStandard() {
		return stationStandard;
	}
	public void setStationStandard(String stationStandard) {
		this.stationStandard = stationStandard;
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
