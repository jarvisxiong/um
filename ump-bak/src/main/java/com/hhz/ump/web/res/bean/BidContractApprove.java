package com.hhz.ump.web.res.bean;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.plan.CostCtrlBidPurcManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.dao.sup.SupApproveResManager;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.plan.CostCtrlBidPurc;
import com.hhz.ump.entity.sup.SupApproveRes;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

/**
 * 招标文件/合同评审表（合同文件）
 * @author baolm
 *
 * 2011-04-15
 */
public class BidContractApprove extends BaseSupTemplate implements IStatusModifyListener {
	/**
	 * 判断有无项目
	 */
	private String noProjectFlg;//Add by zhuxj on 2012.4.13
	/**
	 * 合同ID
	 */
	private String contLedgerId;
	/**
	 * 合同编号
	 */
	private String contNo;
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
	 * 单位名称
	 */
	private String unitName;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 是否垄断
	 */
	private String isMonopoly;
	
	/**
	 * □招标文件
	 */
	private String approveContent1;

	/**
	 * □合同文件
	 */
	private String approveContent2;
	
	/**
	 * 丙方
	 */
	private String approveContent3;//丙方 -Add for part C by zhuxj on 2012.3.31
	
	/**
	 * □招标
	 */
	private String cooperationWays1;
	
	/**
	 * □续标
	 */
	private String cooperationWays2;
	
	/**
	 * □直接委托
	 */
	private String cooperationWays3;
	
	/**
	 * □竞争性谈判
	 */
	private String cooperationWays4;
	
	/**
	 * 招标文件编号
	 */
	private String oriBidFileCd;
	
	/**
	 * 原合同文件编号
	 */
	private String oriContractFileCd;
	
	/**
	 * 直接委托(理由)
	 */
	private String delegateReason;
	
	/**
	 * 竞争性谈判(理由)
	 */
	private String negotiationReason;
	
	/**
	 * 招标范围
	 */
	private String bidRange;
	
	/**
	 * 施工工期
	 */
	private String fromDate;
	
	/**
	 * 施工工期
	 */
	private String toDate;
	
	/**
	 * 施工工期
	 */
	private String totalDay;
	
	/**
	 * 质量要求
	 */
	private String qualityRequirement;
	
	/**
	 * □工程量清单
	 */
	private String inviteBidModel1;
	
	/**
	 * □费率
	 */
	private String inviteBidModel2;
	
	/**
	 * □模拟清单(出图后1个月完成工程量核对)
	 */
	private String inviteBidModel3;
	//存放合同台账传过来的合同性质值
	private String pricingModel;
	/**
	 * □总价包干
	 */
	private String pricingModel1;
	
	/**
	 * □单价包干
	 */
	private String pricingModel2;
	
	/**
	 * □按时结算
	 */
	private String pricingModel3;
	
	/**
	 * 预算金额(元)
	 */
	private String budgetAmount;
	
	/**
	 * 付款方式
	 */
	private String paymentType;
	
	/**
	 * 中标单位
	 */
	private String successfulBidCompany;
	
	/**
	 * 推荐中标理由
	 */
	private String successfulBidReason;
	
	/**
	 * 目标成本（万元）
	 */
	private String targetCost;
	
	/**
	 * 标底（元）
	 */
	private String baseBidPrice;
	
	/**
	 * 中标价（万元）
	 */
	private String successfulBidPrice;
	
	/**
	 * 单方造价（元/平米）
	 */
	private String unilateralCost;
	
	/**
	 * 合同条款审批表
	 */
	private String contractItemApproveId;
	
	/**
	 * 合同
	 */
	private String contractFileId;
	
	/**
	 * 招标审批表
	 */
	private String bidApproveBillId;
	/**
	 * 定标审批表ID:来源于合同台账
	 */
	private String bidApprIdFromCont;
	/**
	 * 定标审批表No:来源于合同台账
	 */
	private String bidApprNoFromCont;
	
	/**
	 * 招标合同文件
	 */
	private String bidContractFileId;
	
	private String businessCompany; //商业公司发起
	private String businessGroup;	//商业集团发起
	
	
	
	
	//是否是项目权限
	private String isProjectAuth;
	
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
	
	
	
	public String getBidSectionName() {
		return bidSectionName;
	}

	public void setBidSectionName(String bidSectionName) {
		this.bidSectionName = bidSectionName;
	}

	//2012-06-26 add by huangbijin
	//招标采购ID
	private String ccbpId;
	//招标采购编号(serailType+no)
	private String ccbpNo; 
	//招标采购事项
	private String ccbpPlanContentDesc;
	
	//战略ID
	private String bidLedgerId;
	private String bidSerialTypeNo;
	private String bidSectionName;
	
	
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
	 * @return the contlib
	 */
	@Override
	public String getContlib() {
		return contlib;
	}

