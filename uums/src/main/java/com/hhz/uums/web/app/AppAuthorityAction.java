/**
 * 
 */
package com.hhz.uums.web.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.app.AppFunctionManager;
import com.hhz.uums.dao.app.AppMenuManager;
import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.dao.app.AppRoleFunctionRelManager;
import com.hhz.uums.dao.app.AppRoleMenuRelManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.entity.app.AppPage;
import com.hhz.uums.entity.app.AppRoleFunctionRel;
import com.hhz.uums.entity.app.AppRoleMenuRel;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.ConvertVoUtil;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.ws.WsPlasRole;


public class AppAuthorityAction extends CrudActionSupport<AppRoleMenuRel> {
	private static final long serialVersionUID = -3072902527095180648L;

	private static final String NODE_TYPE_FUNCTION = "function";

	private static final String NODE_TYPE_MENU = "menu";

	private static final String NODE_TYPE_MODULE = "module";
	


	@Autowired
	private PlasAppManager plasAppManager;
	@Autowired
	private PlasRoleGroupManager plasRoleGroupManager;
	@Autowired
	private PlasRoleManager plasRoleManager;

	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;

	@Autowired
	private AppRoleFunctionRelManager appRoleFunctionRelManager;

	@Autowired
	private AppModuleManager appModuleManager;

	@Autowired
	private AppMenuManager appMenuManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppFunctionManager appFunctionManager;

	private AppRoleMenuRel entity;

	private AppModule appModule;

	private AppMenu appMenu;
	
	private AppPage appPage;
	
	/**
	 * 所有模块及菜单
	 */
	private List<AppModule> appModules;

	/**
	 * 所有角色
	 */
	private List<WsPlasRole> uaapRoles;

	private List<AppRoleMenuRel> appRoleMenuRels;

	/**
	 * 当前角色拥有的功能权限
	 */
	private List<AppRoleFunctionRel> appRoleFunctionRels;

	private String nodeType;

	private String nodeId;

	/**
	 * 所有选中的Id
	 */
	private String chkItemIds;

	/**
	 * 所有选中的菜单Id
	 */
	private String chkRelIds;

	private String chkTypes;

	private String roleCd;
	
