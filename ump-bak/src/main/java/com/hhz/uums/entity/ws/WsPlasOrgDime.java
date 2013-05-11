// 
package com.hhz.uums.entity.ws;

import java.io.Serializable;

// Generated 2010-1-14 11:32:39 by Hibernate Tools 3.2.4.GA

public class WsPlasOrgDime implements Serializable {

	private static final long serialVersionUID = -1818260751101052249L;

    private String plasOrgDimeId;
    private String dimeCd;
    private String dimeName;
    private Long sequenceNo;
    private String remark;
	public String getPlasOrgDimeId() {
		return plasOrgDimeId;
	}
	public void setPlasOrgDimeId(String plasOrgDimeId) {
		this.plasOrgDimeId = plasOrgDimeId;
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
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
 
}
