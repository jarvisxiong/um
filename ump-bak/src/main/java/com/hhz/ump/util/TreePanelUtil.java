package com.hhz.ump.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppMenuManager;
import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.mate.MateOwnerType;
import com.hhz.uums.entity.ws.WsAppDictType;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasRole;
import com.hhz.uums.entity.ws.WsPlasRoleGroup;
import com.hhz.uums.entity.ws.WsPlasUser;


public class TreePanelUtil {

	// 节点状态 : 9-根节点 1-机构 0-用户
	public static String NODE_TYPE_ROOT = "9";

	public static String NODE_TYPE_ORG = "1";

	public static String NODE_TYPE_USER = "0";

	public static String NODE_TYPE_APP = "1";

	public static String NODE_TYPE_ROLE = "0";
	
	public static String NODE_TYPE_ROLE_GROUP = "2";
	
	public static String NODE_TYPE_ROLE_AREA = "area";//区域
	public static String NODE_TYPE_ROLE_PROJECT = "project";//项目

	// 选择状态 : 0-不选中 1-选中 2-半选
	public static String NODE_CHECKED_UNDEFINED = "undefined";

	public static String NODE_CHECKED_NONE = "0";

	public static String NODE_CHECKED_CHECKED = "1";

	public static String NODE_CHECKED_HALF = "2";

	// 逻辑物理视图  1-物理 2-逻辑
	public static String TREE_TYPE_LOGICAL = "2";

	public static String TREE_TYPE_PHYSICAL = "1";

	// 功能/页面/菜单/模块
	public static String NODE_TYPE_FUNCTION = "function";

	public static String NODE_TYPE_PAGE = "page";

	public static String NODE_TYPE_MENU = "menu";

	public static String NODE_TYPE_MODULE = "module";

	public static String NODE_USER_PRE_ID = "usertreenode_";

	//常量
	public static String DEFAULT_ROOT_ORG_ID = "0"; 
	public static String DEFAULT_ROOT_ORG_CD = "0"; 
	public static String DEFAULT_ROOT_ORG_BIZ_CD = "0"; 
	public static String DEFAULT_ROOT_ORG_NAME = "宝龙集团"; 

	public static String DEFAULT_ROOT_APP_ID = "0"; 
	public static String DEFAULT_ROOT_APP_CD = "0"; 
	public static String DEFAULT_ROOT_APP_NAME = "应用与角色关系树"; 

	public static String DEFAULT_ROOT_MODULE_ID = "0"; 
	public static String DEFAULT_ROOT_MODULE_CD = "0"; 
	public static String DEFAULT_ROOT_MODULE_NAME = "模块与菜单关系树"; 

	public static String DEFAULT_ROOT_PAGE_ID = "0"; 
	public static String DEFAULT_ROOT_PAGE_CD = "0"; 
	public static String DEFAULT_ROOT_PAGE_NAME = "页面与功能关系树"; 
	
