package com.hhz.ump.web.res.bean;

public class CostZcMonthPlanForeProp {
	// 项目
	private String projectCd;
	private String projectName;
	// 工程名称
	private String projectDesc;
	// 类别
	//private String typeCd1; //招标
	//private String typeCd2; //采购

	// 预计金额(*万元)
	private String totalPrice;
	
	// 进场时间
	private String enterDate;

	//预计上单时间
	private String preUploadDate;
	// *定标完成时间
	private String bidCompleteDate;
	//招标范围内容
	private String invPurDesc;

	// 备注
	private String remark;
	// *成本意见
	private String areaCostOptionDesc;

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getPreUploadDate() {
		return preUploadDate;
	}

	public void setPreUploadDate(String preUploadDate) {
		this.preUploadDate = preUploadDate;
	}

	public String getBidCompleteDate() {
		return bidCompleteDate;
	}

	public void setBidCompleteDate(String bidCompleteDate) {
		this.bidCompleteDate = bidCompleteDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvPurDesc() {
		return invPurDesc;
	}

	public void setInvPurDesc(String invPurDesc) {
		this.invPurDesc = invPurDesc;
	}

	public String getAreaCostOptionDesc() {
		return areaCostOptionDesc;
	}

	public void setAreaCostOptionDesc(String areaCostOptionDesc) {
		this.areaCostOptionDesc = areaCostOptionDesc;
	}
}
