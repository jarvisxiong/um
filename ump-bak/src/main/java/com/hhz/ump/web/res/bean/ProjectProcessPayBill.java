package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 8、 工程进度款支付审批表(Project_Process_Pay_Apply) (甲供材按100%扣除)
 * 
 * @author waveflat
 * 
 */
public class ProjectProcessPayBill extends BaseTemplatePay {
	

	// []土地 []前期 []基础 []建安 []配套 process_type
	// 合同名称 contract_name
	// []计划内 []计划内 []无计划 plan_type
	// 合同编号 contract_no
	// 预借款余款(元) pre_loan_balance_amt
	// 签约单位 contract_signed_org_name
	// 合同金额(元) contract_amt
	// 约定进度 付款比例 conv_proc_payment_rate
	// 结算金额(元) footings_amt
	// 产值付款比例 (累计甲供材扣除+累计付款)/累计产值output_value_rate
	// 本次支付金额(元) cur_payment_amt
	

	// 完成产值 (包含甲供材) 上期累计(元) 本期发生(元) 本期累计(元)
	// A1 完成合同内的工作产值 prod_prior_include_ amt prod_cur_include_amt
	// -prod_cur_include_accu_amt
	// A2 完成的变更及签证产值 prod_prior_change_ amt prod_cur_change_amt
	// -prod_cur_change_accu_amt
	// A3 完成的合同外委托产值 prod_prior_ext_amt prod_cur_ext_amt -prod_cur_ext_accu_amt
	// A: 小计=A1+A2+A3 -prod_prior_subt_amt
	// -prod_cur_prod_subt_amt　-prod_cur_prod_accu_amt

	// 预留款项
	// B: 预留进度款 -obli_prior_proc_amt -obli_cur_proc_amt -obli_cur_proc_accu_amt

	// 甲供材扣除(按暂定价)
	// C1 钢筋款 Jg_steel_prior_amt Jg_steel_cur _amt -Jg_steel_cur_accu_amt
	// C2 其它 Jg_other_prior_amt Jg_other_cur_amt -Jg_other_cur_accu_amt
	// C: 小计=C1+C2 -jg_dedu_prior_amt -jg_ dedu_cur_amt -jg_ dedu_cur_accu_amt

	// 其它扣除
	// D1 扣除借款 dedu_loan_prior_amt deM_loan_cur_amt -dedu_loan_cur_accu_amt
	// D2 扣代付的水电费 dedu_power_prior_amt
	// dedu_power_cur_amt　-dedu_power_cur_accu_amt
	// D3 扣代付的材料款、人工费 dedu_mate_p_prior_amt dedu_mate_p_cur_amt
	// -dedu_mate_p_cur_accu_amt
	// D4 工期、质量、安全违约金 dedu_dedit_prior_amt dedu_dedit_cur_amt
	// -dedu_dedit_cur_accu_amt
	// D5 其它 dedu_other_prior_amt dedu_other_cur_amt -dedu_other_cur_accu_amt
	// D: 小计=D1+D2+D3+D4+D5 -dedu_prior_subt_amt -dedu_cur_subt_amt
	// -dedu_cur_subt_accu_amt

	// E E: 本次实际应付=A-B-C-D -real_deal_prior_amt -real_deal_cur_amt
	// -real_deal_cur_accu_amt
	// 需说明事项 content_desc

	private String projectName;
	private String projectCd;
	private String processTypeEarlyPeriod;
	private String processTypeBuilder;
	private String processTypeInstall;
	private String processTypeNetwork;
	private String processTypeGardens;
	private String processTypeMating;

	private String contractName;
	private String planTypeCd;
	private String planTypeIn;
	private String planTypeOut;
	private String planTypeNo;
	private String contractNo;
	private String preLoanBalanceAmt;
	private String contractSignedOrgName;
	private String contractAmt;
	private String convProcPaymentRate;
	private String footingsAmt;
	private String outputValueRate;
	private String curPaymentAmt;

	private String prodPriorIncludeAmt;
	private String prodCurIncludeAmt;
	private String prodCurIncludeAccuAmt;
	private String prodPriorChangeAmt;
	private String prodCurChangeAmt;
	private String prodCurChangeAccuAmt;
	private String prodPriorExtAmt;
	private String prodCurExtAmt;
	private String prodCurExtAccuAmt;
	private String prodPriorSubtAmt;
	private String prodCurSubtAmt;
	private String prodCurAccuAmt;

