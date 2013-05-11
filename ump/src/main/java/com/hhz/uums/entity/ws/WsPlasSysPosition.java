package com.hhz.uums.entity.ws;

import java.io.Serializable;
import java.math.BigDecimal;

public class WsPlasSysPosition  implements Serializable {
	
	private static final long serialVersionUID = 2111144263534583585L;
	private String plasSysPositionId;
	private String acctId;// PlasAcct
	private String orgId;// PlasOrg
	private String sysPosCd;
	private String sysPosName;
	private Boolean activeBl;
	private Boolean visableFlg;//是否显示
	private BigDecimal sequenceNo;
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
	public Boolean getActiveBl() {
		return activeBl;
	}
	public void setActiveBl(Boolean activeBl) {
		this.activeBl = activeBl;
	}
	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public Boolean getVisableFlg() {
		return visableFlg;
	}
	public void setVisableFlg(Boolean visableFlg) {
		this.visableFlg = visableFlg;
	}
 
}
