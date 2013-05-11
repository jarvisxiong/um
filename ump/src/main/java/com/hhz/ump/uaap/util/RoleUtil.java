package com.hhz.ump.uaap.util;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.service.AcgiUser;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsPlasRole;

public class RoleUtil {

	// 超级管理员
	public static boolean isAdminSupser() {
		return validateRole(GlobalConstants.A_ADMIN_SUPER);
	}

	// 应用管理员
	public static boolean isAdmin() {
		return validateRole(GlobalConstants.A_ADMIN);
	}

	// 机构管理员
	public static boolean isAdminUaapOrg() {
		return validateRole(GlobalConstants.A_ADMIN_UAAP_ORG);
	}

	// 比较角色
	public static boolean validateRole(String roleCd) {
		
		AcgiUser user = (AcgiUser) SpringSecurityUtils.getCurrentUser();
		List<WsPlasRole> roles = user.getRoleList();
		WsPlasRole role  = null;
		for (int i = 0; i < roles.size(); i++) {
			role= roles.get(i);
			if (roleCd.equals(role.getRoleCd()))
				return true;
		}
		return false;
	}

	public static boolean containAnyRole(List<String> roleCdList,List<String> hrCdList) {
		
		if(roleCdList != null){
			String hrCd  = null;
			for (int i = 0; i < hrCdList.size(); i++) {
				hrCd = hrCdList.get(i);
				if(StringUtils.isNotBlank(hrCd)){
					if (roleCdList.contains(hrCd))
						return true;
				}
			}
		}
		return false;
	} 

	public static boolean getHrRole(List<String> roleCdList,List<String> hrCdList) {
		
		if(roleCdList != null){
			String hrCd  = null;
			for (int i = 0; i < hrCdList.size(); i++) {
				hrCd = hrCdList.get(i);
				if(StringUtils.isNotBlank(hrCd)){
					if (roleCdList.contains(hrCd))
						return true;
				}
			}
		}
		return false;
	} 
	public static void main(String[] args) {

	}
}