	/**
	 * 逻辑机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode, List<WsPlasOrg> orgList, boolean isOneLevel) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, new ArrayList<WsPlasUser>(), NODE_CHECKED_UNDEFINED, isOneLevel);
	}

	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode, List<WsPlasOrg> orgList) {
		return buildLogicalOrgTree(rootNode, orgList, false);
	}

	public static TreePanelNode buildLogicalOrgTreeNoChecked(TreePanelNode rootNode, List<WsPlasOrg> orgList, boolean isOneLevel) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, new ArrayList<WsPlasUser>(), NODE_CHECKED_NONE, isOneLevel);
	}

	public static TreePanelNode buildLogicalOrgTreeNoChecked(TreePanelNode rootNode, List<WsPlasOrg> orgList) {
		return buildLogicalOrgTreeNoChecked(rootNode, orgList, false);
	}

	/**
	 * 物理机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode, List<WsPlasOrg> orgList, boolean isOneLevel) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, new ArrayList<WsPlasUser>(), NODE_CHECKED_UNDEFINED, isOneLevel);
	}

	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode, List<WsPlasOrg> orgList) {
		return buildPhysicalOrgTree(rootNode, orgList, false);
	}

	public static TreePanelNode buildPhysicalOrgTreeNoChecked(TreePanelNode rootNode, List<WsPlasOrg> orgList, boolean isOneLevel) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, new ArrayList<WsPlasUser>(), NODE_CHECKED_NONE, isOneLevel);
	}

	public static TreePanelNode buildPhysicalOrgTreeNoChecked(TreePanelNode rootNode, List<WsPlasOrg> orgList) {
		return buildPhysicalOrgTreeNoChecked(rootNode, orgList, false);
	}

	/**
	 * 公用机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildOrgTree(String treeType, TreePanelNode rootNode, List<WsPlasOrg> orgList) {
		return buildOrgUserTree(treeType, rootNode, orgList, new ArrayList<WsPlasUser>(), NODE_CHECKED_UNDEFINED, false);
	}

	/**
	 * 机构与人员关系树 - 逻辑
	 * 
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @return
	 */
	public static TreePanelNode buildLogicalOrgUserTree(TreePanelNode rootNode, List<WsPlasOrg> orgList, List<WsPlasUser> userList) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED, false);
	}

	/**
	 * 机构与人员关系树 - 物理
	 * 
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgUserTree(TreePanelNode rootNode, List<WsPlasOrg> orgList, List<WsPlasUser> userList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED, false);
	}

	public static TreePanelNode buildPhysicalOrgUserTreeNoCheck(TreePanelNode rootNode, List<WsPlasOrg> orgList, List<WsPlasUser> userList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_NONE, false);
	}

	/**
	 * 机构与人员关系树(逻辑/物理)
	 * 
	 * @param dimeCd
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @return
	 */
	public static TreePanelNode buildOrgUserTree(String dimeCd, TreePanelNode rootNode, List<WsPlasOrg> orgList, List<WsPlasUser> userList,
			String checked, boolean isOneLevel) {

		Map<String, List<WsPlasUser>> orgUsersMap = PlasCacheUtil.getOrgUsersMap(userList);
		Map<String, List<WsPlasOrg>> orgOrgsMap = PlasCache.getOrgOrgsMap(dimeCd);// getOrgOrgsMap(treeType, orgList);
		
		PlasCacheUtil.buildOrgUserTree(rootNode, orgOrgsMap, orgUsersMap, checked, isOneLevel);

		//不显示隐藏节点
		TreePanelUtil.refreshMoveOrgHidden(rootNode);
		
		return rootNode;
	}
 
 
	/**
	 * 页面与功能关系树
	 * 三级：模块，菜单，功能
	 * @param pageList
	 * @param funcList
	 * @return
	 */
	public static TreePanelNode buildPageFunctionTree(List<AppPage> pageList, List<AppFunction> funcList) {
		// 特殊处理
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_PAGE_ID);
		rootNode.setText(DEFAULT_ROOT_PAGE_NAME);
		
		rootNode.setNodeType(TreePanelUtil.NODE_TYPE_PAGE);
		rootNode.setEntityId(DEFAULT_ROOT_PAGE_ID);

		Map<String, List<AppFunction>> pageFuncsMap = getPageFunctionMap(funcList);

		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (pageList != null && pageList.size() > 0) {
			for (AppPage page : pageList) {
				TreePanelNode pageNode = new TreePanelNode();
				getPageNode(pageNode,page);
				// 功能点
				pageNode.setChildren(getChildrenFuncNode(pageNode, pageFuncsMap));
				children.add(pageNode);
			}
		}
		rootNode.setChildren(children);

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenFuncNode(TreePanelNode pageNode, Map<String, List<AppFunction>> pageFuncsMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = pageNode.getEntityId();// 很重要

		List<AppFunction> funcs = pageFuncsMap.get(parentId);// 根据appCD获取
		if (funcs != null && funcs.size() > 0) {
			for (AppFunction func : funcs) {
				TreePanelNode funcNode = new TreePanelNode();
				funcNode.setId(func.getAppFunctionId());
				funcNode.setText(func.getFunctionName());
				funcNode.setNodeType(TreePanelUtil.NODE_TYPE_FUNCTION);//
				funcNode.setEntityId(func.getAppFunctionId());
				children.add(funcNode);
			}
		}
		return children;
	}

	private static Map<String, List<AppFunction>> getPageFunctionMap(List<AppFunction> funcList) {
		Map<String, List<AppFunction>> appFuncsMap = new HashMap<String, List<AppFunction>>();
		for (AppFunction func : funcList) {
			AppPage page = func.getAppPage();
			if (page == null) {
				continue;
			}
			String appPageId = page.getAppPageId();
			if (StringUtils.isNotBlank(appPageId)) {
				if (appFuncsMap.containsKey(appPageId)) {
					appFuncsMap.get(appPageId).add(func);
				} else {
					List<AppFunction> newFuncsList = new ArrayList<AppFunction>();
					newFuncsList.add(func);
					appFuncsMap.put(appPageId, newFuncsList);
				}
			}
		}
		return appFuncsMap;
	}

	/**
	 * 模块与菜单关系树
	 * 
	 * @param moduleList
	 * @param relList
	 * @return
	 */
	public static TreePanelNode buildModuleMenuTree(List<AppModule> moduleList, List<AppModuleMenuRel> relList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_MODULE_ID);
		rootNode.setText(DEFAULT_ROOT_MODULE_NAME);
		
		rootNode.setNodeType(TreePanelUtil.NODE_TYPE_PAGE);
		rootNode.setEntityId(DEFAULT_ROOT_MODULE_ID);// 特殊处理

		Map<String, List<AppModuleMenuRel>> moduleRelMap = getModuleMenuMap(relList);

		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (moduleList != null && moduleList.size() > 0) {
			for (AppModule module : moduleList) {
				TreePanelNode pageNode = new TreePanelNode();
				pageNode.setId(module.getAppModuleId());
				pageNode.setText(module.getModuleName());
				pageNode.setNodeType(TreePanelUtil.NODE_TYPE_MODULE);//
				pageNode.setEntityId(module.getAppModuleId());
				// 功能点
				pageNode.setChildren(getChildrenRelNode(pageNode, moduleRelMap));
				children.add(pageNode);
			}
		}
		rootNode.setChildren(children);

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenRelNode(TreePanelNode pageNode, Map<String, List<AppModuleMenuRel>> moduleRelMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = pageNode.getEntityId();// 很重要

		List<AppModuleMenuRel> rels = moduleRelMap.get(parentId);// 
		if (rels != null && rels.size() > 0) {
			for (AppModuleMenuRel rel : rels) {
				TreePanelNode funcNode = new TreePanelNode();
				funcNode.setId(rel.getAppModuleMenuRelId());// 关系ID
				funcNode.setText(rel.getAppMenu().getMenuName());
				funcNode.setNodeType(TreePanelUtil.NODE_TYPE_MENU);
				funcNode.setEntityId(rel.getAppModuleMenuRelId());
				children.add(funcNode);
			}
		}
		return children;
	}

	private static Map<String, List<AppModuleMenuRel>> getModuleMenuMap(List<AppModuleMenuRel> relList) {
		Map<String, List<AppModuleMenuRel>> moduleRelMap = new HashMap<String, List<AppModuleMenuRel>>();
		for (AppModuleMenuRel rel : relList) {
			AppModule module = rel.getAppModule();
			if (module == null) {
				continue;
			}
			String appModuleId = module.getAppModuleId();
			if (StringUtils.isNotBlank(appModuleId)) {
				if (moduleRelMap.containsKey(appModuleId)) {
					moduleRelMap.get(appModuleId).add(rel);
				} else {
					List<AppModuleMenuRel> newRelList = new ArrayList<AppModuleMenuRel>();
					newRelList.add(rel);
					moduleRelMap.put(appModuleId, newRelList);
				}
			}
		}
		return moduleRelMap;
	}

	/**
	 * 生成指定类型(treePanelType)的报销机构树(因为涉及到费用归属问题)
	 * 
	 * @param nodeType
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelOrgLogicalCost(String uiid) {
		return getTreeNodePanelCost(uiid, false);
	}

	public static TreePanelNode getTreeNodePanelUserLogicalCost(String uiid) {
		return getTreeNodePanelCost(uiid, true);
	}
	
	public static TreePanelNode getTreeNodePanelCost(String uiid, boolean bIncludeUser){
		return getTreeNodePanelCost(uiid, bIncludeUser,false);
	}

	// 供"出差审批"模块,选择随行人员使用
	// 规则说明:
	// 1.若是决策层用户,显示"决策层"以及"所辖中心列表"
	// 2.若非决策层用户,显示"所属中心列表"

	public static TreePanelNode getTreeNodePanelCost(String uiid, boolean bIncludeUser,boolean orgMuti) {

		TreePanelNode rootNode = getRootOrg();
		rootNode.setChecked(orgMuti?NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED);

		List<WsPlasOrg> orgs = PlasCache.getOrgEnableList();
		List<WsPlasUser> users = new ArrayList<WsPlasUser>();

		if (bIncludeUser) {
			users = PlasCache.getUserActiveList();
		}

		// 搜索员工涉及的中心列表(负责两个部门或者隶属在某个机构)
		List<WsPlasOrg> relaOrgs = PlasCache.getRelationCenterOrgs(uiid);
		for (int i = 0; i < relaOrgs.size(); i++) {
			TreePanelNode tmpNode = getOrgNode(relaOrgs.get(i));
			tmpNode.setChecked(orgMuti?NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED);
			if(orgMuti){
				tmpNode = buildPhysicalOrgUserTreeNoCheck(tmpNode, orgs, users);
			}else{
				buildPhysicalOrgUserTree(tmpNode, orgs, users);
			}
			rootNode.getChildren().add(tmpNode);
		}

		return rootNode;
	}

	private static TreePanelNode getOrgNode(WsPlasOrg org) {
		TreePanelNode node = new TreePanelNode();
		node.setId(org.getPlasOrgId());
		node.setText(org.getOrgName());
		
		node.setEntityId(org.getPlasOrgId());// 特殊处理
		node.setEntityCd(org.getOrgCd());
		node.setEntityName(org.getOrgName());
		node.setOrgOrUser(NODE_TYPE_ORG);
		node.setNodeType(NODE_TYPE_ORG);
		node.setChecked(NODE_CHECKED_NONE);

		return node;
	}

	/**
	 * 功能:机构根节点
	 * 
	 * @return
	 */
	public static TreePanelNode getRootOrg() {
		TreePanelNode node = new TreePanelNode();
		node.setId(DEFAULT_ROOT_ORG_ID);
		node.setText(DEFAULT_ROOT_ORG_NAME);
		node.setEntityId(DEFAULT_ROOT_ORG_ID);
		node.setEntityCd(DEFAULT_ROOT_ORG_CD);
		node.setEntityBizCd(DEFAULT_ROOT_ORG_BIZ_CD);
		node.setEntityName(DEFAULT_ROOT_ORG_NAME);
		
		node.setNodeType(NODE_TYPE_ORG);
		node.setOrgOrUser(NODE_TYPE_ORG);
		return node;
	}

	/**
	 * 根据缓存获取逻辑机构机构树
	 * 
	 * @return
	 */
	public static TreePanelNode getCacheLogicalTree() {

		return buildLogicalOrgUserTree(getRootOrg(), PlasCache.getOrgEnableList(), PlasCache.getUserActiveList());
	}

	public static TreePanelNode getCachePhysicalTree() {
		return buildPhysicalOrgUserTree(getRootOrg(), PlasCache.getOrgEnableList(), PlasCache.getUserActiveList());
	}
	//构建菜单Map(以moduleId为key）
	public static Map<String,List<AppMenu>> getMenuMap(){
		AppModuleMenuRelManager appModuleMenuRelManager = SpringContextHolder.getBean("appModuleMenuRelManager");
		return appModuleMenuRelManager.getMenuMap();

	}
	/**
	 * 构建功能Map（以pageId为key）
	 */
	public static Map<String,List<AppFunction>> getFunctionMap(){
		AppFunctionManager appFunctionManager = SpringContextHolder.getBean("appFunctionManager");
		return appFunctionManager.getFunctionMap();
	}
	/**
	 * 
	 * Description:构建模块菜单树（带复选框）
	 */
	public static TreePanelNode createAppModuleMenuTree(List<AppMenu> checks){
		
		AppModuleManager appModuleManager = SpringContextHolder.getBean("appModuleManager");
		List<AppModule> moduleList = appModuleManager.getAllOrdered();//modify by huangbijin 2012-02-28  模块排序
		
		TreePanelNode treeRoot = new TreePanelNode();
		treeRoot.setId(DEFAULT_ROOT_MODULE_ID);
		treeRoot.setText(DEFAULT_ROOT_MODULE_NAME);
		
		treeRoot.setEntityId(DEFAULT_ROOT_MODULE_ID);
		treeRoot.setEntityCd(DEFAULT_ROOT_MODULE_CD);
		treeRoot.setNodeType(NODE_TYPE_ROOT);
		
		TreePanelNode moduleNode = null;
		
		Map<String,List<AppMenu>> menuMap = getMenuMap();
		List<AppMenu> menuList = null;
		for(AppModule appModule : moduleList){
			moduleNode = new TreePanelNode();
			getModuleNode(moduleNode,appModule);
			menuList = menuMap.get(appModule.getAppModuleId());
			if(null!=menuList) {
				moduleNode.setChildren(getMenuNodes(menuList,checks));
			}
			
			addTreeNode(treeRoot,moduleNode,"0");

			refreshNodeStatus(moduleNode);
	
		}
		return treeRoot;
	}
	/**
	 * 构建模块菜单功能树（无复选框）
	 */
	public static TreePanelNode createAppModuleMenuFuncTree(){

		
		TreePanelNode treeRoot = new TreePanelNode();
		treeRoot.setId(DEFAULT_ROOT_MODULE_ID);
		treeRoot.setText(DEFAULT_ROOT_MODULE_NAME);
		
		treeRoot.setEntityId(DEFAULT_ROOT_MODULE_ID);
		treeRoot.setEntityCd(DEFAULT_ROOT_MODULE_CD);
		treeRoot.setNodeType(NODE_TYPE_ROOT);
		
		//构建模块层
		getModuleNodes(treeRoot);
		//构建菜单
		Map<String,List<AppMenu>> menuMap = getMenuMap();
		List<AppMenu> menuList = null;
		TreePanelNode node = null;
		for(Object key : menuMap.keySet()){
			menuList = menuMap.get(key);
			for(AppMenu appMenu:menuList){
				node = new TreePanelNode();
				getMenuNode(node,appMenu);
				addTreeNode(treeRoot, node, key.toString());
			}
		}
		//构建页面
		getPageNodes(treeRoot,null);
		
		//构建功能
		getFuncNodes(treeRoot,null);

		return treeRoot;
	}
	public static void getFuncNodes(TreePanelNode treeRoot,List<AppFunction> checks){
		AppFunctionManager appFunctionManager = SpringContextHolder.getBean("appFunctionManager");

		TreePanelNode node = null;
		for(AppFunction obj : appFunctionManager.getAll()){
			node=new TreePanelNode();
			getFuncNode(node,obj);
			if(null!=checks){
				if(checks.contains(obj)){
					node.setChecked(NODE_CHECKED_CHECKED);
				}else {
					node.setChecked(NODE_CHECKED_NONE);
				}
			}
			addTreeNode(treeRoot, node, obj.getAppPage().getAppPageId());
			
		}

	}
	public static void getModuleNodes(TreePanelNode treeRoot){
		AppModuleManager appModuleManager = SpringContextHolder.getBean("appModuleManager");
		List<AppModule> moduleList = appModuleManager.getAll();
		TreePanelNode moduleNode = null;
		for(AppModule appModule : moduleList){
			moduleNode = new TreePanelNode();
			getModuleNode(moduleNode,appModule);

			addTreeNode(treeRoot,moduleNode,"0");
	
		}
	}
	public static void getModuleNode(TreePanelNode node,AppModule module){
		node.setId(module.getAppModuleId());
		node.setText(module.getModuleName());
		node.setEntityId(module.getAppModuleId());
		node.setEntityCd(module.getModuleCd());
		node.setEntityName(module.getModuleName());
		node.setNodeType(NODE_TYPE_MODULE);
	}
	public static List<TreePanelNode> getMenuNodes(List<AppMenu> menuList,List<AppMenu> checks){
		List<TreePanelNode> result = new ArrayList<TreePanelNode>();
		TreePanelNode node = null;
		for(AppMenu appMenu:menuList){
			node = new TreePanelNode();
			getMenuNode(node,appMenu);
			if(null!=checks){
				
				if(checks.contains(appMenu)){
					node.setChecked(NODE_CHECKED_CHECKED);
				}else {
					node.setChecked(NODE_CHECKED_NONE);
				}
			}
			result.add(node);
		}
		return result;
	}
	public static void getMenuNode(TreePanelNode node,AppMenu menu){
		node.setId(menu.getAppMenuId());
		node.setText(menu.getMenuName());
		node.setEntityId(menu.getAppMenuId());
		node.setEntityCd(menu.getMenuCd());
		node.setEntityName(menu.getMenuName());
		node.setNodeType(NODE_TYPE_MENU);
	}
	/**
	 * 
	 * Description:加载页面功能树（带复选框）
	 */
	public static TreePanelNode creatPageFuncTree(AppPage page,List<AppFunction> funcs , List<AppFunction> checks){
		TreePanelNode treeRoot = new TreePanelNode();
		getPageNode(treeRoot,page);
		
		TreePanelNode funcNode = null;

		for (AppFunction func : funcs) {
			funcNode = new TreePanelNode();
			getFuncNode(funcNode,func);
			if(null!=checks ){
				if(checks.contains(func)){
					funcNode.setChecked(NODE_CHECKED_CHECKED);
				}else{
					funcNode.setChecked(NODE_CHECKED_NONE);
				}
			}
			addTreeNode(treeRoot, funcNode, page.getAppPageId());	
		}
		return treeRoot;
	}
	public static void getPageNodes(TreePanelNode treeRoot,List<AppPage> checks){
		
		AppPageManager appPageManager = SpringContextHolder.getBean("appPageManager");
		TreePanelNode pageNode = null;
		for(AppPage obj:appPageManager.getAll()){
			pageNode = new TreePanelNode();
			getPageNode(pageNode,obj);
			if(null!=checks){
				if(checks.contains(obj)){
					pageNode.setChecked(NODE_CHECKED_CHECKED);
				}else {
					pageNode.setChecked(NODE_CHECKED_NONE);
				}
			}
			addTreeNode(treeRoot, pageNode, getPage2MenuMap().get(obj.getAppPageId()));
		}
	}
	public static Map<String,String> getPage2MenuMap(){
		AppMenuManager appMenuManager = SpringContextHolder.getBean("appMenuManager");
		Map<String,String> result = new HashMap<String, String>();
		for(AppMenu obj : appMenuManager.getAll()){
			result.put(obj.getAppPage().getAppPageId(), obj.getAppMenuId());
		}
		return result;
	}
	public static void getPageNode(TreePanelNode pageNode,AppPage page){
		pageNode.setId(page.getAppPageId());
		pageNode.setText(page.getPageName());
		pageNode.setEntityId(page.getAppPageId());
		pageNode.setEntityCd(page.getPageCd());
		pageNode.setEntityName(page.getPageName());
		pageNode.setNodeType(NODE_TYPE_PAGE);
	}

	public static void getFuncNode(TreePanelNode funcNode,AppFunction func){
		funcNode.setId(func.getAppFunctionId());
		funcNode.setText(func.getFunctionName());
		funcNode.setEntityCd(func.getFunctionCd());
		funcNode.setEntityId(func.getAppFunctionId());
		funcNode.setNodeType(NODE_TYPE_FUNCTION);
	}
	//非常好用
	private static void addTreeNode(TreePanelNode treeRoot, TreePanelNode treeChild,
			String parentId) {
		if (StringUtils.isEmpty(parentId) || treeRoot.getId().equals(parentId)) {
			//如果节点已经存在，退出
			for(TreePanelNode child:treeRoot.getChildren()){
				if(child.getId().equals(treeChild.getId()))
					return;
			}
			treeRoot.addChild(treeChild);

		} else {
			addTreeNode(treeChild, treeRoot.getChildren(), parentId);
		}
	}
	private static void addTreeNode(TreePanelNode treeChild, List<TreePanelNode> list,
			String parentId) {
		boolean found = false;
		for (TreePanelNode easyTree : list) {
			if (easyTree.getId().equals(parentId)) {
				easyTree.addChild(treeChild);
				found = true;
				break;
			}
		}
		if (!found) {
			for (TreePanelNode easyTree : list) {
				addTreeNode(treeChild, easyTree.getChildren(), parentId);
			}
		}
	}
	/*
	 * 更新节点状态
	 * 
	 * @param node
	 * @return
	 */
	public static TreePanelNode refreshNodeStatus(TreePanelNode node) {

		//所有的直接儿子节点总数
		int totalSonCount = 0;
		//选中或半选的直接儿子节点总数
		int checkedSonCount = 0;

		if (node != null && node.getChildren() != null) {
			totalSonCount = node.getChildren().size();
			TreePanelNode tNode = null;
			for (int i = 0; i < totalSonCount; i++) {
				tNode = node.getChildren().get(i);
				if (tNode == null) {
					continue;
				}
				tNode = refreshNodeStatus(tNode);
				//全选或半选
				if (NODE_CHECKED_CHECKED.equals(tNode.getChecked()) || NODE_CHECKED_HALF.equals(tNode.getChecked())) {
					checkedSonCount++;
				}
			}
		}

		if (totalSonCount == 0)
			return node;

		String pNodeStatusCd = NODE_CHECKED_NONE;
		if (checkedSonCount == 0) {
			pNodeStatusCd = NODE_CHECKED_NONE;
		} else if (totalSonCount == checkedSonCount) {
			pNodeStatusCd = NODE_CHECKED_CHECKED;
		} else if (totalSonCount > checkedSonCount && checkedSonCount > 0) {
			pNodeStatusCd = NODE_CHECKED_HALF;
		}
		
		if(node!= null){
			node.setChecked(pNodeStatusCd);
		}
		return node;
	}
	 
	

	/**
	 * @param roleList
	 * @return
	 */
		
	public static TreePanelNode buildRoleTree(List<WsPlasRole> roleList){
		return buildRoleTree(roleList, null);
	}
	/**
	 * @param roleList
	 * @param selRoleIdList 若不空,则认为阜选择
	 * @return
	 */
	public static TreePanelNode buildRoleTree(List<WsPlasRole> roleList,List<String> selRoleIdList){

		// 遍历所有角色，分拣到各自的treeNode树里
		TreePanelNode treeRoot = getRootTreeNodeApp();
		Map<String, WsPlasRoleGroup> mapGroups = getMapGroups(roleList); 
		Map<String, List<WsPlasRole>> mapGroupRoleList = getMapGroupRoles(roleList);
		Map<String,TreePanelNode> mapRoots = new HashMap<String,TreePanelNode>();

		String tAppId = null;
		TreePanelNode root = null;
		for (WsPlasRole tRole : roleList) {
			if(tRole!= null){
				tAppId = tRole.getAppId();
				//若发现新应用,则构造root根
				if(!mapRoots.keySet().contains(tAppId)){
					root = new TreePanelNode();
					root.setId(tAppId);
					root.setText(tRole.getAppChnName());
					root.setEntityId(tAppId);
					root.setEntityCd(tRole.getAppCd());//不知道有没有用
					root.setEntityName(tRole.getAppChnName());
					
					treeRoot.getChildren().add(root);
					mapRoots.put(tAppId, root);
				}
			}
		}
		 
		for (TreePanelNode node : treeRoot.getChildren()) {
			getTreePanel(node, mapGroups, mapGroupRoleList, selRoleIdList);
		}
		

		
		//add by huangbijin 2012-02-02
		if(selRoleIdList!= null){
			refreshNodeStatus(treeRoot);
		}
		return treeRoot;
	}
		
