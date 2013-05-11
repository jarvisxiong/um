/**
 * 
 */
package com.hhz.ump.entity.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 成本合约规划模板明细
 * @author qlb 3/28/2012
 *
 */
public class CostContPlanTplDetailTreeGrid {

    private String costContPlanTplDetailId;
    private String costContPlanTplId;
    private String dispOrderDesc;
    private String subjectCd;
    private String contTypeCd;
    private String contTypeName;
    private String contName;
    private String contCharTypeCd;
    private String contCharTypeName;
    
	private BigDecimal contSubTargetAmt;
    private BigDecimal contCaliAmt;
    private BigDecimal targCaliDiffAmt;
    private String landDesc;
    private String unitDefineDesc;
    private BigDecimal amount;
    private String workReqDesc;
    private String partMateTypeCds;
    private String partMateTypeNames;
    private String partMateTypeIds;
    private String relParentContId;
    private String inviPreCondCds;
    private String inviPreCondNames;
    private String inviPreCondIds;
    private Date outDrawDate;
    private Date planStartDate;
    private Date planEndDate;
    private Date tendStartDate;
    private String memoDesc;
    private String rowTypeCd;
    private Long contSequNo;
    private Long subjectSequNo;
    private String remark;
    private String creator;

	private Date createdDate;

    private long recordVersion;
    private String checked;
	private String state="open";
	private String iconCls="icon-ok";
    
	/**
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	private List<CostContPlanTplDetailTreeGrid>children=new ArrayList<CostContPlanTplDetailTreeGrid>();

    /**
	 * @return the costContPlanTplDetailId
	 */
	public String getCostContPlanTplDetailId() {
		return costContPlanTplDetailId;
	}

	/**
	 * @param costContPlanTplDetailId the costContPlanTplDetailId to set
	 */
	public void setCostContPlanTplDetailId(String costContPlanTplDetailId) {
		this.costContPlanTplDetailId = costContPlanTplDetailId;
	}

	
	/**
	 * @return the costContPlanTplId
	 */
	public String getCostContPlanTplId() {
		return costContPlanTplId;
	}

	/**
	 * @param costContPlanTplId the costContPlanTplId to set
	 */
	public void setCostContPlanTplId(String costContPlanTplId) {
		this.costContPlanTplId = costContPlanTplId;
	}

	/**
	 * @return the dispOrderDesc
	 */
	public String getDispOrderDesc() {
		return dispOrderDesc;
	}

	/**
	 * @param dispOrderDesc the dispOrderDesc to set
	 */
	public void setDispOrderDesc(String dispOrderDesc) {
		this.dispOrderDesc = dispOrderDesc;
	}

	/**
	 * @return the subjectCd
	 */
	public String getSubjectCd() {
		return subjectCd;
	}

	/**
	 * @param subjectCd the subjectCd to set
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	/**
	 * @return the contTypeCd
	 */
	public String getContTypeCd() {
		return contTypeCd;
	}

	/**
	 * @param contTypeCd the contTypeCd to set
	 */
	public void setContTypeCd(String contTypeCd) {
		this.contTypeCd = contTypeCd;
	}

	/**
	 * @return the contName
	 */
	public String getContName() {
		return contName;
	}

	/**
	 * @param contName the contName to set
	 */
	public void setContName(String contName) {
		this.contName = contName;
	}

	/**
	 * @return the contCharTypeCd
	 */
	public String getContCharTypeCd() {
		return contCharTypeCd;
	}

	/**
	 * @param contCharTypeCd the contCharTypeCd to set
	 */
	public void setContCharTypeCd(String contCharTypeCd) {
		this.contCharTypeCd = contCharTypeCd;
	}

	/**
	 * @return the contSubTargetAmt
	 */
	public BigDecimal getContSubTargetAmt() {
		return contSubTargetAmt;
	}

	/**
	 * @param contSubTargetAmt the contSubTargetAmt to set
	 */
	public void setContSubTargetAmt(BigDecimal contSubTargetAmt) {
		this.contSubTargetAmt = contSubTargetAmt;
	}

	/**
	 * @return the contCaliAmt
	 */
	public BigDecimal getContCaliAmt() {
		return contCaliAmt;
	}

	/**
	 * @param contCaliAmt the contCaliAmt to set
	 */
	public void setContCaliAmt(BigDecimal contCaliAmt) {
		this.contCaliAmt = contCaliAmt;
	}

	/**
	 * @return the targCaliDiffAmt
	 */
	public BigDecimal getTargCaliDiffAmt() {
		return targCaliDiffAmt;
	}

	/**
	 * @param targCaliDiffAmt the targCaliDiffAmt to set
	 */
	public void setTargCaliDiffAmt(BigDecimal targCaliDiffAmt) {
		this.targCaliDiffAmt = targCaliDiffAmt;
	}

	/**
	 * @return the landDesc
	 */
	public String getLandDesc() {
		return landDesc;
	}

	/**
	 * @param landDesc the landDesc to set
	 */
	public void setLandDesc(String landDesc) {
		this.landDesc = landDesc;
	}

	/**
	 * @return the unitDefineDesc
	 */
	public String getUnitDefineDesc() {
		return unitDefineDesc;
	}

