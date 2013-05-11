package com.hhz.uums.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.springsecurity.ResourceDetailsService;

import com.hhz.uums.dao.app.AppRoleMenuRelManager;
import com.hhz.uums.dao.app.AppRoleResourceRelManager;
import com.hhz.uums.entity.app.AppRoleMenuRel;
import com.hhz.uums.entity.app.AppRoleResourceRel;
import com.hhz.uums.utils.GlobalConstants;

/**
 * 从数据库查询URL--授权定义Map的实现类.
 * 
 * 
 */
@Transactional(readOnly = true)
public class ResourceDetailsServiceImpl implements ResourceDetailsService {
	@Autowired
	private AppRoleResourceRelManager appRoleResourceRelManager;

	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;
	/**
	 * @see ResourceDetailsService#getRequestMap()
	 */
	public LinkedHashMap<String, List<String>> getRequestMap() throws Exception {
		LinkedHashMap<String, List<String>> requestMap = new LinkedHashMap<String, List<String>>();
		List<AppRoleMenuRel> lstAppRoleMenuRel = appRoleMenuRelManager.getAll();
		for (AppRoleMenuRel appRoleMenuRel : lstAppRoleMenuRel) {
			String sourcePath = appRoleMenuRel.getAppMenu().getAppPage().getPagePath();
			List<String> lstAuthority = requestMap.get(sourcePath);
			if (lstAuthority == null) {
				lstAuthority = new ArrayList<String>();
			}
			lstAuthority.add(appRoleMenuRel.getRoleCd());
			requestMap.put(sourcePath, lstAuthority);
		}
		List<AppRoleResourceRel> appResources = appRoleResourceRelManager.getAll();
		for (AppRoleResourceRel resourceRel : appResources) {
			List<String> lstAuthority = requestMap.get(resourceRel.getResourceValue());
			if (lstAuthority == null) {
				lstAuthority = new ArrayList<String>();
			}
			lstAuthority.add(resourceRel.getRoleCd());
			requestMap.put(resourceRel.getResourceValue(), lstAuthority);
		}
		//决定用户查看模块的权限
		List<String> lstRoleCd = new ArrayList<String>();
		lstRoleCd.add(GlobalConstants.A_ADMIN);//应用管理员
		lstRoleCd.add(GlobalConstants.A_ADMIN_AUDIT);
		lstRoleCd.add(GlobalConstants.A_ADMIN_SUPER);//超级管理员
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_ORG);
//		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_USER);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_APP);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_ROLE);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_RESOURCE);
		lstRoleCd.add(GlobalConstants.A_USER);
//		requestMap.put("/admin/index.action", lstRoleCd);
		requestMap.put("/**", lstRoleCd);
		
		return requestMap;
	}
}
