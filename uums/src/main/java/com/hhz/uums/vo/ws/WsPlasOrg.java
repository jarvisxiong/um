// 
package com.hhz.uums.vo.ws;

import java.io.Serializable;

// Generated 2010-1-14 11:32:39 by Hibernate Tools 3.2.4.GA

public class WsPlasOrg implements Serializable {

	private static final long serialVersionUID = -1138405433221500374L;
	private String plasOrgId;
	private String orgCd;
	private String orgBizCd;
	private String orgName;
	private String shortOrgName;
	private String phoneDesc;
	private String faxDesc;
	private String orgMgrId;// uiid
	private Boolean activeBl;
	private String orgTypeCd;
	private Long sequenceNo;
	private String remark;

	private String physicalOrgId;
	private String physicalOrgCd;
	private String physicalOrgBizCd;
	private String physicalOrgName;

	private String logicalOrgId;
	private String logicalOrgCd;
	private String logicalOrgBizCd;
	private String logicalOrgName;

    private String nodeLevelCd;
    private String orgKindCd;
	private Boolean visableFlg;//是否显示
    
	public String getPlasOrgId() {
		return plasOrgId;
	}

	public void setPlasOrgId(String plasOrgId) {
		this.plasOrgId = plasOrgId;
	}

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

	public String getOrgMgrId() {
		return orgMgrId;
	}

	public void setOrgMgrId(String orgMgrId) {
		this.orgMgrId = orgMgrId;
	}

	public Boolean getActiveBl() {
		return activeBl;
	}

	public void setActiveBl(Boolean activeBl) {
		this.activeBl = activeBl;
	}

	public String getOrgTypeCd() {
		return orgTypeCd;
	}

	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	public Long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhysicalOrgId() {
		return physicalOrgId;
	}

	public void setPhysicalOrgId(String physicalOrgId) {
		this.physicalOrgId = physicalOrgId;
	}

	public String getPhysicalOrgCd() {
		return physicalOrgCd;
	}

	public void setPhysicalOrgCd(String physicalOrgCd) {
		this.physicalOrgCd = physicalOrgCd;
	}

	public String getPhysicalOrgName() {
		return physicalOrgName;
	}

	public void setPhysicalOrgName(String physicalOrgName) {
		this.physicalOrgName = physicalOrgName;
	}

	public String getLogicalOrgId() {
		return logicalOrgId;
	}

	public void setLogicalOrgId(String logicalOrgId) {
		this.logicalOrgId = logicalOrgId;
	}

	public String getLogicalOrgCd() {
		return logicalOrgCd;
	}

	public void setLogicalOrgCd(String logicalOrgCd) {
		this.logicalOrgCd = logicalOrgCd;
	}

	public String getLogicalOrgName() {
		return logicalOrgName;
	}

	public void setLogicalOrgName(String logicalOrgName) {
		this.logicalOrgName = logicalOrgName;
	}

	public String getPhysicalOrgBizCd() {
		return physicalOrgBizCd;
	}

	public void setPhysicalOrgBizCd(String physicalOrgBizCd) {
		this.physicalOrgBizCd = physicalOrgBizCd;
	}

	public String getLogicalOrgBizCd() {
		return logicalOrgBizCd;
	}

	public void setLogicalOrgBizCd(String logicalOrgBizCd) {
		this.logicalOrgBizCd = logicalOrgBizCd;
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

	public Boolean getVisableFlg() {
		return visableFlg;
	}

	public void setVisableFlg(Boolean visableFlg) {
		this.visableFlg = visableFlg;
	}

}
