package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.ctdb.CtdbFixedBidManager;
import com.hhz.ump.dao.plan.CostCtrlBidPurcManager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.ctdb.CtdbFixedBid;
import com.hhz.ump.entity.plan.CostCtrlBidPurc;
import com.hhz.ump.web.res.baseBean.BaseBidTemplate;

/**
 * 定标审批表(工程类) / (项目招标)
 * @author baolm
 *
 * 2011-04-08
 */
//public class BidApproveProjectSheet extends BaseSupTemplate
public class BidApproveProjectSheet extends BaseBidTemplate {
	
	/**
	 * 审批表编号
	 */
	private String approveCode = "SG";

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
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * □金额＞500万 / 报审(大于500万)
	 */
	private String above500;
	
	/**
	 * □100＜金额≤500万元 / 报审(100万~500万)
	 */
	private String from100to500;
	
	/**
	 * □30＜金额≤100万元 / 报审(30万~100万)
	 */
	private String from30to100;
	
	/**
	 * □金额≤30
	 */
	private String below30;
	
	/**
	 * □报备(项目权限)
	 */
	private String projectAuth;
	
	/**
	 * 招标范围
	 */
	private String bidRange;
	
	/**
	 * 施工面积
	 */
	private String constructionArea;
	
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
	 * □总价包干
	 */
	private String pricingModel1;
	
	/**
	 * □单价包干（出图一个月完成总价包干） / 可调总价包干
	 */
	private String pricingModel2;
	
	/**
	 * □按时结算 / 单价包干
	 */
	private String pricingModel3;
	
	/**
	 * □定额计价
	 */
	private String pricingModel4;
	
	/**
	 * 付款方式
	 */
	private String paymentType;
	
	/**
	 * 中标单位
	 */
	private String bidUnit;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 是否垄断
	 */
	private String isMonopoly;
	
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
	 * 1、邀标单位资质审批表
	 */
	private String bidQualificationApproveId;
	
	/**
	 * 2、中标单位报价清单
	 */
	private String bidPriceListId;
	
	/**
	 * 3、投标报价汇总表
	 */
	private String bidSummaryId;
	
	/**
	 * 4、招标答疑及询标问卷
	 */
	private String bidAnswerAndBidQueryId;
	
	/**
	 * 5、技术标评结果
	 */
	private String techBidEvaluateId;
	
	/**
	 * 6、招标图纸
	 */
	private String bidPictrueId;
	
	/**
	 * 预算和批准文件
	 */
	private String budgetApproveFileId;
	
	/**
	 * 招标文件、投标文件
	 */
	private String inviteBidFileId;
	

	/**
	 * 其他文件
	 */
	private String otherFileId;
	
	
	private String businessCompany; //商业公司发起
	private String businessGroup;	//商业集团发起
	
	//是否是项目权限
	private String isProjectAuth;
	
	private String contractPrice ;//审定价
	
	private String contractNo;//编号
	
	//开业前
	private String before;
	//开业后
	private String after;	
	private String meeting;//已上会	
	private String bidMeetingInfo;//上会纪要	


	//2012-06-26 add by huangbijin
	//招标采购ID
	private String ccbpId;
	//招标采购编号(serailType+no)
	private String ccbpNo; 
	//招标采购事项
	private String ccbpPlanContentDesc;
	
	private String bidLedgerId;
	private String bidSupId;
	private String bidSupName;

	
	public String getBidUnit() {
		return bidUnit;
	}

