package com.hhz.ump.web.vo;

import java.math.BigDecimal;
import java.util.Date;

public class VoContVisaUpdate { 
	
	private String contVisaUpdateId;//变更ID

	private String contLedgerId;//FK
	private String contNo;//合同编号
	private String contName;//合同名称

    private String resApproveId;//预估费用的网批ID
    private String resApproveCd;
    private String visaUpdateNo;//签证编号,网批导入，在合同编号上+001 等
    private BigDecimal prepareFee;//预估费用
    private String preVisaContent;//预估变更内容, PRE_VISA_CONTENT(不使用)
    private String cause;//变更原因(代码）
    private String content;//变更内容
    

    private String approveCheckId;//网批核定单网批ID
    private BigDecimal amountUpdate;//变更增减金额
    private String attaBizId;//附件ID.若有变更为网批导入则为空，否则手动上传附件。
    

    private String preDisplayNo;//预估-网批查询编号
    private String chkDisplayNo;//核定-网批查询编号
    
    private String causeDesc;//根据字典代码获得
    
    private String remark;
    private String creator;
    private String createdDeptCd;
    private Date createdDate;
    private String updator;
    private String updatedDeptCd;
    private Date updatedDate;
    private long recordVersion;
	public String getContVisaUpdateId() {
		return contVisaUpdateId;
	}
	public void setContVisaUpdateId(String contVisaUpdateId) {
		this.contVisaUpdateId = contVisaUpdateId;
	}
	public String getContLedgerId() {
		return contLedgerId;
	}
	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}
	public String getResApproveId() {
		return resApproveId;
	}
	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}
	public String getResApproveCd() {
		return resApproveCd;
	}
	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}
	public String getVisaUpdateNo() {
		return visaUpdateNo;
	}
	public void setVisaUpdateNo(String visaUpdateNo) {
		this.visaUpdateNo = visaUpdateNo;
	}
	public BigDecimal getPrepareFee() {
		return prepareFee;
	}
	public void setPrepareFee(BigDecimal prepareFee) {
		this.prepareFee = prepareFee;
	}
	public String getPreVisaContent() {
		return preVisaContent;
	}
	public void setPreVisaContent(String preVisaContent) {
		this.preVisaContent = preVisaContent;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getApproveCheckId() {
		return approveCheckId;
	}
	public void setApproveCheckId(String approveCheckId) {
		this.approveCheckId = approveCheckId;
	}
	public BigDecimal getAmountUpdate() {
		return amountUpdate;
	}
	public void setAmountUpdate(BigDecimal amountUpdate) {
		this.amountUpdate = amountUpdate;
	}
	public String getAttaBizId() {
		return attaBizId;
	}
	public void setAttaBizId(String attaBizId) {
		this.attaBizId = attaBizId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedDeptCd() {
		return createdDeptCd;
	}
	public void setCreatedDeptCd(String createdDeptCd) {
		this.createdDeptCd = createdDeptCd;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdatedDeptCd() {
		return updatedDeptCd;
	}
	public void setUpdatedDeptCd(String updatedDeptCd) {
		this.updatedDeptCd = updatedDeptCd;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public long getRecordVersion() {
		return recordVersion;
	}
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getContName() {
		return contName;
	}
	public void setContName(String contName) {
		this.contName = contName;
	}
	public String getPreDisplayNo() {
		return preDisplayNo;
	}
	public void setPreDisplayNo(String preDisplayNo) {
		this.preDisplayNo = preDisplayNo;
	}
	public String getChkDisplayNo() {
		return chkDisplayNo;
	}
	public void setChkDisplayNo(String chkDisplayNo) {
		this.chkDisplayNo = chkDisplayNo;
	}
	public String getCauseDesc() {
		return causeDesc;
	}
	public void setCauseDesc(String causeDesc) {
		this.causeDesc = causeDesc;
	}
    
}

