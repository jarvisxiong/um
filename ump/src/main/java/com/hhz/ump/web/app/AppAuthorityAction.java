/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppMenuManager;
import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppRoleFunctionRelManager;
import com.hhz.ump.dao.app.AppRoleMenuRelManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.entity.app.AppRoleFunctionRel;
import com.hhz.ump.entity.app.AppRoleMenuRel;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author huangj 2010-1-9
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-authority.action", type = "redirect"),
		@Result(name = "menu", location = "/tags/ModuleMenuTreeData.ftl", type = "freemarker") })
public class AppAuthorityAction extends CrudActionSupport<AppRoleMenuRel> {
	private static final long serialVersionUID = -3072902527095180648L;

	private static final String NODE_TYPE_FUNCTION = "function";

	private static final String NODE_TYPE_MENU = "menu";

	private static final String NODE_TYPE_MODULE = "module";

	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;

	@Autowired
	private AppRoleFunctionRelManager appRoleFunctionRelManager;
	@Autowired
	private AppModuleManager appModuleManager;

	@Autowired
	private AppMenuManager appMenuManager;
	@Autowired
	private AppRoleFunctionRelManager roleFuncRelManager;
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
		// TODO Auto-generated method stub
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
		loadRole();
		return SUCCESS;
	}

	private void loadRoleMenu() throws Exception {
		if (getRoleCd() != null) {
//			System.out.println("getRoleCd()=" + getRoleCd());
			appRoleMenuRels = appRoleMenuRelManager.findByRoleCd(getRoleCd());
			appRoleFunctionRels = appRoleFunctionRelManager.findByRoleCd(getRoleCd());
		}
	}

	public String loadMenuByRoleCd() throws Exception {
		loadRoleMenu();
//		Set<AppRoleMenuRel> roleMenuRelsSet=new HashSet<AppRoleMenuRel>();
//		for (AppRoleMenuRel appRoleMenuRel : appRoleMenuRels) {
//			roleMenuRelsSet.add(appRoleMenuRel);
//		}
		List<AppModuleMenuRel> menuRels = appRoleMenuRelManager.searchMenu(getRoleCd());
		for (AppModuleMenuRel menuRel : menuRels) {
			AppModule appModuleTmp = menuRel.getAppModule();
			if (!appRoleModules.contains(appModuleTmp)) {
				//System.out.println("1="+appModuleTmp.getModuleName());
				appRoleModules.add(appModuleTmp);
				appModuleTmp.getAppModuleMenuRels().clear();
			}
			if(!appModuleTmp.getAppModuleMenuRels().contains(menuRel)){
				if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())
						&&"计划管理".equalsIgnoreCase(appModuleTmp.getModuleName())){
					//如果是施小姐并且在计划管理中，删除不必要的项目
					if(!("项目执行计划(旧)".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"计划管理日志".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"中心执行计划".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"总经理任务".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"成本半年计划".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"成本节点模板".equalsIgnoreCase(menuRel.getAppMenu().getMenuName()))){
						appModuleTmp.getAppModuleMenuRels().add(menuRel);
					}
				}else{
					appModuleTmp.getAppModuleMenuRels().add(menuRel);
				}
			}
		}
		
		CollectionHelper.sortList(appRoleModules, "dispOrderNo");
		return "roleMenu";
	}
	
	public String loadNewMenuByRoleCd() throws Exception {
		loadRoleMenu();
		List<AppModuleMenuRel> menuRels = appRoleMenuRelManager.searchMenu(getRoleCd());
		for (AppModuleMenuRel menuRel : menuRels) {
			AppModule appModuleTmp = menuRel.getAppModule();
			if (!appRoleModules.contains(appModuleTmp)) {
				//System.out.println("1="+appModuleTmp.getModuleName());
				appRoleModules.add(appModuleTmp);
				appModuleTmp.getAppModuleMenuRels().clear();
			}
			if(!appModuleTmp.getAppModuleMenuRels().contains(menuRel)){
				if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())
						&&"计划管理".equalsIgnoreCase(appModuleTmp.getModuleName())){
					//如果是施小姐并且在计划管理中，删除不必要的项目
					if(!("项目执行计划(旧)".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"计划管理日志".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"中心执行计划".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"总经理任务".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"成本半年计划".equalsIgnoreCase(menuRel.getAppMenu().getMenuName())
							||"成本节点模板".equalsIgnoreCase(menuRel.getAppMenu().getMenuName()))){
						appModuleTmp.getAppModuleMenuRels().add(menuRel);
					}
				}else{
					appModuleTmp.getAppModuleMenuRels().add(menuRel);
				}
			}
		}
		
		CollectionHelper.sortList(appRoleModules, "dispOrderNo");
		return "roleNewMenu";
	}
	public String loadMenuByModule() throws Exception {
		
		String moduleCd = Struts2Utils.getParameter("moduleCd");
		
		setRoleCd(PowerUtils.array2String(SpringSecurityUtils.getCurrentRoleCds()));
		
		List<AppModuleMenuRel> menuRels = appRoleMenuRelManager.searchMenu(getRoleCd());
		List<AppModuleMenuRel> appModuleMenuRels = new ArrayList<AppModuleMenuRel>();
		for (AppModuleMenuRel menuRel : menuRels) {
			AppMenu appMenuTmp = menuRel.getAppMenu();
			AppModule appModuleTmp = menuRel.getAppModule();
			if(moduleCd.equals(appModuleTmp.getModuleCd())){
				boolean if_has = false;	//去掉重复的记录
				for(int i=0;i<appModuleMenuRels.size();i++){
					AppModuleMenuRel appModuleMenuRelTmp2 = appModuleMenuRels.get(i);
					AppMenu appMenuTmp2 = appModuleMenuRelTmp2.getAppMenu();
					if(appMenuTmp.getMenuCd().equalsIgnoreCase(appMenuTmp2.getMenuCd())){
						if_has = true;
						break;
					}
				}
				if(!if_has){
					appModuleMenuRels.add(menuRel);
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("appModuleMenuRels", appModuleMenuRels);
		return "moduleMenu";
	}
	
public String loadMenuByNewModule() throws Exception {
		
		String moduleCd = Struts2Utils.getParameter("moduleCd");
		
		setRoleCd(PowerUtils.array2String(SpringSecurityUtils.getCurrentRoleCds()));
		
		List<AppModuleMenuRel> menuRels = appRoleMenuRelManager.searchMenu(getRoleCd());
		List<AppModuleMenuRel> appModuleMenuRels = new ArrayList<AppModuleMenuRel>();
		for (AppModuleMenuRel menuRel : menuRels) {
			AppMenu appMenuTmp = menuRel.getAppMenu();
			AppModule appModuleTmp = menuRel.getAppModule();
			if(moduleCd.equals(appModuleTmp.getModuleCd())){
				boolean if_has = false;	//去掉重复的记录
				for(int i=0;i<appModuleMenuRels.size();i++){
					AppModuleMenuRel appModuleMenuRelTmp2 = appModuleMenuRels.get(i);
					AppMenu appMenuTmp2 = appModuleMenuRelTmp2.getAppMenu();
					if(appMenuTmp.getMenuCd().equalsIgnoreCase(appMenuTmp2.getMenuCd())){
						if_has = true;
						break;
					}
				}
				if(!if_has){
					appModuleMenuRels.add(menuRel);
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("appModuleMenuRels", appModuleMenuRels);
		return "moduleNewMenu";
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appRoleMenuRelManager.getEntity(getId());
		}
	}

	/**
	 * 所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	private void loadRole() {
		uaapRoles = Util.getPlasService().getRoleList();
	}

	/**
	 * 所有菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadMenu() throws Exception {
		appModules = appModuleManager.getAll();

		loadRoleMenu();

		return "menu";
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
		// TODO Auto-generated method stub
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
	public void loadRoleTree(){
		List<WsPlasRole> list = Util.getPlasService().getRoleList();
		Struts2Utils.renderJson(TreePanelUtil.buildRoleTree(list));
	}
	public void loadModuleMenuTree(){
		List<AppMenu> checklist = new ArrayList<AppMenu>();
		if(StringUtils.isNotBlank(getRoleCd())){
			List<AppModuleMenuRel> tmpList = appRoleMenuRelManager.searchMenu(getRoleCd());
			for(AppModuleMenuRel obj : tmpList){
				checklist.add(obj.getAppMenu());
			}
		}
		Struts2Utils.renderJson(TreePanelUtil.createAppModuleMenuTree( checklist));
	}
	
	
	//供首页显示菜单判断
	public void loadAllRoleModuleMenu(){
		StringBuffer t = new StringBuffer();
		List<Map<String, String>> checklist = new ArrayList<Map<String, String>>();
		if(StringUtils.isNotBlank(getRoleCd())){
			List<AppModuleMenuRel> tmpList = appRoleMenuRelManager.searchMenu(getRoleCd());
			//加载二级菜单
			for(AppModuleMenuRel obj : tmpList){
				AppModule appModuleTmp = obj.getAppModule();
				if (!appRoleModules.contains(appModuleTmp)) {
					//System.out.println("1="+appModuleTmp.getModuleName());
					appRoleModules.add(appModuleTmp);
					appModuleTmp.getAppModuleMenuRels().clear();
				}
				if(!appModuleTmp.getAppModuleMenuRels().contains(obj)){
					if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())
							&&"计划管理".equalsIgnoreCase(appModuleTmp.getModuleName())){
						//如果是施小姐并且在计划管理中，删除不必要的项目
						if(!("项目执行计划(旧)".equalsIgnoreCase(obj.getAppMenu().getMenuName())
								||"计划管理日志".equalsIgnoreCase(obj.getAppMenu().getMenuName())
								||"中心执行计划".equalsIgnoreCase(obj.getAppMenu().getMenuName())
								||"总经理任务".equalsIgnoreCase(obj.getAppMenu().getMenuName())
								||"成本半年计划".equalsIgnoreCase(obj.getAppMenu().getMenuName())
								||"成本节点模板".equalsIgnoreCase(obj.getAppMenu().getMenuName()))){
							appModuleTmp.getAppModuleMenuRels().add(obj);
						}
					}else{
						appModuleTmp.getAppModuleMenuRels().add(obj);
					}
				}
				
				if(t.length()>0){
					t.append(",")
					.append("\"")
					//.append(obj.getAppModule().getModuleName()+"||"+obj.getAppMenu().getMenuName())
					.append(obj.getAppModule().getModuleCd()+"||"+obj.getAppMenu().getMenuCd())
					.append("\"")
					.append(":")
					.append("\"")
					.append(obj.getAppMenu().getMenuCd()+"||"
							+obj.getAppMenu().getAppPage().getPagePath()
							);
					/*卢俊云修改：为何要取菜单的说明？2012-05-30
					if(StringUtils.isNotBlank(obj.getAppMenu().getMenuTip())){
						t.append("||"+obj.getAppMenu().getMenuTip());
					}else{
						t.append("||"+obj.getAppMenu().getMenuName());
					}
					*/
					t.append("||"+obj.getAppMenu().getMenuName());
					
					t.append("\"");
				}else{
					t.append("{")
					.append("\"")
					//.append(obj.getAppModule().getModuleName()+"||"+obj.getAppMenu().getMenuName())
					.append(obj.getAppModule().getModuleCd()+"||"+obj.getAppMenu().getMenuCd())
					.append("\"")
					.append(":")
					.append("\"")
					.append(obj.getAppMenu().getMenuCd()+"||"
							+obj.getAppMenu().getAppPage().getPagePath()
							);
					/*卢俊云修改：为何要取菜单的说明？2012-05-30
					if(StringUtils.isNotBlank(obj.getAppMenu().getMenuTip())){
						t.append("||"+obj.getAppMenu().getMenuTip());
					}else{
						t.append("||"+obj.getAppMenu().getMenuName());
					}
					*/
					t.append("||"+obj.getAppMenu().getMenuName());
					t.append("\"");
				}
				
			}
			//加载一级菜单
			for(AppModule appModule  : appRoleModules){
				if(t.length()>0){
					t.append(",")
					.append("\"")
					//.append(appModule.getModuleName()+"||")
					.append(appModule.getModuleCd()+"||")
					.append("\"")
					.append(":")
					.append("\"")
					.append("")
					.append("\"");
				}else{
					t.append("{")
					.append("\"")
					//.append(appModule.getModuleName()+"||")
					.append(appModule.getModuleCd()+"||")
					.append("\"")
					.append(":")
					.append("\"")
					.append("")
					.append("\"");
				}
			}
			t.append("}");
		}
		Struts2Utils.renderJson(t.toString());
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
		try{
		appRoleMenuRelManager.saveBatch(roleCd, addMenuIds, delMenuIds);
		Struts2Utils.renderText("success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 批量保存授权角色页面功能关系
	 */
	public void saveRoleMenuFuncBatch(){
		String addFuncIds = Struts2Utils.getParameter("addFuncIds");
		String delFuncIds = Struts2Utils.getParameter("delFuncIds");
		roleFuncRelManager.saveRoleRel(getRoleCd(), getMenu(), addFuncIds,delFuncIds);
		Struts2Utils.renderText("success");
	}
	

	/**
	 * 加载右边角色树
	 * @param menuId
	 */
	public void loadRightRoleTree(){
		String tMenuId = Struts2Utils.getParameter("menuId");
		
		//菜单 持有角色
		List<String> tmpRoleIdList = appRoleMenuRelManager.getRoleList(tMenuId);

		//所有角色
		List<WsPlasRole> roleList = Util.getPlasService().getRoleList();
		TreePanelNode node = TreePanelUtil.buildRoleTree(roleList, tmpRoleIdList);
		
		
//		TreePanelUtil.getModuleNode(moduleNode, module)
//		//搜索菜单被那些角色使用
//		TreePanelNode node = TreePanelUtil.buildAppRoleTree(
//				ConvertVoUtil.getVoAppList(plasAppManager.getAllOrderedApps()), 
//				ConvertVoUtil.getVoGroupList(plasRoleGroupManager.getAllOrderedGroups()), 
//				ConvertVoUtil.getVoRoleList(plasRoleManager.getAllOrderedRoles()), 
//				tmpRoleIdList);
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
