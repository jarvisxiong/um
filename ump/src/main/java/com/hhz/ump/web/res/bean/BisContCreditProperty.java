package com.hhz.ump.web.res.bean;

/**
 * 合同资信资料
 * @author baolm
 * 2011-11-11
 */
public class BisContCreditProperty {
	
	/**
	 * 序号
	 */
    private String sequenceNo;
    /**
     * 资料名称
     */
    private String infoName;
    /**
     * 资料时限
     */
    private String infoLimit;
    /**
     * 备注
     */
    private String remark;
    /**
     * 资信资料附件ID
     */
	private String contCreditId;
	
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoLimit() {
		return infoLimit;
	}
	public void setInfoLimit(String infoLimit) {
		this.infoLimit = infoLimit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContCreditId() {
		return contCreditId;
	}
	public void setContCreditId(String contCreditId) {
		this.contCreditId = contCreditId;
	}

}
