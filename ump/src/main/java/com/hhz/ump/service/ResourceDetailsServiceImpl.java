package com.hhz.ump.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.springsecurity.ResourceDetailsService;

import com.hhz.ump.dao.app.AppRoelResourceRelManager;
import com.hhz.ump.dao.app.AppRoleMenuRelManager;
import com.hhz.ump.entity.app.AppRoelResourceRel;
import com.hhz.ump.entity.app.AppRoleMenuRel;
import com.hhz.ump.util.GlobalConstants;

/**
 * 从数据库搜索URL--授权定义Map的实现类.
 * 
 * 
 */
@Transactional(readOnly = true)
public class ResourceDetailsServiceImpl implements ResourceDetailsService {
	@Autowired
	private AppRoelResourceRelManager appRoelResourceRelManager;

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
		List<AppRoelResourceRel> resourceRels = appRoelResourceRelManager.getAll();
		for (AppRoelResourceRel appRoelResourceRel : resourceRels) {
			List<String> lstAuthority = requestMap.get(appRoelResourceRel.getResourceValue());
			if (lstAuthority == null) {
				lstAuthority = new ArrayList<String>();
			}
			lstAuthority.add(appRoelResourceRel.getRoleCd());
			requestMap.put(appRoelResourceRel.getResourceValue(), lstAuthority);
		}

		List<String> lstRoleCd = new ArrayList<String>();
		lstRoleCd.add(GlobalConstants.A_ADMIN);
		lstRoleCd.add(GlobalConstants.A_ADMIN_SUPER);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_ORG);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_USER);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_APP);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_ROLE);
		lstRoleCd.add(GlobalConstants.A_ADMIN_UAAP_RESOURCE);
		lstRoleCd.add(GlobalConstants.A_USER);
		lstRoleCd.add(GlobalConstants.A_COM_LOGIN);

		requestMap.put("/**", lstRoleCd);

		return requestMap;
	}
}
