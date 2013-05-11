package com.hhz.ump.web.res.bean;

//成本月招采计划(商业集团)
public class CostZcMonthPlanBisgroupProperty {

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

	// 预计金额(元)
	private String totalPrice;

	// *工期要求
	private String timeLimitDesc;
	// 进场时间
	private String enterDate;
	// 定标完成时间
	private String bidCompleteDate;
	
	//立项审批表或方案上报
	private String lxfaFlag1;//否
	private String lxfaFlag2;//是
	private String lxfaProvideDate;//提供时间
	//预算费用上报
	private String ysfyFlag1;//否
	private String ysfyFlag2;//是
	private String ysfyProvideDate;//提供时间
	//招标方案、图纸及资料上报
	private String zbfaFlag1;//否
	private String zbfaFlag2;//是
	private String zbfaProvideDate;//提供时间

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

	public String getLxfaFlag1() {
		return lxfaFlag1;
	}

	public void setLxfaFlag1(String lxfaFlag1) {
		this.lxfaFlag1 = lxfaFlag1;
	}

	public String getLxfaFlag2() {
		return lxfaFlag2;
	}

	public void setLxfaFlag2(String lxfaFlag2) {
		this.lxfaFlag2 = lxfaFlag2;
	}

	public String getLxfaProvideDate() {
		return lxfaProvideDate;
	}

	public void setLxfaProvideDate(String lxfaProvideDate) {
		this.lxfaProvideDate = lxfaProvideDate;
	}

	public String getYsfyFlag1() {
		return ysfyFlag1;
	}

	public void setYsfyFlag1(String ysfyFlag1) {
		this.ysfyFlag1 = ysfyFlag1;
	}

	public String getYsfyFlag2() {
		return ysfyFlag2;
	}

	public void setYsfyFlag2(String ysfyFlag2) {
		this.ysfyFlag2 = ysfyFlag2;
	}

	public String getYsfyProvideDate() {
		return ysfyProvideDate;
	}

	public void setYsfyProvideDate(String ysfyProvideDate) {
		this.ysfyProvideDate = ysfyProvideDate;
	}

	public String getZbfaFlag1() {
		return zbfaFlag1;
	}

	public void setZbfaFlag1(String zbfaFlag1) {
		this.zbfaFlag1 = zbfaFlag1;
	}

	public String getZbfaFlag2() {
		return zbfaFlag2;
	}

	public void setZbfaFlag2(String zbfaFlag2) {
		this.zbfaFlag2 = zbfaFlag2;
	}

	public String getZbfaProvideDate() {
		return zbfaProvideDate;
	}

	public void setZbfaProvideDate(String zbfaProvideDate) {
		this.zbfaProvideDate = zbfaProvideDate;
	}


}
