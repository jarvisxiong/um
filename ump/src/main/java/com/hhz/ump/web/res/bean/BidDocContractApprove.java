package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseContractTemplate;


/**
 * 招标文件与招标合同评审审批表
 * @author zhuxj
 *
 * 2012-03-30
 */
public class BidDocContractApprove  extends BaseContractTemplate{
	/**
	 * 判断是否包含法务
	 */
	private String containLawFlg;
	/**
	 * 判断是否包含财务
	 */
	private String containFinancialFlg;
	/**
	 * 判断是否包含酒店
	 */
	private String containHotelFlg;
	/**
	 * 招标文件编号
	 */
	private String oriBidFileCode;
	/**
	 * 招标合同甲方
	 */
	private String oriBidConA;
	/**
	 * 招标合同甲方签约人
	 */
	private String oriBidConPersonA;
	/**
	 * 招标合同乙方
	 */
	private String oriBidFileB;
	/**
	 * 招标合同乙方签约人
	 */
	private String oriBidConPersonB;
	/**
	 * 招标合同丙方
	 */
	private String oriBidConC;//丙方 -Add for part C by zhuxj on 2012.3.31
	/**
	 * 招标合同丙方签约人
	 */
	private String oriBidConPersonC;//丙方签约人 -Add for part C by zhuxj on 2012.3.31
	
	/**
	 * 招标文件是否被选中
	 */
	private String oriBidFileCheckA;
	/**
	 * 正式合同是否被选中
	 */
	private String oriBidFileCheckB;
	/**
	 * 合同标段
	 */
	private String oriBidConSection;
	/**
	 * 合同工期
	 */
	private String oriBidConPeriod;
	/**
	 * 范围/数量
	 */
	private String oriBidConRangeAmount;
	/**
	 * 预估合同价款
	 */
	private String oriBidConEvaluated;
	/**
	 * 付款方式
	 */
	private String oriBidConPayType;
	
	
	public BidDocContractApprove() {
		// TODO Auto-generated constructor stub
	}
	public String getOriBidFileCode() {
		return oriBidFileCode;
	}
	public void setOriBidFileCode(String oriBidFileCode) {
		this.oriBidFileCode = oriBidFileCode;
	}
	public String getOriBidConA() {
		return oriBidConA;
	}
	public void setOriBidConA(String oriBidConA) {
		this.oriBidConA = oriBidConA;
	}
	public String getOriBidConPersonA() {
		return oriBidConPersonA;
	}
	public void setOriBidConPersonA(String oriBidConPersonA) {
		this.oriBidConPersonA = oriBidConPersonA;
	}
	public String getOriBidFileB() {
		return oriBidFileB;
	}
	public void setOriBidFileB(String oriBidFileB) {
		this.oriBidFileB = oriBidFileB;
	}
	public String getOriBidConC() {
		return oriBidConC;
	}
	public void setOriBidConC(String oriBidConC) {
		this.oriBidConC = oriBidConC;
	}
	public String getOriBidConPersonC() {
		return oriBidConPersonC;
	}
	public void setOriBidConPersonC(String oriBidConPersonC) {
		this.oriBidConPersonC = oriBidConPersonC;
	}
	public String getOriBidConPersonB() {
		return oriBidConPersonB;
	}
	public void setOriBidConPersonB(String oriBidConPersonB) {
		this.oriBidConPersonB = oriBidConPersonB;
	}
	public String getOriBidFileCheckA() {
		return oriBidFileCheckA;
	}
	public void setOriBidFileCheckA(String oriBidFileCheckA) {
		this.oriBidFileCheckA = oriBidFileCheckA;
	}
	public String getOriBidFileCheckB() {
		return oriBidFileCheckB;
	}
	public void setOriBidFileCheckB(String oriBidFileCheckB) {
		this.oriBidFileCheckB = oriBidFileCheckB;
	}
	public String getOriBidConSection() {
		return oriBidConSection;
	}
	public void setOriBidConSection(String oriBidConSection) {
		this.oriBidConSection = oriBidConSection;
	}
	public String getOriBidConPeriod() {
		return oriBidConPeriod;
	}
	public void setOriBidConPeriod(String oriBidConPeriod) {
		this.oriBidConPeriod = oriBidConPeriod;
	}
	public String getOriBidConRangeAmount() {
		return oriBidConRangeAmount;
	}
	public void setOriBidConRangeAmount(String oriBidConRangeAmount) {
		this.oriBidConRangeAmount = oriBidConRangeAmount;
	}
	public String getOriBidConEvaluated() {
		return oriBidConEvaluated;
	}
	public void setOriBidConEvaluated(String oriBidConEvaluated) {
		this.oriBidConEvaluated = oriBidConEvaluated;
	}
	public String getOriBidConPayType() {
		return oriBidConPayType;
	}
	public void setOriBidConPayType(String oriBidConPayType) {
		this.oriBidConPayType = oriBidConPayType;
	}
	public String getContainLawFlg() {
		return containLawFlg;
	}
	public void setContainLawFlg(String containLawFlg) {
		this.containLawFlg = containLawFlg;
	}
	public String getContainFinancialFlg() {
		return containFinancialFlg;
	}
	public void setContainFinancialFlg(String containFinancialFlg) {
		this.containFinancialFlg = containFinancialFlg;
	}
	public String getContainHotelFlg() {
		return containHotelFlg;
	}
	public void setContainHotelFlg(String containHotelFlg) {
		this.containHotelFlg = containHotelFlg;
	}
	
}
