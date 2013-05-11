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
public class ContentI18NTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1030661069553639860L;



	private boolean escape = true;

	private boolean escapeJavaScript = false;

	private String name;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new ContentI18N(stack);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		ContentI18N tag = (ContentI18N) component;
		tag.setEscape(escape);
		tag.setEscapeJavaScript(escapeJavaScript);
		tag.setName(name);
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	public void setEscapeJavaScript(boolean escapeJavaScript) {
		this.escapeJavaScript = escapeJavaScript;
	}



	public void setName(String nameKey) {
		this.name = nameKey;
	}

}
