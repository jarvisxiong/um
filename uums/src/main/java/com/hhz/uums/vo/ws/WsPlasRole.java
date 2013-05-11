// 
package com.hhz.uums.vo.ws;

import java.io.Serializable;

public class WsPlasRole implements Serializable {

	private static final long serialVersionUID = -6030493144663861218L;

	private String plasRoleId;

	// plasApp
	private String appId;
	private String appCd;
	private String appChnName;
	private String appEngName;

	// PlasRoleGroup
	private String groupId;
	private String groupCd;
	private String groupName;
	private Long groupSeqNo;

	private String roleCd;
	private String roleBizCd;
	private String roleName;
	private Long sequenceNo;
	private String remark;

	public String getPlasRoleId() {
		return plasRoleId;
	}

	public void setPlasRoleId(String plasRoleId) {
		this.plasRoleId = plasRoleId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppCd() {
		return appCd;
	}

	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}

	public String getAppChnName() {
		return appChnName;
	}

	public void setAppChnName(String appChnName) {
		this.appChnName = appChnName;
	}

	public String getAppEngName() {
		return appEngName;
	}

	public void setAppEngName(String appEngName) {
		this.appEngName = appEngName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupCd() {
		return groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getRoleBizCd() {
		return roleBizCd;
	}

	public void setRoleBizCd(String roleBizCd) {
		this.roleBizCd = roleBizCd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Long getGroupSeqNo() {
		return groupSeqNo;
	}

	public void setGroupSeqNo(Long groupSeqNo) {
		this.groupSeqNo = groupSeqNo;
	}

}
