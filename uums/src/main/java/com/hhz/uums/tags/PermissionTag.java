/**
 * 
 */
package com.hhz.uums.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppRoleFunctionRelManager;
import com.hhz.uums.dao.app.AppRoleMenuRelManager;

/**
 * @author huangjian
 * 
 *         2011-1-27
 */
public class PermissionTag extends TagSupport {
	private static final long serialVersionUID = 8871674262768723891L;
	private AppRoleMenuRelManager appRoleMenuRelManager = SpringContextHolder.getBean("appRoleMenuRelManager");
	private AppRoleFunctionRelManager appRoleFunctionRelManager = SpringContextHolder.getBean("appRoleFunctionRelManager");

	/**
	 * menuCd,functionCd
	 * @eg mnu_XX,fun_XX
	 */
	private String key;

	/**
	 * menu,fun
	 */
	private String type;
	@Override
	public int doStartTag() throws JspException {
		boolean isPermission=false;
		if (StringUtils.equals(type, "menu")){
			isPermission=appRoleMenuRelManager.isPermission(key);
		}else if (StringUtils.equals(type, "fun")){
			isPermission=appRoleFunctionRelManager.isPermission(key);
		}
		if (isPermission)
			return TagSupport.EVAL_BODY_INCLUDE;
		else
			return TagSupport.SKIP_BODY;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setType(String type) {
		this.type = type;
	}
}
