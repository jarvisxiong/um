package com.hhz.ump.web.res.bean;

//成本月招采计划(商业公司)
public class CostZcMonthPlanBisprojectProperty {

	// 项目
	private String projectCd;
	private String projectName;
	// 工程名称
	private String projectDesc;
	// 类别
	private String typeCd1; //工程
	private String typeCd2; //企划
	private String typeCd3; //营运
	private String typeCd4; //行政
	// 招标范围/采购内容
	private String invPurDesc;

	//计算指标(面积/数量)
	private String calcIndexSquare;
	//计算指标(*单价)
	private String calcIndexPrice;
	// 预计金额(元)
	private String totalPrice;

	// 工期要求
	private String timeLimitDesc;
	// 进场时间
	private String enterDate;
	// 定标完成时间
	private String bidCompleteDate;
	
	private String lxfaFileId;//立项审批表或方案审批表
	private String ysfyFileId;//预算费用审批表
	private String zbfaFileId;//招标方案、图纸及资料
	
	// 备注
	private String remark;

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

	public String getTypeCd1() {
		return typeCd1;
	}

	public void setTypeCd1(String typeCd1) {
		this.typeCd1 = typeCd1;
	}

	public String getTypeCd2() {
		return typeCd2;
	}

	public void setTypeCd2(String typeCd2) {
		this.typeCd2 = typeCd2;
	}

	public String getTypeCd3() {
		return typeCd3;
	}

	public void setTypeCd3(String typeCd3) {
		this.typeCd3 = typeCd3;
	}

	public String getTypeCd4() {
		return typeCd4;
	}

	public void setTypeCd4(String typeCd4) {
		this.typeCd4 = typeCd4;
	}

	public String getInvPurDesc() {
		return invPurDesc;
	}

	public void setInvPurDesc(String invPurDesc) {
		this.invPurDesc = invPurDesc;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTimeLimitDesc() {
		return timeLimitDesc;
	}

	public void setTimeLimitDesc(String timeLimitDesc) {
		this.timeLimitDesc = timeLimitDesc;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
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

	public String getLxfaFileId() {
		return lxfaFileId;
	}

	public void setLxfaFileId(String lxfaFileId) {
		this.lxfaFileId = lxfaFileId;
	}

	public String getYsfyFileId() {
		return ysfyFileId;
	}

	public void setYsfyFileId(String ysfyFileId) {
		this.ysfyFileId = ysfyFileId;
	}

	public String getZbfaFileId() {
		return zbfaFileId;
	}

	public void setZbfaFileId(String zbfaFileId) {
		this.zbfaFileId = zbfaFileId;
	}

	public String getCalcIndexSquare() {
		return calcIndexSquare;
	}

	public void setCalcIndexSquare(String calcIndexSquare) {
		this.calcIndexSquare = calcIndexSquare;
	}

	public String getCalcIndexPrice() {
		return calcIndexPrice;
	}

	public void setCalcIndexPrice(String calcIndexPrice) {
		this.calcIndexPrice = calcIndexPrice;
	}


}
