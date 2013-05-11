package com.hhz.uums.vo.vw;

public class OrgTreeVo {
	private String orgId;
	private String orgCd;
	private String orgBizCd;
	private String orgName;
	private String orgTypeCd;
	private String parentId;
	public String getOrgId() {
		return orgId;
	}
	public String getOrgTypeCd() {
		return orgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	
}

