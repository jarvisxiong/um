package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.dao.sup.SupApproveResManager;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.sup.SupApproveRes;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;

/**
 * 定标审批表(营销类)
 * @author hy
 *
 * 2011-09-27
 */
public class BidApproveMarketSheet extends BaseSupTemplate {
	
	/**
	 * 审批表编号
	 */
	private String approveCode = "YX";

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
			//ZCGL_HTQS_30
			//若分类代码为地产定标审批表的记录，则新增一条合同台账
			if("ZCGL_HTQS_30".equals(getResApproveInfo().getAuthTypeCd())||"ZCGL_HTQS_60".equals(getResApproveInfo().getAuthTypeCd())){
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
				//工程名称
				contLedger.setContName(engineeringName);
				//招标范围
//				contLedger.setRangeNum(bidRange);
				//总价包干pricingModel1
				if(StringUtils.isNotBlank(pricingModel1)){
					contLedger.setContProperty("0");
				}else if(StringUtils.isNotBlank(pricingModel2)){
					contLedger.setContProperty("2");
				}
				//合同总价 successfulBidPrice
				String totalPrice = successfulBidPrice.replace(",", "");
				//合同总价
				contLedger.setTotalPrice(new BigDecimal(totalPrice));
				//已确认合同总价
				contLedger.setUpdateTotal(contLedger.getTotalPrice());
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
//				contLedger.setPayWay(paymentType);
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
			}
		}
		
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

}