	private String obliPriorProcAmt;
	private String obliCurProcAmt;
	private String obliCurProcAccuAmt;

	 
	private String jgSteelPriorAmt;
	private String jgSteelCurAmt;
	private String jgSteelCurAccuAmt;
	private String jgOtherPriorAmt;
	private String jgOtherCurAmt;
	private String jgOtherCurAccuAmt;
	private String jgDeduPriorAmt;
	private String jgDeduCurAmt;
	private String jgDeduCurAccuAmt;

	private String deduLoanPriorAmt;
	private String deduLoanCurAmt;
	private String deduLoanCurAccuAmt;
	private String deduPowerPriorAmt;
	private String deduPowerCurAmt;
	private String deduPowerCurAccuAmt;
	private String deduMatePPriorAmt;
	private String deduMatePCurAmt;
	private String deduMatePCurAccuAmt;
	private String deduDeditPriorAmt;
	private String deduDeditCurAmt;
	private String deduDeditCurAccuAmt;
	private String deduOtherPriorAmt;
	private String deduOtherCurAmt;
	private String deduOtherCurAccuAmt;
	private String deduPriorSubtAmt;
	private String deduCurSubtAmt;
	private String deduCurSubtAccuAmt;

	private String realDealPriorAmt;
	private String realDealCurAmt;
	private String realDealCurAccuAmt;
	private String contentDesc;
	
	private String totalAlreadyPayment;
	private String receiptAmoutLastPeriod;
	private String receiptAmoutCurPeriod;
	private String receiptAmoutCurPeriodTotal;
	
	//当月工程造价产值签证汇总表和明细表
	private String outputTotalFileId;
	private String outputDetailFileId;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPlanTypeIn() {
		return planTypeIn;
	}

	public void setPlanTypeIn(String planTypeIn) {
		this.planTypeIn = planTypeIn;
	}

	public String getPlanTypeOut() {
		return planTypeOut;
	}

	public void setPlanTypeOut(String planTypeOut) {
		this.planTypeOut = planTypeOut;
	}

	public String getPlanTypeNo() {
		return planTypeNo;
	}

	public void setPlanTypeNo(String planTypeNo) {
		this.planTypeNo = planTypeNo;
	}

	public String getProcessTypeEarlyPeriod() {
		return processTypeEarlyPeriod;
	}

	public void setProcessTypeEarlyPeriod(String processTypeEarlyPeriod) {
		this.processTypeEarlyPeriod = processTypeEarlyPeriod;
	}

	public String getProcessTypeMating() {
		return processTypeMating;
	}

	public void setProcessTypeMating(String processTypeMating) {
		this.processTypeMating = processTypeMating;
	}

	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getPreLoanBalanceAmt() {
		return preLoanBalanceAmt;
	}
	public void setPreLoanBalanceAmt(String preLoanBalanceAmt) {
		this.preLoanBalanceAmt = preLoanBalanceAmt;
	}
	public String getContractSignedOrgName() {
		return contractSignedOrgName;
	}
	public void setContractSignedOrgName(String contractSignedOrgName) {
		this.contractSignedOrgName = contractSignedOrgName;
	}
	public String getContractAmt() {
		return contractAmt;
	}
	public void setContractAmt(String contractAmt) {
		this.contractAmt = contractAmt;
	}

	public String getConvProcPaymentRate() {
		return convProcPaymentRate;
	}

	public void setConvProcPaymentRate(String convProcPaymentRate) {
		this.convProcPaymentRate = convProcPaymentRate;
	}
	public String getFootingsAmt() {
		return footingsAmt;
	}
	public void setFootingsAmt(String footingsAmt) {
		this.footingsAmt = footingsAmt;
	}
	public String getOutputValueRate() {
		return outputValueRate;
	}
	public void setOutputValueRate(String outputValueRate) {
		this.outputValueRate = outputValueRate;
	}
	@Override
	public String getCurPaymentAmt() {
		return curPaymentAmt;
	}
	@Override
	public void setCurPaymentAmt(String curPaymentAmt) {
		this.curPaymentAmt = curPaymentAmt;
	}
	public String getProdPriorIncludeAmt() {
		return prodPriorIncludeAmt;
	}
	public void setProdPriorIncludeAmt(String prodPriorIncludeAmt) {
		this.prodPriorIncludeAmt = prodPriorIncludeAmt;
	}
	public String getProdCurIncludeAmt() {
		return prodCurIncludeAmt;
	}
	public void setProdCurIncludeAmt(String prodCurIncludeAmt) {
		this.prodCurIncludeAmt = prodCurIncludeAmt;
	}
	public String getProdCurIncludeAccuAmt() {
		return prodCurIncludeAccuAmt;
	}
	public void setProdCurIncludeAccuAmt(String prodCurIncludeAccuAmt) {
		this.prodCurIncludeAccuAmt = prodCurIncludeAccuAmt;
	}
	public String getProdPriorChangeAmt() {
		return prodPriorChangeAmt;
	}
	public void setProdPriorChangeAmt(String prodPriorChangeAmt) {
		this.prodPriorChangeAmt = prodPriorChangeAmt;
	}
	public String getProdCurChangeAmt() {
		return prodCurChangeAmt;
	}
	public void setProdCurChangeAmt(String prodCurChangeAmt) {
		this.prodCurChangeAmt = prodCurChangeAmt;
	}

