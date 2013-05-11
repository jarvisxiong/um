package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>项目开业/开街/交房时间审批表</p>
 * @author huangjian
 * @create 2012-6-3
 */
public class ProjectOpenBill extends BaseTemplate {

	private String titleName;//标题
	private String projectName;//项目名称
	private String openArea;//开业/开街区域
	private String roomArea;//交房区域
	private String oriDate;//原定时间
	private String tarDate;//调整时间
	private String remark;//相关说明（附件）
	


	@Override
	public String getResTitleName() {
		return titleName;
	}



	public String getTitleName() {
		return titleName;
	}



	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}


	public String getOpenArea() {
		return openArea;
	}



	public void setOpenArea(String openArea) {
		this.openArea = openArea;
	}



	public String getRoomArea() {
		return roomArea;
	}



	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}



	public String getOriDate() {
		return oriDate;
	}



	public void setOriDate(String oriDate) {
		this.oriDate = oriDate;
	}



	public String getTarDate() {
		return tarDate;
	}



	public void setTarDate(String tarDate) {
		this.tarDate = tarDate;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
