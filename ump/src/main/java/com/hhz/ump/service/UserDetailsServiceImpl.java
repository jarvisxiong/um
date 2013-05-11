/**
 * 
 */
package com.hhz.ump.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author huangj 2009-12-4
 */

@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException, DataAccessException {
		SecurityContextHolder.getContext();

		String uiid = userCode;
		// 搜索用户
		WsPlasAcct acct = Util.getPlasService().getAcctByUiid(uiid);
		if (acct == null)
			throw new UsernameNotFoundException("用户或密码不存在，请重试！");

		
		List<WsPlasRole> lstRole = Util.getPlasService().getRoleListByUiid(uiid);
		//处理空,很重要
		if(lstRole == null){
			lstRole = new ArrayList<WsPlasRole>();
		}else{
//			for (WsPlasRole role : lstRole) {
//				System.out.println(role.getRoleCd()+"/ "+ role.getRoleName());
//			}
		}

//		//针对人员模块,为了减少plas负担，这里缓存plas角色
//		WSPlasService service = Util.getPlasService();
//		service.setAppBizCd(GlobalConstants.UAAP_BIZ_CD);
//		List<WsPlasRole> plasroles = service.getRoleListByUiid(SpringSecurityUtils.getCurrentUiid());
//		if(plasroles == null){
//		}else{
//			lstRole.addAll(plasroles);
////			for (WsPlasRole role : plasroles) {
////				System.out.println(role.getRoleCd()+"/ "+ role.getRoleName());
////			}
//		}
			
		// 安全机制用户
		AcgiUser acgiUser = new AcgiUser(acct);
		acgiUser.setRoleList(lstRole);
		acgiUser.setGrantedAuths(obtainGrantedAuthorities(lstRole));
		return acgiUser;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private GrantedAuthority[] obtainGrantedAuthorities(List<WsPlasRole> lstRole) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (int i = 0; i < lstRole.size(); i++) {
			authSet.add(new GrantedAuthorityImpl(lstRole.get(i).getRoleCd()));
		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}
}
