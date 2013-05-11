// 
package com.hhz.uums.vo.log;

import java.io.Serializable;
import java.math.BigDecimal;



public class LogPlasRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8067275359120245014L;
	private String uaapRoleId;
	private String roleCd;
	private String roleBizCd;
	private String roleName;
	private String roleTypeCd;
	private String roleTypeName;

	private BigDecimal dispOrderNo;
	private String remark;

	private String appBizCd;
	private String appEngName;
	private String appChnName;

	public String getAppBizCd() {
		return appBizCd;
	}

	public void setAppBizCd(String appBizCd) {
		this.appBizCd = appBizCd;
	}

	public String getAppEngName() {
		return appEngName;
	}

	public void setAppEngName(String appEngName) {
		this.appEngName = appEngName;
	}

	public String getAppChnName() {
		return appChnName;
	}

	public void setAppChnName(String appChnName) {
		this.appChnName = appChnName;
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
	public String getRoleTypeCd() {
		return roleTypeCd;
	}
	public void setRoleTypeCd(String roleTypeCd) {
		this.roleTypeCd = roleTypeCd;
	}

	public String getRoleTypeName() {
		return roleTypeName;
	}

	public void setRoleTypeName(String roleTypeName) {
		this.roleTypeName = roleTypeName;
	}

	public BigDecimal getDispOrderNo() {
		return dispOrderNo;
	}

	public void setDispOrderNo(BigDecimal dispOrderNo) {
		this.dispOrderNo = dispOrderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getUaapRoleId() {
		return uaapRoleId;
	}

	public void setUaapRoleId(String uaapRoleId) {
		this.uaapRoleId = uaapRoleId;
	}

}


