/**
 * 
 */
package com.hhz.ump.web.sc.vo;


/**
 * @author qlb 2/7/2012 version 1.0.0
 * 
 */
public class VoScContractTemplet {
	private String contractTempletId;
	private String contractTempletTypeId;
	private String contractTempletTypeName;
	private String templetName;
	private Byte isvalid;
	private Byte iscurversion;
	private Byte isstandard;
	

	private Long sequenceNo;
	private String templetPath;
	private String remark;
	/**
	 * @return the isstandard
	 */
	public Byte getIsstandard() {
		return isstandard;
	}

	/**
	 * @param isstandard the isstandard to set
	 */
	public void setIsstandard(Byte isstandard) {
		this.isstandard = isstandard;
	}

	//是否预览
	private String isView;



	/**
	 * @return the contractTempletTypeName
	 */
	public String getContractTempletTypeName() {
		return contractTempletTypeName;
	}

	/**
	 * @param contractTempletTypeName the contractTempletTypeName to set
	 */
	public void setContractTempletTypeName(String contractTempletTypeName) {
		this.contractTempletTypeName = contractTempletTypeName;
	}

	/**
	 * @return the isView
	 */
	public String getIsView() {
		return isView;
	}

	/**
	 * @param isView the isView to set
	 */
	public void setIsView(String isView) {
		this.isView = isView;
	}

	private long recordVersion;

	/**
	 * @return the contractTempletId
	 */
	public String getContractTempletId() {
		return contractTempletId;
	}

	/**
	 * @param contractTempletId the contractTempletId to set
	 */
	public void setContractTempletId(String contractTempletId) {
		this.contractTempletId = contractTempletId;
	}

	/**
	 * @return the contractTempletTypeId
	 */
	public String getContractTempletTypeId() {
		return contractTempletTypeId;
	}
	/**
	 * @return the iscurversion
	 */
	public Byte getIscurversion() {
		return iscurversion;
	}

	/**
	 * @param iscurversion the iscurversion to set
	 */
	public void setIscurversion(Byte iscurversion) {
		this.iscurversion = iscurversion;
	}
	/**
	 * @param contractTempletTypeId the contractTempletTypeId to set
	 */
	public void setContractTempletTypeId(String contractTempletTypeId) {
		this.contractTempletTypeId = contractTempletTypeId;
	}

	/**
	 * @return the templetName
	 */
	public String getTempletName() {
		return templetName;
	}

	/**
	 * @param templetName the templetName to set
	 */
	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	/**
	 * @return the isvalid
	 */
	public Byte getIsvalid() {
		return isvalid;
	}

	/**
	 * @param isvalid the isvalid to set
	 */
	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
	}

	/**
	 * @return the sequenceNo
	 */
	public Long getSequenceNo() {
		return sequenceNo;
	}

	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	/**
	 * @return the templetPath
	 */
	public String getTempletPath() {
		return templetPath;
	}

	/**
	 * @param templetPath the templetPath to set
	 */
	public void setTempletPath(String templetPath) {
		this.templetPath = templetPath;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the recordVersion
	 */
	public long getRecordVersion() {
		return recordVersion;
	}

	/**
	 * @param recordVersion the recordVersion to set
	 */
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}
}
