/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringSecurityUtils.java 515 2009-10-01 16:19:34Z calvinxiu $
 */
package org.springside.modules.security.springsecurity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.service.AcgiUser;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsAcctSysposRel;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * SpringSecurity的工具类.
 * 
 * @author calvin
 */
public class SpringSecurityUtils {

	/**
	 * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUiid() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	/**
	 * 判断当前用户是否开通eas
	 * 
	 * @return
	 */
	public static boolean enableEas() {
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null)
			return Util.easEnable(acct.getEasFlg());
		else
			return false;
	}

	// eas/mysoft
	public static final int SYS_TYPE_CD_EAS = 1;
	public static final int SYS_TYPE_CD_MYSOFT = 2;
	public static final int SYS_TYPE_CD_COREMAIL_SID = 3;

	public static String[] getEasSsoFields() {
		return Util.getPlasService().getParam(SYS_TYPE_CD_EAS, getCurrentAcctId());
	}

	public static String[] getMysoftFields() {
		return Util.getPlasService().getParam(SYS_TYPE_CD_MYSOFT, getCurrentAcctId());
	}

	public static String[] getCoreMailFields() {
		String[] fields = Util.getPlasService().getParam(SYS_TYPE_CD_COREMAIL_SID, getCurrentAcctId());
		String localAddr = ServletActionContext.getRequest().getLocalAddr();
		String server = fields[0];
		if (StringUtils.isNotBlank(server)) {
			if (localAddr.equals("wt.powerlong.com")) {
				server = "http://mailwt.powerlong.com";
			}
		}
		fields[0] = server;
		return fields;
	}

	public static String[] getCurrentRoleCds() {
		GrantedAuthority[] authorities = getCurrentUser().getAuthorities();
		String[] roleCds = new String[authorities.length];
		for (int i = 0; i < authorities.length; i++) {
			roleCds[i] = authorities[i].getAuthority();
		}
		return roleCds;
	}

	public static boolean hasRole(String roleCd) {
		GrantedAuthority[] authorities = getCurrentUser().getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(roleCd))
				return true;
		}
		return false;
	}

	/**
	 * 根据机构类型取得当前用户的机构
	 * <ul>
	 * <b>orgType</b> 机构类型
	 * <li>Constants.ORG_TYPE_CD_BM 部门
	 * <li>Constants.ORG_TYPE_CD_FG 分管
	 * <li>Constants.ORG_TYPE_CD_ZX 中心
	 * <li>Constants.ORG_TYPE_CD_JT 集团
	 * <li>Constants.ORG_TYPE_CD_XZ 小组
	 * 
	 * @param orgTypeCd
	 * @return
	 */
	public static WsPlasOrg getCurrentOrgByType(String orgTypeCd) {
		WsPlasOrg org = null;
		AcgiUser acgiUser = (AcgiUser) SpringSecurityUtils.getCurrentUser();
		if (acgiUser.getWsPlasOrgs() == null || acgiUser.getWsPlasOrgs().size() == 0) {
			acgiUser.setWsPlasOrgs(PlasCache.getLogicalBubbleOrgListByUiid(getCurrentUiid()));
		}
		for (WsPlasOrg wsUaapOrg : acgiUser.getWsPlasOrgs()) {
			System.out.println(wsUaapOrg.getOrgCd()+"," + wsUaapOrg.getOrgName());
			if (orgTypeCd.equals(wsUaapOrg.getOrgTypeCd())) {
				org = wsUaapOrg;
				break;
			}
		}
		return org;
	}

	public static String getCurrentUserCd() {
		String userCd = null;
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null) {
			userCd = acct.getUserCd();
		}
		if (userCd == null)
			return "";
		return userCd;
	}

	public static String getCurrentUserName() {
		String userName = null;
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null) {
			userName = acct.getUserName();
		}
		if (userName == null)
			return "";
		return userName;
	}
	public static String getCurrentPermissionLevelName() {
		String uiid=getCurrentUiid();
		WsPlasUser wsPlasUser= PlasCache.getUserByUiid(uiid);
		String permisionName=null;
		if (wsPlasUser!=null){
			Map<String, String> perm= PlasCache.getDictDataMap("PLAS_PERM_LEVEL");
			permisionName=perm.get(wsPlasUser.getPermissionLevelCd());
		}
		return StringUtils.trimToEmpty(permisionName);
	}
	public static Date getAttendWorkDate() {
		String uiid=getCurrentUiid();
		WsPlasUser wsPlasUser= PlasCache.getUserByUiid(uiid);
		if (wsPlasUser!=null)
			return wsPlasUser.getAttendWorkDate();
		return null;
	}

	public static String getCurrentDeptName() {
		String deptName = null;
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null) {
			deptName = CodeNameUtil.getDeptNameByCd(acct.getOrgCd());
		}
		if (deptName == null)
			return "";
		return deptName;
	}

	/**
	 * 获取当前用户的机构CD
	 * 
	 * @return
	 */
	public static String getCurrentDeptCd() {
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null)
			return getCurrentPlasUser().getOrgCd();
		else
			return null;
	}

	/**
	 * 取得当前用户, 返回值为SpringSecurity的User类及其子类, 如果当前用户未登录则返回null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends UserDetails> T getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal == null)
				return null;
			return (T) principal;
		}
		return null;
	}

	public static WsPlasAcct getCurrentPlasUser() {
		UserDetails userDetails = SpringSecurityUtils.getCurrentUser();
		if (userDetails != null)
			return ((AcgiUser) userDetails).getAcct();
		else
			return null;

	}

	public static String getCurrentUserId() {
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null)
			return getCurrentPlasUser().getUserId();
		else
			return null;
	}

	public static String getCurrentAcctId() {
		WsPlasAcct acct = getCurrentPlasUser();
		if (acct != null)
			return getCurrentPlasUser().getPlasAcctId();
		else
			return null;
	}

	public static String getCurrentCenterCd() {
		WsPlasAcct wsPlasAcct = getCurrentPlasUser();
		if (wsPlasAcct != null) {
			String orgId = wsPlasAcct.getOrgId();
			List<WsPlasOrg> list = PlasCache.getLogicalBubbleOrgListByOrgId(orgId, DictContants.UAAP_ORG_TYPE_CENTER);// 中心
			if (list == null || list.size() == 0)
				return "";
			else
				return list.get(0).getOrgCd();
		} else
			return "";
	}
	
	/**
	 * 取得当前用户所处的区域机构
	 * @return
	 */
	public static String getCurrentAreaOrg(){
		String orgCd=null;
		//北区
		Map<String, String> mapNorth = PlasCache.getDictDataMap(DictContants.AREA_ORG_NORTH);
		//南区
		Map<String, String> mapSouth = PlasCache.getDictDataMap(DictContants.AREA_ORG_SOUTH);
		String centerCd=getCurrentCenterCd();
		if (mapNorth.containsKey(centerCd)){
			orgCd= Constants.ORG_CD_BFQY;
		}else if (mapSouth.containsKey(centerCd)){
			orgCd= Constants.ORG_CD_NFQY;
		}
		return orgCd;
	}

	public static String getCurrentPositionCd() {
		List<WsAcctSysposRel> list = PlasCache.getPosListByUiid(getCurrentUiid());// 系统职位
		if (list == null || list.size() == 0)
			return "";
		else
			return list.get(0).getSysPosCd();
	}

	public static String getRealPositonName() {
		WsPlasAcct wsPlasAcct = getCurrentPlasUser();
		String realPositionName = null;
		if (wsPlasAcct != null) {
			realPositionName = wsPlasAcct.getRealPositonName();
		}
		return realPositionName;
	}

	public static void setCurrentPlasUser(WsPlasAcct acct) {
		((AcgiUser) SpringSecurityUtils.getCurrentUser()).setAcct(acct);
	}

	// 获取密码原文
	public static String getCurPassword() {
		String password = LoginUtil.getPwd(null);
		return password;
	}

	// 是否修改密码 1-需要修改 0-不需要修改
	public static String isPwdModFlag() {
		WsPlasAcct acct = Util.getPlasService().getAcctByUiid(getCurrentUiid());
		String tmpPwdStrategyCd = acct.getPwdStrategyCd();
		if (StringUtils.isNotBlank(tmpPwdStrategyCd)) {
			if (acct.getPwdLastModDate() != null) {
				Long tRegion = DateOperator.compareDays(acct.getPwdLastModDate(), new Date());
				if (DictContants.PLAS_PWD_STRATEGY_1.equals(tmpPwdStrategyCd)) {
					if (tRegion > 30)
						return "1";
				}
				if (DictContants.PLAS_PWD_STRATEGY_2.equals(tmpPwdStrategyCd)) {
					if (tRegion > 60)
						return "1";
				}
				if (DictContants.PLAS_PWD_STRATEGY_3.equals(tmpPwdStrategyCd)) {
					if (tRegion > 90)
						return "1";
				}
				if (DictContants.PLAS_PWD_STRATEGY_9.equals(tmpPwdStrategyCd))
					return "1";
			}
		}
		return "0";
	}
	
	//获取当前用户的系统机构节点cd
	public static List<String> getCurNodeLevelList(){
		return PlasCacheUtil.getCurNodeLevelList(getCurrentUiid());
	}
}
