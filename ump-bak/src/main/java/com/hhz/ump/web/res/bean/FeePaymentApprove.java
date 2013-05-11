package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 设计费 / 勘察、测绘费、监理费付款审批表
 * 
 * @author baolm
 *         2011-04-26
 */
public class FeePaymentApprove extends BaseContLedgerTemplate {
	private String sendCenter1;// 设计管理中心
	private String sendCenter2;// 技术管理中心
	/**
	 * 合同台账存放的网批CD
	 */
	private String resApproveCd;
	/**
	 * 合同台账存放的网批ID
	 */
	private String resApproveId;

	/**
	 * 合同编号
	 */
	private String contNo;

	/**
	 * 合同名称
	 */
	private String contName;

	/**
	 * 项目cd
	 */
	private String projectCd;

	/**
	 * 乙方(收款人)
	 */
	private String partB;

	/**
	 * 合同总价
	 */
	private String totalPrice;

	/**
	 * 付款方式
	 */
	private String payWay;

	/**
	 * 已确认合同总价
	 */
	private String updateTotal;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * ( )期
	 */
	private String projectPeriod;

	/**
	 * 付款单位
	 */
	private String paymentUnit;

	/**
	 * 收款人开户行
	 */
	private String payeeOpenBankNo;

	/**
	 * 收款人账号
	 */
	private String payeeAccountNo;

	/**
	 * 本次付款前已经支付(元)
	 */
	private String currentPayBefore;

	/**
	 * 本次付款前支付比例%
	 */
	private String payRateBefore;

	/**
	 * 本次付款理由
	 */
	private String currentPayReason;

	/**
	 * 本次付款期数
	 */
	private String currentPayPeriod;

	/**
	 * 付款依据
	 */
	private String payDependence;

	/**
	 * □设计成果审批表
	 */
	private String payDependence1;

	/**
	 * □图纸签收表
	 */
	private String payDependence2;

	/**
	 * □白皮书
	 */
	private String payDependence3;

	/**
	 * □材料封样
	 */
	private String payDependence4;

	/**
	 * 本次付款金额(元)
	 */
	private String currentPayThis;

	/**
	 * 本次付款比例
	 */
	private String payRateThis;

	/**
	 * 本次付款后付款总额(元)
	 */
	private String currentPayTotal;

	/**
	 * 本次付款后支付比例%
	 */
	private String payRateTotal;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 付款依据附件
	 */
	private String payDependenceId;

	// 设计成果审批表
	private String designApproveSheet;

	// 图纸签收表
	private String blueprintSignSheet;

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

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getUpdateTotal() {
		return updateTotal;
	}

	public void setUpdateTotal(String updateTotal) {
		this.updateTotal = updateTotal;
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

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public String getPayeeOpenBankNo() {
		return payeeOpenBankNo;
	}

	public void setPayeeOpenBankNo(String payeeOpenBankNo) {
		this.payeeOpenBankNo = payeeOpenBankNo;
	}

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getCurrentPayReason() {
		return currentPayReason;
	}

	public void setCurrentPayReason(String currentPayReason) {
		this.currentPayReason = currentPayReason;
	}

	public String getCurrentPayPeriod() {
		return currentPayPeriod;
	}

	public void setCurrentPayPeriod(String currentPayPeriod) {
		this.currentPayPeriod = currentPayPeriod;
	}

	public String getPayDependence() {
		return payDependence;
	}

	public void setPayDependence(String payDependence) {
		this.payDependence = payDependence;
	}

	public String getPayDependence1() {
		return payDependence1;
	}

	public void setPayDependence1(String payDependence1) {
		this.payDependence1 = payDependence1;
	}

	public String getPayDependence2() {
		return payDependence2;
	}

	public void setPayDependence2(String payDependence2) {
		this.payDependence2 = payDependence2;
	}

	public String getPayDependence3() {
		return payDependence3;
	}

	public void setPayDependence3(String payDependence3) {
		this.payDependence3 = payDependence3;
	}

	public String getPayDependence4() {
		return payDependence4;
	}

	public void setPayDependence4(String payDependence4) {
		this.payDependence4 = payDependence4;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayDependenceId() {
		return payDependenceId;
	}

	public void setPayDependenceId(String payDependenceId) {
		this.payDependenceId = payDependenceId;
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
		return contName;
	}

	@Override
	public void doImport() {
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContPay contPay = new ContPay();
		ResApproveInfo approveInfo = getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd() + approveInfo.getSerialNo());
		resApproveId = approveInfo.getResApproveInfoId();
		contPay.setResApproveCd(resApproveCd);
		contPay.setResApproveId(resApproveId);
		contPay.setCompleteNum(new BigDecimal(FormatUtil.formatDouble(currentPayThis)));
		contPay.setCurrentPay(new BigDecimal(FormatUtil.formatDouble(currentPayThis)));
		contLedgerManager.updateContByRes(getContLedgerId(), resApproveCd, "", null, null, contPay);
	}

	@Override
	public boolean isAutoImport() {
		// 批完直接导入台账
		return true;
	}

	public String getCurrentPayBefore() {
		return currentPayBefore;
	}

	public void setCurrentPayBefore(String currentPayBefore) {
		this.currentPayBefore = currentPayBefore;
	}

	public String getPayRateBefore() {
		return payRateBefore;
	}

	public void setPayRateBefore(String payRateBefore) {
		this.payRateBefore = payRateBefore;
	}

	public String getCurrentPayThis() {
		return currentPayThis;
	}

	public void setCurrentPayThis(String currentPayThis) {
		this.currentPayThis = currentPayThis;
	}

	public String getPayRateThis() {
		return payRateThis;
	}

	public void setPayRateThis(String payRateThis) {
		this.payRateThis = payRateThis;
	}

	public String getCurrentPayTotal() {
		return currentPayTotal;
	}

	public void setCurrentPayTotal(String currentPayTotal) {
		this.currentPayTotal = currentPayTotal;
	}

	public String getPayRateTotal() {
		return payRateTotal;
	}

	public void setPayRateTotal(String payRateTotal) {
		this.payRateTotal = payRateTotal;
	}

	/**
	 * @return the designApproveSheet
	 */
	public String getDesignApproveSheet() {
		return designApproveSheet;
	}

	/**
	 * @param designApproveSheet
	 *            the designApproveSheet to set
	 */
	public void setDesignApproveSheet(String designApproveSheet) {
		this.designApproveSheet = designApproveSheet;
	}

	/**
	 * @return the blueprintSignSheet
	 */
	public String getBlueprintSignSheet() {
		return blueprintSignSheet;
	}

	/**
	 * @param blueprintSignSheet
	 *            the blueprintSignSheet to set
	 */
	public void setBlueprintSignSheet(String blueprintSignSheet) {
		this.blueprintSignSheet = blueprintSignSheet;
	}

	public String getSendCenter1() {
		return sendCenter1;
	}

	public void setSendCenter1(String sendCenter1) {
		this.sendCenter1 = sendCenter1;
	}

	public String getSendCenter2() {
		return sendCenter2;
	}

	public void setSendCenter2(String sendCenter2) {
		this.sendCenter2 = sendCenter2;
	}


}
