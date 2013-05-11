package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContGuaranteeManager;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContGuarantee;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 工程、材料设备质保金付款审批表
 * @author baolm
 *
 * 2011-04-21
 */
public class MaterialMarginPayment extends BaseContLedgerTemplate {
	private String hasEstate;//有商业公司
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
	 * 本次应付质保金(元)
	 */
	private String marginPay;
	
	/**
	 * 实际预留质保金
	 */
	private String fuRealGuarantee;
	
	/**
	 * 扣：保修应扣款
	 */
	private String fuCutPayment;
	
	/**
	 * 扣：预留保修金
	 */
	private String fuLeaveGuarantee;
	
	/**
	 * 本次应付质保金
	 */
	private String fuPayMoeny;
	
	/**
	 * 需说明事项
	 */
	private String description;
	
	/**
	 * 工程或材料设备结算款付款审批表
	 */
	private String mateSettleId;
	
	/**
	 * 结算审批表
	 */
	private String settleApproveId;
	
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

	public String getMarginPay() {
		return marginPay;
	}

	public void setMarginPay(String marginPay) {
		this.marginPay = marginPay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFuRealGuarantee() {
		return fuRealGuarantee;
	}

	public void setFuRealGuarantee(String fuRealGuarantee) {
		this.fuRealGuarantee = fuRealGuarantee;
	}

	public String getFuPayMoeny() {
		return fuPayMoeny;
	}

	public void setFuPayMoeny(String fuPayMoeny) {
		this.fuPayMoeny = fuPayMoeny;
	}

	public String getFuLeaveGuarantee() {
		return fuLeaveGuarantee;
	}

	public void setFuLeaveGuarantee(String fuLeaveGuarantee) {
		this.fuLeaveGuarantee = fuLeaveGuarantee;
	}

	public String getFuCutPayment() {
		return fuCutPayment;
	}

	public void setFuCutPayment(String fuCutPayment) {
		this.fuCutPayment = fuCutPayment;
	}

	public String getMateSettleId() {
		return mateSettleId;
	}

	public void setMateSettleId(String mateSettleId) {
		this.mateSettleId = mateSettleId;
	}

	public String getSettleApproveId() {
		return settleApproveId;
	}

	public void setSettleApproveId(String settleApproveId) {
		this.settleApproveId = settleApproveId;
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
		// TODO 需要台账实现相关功能后实现
		ContGuaranteeManager contGuaranteeManager = SpringContextHolder.getBean("contGuaranteeManager");
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContGuarantee contGuarantee = new ContGuarantee();
		contGuarantee.setResApproveCd(resApproveCd);
		contGuarantee.setResApproveId(resApproveId);
		contGuarantee.setLeaveGuarantee(new BigDecimal(FormatUtil.formatDouble(fuLeaveGuarantee)));
		contGuarantee.setRealGuarantee(new BigDecimal(FormatUtil.formatDouble(fuRealGuarantee)));
		contGuarantee.setCutPayment(new BigDecimal(FormatUtil.formatDouble(fuCutPayment)));
		contGuarantee.setPayMoeny(new BigDecimal(FormatUtil.formatDouble(fuPayMoeny)));
		ContLedger ledger = contLedgerManager.getEntity(getContLedgerId());
		contGuarantee.setContLedger(ledger);
		contGuaranteeManager.saveContGuarantee(contGuarantee);
	}

	@Override
	public boolean isAutoImport() {
		//批完直接导入台账
		return true;
	}

	public String getHasEstate() {
		return hasEstate;
	}

	public void setHasEstate(String hasEstate) {
		this.hasEstate = hasEstate;
	}

}
