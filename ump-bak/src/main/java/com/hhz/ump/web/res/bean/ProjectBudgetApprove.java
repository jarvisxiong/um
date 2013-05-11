package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 工程预算审批表
 * @author baolm
 *
 * 2011-04-22
 */
public class ProjectBudgetApprove extends BaseTemplate {
	
	//是否地产公司
	private String isLandCompany;
	
	/**
	 * @return the isLandCompany
	 */
	public String getIsLandCompany() {
		return isLandCompany;
	}

	/**
	 * @param isLandCompany the isLandCompany to set
	 */
	public void setIsLandCompany(String isLandCompany) {
		this.isLandCompany = isLandCompany;
	}

	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 合同( )期
	 */
	private String projectPeriod;
	
	/**
	 * □金额＞500万元
	 */
	private String above500;
	
	/**
	 * □100＜金额≤500万元
	 */
	private String from100to500;
	
	/**
	 * □30＜金额≤100万元
	 */
	private String from30to100;
	
	/**
	 * □小于30万元
	 */
	private String below30;
	
	/**
	 * 编号
	 */
	private String serialNo;
	
	/**
	 * 拟招标工程名称
	 */
	private String planBidProjectName;
	
	/**
	 * 工程范围/内容
	 */
	private String projectContent;
	
	/**
	 * 计划开工时间
	 */
	private String planBeginDate;
	
	/**
	 * 计划竣工时间
	 */
	private String planEndDate;
	
	/**
	 * 工期
	 */
	private String planDays;
	
	/**
	 * □邀请招标
	 */
	private String planBidMode1;
	
	/**
	 * □竞争性议标
	 */
	private String planBidMode2;
	
	/**
	 * □直接委托议价
	 */
	private String planBidMode3;
	
	/**
	 * □定向续标
	 */
	private String planBidMode4;
	
	/**
	 * 主合同名称
	 */
	private String contractName;
	
	/**
	 * 合同编号
	 */
	private String contractCd;
	
	/**
	 * □标底限价
	 */
	private String approveUsage1;
	
	/**
	 * □谈判指导价
	 */
	private String approveUsage2;
	
	/**
	 * □立项预算
	 */
	private String approveUsage3;
	
	/**
	 * □其他
	 */
	private String approveUsage4;
	
	/**
	 * □施工图(经批准的)
	 */
	private String budgetAccordingTo1;
	
	/**
	 * □方案(经批准的)
	 */
	private String budgetAccordingTo2;
	
	/**
	 * 地产公司申报预算金额(元)
	 */
	private String declareBudgetAmount;

	/**
	 * 指标
	 */
	private String indicate;
	
	/**
	 * 预算编制说明
	 */
	private String budgetDesc;
	
	/**
	 * 立项审批或图纸审批文件
	 */
	private String approveFileId;
	
	/**
	 * 图纸
	 */
	private String pictureId;
	
	public String getAbove500() {
		return above500;
	}

	public void setAbove500(String above500) {
		this.above500 = above500;
	}

	public String getFrom100to500() {
		return from100to500;
	}

	public void setFrom100to500(String from100to500) {
		this.from100to500 = from100to500;
	}

	public String getFrom30to100() {
		return from30to100;
	}

	public void setFrom30to100(String from30to100) {
		this.from30to100 = from30to100;
	}

	public String getBelow30() {
		return below30;
	}

	public void setBelow30(String below30) {
		this.below30 = below30;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPlanBidProjectName() {
		return planBidProjectName;
	}

	public void setPlanBidProjectName(String planBidProjectName) {
		this.planBidProjectName = planBidProjectName;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(String planBeginDate) {
		this.planBeginDate = planBeginDate;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getPlanDays() {
		return planDays;
	}

	public void setPlanDays(String planDays) {
		this.planDays = planDays;
	}

	public String getPlanBidMode1() {
		return planBidMode1;
	}

	public void setPlanBidMode1(String planBidMode1) {
		this.planBidMode1 = planBidMode1;
	}

	public String getPlanBidMode2() {
		return planBidMode2;
	}

	public void setPlanBidMode2(String planBidMode2) {
		this.planBidMode2 = planBidMode2;
	}

	public String getPlanBidMode3() {
		return planBidMode3;
	}

	public void setPlanBidMode3(String planBidMode3) {
		this.planBidMode3 = planBidMode3;
	}

	public String getPlanBidMode4() {
		return planBidMode4;
	}

	public void setPlanBidMode4(String planBidMode4) {
		this.planBidMode4 = planBidMode4;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractCd() {
		return contractCd;
	}

	public void setContractCd(String contractCd) {
		this.contractCd = contractCd;
	}

	public String getApproveUsage1() {
		return approveUsage1;
	}

	public void setApproveUsage1(String approveUsage1) {
		this.approveUsage1 = approveUsage1;
	}

	public String getApproveUsage2() {
		return approveUsage2;
	}

	public void setApproveUsage2(String approveUsage2) {
		this.approveUsage2 = approveUsage2;
	}

	public String getApproveUsage3() {
		return approveUsage3;
	}

	public void setApproveUsage3(String approveUsage3) {
		this.approveUsage3 = approveUsage3;
	}

	public String getApproveUsage4() {
		return approveUsage4;
	}

	public void setApproveUsage4(String approveUsage4) {
		this.approveUsage4 = approveUsage4;
	}

	public String getBudgetAccordingTo1() {
		return budgetAccordingTo1;
	}

	public void setBudgetAccordingTo1(String budgetAccordingTo1) {
		this.budgetAccordingTo1 = budgetAccordingTo1;
	}

	public String getBudgetAccordingTo2() {
		return budgetAccordingTo2;
	}

	public void setBudgetAccordingTo2(String budgetAccordingTo2) {
		this.budgetAccordingTo2 = budgetAccordingTo2;
	}

	public String getDeclareBudgetAmount() {
		return declareBudgetAmount;
	}

	public void setDeclareBudgetAmount(String declareBudgetAmount) {
		this.declareBudgetAmount = declareBudgetAmount;
	}

	public String getIndicate() {
		return indicate;
	}

	public void setIndicate(String indicate) {
		this.indicate = indicate;
	}

	public String getBudgetDesc() {
		return budgetDesc;
	}

	public void setBudgetDesc(String budgetDesc) {
		this.budgetDesc = budgetDesc;
	}

	public String getApproveFileId() {
		return approveFileId;
	}

	public void setApproveFileId(String approveFileId) {
		this.approveFileId = approveFileId;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		return planBidProjectName;
	}

}
