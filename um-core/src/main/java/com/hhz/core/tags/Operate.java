/**
 * 
 */
package com.hhz.core.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;

import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.MessageUtils;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author huangj 2009-12-8
 */
public class Operate extends UIBean {
	final public static String TEMPLATE = "operate";

	private String action;

	private String enableName;

	private String idName;

	private String mapEnabled;
	public Operate(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		super(stack, request, response);
		// TODO Auto-generated constructor stub
	}

	private String getValue() {

		String rtnVal = null;
		rtnVal = (String) getStack().findValue(enableName, String.class, throwExceptionOnELFailure);
		if (enableName == null) {
			enableName = "top";
		} else {
			enableName = stripExpressionIfAltSyntax(enableName);
		}

		return rtnVal;

	}

	@Override
	public void evaluateParams() {
		// TODO Auto-generated method stub
		super.evaluateParams();
		if (enableName != null) {
			String enableVal = null;
			String enableKey = getValue();
			enableKey = enableKey == null ? "0" : enableKey;
			Map<String, String> mapKeyValue = (Map<String, String>) findValue(mapEnabled);
			if (mapKeyValue != null) {
				enableVal = mapKeyValue.get(enableKey);
			} else {
				enableVal = CoreContants.getMapEnable().get(enableKey);
			}
			addParameter("enableKey", enableKey.equals("1") ? "0" : "1");
			addParameter("enableVal", enableVal);
		}
		String idValue = (String) getStack().findValue(idName, String.class, throwExceptionOnELFailure);
		addParameter("action", action);
		addParameter("enableName", enableName);
		addParameter("idValue", idValue);
		addParameter("editInfo", MessageUtils.getText("common.edit"));
		addParameter("deleteInfo", MessageUtils.getText("common.delete"));
		addParameter("ctx", request.getContextPath());
	}

	@Override
	public String getTheme() {
		return "mytheme";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.components.UIBean#getDefaultTemplate()
	 */
	@Override
	protected String getDefaultTemplate() {
		// TODO Auto-generated method stub
		return TEMPLATE;
	}

	public void setAction(String action) {
		this.action = action;
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
