package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//宝龙商业制度文件审批表
public class BizRegimeFile extends BaseTemplate {

	private String regimeName;//制度文件名称
	private String baseRegime;//基本制度
	private String commonRegime;//一般制度
	private String innerRegime;//内部制度
	//是否需要相关专业口审阅
	private String isAudit;
	
	private String isNotAudit;
	//审批类型
	private String auditType;
	//审批修订
	private String auditModify;
	
	private String contentDesc;//内容
	public String getRegimeName() {
		return regimeName;
	}
	public void setRegimeName(String regimeName) {
		this.regimeName = regimeName;
	}
	public String getBaseRegime() {
		return baseRegime;
	}
	public void setBaseRegime(String baseRegime) {
		this.baseRegime = baseRegime;
	}
	public String getCommonRegime() {
		return commonRegime;
	}
	public void setCommonRegime(String commonRegime) {
		this.commonRegime = commonRegime;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getInnerRegime() {
		return innerRegime;
	}
	public void setInnerRegime(String innerRegime) {
		this.innerRegime = innerRegime;
	}
	public String getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	public String getIsNotAudit() {
		return isNotAudit;
	}
	public void setIsNotAudit(String isNotAudit) {
		this.isNotAudit = isNotAudit;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getAuditModify() {
		return auditModify;
	}
	public void setAuditModify(String auditModify) {
		this.auditModify = auditModify;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return regimeName;
	}
	
}
