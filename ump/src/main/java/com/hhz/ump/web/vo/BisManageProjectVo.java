package com.hhz.ump.web.vo;

import java.math.BigDecimal;


public class BisManageProjectVo   {
	/**
	 * 日常收入总计
	 */
	public static final String BIS_CHARGE_TYPE_101 = "101";
	/**
	 * 招商代理费
	 */
	public static final String BIS_CHARGE_TYPE_102 = "102";
	/**
	 * 多径收入及其他
	 */
	public static final String BIS_CHARGE_TYPE_103 = "103";
	/**
	 * 搜索经营情况汇总(年度、月度)
	 */
	public static final String BIS_CHARGE_TYPE_104 = "104";
	/**
	 * 收入总计
	 */
	public static final String BIS_CHARGE_TYPE_105 = "105";
	/**
	 * 违约金
	 */
	public static final String BIS_CHARGE_TYPE_106 = "106";
	/**
	 * 成本费用支出
	 */
	public static final String BIS_CHARGE_TYPE_107 = "107";
	/**
	 * 广告宣传费
	 */
	public static final String BIS_CHARGE_TYPE_108 = "108";
	/**
	 * 人工福利费
	 */
	public static final String BIS_CHARGE_TYPE_109 = "109";
	/**
	 * 项目能源费
	 */
	public static final String BIS_CHARGE_TYPE_110 = "110";
	/**日常办公支出收入总计
	 */
	public static final String BIS_CHARGE_TYPE_111 = "111";
	/**支出合计
	 */
	public static final String BIS_CHARGE_TYPE_112 = "112";
	
	private String projectid;
	private String projectName;
	private String ctc = "";
	private String ctn = "";
	
	private BigDecimal my = new BigDecimal(0);
	private BigDecimal mm= new BigDecimal(0);
	private BigDecimal fy= new BigDecimal(0);
	private BigDecimal fm= new BigDecimal(0);
	private BigDecimal ry= new BigDecimal(0);
	private BigDecimal rm= new BigDecimal(0);
	public BisManageProjectVo(){
		
	}
	public BisManageProjectVo(String chargeTypeCd,String chargeTypeName,BigDecimal mustYear,BigDecimal factYear,BigDecimal rateYear,BigDecimal mustMonthOne,BigDecimal factMonthOne ,BigDecimal rateMonthOne){
		this.ctc = chargeTypeCd;
		this.ctn = chargeTypeName;
		this.my = mustYear;
		this.fy = factYear;
		this.ry = rateYear;
		this.mm = mustMonthOne;
		this.fm = factMonthOne;
		this.rm =rateMonthOne;
	}
	public String getCtc() {
		return ctc;
	}
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public BigDecimal getMy() {
		return my;
	}
	public void setMy(BigDecimal my) {
		this.my = my;
	}
	public BigDecimal getMm() {
		return mm;
	}
	public void setMm(BigDecimal mm) {
		this.mm = mm;
	}
	public BigDecimal getFy() {
		return fy;
	}
	public void setFy(BigDecimal fy) {
		this.fy = fy;
	}
	public BigDecimal getFm() {
		return fm;
	}
	public void setFm(BigDecimal fm) {
		this.fm = fm;
	}
	public BigDecimal getRy() {
		return ry;
	}
	public void setRy(BigDecimal ry) {
		this.ry = ry;
	}
	public BigDecimal getRm() {
		return rm;
	}
	public void setRm(BigDecimal rm) {
		this.rm = rm;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}