	public String getProdCurChangeAccuAmt() {
		return prodCurChangeAccuAmt;
	}

	public void setProdCurChangeAccuAmt(String prodCurChangeAccuAmt) {
		this.prodCurChangeAccuAmt = prodCurChangeAccuAmt;
	}

	public String getProdPriorExtAmt() {
		return prodPriorExtAmt;
	}
	public void setProdPriorExtAmt(String prodPriorExtAmt) {
		this.prodPriorExtAmt = prodPriorExtAmt;
	}
	public String getProdCurExtAmt() {
		return prodCurExtAmt;
	}
	public void setProdCurExtAmt(String prodCurExtAmt) {
		this.prodCurExtAmt = prodCurExtAmt;
	}
	public String getProdCurExtAccuAmt() {
		return prodCurExtAccuAmt;
	}
	public void setProdCurExtAccuAmt(String prodCurExtAccuAmt) {
		this.prodCurExtAccuAmt = prodCurExtAccuAmt;
	}
	public String getProdPriorSubtAmt() {
		return prodPriorSubtAmt;
	}
	public void setProdPriorSubtAmt(String prodPriorSubtAmt) {
		this.prodPriorSubtAmt = prodPriorSubtAmt;
	}

	public String getProdCurSubtAmt() {
		return prodCurSubtAmt;
	}

	public void setProdCurSubtAmt(String prodCurSubtAmt) {
		this.prodCurSubtAmt = prodCurSubtAmt;
	}

	public String getProdCurAccuAmt() {
		return prodCurAccuAmt;
	}

	public void setProdCurAccuAmt(String prodCurAccuAmt) {
		this.prodCurAccuAmt = prodCurAccuAmt;
	}

	public String getDeduMatePPriorAmt() {
		return deduMatePPriorAmt;
	}

	public void setDeduMatePPriorAmt(String deduMatePPriorAmt) {
		this.deduMatePPriorAmt = deduMatePPriorAmt;
	}

