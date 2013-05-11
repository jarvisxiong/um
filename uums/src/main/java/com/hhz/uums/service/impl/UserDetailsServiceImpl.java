/**
 * 
 */
package com.hhz.uums.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.vo.ws.WsPlasRole;

/**
 * @author huangj 2009-12-4
 */

@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private PlasAcctManager plasAcctManager; 
	@Autowired
	private PlasRoleManager roleManager; 

	public UserDetails loadUserByUsername(String uiid) throws UsernameNotFoundException, DataAccessException {

		WsPlasAcct acct = plasAcctManager.getWsAcct(uiid);
		
		if (acct == null)
			throw new UsernameNotFoundException("您输入的账号不存在，请重试！");
		
		//检查账户状态
		if(DictContants.PLAS_USER_STATUS_NOENTER.equals(acct.getStatusCd()))
			throw new UsernameNotFoundException("账号未启用,请联系系统管理员！");
		
		if(DictContants.PLAS_USER_STATUS_FREEZE.equals(acct.getStatusCd()))
			throw new UsernameNotFoundException("账号已冻结,请联系系统管理员！");
		
		if(DictContants.PLAS_USER_STATUS_CLOSED.equals(acct.getStatusCd()))
			throw new UsernameNotFoundException("账号已注销,请联系系统管理员！");

		if(!DictContants.PLAS_USER_STATUS_CREATE.equals(acct.getStatusCd()))
			throw new UsernameNotFoundException("账号状态无法识别,请联系系统管理员！");
		

		List<WsPlasRole> lstRole = roleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);
		//处理空,很重要
		if(lstRole == null){
			lstRole = new ArrayList<WsPlasRole>();
		}
		
//		for (WsPlasRole tRole : lstRole) {
//			System.out.println(">>>>>>>>>>>>> " + tRole.getRoleCd() + "," + tRole.getRoleName());
//		}
		
		//只有机构管理员/管理员/超级管理员/HR管理员 才允许登录
		boolean isPermFlag = RoleUtil.isLoginRole(lstRole);
		if(!isPermFlag)
			throw new UsernameNotFoundException("对不起，您未授权！");
		
		// 安全机制用户
		AcegiUser acgiUser = new AcegiUser(acct);
		acgiUser.setRoleList(lstRole);
		acgiUser.setGrantedAuths(obtainGrantedAuthorities(lstRole));
		
		return acgiUser;
	}
	
	/**
	 * 获得用户所有角色的权限集合.
	 */
	private GrantedAuthority[] obtainGrantedAuthorities(List<WsPlasRole> lstRole) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		WsPlasRole tmpRole = null;
		for (int i = 0; i < lstRole.size(); i++) {
			tmpRole= lstRole.get(i);
			if(tmpRole != null){
				authSet.add(new GrantedAuthorityImpl(tmpRole.getRoleCd()));
			}else{
				System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> error: 取到角色为空!");
			}
		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}
}