//	private static void getTreePanel(TreePanelNode node,Map<String, WsPlasRoleGroup>mapGroups, Map<String,List<WsPlasRole>> moduleRolesMap ) {
//		getTreePanel(node, mapGroups, moduleRolesMap, null);
//	}
	private static void getTreePanel(TreePanelNode node,Map<String, WsPlasRoleGroup>mapGroups, Map<String,List<WsPlasRole>> moduleRolesMap ,List<String> selRoleIdList){

		TreePanelNode tGNode =null;
		TreePanelNode tNode =null;
		WsPlasRoleGroup tGroup = null;
		List<WsPlasRole> tRoles = null;
		
		for (String tGroupId : mapGroups.keySet()) {
			//角色组
			tGroup = mapGroups.get(tGroupId);
			if(node.getEntityId().equals(tGroup.getParentId())){
				if(tGroup != null){
					tGNode = new TreePanelNode();
					tGNode.setId(tGroup.getPlasRoleGroupId());
					tGNode.setText(tGroup.getRoleGroupName());
					tGNode.setOrgOrUser(NODE_TYPE_ROLE_GROUP);
					tGNode.setNodeType(NODE_TYPE_ROLE_GROUP);
					tGNode.setEntityId(tGroup.getPlasRoleGroupId());
					tGNode.setEntityCd(tGroup.getRoleGroupCd());
					tGNode.setEntityName(tGroup.getRoleGroupName());
					
					//角色
					tRoles = moduleRolesMap.get(tGroupId);
					if(tRoles!= null){
						for (WsPlasRole tRole : tRoles) {
							tNode = new TreePanelNode();
							tNode.setId(tRole.getPlasRoleId());
							tNode.setText(tRole.getRoleName());
							tNode.setOrgOrUser(NODE_TYPE_ROLE);
							tNode.setNodeType(NODE_TYPE_ROLE);
							tNode.setEntityId(tRole.getPlasRoleId());
							tNode.setEntityCd(tRole.getRoleCd());
							tNode.setEntityName(tRole.getRoleName());
							
							if(selRoleIdList!= null){
								//选择框
								if(selRoleIdList.contains(tRole.getRoleCd())){
									tNode.setChecked(TreePanelUtil.NODE_CHECKED_CHECKED);
								}else{
									tNode.setChecked(TreePanelUtil.NODE_CHECKED_NONE);
								}
							}
							
							tGNode.getChildren().add(tNode);
						}
					}
				}
				node.getChildren().add(tGNode);
			}
		}
	}

	
	private static Map<String, WsPlasRoleGroup> getMapGroups(List<WsPlasRole> roleList){
		 Map<String, WsPlasRoleGroup> mapGroups = new HashMap<String, WsPlasRoleGroup>();
		 for (WsPlasRole tRole : roleList) {
			if(!mapGroups.keySet().contains(tRole.getGroupId())){
				mapGroups.put(tRole.getGroupId(), getWsPlasRoleGroup(tRole));
			}
		}
		return mapGroups;
	}
	public static WsPlasRoleGroup getWsPlasRoleGroup(WsPlasRole role){
		WsPlasRoleGroup group = new WsPlasRoleGroup();
//		private String plasRoleGroupId;
//		private String roleGroupCd;
//		private String roleGroupBizCd;
//		private String roleGroupName;
//		private String parentId;
//		private Long sequenceNo;
		group.setPlasRoleGroupId(role.getGroupId());
		group.setRoleGroupCd(role.getGroupCd());
		group.setRoleGroupName(role.getGroupName());
		group.setParentId(role.getAppId());
//		group.setSequenceNo(role.getSequenceNo());
		return group;
	}
	private static Map<String, List<WsPlasRole>> getMapGroupRoles(List<WsPlasRole> roleList){
		 Map<String, List<WsPlasRole>> mapGroupRoles = new HashMap<String, List<WsPlasRole>>();
		 List<WsPlasRole> tRoleList = null;
		 for (WsPlasRole tRole : roleList) {
			 
			if(mapGroupRoles.keySet().contains(tRole.getGroupId())){
				tRoleList = mapGroupRoles.get(tRole.getGroupId());
				tRoleList.add(tRole);
				mapGroupRoles.put(tRole.getGroupId(), tRoleList);
			}else{
				tRoleList = new ArrayList<WsPlasRole>();
				tRoleList.add(tRole);
				mapGroupRoles.put(tRole.getGroupId(), tRoleList);
			}
		}
		return mapGroupRoles;
	}

	//构造"应用"根节点
	private static TreePanelNode getRootTreeNodeApp() {
		TreePanelNode node = new TreePanelNode();
		node.setId(DEFAULT_ROOT_APP_ID);
		node.setText(DEFAULT_ROOT_APP_NAME);
		
		node.setEntityId(DEFAULT_ROOT_APP_ID);
		node.setEntityCd(DEFAULT_ROOT_APP_CD);
		node.setEntityName(DEFAULT_ROOT_APP_NAME);
		
		node.setOrgOrUser(NODE_TYPE_ROOT);
		node.setNodeType(NODE_TYPE_ROOT);
		node.setChecked(NODE_CHECKED_UNDEFINED);
		return node;
	}
	
	

	/**
	 * 获取区域节点
	 * @param appDictTypeCd 区域类型 
	 * @param multiFlg 复选
	 * @return
	 */
	public static TreePanelNode getDictNode(String appDictTypeCd,boolean multiFlg){

		WsAppDictType type = PlasCache.getDictType(appDictTypeCd);
		if(type == null)
			return null;

		String nodeCd = type.getDictTypeCd();
		String nodeName = type.getDictTypeName();
		int count = 1;
		if(StringUtils.isBlank(nodeCd)){
			nodeCd = "node"+ String.valueOf(count++);
		}
		TreePanelNode rootNode = getNode(nodeCd,nodeName, NODE_TYPE_ROLE_AREA, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED);//区域
		Map<String,String> map = PlasCache.getDictDataMap(appDictTypeCd);
		if( map != null && map.keySet().size() > 0){
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				nodeCd = iter.next();
				if(StringUtils.isBlank(nodeCd)){
					continue;
				}
				nodeName = map.get(nodeCd);
				rootNode.getChildren().add(getNode(nodeCd,nodeName, NODE_TYPE_ROLE_PROJECT, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED));//项目
			}
		}
		return rootNode;
	}
	
	private static TreePanelNode getNode(String nodeCd, String nodeName, String typeCd, String checkedType){
		TreePanelNode tmp = new TreePanelNode();
		tmp.setEntityId(nodeCd);
		tmp.setEntityCd(nodeCd);
		tmp.setEntityBizCd(nodeCd);
		tmp.setEntityName(nodeName);
		tmp.setNodeType(typeCd);
		tmp.setChecked(checkedType);

		tmp.setId(nodeCd);
		tmp.setText(nodeName);
		return tmp;
	}
	
	/**
	 * 获取类型列表
	 * @param projectList 项目列表
	 * @param mateTypeList 甲供料类型列表
	 * @return
	 */
	public static TreeNode buildMateTypeTreeNode(List<ContProjectCode> projectList,  List<MateOwnerType> mateTypeList){
		return buildMateTypeTreeNode(NODE_CHECKED_UNDEFINED, projectList, mateTypeList);
	}
	public static TreeNode buildMateTypeTreeNode(String nodeCheckType,List<ContProjectCode> projectList,  List<MateOwnerType> mateTypeList){
		
		TreeNode root = new TreeNode();
		root.setId("0");
		root.setFinType("");//根节点
		root.setText("甲供材料类别");
		root.setOrderNo(new Long(0));
		root.setChecked(nodeCheckType);
		
		Map<String,String> projectCdMap = new HashMap<String,String>();
		for (ContProjectCode tmpProject : projectList) {
//			System.out.println("------------------- " + tmpProject.getProjectCd() + "," + tmpProject.getProjectName());
			projectCdMap.put(tmpProject.getProjectCd(),tmpProject.getProjectName());
		}

		//获取映射关系
		Map<String, List<MateOwnerType>> projectMateMap = new HashMap<String, List<MateOwnerType>>();
		Map<String, List<MateOwnerType>> mateMatesMap = new HashMap<String, List<MateOwnerType>>();
		String tmpPid = null;
		List<MateOwnerType> tmpList = null;
		for (MateOwnerType tmpType : mateTypeList) {
//			System.out.println("------------------- " + tmpType.getProjectCd()+","+ tmpType.getOwnerMaterialType() + "," + tmpType.getTypeName() + "," + tmpType.getOwnerParMaterialType());
//			projectCd or mateId
			tmpPid = tmpType.getOwnerParMaterialType();
			
			//项目
			if(projectCdMap.containsKey(tmpPid)){
				if( projectMateMap.containsKey(tmpPid)){
					projectMateMap.get(tmpPid).add(tmpType);
				}else{
					tmpList = new ArrayList<MateOwnerType>();
					tmpList.add(tmpType);
					projectMateMap.put(tmpPid, tmpList);
				}
			}
			//甲供料类型
			else{
				if(StringUtils.isNotBlank(tmpPid)){
					if( mateMatesMap.containsKey(tmpPid)){
						mateMatesMap.get(tmpPid).add(tmpType);
					}else{
						tmpList = new ArrayList<MateOwnerType>();
						tmpList.add(tmpType);
						
						mateMatesMap.put(tmpPid, tmpList);
					}
				}else{
					System.out.println(">>>>>>>>>>>>>>>> matetypeid:" + tmpType.getMateOwnerTypeId());
				}
			}
		}
		
		

		//构造项目
		List<TreeNode> projectNodeList = new ArrayList<TreeNode>();
		TreeNode pNode = null;
		for (ContProjectCode tProject : projectList) {
			pNode = new TreeNode();
			pNode.setId(tProject.getProjectCd());
			pNode.setFinType(tProject.getProjectCd());//注意: 节点类型
			pNode.setText(tProject.getProjectName());
			pNode.setParentId("0");//根节点
			pNode.setChecked(nodeCheckType);
			//System.out.println("================= " + tProject.getProjectCd() + "," + tProject.getProjectName());
			
			List<TreeNode> projectSonList = new ArrayList<TreeNode>();
			List<MateOwnerType> tMateList = projectMateMap.get(tProject.getProjectCd());
			if(tMateList != null){
				for (MateOwnerType item : tMateList) {
					TreeNode childNode = new TreeNode();
					childNode.setId(item.getOwnerMaterialType());//id
					childNode.setText(item.getTypeName());//name
					childNode.setParentId(pNode.getId());
					childNode.setParentName(pNode.getText());
					childNode.setFinItemCd(item.getOwnerParMaterialType());
					childNode.setFinType(pNode.getFinType());
					childNode.setTrueId(item.getMateOwnerTypeId());
					childNode.setChecked(nodeCheckType);
					childNode.setChildren(buildMateTypeNode(nodeCheckType, childNode, mateMatesMap));
					projectSonList.add(childNode);
				}
			}
			pNode.setChildren(projectSonList);
			projectNodeList.add(pNode);
		}
		root.setChildren(projectNodeList);
		return root;
	}

	private static List<TreeNode> buildMateTypeNode(String nodeCheckType, TreeNode tNode, Map<String, List<MateOwnerType>> mateMatesMap) {
		List<TreeNode> rtnList = new ArrayList<TreeNode>();
		List<MateOwnerType> tMateList = mateMatesMap.get(tNode.getId());
		if(tMateList != null){
			for (MateOwnerType item : tMateList) {
				TreeNode childItem = new TreeNode();
				childItem.setId(item.getOwnerMaterialType());
				childItem.setText(item.getTypeName());
				childItem.setParentId(tNode.getId());
				childItem.setParentName(tNode.getText());
				childItem.setFinItemCd(item.getOwnerParMaterialType());
				childItem.setFinType(tNode.getFinType());//注意: 节点类型
				childItem.setTrueId(item.getMateOwnerTypeId());
				childItem.setChecked(nodeCheckType);
				childItem.setChildren(buildMateTypeNode(nodeCheckType, childItem, mateMatesMap));
				rtnList.add(childItem);
			}
		}
		return rtnList;
	}
	
	/**
	 * 获取指定ID的树节点
	 * @param node
	 * @param findNodeId
	 * @return
	 */
	public static TreePanelNode findTreeNode(TreePanelNode node,String findNodeId){
		return findTreeNode(node, findNodeId, null, null);
	}
	public static TreePanelNode findTreeNode(TreePanelNode node,String findNodeId,String nodeTypeCd,String extNodeTypeCd){
//		System.out.println(">>>>>>>>>>>>>find:" + findNodeId + "/" +node.getId() + "," + node.getText());
		if(StringUtils.isBlank(findNodeId))
			return null;
		else{
			
//			//若非指定的节点类型
//			if(StringUtils.isNotBlank(nodeTypeCd)){
//				if(!nodeTypeCd.equals(node.getNodeType()))
//					return null;
//			}
//
//			//若排除的节点类型
//			if(StringUtils.isNotBlank(extNodeTypeCd)){
//				if(!extNodeTypeCd.equals(node.getNodeType()))
//					return null;
//			}
			
			TreePanelNode rtnNode = null;
			if(findNodeId.equals(node.getId()))
				return node;
			
			List<TreePanelNode> list = node.getChildren();
			if(list!= null){
				for (TreePanelNode t : list) {
					if(t!= null){
						rtnNode = findTreeNode(t, findNodeId);
						if(rtnNode != null)
							return rtnNode;
					}
				}
				return null;
			} else
				return null;
		}
	}
	
	

	/**
	 * 不显示隐藏机构
	 * @param node
	 */
	public static void refreshMoveOrgHidden(TreePanelNode node){
		if(node == null)
			return;
		List<TreePanelNode> childList = node.getChildren();
		if (childList != null) {
			WsPlasOrg tOrg = null;
			TreePanelNode tNode = null;
			for (Iterator<TreePanelNode> iterator = childList.iterator(); iterator.hasNext();) {
				tNode = iterator.next();
				if(tNode != null){
					if("1".equals(tNode.getOrgOrUser())){
						tOrg = PlasCache.getOrgById(tNode.getEntityId());
						if(tOrg != null){
							if(tOrg.getVisableFlg().booleanValue()){
								refreshMoveOrgHidden(tNode);
							}else{
								System.out.println("隐藏机构! 名称：" + tNode.getEntityName());;
								iterator.remove();
							}
						}
					} 
				}
			}
		}
	}

	//不显示其他邮箱
	public static void refreshMoveOrgOtherEmailGroup(TreePanelNode node){

		if(node == null)
			return;
		
		List<TreePanelNode> childList = node.getChildren();
		if(childList != null){
			TreePanelNode tNode = null;
			for (Iterator<TreePanelNode> iterator = childList.iterator(); iterator.hasNext();) {
				tNode = iterator.next();
				if(GlobalConstants.ORG_OTHER_EMAIL_GROUP.equals(tNode.getId())){
					iterator.remove();
					break;
				}
			}
		}
	}
	//若一个机构的两个职位对应同一个人，只保留一个
	public static void refreshMoveDumpPosNode(TreePanelNode node){
		
		if(node == null)
			return;

		List<TreePanelNode> childList2 = new ArrayList<TreePanelNode>();
		Set<String> tUiidSet = new HashSet<String>();

		List<TreePanelNode> childList = node.getChildren();
		if(childList != null){
			TreePanelNode tNode = null;
			String tUiid = null;
			for(int i=0; i<childList.size(); i++){
				tNode = childList.get(i);
//				System.out.println(">>>>>>>>>>>>>>>>>>>>" + tNode.getExtParam() + "," + tNode.getText() + "," + tNode.getNodeType());
				if(TreePanelUtil.NODE_TYPE_USER.equals(tNode.getNodeType())){
					tUiid = tNode.getExtParam();
					if(StringUtils.isNotBlank(tUiid)){
						if(tUiidSet.contains(tUiid)){
							System.out.println("存在同机构下兼职人员!" + tUiid + "," + tNode.getText() + ",");
						}else{
							tUiidSet.add(tUiid);
							childList2.add(tNode);
						}
					}
				}else{
					refreshMoveDumpPosNode(tNode);
					childList2.add(tNode);
				}
			}
		}
		node.setChildren(childList2);
	}
}