	public String getObliPriorProcAmt() {
		return obliPriorProcAmt;
	}
	public void setObliPriorProcAmt(String obliPriorProcAmt) {
		this.obliPriorProcAmt = obliPriorProcAmt;
	}
	public String getObliCurProcAmt() {
		return obliCurProcAmt;
	}
	public void setObliCurProcAmt(String obliCurProcAmt) {
		this.obliCurProcAmt = obliCurProcAmt;
	}
	public String getObliCurProcAccuAmt() {
		return obliCurProcAccuAmt;
	}
	public void setObliCurProcAccuAmt(String obliCurProcAccuAmt) {
		this.obliCurProcAccuAmt = obliCurProcAccuAmt;
	}
	public String getJgSteelPriorAmt() {
		return jgSteelPriorAmt;
	}
	public void setJgSteelPriorAmt(String jgSteelPriorAmt) {
		this.jgSteelPriorAmt = jgSteelPriorAmt;
	}
	public String getJgSteelCurAmt() {
		return jgSteelCurAmt;
	}
	public void setJgSteelCurAmt(String jgSteelCurAmt) {
		this.jgSteelCurAmt = jgSteelCurAmt;
	}
	public String getJgSteelCurAccuAmt() {
		return jgSteelCurAccuAmt;
	}
	public void setJgSteelCurAccuAmt(String jgSteelCurAccuAmt) {
		this.jgSteelCurAccuAmt = jgSteelCurAccuAmt;
	}
	public String getJgOtherPriorAmt() {
		return jgOtherPriorAmt;
	}
	public void setJgOtherPriorAmt(String jgOtherPriorAmt) {
		this.jgOtherPriorAmt = jgOtherPriorAmt;
	}
	public String getJgOtherCurAmt() {
		return jgOtherCurAmt;
	}
	public void setJgOtherCurAmt(String jgOtherCurAmt) {
		this.jgOtherCurAmt = jgOtherCurAmt;
	}
	public String getJgOtherCurAccuAmt() {
		return jgOtherCurAccuAmt;
	}
	public void setJgOtherCurAccuAmt(String jgOtherCurAccuAmt) {
		this.jgOtherCurAccuAmt = jgOtherCurAccuAmt;
	}
	public String getJgDeduPriorAmt() {
		return jgDeduPriorAmt;
	}
	public void setJgDeduPriorAmt(String jgDeduPriorAmt) {
		this.jgDeduPriorAmt = jgDeduPriorAmt;
	}
	public String getJgDeduCurAmt() {
		return jgDeduCurAmt;
	}
	public void setJgDeduCurAmt(String jgDeduCurAmt) {
		this.jgDeduCurAmt = jgDeduCurAmt;
	}
	public String getJgDeduCurAccuAmt() {
		return jgDeduCurAccuAmt;
	}
	public void setJgDeduCurAccuAmt(String jgDeduCurAccuAmt) {
		this.jgDeduCurAccuAmt = jgDeduCurAccuAmt;
	}
	public String getDeduLoanPriorAmt() {
		return deduLoanPriorAmt;
	}
	public void setDeduLoanPriorAmt(String deduLoanPriorAmt) {
		this.deduLoanPriorAmt = deduLoanPriorAmt;
	}
	public String getDeduLoanCurAmt() {
		return deduLoanCurAmt;
	}
	public void setDeduLoanCurAmt(String deduLoanCurAmt) {
		this.deduLoanCurAmt = deduLoanCurAmt;
	}
	public String getDeduLoanCurAccuAmt() {
		return deduLoanCurAccuAmt;
	}
	public void setDeduLoanCurAccuAmt(String deduLoanCurAccuAmt) {
		this.deduLoanCurAccuAmt = deduLoanCurAccuAmt;
	}
	public String getDeduPowerPriorAmt() {
		return deduPowerPriorAmt;
	}
	public void setDeduPowerPriorAmt(String deduPowerPriorAmt) {
		this.deduPowerPriorAmt = deduPowerPriorAmt;
	}
	public String getDeduPowerCurAmt() {
		return deduPowerCurAmt;
	}
	public void setDeduPowerCurAmt(String deduPowerCurAmt) {
		this.deduPowerCurAmt = deduPowerCurAmt;
	}
	public String getDeduPowerCurAccuAmt() {
		return deduPowerCurAccuAmt;
	}
	public void setDeduPowerCurAccuAmt(String deduPowerCurAccuAmt) {
		this.deduPowerCurAccuAmt = deduPowerCurAccuAmt;
	}

