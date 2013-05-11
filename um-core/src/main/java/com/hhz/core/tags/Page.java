/**
 * 
 */
package com.hhz.core.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.apache.struts2.components.UIBean;

import com.hhz.core.utils.MessageUtils;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author huangj 2009-12-8
 */
public class Page extends UIBean {
	final public static String TEMPLATE = "page";

	private String formId;

	private String pageInfo;

	private String key;

	private String showInput = "true";
	private String createAttr = "true";// 是否生成隐藏变量
	public Page(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		super(stack, request, response);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evaluateParams() {
		// TODO Auto-generated method stub
		super.evaluateParams();
		org.springside.modules.orm.Page page = (org.springside.modules.orm.Page) findValue(pageInfo);
		if (page != null) {
			addParameter("noInfo", MessageUtils.getText("page.noInfo", String.valueOf(page.getPageNo()), String.valueOf(page.getTotalPages() < 0 ? 0
					: page.getTotalPages())));
			addParameter("firstInfo", MessageUtils.getText("page.firstInfo"));
			addParameter("hasPre", page.isHasPre());
			addParameter("pageNo", page.getPageNo());
			addParameter("prePage", page.getPrePage());
			addParameter("perInfo", MessageUtils.getText("page.perInfo"));
			addParameter("hasNext", page.isHasNext());
			addParameter("nextPage", page.getNextPage());
			addParameter("nextInfo", MessageUtils.getText("page.nextInfo"));
			addParameter("totalPage", page.getTotalPages());
			addParameter("lastInfo", MessageUtils.getText("page.lastInfo"));
			addParameter("orderBy", page.getOrderBy());
			addParameter("order", page.getOrder());
			addParameter("totalCount", MessageUtils.getText("page.totalCount", String.valueOf(page.getTotalCount())));
			addParameter("formId", formId);
			addParameter("page", pageInfo);
			addParameter("key", key);
			addParameter("showInput", BooleanUtils.toBoolean(showInput));
			addParameter("createAttr", BooleanUtils.toBoolean(createAttr));
		}
		// if (size != null) {
		// addParameter("size", findString(size));
		// }
		//
		// if (maxlength != null) {
		// addParameter("maxlength", findString(maxlength));
		// }
		//
		// if (readonly != null) {
		// addParameter("readonly", findValue(readonly, Boolean.class));
		// }
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

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
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
