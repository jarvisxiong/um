package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>EAS用户权限变动申请表</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-23
 */

public class EasHrUserRightChangeSheet extends BaseTemplate {

	
	//申请人
	private String applyUserName;   
	
	//申请人帐号
	private String applyUserAccounts;
	
	//申请人日期
	private String applyUserDate;
	
	//所在单位or部门or职位
	private String deptName;
	
	//-------变更类型 start 
	private String ctAddRight;  		//增加权限
	private String ctDelRight;			//删除权限
	private String ctFinancePeople;		//财务人员
	//-------变更类型 end
	
	
	//-------变更原因 start
	private String personnelChange;		//人事异动
	private String frontPersonnelChange;//人事异动前公司or部门or职位
	private String afterPersonnelChange;//人事异动后公司or部门or职位
	
	private String addUnit;				//增加单位or部门
	private String addUnitValue;		//增加单位or部门值
	
	private String other;				//其它
	private String otherValue;			//其它值
	//-------变更原因 end
	
	
	//申请人岗位职责描述
	private String postResponsibilityDes;
	
	//组织范围
	private String groupScope;
	
	//------权限模块 start  多选
	private String abilityQualityModel;     //能力素质模型
	private String performanceManage;		//绩效管理
	private String groupPlanning;			//组织规划
	private String personnelManage;			//职员管理
	private String workManage;				//考勤管理
	private String jobSelect;				//招聘选拔
	private String trainDevelop;			//培训发展
	private String salaryDesign;			//薪水设计
	private String salaryCheck;				//薪酬核算
	private String socialSecurity;			//社保福利
	private String seachReport;				//搜索报表
	private String mobileHRGuide;			//移动HR向导
	private String myWorkbench;				//我的工作台
	//------权限模块 end
	
	//工资项目权限
	private String salaryProjectRight;
	
	//申请权限内容描述
	private String applyRightContentDes;
	
	



	/**
	 * @return the applyUserName
	 */
	public String getApplyUserName() {
		return applyUserName;
	}

	/**
	 * @param applyUserName the applyUserName to set
	 */
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	/**
	 * @return the applyUserAccounts
	 */
	public String getApplyUserAccounts() {
		return applyUserAccounts;
	}

	/**
	 * @param applyUserAccounts the applyUserAccounts to set
	 */
	public void setApplyUserAccounts(String applyUserAccounts) {
		this.applyUserAccounts = applyUserAccounts;
	}

	/**
	 * @return the applyUserDate
	 */
	public String getApplyUserDate() {
		return applyUserDate;
	}

