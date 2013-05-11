/**
 * 
 */
package com.hhz.ump.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 判断是否拥有菜单权限
 * 
 * @author huangj 2010-3-20
 */
public class PermissionTag extends ComponentTagSupport {

	private static final long serialVersionUID = -8047361208197518243L;

	/**
	 * menuCd,functionCd
	 */
	private String key;

	/**
	 * menu,function
	 */
	private String type;
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return new Permission(stack);
	}

	@Override
	protected void populateParams() {
		((Permission) getComponent()).setKey(key);
		((Permission) getComponent()).setType(type);
	}

	public void setKey(String menuCd) {
		this.key = menuCd;
	}

	public void setType(String type) {
		this.type = type;
	}

}
