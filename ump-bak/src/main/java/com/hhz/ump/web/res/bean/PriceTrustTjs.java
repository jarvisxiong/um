package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

public class PriceTrustTjs extends BaseContLedgerTemplate {

	/**
	 * 合同编号
	 */
	private String contNo;
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
	private String preConsultFee;//预估咨询费(元)
	private String totalDoPrice;//总价包干
	private String totalMainPrice;//总价包干为主，局部模拟清单报价
	private String analogPrice;//模拟清单报价
	private String quotaPrice;//定额报价
	private String otherPrice;//其它
	private String reformProjectList;//编制工程量清单
	private String reformListDate;//提交时间
	private String controlProjectList;//编制工程造价标底及最高控制价
	private String controlListDate;//提交时间
	private String afterProjectList;//工程量偏差标后核对
	private String afterListDate;//完成时间
	private String drawingAfterList;//重新度量，施工图预算标后核对
	private String planWorkPeriod;//预计工作时间段
	private String writtenComments;//施工图的错漏碰缺的书面意见反馈
	private String submitDate;//提交时间
	private String projectSettlement;//工程结算
	private String projectWorkPeriod;//预计工作时间段
	private String otherConsultContent;//其它咨询内容
	private String ohterConsContent;//其它咨询内容填写
	private String bidDrawing;//招标施工图
	private String bidDrawingDate;//提交时间
	private String bidFile;//招标文件
	private String bidFileDate;//提交时间
	private String officialDrawing;//正式施工图
	private String officialSubmitDate;//预计提交时间
	private String other;//其他

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

	public String getPreConsultFee() {
		return preConsultFee;
	}

	public void setPreConsultFee(String preConsultFee) {
		this.preConsultFee = preConsultFee;
	}

	public String getTotalDoPrice() {
		return totalDoPrice;
	}

	public void setTotalDoPrice(String totalDoPrice) {
		this.totalDoPrice = totalDoPrice;
	}

	public String getTotalMainPrice() {
		return totalMainPrice;
	}

	public void setTotalMainPrice(String totalMainPrice) {
		this.totalMainPrice = totalMainPrice;
	}

	public String getAnalogPrice() {
		return analogPrice;
	}

	public void setAnalogPrice(String analogPrice) {
		this.analogPrice = analogPrice;
	}

	public String getQuotaPrice() {
		return quotaPrice;
	}

	public void setQuotaPrice(String quotaPrice) {
		this.quotaPrice = quotaPrice;
	}

	public String getOtherPrice() {
		return otherPrice;
	}

	public void setOtherPrice(String otherPrice) {
		this.otherPrice = otherPrice;
	}

	public String getReformProjectList() {
		return reformProjectList;
	}

	public void setReformProjectList(String reformProjectList) {
		this.reformProjectList = reformProjectList;
	}

	public String getReformListDate() {
		return reformListDate;
	}

	public void setReformListDate(String reformListDate) {
		this.reformListDate = reformListDate;
	}

	public String getControlProjectList() {
		return controlProjectList;
	}

	public void setControlProjectList(String controlProjectList) {
		this.controlProjectList = controlProjectList;
	}

	public String getControlListDate() {
		return controlListDate;
	}

	public void setControlListDate(String controlListDate) {
		this.controlListDate = controlListDate;
	}

	public String getAfterProjectList() {
		return afterProjectList;
	}

	public void setAfterProjectList(String afterProjectList) {
		this.afterProjectList = afterProjectList;
	}

	public String getAfterListDate() {
		return afterListDate;
	}

	public void setAfterListDate(String afterListDate) {
		this.afterListDate = afterListDate;
	}

	public String getDrawingAfterList() {
		return drawingAfterList;
	}

	public void setDrawingAfterList(String drawingAfterList) {
		this.drawingAfterList = drawingAfterList;
	}

	public String getPlanWorkPeriod() {
		return planWorkPeriod;
	}

	public void setPlanWorkPeriod(String planWorkPeriod) {
		this.planWorkPeriod = planWorkPeriod;
	}

