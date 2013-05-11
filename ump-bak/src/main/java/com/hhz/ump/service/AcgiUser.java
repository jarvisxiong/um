package com.hhz.ump.service;

import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasRole;

public class AcgiUser implements UserDetails {

	private static final long serialVersionUID = 6972824651204409336L;

	private WsPlasAcct acct;
	private List<WsPlasRole> roleList;// UAAP_ROLE_LIST
	private List<WsPlasOrg> wsPlasOrgs;

	public AcgiUser(WsPlasAcct acct) {
		this.acct = acct;
	}

	// 授权 TODO
	private GrantedAuthority[] grantedAuths;

	@Override
	public String getPassword() {
		return acct.getLoginInPassword();
	}

	@Override
	public String getUsername() {
		return acct.getUiid();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public GrantedAuthority[] getAuthorities() {

		return grantedAuths;
	}

	public void setGrantedAuths(GrantedAuthority[] grantedAuths) {
		this.grantedAuths = grantedAuths;
	}

	public List<WsPlasRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<WsPlasRole> roleList) {
		this.roleList = roleList;
	}

	public List<WsPlasOrg> getWsPlasOrgs() {
		return wsPlasOrgs;
	}

	public void setWsPlasOrgs(List<WsPlasOrg> wsPlasOrgs) {
		this.wsPlasOrgs = wsPlasOrgs;
	}

	public WsPlasAcct getAcct() {
		return acct;
	}

	public void setAcct(WsPlasAcct acct) {
		this.acct = acct;
	}
 
 
}
