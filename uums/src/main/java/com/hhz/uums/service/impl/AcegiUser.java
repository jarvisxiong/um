/**
 * 
 */
package com.hhz.uums.service.impl;

import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.vo.ws.WsPlasRole;

/**
 * @author huangj 2010-12-27
 */
public class AcegiUser implements UserDetails {
	private static final long serialVersionUID = 3931135519863477788L;

	private WsPlasAcct acct;
	private List<WsPlasRole> roleList;// UAAP_ROLE_LIST

	// 授权 TODO
	private GrantedAuthority[] grantedAuths;
	private String roleCds;

	public AcegiUser(WsPlasAcct acct) {
		this.acct = acct;
	}

	public AcegiUser(WsPlasAcct acct, GrantedAuthority[] grantedAuths) {
		this.acct = acct;
		this.grantedAuths = grantedAuths;
		StringBuffer sbAuthority = new StringBuffer();
		int index = 0;
		for (GrantedAuthority authority : grantedAuths) {
//			sbAuthority.append("'");
			sbAuthority.append(authority);
//			sbAuthority.append("'");
			if (index < grantedAuths.length-1) {
				sbAuthority.append(",");
			}
			index++;
		}
		roleCds = sbAuthority.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		return grantedAuths;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {

		return acct.getLoginInPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return acct.getUiid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isAccountNonExpired
	 * ()
	 */
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired
	 * ()
	 */
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
 
	public String getRoleCds() {
		return roleCds;
	}

	public WsPlasAcct getAcct() {
		return acct;
	}

	public void setAcct(WsPlasAcct acct) {
		this.acct = acct;
	}
}
