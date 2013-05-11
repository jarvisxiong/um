package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContVisaUpdateManager;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**工程造价咨询单项委托单审批表 
 */
public class PriceFeeCheck  extends BaseContLedgerTemplate {
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 委托单编号
	 */
	private String visaNo;
	/**
	 * 合同名称
	 */
	private String contName;

	/**
	 * 乙方(收款人)
	 */
	private String partB;
	/**
	 * 项目cd
	 */
	private String projectCd;
	/**
	 * 项目名称
	 */
	private String projectName;
	private String projectPeriod;// 期

	private String area;// 面积
	private String partATrustPart;//甲方委托部门
	private String partAOperator;//甲方经办人
	private String consultFeeRule;//咨询费用计算规则
	private String consultRate;//咨询费比例
	private String trustBidItem;//委托事项：工程招标造价咨询
	private String settleItem;//委托事项：工程结算造价咨询
	private String consultFee;//核定咨询费(元)
	private String braidList;//编制清单
	private String braidBaseBidPrice;//编制标底
	private String rePriceCheck;//重新度量标后核对
	private String projectSettle;//工程结算
	private String braidOther;//其他
	private String baseBidPriceBudget;//标底预算价
	private String reDrawingBudget;//重新度量施工图预算价
	private String bidApprovalPrice;//定标价
	private String afterCheckApprove;//标后核对批准价
	private String projectPrice;//应计入工程造价的电缆、面砖、石材的价格
	private String cutDrawingPrice;//应扣除套图部分造价
	private String otherProjectPrice;//其他工程造价加减额
	private String standardConsultFee;//标准咨询费
	private String addSubFee;//加减咨询费
	private String singleTrustId;//单项委托单-附件
	private String bidApprovalId;//定标单或标后核对核定单
	private String firstTrialId;//与咨询机构初审意见
	private String contVisaUpdateId;
	@Override
	public void doImport() {
		// TODO Auto-generated method stub
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContVisaUpdateManager visaManager = SpringContextHolder.getBean("contVisaUpdateManager");
		if(StringUtils.isNotBlank(contVisaUpdateId)){
			ContVisaUpdate contVisaUpdate =visaManager.getEntity(contVisaUpdateId);
			BigDecimal groupCheckFee =new BigDecimal(FormatUtil.formatDouble(consultFee));//集团核定费用
			contVisaUpdate.setAmountUpdate(groupCheckFee);
			ledgerManager.updateContByRes(getContLedgerId(),"", null, contVisaUpdate, null, null);
		}
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPartATrustPart() {
		return partATrustPart;
	}

	public void setPartATrustPart(String partATrustPart) {
		this.partATrustPart = partATrustPart;
	}

	public String getPartAOperator() {
		return partAOperator;
	}

	public void setPartAOperator(String partAOperator) {
		this.partAOperator = partAOperator;
	}

	public String getConsultFeeRule() {
		return consultFeeRule;
	}

	public void setConsultFeeRule(String consultFeeRule) {
		this.consultFeeRule = consultFeeRule;
	}

	public String getConsultRate() {
		return consultRate;
	}

	public void setConsultRate(String consultRate) {
		this.consultRate = consultRate;
	}

	public String getTrustBidItem() {
		return trustBidItem;
	}

	public void setTrustBidItem(String trustBidItem) {
		this.trustBidItem = trustBidItem;
	}

	public String getSettleItem() {
		return settleItem;
	}

	public void setSettleItem(String settleItem) {
		this.settleItem = settleItem;
	}

	public String getConsultFee() {
		return consultFee;
	}

	public void setConsultFee(String consultFee) {
		this.consultFee = consultFee;
	}

	public String getBraidList() {
		return braidList;
	}

	public void setBraidList(String braidList) {
		this.braidList = braidList;
	}

	public String getBraidBaseBidPrice() {
		return braidBaseBidPrice;
	}

	public void setBraidBaseBidPrice(String braidBaseBidPrice) {
		this.braidBaseBidPrice = braidBaseBidPrice;
	}

	public String getRePriceCheck() {
		return rePriceCheck;
	}

	public void setRePriceCheck(String rePriceCheck) {
		this.rePriceCheck = rePriceCheck;
	}

	public String getProjectSettle() {
		return projectSettle;
	}

	public void setProjectSettle(String projectSettle) {
		this.projectSettle = projectSettle;
	}

	public String getBraidOther() {
		return braidOther;
	}

	public void setBraidOther(String braidOther) {
		this.braidOther = braidOther;
	}

	public String getBaseBidPriceBudget() {
		return baseBidPriceBudget;
	}

	public void setBaseBidPriceBudget(String baseBidPriceBudget) {
		this.baseBidPriceBudget = baseBidPriceBudget;
	}

	public String getReDrawingBudget() {
		return reDrawingBudget;
	}

	public void setReDrawingBudget(String reDrawingBudget) {
		this.reDrawingBudget = reDrawingBudget;
	}

	public String getBidApprovalPrice() {
		return bidApprovalPrice;
	}

	public void setBidApprovalPrice(String bidApprovalPrice) {
		this.bidApprovalPrice = bidApprovalPrice;
	}

	public String getAfterCheckApprove() {
		return afterCheckApprove;
	}

	public void setAfterCheckApprove(String afterCheckApprove) {
		this.afterCheckApprove = afterCheckApprove;
	}

	public String getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(String projectPrice) {
		this.projectPrice = projectPrice;
	}

	public String getCutDrawingPrice() {
		return cutDrawingPrice;
	}

	public void setCutDrawingPrice(String cutDrawingPrice) {
		this.cutDrawingPrice = cutDrawingPrice;
	}

	public String getOtherProjectPrice() {
		return otherProjectPrice;
	}

	public void setOtherProjectPrice(String otherProjectPrice) {
		this.otherProjectPrice = otherProjectPrice;
	}

	public String getStandardConsultFee() {
		return standardConsultFee;
	}

	public void setStandardConsultFee(String standardConsultFee) {
		this.standardConsultFee = standardConsultFee;
	}

	public String getSingleTrustId() {
		return singleTrustId;
	}

	public void setSingleTrustId(String singleTrustId) {
		this.singleTrustId = singleTrustId;
	}

	public String getFirstTrialId() {
		return firstTrialId;
	}

	public void setFirstTrialId(String firstTrialId) {
		this.firstTrialId = firstTrialId;
	}

	public String getAddSubFee() {
		return addSubFee;
	}

	public void setAddSubFee(String addSubFee) {
		this.addSubFee = addSubFee;
	}

	public String getBidApprovalId() {
		return bidApprovalId;
	}

	public void setBidApprovalId(String bidApprovalId) {
		this.bidApprovalId = bidApprovalId;
	}

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getContVisaUpdateId() {
		return contVisaUpdateId;
	}

	public void setContVisaUpdateId(String contVisaUpdateId) {
		this.contVisaUpdateId = contVisaUpdateId;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contName;
	}

}
