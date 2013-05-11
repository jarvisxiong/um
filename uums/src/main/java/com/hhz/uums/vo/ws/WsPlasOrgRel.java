package com.hhz.uums.vo.ws;

import java.io.Serializable;

public class WsPlasOrgRel implements Serializable {

	private static final long serialVersionUID = 2357718482432970939L;

	private String plasDimeOrgRelId;

	// plasOrgDime
	private String dimeId;
	private String dimeCd;
	private String dimeName;

	// plasOrg
	private String orgId;
	private String orgCd;
	private String orgName;

	private String parentId;
	private Long treeLevel;
	private String remark;

	public String getPlasDimeOrgRelId() {
		return plasDimeOrgRelId;
	}

	public void setPlasDimeOrgRelId(String plasDimeOrgRelId) {
		this.plasDimeOrgRelId = plasDimeOrgRelId;
	}

	public String getDimeId() {
		return dimeId;
	}

	public void setDimeId(String dimeId) {
		this.dimeId = dimeId;
	}

	public String getDimeCd() {
		return dimeCd;
	}

	public void setDimeCd(String dimeCd) {
		this.dimeCd = dimeCd;
	}

	public String getDimeName() {
		return dimeName;
	}

	public void setDimeName(String dimeName) {
		this.dimeName = dimeName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Long treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
