package com.hhz.uums.vo.log;

import com.hhz.uums.entity.plas.PlasUser;



public class ExPlasUserVo extends PlasUser {

	private static final long serialVersionUID = -997921823079464607L;
	private String uaapOrgId;
	private String orgCd;
	private String orgBizCd;
	private String orgName;
	private String uaapPhysicalOrgId;
	private String uaapPhysicalOrgName;
	private String uaapPhysicalOrgBizCd;
	private String uaapUserRoleOrgRelId;

	public String getUaapOrgId() {
		return uaapOrgId;
	}

	public void setUaapOrgId(String uaapOrgId) {
		this.uaapOrgId = uaapOrgId;
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


	public String getUaapPhysicalOrgName() {
		return uaapPhysicalOrgName;
	}

	public void setUaapPhysicalOrgName(String uaapPhysicalOrgName) {
		this.uaapPhysicalOrgName = uaapPhysicalOrgName;
	}

	public String getUaapPhysicalOrgId() {
		return uaapPhysicalOrgId;
	}

	public void setUaapPhysicalOrgId(String uaapPhysicalOrgId) {
		this.uaapPhysicalOrgId = uaapPhysicalOrgId;
	}

	public String getUaapPhysicalOrgBizCd() {
		return uaapPhysicalOrgBizCd;
	}

	public void setUaapPhysicalOrgBizCd(String uaapPhysicalOrgBizCd) {
		this.uaapPhysicalOrgBizCd = uaapPhysicalOrgBizCd;
	}

	public String getUaapUserRoleOrgRelId() {
		return uaapUserRoleOrgRelId;
	}

	public void setUaapUserRoleOrgRelId(String uaapUserRoleOrgRelId) {
		this.uaapUserRoleOrgRelId = uaapUserRoleOrgRelId;
	}
}
