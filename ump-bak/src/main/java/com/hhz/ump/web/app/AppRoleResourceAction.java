/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.List;

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
import com.hhz.ump.dao.app.AppRoelResourceRelManager;
import com.hhz.ump.entity.app.AppRoelResourceRel;

/**
 * 角色资源关系
 * 
 * @author huangj 2010-1-11
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-role-resource.action", type = "redirect") })
public class AppRoleResourceAction extends CrudActionSupport<AppRoelResourceRel> {
	private static final long serialVersionUID = 6972391183013383054L;

	private AppRoelResourceRel entity;

	@Autowired
	private AppRoelResourceRelManager appRoelResourceRelManager;
	@Override
	public String delete() throws Exception {
		try {
			appRoelResourceRelManager.deleteAppRoelResourceRel(getId());
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
			appRoelResourceRelManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("roleCd");
			page.setOrder(Page.ASC);
		}
		page = appRoelResourceRelManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId()!=null){
			entity = appRoelResourceRelManager.getEntity(getId());
		}else{
			entity = new AppRoelResourceRel();
		}

	}

	@Override
	public String save() throws Exception {
		appRoelResourceRelManager.saveAppRoelResourceRel(entity);
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	public AppRoelResourceRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

}
