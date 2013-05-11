package com.hhz.uums.entity.ws;

import java.io.Serializable;

public class WsAcctSysposRel implements Serializable {

	private static final long serialVersionUID = 8424610629107091466L;
	
	private String acctId;
	private String uiid;
	private String sysPosId;
	private String sysPosCd;
	private String sysPosName;
	private String shortName;

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getSysPosId() {
		return sysPosId;
	}

	public void setSysPosId(String sysPosId) {
		this.sysPosId = sysPosId;
	}

	public String getSysPosCd() {
		return sysPosCd;
	}

	public void setSysPosCd(String sysPosCd) {
		this.sysPosCd = sysPosCd;
	}

	public String getSysPosName() {
		return sysPosName;
	}

	public void setSysPosName(String sysPosName) {
		this.sysPosName = sysPosName;
	}

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
