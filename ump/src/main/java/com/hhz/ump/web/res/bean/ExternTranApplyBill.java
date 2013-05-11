/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 赴外培训申请表
 * 
 */
public class ExternTranApplyBill extends BaseTemplatePay {

	// 申请中心 centerOrgName,centerOrgCd
	// 培训对象 tranObjectName
	// 培训课程 tranClassName
	// 培训日期 tranDate
	// 培训地点 tranAddr
	// 培训机构 tranOrgName
	// 培训预算 tranBudget
	// 1.培训费  tranCost
	// 2.交通费 vehicleCost
	// 3.住宿费  settleCost
	// 4.其他费  otherCost
	// 总计费用 totoalCost
	
	private String centerOrgName;
	private String centerOrgCd;
	private String tranObjectName;
	private String tranClassName;
	private String tranDate;
	private String tranAddr;
	private String tranOrgName;
	private String tranBudgetAmt;
	private String tranCostAmt;
	private String vehicleCostAmt;
	private String settleCostAmt;
	private String otherCostAmt;
	private String totoalCostAmt;
	private String reasonDesc;
	
	//新加附件上传
	private String attachmentId;
 
	public String getCenterOrgName() {
		return centerOrgName;
	}

	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}

	public String getCenterOrgCd() {
		return centerOrgCd;
	}

	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}

	public String getTranObjectName() {
		return tranObjectName;
	}

	public void setTranObjectName(String tranObjectName) {
		this.tranObjectName = tranObjectName;
	}

	public String getTranClassName() {
		return tranClassName;
	}

	public void setTranClassName(String tranClassName) {
		this.tranClassName = tranClassName;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranAddr() {
		return tranAddr;
	}

	public void setTranAddr(String tranAddr) {
		this.tranAddr = tranAddr;
	}

	public String getTranOrgName() {
		return tranOrgName;
	}

	public void setTranOrgName(String tranOrgName) {
		this.tranOrgName = tranOrgName;
	}

	public String getTranBudgetAmt() {
		return tranBudgetAmt;
	}

	public void setTranBudgetAmt(String tranBudgetAmt) {
		this.tranBudgetAmt = tranBudgetAmt;
	}

	public String getTranCostAmt() {
		return tranCostAmt;
	}

	public void setTranCostAmt(String tranCostAmt) {
		this.tranCostAmt = tranCostAmt;
	}

	public String getVehicleCostAmt() {
		return vehicleCostAmt;
	}

	public void setVehicleCostAmt(String vehicleCostAmt) {
		this.vehicleCostAmt = vehicleCostAmt;
	}

	public String getSettleCostAmt() {
		return settleCostAmt;
	}

	public void setSettleCostAmt(String settleCostAmt) {
		this.settleCostAmt = settleCostAmt;
	}

	public String getOtherCostAmt() {
		return otherCostAmt;
	}

	public void setOtherCostAmt(String otherCostAmt) {
		this.otherCostAmt = otherCostAmt;
	}

	public String getTotoalCostAmt() {
		return totoalCostAmt;
	}

	public void setTotoalCostAmt(String totoalCostAmt) {
		this.totoalCostAmt = totoalCostAmt;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.tranClassName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.tranClassName;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	/**
	 * @return the attachmentId
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
  
}
