package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 现场变更审批表
 * @author Administrator
 *
 */
public class SpotUpdateApprove extends BaseContLedgerTemplate {

	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 变更编号
	 */
	private String visaNo;
	/**
	 * 合同名称
	 */
	private String contName;
	/**
	 * 乙方
	 */
	private String partB;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 变更内容
	 */
	private String visaContent;
	
	private String visaCause;//变更原因
	private String designWrong;//设计错漏  变更原因
	private String saleCause;//招商、销售原因
	private String constructCause;//施工原因
	private String partACause;//甲方原因
	private String otherCause;//其它
	private String period;
	/**
	 * 拟签发变更单编号
	 */
	private String issueVisaNo;
	/**
	 * 合同总价
	 */
	private String totalPrice;
	/**
	 * 已确认合同总价
	 */
	private String updateTotal;
	/**
	 * 已累计变更预估费用（元）
	 */
	private String prepareFeeTotal;
	/**
	 * 已预估变更比例
	 */
	private String preFeeTotRate;
	/**
	 * 本次预估费用（元）
	 */
	private String prepareFee;
	/**
	 * 本次预估变更比例
	 */
	private String preFeeRate;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 拟签发指令单
	 */
	private String issueVisaInstrId;
	/**
	 * 变更预算
	 */
	private String updateBudgetId;
	/**
	 * 提出部门的文件
	 */
	private String adjureFileId;
	
	private String hotel;//与酒店有关
	private String trade;//与行业有关
	private String estate;//与物业有关
	/**
	 * 合同台账存放的网批CD
	 */
	private String resApproveCd;
	/**
	 * 合同台账存放的网批ID
	 */
	private String resApproveId;
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getVisaNo() {
		return visaNo;
	}
	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getVisaContent() {
		return visaContent;
	}
	public void setVisaContent(String visaContent) {
		this.visaContent = visaContent;
	}
	public String getVisaCause() {
		return visaCause;
	}
	public void setVisaCause(String visaCause) {
		this.visaCause = visaCause;
	}
	public String getDesignWrong() {
		return designWrong;
	}
	public void setDesignWrong(String designWrong) {
		this.designWrong = designWrong;
	}
	public String getSaleCause() {
		return saleCause;
	}
	public void setSaleCause(String saleCause) {
		this.saleCause = saleCause;
	}
	public String getConstructCause() {
		return constructCause;
	}
	public void setConstructCause(String constructCause) {
		this.constructCause = constructCause;
	}
	public String getPartACause() {
		return partACause;
	}
	public void setPartACause(String partACause) {
		this.partACause = partACause;
	}
	public String getOtherCause() {
		return otherCause;
	}
	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getIssueVisaNo() {
		return issueVisaNo;
	}
	public void setIssueVisaNo(String issueVisaNo) {
		this.issueVisaNo = issueVisaNo;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUpdateTotal() {
		return updateTotal;
	}
	public void setUpdateTotal(String updateTotal) {
		this.updateTotal = updateTotal;
	}
	public String getPrepareFeeTotal() {
		return prepareFeeTotal;
	}
	public void setPrepareFeeTotal(String prepareFeeTotal) {
		this.prepareFeeTotal = prepareFeeTotal;
	}
	public String getPreFeeTotRate() {
		return preFeeTotRate;
	}
	public void setPreFeeTotRate(String preFeeTotRate) {
		this.preFeeTotRate = preFeeTotRate;
	}
	public String getPrepareFee() {
		return prepareFee;
	}
	public void setPrepareFee(String prepareFee) {
		this.prepareFee = prepareFee;
	}
	public String getPreFeeRate() {
		return preFeeRate;
	}
	public void setPreFeeRate(String preFeeRate) {
		this.preFeeRate = preFeeRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String getHotel() {
		return hotel;
	}
	@Override
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	@Override
	public String getTrade() {
		return trade;
	}
	@Override
	public void setTrade(String trade) {
		this.trade = trade;
	}
	@Override
	public String getEstate() {
		return estate;
	}
	@Override
	public void setEstate(String estate) {
		this.estate = estate;
	}
	public String getResApproveCd() {
		return resApproveCd;
	}
	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}
	public String getResApproveId() {
		return resApproveId;
	}
	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

	public String getIssueVisaInstrId() {
		return issueVisaInstrId;
	}
	public void setIssueVisaInstrId(String issueVisaInstrId) {
		this.issueVisaInstrId = issueVisaInstrId;
	}
	public String getUpdateBudgetId() {
		return updateBudgetId;
	}
	public void setUpdateBudgetId(String updateBudgetId) {
		this.updateBudgetId = updateBudgetId;
	}
	public String getAdjureFileId() {
		return adjureFileId;
	}
	public void setAdjureFileId(String adjureFileId) {
		this.adjureFileId = adjureFileId;
	}
	@Override
	public boolean isAutoImport() {
		// TODO 自动导入合同台账
		return true;
	}
	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
		ContVisaUpdate visaUpdate = new ContVisaUpdate();
		//visaUpdate.setVisaUpdateNo(visaNo);
		visaUpdate.setContent(visaContent);
		
		//变更原因 
		if("true".equals(designWrong)){
			visaUpdate.setCause(DictContants.CONT_VISA_CONTENT_1);
		}else if("true".equals(saleCause)){
			visaUpdate.setCause(DictContants.CONT_VISA_CONTENT_2);
		}else if("true".equals(constructCause)){
			visaUpdate.setCause(DictContants.CONT_VISA_CONTENT_3);
		}else if("true".equals(partACause)){
			visaUpdate.setCause(DictContants.CONT_VISA_CONTENT_4);
		}else{
			visaUpdate.setCause(DictContants.CONT_VISA_CONTENT_5);
		}
		
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		visaUpdate.setResApproveCd(resApproveCd);
		visaUpdate.setResApproveId(resApproveId);
		visaUpdate.setPrepareFee(new BigDecimal(FormatUtil.formatDouble(prepareFee)));//本次预估费用
		
		int visaCount = 0;
		if(ledger.getContVisaUpdates() != null){
			visaCount = ledger.getContVisaUpdates().size();
		}
		
		
//		if(contLedger.getContVisaUpdates()!=null){
//			  contVisaUpdate.setVisaUpdateNo(ledgerCd+XOBG+(contLedger.getContVisaUpdates().size()+1));
//		}else{
//			   contVisaUpdate.setVisaUpdateNo(ledgerCd+XOBG+1);
//		}
		String visaUpdateNo = StringUtils.leftPad(String.valueOf(visaCount+1),4, "0");//4位，不够补0;
		visaUpdate.setVisaUpdateNo(visaUpdateNo);
//		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
//		ledger.getContVisaUpdates().add(visaUpdate);
//		ledgerManager.saveContLedger(ledger);
		//ledgerManager.updateContByRes(getContLedgerId(),resApproveCd, null, visaUpdate, null, null);
		ledger.getContVisaUpdates().add(visaUpdate);
		ledgerManager.saveContLedger(ledger);
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return visaContent;
	}
}
