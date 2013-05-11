// 
package com.hhz.uums.entity.ws;

// Generated 2010-1-12 16:06:03 by Hibernate Tools 3.2.4.GA


public class WsAppDictData implements java.io.Serializable {

	private static final long serialVersionUID = 5106232028687674475L;

    private String appDictDataId;
    
    //appDictType
    private String dictTypeId;
    private String dictTypeCd;
    private String dictTypeName;
    
    private String dictCd;
    private String dictName;
    private Long dictLevelNum;
    private Boolean defaultFlg;
    private Long sequenceNo;
    private String remark;
	public String getAppDictDataId() {
		return appDictDataId;
	}
	public void setAppDictDataId(String appDictDataId) {
		this.appDictDataId = appDictDataId;
	}
	public String getDictTypeId() {
		return dictTypeId;
	}
	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
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
	public String getDictCd() {
		return dictCd;
	}
	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Long getDictLevelNum() {
		return dictLevelNum;
	}
	public void setDictLevelNum(Long dictLevelNum) {
		this.dictLevelNum = dictLevelNum;
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
