package com.hhz.ump.web.res.bean;

 
//成本月招采计划(项目)
public class CostZcMonthPlanProjectProperty {
	
	//序号
	private String seqNo;
	//项目
	private String projectCd;
	private String projectName;
	//工程名称
	private String projectDesc;
	//类别
	private String typeCd1;
	private String typeCd2;
	//招标范围/采购内容
	private String invPurDesc;
	//计算指标(面积/数量)
	private String calcIndexSquare;
	//计算指标(*单价)
	private String calcIndexPrice;
	//预计金额(*万元)
	private String totalPrice;
	//进场时间
	private String enterDate;
	//方案上报(是/否)
	private String isUp1;
	private String isUp2;
	//方案上报(若否,则提供时间)
	private String provideDate;
	//备注
	private String remark;
	//*成本意见
	private String areaCostOptionDesc;
	
	/**
	 * add by liuzhihui 2012-04-11
	 */
	//出图时间
	private String plotDate;
	//定标时间
	private String calibrationDate;
	//定标完成时间
	private String calibrationCompleteDate;
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
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getProvideDate() {
		return provideDate;
	}
	public void setProvideDate(String provideDate) {
		this.provideDate = provideDate;
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
	public String getIsUp1() {
		return isUp1;
	}
	public void setIsUp1(String isUp1) {
		this.isUp1 = isUp1;
	}
	public String getIsUp2() {
		return isUp2;
	}
	public void setIsUp2(String isUp2) {
		this.isUp2 = isUp2;
	}
	public String getPlotDate() {
		return plotDate;
	}
	public void setPlotDate(String plotDate) {
		this.plotDate = plotDate;
	}
	public String getCalibrationDate() {
		return calibrationDate;
	}
	public void setCalibrationDate(String calibrationDate) {
		this.calibrationDate = calibrationDate;
	}
	
	public String getCalibrationCompleteDate() {
		return calibrationCompleteDate;
	}
	public void setCalibrationCompleteDate(String calibrationCompleteDate) {
		this.calibrationCompleteDate = calibrationCompleteDate;
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
