package com.hhz.ump.web.vo;

import com.hhz.uums.entity.ws.WsPlasOrg;

public class OrgLevelOneVo {

	private WsPlasOrg levelOne;

	private String orgLevelOneOrgCd;


	public String getOrgLevelOneOrgCd() {
		// return OrgLevelOneOrgCd;
		if (levelOne == null)
			return null;
		else
			return levelOne.getOrgCd();
	}

	public WsPlasOrg getLevelOne() {
		return levelOne;
	}

	public void setLevelOne(WsPlasOrg levelOne) {
		this.levelOne = levelOne;
	}

	public void setOrgLevelOneOrgCd(String orgLevelOneOrgCd) {
		this.orgLevelOneOrgCd = orgLevelOneOrgCd;
	}

}
