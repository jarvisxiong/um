/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringSecurityUtils.java 515 2009-10-01 16:19:34Z calvinxiu $
 */
package org.springside.modules.security.springsecurity;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

import com.hhz.uums.service.impl.AcegiUser;
import com.hhz.uums.vo.ws.WsPlasAcct;

/**
 * SpringSecurity的工具类.
 * 
 * @author calvin
 */
public class SpringSecurityUtils {
	
	// 取得当前账户的账号, 如果当前账户未登录则返回空字符串.
	public static String getLoginCode() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	// 获取当前账户的姓名
	public static String getCurUserName() {
		return getCurPlasAcct().getUserName();
	}

	// 获取当前账号的部门
	public static String getCurDeptName() {
		return getCurPlasAcct().getOrgName();
	}
	// 获取当前账户UIID
	public static String getCurUiid() {
		return getCurPlasAcct().getUiid();
	}
	/**
	 * 获取当前用户的机构CD
	 * 
	 * @return
	 */
	public static String getCurrentDeptCd() {
		return getCurPlasAcct().getOrgCd();
	} 
	public static String getCurrentCenterCd() {
		return "";
	} 
	public static String getCurrentPositionCd() {
		return "";
	} 
	

	//取得当前账户, 返回值为SpringSecurity的User类及其子类, 如果当前账户未登录则返回null.
	public static AcegiUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null)
			return null;
		return (AcegiUser) principal;
	}
	
	public static String getCurrentUiid() {
		return getCurrentUser().getAcct().getUiid();
	}

	// 获取当前账户对象
	public static WsPlasAcct getCurPlasAcct() {
		return getCurrentUser().getAcct();
	}
	
	// 获取当前账户ID
	public static String getCurPlasAcctId() {
		return getCurPlasAcct().getPlasAcctId();
	} 
	// 获取当前用户ID
	public static String getCurPlasUserId() {
		return getCurPlasAcct().getUserId();
	} 
}
