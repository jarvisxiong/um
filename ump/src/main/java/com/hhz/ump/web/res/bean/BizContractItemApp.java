package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

/**
 * 合同审批表（其他情况）
 * 
 * @author baolm 2011-04-11
 */
public class BizContractItemApp extends BaseSupTemplate implements IStatusModifyListener{
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	

	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 甲方
	 */
	private String sideA;

	/**
	 * 乙方
	 */
	private String sideB;
	
	/**
	 * 丙方
	 */
	private String sideC;//丙方 -Add for part C by zhuxj on 2012.3.31

	private String signerA;// 甲方签约人

	private String signerB;// 乙方签约人
	
	private String signerC;// 丙方签约人-Add for part C by zhuxj on 2012.3.31

	private String isInvite;// 招标

	private String isDirect;// 直接委托

	private String isCompetitive;// 竞争性谈判

	private String inviteNo;// 标书编号

	private String directReason;// 理由

	private String conContent;// 备注

	private String conPrice;// 合同价款

	private String section;// 标段

	private String scope;// 范围/数量
	
	/**
	 * 招标范围
	 */
	private String bidRange;
	
	/**
	 * 施工工期
	 */
	private String constructionTimeLimit;
	
	/**
	 * 质量要求
	 */
	private String qualityRequirement;

	/**
	 * 预算金额
	 */
	private String budgetPrice;
	
	/**
	 * 付款方式
	 */
	private String payMode;

	private List<ContractItemOtherProperty> otherProperties = new ArrayList<ContractItemOtherProperty>();

	/**
	 * 标准合同附件
	 */
	private String criterionContractId;
	
	private String businessCompany; //商业公司发起
	private String businessGroup;	//商业集团发起

	public String getCriterionContractId() {
		return criterionContractId;
	}

	public void setCriterionContractId(String criterionContractId) {
		this.criterionContractId = criterionContractId;
	}

	public String getSideA() {
		return sideA;
	}

	public void setSideA(String sideA) {
		this.sideA = sideA;
	}

	public String getSideB() {
		return sideB;
	}

	public void setSideB(String sideB) {
		this.sideB = sideB;
	}
	

	public String getSideC() {
		return sideC;
	}

	public void setSideC(String sideC) {
		this.sideC = sideC;
	}


	public String getSignerA() {
		return signerA;
	}

	public void setSignerA(String signerA) {
		this.signerA = signerA;
	}

	public String getSignerB() {
		return signerB;
	}

	public void setSignerB(String signerB) {
		this.signerB = signerB;
	}
	public String getSignerC() {
		return signerC;
	}

	public void setSignerC(String signerC) {
		this.signerC = signerC;
	}
	public String getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}

	public String getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

	public String getInviteNo() {
		return inviteNo;
	}

	public void setInviteNo(String inviteNo) {
		this.inviteNo = inviteNo;
	}

	public String getDirectReason() {
		return directReason;
	}

	public void setDirectReason(String directReason) {
		this.directReason = directReason;
	}

	public String getConContent() {
		return conContent;
	}

	public void setConContent(String conContent) {
		this.conContent = conContent;
	}

	public String getConPrice() {
		return conPrice;
	}

	public void setConPrice(String conPrice) {
		this.conPrice = conPrice;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getIsCompetitive() {
		return isCompetitive;
	}

	public void setIsCompetitive(String isCompetitive) {
		this.isCompetitive = isCompetitive;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBidRange() {
		return bidRange;
	}

	public void setBidRange(String bidRange) {
		this.bidRange = bidRange;
	}

	public String getConstructionTimeLimit() {
		return constructionTimeLimit;
	}

	public void setConstructionTimeLimit(String constructionTimeLimit) {
		this.constructionTimeLimit = constructionTimeLimit;
	}

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public List<ContractItemOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<ContractItemOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		return projectName;
	}
	@Override
	public String getResTitleName() {
		return engineeringName;
	}

	/**
	 * @return the businessCompany
	 */
	public String getBusinessCompany() {
		return businessCompany;
	}

	/**
	 * @param businessCompany the businessCompany to set
	 */
	public void setBusinessCompany(String businessCompany) {
		this.businessCompany = businessCompany;
	}

	/**
	 * @return the businessGroup
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * @param businessGroup the businessGroup to set
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	@Override
	public  void doAutoImport(){
		
	};
	@Override
	public void doImport(){
		
	}
	@Override
	public boolean isAutoImport(){
		return true;
	}
	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "BizContractItemApprove");
	}
	
}
