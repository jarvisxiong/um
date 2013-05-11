package com.hhz.ump.web.res.bean;

//成本月招采计划(集团)
public class CostZcMonthPlanGroupProperty {

	// 序号
	private String seqNo;
	// 项目
	private String projectCd;
	private String projectName;
	// 工程名称
	private String projectDesc;
	// 类别
	private String typeCd1; //招标
	private String typeCd2; //采购
	// 招标范围/采购内容
	private String invPurDesc;
	// 质量标准/技术要求
	private String qualityTechDesc;
	// 合同性质
	private String contactCharacterCd1;
	private String contactCharacterCd2;
	private String contactCharacterCd3;

	// 预计金额(*万元)
	private String totalPrice;

	// *工期要求
	private String timeLimitDesc;
	// 进场时间
	private String enterDate;

	// 技术中心(*出图时间)
	private String pictureDate;
	// 技术中心(*负责人)
	private String responserCd;
	private String responserName;

	// *定标完成时间
	private String bidCompleteDate;

	// 备注
	private String remark;
	// *区域成本意见
	private String areaCostOptionDesc;
	//垄断--是
	private String isForestall;
	
	private String notForestall;
	
	/**
	 * 定样完成时间、方案审批时间 -Add by liuzhihui 2012-04-12
	 */
	//定样完成时间
	private String sampleCompleteDate;
	//方案审批时间
	private String programApprovalDate;

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

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

	public String getInvPurDesc() {
		return invPurDesc;
	}

	public void setInvPurDesc(String invPurDesc) {
		this.invPurDesc = invPurDesc;
	}

	public String getQualityTechDesc() {
		return qualityTechDesc;
	}

	public void setQualityTechDesc(String qualityTechDesc) {
		this.qualityTechDesc = qualityTechDesc;
	}

	public String getContactCharacterCd1() {
		return contactCharacterCd1;
	}

	public void setContactCharacterCd1(String contactCharacterCd1) {
		this.contactCharacterCd1 = contactCharacterCd1;
	}

	public String getContactCharacterCd2() {
		return contactCharacterCd2;
	}

	public void setContactCharacterCd2(String contactCharacterCd2) {
		this.contactCharacterCd2 = contactCharacterCd2;
	}

	public String getContactCharacterCd3() {
		return contactCharacterCd3;
	}

	public void setContactCharacterCd3(String contactCharacterCd3) {
		this.contactCharacterCd3 = contactCharacterCd3;
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

	public String getPictureDate() {
		return pictureDate;
	}

	public void setPictureDate(String pictureDate) {
		this.pictureDate = pictureDate;
	}

	public String getResponserCd() {
		return responserCd;
	}

	public void setResponserCd(String responserCd) {
		this.responserCd = responserCd;
	}

	public String getResponserName() {
		return responserName;
	}

	public void setResponserName(String responserName) {
		this.responserName = responserName;
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

	public String getAreaCostOptionDesc() {
		return areaCostOptionDesc;
	}

	public void setAreaCostOptionDesc(String areaCostOptionDesc) {
		this.areaCostOptionDesc = areaCostOptionDesc;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getIsForestall() {
		return isForestall;
	}

	public void setIsForestall(String isForestall) {
		this.isForestall = isForestall;
	}

	public String getNotForestall() {
		return notForestall;
	}

	public void setNotForestall(String notForestall) {
		this.notForestall = notForestall;
	}

	public String getSampleCompleteDate() {
		return sampleCompleteDate;
	}

	public void setSampleCompleteDate(String sampleCompleteDate) {
		this.sampleCompleteDate = sampleCompleteDate;
	}

	public String getProgramApprovalDate() {
		return programApprovalDate;
	}

	public void setProgramApprovalDate(String programApprovalDate) {
		this.programApprovalDate = programApprovalDate;
	}

}
