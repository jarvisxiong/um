package com.hhz.ump.web.bid;

import java.math.BigDecimal;
import java.util.Date;

public class BidLedProjectVo implements  java.io.Serializable{

    private String bidLedgerId;
    private String orgCd;
    private String bidSectionName;
    private String visableFlg;
    private BigDecimal targetAmt;
    private String sectionNo;
    private String budgetInFlg;
    private String budgetOutFlg;
    private BigDecimal guaranteeAmt;
    private String operDesc;
    private String placeDesc;
    private BigDecimal sectionTotalArea;
    private BigDecimal bizArea;
    private BigDecimal houseArea;
    private String followNames;
    private String followCds;
    private Date bidOpenPlanDate;
    private Date bidOpenRealDate;
    private Date bidConfirmPlanDate;
    private Date bidConfirmRealDate;
    private String bidStatusCd;
    private String bidDesc;
    private String resApproveInfoId;
    private Long batchNo;
    private String remark;
    private String creator;
    private String createdCenterCd;
    private String createdPositionCd;
    private Date createdDate;
    private String updator;
    private String updatedCenterCd;
    private String updatedPositionCd;
    private Date updatedDate;
    private long recordVersion;
    private String orgName;
    private String judgeCd;
    private String judgeName;
    private Date judgeDate;
    private String totalRankDesc;
    private String projectNum;
    private String bidSupNum;

    private String ccbpNo;
    private String ccbpId;
    
    
    
	public String getBidLedgerId() {
		return bidLedgerId;
	}
	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getBidSectionName() {
		return bidSectionName;
	}
	public void setBidSectionName(String bidSectionName) {
		this.bidSectionName = bidSectionName;
	}
	public String getVisableFlg() {
		return visableFlg;
	}
	public void setVisableFlg(String visableFlg) {
		this.visableFlg = visableFlg;
	}
	public BigDecimal getTargetAmt() {
		return targetAmt;
	}
	public void setTargetAmt(BigDecimal targetAmt) {
		this.targetAmt = targetAmt;
	}
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getBudgetInFlg() {
		return budgetInFlg;
	}
	public void setBudgetInFlg(String budgetInFlg) {
		this.budgetInFlg = budgetInFlg;
	}
	public String getBudgetOutFlg() {
		return budgetOutFlg;
	}
	public void setBudgetOutFlg(String budgetOutFlg) {
		this.budgetOutFlg = budgetOutFlg;
	}
	public BigDecimal getGuaranteeAmt() {
		return guaranteeAmt;
	}
	public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}
	public String getOperDesc() {
		return operDesc;
	}
	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}
	public String getPlaceDesc() {
		return placeDesc;
	}
	public void setPlaceDesc(String placeDesc) {
		this.placeDesc = placeDesc;
	}
	public BigDecimal getSectionTotalArea() {
		return sectionTotalArea;
	}
	public void setSectionTotalArea(BigDecimal sectionTotalArea) {
		this.sectionTotalArea = sectionTotalArea;
	}
	public BigDecimal getBizArea() {
		return bizArea;
	}
	public void setBizArea(BigDecimal bizArea) {
		this.bizArea = bizArea;
	}
	public BigDecimal getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}
	public String getFollowNames() {
		return followNames;
	}
	public void setFollowNames(String followNames) {
		this.followNames = followNames;
	}
	public String getFollowCds() {
		return followCds;
	}
	public void setFollowCds(String followCds) {
		this.followCds = followCds;
	}
	public Date getBidOpenPlanDate() {
		return bidOpenPlanDate;
	}
	public void setBidOpenPlanDate(Date bidOpenPlanDate) {
		this.bidOpenPlanDate = bidOpenPlanDate;
	}
	public Date getBidOpenRealDate() {
		return bidOpenRealDate;
	}
	public void setBidOpenRealDate(Date bidOpenRealDate) {
		this.bidOpenRealDate = bidOpenRealDate;
	}
	public Date getBidConfirmPlanDate() {
		return bidConfirmPlanDate;
	}
	public void setBidConfirmPlanDate(Date bidConfirmPlanDate) {
		this.bidConfirmPlanDate = bidConfirmPlanDate;
	}
	public Date getBidConfirmRealDate() {
		return bidConfirmRealDate;
	}
	public void setBidConfirmRealDate(Date bidConfirmRealDate) {
		this.bidConfirmRealDate = bidConfirmRealDate;
	}
	public String getBidStatusCd() {
		return bidStatusCd;
	}
	public void setBidStatusCd(String bidStatusCd) {
		this.bidStatusCd = bidStatusCd;
	}
	public String getBidDesc() {
		return bidDesc;
	}
	public void setBidDesc(String bidDesc) {
		this.bidDesc = bidDesc;
	}
	public String getResApproveInfoId() {
		return resApproveInfoId;
	}
	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
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
	public String getCreatedCenterCd() {
		return createdCenterCd;
	}
	public void setCreatedCenterCd(String createdCenterCd) {
		this.createdCenterCd = createdCenterCd;
	}
	public String getCreatedPositionCd() {
		return createdPositionCd;
	}
	public void setCreatedPositionCd(String createdPositionCd) {
		this.createdPositionCd = createdPositionCd;
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
	public String getUpdatedCenterCd() {
		return updatedCenterCd;
	}
	public void setUpdatedCenterCd(String updatedCenterCd) {
		this.updatedCenterCd = updatedCenterCd;
	}
	public String getUpdatedPositionCd() {
		return updatedPositionCd;
	}
	public void setUpdatedPositionCd(String updatedPositionCd) {
		this.updatedPositionCd = updatedPositionCd;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getJudgeCd() {
		return judgeCd;
	}
	public void setJudgeCd(String judgeCd) {
		this.judgeCd = judgeCd;
	}
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	public Date getJudgeDate() {
		return judgeDate;
	}
	public void setJudgeDate(Date judgeDate) {
		this.judgeDate = judgeDate;
	}
	public String getTotalRankDesc() {
		return totalRankDesc;
	}
	public void setTotalRankDesc(String totalRankDesc) {
		this.totalRankDesc = totalRankDesc;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getBidSupNum() {
		return bidSupNum;
	}
	public void setBidSupNum(String bidSupNum) {
		this.bidSupNum = bidSupNum;
	}
	public String getCcbpNo() {
		return ccbpNo;
	}
	public void setCcbpNo(String ccbpNo) {
		this.ccbpNo = ccbpNo;
	}
	public String getCcbpId() {
		return ccbpId;
	}
	public void setCcbpId(String ccbpId) {
		this.ccbpId = ccbpId;
	}
	

}
