package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.ctdb.CtdbFixedBidManager;
import com.hhz.ump.entity.ctdb.CtdbFixedBid;
import com.hhz.ump.web.res.baseBean.BaseBidTemplate;

/**
 * 定标审批表（设计类）
 * 
 * @author baolm
 * 
 *         2011-04-07
 */
public class BidApproveDesignSheet extends BaseBidTemplate {

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 判断有无项目
	 */
	private String noProjectFlg;//Add by zhuxj on 2012.4.13
	
	/**
	 * 项目Cd
	 */
	private String projectCd;

	/**
	 * 合同( )期
	 */
	private String projectPeriod;

	/**
	 * 审批表编号
	 */
	private String approveCode = "SJ";

	/**
	 * □概念
	 */
	private String designStage1;

	/**
	 * □方案
	 */
	private String designStage2;

	/**
	 * □扩初
	 */
	private String designStage3;

	/**
	 * □施工图
	 */
	private String designStage4;

	/**
	 * 设计范围
	 */
	private String designRange;

	/**
	 * □金额＞100万
	 */
	private String above100;

	/**
	 * □10万元＜金额≤100万元
	 */
	private String from10to100;

	/**
	 * □金额≤10万元
	 */
	private String below10;

	/**
	 * 设计周期
	 */
	private String fromDate;

	/**
	 * 设计周期
	 */
	private String toDate;

	/**
	 * 设计周期
	 */
	private String totalDay;

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
	 * 招标人
	 */
	private String bidPerson;

	/**
	 * 招标部负责人
	 */
	private String bidDepartPerson;

	/**
	 * 付款方式
	 */
	private String paymentType;

	/**
	 * 暂定面积（平米）
	 */
	private String tentativeArea;

	/**
	 * 目标成本（万元）
	 */
	private String targetCost;

	/**
	 * 中标价（万元）
	 */
	private String successfulBidPrice;

	/**
	 * 单方造价（元/平米）
	 */
	private String unilateralCost;

	/**
	 * 1、中标单位报价清单
	 */
	private String bidPriceListId;

	/**
	 * 2、设计任务书
	 */
	private String designTaskBookId;

	/**
	 * 3、设计深度要求
	 */
	private String designDepthRequirementId;
	
	private String businessCompany; //商业公司发起
	private String businessGroup;	//商业集团发起
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

	public String getBidPerson() {
		return bidPerson;
	}

	public void setBidPerson(String bidPerson) {
		this.bidPerson = bidPerson;
	}

	public String getBidDepartPerson() {
		return bidDepartPerson;
	}

	public void setBidDepartPerson(String bidDepartPerson) {
		this.bidDepartPerson = bidDepartPerson;
	}

	public String getTentativeArea() {
		return tentativeArea;
	}

	public void setTentativeArea(String tentativeArea) {
		this.tentativeArea = tentativeArea;
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

	public String getDesignTaskBookId() {
		return designTaskBookId;
	}

	public void setDesignTaskBookId(String designTaskBookId) {
		this.designTaskBookId = designTaskBookId;
	}

	public String getDesignDepthRequirementId() {
		return designDepthRequirementId;
	}

	public void setDesignDepthRequirementId(String designDepthRequirementId) {
		this.designDepthRequirementId = designDepthRequirementId;
	}

	public String getDesignRange() {
		return designRange;
	}

	public void setDesignRange(String designRange) {
		this.designRange = designRange;
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
	
	
	
	public String getNoProjectFlg() {
		return noProjectFlg;
	}

	public void setNoProjectFlg(String noProjectFlg) {
		this.noProjectFlg = noProjectFlg;
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

	public String getApproveCode() {
		return approveCode;
	}

	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	public String getDesignStage1() {
		return designStage1;
	}

	public void setDesignStage1(String designStage1) {
		this.designStage1 = designStage1;
	}

	public String getDesignStage2() {
		return designStage2;
	}

	public void setDesignStage2(String designStage2) {
		this.designStage2 = designStage2;
	}

	public String getDesignStage3() {
		return designStage3;
	}

	public void setDesignStage3(String designStage3) {
		this.designStage3 = designStage3;
	}

	public String getDesignStage4() {
		return designStage4;
	}

	public void setDesignStage4(String designStage4) {
		this.designStage4 = designStage4;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getAbove100() {
		return above100;
	}

	public void setAbove100(String above100) {
		this.above100 = above100;
	}

	public String getFrom10to100() {
		return from10to100;
	}

	public void setFrom10to100(String from10to100) {
		this.from10to100 = from10to100;
	}

	public String getBelow10() {
		return below10;
	}

	public void setBelow10(String below10) {
		this.below10 = below10;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return bidUnit;
	}


	private void importFixedBid() {
		CtdbFixedBidManager ctdbFixedBidManager = SpringContextHolder.getBean("ctdbFixedBidManager");
		CtdbFixedBid cfb=new CtdbFixedBid();
		//1.项目名称
		cfb.setOrgName(this.getProjectName());
		cfb.setOrgCd(this.getProjectCd());
		//2.工程名称（设计阶段）
		//cfb.setProjectName(this.getEngineeringName());
		//3.中标单位
		cfb.setBidSupCd(this.getBidUnit());
		cfb.setBidSupName(this.getBidUnit());
		//4.招标范围
		//cfb.setBidArea(this.getBidRange());		
		//5.施工面积（数量）
		if(StringUtils.isNotBlank(this.getTentativeArea())) {
			try{
				BigDecimal constructArea=BigDecimal.valueOf(Double.parseDouble(this.getTentativeArea().trim().replaceAll(",", "")));
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
		//7.单位造价（单价）
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
		//默认系统创建
		cfb.setCreator("system");
		cfb.setCreatedDate(new Date());
		cfb.setAuthTypeCd(this.getResApproveInfo().getAuthTypeCd());
		//执行保存
		ctdbFixedBidManager.saveCtdbFixedBid(cfb);
		
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
	 * 增加自动导入功能
	 */
	@Override
	public void doAutoImport(){
		try {
			//自动导入定标数据库
			importFixedBid();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
