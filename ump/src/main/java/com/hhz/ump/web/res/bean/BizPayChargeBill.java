package com.hhz.ump.web.res.bean;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.pm.PmMateEntryManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

//商业付款审批表(eg: 商业公司工程改造付款审批表)
public class BizPayChargeBill extends BaseTemplate implements IAutoImport {
	private String hasLandCompany = "true";// 有地产公司,默认有
	//合同台账ID(主键)
	private String contLedgerId;
	// 合同编号 
	private String contactNo;
	//合同名称
	private String contactName;
	//RES_AUTH_TYPE表的主键
	private String resAuthTypeId;
	//类型 （RES_AUTH_TYPE的表明）
	private String displayName;
	//企划案例Id(外键)
	private String pmMateEntryId;
	//企划案例编号
	private String pmMateEntryNo;
	//活动主题
	private String activeTitle;
	//时间周期
	private String activePeriod;
	//预算费用
	private String expensesBudget;
	// 项目名称
	private String projectName;
	private String projectCd;
	// 付款单位
	private String payUnitName;
	private String payUnitCd;
	// 收款人（乙方）信息
	// 收款人名称
	// 收款人开户行
	// 收款人账号
	private String receName;
	private String receOpenBankName;
	private String receAcctNo;
	// 付款方式
	private String payTypeDesc;

	// 合同总价 已确认合同总价
	private String contractTotalAmt;
	private String confirmTotalAmt;
	//已付合同款（元）
	private String paidTotalAmt;
	/**
	 * @return the paidTotalAmt
	 */
	public String getPaidTotalAmt() {
		return paidTotalAmt;
	}

	/**
	 * @param paidTotalAmt the paidTotalAmt to set
	 */
	public void setPaidTotalAmt(String paidTotalAmt) {
		this.paidTotalAmt = paidTotalAmt;
	}

	// 本次付款申请金额（元）
	private String applyAmt;

	
	public String getContLedgerId() {
		return contLedgerId;
	}

	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	
	public String getResAuthTypeId() {
		return resAuthTypeId;
	}

	public void setResAuthTypeId(String resAuthTypeId) {
		this.resAuthTypeId = resAuthTypeId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPmMateEntryId() {
		return pmMateEntryId;
	}

	public void setPmMateEntryId(String pmMateEntryId) {
		this.pmMateEntryId = pmMateEntryId;
	}

	public String getPmMateEntryNo() {
		return pmMateEntryNo;
	}

	public void setPmMateEntryNo(String pmMateEntryNo) {
		this.pmMateEntryNo = pmMateEntryNo;
	}

	public String getActiveTitle() {
		return activeTitle;
	}

	public void setActiveTitle(String activeTitle) {
		this.activeTitle = activeTitle;
	}

	public String getActivePeriod() {
		return activePeriod;
	}

	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}

	public String getExpensesBudget() {
		return expensesBudget;
	}

	public void setExpensesBudget(String expensesBudget) {
		this.expensesBudget = expensesBudget;
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

	public String getPayUnitName() {
		return payUnitName;
	}

	public void setPayUnitName(String payUnitName) {
		this.payUnitName = payUnitName;
	}

	public String getPayUnitCd() {
		return payUnitCd;
	}

	public void setPayUnitCd(String payUnitCd) {
		this.payUnitCd = payUnitCd;
	}

	public String getReceName() {
		return receName;
	}

	public void setReceName(String receName) {
		this.receName = receName;
	}

	public String getReceOpenBankName() {
		return receOpenBankName;
	}

	public void setReceOpenBankName(String receOpenBankName) {
		this.receOpenBankName = receOpenBankName;
	}

	public String getReceAcctNo() {
		return receAcctNo;
	}

	public void setReceAcctNo(String receAcctNo) {
		this.receAcctNo = receAcctNo;
	}

	public String getPayTypeDesc() {
		return payTypeDesc;
	}

	public void setPayTypeDesc(String payTypeDesc) {
		this.payTypeDesc = payTypeDesc;
	}

	public String getContractTotalAmt() {
		return contractTotalAmt;
	}

	public void setContractTotalAmt(String contractTotalAmt) {
		this.contractTotalAmt = contractTotalAmt;
	}

	public String getConfirmTotalAmt() {
		return confirmTotalAmt;
	}

	public void setConfirmTotalAmt(String confirmTotalAmt) {
		this.confirmTotalAmt = confirmTotalAmt;
	}

	public String getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}

	public String getHasLandCompany() {
		return hasLandCompany;
	}

	public void setHasLandCompany(String hasLandCompany) {
		this.hasLandCompany = hasLandCompany;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contactName;
	}

	@Override
	public void doImport() throws Exception {
		//TODO:网批通过后，自动将记录导入目标表 
		PmMateEntryManager pmMateEntryManager = SpringContextHolder.getBean("pmMateEntryManager");
		pmMateEntryManager.importResRelation(this);
	}

	@Override
	public boolean isAutoImport() {
		return true;
	}
}
