package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 7、 工程预付款审批表(Poroject_Pre_Pay_Apply)
 * 
 * @author waveflat
 * 
 */
public class ProjectPreLoanBill extends BaseTemplatePay {

	// 项目名称 project_name
	private String projectName;
	private String projectCd;
	// 借款单位 loan_org_name
	private String loanOrgName;
	// 合同名称 contract_name
	private String contractName;
	private String contractNo;
	// 合同总价totoal_contract_prices
	private String totoalContractPrices;
	// 预借款余额 pre_loan_balance_amt
	private String preLoanBalanceAmt;

	// A.本次拟借款draft_loan_amt
	private String draftLoanAmt;
	
	// B.已付款总额(B=D+F) paid_total_amt
	private String paidTotalAmt;
	// D.实际领用甲供价格(暂定价) real_pick_jg_interim_price
	private String realPickJgInterimPrice;
	// F.不含甲供已付款 no_contain_jg_paid_amt
	private String noContainJgPaidAmt;
	
	// C.已完产值总额(C=E+G) finish_product_amt
	private String finishProductAmt;
	// E.按进度核定的甲供材料(暂定价) proc_chk_jg_interim_price
	private String procChkJgInterimPrice;
	// G.不含甲供产值(含甲供材料已开票税额) No_contain_jg_amt
	private String noContainJgAmt;

	
	// 付款比例(含甲供)
	// 借款前B/C pcjg_loan_before_rate
	private String pcjgLoanBeforeRate;
	// 借款后(A+B)/C pcjg_loan_after_rate
	private String pcjgLoanAfterRate;
	// 甲供材料总限额 Jg_material_ total_quota_amt
	private String jgMaterialTotalQuotaAmt;
	// 约定的进度款付款比例 conv_proc_payment_rate
	private String convProcPaymentRate;
	
	
	
	// 付款比例(不含甲供)
	// 借款前F/G pncjg_loan_before_rate
	private String pncjgLoanBeforeRate;
	// 借款后(A+F)/G pncjg_loan_after_rate
	private String pncjgLoanAfterRate;

	// 需说明事项 content_desc
	private String contentDesc;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLoanOrgName() {
		return loanOrgName;
	}

	public void setLoanOrgName(String loanOrgName) {
		this.loanOrgName = loanOrgName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTotoalContractPrices() {
		return totoalContractPrices;
	}

	public void setTotoalContractPrices(String totoalContractPrices) {
		this.totoalContractPrices = totoalContractPrices;
	}


	public String getPreLoanBalanceAmt() {
		return preLoanBalanceAmt;
	}

	public void setPreLoanBalanceAmt(String preLoanBalanceAmt) {
		this.preLoanBalanceAmt = preLoanBalanceAmt;
	}

	public String getDraftLoanAmt() {
		return draftLoanAmt;
	}

	public void setDraftLoanAmt(String draftLoanAmt) {
		this.draftLoanAmt = draftLoanAmt;
	}

	public String getPaidTotalAmt() {
		return paidTotalAmt;
	}

	public void setPaidTotalAmt(String paidTotalAmt) {
		this.paidTotalAmt = paidTotalAmt;
	}

	public String getFinishProductAmt() {
		return finishProductAmt;
	}

	public void setFinishProductAmt(String finishProductAmt) {
		this.finishProductAmt = finishProductAmt;
	}

	public String getRealPickJgInterimPrice() {
		return realPickJgInterimPrice;
	}

	public void setRealPickJgInterimPrice(String realPickJgInterimPrice) {
		this.realPickJgInterimPrice = realPickJgInterimPrice;
	}

	public String getProcChkJgInterimPrice() {
		return procChkJgInterimPrice;
	}

	public void setProcChkJgInterimPrice(String procChkJgInterimPrice) {
		this.procChkJgInterimPrice = procChkJgInterimPrice;
	}

	public String getNoContainJgPaidAmt() {
		return noContainJgPaidAmt;
	}

	public void setNoContainJgPaidAmt(String noContainJgPaidAmt) {
		this.noContainJgPaidAmt = noContainJgPaidAmt;
	}

	public String getNoContainJgAmt() {
		return noContainJgAmt;
	}

	public void setNoContainJgAmt(String noContainJgAmt) {
		this.noContainJgAmt = noContainJgAmt;
	}

	public String getPcjgLoanBeforeRate() {
		return pcjgLoanBeforeRate;
	}

	public void setPcjgLoanBeforeRate(String pcjgLoanBeforeRate) {
		this.pcjgLoanBeforeRate = pcjgLoanBeforeRate;
	}

	public String getPcjgLoanAfterRate() {
		return pcjgLoanAfterRate;
	}

	public void setPcjgLoanAfterRate(String pcjgLoanAfterRate) {
		this.pcjgLoanAfterRate = pcjgLoanAfterRate;
	}

	public String getConvProcPaymentRate() {
		return convProcPaymentRate;
	}

	public void setConvProcPaymentRate(String convProcPaymentRate) {
		this.convProcPaymentRate = convProcPaymentRate;
	}

	public String getPncjgLoanBeforeRate() {
		return pncjgLoanBeforeRate;
	}

	public void setPncjgLoanBeforeRate(String pncjgLoanBeforeRate) {
		this.pncjgLoanBeforeRate = pncjgLoanBeforeRate;
	}

	public String getPncjgLoanAfterRate() {
		return pncjgLoanAfterRate;
	}

	public void setPncjgLoanAfterRate(String pncjgLoanAfterRate) {
		this.pncjgLoanAfterRate = pncjgLoanAfterRate;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getJgMaterialTotalQuotaAmt() {
	    return jgMaterialTotalQuotaAmt;
	}

	public void setJgMaterialTotalQuotaAmt(String jgMaterialTotalQuotaAmt) {
	    this.jgMaterialTotalQuotaAmt = jgMaterialTotalQuotaAmt;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		if (contractName!=null)
			return contractName+"合同";
		return null;
	}
}
