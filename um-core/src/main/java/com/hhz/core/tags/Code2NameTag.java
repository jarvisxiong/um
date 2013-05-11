/**
 * 
 */
package com.hhz.core.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * Code转Name标签，可设置Map<String,String>或两个字符串数组名称
 * 
 * @author huangj 2009-12-8
 */
public class Code2NameTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1030661069553639860L;

	private String defaultValue;

	private String value;

	private boolean escape = true;

	private boolean escapeJavaScript = false;

	private String codeKey;

	private String nameKey;

	private String mapCodeName;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new Code2Name(stack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		Code2Name tag = (Code2Name) component;
		tag.setDefault(defaultValue);
		tag.setValue(value);
		tag.setEscape(escape);
		tag.setEscapeJavaScript(escapeJavaScript);
		tag.setCodeKey(codeKey);
		tag.setNameKey(nameKey);
		tag.setMapCodeName(mapCodeName);
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	public void setEscapeJavaScript(boolean escapeJavaScript) {
		this.escapeJavaScript = escapeJavaScript;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	public void setMapCodeName(String map) {
		this.mapCodeName = map;
	}
}