	/**
	 * @param contlib the contlib to set
	 */
	@Override
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @return the noncontlib
	 */
	@Override
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param noncontlib the noncontlib to set
	 */
	@Override
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}

	/**
	 * @return the contractNo
	 */
	@Override
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	@Override
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the contractName
	 */
	@Override
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName the contractName to set
	 */
	@Override
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getApproveContent1() {
		return approveContent1;
	}

	public void setApproveContent1(String approveContent1) {
		this.approveContent1 = approveContent1;
	}

	public String getApproveContent2() {
		return approveContent2;
	}

	public void setApproveContent2(String approveContent2) {
		this.approveContent2 = approveContent2;
	}
	
	public String getApproveContent3() {
		return approveContent3;
	}

	public void setApproveContent3(String approveContent3) {
		this.approveContent3 = approveContent3;
	}

	public String getCooperationWays1() {
		return cooperationWays1;
	}

	public void setCooperationWays1(String cooperationWays1) {
		this.cooperationWays1 = cooperationWays1;
	}

	public String getCooperationWays2() {
		return cooperationWays2;
	}

	public void setCooperationWays2(String cooperationWays2) {
		this.cooperationWays2 = cooperationWays2;
	}

	public String getCooperationWays3() {
		return cooperationWays3;
	}

	public void setCooperationWays3(String cooperationWays3) {
		this.cooperationWays3 = cooperationWays3;
	}

	public String getCooperationWays4() {
		return cooperationWays4;
	}

	public void setCooperationWays4(String cooperationWays4) {
		this.cooperationWays4 = cooperationWays4;
	}

	public String getOriBidFileCd() {
		return oriBidFileCd;
	}

	public void setOriBidFileCd(String oriBidFileCd) {
		this.oriBidFileCd = oriBidFileCd;
	}

	public String getOriContractFileCd() {
		return oriContractFileCd;
	}

	public void setOriContractFileCd(String oriContractFileCd) {
		this.oriContractFileCd = oriContractFileCd;
	}

	public String getDelegateReason() {
		return delegateReason;
	}

	public void setDelegateReason(String delegateReason) {
		this.delegateReason = delegateReason;
	}

	public String getNegotiationReason() {
		return negotiationReason;
	}

	public void setNegotiationReason(String negotiationReason) {
		this.negotiationReason = negotiationReason;
	}

	public String getBidRange() {
		return bidRange;
	}

	public void setBidRange(String bidRange) {
		this.bidRange = bidRange;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(String totalDay) {
		this.totalDay = totalDay;
	}

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	public String getInviteBidModel1() {
		return inviteBidModel1;
	}

	public void setInviteBidModel1(String inviteBidModel1) {
		this.inviteBidModel1 = inviteBidModel1;
	}

	public String getInviteBidModel2() {
		return inviteBidModel2;
	}

	public void setInviteBidModel2(String inviteBidModel2) {
		this.inviteBidModel2 = inviteBidModel2;
	}

	public String getInviteBidModel3() {
		return inviteBidModel3;
	}

	public void setInviteBidModel3(String inviteBidModel3) {
		this.inviteBidModel3 = inviteBidModel3;
	}

	public String getPricingModel1() {
		return pricingModel1;
	}

	public void setPricingModel1(String pricingModel1) {
		this.pricingModel1 = pricingModel1;
	}

	public String getPricingModel2() {
		return pricingModel2;
	}

	public void setPricingModel2(String pricingModel2) {
		this.pricingModel2 = pricingModel2;
	}

	public String getPricingModel3() {
		return pricingModel3;
	}

	public void setPricingModel3(String pricingModel3) {
		this.pricingModel3 = pricingModel3;
	}

	public String getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(String budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSuccessfulBidCompany() {
		return successfulBidCompany;
	}

	public void setSuccessfulBidCompany(String successfulBidCompany) {
		this.successfulBidCompany = successfulBidCompany;
	}

	public String getSuccessfulBidReason() {
		return successfulBidReason;
	}

	public void setSuccessfulBidReason(String successfulBidReason) {
		this.successfulBidReason = successfulBidReason;
	}

	public String getTargetCost() {
		return targetCost;
	}

	public void setTargetCost(String targetCost) {
		this.targetCost = targetCost;
	}

	public String getBaseBidPrice() {
		return baseBidPrice;
	}

	public void setBaseBidPrice(String baseBidPrice) {
		this.baseBidPrice = baseBidPrice;
	}

	public String getSuccessfulBidPrice() {
		return successfulBidPrice;
	}

	public void setSuccessfulBidPrice(String successfulBidPrice) {
		this.successfulBidPrice = successfulBidPrice;
	}

	public String getUnilateralCost() {
		return unilateralCost;
	}

	public void setUnilateralCost(String unilateralCost) {
		this.unilateralCost = unilateralCost;
	}

	public String getContractItemApproveId() {
		return contractItemApproveId;
	}

	public void setContractItemApproveId(String contractItemApproveId) {
		this.contractItemApproveId = contractItemApproveId;
	}

	public String getContractFileId() {
		return contractFileId;
	}

	public void setContractFileId(String contractFileId) {
		this.contractFileId = contractFileId;
	}

	public String getBidApproveBillId() {
		return bidApproveBillId;
	}

	public void setBidApproveBillId(String bidApproveBillId) {
		this.bidApproveBillId = bidApproveBillId;
	}

	public String getBidContractFileId() {
		return bidContractFileId;
	}

	public void setBidContractFileId(String bidContractFileId) {
		this.bidContractFileId = bidContractFileId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsMonopoly() {
		return isMonopoly;
	}
	
	public String getNoProjectFlg() {
		return noProjectFlg;
	}

	public void setNoProjectFlg(String noProjectFlg) {
		this.noProjectFlg = noProjectFlg;
	}

	public void setIsMonopoly(String isMonopoly) {
		this.isMonopoly = isMonopoly;
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
		return engineeringName;
	}

	@Override
	public void doImport() {
		
		if (StringUtils.isNotBlank(getSupBasicId())){
			
			SupApproveResManager supApproveResManager = SpringContextHolder.getBean("supApproveResManager");
			SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
			SupApproveRes approveRes = new SupApproveRes();
			approveRes.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());
			approveRes.setSupAppType("2");// 合同
			approveRes.setProjectName(projectName);
			approveRes.setProjectCd(projectCd);
			approveRes.setAppProjectName(engineeringName);
			approveRes.setAppCompTime(getResApproveInfo().getCompleteDate());
			SupBasic supBasic= supBasicManager.getEntity(getSupBasicId());
			approveRes.setSupBasic(supBasic);
			supApproveResManager.saveSupApproveRes(approveRes);
		}
		

		//若是工程
		if(StringUtils.isNotBlank( this.ccbpId)){
			//推送招标合同文件至 计划平台 add by huangbijin 2012-06-27
			CostCtrlBidPurcManager costCtrlBidPurcManager = SpringContextHolder.getBean("costCtrlBidPurcManager");
			CostCtrlBidPurc purc = costCtrlBidPurcManager.getEntity( this.ccbpId);
			if(purc != null){
				purc.setCallResId(this.getResApproveInfoId());
				purc.setCallResAttachId(this.getContractFileId());
				costCtrlBidPurcManager.saveCostCtrlBidPurc(purc);
			}
			
			//更新投标台账的“招标网批ID”（callResId)  add by huangbijin 2012-06-27
			BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
			BidLedger bid = bidLedgerManager.getLedgerByCcbpNo( this.ccbpId);
			
			if(bid != null){
				bid.setCallResId(this.getResApproveInfoId());
				bid.setCallResAttachId(this.getBidContractFileId());//"招标合同文件"附件
				bidLedgerManager.saveBidLedger(bid);
			}
		}
		

		//若是战略
		if(StringUtils.isNotBlank(this.bidLedgerId)){
			//更新投标台账的“招标网批ID”（callResId)  add by huangbijin 2012-06-27
			BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
			BidLedger bid = bidLedgerManager.getEntity(this.bidLedgerId);
			if(bid != null){
				bid.setCallResId(this.getResApproveInfoId());
				bid.setCallResAttachId(this.getBidContractFileId());//"招标合同文件"附件
				bidLedgerManager.saveBidLedger(bid);
			}
		}
		
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
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

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContLedgerId() {
		return contLedgerId;
	}

	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}

	public String getPricingModel() {
		return pricingModel;
	}

	public void setPricingModel(String pricingModel) {
		this.pricingModel = pricingModel;
	}

	public String getIsProjectAuth() {
		return isProjectAuth;
	}

	public void setIsProjectAuth(String isProjectAuth) {
		this.isProjectAuth = isProjectAuth;
	}

	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "BidContractApprove");
	}

	public String getBidApprIdFromCont() {
		return bidApprIdFromCont;
	}

	public void setBidApprIdFromCont(String bidApprIdFromCont) {
		this.bidApprIdFromCont = bidApprIdFromCont;
	}

	public String getBidApprNoFromCont() {
		return bidApprNoFromCont;
	}

	public void setBidApprNoFromCont(String bidApprNoFromCont) {
		this.bidApprNoFromCont = bidApprNoFromCont;
	}

	public String getCcbpNo() {
		return ccbpNo;
	}

	public void setCcbpNo(String ccbpNo) {
		this.ccbpNo = ccbpNo;
	}

	public String getCcbpId() {
		return ccbpId;
	}

	public void setCcbpId(String ccbpId) {
		this.ccbpId = ccbpId;
	}

	public String getCcbpPlanContentDesc() {
		return ccbpPlanContentDesc;
	}

	public void setCcbpPlanContentDesc(String ccbpPlanContentDesc) {
		this.ccbpPlanContentDesc = ccbpPlanContentDesc;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public String getBidSerialTypeNo() {
		return bidSerialTypeNo;
	}

	public void setBidSerialTypeNo(String bidSerialTypeNo) {
		this.bidSerialTypeNo = bidSerialTypeNo;
	}
	
}