	/**
	 * @param unitDefineDesc the unitDefineDesc to set
	 */
	public void setUnitDefineDesc(String unitDefineDesc) {
		this.unitDefineDesc = unitDefineDesc;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the workReqDesc
	 */
	public String getWorkReqDesc() {
		return workReqDesc;
	}

	/**
	 * @param workReqDesc the workReqDesc to set
	 */
	public void setWorkReqDesc(String workReqDesc) {
		this.workReqDesc = workReqDesc;
	}

	/**
	 * @return the partMateTypeCds
	 */
	public String getPartMateTypeCds() {
		return partMateTypeCds;
	}

	/**
	 * @param partMateTypeCds the partMateTypeCds to set
	 */
	public void setPartMateTypeCds(String partMateTypeCds) {
		this.partMateTypeCds = partMateTypeCds;
	}

	/**
	 * @return the partMateTypeNames
	 */
	public String getPartMateTypeNames() {
		return partMateTypeNames;
	}

	/**
	 * @param partMateTypeNames the partMateTypeNames to set
	 */
	public void setPartMateTypeNames(String partMateTypeNames) {
		this.partMateTypeNames = partMateTypeNames;
	}

	/**
	 * @return the partMateTypeIds
	 */
	public String getPartMateTypeIds() {
		return partMateTypeIds;
	}

	/**
	 * @param partMateTypeIds the partMateTypeIds to set
	 */
	public void setPartMateTypeIds(String partMateTypeIds) {
		this.partMateTypeIds = partMateTypeIds;
	}

	/**
	 * @return the relParentContId
	 */
	public String getRelParentContId() {
		return relParentContId;
	}

	/**
	 * @param relParentContId the relParentContId to set
	 */
	public void setRelParentContId(String relParentContId) {
		this.relParentContId = relParentContId;
	}

	/**
	 * @return the inviPreCondCds
	 */
	public String getInviPreCondCds() {
		return inviPreCondCds;
	}

	/**
	 * @param inviPreCondCds the inviPreCondCds to set
	 */
	public void setInviPreCondCds(String inviPreCondCds) {
		this.inviPreCondCds = inviPreCondCds;
	}

	/**
	 * @return the inviPreCondNames
	 */
	public String getInviPreCondNames() {
		return inviPreCondNames;
	}

	/**
	 * @param inviPreCondNames the inviPreCondNames to set
	 */
	public void setInviPreCondNames(String inviPreCondNames) {
		this.inviPreCondNames = inviPreCondNames;
	}

	/**
	 * @return the inviPreCondIds
	 */
	public String getInviPreCondIds() {
		return inviPreCondIds;
	}

	/**
	 * @param inviPreCondIds the inviPreCondIds to set
	 */
	public void setInviPreCondIds(String inviPreCondIds) {
		this.inviPreCondIds = inviPreCondIds;
	}

	/**
	 * @return the outDrawDate
	 */
	public Date getOutDrawDate() {
		return outDrawDate;
	}

	/**
	 * @param outDrawDate the outDrawDate to set
	 */
	public void setOutDrawDate(Date outDrawDate) {
		this.outDrawDate = outDrawDate;
	}

	/**
	 * @return the planStartDate
	 */
	public Date getPlanStartDate() {
		return planStartDate;
	}

	/**
	 * @param planStartDate the planStartDate to set
	 */
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}


	/**
	 * @return the planEndDate
	 */
	public Date getPlanEndDate() {
		return planEndDate;
	}

	/**
	 * @param planEndDate the planEndDate to set
	 */
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	/**
	 * @return the tendStartDate
	 */
	public Date getTendStartDate() {
		return tendStartDate;
	}

	/**
	 * @param tendStartDate the tendStartDate to set
	 */
	public void setTendStartDate(Date tendStartDate) {
		this.tendStartDate = tendStartDate;
	}

	/**
	 * @return the memoDesc
	 */
	public String getMemoDesc() {
		return memoDesc;
	}

	/**
	 * @param memoDesc the memoDesc to set
	 */
	public void setMemoDesc(String memoDesc) {
		this.memoDesc = memoDesc;
	}

	/**
	 * @return the rowTypeCd
	 */
	public String getRowTypeCd() {
		return rowTypeCd;
	}

	/**
	 * @param rowTypeCd the rowTypeCd to set
	 */
	public void setRowTypeCd(String rowTypeCd) {
		this.rowTypeCd = rowTypeCd;
	}

	/**
	 * @return the contSequNo
	 */
	public Long getContSequNo() {
		return contSequNo;
	}

	/**
	 * @param contSequNo the contSequNo to set
	 */
	public void setContSequNo(Long contSequNo) {
		this.contSequNo = contSequNo;
	}
	
	

	public Long getSubjectSequNo() {
		return subjectSequNo;
	}

	public void setSubjectSequNo(Long subjectSequNo) {
		this.subjectSequNo = subjectSequNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the recordVersion
	 */
	public long getRecordVersion() {
		return recordVersion;
	}

	/**
	 * @param recordVersion the recordVersion to set
	 */
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	/**
	 * @return the children
	 */
	public List<CostContPlanTplDetailTreeGrid> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<CostContPlanTplDetailTreeGrid> children) {
		this.children = children;
	}
	
	/**
	 * @return the contTypeName
	 */
	public String getContTypeName() {
		return contTypeName;
	}

	/**
	 * @param contTypeName the contTypeName to set
	 */
	public void setContTypeName(String contTypeName) {
		this.contTypeName = contTypeName;
	}

	/**
	 * @return the contCharTypeName
	 */
	public String getContCharTypeName() {
		return contCharTypeName;
	}

	/**
	 * @param contCharTypeName the contCharTypeName to set
	 */
	public void setContCharTypeName(String contCharTypeName) {
		this.contCharTypeName = contCharTypeName;
	}


}
