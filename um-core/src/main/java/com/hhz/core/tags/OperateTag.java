/**
 * 
 */
package com.hhz.core.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 翻页控件
 * 
 * @author huangj 2009-12-7
 */
public class OperateTag extends AbstractUITag {

	private static final long serialVersionUID = 6282249467078348693L;

	private String action;

	private String enableName = "enable";

	private String mapEnabled = "mapEnabledFlg";

	private String idName;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new Operate(stack, request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		Operate operate = ((Operate) component);
		operate.setAction(action);
		operate.setIdName(idName);
		operate.setMapEnabled(mapEnabled);
		operate.setEnableName(StringUtils.isEmpty(enableName) ? null : enableName);
	}

	public void setAction(String formId) {
		this.action = formId;
	}

	public void setEnableName(String enableName) {
		this.enableName = enableName;
	}

	public void setIdName(String idValue) {
		this.idName = idValue;
	}

	public void setMapEnabled(String mapEnabled) {
		this.mapEnabled = mapEnabled;
	}

}
