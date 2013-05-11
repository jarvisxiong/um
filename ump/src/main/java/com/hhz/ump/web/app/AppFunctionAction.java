/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-function.action", type = "redirect") })
public class AppFunctionAction extends CrudActionSupport<AppFunction> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppFunction> page = new Page<AppFunction>(20);// 每页10条记录

	@Autowired
	private AppFunctionManager appFunctionManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private AppFunction entity;

	private String filter_LIKES_functionName;

	private String filter_EQS_functionCd;


	public AppFunctionAction() {
	}

	public AppFunction getModel() {
		return entity;
	}

	public String getFilter_LIKES_functionName() {
		return filter_LIKES_functionName;
	}

	public void setFilter_LIKES_pageName(String filterLIKESFunctionName) {
		filter_LIKES_functionName = filterLIKESFunctionName;
	}

	public String getFilter_EQS_functionCd() {
		return filter_EQS_functionCd;
	}

	public void setFilter_EQS_functionCd(String filterEQSFunctionCd) {
		filter_EQS_functionCd = filterEQSFunctionCd;
	}

	@Override
	public Page<AppFunction> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		String appFunctionId = getId();
		if (StringUtils.isNotBlank(appFunctionId)) {
			entity = appFunctionManager.getEntity(appFunctionId);
		} else {
			entity = new AppFunction();
			entity.setAppPage(new AppPage());
		}
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrder(Page.ASC);
		}

		page = appFunctionManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		entity = appFunctionManager.getEntity(getId());
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {

		String functionCd = entity.getFunctionCd();
		if (StringUtils.isBlank(functionCd)) {
			functionCd = appSeqManager.createNextValue(
					GlobalConstants.SEQ_FUNCTION_CD)
					.toString();
			entity.setFunctionCd(functionCd);
		}

		appFunctionManager.saveAppFunction(entity);
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		try {
			appFunctionManager.deleteAppFunction(getId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			appFunctionManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	public Map<String, String> getMapEnabledFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}

	public Map<String, String> getMapAppFunctionType() {
		return appDictTypeManager
				.getDictDataByTypeCd(DictContants.APP_FUNCTION_TYPE);
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isFunctionCdExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newFunctionCd = request.getParameter("functionCd").trim();
		String oldFunctionCd = request.getParameter("oldFunctionCd").trim();

		if (appFunctionManager.isPropertyUnique("functionCd", newFunctionCd,
				oldFunctionCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}
}
