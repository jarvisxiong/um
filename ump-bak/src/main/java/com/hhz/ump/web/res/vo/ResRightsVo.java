package com.hhz.ump.web.res.vo;
/**
 * 权责表对象
 * @author Aspenn
 *
 */
public class ResRightsVo {
    private String resRightsId;
    private String rightsName;
    private String parentId;
    private String resAuthTypeId;
    private String resAuthTypeCd;
    private Long sequenceNo;
    private Boolean ifActive;
    private String ifActive_input;
    private String resOptionId;
    private String resOptionValueId;
    private String turnAfterOptionId;
    private String remark;
    
    
	public ResRightsVo() {
		super();
	}
	
	public ResRightsVo(String resRightsId, String rightsName, String parentId,
			String resAuthTypeId, String resAuthTypeCd, Long sequenceNo,
			Boolean ifActive, String ifActiveInput, String resOptionId,
			String resOptionValueId, String turnAfterOptionId, String remark) {
		super();
		this.resRightsId = resRightsId;
		this.rightsName = rightsName;
		this.parentId = parentId;
		this.resAuthTypeId = resAuthTypeId;
		this.resAuthTypeCd = resAuthTypeCd;
		this.sequenceNo = sequenceNo;
		this.ifActive = ifActive;
		ifActive_input = ifActiveInput;
		this.resOptionId = resOptionId;
		this.resOptionValueId = resOptionValueId;
		this.turnAfterOptionId = turnAfterOptionId;
		this.remark = remark;
	}

	public String getResRightsId() {
		return resRightsId;
	}
	public void setResRightsId(String resRightsId) {
		this.resRightsId = resRightsId;
	}
	public String getRightsName() {
		return rightsName;
	}
	public void setRightsName(String rightsName) {
		this.rightsName = rightsName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResAuthTypeId() {
		return resAuthTypeId;
	}
	public void setResAuthTypeId(String resAuthTypeId) {
		this.resAuthTypeId = resAuthTypeId;
	}
	public String getResAuthTypeCd() {
		return resAuthTypeCd;
	}
	public void setResAuthTypeCd(String resAuthTypeCd) {
		this.resAuthTypeCd = resAuthTypeCd;
	}
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public Boolean getIfActive() {
		return ifActive;
	}
	public void setIfActive(Boolean ifActive) {
		this.ifActive = ifActive;
	}
	public String getIfActive_input() {
		return ifActive_input;
	}
	public void setIfActive_input(String ifActiveInput) {
		ifActive_input = ifActiveInput;
	}
	public String getResOptionId() {
		return resOptionId;
	}
	public void setResOptionId(String resOptionId) {
		this.resOptionId = resOptionId;
	}
	public String getResOptionValueId() {
		return resOptionValueId;
	}
	public void setResOptionValueId(String resOptionValueId) {
		this.resOptionValueId = resOptionValueId;
	}
	public String getTurnAfterOptionId() {
		return turnAfterOptionId;
	}
	public void setTurnAfterOptionId(String turnAfterOptionId) {
		this.turnAfterOptionId = turnAfterOptionId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
