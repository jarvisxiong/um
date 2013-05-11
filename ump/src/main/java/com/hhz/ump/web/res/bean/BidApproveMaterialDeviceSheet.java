package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.ctdb.CtdbFixedBidManager;
import com.hhz.ump.entity.ctdb.CtdbFixedBid;
import com.hhz.ump.web.res.baseBean.BaseBidTemplate;

/**
 * 定标审批表（材料及设备类）
 * 
 * @author baolm
 * 
 *         2011-04-11
 */
public class BidApproveMaterialDeviceSheet extends BaseBidTemplate {

	/**
	 * 审批表编号
	 */
	private String approveCode = "SB";

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
	 * □金额＞200万
	 */
	private String above200;

	/**
	 * □50＜金额≤200万元
	 */
	private String from50to200;

	/**
	 * □10＜金额≤50万元
	 */
	private String from10to50;

	/**
	 * □金额≤10万元
	 */
	private String below10;

	/**
	 * 招标范围
	 */
	private String bidRange;

	/**
	 * 数量
	 */
	private String quatity;

	/**
	 * 供货周期
	 */
	private String supplyPeriod;

	/**
	 * 技术要求
	 */
	private String teckRequirement;

	/**
	 * □总价包干
	 */
	private String pricingModel1;

	/**
	 * □单价包干
	 */
	private String pricingModel2;

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
	
	
	//add by huangbijin 2012-04-10
	//甲方点选：二方合同、三方合同、带贸易公司合同(必填)
	private String contTypeCd1;
	private String contTypeCd2;
	private String contTypeCd3;
	//增加：甲方：(必填)
	private String parta;
	//增加：乙方：(必填)
	private String partb;
	//增加：丙方：(适用三方合同)
	private String partC;
	//增加：领料施工单位：(适用三方合同)
	private String realUseName;
	//增加：实际供方(适用：带贸易公司合同)
	private String realProvName;


	/**
	 * 是否垄断
	 */
	private String isMonopoly;

	/**
	 * 推荐中标理由
	 */
	private String successfulBidReason;

	/**
	 * 预算金额（元）
	 */
	private String budgetPrice;

	/**
	 * 目标成本（元）
	 */
	private String targetCost;

	/**
	 * 中标价（元）
	 */
	private String successfulBidPrice;

	/**
	 * 单价
	 */
	private String unitPrice;

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
	 * 6、招标图纸/技术要求
	 */
	private String bidPictrueId;
	
	//是否是项目权限
	private String isProjectAuth;
	
	private String contractPrice ;//审定价
	
	private String contractNo;//编号

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

	public String getAbove200() {
		return above200;
	}

	public void setAbove200(String above200) {
		this.above200 = above200;
	}

	public String getFrom50to200() {
		return from50to200;
	}

	public void setFrom50to200(String from50to200) {
		this.from50to200 = from50to200;
	}

	public String getFrom10to50() {
		return from10to50;
	}

	public void setFrom10to50(String from10to50) {
		this.from10to50 = from10to50;
	}

	public String getBelow10() {
		return below10;
	}

	public void setBelow10(String below10) {
		this.below10 = below10;
	}

	public String getQuatity() {
		return quatity;
	}

	public void setQuatity(String quatity) {
		this.quatity = quatity;
	}

	public String getSupplyPeriod() {
		return supplyPeriod;
	}

	public void setSupplyPeriod(String supplyPeriod) {
		this.supplyPeriod = supplyPeriod;
	}

	public String getTeckRequirement() {
		return teckRequirement;
	}

