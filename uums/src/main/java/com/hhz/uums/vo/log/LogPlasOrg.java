// 
package com.hhz.uums.vo.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// Generated 2010-1-14 11:32:39 by Hibernate Tools 3.2.4.GA

public class LogPlasOrg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4024042778085424031L;
	private String orgId;
	private String orgCd;
	private String orgBizCd;
	private String orgName;
	private String shortOrgName;
	private String orgTypeCd;
	private String phoneDesc;
	private String faxDesc;
	private String pubFlg;
	private String remark;

	private BigDecimal sequenceNo;

	private String orgMgrId;
	private String parentOrgCd;
	private String parentOrgBizCd;
	private String parentOrgName;
	private String parentPhysicalOrgCd;
	
	private String parentMainMgrCd;
	private String parentPartMgrCd;

	// 拦截器取不到会话中的用户信息
	private String creator;
	private String createdDeptCd;
	private Date createdDate;
	private String updator;
	private String updatedDeptCd;
	private Date updatedDate;
	private long recordVersion;
	

    private String nodeLevelCd;
    private String orgKindCd;
    private String orgMgrSysPosId;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgBizCd() {
		return orgBizCd;
	}

	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getShortOrgName() {
		return shortOrgName;
	}

	public void setShortOrgName(String shortOrgName) {
		this.shortOrgName = shortOrgName;
	}

	public String getOrgTypeCd() {
		return orgTypeCd;
	}

	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	public String getPhoneDesc() {
		return phoneDesc;
	}

	public void setPhoneDesc(String phoneDesc) {
		this.phoneDesc = phoneDesc;
	}

	public String getFaxDesc() {
		return faxDesc;
	}

	public void setFaxDesc(String faxDesc) {
		this.faxDesc = faxDesc;
	}

	public String getPubFlg() {
		return pubFlg;
	}

	public void setPubFlg(String pubFlg) {
		this.pubFlg = pubFlg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentOrgCd() {
		return parentOrgCd;
	}

	public void setParentOrgCd(String parentOrgCd) {
		this.parentOrgCd = parentOrgCd;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentOrgBizCd() {
		return parentOrgBizCd;
	}

	public void setParentOrgBizCd(String parentOrgBizCd) {
		this.parentOrgBizCd = parentOrgBizCd;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
 

	public String getOrgMgrId() {
		return orgMgrId;
	}

	public void setOrgMgrId(String orgMgrId) {
		this.orgMgrId = orgMgrId;
	}

	public String getParentPhysicalOrgCd() {
		return parentPhysicalOrgCd;
	}

	public void setParentPhysicalOrgCd(String parentPhysicalOrgCd) {
		this.parentPhysicalOrgCd = parentPhysicalOrgCd;
	}

	public String getUaapOrgId() {
		return orgId;
	}

	public void setUaapOrgId(String uaapOrgId) {
		this.orgId = uaapOrgId;
	}
 

	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
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

	public String getParentMainMgrCd() {
		return parentMainMgrCd;
	}

	public void setParentMainMgrCd(String parentMainMgrCd) {
		this.parentMainMgrCd = parentMainMgrCd;
	}

	public String getParentPartMgrCd() {
		return parentPartMgrCd;
	}

	public void setParentPartMgrCd(String parentPartMgrCd) {
		this.parentPartMgrCd = parentPartMgrCd;
	}

	public String getNodeLevelCd() {
		return nodeLevelCd;
	}

	public void setNodeLevelCd(String nodeLevelCd) {
		this.nodeLevelCd = nodeLevelCd;
	}

	public String getOrgKindCd() {
		return orgKindCd;
	}

	public void setOrgKindCd(String orgKindCd) {
		this.orgKindCd = orgKindCd;
	}

	public String getOrgMgrSysPosId() {
		return orgMgrSysPosId;
	}

	public void setOrgMgrSysPosId(String orgMgrSysPosId) {
		this.orgMgrSysPosId = orgMgrSysPosId;
	}
}
