package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 13、 工程进度款支付审批表(Project_Process_Pay_Apply) (甲供材按付款比例扣除)
 * 
 * @author waveflat
 * 
 */
public class ProjectProcessPayRemoveJgBill extends BaseTemplatePay {

	/**
	 * 项目名称
	 */
	private String projectName;
	private String projectCd;
	/**
	 * process_type 多项选择
	 */
	// 前期
	private String processTypeEarlyPeriod;
	// 建筑
	private String processTypeBuilder;
	// 安装
	private String processTypeInstall;
	// 管网
	private String processTypeNetwork;
	// 园林
	private String processTypeGardens;
	// 配套
	private String processTypeMating;

	/**
	 * 合同 名称
	 */
	private String contractName;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * planType 计划类型，多选
	 */
	// 计划内
	private String planTypeIn;
	// 计划外
	private String planTypeOut;
	// 无计划
	private String planTypeNo;

	/**
	 * 预借款余款(元)
	 */
	private String preLoanBalanceAmt;
	/**
	 * 签约单位
	 */
	private String contractSignedOrgName;
	/**
	 * 合同金额(元)
	 */
	private String contractAmt;

	/**
	 * 约定进度 付款比例(拨付率)
	 */
	private String convProcPaymentRate;
	/**
	 * 结算金额(元)
	 */
	private String footingsAmt;
	/**
	 * 产值付款比例 (累计其他扣除+累计付款)/（累计产值-累计甲供材扣除）
	 */
	private String outputValueRate;
	/**
	 * 本次支付金额(元)
	 */
	private String curPaymentAmt;

	/**
	 * 完成产值 (包含甲供材) 上期累计（元）,本期发生（元）,本期累计（元）
	 */
	/**
	 * A1 完成合同内的工作产值
	 */
	private String prodPriorIncludeAmt;
	private String prodCurIncludeAmt;
	private String prodCurIncludeAccuAmt;
	/**
	 * A2 完成的变更及签证产值
	 */
	private String prodPriorChangeAmt;
	private String prodCurChangeAmt;
	private String prodCurChangeAccuAmt;
	/**
	 * A3 完成的合同外委托产值
	 */
	private String prodPriorExtAmt;
	private String prodCurExtAmt;
	private String prodCurExtAccuAmt;
	/**
	 * A: 小计=A1+A2+A3
	 */
	// 
	private String prodPriorSubtAmt;
	private String prodCurSubtAmt;
	// prodCurAccuAmt = prodPriorSubtAmt + prodCurSubtAmt
	private String prodCurAccuAmt;

	/**
	 * 预留款项 上期累计（元）,本期发生（元）,本期累计（元）
	 * 
	 * C: 预留进度款 (A-B)*(1-拨付率）
	 */
	private String obliPriorProcAmt;
	private String obliCurProcAmt;
	private String obliCurProcAccuAmt;

	/**
	 * 甲供材扣除 (按暂定价) 上期累计（元）,本期发生（元）,本期累计（元）
	 */
	/**
	 * B1 钢筋款
	 */
	private String jgSteelPriorAmt;
	private String jgSteelCurAmt;
	private String jgSteelCurAccuAmt;
	/**
	 * B2 其他
	 */
	private String jgOtherPriorAmt;
	private String jgOtherCurAmt;
	private String jgOtherCurAccuAmt;
	/**
	 * 小计 B=B1+B2
	 */
	private String jgDeduPriorAmt;
	private String jgDeduCurAmt;
	private String jgDeduCurAccuAmt;

	/**
	 * 其它扣除 上期累计（元）,本期发生（元）,本期累计（元）
	 */

	/**
	 * D1 扣除借款
	 */
	private String deduLoanPriorAmt;
	private String deduLoanCurAmt;
	private String deduLoanCurAccuAmt;
	/**
	 * D2 扣代付的水电费
	 */
	private String deduPowerPriorAmt;
	private String deduPowerCurAmt;
	private String deduPowerCurAccuAmt;
	/**
	 * D3 扣代付的材料款、人工费
	 */
	private String deduMatePPriorAmt;
	private String deduMatePCurAmt;
	private String deduMatePCurAccuAmt;
	/**
	 * D4 工期、质量、安全违约金
	 */
	private String deduDeditPriorAmt;
	private String deduDeditCurAmt;
	private String deduDeditCurAccuAmt;
	/**
	 * D5 其它
	 */
	private String deduOtherPriorAmt;
	private String deduOtherCurAmt;
	private String deduOtherCurAccuAmt;
	/**
	 * D: 小计=D1+D2+D3+D4+D5
	 */
	private String deduPriorSubtAmt;
	private String deduCurSubtAmt;
	private String deduCurSubtAccuAmt;

	/**
	 * E: 本次实际应付=A-B-C-D 上期累计（元）,本期发生（元）,本期累计（元）
	 */
	private String realDealPriorAmt;
	private String realDealCurAmt;
	private String realDealCurAccuAmt;
	/**
	 * 需说明事项
	 */
	private String contentDesc;

	/**
	 * 实际累计已付金额（不含C,D，对应E）
	 */
	private String totalAlreadyPayment;

	/**
	 * 发票金额   上期累计（元）,本期发生（元）,本期累计（元）
	 */
	private String receiptAmoutLastPeriod;
	private String receiptAmoutCurPeriod;
	private String receiptAmoutCurPeriodTotal;


	//当月工程造价产值签证汇总表和明细表
	private String outputTotalFileId;
	private String outputDetailFileId;
	
	public String getProjectName() {
		return projectName;
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
}
