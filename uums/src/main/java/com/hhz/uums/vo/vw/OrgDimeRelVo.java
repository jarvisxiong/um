package com.hhz.uums.vo.vw;

public class OrgDimeRelVo {

	private boolean relFlag;
	private String orgId;
	private String dimeId;
	private String dimeCd;
	private String dimeName;
	private String parentId;
	private String parentOrgName;
	private String relId;

	public String getDimeCd() {
		return dimeCd;
	}

	public void setDimeCd(String dimeCd) {
		this.dimeCd = dimeCd;
	}

	public boolean isRelFlag() {
		return relFlag;
	}

	public void setRelFlag(boolean relFlag) {
		this.relFlag = relFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDimeId() {
		return dimeId;
	}

	public void setDimeId(String dimeId) {
		this.dimeId = dimeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDimeName() {
		return dimeName;
	}

	public void setDimeName(String dimeName) {
		this.dimeName = dimeName;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

}
