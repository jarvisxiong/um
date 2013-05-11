package com.hhz.ump.web.vo;

import com.hhz.uums.entity.ws.WsPlasOrg;

public class OrgCenterVo {

	private WsPlasOrg center;

	private String levelOneOrgCd;

	private String orgCenterParentOrgCd;

	private String orgCenterOrgName;

	private String centerOrgCd;

	private String centerOrgBizCd;

	public String getOrgCenterOrgName() {
//		return orgCenterOrgName;
		if (center == null)
			return null;
		else
			return center.getOrgName();
	}

	public void setOrgCenterOrgName(String orgCenterOrgName) {

		this.orgCenterOrgName = orgCenterOrgName;
	}
 
	public String getOrgCenterParentOrgCd() {
		//return orgCenterOrgCd; 
//		if (center == null)
			return null;
/*todu		else
			return center.getParentOrgCd();*/
	}

	public void setOrgCenterParentOrgCd(String orgCenterOrgCd) {
		this.orgCenterParentOrgCd = orgCenterOrgCd;
	}

	public WsPlasOrg getCenter() {
		return center;
	}

	public void setCenter(WsPlasOrg center) {
		this.center = center;
	}

	public String getLevelOneOrgCd() {
		return levelOneOrgCd;
	}

	public void setLevelOneOrgCd(String levelOneOrgCd) {
		this.levelOneOrgCd = levelOneOrgCd;
	}

	public String getCenterOrgCd() {
		// return centerOrgCd;
		if (center == null)
			return null;
		else
			return center.getOrgCd();
	}

	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}

	public String getCenterOrgBizCd() {
		// return centerOrgBizCd;
		if (center == null)
			return null;
		else
			return center.getOrgBizCd();
	}

	public void setCenterOrgBizCd(String centerOrgBizCd) {
		this.centerOrgBizCd = centerOrgBizCd;
	}

}