	private String menu;

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	private List<AppModule> appRoleModules = new ArrayList<AppModule>();

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		if (nodeType.equals(NODE_TYPE_MODULE)) {
			appModule = appModuleManager.getEntity(nodeId);
		} else if (nodeType.equals(NODE_TYPE_MENU)) {
			appMenu = appMenuManager.getEntity(nodeId);
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {

		return SUCCESS;
	}


	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appRoleMenuRelManager.getEntity(getId());
		}
	}


	public String saveRule() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		if (chkItemIds != null) {
			String[] itemIds = chkItemIds.split(",");
			String[] types = chkTypes.split(",");
			String[] relIds = chkRelIds.split(",");
			List<AppRoleMenuRel> lstAppRoleMenuRelAdd = new ArrayList<AppRoleMenuRel>();
			List<String> lstAppRoleMenuRelDel = new ArrayList<String>();
			List<AppRoleFunctionRel> lstAppRoleFunctionRelAdd = new ArrayList<AppRoleFunctionRel>();
			List<String> lstAppRoleFunctionRelDel = new ArrayList<String>();

			for (int i = 0; i < itemIds.length; i++) {
				String itemId = StringUtils.trimToNull(itemIds[i]);
				String type = StringUtils.trimToNull(types[i]);
				String relId = StringUtils.trimToNull(relIds[i]);
				if (type.equals(NODE_TYPE_MENU)) {
					AppRoleMenuRel appRoleMenuRel = new AppRoleMenuRel();
					AppMenu appMenu = appMenuManager.getEntity(itemId);
					appRoleMenuRel.setAppMenu(appMenu);
					appRoleMenuRel.setRoleCd(roleCd);
					if (StringUtils.isEmpty(relId)) {
						lstAppRoleMenuRelAdd.add(appRoleMenuRel);
					} else {
						lstAppRoleMenuRelDel.add(relId);
					}
				} else if (type.equals(NODE_TYPE_FUNCTION)) {
					AppRoleFunctionRel appRoleFunctionRel = new AppRoleFunctionRel();
					String functionId = itemId.substring(itemId.lastIndexOf("-") + 1);
					String menuCd = itemId.substring(0, itemId.lastIndexOf("-"));
					AppFunction appFunction = appFunctionManager.getEntity(functionId);
					appRoleFunctionRel.setAppFunction(appFunction);
					appRoleFunctionRel.setRoleCd(roleCd);
					appRoleFunctionRel.setMenuCd(menuCd);
					if (StringUtils.isEmpty(relId)) {
						lstAppRoleFunctionRelAdd.add(appRoleFunctionRel);
					} else {
						lstAppRoleFunctionRelDel.add(relId);
					}
				}
			}
			appRoleMenuRelManager.saveRoleRel(lstAppRoleMenuRelAdd, lstAppRoleMenuRelDel, lstAppRoleFunctionRelAdd, lstAppRoleFunctionRelDel);
			Struts2Utils.renderText("true");
		}
		return null;
	}

	public Map<String, String> getMapEnabledFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.ENABLED_FLG);
	}

	public AppRoleMenuRel getModel() {
		return entity;
	}

	public List<AppModule> getAppModules() {
		return appModules;
	}

	public List<WsPlasRole> getUaapRoles() {
		return uaapRoles;
	}

	public Map<String, String> getMapResourceType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APP_RESOURCE_TYPE);
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public void setChkItemIds(String chkMenuIds) {
		this.chkItemIds = chkMenuIds;
	}

	public List<AppRoleMenuRel> getAppRoleMenuRels() {
		return appRoleMenuRels;
	}

	public void setChkRelIds(String chkRelIds) {
		this.chkRelIds = chkRelIds;
	}

	public List<AppRoleFunctionRel> getAppRoleFunctionRels() {
		return appRoleFunctionRels;
	}

	public void setChkTypes(String chkTypes) {
		this.chkTypes = chkTypes;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public AppModule getAppModule() {
		return appModule;
	}

	public AppMenu getAppMenu() {
		return appMenu;
	}

	public List<AppModule> getAppRoleModules() {
		return appRoleModules;
	}
	
	//ajax 加载应用角色树
	public void loadRoleTree(){
		Struts2Utils.renderJson(TreePanelUtil.createAppRoleTree());
	}
	
	//ajax 加载角色对应的菜单树
	public void loadModuleMenuTree(){
		List<AppMenu> checklist = new ArrayList<AppMenu>();
		if(!StringUtils.isBlank(getRoleCd())) {
			checklist = appRoleMenuRelManager.searchMenu(getRoleCd());
		}
		Struts2Utils.renderJson(TreePanelUtil.createAppModuleMenuTree( checklist));
	}
	public void loadMenuPageFuncTree(){
		List<AppRoleFunctionRel> tmp = appRoleFunctionRelManager.findByRoleCd(getRoleCd());
		List<AppFunction> checklist = new ArrayList<AppFunction>();
		for(AppRoleFunctionRel roleF:tmp){
			checklist.add(roleF.getAppFunction());
		}
		appMenu = appMenuManager.getEntityByMenuCd(menu);
		if(appMenu != null) {
			appPage = appMenu.getAppPage();
		}
		Struts2Utils.renderJson(TreePanelUtil.creatPageFuncTree(appPage,
			appPage.getAppFunctions(), checklist));
	}
	
	/**
	 * 保存角色授权菜单
	 */
	public void saveRoleMenuBatch(){
		String addMenuIds = Struts2Utils.getParameter("addMenuIds");
		String delMenuIds = Struts2Utils.getParameter("delMenuIds");
		appRoleMenuRelManager.saveBatch(roleCd, addMenuIds, delMenuIds);
		Struts2Utils.renderText("success");
	}
	/**
	 * 批量保存授权角色页面功能关系
	 */
	public void saveRoleMenuFuncBatch(){
		String addFuncIds = Struts2Utils.getParameter("addFuncIds");
		String delFuncIds = Struts2Utils.getParameter("delFuncIds");
		appRoleFunctionRelManager.saveRoleRel(getRoleCd(), getMenu(), addFuncIds,delFuncIds);
		Struts2Utils.renderText("success");
	}
	
	
	/**
	 * 加载右边角色树
	 * @param menuId
	 */
	public void loadRightRoleTree(){
		String tMenuId = Struts2Utils.getParameter("menuId");
		//查询菜单被那些角色使用
		List<String> tmpRoleIdList = appRoleMenuRelManager.getRoleList(tMenuId);
		TreePanelNode node = TreePanelUtil2.buildAppRoleTree(
				ConvertVoUtil.getVoAppList(plasAppManager.getAllOrderedApps()), 
				ConvertVoUtil.getVoGroupList(plasRoleGroupManager.getAllOrderedGroups()), 
				ConvertVoUtil.getVoRoleList(plasRoleManager.getAllOrderedRoles()), 
				tmpRoleIdList);
		Struts2Utils.renderJson(node);
	}
	
	
	/**
	 * 批量保存: 授权菜单-角色功能关系
	 */
	public void saveMenuRoleBatch(){
		String tmpMenuId = Struts2Utils.getParameter("menuId");
		String tmpAddRoleIds = Struts2Utils.getParameter("addRoleIds");
		String tmpDelRoleIds = Struts2Utils.getParameter("delRoleIds");
		appRoleMenuRelManager.saveBatchRole(tmpMenuId, tmpAddRoleIds, tmpDelRoleIds);
		Struts2Utils.renderText("success");
	}
	
}
