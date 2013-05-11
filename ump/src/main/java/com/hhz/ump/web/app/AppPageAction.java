/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hhz.ump.dao.app.AppMenuManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.EasyTreeUtil;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JsonUtil2;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-page.action", type = "redirect") })
public class AppPageAction extends CrudActionSupport<AppPage> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppPage> page = new Page<AppPage>(40);// 每页15条记录

	@Autowired
	private AppPageManager appPageManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppSeqManager appSeqManager;
	@Autowired
	private AppMenuManager appMenuManager;
	private AppPage entity;

	private String filter_LIKES_pageName;
	private String filter_LIKES_pagePath;
	private String filter_EQS_pageCd;
	private String filter_EQS_pageStatusCd;
	private String menuId;
	private String menuName;

	public AppPageAction() {
	}

	@Override
	public AppPage getModel() {
		return entity;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getFilter_LIKES_pageName() {
		return filter_LIKES_pageName;
	}

	public void setFilter_LIKES_pageName(String filterLIKESPageName) {
		filter_LIKES_pageName = filterLIKESPageName;
	}

	public String getFilter_EQS_pageCd() {
		return filter_EQS_pageCd;
	}

	public void setFilter_EQS_pageCd(String filterEQSPageCd) {
		filter_EQS_pageCd = filterEQSPageCd;
	}

	@Override
	public Page<AppPage> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = appPageManager.getEntity(getId());
		} else {
			entity = new AppPage();
		}
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrder(Page.ASC);
		}
		page.setOrderBy("pagePath");
		page.setOrder(Page.ASC);

		page = appPageManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}
	@Override
	public void prepareInput()throws Exception {
		prepareModel();
 	}
	@Override
	public String save() throws Exception {
		if (StringUtils.isBlank(entity.getPageCd())) {
			entity.setPageCd(appSeqManager.createNextValue(GlobalConstants.SEQ_PAGE_CD).toString());
		}

		appPageManager.saveAppPage(entity);
		if(StringUtils.isNotBlank(menuId)&&!"0".equals(menuId)){
			AppMenu menu = appMenuManager.getEntity(menuId);
			menu.setAppPage(entity);
			appMenuManager.saveAppMenu(menu);
		}
		 
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		try {
			appPageManager.deleteAppPage(getId());
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("success");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("failure");
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			appPageManager.deleteBatch(getIds());
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

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isPageCdExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newPageCd = request.getParameter("pageCd").trim();
		String oldPageCd = request.getParameter("oldPageCd").trim();

		if (appPageManager.isPropertyUnique("pageCd", newPageCd, oldPageCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}


	// 模糊搜索页面列表
	public String searchPageList() {

		String pageName = Struts2Utils.getParameter("value").trim();
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();
		// 增加根节点
		Map<String, String> map = new HashMap<String, String>(); 
		if (StringUtils.isNotBlank(pageName)) {
			List<AppPage> list = appPageManager.getPageList(pageName,30);
			for (int i = 0; i < list.size(); i++) {
				AppPage tmp = list.get(i);
				map = new HashMap<String, String>();
				map.put("pageCd", tmp.getPageCd());
				map.put("pageName", tmp.getPageName());
				tmpList.add(map);
			}
		}
		Struts2Utils.renderJson(tmpList);
		return null;
	}

	public String getFilter_LIKES_pagePath() {
		return filter_LIKES_pagePath;
	}

	public void setFilter_LIKES_pagePath(String filter_LIKES_pagePath) {
		this.filter_LIKES_pagePath = filter_LIKES_pagePath;
	}

	public String getFilter_EQS_pageStatusCd() {
		return filter_EQS_pageStatusCd;
	}

	public void setFilter_EQS_pageStatusCd(String filter_EQS_pageStatusCd) {
		this.filter_EQS_pageStatusCd = filter_EQS_pageStatusCd;
	}
	
	public void loadModuleMenuPageTree(){
		JsonUtil2.renderTreeJson(EasyTreeUtil.createModuleMenuPageTree());
	}
}
