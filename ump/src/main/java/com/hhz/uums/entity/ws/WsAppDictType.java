// 
package com.hhz.uums.entity.ws;

// Generated 2011-1-27 16:11:20 by Hibernate Tools 3.2.2.GA


public class WsAppDictType implements java.io.Serializable {

	private static final long serialVersionUID = 2575280883196855657L;

    private String appDictTypeId;
    private String dictTypeCd;
    private String dictTypeName;
    private Boolean defaultFlg;
    private Long sequenceNo;
    private String remark;
    
	public String getAppDictTypeId() {
		return appDictTypeId;
	}
	public void setAppDictTypeId(String appDictTypeId) {
		this.appDictTypeId = appDictTypeId;
	}
	public String getDictTypeCd() {
		return dictTypeCd;
	}
	public void setDictTypeCd(String dictTypeCd) {
		this.dictTypeCd = dictTypeCd;
	}
	public String getDictTypeName() {
		return dictTypeName;
	}
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	public Boolean getDefaultFlg() {
		return defaultFlg;
	}
	public void setDefaultFlg(Boolean defaultFlg) {
		this.defaultFlg = defaultFlg;
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