	/**
	 * @param applyUserDate the applyUserDate to set
	 */
	public void setApplyUserDate(String applyUserDate) {
		this.applyUserDate = applyUserDate;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the ctAddRight
	 */
	public String getCtAddRight() {
		return ctAddRight;
	}

	/**
	 * @param ctAddRight the ctAddRight to set
	 */
	public void setCtAddRight(String ctAddRight) {
		this.ctAddRight = ctAddRight;
	}

	/**
	 * @return the ctDelRight
	 */
	public String getCtDelRight() {
		return ctDelRight;
	}

	/**
	 * @param ctDelRight the ctDelRight to set
	 */
	public void setCtDelRight(String ctDelRight) {
		this.ctDelRight = ctDelRight;
	}

	/**
	 * @return the ctFinancePeople
	 */
	public String getCtFinancePeople() {
		return ctFinancePeople;
	}

	/**
	 * @param ctFinancePeople the ctFinancePeople to set
	 */
	public void setCtFinancePeople(String ctFinancePeople) {
		this.ctFinancePeople = ctFinancePeople;
	}

	/**
	 * @return the personnelChange
	 */
	public String getPersonnelChange() {
		return personnelChange;
	}

	/**
	 * @param personnelChange the personnelChange to set
	 */
	public void setPersonnelChange(String personnelChange) {
		this.personnelChange = personnelChange;
	}

	/**
	 * @return the frontPersonnelChange
	 */
	public String getFrontPersonnelChange() {
		return frontPersonnelChange;
	}

	/**
	 * @param frontPersonnelChange the frontPersonnelChange to set
	 */
	public void setFrontPersonnelChange(String frontPersonnelChange) {
		this.frontPersonnelChange = frontPersonnelChange;
	}

	/**
	 * @return the afterPersonnelChange
	 */
	public String getAfterPersonnelChange() {
		return afterPersonnelChange;
	}

	/**
	 * @param afterPersonnelChange the afterPersonnelChange to set
	 */
	public void setAfterPersonnelChange(String afterPersonnelChange) {
		this.afterPersonnelChange = afterPersonnelChange;
	}

	/**
	 * @return the addUnit
	 */
	public String getAddUnit() {
		return addUnit;
	}

	/**
	 * @param addUnit the addUnit to set
	 */
	public void setAddUnit(String addUnit) {
		this.addUnit = addUnit;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * @return the postResponsibilityDes
	 */
	public String getPostResponsibilityDes() {
		return postResponsibilityDes;
	}

	/**
	 * @param postResponsibilityDes the postResponsibilityDes to set
	 */
	public void setPostResponsibilityDes(String postResponsibilityDes) {
		this.postResponsibilityDes = postResponsibilityDes;
	}

	/**
	 * @return the groupScope
	 */
	public String getGroupScope() {
		return groupScope;
	}

	/**
	 * @param groupScope the groupScope to set
	 */
	public void setGroupScope(String groupScope) {
		this.groupScope = groupScope;
	}

	/**
	 * @return the abilityQualityModel
	 */
	public String getAbilityQualityModel() {
		return abilityQualityModel;
	}

	/**
	 * @param abilityQualityModel the abilityQualityModel to set
	 */
	public void setAbilityQualityModel(String abilityQualityModel) {
		this.abilityQualityModel = abilityQualityModel;
	}

	/**
	 * @return the performanceManage
	 */
	public String getPerformanceManage() {
		return performanceManage;
	}

	/**
	 * @param performanceManage the performanceManage to set
	 */
	public void setPerformanceManage(String performanceManage) {
		this.performanceManage = performanceManage;
	}

	/**
	 * @return the groupPlanning
	 */
	public String getGroupPlanning() {
		return groupPlanning;
	}

	/**
	 * @param groupPlanning the groupPlanning to set
	 */
	public void setGroupPlanning(String groupPlanning) {
		this.groupPlanning = groupPlanning;
	}

	/**
	 * @return the personnelManage
	 */
	public String getPersonnelManage() {
		return personnelManage;
	}

	/**
	 * @param personnelManage the personnelManage to set
	 */
	public void setPersonnelManage(String personnelManage) {
		this.personnelManage = personnelManage;
	}

	/**
	 * @return the workManage
	 */
	public String getWorkManage() {
		return workManage;
	}

	/**
	 * @param workManage the workManage to set
	 */
	public void setWorkManage(String workManage) {
		this.workManage = workManage;
	}

	/**
	 * @return the jobSelect
	 */
	public String getJobSelect() {
		return jobSelect;
	}

	/**
	 * @param jobSelect the jobSelect to set
	 */
	public void setJobSelect(String jobSelect) {
		this.jobSelect = jobSelect;
	}

	/**
	 * @return the trainDevelop
	 */
	public String getTrainDevelop() {
		return trainDevelop;
	}

	/**
	 * @param trainDevelop the trainDevelop to set
	 */
	public void setTrainDevelop(String trainDevelop) {
		this.trainDevelop = trainDevelop;
	}

	/**
	 * @return the salaryDesign
	 */
	public String getSalaryDesign() {
		return salaryDesign;
	}

	/**
	 * @param salaryDesign the salaryDesign to set
	 */
	public void setSalaryDesign(String salaryDesign) {
		this.salaryDesign = salaryDesign;
	}

	/**
	 * @return the salaryCheck
	 */
	public String getSalaryCheck() {
		return salaryCheck;
	}

	/**
	 * @param salaryCheck the salaryCheck to set
	 */
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
	}

	/**
	 * @return the socialSecurity
	 */
	public String getSocialSecurity() {
		return socialSecurity;
	}

	/**
	 * @param socialSecurity the socialSecurity to set
	 */
	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	/**
	 * @return the seachReport
	 */
	public String getSeachReport() {
		return seachReport;
	}

	/**
	 * @param seachReport the seachReport to set
	 */
	public void setSeachReport(String seachReport) {
		this.seachReport = seachReport;
	}

	/**
	 * @return the mobileHRGuide
	 */
	public String getMobileHRGuide() {
		return mobileHRGuide;
	}

	/**
	 * @param mobileHRGuide the mobileHRGuide to set
	 */
	public void setMobileHRGuide(String mobileHRGuide) {
		this.mobileHRGuide = mobileHRGuide;
	}

	/**
	 * @return the myWorkbench
	 */
	public String getMyWorkbench() {
		return myWorkbench;
	}

	/**
	 * @param myWorkbench the myWorkbench to set
	 */
	public void setMyWorkbench(String myWorkbench) {
		this.myWorkbench = myWorkbench;
	}

	/**
	 * @return the salaryProjectRight
	 */
	public String getSalaryProjectRight() {
		return salaryProjectRight;
	}

	/**
	 * @param salaryProjectRight the salaryProjectRight to set
	 */
	public void setSalaryProjectRight(String salaryProjectRight) {
		this.salaryProjectRight = salaryProjectRight;
	}

	/**
	 * @return the applyRightContentDes
	 */
	public String getApplyRightContentDes() {
		return applyRightContentDes;
	}

	/**
	 * @param applyRightContentDes the applyRightContentDes to set
	 */
	public void setApplyRightContentDes(String applyRightContentDes) {
		this.applyRightContentDes = applyRightContentDes;
	}

	/**
	 * @return the addUnitValue
	 */
	public String getAddUnitValue() {
		return addUnitValue;
	}

	/**
	 * @param addUnitValue the addUnitValue to set
	 */
	public void setAddUnitValue(String addUnitValue) {
		this.addUnitValue = addUnitValue;
	}

	/**
	 * @return the otherValue
	 */
	public String getOtherValue() {
		return otherValue;
	}

	/**
	 * @param otherValue the otherValue to set
	 */
	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applyUserName;
	}

	
	
	
}