	public void setBidUnit(String bidUnit) {
		this.bidUnit = bidUnit;
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

	public void setIsMonopoly(String isMonopoly) {
		this.isMonopoly = isMonopoly;
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

	public String getBidPriceListId() {
		return bidPriceListId;
	}

	public void setBidPriceListId(String bidPriceListId) {
		this.bidPriceListId = bidPriceListId;
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

	public String getBidRange() {
		return bidRange;
	}

	public void setBidRange(String bidRange) {
		this.bidRange = bidRange;
	}

	public String getConstructionArea() {
		return constructionArea;
	}

	public void setConstructionArea(String constructionArea) {
		this.constructionArea = constructionArea;
	}

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
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

	public String getBaseBidPrice() {
		return baseBidPrice;
	}

	public void setBaseBidPrice(String baseBidPrice) {
		this.baseBidPrice = baseBidPrice;
	}

	public String getBidQualificationApproveId() {
		return bidQualificationApproveId;
	}

	public void setBidQualificationApproveId(String bidQualificationApproveId) {
		this.bidQualificationApproveId = bidQualificationApproveId;
	}

	public String getBidSummaryId() {
		return bidSummaryId;
	}

	public void setBidSummaryId(String bidSummaryId) {
		this.bidSummaryId = bidSummaryId;
	}

	public String getBidAnswerAndBidQueryId() {
		return bidAnswerAndBidQueryId;
	}

	public void setBidAnswerAndBidQueryId(String bidAnswerAndBidQueryId) {
		this.bidAnswerAndBidQueryId = bidAnswerAndBidQueryId;
	}

	public String getTechBidEvaluateId() {
		return techBidEvaluateId;
	}

	public void setTechBidEvaluateId(String techBidEvaluateId) {
		this.techBidEvaluateId = techBidEvaluateId;
	}

	public String getBidPictrueId() {
		return bidPictrueId;
	}

	public void setBidPictrueId(String bidPictrueId) {
		this.bidPictrueId = bidPictrueId;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getApproveCode() {
		return approveCode;
	}

	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getPricingModel4() {
		return pricingModel4;
	}

	public void setPricingModel4(String pricingModel4) {
		this.pricingModel4 = pricingModel4;
	}

	public String getBudgetApproveFileId() {
		return budgetApproveFileId;
	}

	public void setBudgetApproveFileId(String budgetApproveFileId) {
		this.budgetApproveFileId = budgetApproveFileId;
	}

	public String getInviteBidFileId() {
		return inviteBidFileId;
	}

	public void setInviteBidFileId(String inviteBidFileId) {
		this.inviteBidFileId = inviteBidFileId;
	}

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

	public String getProjectAuth() {
		return projectAuth;
	}

	public void setProjectAuth(String projectAuth) {
		this.projectAuth = projectAuth;
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
/*
	@Override
	public void doImport() {
		if (StringUtils.isNotBlank(getSupBasicId())){
			SupApproveResManager supApproveResManager = SpringContextHolder.getBean("supApproveResManager");
			SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
			SupApproveRes approveRes = new SupApproveRes();
			approveRes.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());
			approveRes.setSupAppType("1");// 定标
			approveRes.setProjectName(projectName);
			approveRes.setProjectCd(projectCd);
			approveRes.setAppCompTime(getResApproveInfo().getCompleteDate());
			approveRes.setAppProjectName(engineeringName);
			SupBasic supBasic= supBasicManager.getEntity(getSupBasicId());
			approveRes.setSupBasic(supBasic);
			supApproveResManager.saveSupApproveRes(approveRes);
		}
		//ZCGL_HTQS_30
		//若分类代码为地产定标审批表的记录，则新增一条合同台账
		//ZCGL_HTQS_30:地产定标审批表(工程)；BLSY_ZCGL_DB_20：定标审批表(工程改造、维护、维修);ZCGL_HTQS_80:战略
	/**	if("ZCGL_HTQS_30".equals(getResApproveInfo().getAuthTypeCd())||"ZCGL_HTQS_60".equals(getResApproveInfo().getAuthTypeCd())
				||"BLSY_ZCGL_DB_20".equals(getResApproveInfo().getAuthTypeCd())||"BLSY_ZCGL_DB_30".equals(getResApproveInfo().getAuthTypeCd())
				||"BLSY_ZCGL_DB_40".equals(getResApproveInfo().getAuthTypeCd())||"BLSY_ZCGL_DB_50".equals(getResApproveInfo().getAuthTypeCd())
				||"BLSY_ZCGL_DB_60".equals(getResApproveInfo().getAuthTypeCd())||"BLSY_ZCGL_DB_70".equals(getResApproveInfo().getAuthTypeCd())
				||"ZCGL_HTQS_80".equals(getResApproveInfo().getAuthTypeCd())){//
		//所有的定标审批表都进入合同台账
			ContLedger contLedger= new ContLedger();
			ContProjectCodeManager projectCodeManager = SpringContextHolder.getBean("contProjectCodeManager");
			//contLedger.setProjectCd(projectCd)
			List<ContProjectCode> projectCodeList =projectCodeManager.getAll();
			//寻找projectCd，若在合同类别里有projectCd,则新增该项目，若无则不setProjectCd 
			boolean haveProject=false;
			for(ContProjectCode code:projectCodeList){
				if(projectCd.equals(code.getProjectCd())){
					haveProject =true;
					break;
				}
			}
			if(haveProject){
				contLedger.setProjectCd(projectCd);
			}
			//如果是定标审批表(工程改造、维护、维修)，则添加商业工程设计字段
			if("BLSY_ZCGL_DB_20".equals(getResApproveInfo().getAuthTypeCd())){
				contLedger.setContTypeCd2("22");
			}else if("BLSY_ZCGL_DB_30".equals(getResApproveInfo().getAuthTypeCd())){
				contLedger.setContTypeCd2("23");
			}else if("BLSY_ZCGL_DB_40".equals(getResApproveInfo().getAuthTypeCd())){
				contLedger.setContTypeCd2("24");
			}else if("BLSY_ZCGL_DB_50".equals(getResApproveInfo().getAuthTypeCd())){
				contLedger.setContTypeCd2("25");
			}else if("BLSY_ZCGL_DB_60".equals(getResApproveInfo().getAuthTypeCd())||"BLSY_ZCGL_DB_70".equals(getResApproveInfo().getAuthTypeCd())){
				contLedger.setContTypeCd2("26");
			}
			//工程名称
			contLedger.setContName(engineeringName);
			//招标范围
//			contLedger.setRangeNum(bidRange);
			//总价包干pricingModel1
			if(StringUtils.isNotBlank(pricingModel1)){
				contLedger.setContProperty("0");
			}else if(StringUtils.isNotBlank(pricingModel2)){
				contLedger.setContProperty("2");
			}
			//新增合同编号
			if(StringUtils.isNotBlank(contractNo)){
				contLedger.setContNo(contractNo);
			}
			//合同总价 ：审定价
			if(StringUtils.isNotBlank(contractPrice)){
				String totalPrice = contractPrice.replace(",", "");
				//合同总价
				contLedger.setTotalPrice(new BigDecimal(totalPrice));
				//已确认合同总价
				contLedger.setUpdateTotal(contLedger.getTotalPrice());
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				//计划开工日期
				contLedger.setPlanBeginDate(format.parse(fromDate));
				//计划竣工日期
				contLedger.setPlanEndDate(format.parse(toDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//共多少天
			contLedger.setPeriod(totalDay);
			//付款方式
//			contLedger.setPayWay(paymentType);
			//中标单位
			contLedger.setPartB(bidUnit);
			//中标价successfulBidPrice
			//进度状态默认为未开工
			contLedger.setProcStatus("0");
			//合同状态默认为未完未结
			contLedger.setContStatus("0");
			//带入网批CD和ID
			contLedger.setResApproveCd(getResApproveInfo().getApproveCd()+getResApproveInfo().getSerialNo());
			contLedger.setResApproveId(getResApproveInfo().getResApproveInfoId());
			
			ContLedgerManager contLedgerManager= SpringContextHolder.getBean("contLedgerManager");
			contLedgerManager.saveContLedger(contLedger);
		//}
			
	}
*/
	
	private void importFixedBid() {
		CtdbFixedBidManager ctdbFixedBidManager = SpringContextHolder.getBean("ctdbFixedBidManager");
		CtdbFixedBid cfb=new CtdbFixedBid();
		//1.项目名称
		cfb.setOrgName(this.getProjectName());
		cfb.setOrgCd(this.getProjectCd());
		//2.工程名称（设计阶段）
		cfb.setProjectName(this.getEngineeringName());
		//3.中标单位
		cfb.setBidSupCd(this.getBidUnit());
		//4.招标范围
		cfb.setBidArea(this.getBidRange());		
		//5.施工面积（数量）
		if(StringUtils.isNotBlank(this.getConstructionArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(this.getConstructionArea().trim()));
				cfb.setConstructArea(constructArea);
			}
			catch(Exception e){}
		}
		
		//6.中标价
		if(StringUtils.isNotBlank(this.getSuccessfulBidPrice())) {
			try{
				BigDecimal bidedPrice=BigDecimal.valueOf(Double.parseDouble(this.getSuccessfulBidPrice().trim().replaceAll(",", "")));			
				cfb.setBidedPrice(bidedPrice);
			}
			catch(Exception e){}
		}		
		//7.单位造价（单价）unilateralCost
		if(StringUtils.isNotBlank(this.getUnilateralCost())) {
			try{
				BigDecimal unitPrice=BigDecimal.valueOf(Double.parseDouble(this.getUnilateralCost().trim().replaceAll(",", "")));			
				cfb.setUnitPrice(unitPrice);
			}
			catch(Exception e){}
		}
		//8.定标日期（按“定标审批表”发起的日期导入）
		cfb.setBidDate(this.getResApproveInfo().getCreatedDate());
		//9.网批编号
		cfb.setResCd(this.getResApproveInfo().getResApproveInfoId());
		cfb.setApproveCd(this.getResApproveInfo().getApproveCd()+this.getResApproveInfo().getSerialNo());
		//10.导入类型
		cfb.setImportType(this.getResApproveInfo().getAuthTypeCd());
		//11、计价模式
		//工程
		if("ZCGL_HTQS_30".equals(getResApproveInfo().getAuthTypeCd())){
			if(StringUtils.isNotBlank(pricingModel1)&&"true".equals(pricingModel1)){
				cfb.setCalcuMode("总价包干");
			}	
			else 
				if(StringUtils.isNotBlank(pricingModel2)&&"true".equals(pricingModel2)){
					cfb.setCalcuMode("单价包干（出图一个月完成总价包干）");
				}
				else 
					if(StringUtils.isNotBlank(pricingModel3)&&"true".equals(pricingModel3)){
						cfb.setCalcuMode("按时结算");
					}
		}else {
			if(StringUtils.isNotBlank(pricingModel1)&&"true".equals(pricingModel1)){
				cfb.setCalcuMode("总价包干");
			}	
			else 
				if(StringUtils.isNotBlank(pricingModel2)&&"true".equals(pricingModel2)){
					cfb.setCalcuMode("可调总价包干");
				}
				else 
					if(StringUtils.isNotBlank(pricingModel3)&&"true".equals(pricingModel3)){
						cfb.setCalcuMode("单价包干");
					}
					else 
						if(StringUtils.isNotBlank(pricingModel4)&&"true".equals(pricingModel4)){
							cfb.setCalcuMode("定额计价");
						}
		}
		
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(this.getResApproveInfo().getAuthTypeCd());
		//执行保存
		ctdbFixedBidManager.saveCtdbFixedBid(cfb);
		
	}
/*
	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}
	*/

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

	public String getIsProjectAuth() {
		return isProjectAuth;
	}

	public void setIsProjectAuth(String isProjectAuth) {
		this.isProjectAuth = isProjectAuth;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Override
	public String getContractNo() {
		return contractNo;
	}

	@Override
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the before
	 */
	public String getBefore() {
		return before;
	}

	/**
	 * @param before the before to set
	 */
	public void setBefore(String before) {
		this.before = before;
	}

	/**
	 * @return the after
	 */
	public String getAfter() {
		return after;
	}

	/**
	 * @param after the after to set
	 */
	public void setAfter(String after) {
		this.after = after;
	}
	
	/**
	 * 增加自动导入功能
	 */
	@Override
	public void doAutoImport(){
		try {
			
			//自动设置中标单位
			if(StringUtils.isNotBlank(this.bidLedgerId)){
				BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
				BidSupManager bidSupManager = SpringContextHolder.getBean("bidSupManager");
				
				BidLedger bid = bidLedgerManager.getEntity(this.bidLedgerId);
				if(bid != null){
					//定标单ID
					bid.setBidResId(this.getResApproveInfoId());
					//设置中标
					bid.setBidStatusCd(BidLedgerManager.BID_STATUS_WIN);
					bidLedgerManager.saveBidLedger(bid);
					
					//设置中标单位
					if(StringUtils.isNotBlank(this.bidSupId)){
						BidSup sup = bidSupManager.getEntity(this.bidSupId);
						//1-设置中标
						sup.setSupBidStatusCd("1");
						bidSupManager.saveBidSup(sup);
					}
					
					//更新招标平台的定标单id，如不空或非-1，则为工程;如空或-1，则为战略。
					if(StringUtils.isNotBlank(this.ccbpId) && (!"-1".equals(this.ccbpId))){
						CostCtrlBidPurcManager costCtrlBidPurcManager = SpringContextHolder.getBean("costCtrlBidPurcManager");
						CostCtrlBidPurc purc = costCtrlBidPurcManager.getEntity(ccbpId);
						if(purc != null){
							//定标单ID
							purc.setBidResId(this.getResApproveInfoId());
						}
					}
				}
			}
			
			//自动导入定标数据库
			importFixedBid();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("定表单导入库异常! 网批ID:"+ this.getResApproveInfoId(), e);
		}	
	}

	public String getMeeting() {
		return meeting;
	}

	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}

	public String getBidMeetingInfo() {
		return bidMeetingInfo;
	}

	public void setBidMeetingInfo(String bidMeetingInfo) {
		this.bidMeetingInfo = bidMeetingInfo;
	}

	public String getCcbpId() {
		return ccbpId;
	}

	public void setCcbpId(String ccbpId) {
		this.ccbpId = ccbpId;
	}

	public String getCcbpNo() {
		return ccbpNo;
	}

	public void setCcbpNo(String ccbpNo) {
		this.ccbpNo = ccbpNo;
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

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public String getBidSupName() {
		return bidSupName;
	}

	public void setBidSupName(String bidSupName) {
		this.bidSupName = bidSupName;
	}

	public String getOtherFileId() {
		return otherFileId;
	}

	public void setOtherFileId(String otherFileId) {
		this.otherFileId = otherFileId;
	}

}
