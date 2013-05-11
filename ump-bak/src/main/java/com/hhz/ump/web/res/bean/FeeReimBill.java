package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>
 * 费用报销审批表
 * </p>
 * 
 * @author huangjian
 * @create 2011-9-20
 */
public class FeeReimBill extends BaseTemplate {
	private String match;//符合规定
	private String mismatch;//不符合规定
	private String otherInfo;//原因
	private String applyCenterName; // 申请中心
	private String applyCenterCd; // 申请中心CD

	private String recordedCompany; // 入账公司：
	// 0-上海贵铂投资管理咨询有限公司，
	// 1-上海宝龙失业发展有限公司，
	// 2-上海盈捷营销策划有限公司，
	// 3-上海盈捷贸易有限公司,
	// 4-上海寰亚建筑设计有限公司，
	// 5-上海高恒建筑工程有限公司，
	// 6-上海宝龙华睿房地产开放有限公司，
	// 7-上海睿商建筑公司有限公司，
	// 8-上海茂康投资有限公司

	private String feeType1; // 开支内容：接待费
	private String feeType2; // 开支内容：差旅费
	private String feeType3; // 开支内容：符合制度要求的相关补贴（车补、房补、通讯费）

	private String total; // 报销总计
	private String totalBig; // 报销总计（大写）
	//付款方式
	private String payType1; // 现金
	private String payType2; // 电汇
	private String payType3; // 信用证
	private String payType4; // 转账
	private String payType5; // 票汇
	private String payType6; // 其他
	
	private String currency; // 币种

	private String companyPay; // 公司代付合计

	private String punchAdvance; // 冲预借款

	private String accountReduce; // 会计核减
	private String payee;// 收款人

	private String payeeAccount;// 收款帐号

	private String payeeBank;// 开户行

	private String payAmount; // 实付金额
	private String payAmountBig; // 实付金额（大写）
	
	private String tripFileId;//出差审批单
	private String jdFileId;//接待审批单

	// 报销/付款信息
	private List<FeeReimItem> otherProperties = new ArrayList<FeeReimItem>();
	@Override
	public String getResTitleName() {
		String recordedCompanyName=CodeNameUtil.getDictNameByCd(DictContants.RES_FEE_RECORDED_COMPANY,recordedCompany) ;
		return recordedCompanyName;
	}
	@Override
	public String getResProjectCd() {
		return applyCenterCd;
	}
	@Override
	public String getResProjectName() {
		return applyCenterName;
	}
	public String getApplyCenterName() {
		return applyCenterName;
	}

	public void setApplyCenterName(String applyCenterName) {
		this.applyCenterName = applyCenterName;
	}

	public String getApplyCenterCd() {
		return applyCenterCd;
	}

	public void setApplyCenterCd(String applyCenterCd) {
		this.applyCenterCd = applyCenterCd;
	}

	public String getRecordedCompany() {
		return recordedCompany;
	}

	public void setRecordedCompany(String recordedCompany) {
		this.recordedCompany = recordedCompany;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalBig() {
		return totalBig;
	}

	public void setTotalBig(String totalBig) {
		this.totalBig = totalBig;
	}

	public String getPayType1() {
		return payType1;
	}

	public void setPayType1(String payType1) {
		this.payType1 = payType1;
	}

	public String getPayType2() {
		return payType2;
	}

	public void setPayType2(String payType2) {
		this.payType2 = payType2;
	}

	public String getPayType3() {
		return payType3;
	}

	public void setPayType3(String payType3) {
		this.payType3 = payType3;
	}

	public String getPayType4() {
		return payType4;
	}

	public void setPayType4(String payType4) {
		this.payType4 = payType4;
	}

	public String getPayType5() {
		return payType5;
	}

	public void setPayType5(String payType5) {
		this.payType5 = payType5;
	}

	public String getPayType6() {
		return payType6;
	}

	public void setPayType6(String payType6) {
		this.payType6 = payType6;
	}

	public String getCompanyPay() {
		return companyPay;
	}

	public void setCompanyPay(String companyPay) {
		this.companyPay = companyPay;
	}

	public String getPunchAdvance() {
		return punchAdvance;
	}

	public void setPunchAdvance(String punchAdvance) {
		this.punchAdvance = punchAdvance;
	}

	public String getAccountReduce() {
		return accountReduce;
	}

	public void setAccountReduce(String accountReduce) {
		this.accountReduce = accountReduce;
	}
	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeBank() {
		return payeeBank;
	}

	public void setPayeeBank(String payeeBank) {
		this.payeeBank = payeeBank;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayAmountBig() {
		return payAmountBig;
	}

	public void setPayAmountBig(String payAmountBig) {
		this.payAmountBig = payAmountBig;
	}

	public String getFeeType1() {
		return feeType1;
	}

	public void setFeeType1(String feeType1) {
		this.feeType1 = feeType1;
	}

	public String getFeeType2() {
		return feeType2;
	}

	public void setFeeType2(String feeType2) {
		this.feeType2 = feeType2;
	}

	public String getFeeType3() {
		return feeType3;
	}

	public void setFeeType3(String feeType3) {
		this.feeType3 = feeType3;
	}

	public List<FeeReimItem> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<FeeReimItem> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getTripFileId() {
		return tripFileId;
	}
	public void setTripFileId(String tripFileId) {
		this.tripFileId = tripFileId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getJdFileId() {
		return jdFileId;
	}
	public void setJdFileId(String jdFileId) {
		this.jdFileId = jdFileId;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getMismatch() {
		return mismatch;
	}
	public void setMismatch(String mismatch) {
		this.mismatch = mismatch;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

}
