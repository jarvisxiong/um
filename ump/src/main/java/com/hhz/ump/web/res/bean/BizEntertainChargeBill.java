package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//商业招待费付款(eg: 宝龙商业招待费付款审批表)
public class BizEntertainChargeBill extends BaseTemplate {

	// 商业公司
	// 申请人
	private String companyName;
	private String companyCd;
	private String applyName;
	private String applyCd;

	// 招待对象
	// 招待地点
	private String entertainObjectDesc;
	private String entertainPlaceDesc;

	// 本次支付金额（元）
	// 本次付款时间
	private String payAmt;
	private String payDate;

	// 付款原由
	private String payReasonDesc;

	@Override
	public String getCompanyName() {
		return companyName;
	}

	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyCd() {
		return applyCd;
	}

	public void setApplyCd(String applyCd) {
		this.applyCd = applyCd;
	}

	public String getEntertainObjectDesc() {
		return entertainObjectDesc;
	}

	public void setEntertainObjectDesc(String entertainObjectDesc) {
		this.entertainObjectDesc = entertainObjectDesc;
	}

	public String getEntertainPlaceDesc() {
		return entertainPlaceDesc;
	}

	public void setEntertainPlaceDesc(String entertainPlaceDesc) {
		this.entertainPlaceDesc = entertainPlaceDesc;
	}

	public String getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayReasonDesc() {
		return payReasonDesc;
	}

	public void setPayReasonDesc(String payReasonDesc) {
		this.payReasonDesc = payReasonDesc;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return entertainObjectDesc;
	}

}
