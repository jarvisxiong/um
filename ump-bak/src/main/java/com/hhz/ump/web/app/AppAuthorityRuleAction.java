/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppRoleRuleRelManager;
import com.hhz.ump.dao.app.AppRuleManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppRoleRuleRel;
import com.hhz.ump.entity.app.AppRule;

/**
 * @author huangj 2010-1-12
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-authority-rule.action", type = "redirect", params = { "appFunctionId",
		"${appFunctionId}", "roleCd", "${roleCd}" }) })
public class AppAuthorityRuleAction extends CrudActionSupport<AppRule> {

	private static final long serialVersionUID = 2422625655266394522L;

	private AppRule entity;

	@Autowired
	private AppRuleManager appRuleManager;

	@Autowired
	private AppFunctionManager appFunctionManager;

	@Autowired
	private AppRoleRuleRelManager appRoleRuleRelManager;

	private List result;

	private String roleCd;

	private String appFunctionId;

	@Override
	public String delete() throws Exception {
		try {
			appRuleManager.deleteAppRule(getId());
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("true");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("false");
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		// List<PropertyFilter> filters =
		// HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("ruleCd");
			page.setOrder(Page.ASC);
		}
		// if (appFunctionId != null) {
		// filters.add(new PropertyFilter("EQS_appFunction.appFunctionId",
		// appFunctionId));
		// }
		StringBuffer sHql = new StringBuffer(
				"select ar,( select count(arr.roleCd) from AppRoleRuleRel arr where arr.appRule.appRuleId=ar.appRuleId and arr.roleCd=:p_roleCd) as roleCd from AppRule ar where 1=1 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_roleCd", roleCd);
		if (appFunctionId != null) {
			sHql.append(" and ar.appFunction.appFunctionId=:p_appFunctionId");
			map.put("p_appFunctionId", appFunctionId);
		}
		result = appRuleManager.find(sHql.toString(), map);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appRuleManager.getEntity(getId());
		} else {
			entity = new AppRule();
		}

	}

	@Override
	public String save() throws Exception {
		AppFunction appFunction = appFunctionManager.getEntity(appFunctionId);
		entity.setAppFunction(appFunction);
		appRuleManager.saveAppRule(entity);
		setId(entity.getAppRuleId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	public String add2Role() throws Exception {
		if (StringUtils.isNotEmpty(roleCd) && getId() != null) {
			Criterion criterion1 = Restrictions.eq("roleCd", roleCd);
			Criterion criterion2 = Restrictions.eq("appRule.appRuleId", getId());
			List<AppRoleRuleRel> lstRel = appRoleRuleRelManager.findBy(criterion1, criterion2);
			if (lstRel.size() > 0) {
				appRoleRuleRelManager.delete(lstRel);
			} else {
				AppRoleRuleRel appRoleRuleRel = new AppRoleRuleRel();
				appRoleRuleRel.setAppRule(entity);
				appRoleRuleRel.setRoleCd(roleCd);
				appRoleRuleRelManager.saveAppRoleRuleRel(appRoleRuleRel);
			}
			setId(entity.getAppRuleId());
			addActionMessage(getText("common.success"));
		}
		return RELOAD;
	}

	public void prepareAdd2Role() throws Exception {
		prepareModel();
	}

	public AppRule getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getAppFunctionId() {
		return appFunctionId;
	}

	public void setAppFunctionId(String appFunctionId) {
		this.appFunctionId = appFunctionId;
	}

	public List getResult() {
		return result;
	}

}