	public String getWrittenComments() {
		return writtenComments;
	}

	public void setWrittenComments(String writtenComments) {
		this.writtenComments = writtenComments;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getProjectSettlement() {
		return projectSettlement;
	}

	public void setProjectSettlement(String projectSettlement) {
		this.projectSettlement = projectSettlement;
	}

	public String getProjectWorkPeriod() {
		return projectWorkPeriod;
	}

	public void setProjectWorkPeriod(String projectWorkPeriod) {
		this.projectWorkPeriod = projectWorkPeriod;
	}

	public String getOtherConsultContent() {
		return otherConsultContent;
	}

	public void setOtherConsultContent(String otherConsultContent) {
		this.otherConsultContent = otherConsultContent;
	}

	public String getBidDrawing() {
		return bidDrawing;
	}

	public void setBidDrawing(String bidDrawing) {
		this.bidDrawing = bidDrawing;
	}

	public String getBidDrawingDate() {
		return bidDrawingDate;
	}

	public void setBidDrawingDate(String bidDrawingDate) {
		this.bidDrawingDate = bidDrawingDate;
	}

	public String getBidFile() {
		return bidFile;
	}

	public void setBidFile(String bidFile) {
		this.bidFile = bidFile;
	}

	public String getBidFileDate() {
		return bidFileDate;
	}

	public void setBidFileDate(String bidFileDate) {
		this.bidFileDate = bidFileDate;
	}

	public String getOfficialDrawing() {
		return officialDrawing;
	}

	public void setOfficialDrawing(String officialDrawing) {
		this.officialDrawing = officialDrawing;
	}

	public String getOfficialSubmitDate() {
		return officialSubmitDate;
	}

	public void setOfficialSubmitDate(String officialSubmitDate) {
		this.officialSubmitDate = officialSubmitDate;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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
	@Override
	public void doImport() {
		// TODO Auto-generated method stub
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContVisaUpdate visaUpdate =new ContVisaUpdate();
		if(StringUtils.isNotBlank(trustBidItem)){
			visaUpdate.setContent(trustBidItem+"工程招标造价咨询");
		}else if(StringUtils.isNotBlank(settleItem)){
			visaUpdate.setContent(settleItem+"工程结算造价咨询");
		}
		visaUpdate.setPrepareFee(new BigDecimal(FormatUtil.formatDouble(preConsultFee)));
		//签证变更预留字段，存放变更的是：面积;/;甲方委托部门;/;甲方经办人;/;咨询费比例;/;
		StringBuffer preVisaContent=new StringBuffer("");
		if(StringUtils.isNotBlank(area)){
			preVisaContent.append(area).append(";/;");
		}else{
			preVisaContent.append(";/;");
		}
		if(StringUtils.isNotBlank(partATrustPart)){
			preVisaContent.append(partATrustPart).append(";/;");
		}else{
			preVisaContent.append(";/;");
		}
		if(StringUtils.isNotBlank(partAOperator)){
			preVisaContent.append(partAOperator).append(";/;");
		}else{
			preVisaContent.append(";/;");
		}
		if(StringUtils.isNotBlank(consultRate)){
			preVisaContent.append(consultRate).append(";/;");
		}else{
			preVisaContent.append(";/;");
		}
		visaUpdate.setPreVisaContent(preVisaContent.toString());
		ResApproveInfo approveInfo=getResApproveInfo();
		visaUpdate.setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		visaUpdate.setResApproveId(approveInfo.getResApproveInfoId());
		
		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
		ledger.getContVisaUpdates().add(visaUpdate);
		ledgerManager.saveContLedger(ledger);
		//ledgerManager.updateContByRes(getContLedgerId(),approveInfo.getApproveCd()+approveInfo.getSerialNo(),null, visaUpdate, null, null);
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getOhterConsContent() {
		return ohterConsContent;
	}

	public void setOhterConsContent(String ohterConsContent) {
		this.ohterConsContent = ohterConsContent;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		//return contName;
		return projectName+"项目"+projectPeriod+"工程";
	}

}
