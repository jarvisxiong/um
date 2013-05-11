package com.hhz.uums.vo.vw;


public class VoSysPosition {

    private String plasSysPositionId;
    private String acctId;
    private String orgId;
    private String orgCd;
    private String orgName;
    private String sysPosCd;
    private String sysPosName;
    private String shortName;
    private Boolean activeBl;
    private Boolean tmpPosFlg;
    private Boolean beyondFlg;
    private Boolean outStatFlg;

    private Boolean visableFlg; //add by huangbijin 2012-03-31
    
    private String uiid;
    private String userName;
    private String acctStatusCd;//账号状态
    private String sexCd;
    
	public String getPlasSysPositionId() {
		return plasSysPositionId;
	}
	public void setPlasSysPositionId(String plasSysPositionId) {
		this.plasSysPositionId = plasSysPositionId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Boolean getActiveBl() {
		return activeBl;
	}
	public void setActiveBl(Boolean activeBl) {
		this.activeBl = activeBl;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAcctStatusCd() {
		return acctStatusCd;
	}
	public void setAcctStatusCd(String acctStatusCd) {
		this.acctStatusCd = acctStatusCd;
	}
	public Boolean getTmpPosFlg() {
		return tmpPosFlg;
	}
	public void setTmpPosFlg(Boolean tmpPosFlg) {
		this.tmpPosFlg = tmpPosFlg;
	}
	public Boolean getBeyondFlg() {
		return beyondFlg;
	}
	public void setBeyondFlg(Boolean beyondFlg) {
		this.beyondFlg = beyondFlg;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public Boolean getOutStatFlg() {
		return outStatFlg;
	}
	public void setOutStatFlg(Boolean outStatFlg) {
		this.outStatFlg = outStatFlg;
	}
	public Boolean getVisableFlg() {
		return visableFlg;
	}
	public void setVisableFlg(Boolean visableFlg) {
		this.visableFlg = visableFlg;
	} 
	
}