/**
 * 
 */
package com.hhz.core.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 翻页控件
 * 
 * @author huangj 2009-12-7
 */
public class PageTag extends AbstractUITag {

	private static final long serialVersionUID = 6282249467078348693L;

	private String formId = "mainForm";

	private String pageInfo = "page";

	private String key = "";

	private String showInput = "true";

	private String createAttr = "true";// 是否生成隐藏变量
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new Page(stack, request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		Page page = ((Page) component);
		page.setFormId(formId);
		page.setPageInfo(pageInfo);
		page.setShowInput(showInput);
		page.setCreateAttr(createAttr);
		page.setKey(key);
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setPageInfo(String page) {
		this.pageInfo = page;
	}

	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	public void setShowInput(String showInput) {
		this.showInput = showInput;
	}

	public String getCreateAttr() {
		return createAttr;
	}

	public void setCreateAttr(String createAttr) {
		this.createAttr = createAttr;
	}

}
