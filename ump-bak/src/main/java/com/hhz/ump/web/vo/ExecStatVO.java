package com.hhz.ump.web.vo;

import java.math.BigDecimal;

public class ExecStatVO {

	private String planMainId;//ID
	private String projectCd;//项目CD
	private String projectName;//项目Name
	private String resOrgCd;//责任中心cd
	private String resOrgName;//责任中心Name
	private BigDecimal pc;//非关键完成
	private BigDecimal pd;//非关键延期
	private BigDecimal cc;//关键完成
	private BigDecimal cd;//关键延期
	private BigDecimal mc;//应完成
	private BigDecimal hc;//已完成
	private BigDecimal nc;//未完成
	private BigDecimal rc;//实际完成
	private BigDecimal rate;//完成率 
	public String getPlanMainId() {
		return planMainId;
	}
	public void setPlanMainId(String planMainId) {
		this.planMainId = planMainId;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getResOrgCd() {
		return resOrgCd;
	}
	public void setResOrgCd(String resOrgCd) {
		this.resOrgCd = resOrgCd;
	}
	public String getResOrgName() {
		return resOrgName;
	}
	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}
	public BigDecimal getPc() {
		return pc;
	}
	public void setPc(BigDecimal pc) {
		this.pc = pc;
	}
	public BigDecimal getPd() {
		return pd;
	}
	public void setPd(BigDecimal pd) {
		this.pd = pd;
	}
	public BigDecimal getCc() {
		return cc;
	}
	public void setCc(BigDecimal cc) {
		this.cc = cc;
	}
	public BigDecimal getCd() {
		return cd;
	}
	public void setCd(BigDecimal cd) {
		this.cd = cd;
	}
	public BigDecimal getMc() {
		return mc;
	}
	public void setMc(BigDecimal mc) {
		this.mc = mc;
	}
	public BigDecimal getHc() {
		return hc;
	}
	public void setHc(BigDecimal hc) {
		this.hc = hc;
	}
	public BigDecimal getNc() {
		return nc;
	}
	public void setNc(BigDecimal nc) {
		this.nc = nc;
	}
	public BigDecimal getRc() {
		return rc;
	}
	public void setRc(BigDecimal rc) {
		this.rc = rc;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
}
