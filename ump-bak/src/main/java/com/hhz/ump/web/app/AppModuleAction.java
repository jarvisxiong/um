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
import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JsonUtil2;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-module.action", type = "redirect") })
public class AppModuleAction extends CrudActionSupport<AppModule> {
	private static final long serialVersionUID = -3445152342227169047L;
	private Page<AppModule> page = new Page<AppModule>(20);// 每页10条记录

	@Autowired
	private AppModuleManager appModuleManager;
	@Autowired
	private AppPageManager appPageManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private AppModule entity;

	private List<AppModule> moduleList;

	private List<AppMenu> menuList;

	private String filter_LIKES_moduleName;

	private String filter_EQS_moduleCd;
	
	
	private String pageName;
	private String parentModuleName;

	//页面编码/名称映射
	private Map<String,String> mapPageCdName = new HashMap<String,String>();
	

	public AppModuleAction() {
	}

	public AppModule getModel() {
		return entity;
	}

	public String getFilter_LIKES_moduleName() {
		return filter_LIKES_moduleName;
	}

	public void setFilter_LIKES_moduleName(String filterLIKESModuleName) {
		filter_LIKES_moduleName = filterLIKESModuleName;
	}

	public String getFilter_EQS_moduleCd() {
		return filter_EQS_moduleCd;
	}

	public void setFilter_EQS_moduleCd(String filterEQSModuleCd) {
		filter_EQS_moduleCd = filterEQSModuleCd;
	}


	@Override
	public Page<AppModule> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		String appModuleId = getId();
		if (StringUtils.isNotBlank(appModuleId)) {
			entity = appModuleManager.getEntity(appModuleId);
		} else {
			entity = new AppModule();
		}
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public String list() throws Exception {
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("dispOrderNo");
			page.setOrder(Page.ASC);
		}

		// if (pageSize == null) {
		// page.setPageSize(Constants.PAGE_SIZE);
		// }
		page = appModuleManager.findPage(page, filters);
		
		mapPageCdName.clear();
		List<AppPage> pageList = appPageManager.getAll();
		AppPage tmp = null;
		for (int i = 0; i < pageList.size(); i++) {
			tmp = pageList.get(i);
			mapPageCdName.put(tmp.getPageCd(), tmp.getPageName());
		}
		JsonUtil2.renderJson(page,new String[]{"appModuleMenuRels"});
		return null;
	}

	@Override
	public String input() throws Exception {

		if(StringUtils.isNotBlank(entity.getPageCd())){
			AppPage tmpPage= appPageManager.getEntityByPageCd(entity.getPageCd());
			if( tmpPage != null){
				pageName = tmpPage.getPageName();
			}
		}
		if(StringUtils.isNotBlank(entity.getPageCd())){
			AppModule tmpModule= appModuleManager.getEntityByModuleCd(entity.getParentModuleCd());
			if( tmpModule != null){
				parentModuleName = tmpModule.getModuleName();
			}
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {

		String moduleCd = entity.getModuleCd();
		if (StringUtils.isBlank(moduleCd)) {
			moduleCd = appSeqManager.createNextValue(
					GlobalConstants.SEQ_MODULE_CD).toString();
			entity.setModuleCd(moduleCd);
		}

		appModuleManager.saveAppModule(entity);
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		appModuleManager.deleteAppModule(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			appModuleManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	public List<AppModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<AppModule> moduleList) {
		this.moduleList = moduleList;
	}

	public List<AppMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AppMenu> menuList) {
		this.menuList = menuList;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isModuleCdExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newModuleCd = request.getParameter("moduleCd").trim();
		String oldModuleCd = request.getParameter("oldModuleCd").trim();

		if (appModuleManager.isPropertyUnique("moduleCd", newModuleCd, oldModuleCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * 支持使用Jquery.validate Ajax
	 * 
	 * 检验 模块名称 不能重复
	 */
	public String isModuleNameExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newModuleName = request.getParameter("moduleName").trim();
		String oldModuleName = request.getParameter("oldModuleName").trim();

		if (appModuleManager.isPropertyUnique("moduleName", newModuleName,
				oldModuleName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}
	

	// 模糊搜索模块列表
	public String searchModuleList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String moduleName = request.getParameter("moduleName").trim();

		List<Object> rtn = new ArrayList<Object>();
		if (StringUtils.isNotBlank(moduleName)) {
			List<AppModule> list = appModuleManager.getModuleList(moduleName,30);
			for (int i = 0; i < list.size(); i++) {
				AppModule tmp = list.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("moduleCd", tmp.getModuleCd());
				map.put("moduleName", tmp.getModuleName());
				rtn.add(map);
			}
		}
		Struts2Utils.renderJson(rtn);
		
		return null;
	}

	public Map<String, String> getMapPageCdName() {
		return mapPageCdName;
	}

	public void setMapPageCdName(Map<String, String> mapPageCdName) {
		this.mapPageCdName = mapPageCdName;
	}
	
}