	public String getDeduMatePCurAmt() {
		return deduMatePCurAmt;
	}
	public void setDeduMatePCurAmt(String deduMatePCurAmt) {
		this.deduMatePCurAmt = deduMatePCurAmt;
	}
	public String getDeduMatePCurAccuAmt() {
		return deduMatePCurAccuAmt;
	}
	public void setDeduMatePCurAccuAmt(String deduMatePCurAccuAmt) {
		this.deduMatePCurAccuAmt = deduMatePCurAccuAmt;
	}
	public String getDeduDeditPriorAmt() {
		return deduDeditPriorAmt;
	}
	public void setDeduDeditPriorAmt(String deduDeditPriorAmt) {
		this.deduDeditPriorAmt = deduDeditPriorAmt;
	}
	public String getDeduDeditCurAmt() {
		return deduDeditCurAmt;
	}
	public void setDeduDeditCurAmt(String deduDeditCurAmt) {
		this.deduDeditCurAmt = deduDeditCurAmt;
	}
	public String getDeduDeditCurAccuAmt() {
		return deduDeditCurAccuAmt;
	}
	public void setDeduDeditCurAccuAmt(String deduDeditCurAccuAmt) {
		this.deduDeditCurAccuAmt = deduDeditCurAccuAmt;
	}
	public String getDeduOtherPriorAmt() {
		return deduOtherPriorAmt;
	}
	public void setDeduOtherPriorAmt(String deduOtherPriorAmt) {
		this.deduOtherPriorAmt = deduOtherPriorAmt;
	}
	public String getDeduOtherCurAmt() {
		return deduOtherCurAmt;
	}
	public void setDeduOtherCurAmt(String deduOtherCurAmt) {
		this.deduOtherCurAmt = deduOtherCurAmt;
	}
	public String getDeduOtherCurAccuAmt() {
		return deduOtherCurAccuAmt;
	}
	public void setDeduOtherCurAccuAmt(String deduOtherCurAccuAmt) {
		this.deduOtherCurAccuAmt = deduOtherCurAccuAmt;
	}
	public String getDeduPriorSubtAmt() {
		return deduPriorSubtAmt;
	}
	public void setDeduPriorSubtAmt(String deduPriorSubtAmt) {
		this.deduPriorSubtAmt = deduPriorSubtAmt;
	}
	public String getDeduCurSubtAmt() {
		return deduCurSubtAmt;
	}
	public void setDeduCurSubtAmt(String deduCurSubtAmt) {
		this.deduCurSubtAmt = deduCurSubtAmt;
	}
	public String getDeduCurSubtAccuAmt() {
		return deduCurSubtAccuAmt;
	}
	public void setDeduCurSubtAccuAmt(String deduCurSubtAccuAmt) {
		this.deduCurSubtAccuAmt = deduCurSubtAccuAmt;
	}
	public String getRealDealPriorAmt() {
		return realDealPriorAmt;
	}
	public void setRealDealPriorAmt(String realDealPriorAmt) {
		this.realDealPriorAmt = realDealPriorAmt;
	}
	public String getRealDealCurAmt() {
		return realDealCurAmt;
	}
	public void setRealDealCurAmt(String realDealCurAmt) {
		this.realDealCurAmt = realDealCurAmt;
	}
	public String getRealDealCurAccuAmt() {
		return realDealCurAccuAmt;
	}
	public void setRealDealCurAccuAmt(String realDealCurAccuAmt) {
		this.realDealCurAccuAmt = realDealCurAccuAmt;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getTotalAlreadyPayment() {
	    return totalAlreadyPayment;
	}

	public void setTotalAlreadyPayment(String totalAlreadyPayment) {
	    this.totalAlreadyPayment = totalAlreadyPayment;
	}

	public String getReceiptAmoutLastPeriod() {
	    return receiptAmoutLastPeriod;
	}

	public void setReceiptAmoutLastPeriod(String receiptAmoutLastPeriod) {
	    this.receiptAmoutLastPeriod = receiptAmoutLastPeriod;
	}

	public String getReceiptAmoutCurPeriod() {
	    return receiptAmoutCurPeriod;
	}

	public void setReceiptAmoutCurPeriod(String receiptAmoutCurPeriod) {
	    this.receiptAmoutCurPeriod = receiptAmoutCurPeriod;
	}

	public String getReceiptAmoutCurPeriodTotal() {
	    return receiptAmoutCurPeriodTotal;
	}

	public void setReceiptAmoutCurPeriodTotal(String receiptAmoutCurPeriodTotal) {
	    this.receiptAmoutCurPeriodTotal = receiptAmoutCurPeriodTotal;
	}

	public String getProcessTypeBuilder() {
		return processTypeBuilder;
	}

	public void setProcessTypeBuilder(String processTypeBuilder) {
		this.processTypeBuilder = processTypeBuilder;
	}

	public String getProcessTypeInstall() {
		return processTypeInstall;
	}

	public void setProcessTypeInstall(String processTypeInstall) {
		this.processTypeInstall = processTypeInstall;
	}

	public String getProcessTypeNetwork() {
		return processTypeNetwork;
	}

	public void setProcessTypeNetwork(String processTypeNetwork) {
		this.processTypeNetwork = processTypeNetwork;
	}

	public String getProcessTypeGardens() {
		return processTypeGardens;
	}

	public void setProcessTypeGardens(String processTypeGardens) {
		this.processTypeGardens = processTypeGardens;
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

	public String getOutputTotalFileId() {
		return outputTotalFileId;
	}

	public void setOutputTotalFileId(String outputTotalFileId) {
		this.outputTotalFileId = outputTotalFileId;
	}

	public String getOutputDetailFileId() {
		return outputDetailFileId;
	}

	public void setOutputDetailFileId(String outputDetailFileId) {
		this.outputDetailFileId = outputDetailFileId;
	}
}