	public void setTeckRequirement(String teckRequirement) {
		this.teckRequirement = teckRequirement;
	}

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
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

/*	@Override
	public void doImport() {
		if (StringUtils.isNotBlank(getSupBasicId())) {
			SupApproveResManager supApproveResManager = SpringContextHolder.getBean("supApproveResManager");
			SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
			SupApproveRes approveRes = new SupApproveRes();
			approveRes.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());
			approveRes.setSupAppType("1");// 定标
			approveRes.setProjectName(projectName);
			approveRes.setProjectCd(projectCd);
			approveRes.setAppCompTime(getResApproveInfo().getCompleteDate());
			approveRes.setAppProjectName(engineeringName);
			SupBasic supBasic = supBasicManager.getEntity(getSupBasicId());
			approveRes.setSupBasic(supBasic);
			supApproveResManager.saveSupApproveRes(approveRes);
		}
		//ZCGL_HTQS_60
		//若分类代码为地产定标审批表的记录，则新增一条合同台账
		if("ZCGL_HTQS_40".equals(getResApproveInfo().getAuthTypeCd())){
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
			//招标范围/数量
//			contLedger.setRangeNum(bidRange+"/"+quatity);
			
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
			//付款方式
//			contLedger.setPayWay(paymentType);
			//中标价successfulBidPrice
			//进度状态默认为未开工
			contLedger.setProcStatus("0");
			//合同状态默认为未完未结
			contLedger.setContStatus("0");
			//带入网批CD和ID
			contLedger.setResApproveCd(getResApproveInfo().getApproveCd()+getResApproveInfo().getSerialNo());
			contLedger.setResApproveId(getResApproveInfo().getResApproveInfoId());

			//中标单位
//			contLedger.setPartB(bidUnit); 
			contLedger.setPartA(parta);
			//合同台账以乙方为准
			contLedger.setPartB(partb);
			if(StringUtils.isNotBlank(contTypeCd1) && contTypeCd1.equals("true") ){
				
				//不做任何
			}else	if(StringUtils.isNotBlank(contTypeCd2) && contTypeCd2.equals("true")){
				contLedger.setPartC(partC);
				//领料施工单位
				//contLedger.setAgreeAttaId(realUseName);
				
				
			}else if(StringUtils.isNotBlank(contTypeCd3) && contTypeCd3.equals("true")){
			contLedger.setRealProvName(realProvName);
			}
			//导出合同台账设置战略类型，0：非战略，1：战略
		    contLedger.setStrageFlg("0");
			ContLedgerManager contLedgerManager= SpringContextHolder.getBean("contLedgerManager");
			contLedgerManager.saveContLedger(contLedger);
		}
		
		
	}*/
	
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
		/*if(StringUtils.isNotBlank(this.getConstructionArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(this.getConstructionArea().trim()));
				cfb.setConstructArea(constructArea);
			}
			catch(Exception e){}
		}*/
		
		//6.中标价
		if(StringUtils.isNotBlank(this.getSuccessfulBidPrice())) {
			try{
				BigDecimal bidedPrice=BigDecimal.valueOf(Double.parseDouble(this.getSuccessfulBidPrice().trim().replaceAll(",", "")));			
				cfb.setBidedPrice(bidedPrice);
			}
			catch(Exception e){}
		}		
		//7.单位造价（单价）
		if(StringUtils.isNotBlank(this.getUnitPrice())) {
			try{
				BigDecimal unitPric=BigDecimal.valueOf(Double.parseDouble(this.getUnitPrice().trim().replaceAll(",", "")));			
				cfb.setUnitPrice(unitPric);
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
		if("true".equals(this.getPricingModel1())){
			cfb.setCalcuMode("总价包干");
		}
		if("true".equals(this.getPricingModel2())){
			cfb.setCalcuMode("单价包干");
		}
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(this.getResApproveInfo().getAuthTypeCd());
		//执行保存
		ctdbFixedBidManager.saveCtdbFixedBid(cfb);
		
	}

	/*@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}*/

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
 
	public String getParta() {
		return parta;
	}

	public void setParta(String parta) {
		this.parta = parta;
	}

	public String getPartb() {
		return partb;
	}

	public void setPartb(String partb) {
		this.partb = partb;
	}
 

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getRealUseName() {
		return realUseName;
	}

	public void setRealUseName(String realUseName) {
		this.realUseName = realUseName;
	}

	public String getRealProvName() {
		return realProvName;
	}

	public void setRealProvName(String realProvName) {
		this.realProvName = realProvName;
	}

	public String getContTypeCd1() {
		return contTypeCd1;
	}

	public void setContTypeCd1(String contTypeCd1) {
		this.contTypeCd1 = contTypeCd1;
	}

	public String getContTypeCd2() {
		return contTypeCd2;
	}

	public void setContTypeCd2(String contTypeCd2) {
		this.contTypeCd2 = contTypeCd2;
	}

	public String getContTypeCd3() {
		return contTypeCd3;
	}

	public void setContTypeCd3(String contTypeCd3) {
		this.contTypeCd3 = contTypeCd3;
	}

	/**
	 * 增加自动导入功能
	 */
	@Override
	public void doAutoImport(){
		//执行导入定标审批表
		try{
			importFixedBid();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
}
