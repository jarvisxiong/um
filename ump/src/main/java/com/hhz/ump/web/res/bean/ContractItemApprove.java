package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sc.ScContractInfoFlowManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.sc.ScContractInfoFlow;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;
import com.hhz.ump.web.res.baseBean.IBeforeProcess;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;
import com.hhz.ump.web.sc.ScContractTempletInfoAction;

/**
 * 合同审批表（其他情况）
 * 
 * @author baolm 2011-04-11
 */
public class ContractItemApprove extends BaseSupTemplate implements IBeforeProcess,IStatusModifyListener {
	
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
	private String sideC;//甲方-Add for part C by zhuxj on 2012.3.31

	private String signerA;// 甲方签约人

	private String signerB;// 乙方签约人
	
	private String signerC;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
 
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
	
	//标准合同
	private String standard;
	
	//非标准合同
	private String nonstandard;
	
	private String contractTempletInfoId;
	//合同历史版id
	private String contractTempletHisId;
	
	//使用合同库
	private String contlib;
	//不使用合同库
	private String noncontlib;
	//合同编号
	private String contractNo;
	//合同名称
	private String contractName;
	
	/**
	 * @return the contractTempletHisId
	 */
	@Override
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @param contractTempletHisId the contractTempletHisId to set
	 */
	@Override
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	

	/**
	 * @return the contractNo
	 */
	@Override
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @return the contractName
	 */
	@Override
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	@Override
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @param contractName the contractName to set
	 */
	@Override
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the contractTempletInfoId
	 */
	@Override
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @param contractTempletInfoId the contractTempletInfoId to set
	 */
	@Override
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @return the nonstandard
	 */
	@Override
	public String getNonstandard() {
		return nonstandard;
	}

	/**
	 * @param nonstandard the nonstandard to set
	 */
	@Override
	public void setNonstandard(String nonstandard) {
		this.nonstandard = nonstandard;
	}

	/**
	 * 标准合同附件
	 */
	private String criterionContractId;

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

	
	
	public String getSideC() {
		return sideC;
	}

	public void setSideC(String sideC) {
		this.sideC = sideC;
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
	 * @return the standard
	 */
	@Override
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	@Override
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	
	@Override
	public  void doAutoImport(){
		ScContractInfoFlowManager scContractInfoFlowManager = SpringContextHolder.getBean("scContractInfoFlowManager");
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		ScContractInfoFlow scContractInfoFlow = new ScContractInfoFlow();
		ScContractTempletInfo scContractTempletInfo = new ScContractTempletInfo();
		if(StringUtils.isNotBlank(this.contractTempletInfoId)){
			scContractTempletInfo = scContractTempletInfoManager.getEntity(this.contractTempletInfoId);
			if(scContractTempletInfo != null){
				ResApproveInfo approveInfo=getResApproveInfo();
				scContractInfoFlow.setApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
				scContractInfoFlow.setResApproveInfoId(approveInfo.getResApproveInfoId());
				scContractInfoFlow.setScContractTempletInfo(scContractTempletInfo);
				scContractInfoFlow.setContractTempletHisId(contractTempletHisId);
				scContractInfoFlowManager.saveScContractInfoFlow(scContractInfoFlow);
				
				//更新合同状态为审批完成
				scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_MARK);
				scContractTempletInfo.setResApproveInfoId(null);
				scContractTempletInfoManager.saveScContractTempletInfo(scContractTempletInfo);
			}
			
		}
		
	};
	

	@Override
	public boolean doBeforeProcess() throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		ScContractTempletInfo scContractTempletInfo  = scContractTempletInfoManager.getEntity(this.contractTempletInfoId);
		if(StringUtils.isNotBlank(this.contractTempletInfoId) && scContractTempletInfo != null){
			ResApproveInfo approveInfo=getResApproveInfo();
			scContractTempletInfo.setResApproveInfoId(approveInfo.getResApproveInfoId());
			scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_ING);
			scContractTempletInfoManager.saveScContractTempletInfo(scContractTempletInfo);
		}
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return the contlib
	 */
	@Override
	public String getContlib() {
		return contlib;
	}

	/**
	 * @return the noncontlib
	 */
	@Override
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param contlib the contlib to set
	 */
	@Override
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @param noncontlib the noncontlib to set
	 */
	@Override
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}

	@Override
	public void doImport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "ContractItemApprove");
	}
	
}
