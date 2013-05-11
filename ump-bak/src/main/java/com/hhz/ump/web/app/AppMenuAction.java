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
import com.hhz.ump.dao.app.AppMenuManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.web.vo.VoModuleMenuPage;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-menu.action", type = "redirect") })
public class AppMenuAction extends CrudActionSupport<AppMenu> {

	
	private Page<AppMenu> page = new Page<AppMenu>(20);// 每页10条记录

	@Autowired
	private AppMenuManager appMenuManager;
	@Autowired
	private AppModuleMenuRelManager appModuleMenuRelManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private AppMenu entity;

	private List<AppMenu> menuList;

	public AppMenuAction() {
	}

	public AppMenu getModel() {
		return entity;
	}

	@Override
	public Page<AppMenu> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		String appMenuId = getId();
		if (StringUtils.isNotBlank(appMenuId)) {
			entity = appMenuManager.getEntity(appMenuId);
		} else {
			entity = new AppMenu();
		}
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
		page = appMenuManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		String menuCd = entity.getMenuCd();
		if (StringUtils.isBlank(menuCd)) {
			menuCd = appSeqManager.createNextValue(
					GlobalConstants.SEQ_MENU_CD).toString();
			entity.setMenuCd(menuCd);
		}

		appMenuManager.saveAppMenu(entity);
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		appMenuManager.deleteAppMenu(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			appMenuManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	public List<AppMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AppMenu> menuList) {
		this.menuList = menuList;
	}

	// 模糊搜索菜单列表
	public String searchMenuList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String menuName = request.getParameter("menuName").trim();

		List<Object> rtn = new ArrayList<Object>();
		if (StringUtils.isNotBlank(menuName)) {
			List<AppMenu> list = appMenuManager.getModuleList(menuName,30);
			for (int i = 0; i < list.size(); i++) {
				AppMenu tmp = list.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("menuCd", tmp.getMenuCd());
				map.put("menuName", tmp.getMenuName());
				rtn.add(map);
			}
		}
		Struts2Utils.renderJson(rtn);
		
		return null;
	}
	public void searchMenu(){
		String tmpName = Struts2Utils.getParameter("value").trim();
		List<VoModuleMenuPage> result= null;

		result = appModuleMenuRelManager.getVoModuleMenuList(tmpName, 10);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		//增加根节点
		Map<String, String> map=new HashMap<String, String>();
		map.put("menuId", "0");
		map.put("menuName", "根节点");
		map.put("moduleName", "");
		list.add(map);
		
		for(VoModuleMenuPage tmp : result){
			
			map = new HashMap<String, String>();
			map.put("menuId", tmp.getMenuId());
			map.put("menuName", tmp.getMenuName());
			map.put("moduleName", tmp.getModuleName());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
}
