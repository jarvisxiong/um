package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//宝龙商业人事变动表
public class BizPersonnelChange extends BaseTemplate {

	private String name;//姓名
	private String centerName;//中心名称
	private String centerCd;//中心CD
	private String dept;//部门
	private String position;//职位
	private String reportDuty;//入职日期
	private String effectiveDate;//生效日期
	private String official;//正式工
	private String temporaryWorkers;//临时工
	private String trainee;//实习生
	private String externalAuditor;//外聘
	private String outAssign;//外派
	private String foreignNationality;//外籍
	private String newEmploy;//新聘
	private String passQualifyingPeriod;//通过试用期
	private String promotion;//升职
	private String demotion;//降职
	private String insideRemove;//内部调动
	private String salaryChange;//工资变动
	private String otherChange;//其他变动
	private String preCenter;//调整前中心/公司
	private String aftCenter;//调整后中心/公司
	private String preDept;//调整前部门
	private String aftDept;//调整后部门
	private String prePosition;//调整前职位
	private String aftPosition;//调整后职位
	private String preRank;//调整前职级
	private String aftRank;//调整后职级
	private String preSalary;//调整前工资
	private String aftSalary;//调整后工资
	private String preAssginBounty;//外派补贴
	private String aftAssginBounty;
	private String preHousingBounty;//住房补贴
	private String aftHousingBounty;
	private String preTrafficBounty;//交通补贴
	private String aftTrafficBounty;
	private String communication;//通讯补贴
	private String aftCommunication;
	private String preSpecialBounty;//特殊补贴
	private String aftSpecialBounty;
	private String preOtherBounty;//其它补贴
	private String aftOtherBounty;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getReportDuty() {
		return reportDuty;
	}
	public void setReportDuty(String reportDuty) {
		this.reportDuty = reportDuty;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getTemporaryWorkers() {
		return temporaryWorkers;
	}
	public void setTemporaryWorkers(String temporaryWorkers) {
		this.temporaryWorkers = temporaryWorkers;
	}
	public String getTrainee() {
		return trainee;
	}
	public void setTrainee(String trainee) {
		this.trainee = trainee;
	}
	public String getExternalAuditor() {
		return externalAuditor;
	}
	public void setExternalAuditor(String externalAuditor) {
		this.externalAuditor = externalAuditor;
	}
	public String getOutAssign() {
		return outAssign;
	}
	public void setOutAssign(String outAssign) {
		this.outAssign = outAssign;
	}
	public String getForeignNationality() {
		return foreignNationality;
	}
	public void setForeignNationality(String foreignNationality) {
		this.foreignNationality = foreignNationality;
	}
	public String getNewEmploy() {
		return newEmploy;
	}
	public void setNewEmploy(String newEmploy) {
		this.newEmploy = newEmploy;
	}
	public String getPassQualifyingPeriod() {
		return passQualifyingPeriod;
	}
	public void setPassQualifyingPeriod(String passQualifyingPeriod) {
		this.passQualifyingPeriod = passQualifyingPeriod;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getDemotion() {
		return demotion;
	}
	public void setDemotion(String demotion) {
		this.demotion = demotion;
	}
	public String getInsideRemove() {
		return insideRemove;
	}
	public void setInsideRemove(String insideRemove) {
		this.insideRemove = insideRemove;
	}
	public String getSalaryChange() {
		return salaryChange;
	}
	public void setSalaryChange(String salaryChange) {
		this.salaryChange = salaryChange;
	}
	public String getOtherChange() {
		return otherChange;
	}
	public void setOtherChange(String otherChange) {
		this.otherChange = otherChange;
	}
	public String getPreCenter() {
		return preCenter;
	}
	public void setPreCenter(String preCenter) {
		this.preCenter = preCenter;
	}
	public String getAftCenter() {
		return aftCenter;
	}
	public void setAftCenter(String aftCenter) {
		this.aftCenter = aftCenter;
	}
	public String getPreDept() {
		return preDept;
	}
	public void setPreDept(String preDept) {
		this.preDept = preDept;
	}
	public String getAftDept() {
		return aftDept;
	}
	public void setAftDept(String aftDept) {
		this.aftDept = aftDept;
	}
	public String getPrePosition() {
		return prePosition;
	}
	public void setPrePosition(String prePosition) {
		this.prePosition = prePosition;
	}
	public String getAftPosition() {
		return aftPosition;
	}
	public void setAftPosition(String aftPosition) {
		this.aftPosition = aftPosition;
	}
	public String getPreRank() {
		return preRank;
	}
	public void setPreRank(String preRank) {
		this.preRank = preRank;
	}
	public String getAftRank() {
		return aftRank;
	}
	public void setAftRank(String aftRank) {
		this.aftRank = aftRank;
	}
	public String getPreSalary() {
		return preSalary;
	}
	public void setPreSalary(String preSalary) {
		this.preSalary = preSalary;
	}
	public String getAftSalary() {
		return aftSalary;
	}
	public void setAftSalary(String aftSalary) {
		this.aftSalary = aftSalary;
	}
	public String getPreAssginBounty() {
		return preAssginBounty;
	}
	public void setPreAssginBounty(String preAssginBounty) {
		this.preAssginBounty = preAssginBounty;
	}
	public String getAftAssginBounty() {
		return aftAssginBounty;
	}
	public void setAftAssginBounty(String aftAssginBounty) {
		this.aftAssginBounty = aftAssginBounty;
	}
	public String getPreHousingBounty() {
		return preHousingBounty;
	}
	public void setPreHousingBounty(String preHousingBounty) {
		this.preHousingBounty = preHousingBounty;
	}
	public String getAftHousingBounty() {
		return aftHousingBounty;
	}
	public void setAftHousingBounty(String aftHousingBounty) {
		this.aftHousingBounty = aftHousingBounty;
	}
	public String getPreTrafficBounty() {
		return preTrafficBounty;
	}
	public void setPreTrafficBounty(String preTrafficBounty) {
		this.preTrafficBounty = preTrafficBounty;
	}
	public String getAftTrafficBounty() {
		return aftTrafficBounty;
	}
	public void setAftTrafficBounty(String aftTrafficBounty) {
		this.aftTrafficBounty = aftTrafficBounty;
	}
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	public String getAftCommunication() {
		return aftCommunication;
	}
	public void setAftCommunication(String aftCommunication) {
		this.aftCommunication = aftCommunication;
	}
	public String getPreSpecialBounty() {
		return preSpecialBounty;
	}
	public void setPreSpecialBounty(String preSpecialBounty) {
		this.preSpecialBounty = preSpecialBounty;
	}
	public String getAftSpecialBounty() {
		return aftSpecialBounty;
	}
	public void setAftSpecialBounty(String aftSpecialBounty) {
		this.aftSpecialBounty = aftSpecialBounty;
	}
	public String getPreOtherBounty() {
		return preOtherBounty;
	}
	public void setPreOtherBounty(String preOtherBounty) {
		this.preOtherBounty = preOtherBounty;
	}
	public String getAftOtherBounty() {
		return aftOtherBounty;
	}
	public void setAftOtherBounty(String aftOtherBounty) {
		this.aftOtherBounty = aftOtherBounty;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCd() {
		return centerCd;
	}
	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return name;
	}

}
