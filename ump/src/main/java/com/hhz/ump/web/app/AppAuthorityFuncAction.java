/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppRoleFunctionRelManager;
import com.hhz.ump.dao.app.AppRoleMenuRelManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppRoleFunctionRel;
import com.hhz.ump.entity.app.AppRoleMenuRel;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author huangj 2010-1-12
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-authority-func.action", type = "redirect"),
		@Result(name = "roleFunc", location = "/tags/MenuFuncTreeData.ftl", type = "freemarker") })
public class AppAuthorityFuncAction extends CrudActionSupport<AppRoleFunctionRel> {

	private static final long serialVersionUID = 2422625655266394522L;

	private AppRoleFunctionRel entity;

	@Autowired
	private AppRoleFunctionRelManager appRoleFunctionRelManager;

	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;

	@Autowired
	private AppFunctionManager appFunctionManager;

	/**
	 * 所有角色
	 */
	private List<WsPlasRole> uaapRoles;

	/**
	 * 当前角色拥有的功能权限
	 */
	private List<AppRoleFunctionRel> appRoleFunctionRels;

	/**
	 * 当前角色拥有的所有菜单权限
	 */
	private List<AppRoleMenuRel> appRoleMenuRels;

	/**
	 * 所有选中的菜单Id
	 */
	private String chkFuncIds;

	/**
	 * 所有选中的菜单Id
	 */
	private String chkRelIds;
	private String roleCd;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
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
		loadRole();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			appRoleFunctionRelManager.getEntity(getId());
		}

	}

	/**
	 * 所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	private void loadRole() throws Exception {
		uaapRoles = Util.getPlasService().getRoleList();
	}

	private void loadRoleMenu() {
		if (roleCd != null) {
			Criterion criterion = Restrictions.eq("roleCd", roleCd);
			appRoleMenuRels = appRoleMenuRelManager.findBy(criterion);
		}
	}

	public String loadFunc() throws Exception {
		loadRoleMenu();
		if (roleCd != null) {
			Criterion criterion = Restrictions.eq("roleCd", roleCd);
			appRoleFunctionRels = appRoleFunctionRelManager.findBy(criterion);
		}
		return "roleFunc";
	}

	@Override
	public String save() throws Exception {
		if (chkFuncIds != null) {
			String[] funcIds = chkFuncIds.split(",");
			String[] relIds = chkRelIds.split(",");
			List<AppRoleFunctionRel> lstAppRoleFunctionRelAdd = new ArrayList<AppRoleFunctionRel>();
			List<String> lstAppRoleFunctionRelDel = new ArrayList<String>();

			for (int i = 0; i < funcIds.length; i++) {
				String funcId = StringUtils.trimToNull(funcIds[i]);
				String relId = StringUtils.trimToNull(relIds[i]);
				AppRoleFunctionRel appRoleFunctionRel = new AppRoleFunctionRel();
				AppFunction appFunction = appFunctionManager.getEntity(funcId);
				appRoleFunctionRel.setAppFunction(appFunction);
				appRoleFunctionRel.setRoleCd(roleCd);
				if (StringUtils.isEmpty(relId)) {
					lstAppRoleFunctionRelAdd.add(appRoleFunctionRel);
				} else {
					lstAppRoleFunctionRelDel.add(relId);
				}
			}
			appRoleFunctionRelManager.saveRoleRel(lstAppRoleFunctionRelAdd, lstAppRoleFunctionRelDel);
			Struts2Utils.renderText("true");
		}
		return null;
	}

	public AppRoleFunctionRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public List<AppRoleFunctionRel> getAppRoleFunctionRels() {
		return appRoleFunctionRels;
	}

	public List<AppRoleMenuRel> getAppRoleMenuRels() {
		return appRoleMenuRels;
	}

	public void setChkFuncIds(String chkFuncIds) {
		this.chkFuncIds = chkFuncIds;
	}

	public void setChkRelIds(String chkRelIds) {
		this.chkRelIds = chkRelIds;
	}

	public List<WsPlasRole> getUaapRoles() {
		return uaapRoles;
	}

}
