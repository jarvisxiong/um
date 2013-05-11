package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 材料设备款付款审批表
 * @author baolm
 *
 * 2011-04-21
 */
public class MaterialPayment extends BaseContLedgerTemplate {
	
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
	 * 保修期开始日期
	 */
	private String guarBeginDate;
	
	/**
	 * 保修期结束日期
	 */
	private String guarEndDate;
	
	/**
	 * 结算价
	 */
	private String clearPrice;
	
	/**
	 * 变更后的合同总价
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
	 * 本次付款申请金额(元)
	 */
	private String applyAmount;
	
	/**
	 * 已供货合价
	 */
	private String completeNumThis;
	private String completeNumBefore;
	private String completeNumTotal;

	/**
	 * 付款
	 */
	private String currentPayThis;
	private String currentPayBefore;
	private String currentPayTotal;
	
	/**
	 * 付款比例%
	 */
	private String payRateThis;
	private String payRateBefore;
	private String payRateTotal;
	
	/**
	 * 施工单位领货合价
	 */
	private List<MaterialPaymentOtherProperty> otherProperties = new ArrayList<MaterialPaymentOtherProperty>();
	
	/**
	 * 需说明事项
	 */
	private String description;
	
	/**
	 * 供货、领货明细表
	 */
	private String provideDetailId;
	
	/**
	 * 三方验收凭证
	 */
	private String threeSideAcceptCertificateId;
	
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

	public String getGuarBeginDate() {
		return guarBeginDate;
	}

	public void setGuarBeginDate(String guarBeginDate) {
		this.guarBeginDate = guarBeginDate;
	}

	public String getGuarEndDate() {
		return guarEndDate;
	}

	public void setGuarEndDate(String guarEndDate) {
		this.guarEndDate = guarEndDate;
	}

	public String getClearPrice() {
		return clearPrice;
	}

	public void setClearPrice(String clearPrice) {
		this.clearPrice = clearPrice;
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

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getCompleteNumBefore() {
		return completeNumBefore;
	}

	public void setCompleteNumBefore(String completeNumBefore) {
		this.completeNumBefore = completeNumBefore;
	}

	public String getCompleteNumThis() {
		return completeNumThis;
	}

	public void setCompleteNumThis(String completeNumThis) {
		this.completeNumThis = completeNumThis;
	}

	public String getCompleteNumTotal() {
		return completeNumTotal;
	}

	public void setCompleteNumTotal(String completeNumTotal) {
		this.completeNumTotal = completeNumTotal;
	}

	public String getCurrentPayBefore() {
		return currentPayBefore;
	}

	public void setCurrentPayBefore(String currentPayBefore) {
		this.currentPayBefore = currentPayBefore;
	}

	public String getCurrentPayThis() {
		return currentPayThis;
	}

	public void setCurrentPayThis(String currentPayThis) {
		this.currentPayThis = currentPayThis;
	}

	public String getCurrentPayTotal() {
		return currentPayTotal;
	}

	public void setCurrentPayTotal(String currentPayTotal) {
		this.currentPayTotal = currentPayTotal;
	}

	public String getPayRateBefore() {
		return payRateBefore;
	}

	public void setPayRateBefore(String payRateBefore) {
		this.payRateBefore = payRateBefore;
	}

	public String getPayRateThis() {
		return payRateThis;
	}

	public void setPayRateThis(String payRateThis) {
		this.payRateThis = payRateThis;
	}

	public String getPayRateTotal() {
		return payRateTotal;
	}

	public void setPayRateTotal(String payRateTotal) {
		this.payRateTotal = payRateTotal;
	}

	public List<MaterialPaymentOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(
			List<MaterialPaymentOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvideDetailId() {
		return provideDetailId;
	}

	public void setProvideDetailId(String provideDetailId) {
		this.provideDetailId = provideDetailId;
	}

	public String getThreeSideAcceptCertificateId() {
		return threeSideAcceptCertificateId;
	}

	public void setThreeSideAcceptCertificateId(String threeSideAcceptCertificateId) {
		this.threeSideAcceptCertificateId = threeSideAcceptCertificateId;
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
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		ContPay contPay = new ContPay();
		contPay.setResApproveCd(resApproveCd);
		contPay.setResApproveId(resApproveId);
		contPay.setCompleteNum(new BigDecimal(FormatUtil.formatDouble(completeNumThis)));
//		contPay.setMatieralNum(new BigDecimal(FormatUtil.formatDouble(matieralNumThis)));
//		contPay.setCurrentAdd(new BigDecimal(FormatUtil.formatDouble(currentAddThis)));
		contPay.setCurrentPay(new BigDecimal(FormatUtil.formatDouble(currentPayThis)));
		contPay.setPayRate(new BigDecimal(FormatUtil.formatDouble(payRateThis)));
		contLedgerManager.updateContByRes(getContLedgerId(),resApproveCd, "", null, null, contPay);
	}

	@Override
	public boolean isAutoImport() {
		//批完直接导入台账
		return true;
	}

}
