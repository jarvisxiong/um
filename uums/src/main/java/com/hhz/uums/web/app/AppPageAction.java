package com.hhz.uums.web.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppFunctionManager;
import com.hhz.uums.dao.app.AppPageManager;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppPage;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;

public class AppPageAction extends CrudActionSupport<AppPage> {
	
	private static final long serialVersionUID = 4992584095489244626L;
	
	@Autowired
	private AppPageManager appPageManager;

	@Autowired
	private AppFunctionManager appFunctionManager;
	@Autowired
	private AppSeqManager appSeqManager;
	
	private AppPage entity;
	
	private String appFunctionId;
	
	@Override
	public AppPage getModel() {
		return entity;
	}

	/**
	 * 删除单条记录
	 */
	@Override
	public String delete() throws Exception {
		appPageManager.deleteAppPage(getId());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	/**
	 * 新增/编辑页面
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 查询列表
	 */
	@Override
	public String list() throws Exception {
		
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField);
			page.setOrder(order);
		}
		page = appPageManager.findPage(page, filters);
		
		JsonUtil.renderJson(page,new String[]{"appMenus","appFunctions"});
		return null;
	}
	
	public String listFunc() throws Exception{
		entity = appPageManager.getEntity(getId());
		JsonUtil.renderListJson(entity.getAppFunctions(),new String[]{"appRoleFunctionRels","appRules","appPage"});
		return null;
	}

	/**
	 * 保存记录
	 */
	@Override
	public String save() throws Exception {
		List<AppFunction> datasInsert = (List<AppFunction>) JsonUtil.getInsertRecords(AppFunction.class);
		List<AppFunction> datasUpdated = (List<AppFunction>) JsonUtil.getUpdatedRecords(AppFunction.class);


		//注意:页面编号自动生成 
		// add by huangbijin 2011-04-23
		String pageCd = null;
		pageCd = entity.getPageCd();
		if (StringUtils.isBlank(pageCd)) {
			pageCd = appSeqManager.createNextValue(GlobalConstants.SEQ_PAGE_CD).toString();
			entity.setPageCd(pageCd);
		}
		
		
		appPageManager.saveAppPage(entity,datasInsert,datasUpdated);
		Struts2Utils.renderHtml("success");
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appPageManager.getEntity(getId());
		} else {
			entity = new AppPage();
		}
	}
	
	/**
	 * 删除页面功能
	 */
	public void deleteFunc(){
		try {
			appFunctionManager.deleteAppFunction(getAppFunctionId());
			JsonUtil.renderSuccess("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JsonUtil.renderFailure("删除失败");
		}
	}

	public String getAppFunctionId() {
		return appFunctionId;
	}

	public void setAppFunctionId(String appFunctionId) {
		this.appFunctionId = appFunctionId;
	}


}
