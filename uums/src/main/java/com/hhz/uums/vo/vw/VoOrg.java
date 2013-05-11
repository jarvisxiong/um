package com.hhz.uums.vo.vw;

public class VoOrg {
	
	private String orgId;
	private String parentOrgId;
	private String orgName;
	private String orgCd;
	private String orgBizCd;
	private Boolean activeBl;
	private Long sequenceNo;
	
	//coremail使用
	private Long dispOrderNo;
	private Boolean visableFlg;
	
	public VoOrg(){
		
	}
	
	public VoOrg(String orgId, String parentOrgId, String orgName, String orgCd, String orgBizCd, Boolean activeBl,
			Long sequenceNo, Long dispOrderNo) {
		super();
		this.orgId = orgId;
		this.parentOrgId = parentOrgId;
		this.orgName = orgName;
		this.orgCd = orgCd;
		this.orgBizCd = orgBizCd;
		this.activeBl = activeBl;
		this.sequenceNo = sequenceNo;
		this.dispOrderNo = dispOrderNo;
	}
	
	public VoOrg(String orgId, String parentOrgId, String orgName, String orgCd, String orgBizCd, Boolean activeBl,
			Long sequenceNo) {
		super();
		this.orgId = orgId;
		this.parentOrgId = parentOrgId;
		this.orgName = orgName;
		this.orgCd = orgCd;
		this.orgBizCd = orgBizCd;
		this.activeBl = activeBl;
		this.sequenceNo = sequenceNo;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
	
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
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
	public String getOrgBizCd() {
		return orgBizCd;
	}
	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}
	public Boolean getActiveBl() {
		return activeBl;
	}
	public void setActiveBl(Boolean activeBl) {
		this.activeBl = activeBl;
	}
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public Long getDispOrderNo() {
		return dispOrderNo;
	}
	public void setDispOrderNo(Long dispOrderNo) {
		this.dispOrderNo = dispOrderNo;
	}

	public Boolean getVisableFlg() {
		return visableFlg;
	}

	public void setVisableFlg(Boolean visableFlg) {
		this.visableFlg = visableFlg;
	}
 
	
}
