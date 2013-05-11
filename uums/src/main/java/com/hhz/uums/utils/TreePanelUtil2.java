package com.hhz.uums.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.entity.app.AppPage;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoAcct;
import com.hhz.uums.vo.vw.VoApp;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.vw.VoRole;
import com.hhz.uums.vo.vw.VoRoleGroup;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.vo.vw.VoUser;
import com.hhz.uums.vo.ws.WsAppDictData;
import com.hhz.uums.vo.ws.WsPlasRole;

/**
 * @author jiaoxf
 * 
 */
public class TreePanelUtil2 {

	// 节点状态 : 9-根节点 1-机构 0-用户
	public static String NODE_TYPE_ROOT = "9";
	public static String NODE_TYPE_ORG = "1";
	public static String NODE_TYPE_USER = "0";
	public static String NODE_TYPE_SYSP = "3";
	public static String NODE_TYPE_ACCT = "0";
	
	public static String NODE_TYPE_APP = "1";
	public static String NODE_TYPE_ROLE = "0";
	public static String NODE_TYPE_ROLE_GROUP = "2";

	// 选择状态 : 0-不选中 1-选中 2-半选
	public static String NODE_CHECKED_UNDEFINED = "undefined";
	public static String NODE_CHECKED_NONE = "0";
	public static String NODE_CHECKED_CHECKED = "1";
	public static String NODE_CHECKED_HALF = "2";

	// 逻辑物理视图1-物理 2-逻辑
	public static String TREE_TYPE_PHYSICAL = "1";
	public static String TREE_TYPE_LOGICAL = "2";

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
	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode, List<VoOrg> orgList) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, new ArrayList<VoUser>(),NODE_CHECKED_UNDEFINED);
	}

	/**
	 * 物理机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode, List<VoOrg> orgList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, new ArrayList<VoUser>(),NODE_CHECKED_UNDEFINED);
	}

	/**
	 * 公用机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildOrgTree(String treeType, TreePanelNode rootNode, List<VoOrg> orgList) {
		return buildOrgUserTree(treeType, rootNode, orgList, new ArrayList<VoUser>(), NODE_CHECKED_UNDEFINED);
	}

	/**
	 * 机构与人员关系树 - 逻辑
	 * 
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @return
	 */
	public static TreePanelNode buildLogicalOrgUserTree(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED);
	} 
	public static TreePanelNode buildLogicalOrgUserTreeNoCheck(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildOrgUserTree(TREE_TYPE_LOGICAL, rootNode, orgList, userList, NODE_CHECKED_NONE);
	} 

	/**
	 * 机构与人员关系树 - 物理
	 * 
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgUserTree(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED);
	}
	public static TreePanelNode buildPhysicalOrgUserTreeNoCheck(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_NONE);
	} 
	public static TreePanelNode buildPhysicalOrgUserTreeUndefined(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildOrgUserTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED);
	}

	/**
	 * 机构与人员关系树(逻辑/物理)
	 * 
	 * @param treeType
	 * @param rootNode
	 * @param orgList
	 * @param userList
	 * @param checked
	 * @return
	 */
	public static TreePanelNode buildOrgUserTree(String treeType, TreePanelNode rootNode,
			List<VoOrg> orgList,
			List<VoUser> userList, String checked) {

		Map<String, List<VoUser>> orgUsersMap = getOrgUsersMap(treeType, userList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode(treeType, rootNode, orgOrgsMap, orgUsersMap, checked));
 
		return rootNode;
	}
	

	private static List<TreePanelNode> getChildrenNode(String treeType, TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoUser>> orgUsersMap,
			String checked) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<VoUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {
			TreePanelNode userNode = null;
			for (VoUser user : users) {
				userNode = getTreePanelUserNoChild(user);
				userNode.setChecked(checked);
				children.add(userNode);
			}
		}

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			TreePanelNode orgNode = null;
			for (VoOrg org : orgs) {
				orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode(treeType, orgNode, orgOrgsMap, orgUsersMap, checked));
				//System.out.println(">>>>>>>>>>>>>> " + checked + " | " + orgNode.getEntityName());
				if(NODE_CHECKED_NONE.equals(checked)){//人员调动使用
					refreshNodeChecked(orgNode);
				}
				children.add(orgNode);
			}
		}
		return children;
	}

	// 遍历人员,设置机构与人员关系
	public static Map<String, List<VoUser>> getOrgUsersMap(String treeType, List<VoUser> userList) {

		Map<String, List<VoUser>> orgUsersMap = new HashMap<String, List<VoUser>>();
		String tmpParentOrgId = null;
		
		List<VoUser> newUserList = null;
		for (VoUser user : userList) {
			
			tmpParentOrgId = user.getParentOrgId();
			
//			// 默认:物理
//			if (TREE_TYPE_LOGICAL.equals(treeType)) {
//				prentOrgCd = user.getOrg().getOrgCd();
//			} else {
//				prentOrgCd = user.getPhysicalOrgCd();
//			}
			
			if(StringUtils.isBlank(tmpParentOrgId)){
				System.out.println("未找到上级机构! " + user.getUserName()+"["+user.getUiid()+"]");
			}
			else{
				if (orgUsersMap.containsKey(tmpParentOrgId)) {
					orgUsersMap.get(tmpParentOrgId).add(user);
				} else {
					newUserList = new ArrayList<VoUser>();
					newUserList.add(user);
					orgUsersMap.put(tmpParentOrgId, newUserList);
				}
			}
		}
		return orgUsersMap;
	}
	// 遍历人员,设置机构与账号关系
	public static Map<String, List<VoAcct>> getOrgAcctsMap(String treeType, List<VoAcct> acctList) {

		Map<String, List<VoAcct>> orgAcctsMap = new HashMap<String, List<VoAcct>>();
		String tmpParentOrgId = null;
		
		List<VoAcct> newUserList = null;
		for (VoAcct acct : acctList) {
			
			tmpParentOrgId = acct.getParentOrgId();
			
//			// 默认:物理
//			if (TREE_TYPE_LOGICAL.equals(treeType)) {
//				prentOrgCd = user.getOrg().getOrgCd();
//			} else {
//				prentOrgCd = user.getPhysicalOrgCd();
//			}
			
			if(StringUtils.isBlank(tmpParentOrgId)){
//				System.out.println("未找到上级机构! " + acct.getUserName()+"["+acct.getUiid()+"]");
				tmpParentOrgId = DEFAULT_ROOT_ORG_ID;
			}
			
			
			if (orgAcctsMap.containsKey(tmpParentOrgId)) {
				orgAcctsMap.get(tmpParentOrgId).add(acct);
			} else {
				newUserList = new ArrayList<VoAcct>();
				newUserList.add(acct);
				orgAcctsMap.put(tmpParentOrgId, newUserList);
			}
		}
		return orgAcctsMap;
	}

	// 遍历机构,设置机构与机构关系
	public static Map<String, List<VoOrg>> getOrgOrgsMap(List<VoOrg> orgList){
		return getOrgOrgsMap(null,orgList);
	}
	public static Map<String, List<VoOrg>> getOrgOrgsMap(String treeType, List<VoOrg> orgList) {

		Map<String, List<VoOrg>> orgOrgsMap = new HashMap<String, List<VoOrg>>();
		
		String tmpParentOrgId = null;
		List<VoOrg> newOrgList = null;
		for (VoOrg org : orgList) {
			tmpParentOrgId = org.getParentOrgId();
			if(StringUtils.isBlank(tmpParentOrgId)){
//				System.out.println("未找到上级机构! " + org.getOrgName()+"["+org.getOrgCd()+"]");
				tmpParentOrgId = DEFAULT_ROOT_ORG_ID;//TODO
			}
			
			if (orgOrgsMap.containsKey(tmpParentOrgId)) {
				orgOrgsMap.get(tmpParentOrgId).add(org);
			} else {
				newOrgList = new ArrayList<VoOrg>();
				newOrgList.add(org);
				orgOrgsMap.put(tmpParentOrgId, newOrgList);
			}
		}
		return orgOrgsMap;
	}

	/**
	 * 应用与角色关系树
	 * 
	 * @param appList
	 * @param roleList
	 * @return
	 */
	public static TreePanelNode buildAppRoleTree(List<VoApp> appList, List<VoRoleGroup> moduleList, List<VoRole> roleList) {
		return buildAppRoleTree(appList, moduleList, roleList, null);
	}
	public static TreePanelNode buildAppRoleTree(List<VoApp> appList, List<VoRoleGroup> moduleList, List<VoRole> roleList,List<String> selRoleIdList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_APP_ID);
		rootNode.setText(DEFAULT_ROOT_APP_NAME);
		rootNode.setOrgOrUser(NODE_TYPE_ROOT);

		rootNode.setNodeType(NODE_TYPE_ROOT);
		rootNode.setEntityId(DEFAULT_ROOT_APP_ID);
		rootNode.setEntityName(DEFAULT_ROOT_APP_NAME);

		Map<String, List<VoRoleGroup>> appModulesMap = getAppModulesMap(moduleList);
		Map<String, List<VoRole>> moduleRolesMap = getModuleRolesMap(roleList);
		Map<String, List<VoRole>> appRolesMap = getAppRolesMap(roleList, true);

		// 构造应用
		List<TreePanelNode> tmpChildAppList = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			
			TreePanelNode tmpAppNode = null;
			List<TreePanelNode> tmpModuleList = null;
			TreePanelNode tmpModule = null;
			List<TreePanelNode> modules2 = null;
			
			for (VoApp app : appList) {
				tmpAppNode = new TreePanelNode();
				tmpAppNode.setId(app.getAppId());
				tmpAppNode.setText(app.getAppChnName());
				tmpAppNode.setOrgOrUser(NODE_TYPE_APP);
				tmpAppNode.setNodeType(NODE_TYPE_APP);

				tmpAppNode.setEntityId(app.getAppId());
				tmpAppNode.setEntityCd(app.getAppCd());
				tmpAppNode.setEntityBizCd(app.getAppBizCd());
				tmpAppNode.setEntityName(app.getAppChnName());

				// 模块
				tmpModuleList = new ArrayList<TreePanelNode>();
				modules2 = getChildrenModuleNode(tmpAppNode, appModulesMap, moduleRolesMap, selRoleIdList);
				// 忽略没有关联角色的模块
				for (int i = 0; i < modules2.size(); i++) {
					tmpModule = modules2.get(i);
					if (tmpModule.getChildren().size() == 0) {
						continue;
					}
					tmpModuleList.add(tmpModule);
				}
				// 角色
				tmpModuleList.addAll(getChildrenAppRoleNode(tmpAppNode, appRolesMap, true, selRoleIdList));

				tmpAppNode.setChildren(tmpModuleList);
				tmpChildAppList.add(tmpAppNode);
			}
		}
		rootNode.setChildren(tmpChildAppList);

		//add by huangbijin 2012-02-02
		if(selRoleIdList!= null){
			refreshNodeStatus(rootNode);
		}
		
		return rootNode;
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
	 
	public static TreePanelNode buildAppRoleTree(List<VoApp> appList, List<VoRole> roleList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_APP_ID);
		rootNode.setText(DEFAULT_ROOT_APP_NAME);
		rootNode.setOrgOrUser(NODE_TYPE_APP);
		
		rootNode.setEntityId(DEFAULT_ROOT_APP_ID);
		rootNode.setNodeType(NODE_TYPE_APP);
		 
		Map<String, List<VoRole>> appRolesMap = getAppRolesMap(roleList);
		
		// 构造应用
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			TreePanelNode appNode = null;
			for (VoApp app : appList) {
				appNode = new TreePanelNode();
				appNode.setId(app.getAppId());
				appNode.setText(app.getAppChnName());
				appNode.setOrgOrUser(NODE_TYPE_APP);
				appNode.setNodeType(NODE_TYPE_APP);
				appNode.setEntityId(app.getAppId());
				appNode.setEntityCd(app.getAppCd());
				appNode.setEntityName(app.getAppChnName());
				// 角色
				appNode.setChildren(getChildrenAppRoleNode(appNode, appRolesMap));
				children.add(appNode);
			}
		}
		rootNode.setChildren(children);
		return rootNode;
	}
	public static List<TreePanelNode> getChildrenAppRoleNode(TreePanelNode treeNode,
			Map<String, List<VoRole>> appRolesMap, boolean bModule,List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getEntityId();// 很重要

		List<VoRole> roles = appRolesMap.get(parentId);// 根据appCD获取
		if (roles != null && roles.size() > 0) {
			
			TreePanelNode roleNode = null;
			for (VoRole role : roles) {

				if (bModule && role.getRoleGroupId() != null) {
					continue;
				}
				roleNode = new TreePanelNode();
				roleNode.setId(role.getRoleId());
				roleNode.setText(role.getRoleName());
				roleNode.setOrgOrUser(NODE_TYPE_ROLE);
				roleNode.setNodeType(NODE_TYPE_ROLE);
				roleNode.setEntityId(role.getRoleId());
				roleNode.setEntityCd(role.getRoleCd());
				roleNode.setEntityName(role.getRoleName());
				if(roleIdList!= null){
					if(roleIdList.contains(role.getRoleId())){
						roleNode.setChecked(NODE_CHECKED_CHECKED);
					}else{
						roleNode.setChecked(NODE_CHECKED_NONE);
					}
				}
				children.add(roleNode);
			}
		}
		return children;
	}

	public static List<TreePanelNode> getChildrenAppRoleNode(TreePanelNode treeNode,
			Map<String, List<VoRole>> appRolesMap) {
		return getChildrenAppRoleNode(treeNode, appRolesMap, false, null);
	}

	public static List<TreePanelNode> getChildrenModuleRoleNode(TreePanelNode module,
			Map<String, List<VoRole>> moduleRolesMap, List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = module.getEntityId();// 很重要

		List<VoRole> roles = moduleRolesMap.get(parentId);// 根据appCD获取
		if (roles != null && roles.size() > 0) {
			TreePanelNode tmpRoleNode = null;
			for (VoRole role : roles) {
				tmpRoleNode = new TreePanelNode();
				tmpRoleNode.setId(role.getRoleId());
				tmpRoleNode.setText(role.getRoleName());
				tmpRoleNode.setOrgOrUser(NODE_TYPE_ROLE);
				tmpRoleNode.setNodeType(NODE_TYPE_ROLE);
				tmpRoleNode.setEntityId(role.getRoleId());
				tmpRoleNode.setEntityCd(role.getRoleCd());
				tmpRoleNode.setEntityName(role.getRoleName());
				
				if(roleIdList!= null){
					if(roleIdList.contains(role.getRoleId())){
						tmpRoleNode.setChecked(NODE_CHECKED_CHECKED);
					}else{
						tmpRoleNode.setChecked(NODE_CHECKED_NONE);
					}
				}
				
				children.add(tmpRoleNode);
			}
		}
		return children;
	}

	/**
	 * 
	 * @param roleList
	 * @param bModule
	 *            是否包含角色模块
	 * @return
	 */
	public static Map<String, List<VoRole>> getAppRolesMap(List<VoRole> roleList) {
		return getAppRolesMap(roleList, false);
	}
	/**
	 * @param roleList
	 * @param bModule 是否包含模块
	 * @return
	 */
	public static Map<String, List<VoRole>> getAppRolesMap(List<VoRole> roleList,boolean bModule) {

		Map<String, List<VoRole>> appRolesMap = new HashMap<String, List<VoRole>>();
		String tmpAppId = null;
		String tmpGroupId = null;
		for (VoRole role : roleList) {
			tmpAppId = role.getAppId();
			tmpGroupId = role.getRoleGroupId(); 
			if (StringUtils.isNotBlank(tmpAppId)) {
				if (appRolesMap.containsKey(tmpAppId)) {
					if(bModule && tmpGroupId!= null){
						continue;
					}
					appRolesMap.get(tmpAppId).add(role);
				} else {
					List<VoRole> newRolesList = new ArrayList<VoRole>();
					newRolesList.add(role);
					appRolesMap.put(tmpAppId, newRolesList);
				}
			}
		}
		return appRolesMap;
	}

	/**
	 * 页面与功能关系树
	 * 
	 * @param pageList
	 * @param funcList
	 * @return
	 */
	public static TreePanelNode buildPageFunctionTree(List<AppPage> pageList, List<AppFunction> funcList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_PAGE_ID);
		rootNode.setText(DEFAULT_ROOT_PAGE_NAME);
		
		rootNode.setNodeType(NODE_TYPE_PAGE);
		rootNode.setEntityId(DEFAULT_ROOT_PAGE_ID);
		rootNode.setEntityCd(DEFAULT_ROOT_PAGE_CD);
		rootNode.setEntityName(DEFAULT_ROOT_PAGE_NAME);
		 
		Map<String, List<AppFunction>> pageFuncsMap = getPageFunctionMap(funcList);
		
		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (pageList != null && pageList.size() > 0) {
			TreePanelNode tmpPageNode = null;
			for (AppPage page : pageList) {
				tmpPageNode = new TreePanelNode();
				tmpPageNode.setId(page.getAppPageId());// app_xxx
				tmpPageNode.setText(page.getPageName());
				tmpPageNode.setNodeType(NODE_TYPE_PAGE);//
				tmpPageNode.setEntityId(page.getAppPageId());
				// 功能点
				tmpPageNode.setChildren(getChildrenFuncNode(tmpPageNode, pageFuncsMap));
				children.add(tmpPageNode);
			}
		}
		rootNode.setChildren(children);
		
		return rootNode;
	}

	private static List<TreePanelNode> getChildrenFuncNode(TreePanelNode pageNode,
			Map<String, List<AppFunction>> pageFuncsMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = pageNode.getEntityId();

		List<AppFunction> funcs = pageFuncsMap.get(parentId);
		if (funcs != null && funcs.size() > 0) {
			TreePanelNode tmpfuncNode = null;
			for (AppFunction func : funcs) {
				tmpfuncNode = new TreePanelNode();
				tmpfuncNode.setId(func.getAppFunctionId());
				tmpfuncNode.setText(func.getFunctionName());
				tmpfuncNode.setNodeType(NODE_TYPE_FUNCTION);
				tmpfuncNode.setEntityId(func.getAppFunctionId());
				children.add(tmpfuncNode);
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


	public static TreePanelNode buildModuleMenuTree(List<AppModule> moduleList, List<AppMenu> relList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_MODULE_ID);
		rootNode.setText(DEFAULT_ROOT_MODULE_NAME);
		
		rootNode.setNodeType(NODE_TYPE_PAGE);
		rootNode.setEntityId(DEFAULT_ROOT_MODULE_ID);
		rootNode.setEntityCd(DEFAULT_ROOT_MODULE_CD);
		rootNode.setEntityName(DEFAULT_ROOT_MODULE_NAME);

		Map<String, List<AppMenu>> moduleRelMap = getModuleMenuMap(relList);

		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (moduleList != null && moduleList.size() > 0) {
			TreePanelNode tmpPageNode = null;
			for (AppModule module : moduleList) {
				tmpPageNode = new TreePanelNode();
				tmpPageNode.setId(module.getAppModuleId());
				tmpPageNode.setText(module.getModuleName());
				tmpPageNode.setNodeType(NODE_TYPE_MODULE);//
				tmpPageNode.setEntityId(module.getAppModuleId());
				// 功能点
				tmpPageNode.setChildren(getChildrenRelNode(tmpPageNode, moduleRelMap));
				children.add(tmpPageNode);
			}
		}
		rootNode.setChildren(children);

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenRelNode(TreePanelNode pageNode,
			Map<String, List<AppMenu>> moduleRelMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = pageNode.getEntityId();// 很重要

		List<AppMenu> rels = moduleRelMap.get(parentId);// 
		if (rels != null && rels.size() > 0) {
			TreePanelNode tmpFuncNode = null;
			for (AppMenu rel : rels) {
				tmpFuncNode = new TreePanelNode();
				tmpFuncNode.setId(rel.getAppMenuId());// 关系ID
				tmpFuncNode.setText(rel.getMenuName());
				
				tmpFuncNode.setNodeType(NODE_TYPE_MENU);
				tmpFuncNode.setEntityId(rel.getAppMenuId());
				tmpFuncNode.setEntityName(rel.getMenuName());
				
				children.add(tmpFuncNode);
			}
		}
		return children;
	}

	private static Map<String, List<AppMenu>> getModuleMenuMap(List<AppMenu> relList) {
		Map<String, List<AppMenu>> moduleRelMap = new HashMap<String, List<AppMenu>>();
		for (AppMenu rel : relList) {
			AppModule module = rel.getAppModule();
			if (module == null) {
				continue;
			}
			String appModuleId = module.getAppModuleId();
			if (StringUtils.isNotBlank(appModuleId)) {
				if (moduleRelMap.containsKey(appModuleId)) {
					moduleRelMap.get(appModuleId).add(rel);
				} else {
					List<AppMenu> newRelList = new ArrayList<AppMenu>();
					newRelList.add(rel);
					moduleRelMap.put(appModuleId, newRelList);
				}
			}
		}
		return moduleRelMap;
	}


	// 构造机构
	public static TreePanelNode getTreePanelOrgNoChild(VoOrg org) {
		return getTreePanelOrgNoChild(org, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelOrgNoChild(VoOrg org, String checked) {
		
		TreePanelNode orgNode = new TreePanelNode();
		
		orgNode.setId(org.getOrgId());
		orgNode.setText(org.getOrgName());
		
		orgNode.setEntityId(org.getOrgId());
		orgNode.setEntityCd(org.getOrgCd());
		orgNode.setEntityBizCd(org.getOrgBizCd());
		orgNode.setEntityName(org.getOrgName());
		
		orgNode.setOrgOrUser(NODE_TYPE_ORG);
		orgNode.setNodeType(NODE_TYPE_ORG);
		orgNode.setChecked(checked);
		
		//为了同步cormail, activeBl,sequenceNo
		orgNode.setExtParam(getBVal(org.getActiveBl())+","+ getSequenceNo(org.getSequenceNo())+","+ getSequenceNo(org.getDispOrderNo()));
		
		//设置不可见 2012-05-31 陈景晶
		orgNode.setV((org.getVisableFlg() == null || org.getVisableFlg().booleanValue())?"1":"0");	
		
		return orgNode;
	}
	 
	private static String getBVal(Boolean val){
		if(val == null)
			return "";
		else
			return val.booleanValue()?"1":"0";
	}
	
	private static String getSequenceNo(Long val){
		if(val == null)
			return "10";
		else
			return String.valueOf(val);
	}

	// 构造人员
	public static TreePanelNode getTreePanelUserNoChild(VoUser user) {
		return getTreePanelUserNoChild(user, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelUserNoChild(VoUser user, String checked) {

		TreePanelNode node = new TreePanelNode();
		node.setId(user.getUserId());
		
		String uiidDes = StringUtils.isNotBlank(user.getUiid())?("("+user.getUiid()+")"):"(未开通)";
		node.setText(user.getUserName() + uiidDes + (StringUtils.isNotBlank(user.getPositionNames())?user.getPositionNames():""));
		node.setSexCd(user.getSexCd());
		node.setExtParam(user.getUiid());
		
		node.setEntityStatusCd(user.getServiceCd());
		node.setEntityId(user.getUserId());
		node.setEntityCd(user.getUserCd());
		node.setEntityBizCd(user.getUserBizCd());
		node.setEntityName(user.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_USER);
		node.setNodeType(NODE_TYPE_USER);
		node.setChecked(checked);
		
		//---=========hy  start 新增分机号与职位
			// mobile|||固定电话|||手机1,手机2|||职位
			StringBuffer tmp = new StringBuffer();
			tmp.append(user.getPhone())
			   .append("|||")
			   .append(user.getMobilePhone());
			   if(StringUtils.isNotBlank(user.getMobilePhone2())){
				   tmp.append(",")
				   		.append(user.getMobilePhone2());
			   }
			   tmp.append("|||")
			   		.append(user.getWorkDutyDesc());
			  node.setMobile(tmp.toString());
		//---==========hy end
		return node;
	}

	/**
	 * 生成指定类型(treePanelType)的机构权限树
	 * 
	 * @param nodeType
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelOrgLogical(String uiid) {
		return getTreeNodePanel(TREE_TYPE_LOGICAL, uiid, false);
	}

	public static TreePanelNode getTreeNodePanelOrgPhysical(String uiid) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, false);
	}

	// 分公司或其他业务界面显示的机构用户树
	public static TreePanelNode getTreeNodePanelUserLogical(String uiid) {
		return getTreeNodePanel(TREE_TYPE_LOGICAL, uiid, true);
	}

	public static TreePanelNode getTreeNodePanelUserPhysical(String uiid) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true);
	}

	// plas界面显示的用户树

	//是否职位
	public static TreePanelNode getTreeNodePanelUserLogicalAll(String uiid,boolean isPosFlg) {
		return getTreeNodePanel(TREE_TYPE_LOGICAL, uiid, true, true, null, false, isPosFlg);
	}

	public static TreePanelNode getTreeNodePanelUserPhysicalAll(String uiid,boolean isPosFlg) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true, true, null, false,isPosFlg);
	}
	public static TreePanelNode getTreeNodePanelUserPhysicalAll(String uiid,boolean isPosFlg,boolean bAllFlg) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true, bAllFlg, null, false,isPosFlg);
	}

	//单选
	public static TreePanelNode getTreeNodePanelUserLogicalAll(String uiid) {
		return getTreeNodePanel(TREE_TYPE_LOGICAL, uiid, true, true, null, false);
	}

	public static TreePanelNode getTreeNodePanelUserPhysicalAll(String uiid) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true, true, null, false);
	}

	//复选
	public static TreePanelNode getTreeNodePanelOrgPhysicalAllM(String uiid) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true, true, null, true);
	}
	//复选
	public static TreePanelNode getTreeNodePanelUserLogicalAllM(String uiid) {
		return getTreeNodePanel(TREE_TYPE_LOGICAL, uiid, true, true, null, true);
	}
	
	public static TreePanelNode getTreeNodePanelUserPhysicalAllM(String uiid) {
		return getTreeNodePanel(TREE_TYPE_PHYSICAL, uiid, true, true, null, true);
	}

	/**
	 * 取当期那用户权限下的机构人员
	 * 
	 * @param treeType
	 *            类型: 逻辑/物理
	 * @param uiid
	 *            用户ID
	 * @param userFlag
	 *            是否含用户
	 * @param bEnableAll是否所有用户
	 * @return
	 */
	
	public static TreePanelNode getTreeNodePanel(String treeType, String uiid, boolean bIncludeUser) {
		return getTreeNodePanel(treeType, uiid, bIncludeUser, false,null,false);
	} 

	// 提供机构管理员在维护"机构和用户列表"时使用
	/**
	 * @param treeTypeCd
	 * @param uiid
	 * @param bIncludeUser  是否含用户
	 * @param bEnableAll 是否全部用户
	 * @param checkList
	 * @param check
	 * @return
	 */

	public static TreePanelNode getTreeNodePanel(
			String treeTypeCd, 
			String uiid, 
			boolean bIncludeUser, 
			boolean bEnableAll,
			List<VoUser> checkList,
			boolean check) {
		return getTreeNodePanel(
				treeTypeCd, 
				uiid, 
				bIncludeUser, 
				bEnableAll,
				checkList,
				check,
				false);
	}
	
	
	/**
	 * 所辖范围内的空
	 * @param uiid
	 * @param acctId
	 * @param orgId
	 * @return
	 */
	public static TreePanelNode getEmptyPosTreePanel(String uiid, String acctId, String orgId){

		PlasSysPositionManager plasSysPositionManager = SpringContextHolder.getBean("plasSysPositionManager");
		PlasDimeOrgRelManager plasDimeOrgRelManager = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		

		List<String> checkedIdList = new ArrayList<String>();
		//所辖范围的职位列表
		List<VoSysPosition> voPosList = plasSysPositionManager.searchPositionList(null, false, true, orgId);
		//已有的职位列表
		
		List<VoSysPosition> selPosList = plasSysPositionManager.searchPositionListByAcctId(acctId);
		if(selPosList!= null){
			for (VoSysPosition pos :selPosList) {
				checkedIdList.add(pos.getPlasSysPositionId());
			}
			voPosList.addAll(selPosList);
		}
		

		List<PlasRole> roles = plasRoleManager.getUserRoles(uiid);
		// 1.全局角色
		boolean isGlobalRole = RoleUtil.isGlobalRole(roles);
		
		// 2.是否"机构管理员"(A_ADMIN_UAAP_ORG)
		boolean isAdminPlasOrg = RoleUtil.isAdminPlasOrg(roles);

		//构造空职位的机构树
		TreePanelNode rootNode = getRootTreeNodeOrg();
		List<VoOrg> orgList = null;
		if (isGlobalRole) {
			orgList = plasDimeOrgRelManager.getVoOrgList(TREE_TYPE_PHYSICAL,true);//所有机构
			buildOrgPosTree(rootNode, orgList, voPosList, checkedIdList, NODE_CHECKED_NONE,false);
		}
		else if (isAdminPlasOrg){
			orgList = plasDimeOrgRelManager.getVoOrgList(TREE_TYPE_PHYSICAL,false);//有效机构
			List<VoOrg> authOrgs =  plasOrgManager.getVoOrgList(TREE_TYPE_PHYSICAL, SpringSecurityUtils.getCurUiid(),false);
			TreePanelNode node = null;
			for (int i = 0; i < authOrgs.size(); i++) {
				node = getTreePanelOrgNoChild(authOrgs.get(i));
				buildOrgPosTree(node, orgList, voPosList, checkedIdList, NODE_CHECKED_NONE,false);
				rootNode.getChildren().add(node);
			}
		}
		
		//删除未挂职位的机构
		refreshNodeCheckedNoType(rootNode, NODE_TYPE_SYSP);
		return rootNode;
	}
	public static TreePanelNode getTreeNodePanel(
			String treeTypeCd, 
			String uiid, 
			boolean bIncludeUser, 
			boolean bEnableAll,
			List<VoUser> checkList,
			boolean check,
			boolean isPosFlg) {


		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasUserManager plasUserManager = SpringContextHolder.getBean("plasUserManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
		
		List<PlasRole> roles = plasRoleManager.getUserRoles(uiid);

		// 1.全局角色
		boolean isGlobalRole = RoleUtil.isGlobalRole(roles);
		
		// 2.是否"机构管理员"(A_ADMIN_UAAP_ORG)
		boolean isAdminPlasOrg = RoleUtil.isAdminPlasOrg(roles);

		TreePanelNode rootNode = getRootTreeNodeOrg();
		List<VoOrg> orgList = null;
		
		List<VoUser> userList = new ArrayList<VoUser>();
		
		if (isGlobalRole) {
			if (bIncludeUser) {
				if (bEnableAll) {
					userList = plasUserManager.getVoUserListEnable(isPosFlg);
				} else {
					userList = plasUserManager.getVoUserListAll(isPosFlg);
				}
			}
			orgList = plasDimeOrgRel.getVoOrgList(treeTypeCd,true);//所有机构
			if (DictContants.TREE_DIME_LOGICAL.equals(treeTypeCd)) {
				if(check){
					buildLogicalOrgUserTreeNoCheck(rootNode, orgList, userList);//,checkList,check);//TODO
				}else{
					buildLogicalOrgUserTree(rootNode, orgList, userList);//,checkList,check);//TODO
				}
			}
			else if (DictContants.TREE_DIME_PHYSICAL.equals(treeTypeCd)) {
				if(check){
					buildPhysicalOrgUserTreeNoCheck(rootNode, orgList, userList);//,checkList,check);//TODO
				}else{
					buildPhysicalOrgUserTree(rootNode, orgList, userList);//,checkList,check);//TODO
				}
			}
		}
		//项目公司管理员
		else if (isAdminPlasOrg) {
			if (bIncludeUser) {
				userList = plasUserManager.getVoUserList(true,false);//在职或未入职用户
			}
			orgList = plasDimeOrgRel.getVoOrgList(treeTypeCd,false);//所有有效机构,用于生成map
			
			//机构管理员管理的机构
			List<VoOrg> authOrgs =  plasOrgManager.getVoOrgList(treeTypeCd,uiid,false);
			TreePanelNode node = null;
			
			//遍历所辖机构
			for (int i = 0; i < authOrgs.size(); i++) {
				node = getTreePanelOrgNoChild(authOrgs.get(i));
				if (DictContants.TREE_DIME_LOGICAL.equals(treeTypeCd)) {
					if(check){
						buildLogicalOrgUserTreeNoCheck(rootNode, orgList, userList);
					}else{
						buildLogicalOrgUserTree(node, orgList, userList);//,checkList,check);//TODO
					}
				}
				else if (DictContants.TREE_DIME_PHYSICAL.equals(treeTypeCd)) {
					if(check){
						buildPhysicalOrgUserTreeNoCheck(rootNode, orgList, userList);
					}else{
						buildPhysicalOrgUserTree(node, orgList, userList);//,checkList,check);//TODO
					}
				}
				rootNode.getChildren().add(node);
			}
			
			
		} else {
			// 不作处理
			orgList = plasDimeOrgRel.getVoOrgList(treeTypeCd,false);
		}
		return rootNode;
	}
	
	//机构职位树
	public static TreePanelNode getPosTreeNodePanel(
			String treeTypeCd, //树类型： 1-物理 2-逻辑
			String uiid, // 当前用户uiid
			boolean bIncludePos, //是否含职位节点
			List<VoSysPosition> checkList,//选中的系统职位节点
			boolean check,//是否需要复选框
			boolean isAllPosFlg,//是否显示所有系统职位（含失效)
			boolean isLook) {//是否只显示选中
		
		List<String> chkIdList = null;
		if( checkList != null){
			chkIdList = new ArrayList<String>();
			for (VoSysPosition vo : checkList) {
				chkIdList.add(vo.getPlasSysPositionId());
			}
		}
 
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasSysPositionManager plasSysManager = SpringContextHolder.getBean("plasSysPositionManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
		
		List<PlasRole> roles = plasRoleManager.getUserRoles(uiid);

		// 1.是否"超级管理员"(A_ADMIN_SUPER/A_ADMIN)
		boolean isAdminSuper = RoleUtil.isAdminSupser(roles);
		boolean isAdmin = RoleUtil.isAdmin(roles);
		
		// 2.是否"机构管理员"(A_ADMIN_UAAP_ORG)
		boolean isAdminPlasOrg = RoleUtil.isAdminPlasOrg(roles);

		List<VoOrg> orgList = new ArrayList<VoOrg>();
		List<VoSysPosition> posList = new ArrayList<VoSysPosition>();
		
		TreePanelNode rootNode = getRootTreeNodeOrg();
		posList = plasSysManager.searchPositionListByFlg(isAllPosFlg);
		//管理员
		if (isAdminSuper || isAdmin) {
			orgList = plasDimeOrgRel.getVoOrgList(treeTypeCd,true);//所有机构
			if(isLook){//查看职位
				buildOrgPosTree(rootNode, orgList, checkList, chkIdList, check? NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED,true);
			}else{
				buildOrgPosTree(rootNode, orgList, posList, chkIdList, check? NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED,false);
			}
		} 
		//项目公司管理员
		else if (isAdminPlasOrg) {
			orgList = plasDimeOrgRel.getVoOrgList(treeTypeCd,false);//有效机构
			List<VoOrg> authOrgs =  plasOrgManager.getVoOrgList(treeTypeCd,uiid,false);
			TreePanelNode node = null;
			for (int i = 0; i < authOrgs.size(); i++) {
				node = getTreePanelOrgNoChild(authOrgs.get(i));
				if(isLook){//查看职位
					buildOrgPosTree(node, orgList, checkList, chkIdList, check? NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED,true);
				}else{
					buildOrgPosTree(node, orgList, posList, chkIdList, check? NODE_CHECKED_NONE:NODE_CHECKED_UNDEFINED,false);
				}
				rootNode.getChildren().add(node);
			}
		} else {
			// 不作处理
		}
		return rootNode;
	
	}
	/**
	 * 根据checkedMap设置默认选中或不选中(例角色批量授权人员)
	 * 
	 * @param orgList
	 * @param userList
	 * @param checkedMap
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgUserTreeCheck(List<VoOrg> orgList, List<VoUser> userList, Map<String, VoUser> checkedMap) {
		return buildPhysicalOrgUserTreeCheck(TREE_TYPE_PHYSICAL, getRootTreeNodeOrg(), orgList, userList, checkedMap);
	}

	public static TreePanelNode buildPhysicalOrgUserTreeCheck(String treeType, TreePanelNode rootNode, List<VoOrg> orgList,
			List<VoUser> userList, Map<String, VoUser> checkedMap) {

		Map<String, List<VoUser>> orgUsersMap = getOrgUsersMap(treeType, userList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		rootNode.setChildren(getChildrenNodeOnCheck(treeType, rootNode, orgOrgsMap, orgUsersMap, checkedMap));
		refreshNodeChecked(rootNode);
		return rootNode;
	}

	public static TreePanelNode buildLigicalOrgUserTreeCheck(List<VoOrg> orgList, List<VoUser> userList, Map<String, VoUser> checkedMap) {
		return buildPhysicalOrgUserTreeCheck(TREE_TYPE_LOGICAL, getRootTreeNodeOrg(), orgList, userList, checkedMap);
	}

	public static TreePanelNode buildLogicalOrgUserTreeCheck(String treeType, TreePanelNode rootNode, List<VoOrg> orgList, List<VoUser> userList,
			Map<String, VoUser> checkedMap) {

		Map<String, List<VoUser>> orgUsersMap = getOrgUsersMap(treeType, userList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		rootNode.setChildren(getChildrenNodeOnCheck(treeType, rootNode, orgOrgsMap, orgUsersMap, checkedMap));
		refreshNodeChecked(rootNode);
		return rootNode;
	}

	private static List<TreePanelNode> getChildrenNodeOnCheck(String treeType, TreePanelNode treeNode, Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoUser>> orgUsersMap, Map<String, VoUser> checkedMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<VoUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {

			for (VoUser user : users) {
				TreePanelNode userNode = getTreePanelUserNoChild(user, NODE_CHECKED_NONE);
				String checked = checkedMap.keySet().contains(user.getUserId()) ? NODE_CHECKED_CHECKED : NODE_CHECKED_NONE;
				userNode.setChecked(checked);
				children.add(userNode);

			}
		}

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (VoOrg org : orgs) {
				TreePanelNode orgNode = getTreePanelOrgNoChild(org, NODE_CHECKED_NONE);
				orgNode.setChildren(getChildrenNodeOnCheck(treeType, orgNode, orgOrgsMap, orgUsersMap, checkedMap));
				refreshNodeChecked(orgNode);
				children.add(orgNode);
			}
		}

		return children;
	}


	// 获取节点的状态
	public static void refreshNodeChecked(TreePanelNode node) {

		List<TreePanelNode> children = node.getChildren();
		int childSize = children.size();
		int checkedSize = 0;
		int halfCheckedSize = 0;

		for (TreePanelNode tmpNode : children) {
			if (NODE_CHECKED_CHECKED.equals(tmpNode.getChecked())) {
				checkedSize++;
			}
			if (NODE_CHECKED_HALF.equals(tmpNode.getChecked())) {
				halfCheckedSize++;
			}
		}
		String strCheck = NODE_CHECKED_UNDEFINED;
		if (childSize == checkedSize) {
			strCheck = NODE_CHECKED_CHECKED;
		} else if (checkedSize == 0 && halfCheckedSize == 0) {
			strCheck = NODE_CHECKED_NONE;
		} else if (halfCheckedSize > 0 || checkedSize > 0) {
			strCheck = NODE_CHECKED_HALF;
		} else {
			strCheck = NODE_CHECKED_NONE;
		}

		// 若机构无子节点,则不可用
		if (childSize == 0) {
			strCheck = NODE_CHECKED_UNDEFINED;
		}

		node.setChecked(strCheck);
	}
	
	/**
	 * 移除不含子孙类型为指定类型的节点
	 * @param parentNode
	 * @param containNodeTypeCd
	 * @return
	 */
	public static boolean refreshNodeCheckedNoType(TreePanelNode parentNode,String containNodeTypeCd) {

		List<TreePanelNode> children = parentNode.getChildren();
		int childSize = children.size();
		boolean containFlg = false;
		
		TreePanelNode tmpSon = null;
		for(int i=(childSize-1); i >= 0; i--){
			tmpSon = children.get(i);
			
			if(containNodeTypeCd.equals(tmpSon.getNodeType())) {
				containFlg = true;
			} else{
				if(!refreshNodeCheckedNoType(tmpSon, containNodeTypeCd)){
//					System.out.println(">>>>>remove["+i+"]: " + tmpSon.getEntityName() );
					children.remove(i);
				} else {
					containFlg = true;
				}
			}
		}
		return containFlg;
	}
	//构造"机构"根节点
	public static TreePanelNode getRootTreeNodeOrg(String nodeName) {

		String tName = StringUtils.isBlank(nodeName)?DEFAULT_ROOT_ORG_NAME:nodeName;
		TreePanelNode node = new TreePanelNode();
		node.setId(DEFAULT_ROOT_ORG_ID);
		node.setText(tName);
		
		node.setEntityId(DEFAULT_ROOT_ORG_ID);
		node.setEntityCd(DEFAULT_ROOT_ORG_CD);
		node.setEntityName(tName);
		
		node.setOrgOrUser(NODE_TYPE_ROOT);
		node.setNodeType(NODE_TYPE_ROOT);
		node.setChecked(NODE_CHECKED_UNDEFINED);
		
		return node;
	}
	public static TreePanelNode getRootTreeNodeOrg() {
		return getRootTreeNodeOrg(null);
	}

	//构造"应用"根节点
	public static TreePanelNode getRootTreeNodeApp() {
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

	public static TreePanelNode buildAppModuleTree(List<VoApp> appList,
			List<VoRoleGroup> moduleList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("应用与角色模块关系树");
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setOrgOrUser(NODE_TYPE_APP);

		Map<String, List<VoRoleGroup>> appModulesMap = getAppModulesMap(moduleList);
		Map<String, List<VoRole>> moduleRolesMap = new HashMap<String, List<VoRole>>();

		// 构造应用
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			for (VoApp app : appList) {
				TreePanelNode appNode = new TreePanelNode();
				appNode.setId("app_" + app.getAppCd());
				appNode.setText(app.getAppChnName());
				appNode.setOrgOrUser(NODE_TYPE_APP);
				appNode.setNodeType(NODE_TYPE_APP);
				appNode.setEntityId(app.getAppId());
				appNode.setEntityCd(app.getAppCd());
				appNode.setEntityName(app.getAppChnName());
				appNode.setChildren(getChildrenModuleNode(appNode, appModulesMap, moduleRolesMap));
				// 模块
				children.add(appNode);
			}
		}
		rootNode.setChildren(children);

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenModuleNode(TreePanelNode node,Map<String, List<VoRoleGroup>> appModulesMap,Map<String,List<VoRole>> moduleRolesMap) {
		return getChildrenModuleNode(node, appModulesMap, moduleRolesMap, null);
	}
	private static List<TreePanelNode> getChildrenModuleNode(TreePanelNode node,Map<String, List<VoRoleGroup>> appModulesMap,Map<String,List<VoRole>> moduleRolesMap, List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = node.getEntityId();// 很重要

		List<VoRoleGroup> modules = appModulesMap.get(parentId);
		if (modules != null && modules.size() > 0) {
			TreePanelNode tmpModuleNode = null;
			for (VoRoleGroup tmpGroup : modules) {
				tmpModuleNode = new TreePanelNode();
				tmpModuleNode.setId(tmpGroup.getGroupId());
				tmpModuleNode.setText(tmpGroup.getGroupName());
				tmpModuleNode.setOrgOrUser(NODE_TYPE_ROLE_GROUP);
				tmpModuleNode.setNodeType(NODE_TYPE_ROLE_GROUP);
				
				tmpModuleNode.setEntityId(tmpGroup.getGroupId());
				tmpModuleNode.setEntityCd(tmpGroup.getGroupCd());
				tmpModuleNode.setEntityBizCd(tmpGroup.getGroupBizCd());
				tmpModuleNode.setEntityName(tmpGroup.getGroupName());
				tmpModuleNode.setChildren(getChildrenModuleRoleNode(tmpModuleNode, moduleRolesMap, roleIdList));
				
				if(roleIdList!= null){ 
					int checkedCount = 0;
					for (TreePanelNode roleNode : tmpModuleNode.getChildren()) {
						if(NODE_CHECKED_CHECKED.equals(roleNode.getChecked())){
							checkedCount ++;
							continue;
						}
					}
					if(checkedCount == 0){
						tmpModuleNode.setChecked(NODE_CHECKED_NONE);
					}else{
						if(checkedCount == tmpModuleNode.getChildren().size()){
							tmpModuleNode.setChecked(NODE_CHECKED_CHECKED);
						}else{
							tmpModuleNode.setChecked(NODE_CHECKED_HALF);
						}
					}
				}
				children.add(tmpModuleNode);
			}
		}
		return children;
	}

	private static Map<String, List<VoRoleGroup>> getAppModulesMap(List<VoRoleGroup> groupList) {
		Map<String, List<VoRoleGroup>> appModulesMap = new HashMap<String, List<VoRoleGroup>>();
		String tmpAppId = null;
		for (VoRoleGroup group : groupList) { 
			tmpAppId = group.getAppId();
			if (StringUtils.isNotBlank(tmpAppId)) {
				if (appModulesMap.containsKey(tmpAppId)) {
					appModulesMap.get(tmpAppId).add(group);
				} else {
					List<VoRoleGroup> newRolesList = new ArrayList<VoRoleGroup>();
					newRolesList.add(group);
					appModulesMap.put(tmpAppId, newRolesList);
				}
			}
		}
		return appModulesMap;
	}

	private static Map<String, List<VoRole>> getModuleRolesMap(List<VoRole> roleList) {
		Map<String, List<VoRole>> appModulesMap = new HashMap<String, List<VoRole>>();
		String groupId = null;
		for (VoRole role : roleList) {
			groupId = role.getRoleGroupId();
			if (StringUtils.isNotBlank(groupId)) {
				if (appModulesMap.containsKey(groupId)) {
					appModulesMap.get(groupId).add(role);
				} else {
					List<VoRole> newRolesList = new ArrayList<VoRole>();
					newRolesList.add(role);
					appModulesMap.put(groupId, newRolesList);
				}
			}
		}
		return appModulesMap;
	}

 
	/**
	 * 机构与账户树 
	 * @param orgList
	 * @param acctList
	 * @param checked
	 * @return
	 */
	public static TreePanelNode buildOrgAcctTreeNoChecked(String treeType,List<VoOrg> orgList,List<VoAcct> acctList){
		return buildOrgAcctTree(treeType, orgList, acctList, NODE_CHECKED_UNDEFINED);
	}
	public static TreePanelNode buildOrgAcctTree(String treeType,List<VoOrg> orgList,List<VoAcct> acctList,String checked){

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DEFAULT_ROOT_ORG_ID);
		rootNode.setText(DEFAULT_ROOT_ORG_NAME);
		
		rootNode.setEntityId(DEFAULT_ROOT_ORG_ID);
		rootNode.setEntityCd(DEFAULT_ROOT_ORG_CD);
		rootNode.setEntityName(DEFAULT_ROOT_ORG_NAME);
		
		rootNode.setNodeType(NODE_TYPE_ORG);
		
		Map<String,List<VoOrg>> orgsMap = getOrgOrgsMap(treeType, orgList);
		Map<String,List<VoAcct>> acctsMap = getOrgAcctsMap(treeType, acctList);
		
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode2(treeType, rootNode, orgsMap, acctsMap, checked));

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenNode2(String treeType, TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoAcct>> orgAccts,
			String checked) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先账号
		List<VoAcct> accts = orgAccts.get(parentId);// 根据机构CD获取
		if (accts != null && accts.size() > 0) {
			TreePanelNode acctNode = null;
			for (VoAcct acct : accts) {
				acctNode = getTreePanelAcctNoChild(acct,checked);
				acctNode.setChecked(checked);
				children.add(acctNode);
			}
		}

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			TreePanelNode orgNode  = null;
			for (VoOrg org : orgs) {
				orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode2(treeType, orgNode, orgOrgsMap, orgAccts, checked));
				children.add(orgNode);
			}
		}
		return children;
	}

	public static TreePanelNode getTreePanelAcctNoChild(VoAcct acct, String checked) {

		TreePanelNode node = new TreePanelNode();
		node.setId(acct.getAcctId());
		node.setText(acct.getUserName());
		node.setExtParam(acct.getUiid());
		
		node.setEntityId(acct.getAcctId());
		node.setEntityName(acct.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_ACCT);
		node.setNodeType(NODE_TYPE_ACCT);
		node.setChecked(checked);
		
		node.setSexCd(acct.getSexCd());
		node.setEntityStatusCd(acct.getStatusCd());
		return node;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////

	//机构职位树
	public static TreePanelNode buildOrgPosTree(
			List<VoOrg> orgList,
			List<VoSysPosition> posList, List<String> checkedPosIdList) {
		TreePanelNode rootNode = getRootTreeNodeOrg();
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(orgList);
		Map<String, List<VoSysPosition>> orgPossMap = getOrgPosMap(posList);
		rootNode.setChildren(getChildrenNode(rootNode, orgOrgsMap, orgPossMap, checkedPosIdList, NODE_CHECKED_NONE));
		return rootNode;
	}
	public static TreePanelNode buildOrgPosTreeNoCheck(
			List<VoOrg> orgList,
			List<VoSysPosition> posList, List<String> checkedPosIdList) {
		TreePanelNode rootNode = getRootTreeNodeOrg();
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(orgList);
		Map<String, List<VoSysPosition>> orgPossMap = getOrgPosMap(posList);
		rootNode.setChildren(getChildrenNode(rootNode, orgOrgsMap, orgPossMap, null,NODE_CHECKED_UNDEFINED));
		return rootNode;
	}
	public static TreePanelNode buildOrgPosTree(TreePanelNode rootNode,
			List<VoOrg> orgList,
			List<VoSysPosition> posList, List<String> checkedPosIdList,
			String checked,boolean isLook) {

		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(orgList);
		Map<String, List<VoSysPosition>> orgPossMap = getOrgPosMap(posList);
		rootNode.setChildren(getChildrenNode(rootNode, orgOrgsMap, orgPossMap, checkedPosIdList,checked));
		
		if(isLook){//如果是“查看职位”则移除没有选中的
			refreshNodeCheckedNoType(rootNode, NODE_TYPE_SYSP);
		}
		
		return rootNode;
	}
	// 遍历人员,设置机构与职位关系
	public static Map<String, List<VoSysPosition>> getOrgPosMap(List<VoSysPosition> posList) {

		Map<String, List<VoSysPosition>> orgPosMap = new HashMap<String, List<VoSysPosition>>();
		String parentOrgId = "";
		List<VoSysPosition> newPosList = null;
		for (VoSysPosition pos : posList) {
			
			parentOrgId = pos.getOrgId();
			
			if(StringUtils.isBlank(parentOrgId)){
				parentOrgId = DEFAULT_ROOT_ORG_ID;
			}

			if (StringUtils.isNotBlank(parentOrgId)) {
				if (orgPosMap.containsKey(parentOrgId)) {
					orgPosMap.get(parentOrgId).add(pos);
				} else {
					newPosList = new ArrayList<VoSysPosition>();
					newPosList.add(pos);
					orgPosMap.put(parentOrgId, newPosList);
				}
			}
		}
		return orgPosMap;
	}
	private static List<TreePanelNode> getChildrenNode(
			TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoSysPosition>> orgPosMap,
			List<String> checkedPosIdList,
			String checked) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先职位
		List<VoSysPosition> posList = orgPosMap.get(parentId);
		TreePanelNode posNode = null;
		if (posList != null && posList.size() > 0) {
			for (VoSysPosition pos : posList) {
				posNode = getTreePanelPosNoChild(pos);
				if(checkedPosIdList!= null){
					if(checkedPosIdList.contains(pos.getPlasSysPositionId())){
						posNode.setChecked(NODE_CHECKED_CHECKED);
					}else{
						posNode.setChecked(NODE_CHECKED_NONE);
					}
				}else{
					posNode.setChecked(NODE_CHECKED_UNDEFINED);
				}
				children.add(posNode);
			}
		}

		// 后机构
		TreePanelNode orgNode = null;
		List<VoOrg> orgs = orgOrgsMap.get(parentId);
		if (orgs != null && orgs.size() > 0) {
			for (VoOrg org : orgs) {
				orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode(orgNode, orgOrgsMap, orgPosMap, checkedPosIdList,checked));
				if(NODE_CHECKED_UNDEFINED!=checked){
					refreshNodeChecked(orgNode);
				}
				children.add(orgNode);
			}
		}
		return children;
	}
	// 构造职位
	public static TreePanelNode getTreePanelPosNoChild(VoSysPosition pos) {
		return getTreePanelPosNoChild(pos, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelPosNoChild(VoSysPosition pos, String checked) {

		TreePanelNode node = new TreePanelNode();
		node.setId(pos.getPlasSysPositionId());
		
//		node.setText("["+pos.getUiid() +"," +pos.getUserName() +"]"+ pos.getSysPosName());
		
		String tmp = null;
		String ext = null;
		if(StringUtils.isBlank(pos.getUiid())){
			tmp = "(空缺)";
		}else{
			tmp = "("+pos.getUiid() +"," +pos.getUserName()+")";
		}
		if(pos.getBeyondFlg()!= null && pos.getBeyondFlg().booleanValue()){
			ext = "超编 ";
		}
		if(pos.getTmpPosFlg()!= null && pos.getTmpPosFlg().booleanValue()){
			ext = StringUtils.isBlank(ext)?"临时 ":(ext+"临时 ");
		}
		
		//兼职也算编制，所以这里屏蔽代码  hidden by huangbijin 2012-02-10
//		if(pos.getOutStatFlg()!= null && pos.getOutStatFlg().booleanValue()){
//			ext = StringUtils.isBlank(ext)?"兼职 ":(ext+"兼职");
//		}
		
		node.setText0(ext);//超编 + 临时
		node.setText(pos.getSysPosName() + tmp);
		node.setExtParam(pos.getAcctStatusCd());//账号状态
		node.setEntityStatusCd(pos.getActiveBl()==null?"0":(pos.getActiveBl().booleanValue()?"1":"0"));//是否有效
		node.setEntityId(pos.getPlasSysPositionId());
		node.setEntityCd(pos.getSysPosCd());
		node.setEntityName(pos.getShortName());
		node.setNodeType(NODE_TYPE_SYSP);
		node.setChecked(checked);
		
		//add by huangbijin 2011-12-21
		//是否超编 1-是 0-否
		node.setMobile(pos.getBeyondFlg()==null?"0":(pos.getBeyondFlg().booleanValue()?"1":"0"));
		//是否临时 1-是 0-否
		node.setOrgOrUser(pos.getTmpPosFlg()==null?"0":(pos.getTmpPosFlg().booleanValue()?"1":"0"));
		//是否不含统计 1-是 0-否
		node.setC1(pos.getOutStatFlg()==null?"0":(pos.getOutStatFlg().booleanValue()?"1":"0"));
		node.setSexCd(pos.getSexCd());
		
		//add by huangbijin 2012-03-31,若空，默认显示。
		node.setV(pos.getVisableFlg()==null?"1":(pos.getVisableFlg().booleanValue()?"1":"0"));
		
		return node;
	}
	

	/**
	 * 机构树
	 * @param uiid
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelHrOrg(TreePanelNode rootNode, String uiid) {
		return getTreeNodePanelHr(rootNode, uiid, false, false);
	}
	
	
	/**
	 * 机构人员树
	 * @param uiid
	 * @param isPosFlg
	 * @param userFlg 是否含用户
	 * 
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelHr(TreePanelNode rootNode, String uiid, boolean isPosFlg){
		return getTreeNodePanelHr(rootNode, uiid, isPosFlg, true);
	}

	public static TreePanelNode getTreeNodePanelHr(TreePanelNode rootNode, String uiid, boolean isPosFlg, boolean userFlg) {
		
		
		List<WsPlasRole> roleList = getRoleMgr().getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);

		List<VoOrg> orgList = getDimeOrgRelMgr().getVoOrgList(TREE_TYPE_PHYSICAL,true);//所有机构
		List<VoUser> userList = new ArrayList<VoUser>();
		if(userFlg){
			userList = getUserMgr().getVoUserListEnable(isPosFlg);
		}
		//总管理员/总管理员/人事管理员
		if(RoleUtil.isHrGroup(roleList) || RoleUtil.isHrBizCenter(roleList) ||RoleUtil.isHrAdmin(roleList)
				||RoleUtil.isAdmin()||RoleUtil.isAdminSupser()){
//			buildPhysicalOrgUserTree(rootNode, orgList, userList);
			buildHrTree(rootNode, orgList, userList);
		}else{
			
			boolean dcFlg = false;
			//南区人事(区域)
			//北区人事(区域)
			if(RoleUtil.isHrDcQy(roleList)){
				List<String> list = new ArrayList<String>();
				List<String> sonList = new ArrayList<String>();
				
				list.add(RoleUtil.ORG_DC_GDCGS);
				if(RoleUtil.isHrNq(roleList)){
					list.add(RoleUtil.ORG_DC_NQ);
					sonList.addAll(getProjectCds(RoleUtil.AREA_ORG_SOUTH));
				}
				if(RoleUtil.isHrBq(roleList)){
					list.add(RoleUtil.ORG_DC_BQ);
					sonList.addAll(getProjectCds(RoleUtil.AREA_ORG_NORTH));
				}
				getHrTree(rootNode, list, orgList, userList, RoleUtil.ORG_DC_GDCGS,sonList);
				dcFlg = true;
			}

			//上海城市公司,管理3个机构
			boolean ShcsFlg = false;
			//查询授权管理的机构
			List<String> authOrgIdList = new ArrayList<String>();
			List<VoOrg> authOrgs =  getOrgMgr().getVoOrgList(TREE_TYPE_PHYSICAL, SpringSecurityUtils.getCurUiid(),false);
			for (VoOrg voOrg : authOrgs) {
//				System.out.println(">>>>>>>>>>>>>> " + voOrg.getOrgCd() + ", " + voOrg.getOrgName());
				authOrgIdList.add(voOrg.getOrgId());
				if(RoleUtil.ORG_DC_SHCS.equals(voOrg.getOrgCd())){
					ShcsFlg = true;
				}
			}
			
			if(!dcFlg){
				//上海城市公司
				if(ShcsFlg){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_DC_NQ, orgList, userList);
				}
				//地产公司人事(项目)
				if(RoleUtil.isHrDcgs(roleList)){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_DC_GDCGS, orgList, userList);
				}
			}
			
			//宝龙华康
			if(RoleUtil.isHrBlhk(roleList)){
				getHrTree(rootNode, RoleUtil.ORG_BLHK, orgList, userList);
			}
			
			//商业人事(区域)
			if(RoleUtil.isHrSy(roleList)){
				getHrTree(rootNode, RoleUtil.ORG_SY, orgList, userList);
			}else{
				//商业公司人事(项目)
				if(RoleUtil.isHrSygs(roleList)){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_SY_GSYGS, orgList, userList);
				}
				//各百货公司
				if(RoleUtil.isHrBhgs(roleList)){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_SY_GBHGS, orgList, userList);
				}
				//各KTV公司
				if(RoleUtil.isHrKtvgs(roleList)){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_SY_GKTVGS, orgList, userList);
				}
			}
			//事业人事(区域)
			if(RoleUtil.isHrYS(roleList)){
				getHrTree(rootNode, RoleUtil.ORG_YS, orgList, userList);
			}else{
				//事业公司人事(项目)
				if(RoleUtil.isHrYsgs(roleList)){
					getNode(rootNode, authOrgIdList, RoleUtil.ORG_YS, orgList, userList);
				}
				//行业人事(区域)
//				if(RoleUtil.isHrDw(roleList)){
//					getHrTree(rootNode, RoleUtil.ORG_HY_DWGLZX, orgList, userList);
//				}else{
					//行业公司人事(项目)
					if(RoleUtil.isHrDwgs(roleList)){
						getNode(rootNode, authOrgIdList, RoleUtil.ORG_HY_GDWMD, orgList, userList);
					}
//				}
			}
			//酒店人事(区域)
			if(RoleUtil.isHrJd(roleList)){
				getHrTree(rootNode, RoleUtil.ORG_DC_JD, orgList, userList);
			}else{
			}
			//酒店公司人事(项目)
			if(RoleUtil.isHrJdgs(roleList)){
				getNode(rootNode, authOrgIdList, RoleUtil.ORG_DC_JD_GJDGS, orgList, userList);
			}
		}
		
		
		return rootNode;
	} 

	/**
	 * 根据当前用户所在deptId,获取对应的中心下属的项目公司 
	 * @param rootNode 节点
	 * @param deptId 当前用户所在deptId
	 * @param relOrgCd 上级机构
	 * @param orgList 所有机构列表
	 * @param userList 所有员工列表
	 */
//	private static void getNode(TreePanelNode rootNode, String deptId, String relOrgCd,List<VoOrg> orgList, List<VoUser> userList){
//		List<String> deptIdList = new ArrayList<String>();
//		deptIdList.add(deptId);
//		getNode(rootNode, deptIdList, relOrgCd, orgList, userList);
//	}
	private static void getNode(TreePanelNode rootNode, List<String> deptIdList, String relOrgCd,List<VoOrg> orgList, List<VoUser> userList){
		
		if(StringUtils.isBlank(relOrgCd))
			return;
		
		List<PlasOrg> tOrgList = null;
		for (String tDeptId : deptIdList) {

			String preOrgCd = null;
			boolean findFlg = false;
			
			//跟当前节点比对
			PlasOrg tt = getOrgMgr().getPlasOrgById(tDeptId);
			tOrgList = getOrgMgr().getBubbleOrgsByOrgId(tDeptId, TREE_TYPE_PHYSICAL);
			if(tt != null){
//				System.out.println("======== " +tt.getOrgCd() + "," + tt.getOrgName() + " <=>" + relOrgCd );
				if(tt.getOrgCd().equals(relOrgCd)){
					findFlg = true;
					if(tOrgList == null || tOrgList.size() == 0){
						preOrgCd = "0";
					}else{
						preOrgCd = ((PlasOrg)tOrgList.get(0)).getOrgCd();
					}
				}
			}
			
			if(!findFlg){
				for (PlasOrg tOrg : tOrgList) {
//				System.out.println(tOrg.getOrgName());
					if(relOrgCd.equals(tOrg.getOrgCd())){
						findFlg = true;
						break;
					}
					preOrgCd = tOrg.getOrgCd();
				}
			}
			if(findFlg){
				//项目公司
				if(StringUtils.isNotBlank(preOrgCd)){
					if(tOrgList.contains(RoleUtil.ORG_DC_SHCS)){
						//上海城市公司的，可以管理3个机构
						List<String> orgCdList = new ArrayList<String>();
						orgCdList.add(preOrgCd);
						orgCdList.add(RoleUtil.ORG_DC_SHHX);
						orgCdList.add(RoleUtil.ORG_DC_SHXL);
						orgCdList.add(RoleUtil.ORG_DC_SHGFL);
						getHrTree(rootNode,orgCdList, orgList, userList);
					}else{
						getHrTree(rootNode, preOrgCd, orgList, userList); 
					}
				}
			}
		}
	}
	
 
	private static PlasDimeOrgRelManager getDimeOrgRelMgr(){
		return SpringContextHolder.getBean("plasDimeOrgRelManager");
	}
	private static PlasUserManager getUserMgr(){
		return SpringContextHolder.getBean("plasUserManager");
	}
	private static PlasSysPositionManager getSysPosMgr(){
		return SpringContextHolder.getBean("plasSysPositionManager");
	}
	private static PlasRoleManager getRoleMgr(){
		return SpringContextHolder.getBean("plasRoleManager");
	}
	private static PlasOrgManager getOrgMgr(){
		return SpringContextHolder.getBean("plasOrgManager");
	}

	private static AppDictTypeManager getDictTypeMgr(){
		return SpringContextHolder.getBean("appDictTypeManager");
	}

	private static void getHrTree(TreePanelNode rootNode,String orgCd, List<VoOrg> orgList, List<VoUser> userList){
		List<String> list = new ArrayList<String>();
		list.add(orgCd);
		getHrTree(rootNode, list, orgList, userList, null, null);
	}
	private static void getHrTree(TreePanelNode rootNode,List<String> orgCdList, List<VoOrg> orgList, List<VoUser> userList){
		getHrTree(rootNode, orgCdList, orgList, userList, null, null);
	}
	private static void getHrTree(TreePanelNode rootNode,List<String> orgCds, List<VoOrg> orgList, List<VoUser> userList,String parentCd, List<String> sonList){
		List<VoOrg> authOrgs = getOrgMgr().getVoOrg(TREE_TYPE_PHYSICAL, orgCds);
		getTreePanel(rootNode, authOrgs, orgList, userList, parentCd, sonList);
	}
	private static void getTreePanel(TreePanelNode rootNode, List<VoOrg> authOrgs, List<VoOrg> orgList, List<VoUser> userList, String parentCd, List<String> sonCdList){
		TreePanelNode node = null;
		Map<String, List<VoUser>> orgUsersMap = getOrgUsersMap(TREE_TYPE_PHYSICAL, userList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(TREE_TYPE_PHYSICAL, orgList);
		
		if(StringUtils.isNotBlank(parentCd)){
			PlasOrg parentOrg = getOrgMgr().getPlasOrgByCd(parentCd);
			if(parentOrg!= null){
				if(orgOrgsMap!= null && orgOrgsMap.containsKey(parentOrg.getPlasOrgId())){
					List<VoOrg> newList = new ArrayList<VoOrg>();
					if(sonCdList!= null){
						for (VoOrg tVo : orgOrgsMap.get(parentOrg.getPlasOrgId())) {
							if( sonCdList.contains(tVo.getOrgCd())){
								newList.add(tVo);
							}
						}
					}
					orgOrgsMap.put(parentOrg.getPlasOrgId(),newList);
				}
			}
		}
		
		//遍历所辖机构
		if(authOrgs != null){
			for (int i = 0; i < authOrgs.size(); i++) {
				node = getTreePanelOrgNoChild(authOrgs.get(i));
				node.setChildren(getHrChildrenNode(TREE_TYPE_PHYSICAL, node, orgOrgsMap, orgUsersMap, TreePanelUtil2.NODE_CHECKED_UNDEFINED));
				rootNode.getChildren().add(node);
			}
		}
	}

	/**
	 * 获取系统职位树
	 * @param rootNode
	 * @param orgCd
	 * @param orgList
	 * @param posList
	 */
	private static void getPosTree(TreePanelNode rootNode,String orgCd, List<VoOrg> orgList, List<VoSysPosition> posList,String nodeCheck){
		List<String> list = new ArrayList<String>();
		list.add(orgCd);
		getPosTree(rootNode, list, orgList, posList, null, null, nodeCheck);
	}
	private static void getPosTree(TreePanelNode rootNode,List<String> orgCds, List<VoOrg> orgList, List<VoSysPosition> posList,String parentCd, List<String> sonList, String nodeCheck){
		List<VoOrg> authOrgs = getOrgMgr().getVoOrg(TREE_TYPE_PHYSICAL, orgCds);
		getPosTreePanel(rootNode, authOrgs, orgList, posList, parentCd, sonList, nodeCheck);
	}
	private static void getPosTreePanel(TreePanelNode rootNode, List<VoOrg> authOrgs, List<VoOrg> orgList, List<VoSysPosition> posList, String parentCd, List<String> sonCdList, String nodeCheck){
		TreePanelNode node = null;
		Map<String, List<VoSysPosition>> orgPosMap = getOrgPosMap(posList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(TREE_TYPE_PHYSICAL, orgList);
		
		if(StringUtils.isNotBlank(parentCd)){
			PlasOrg parentOrg = getOrgMgr().getPlasOrgByCd(parentCd);
			if(parentOrg!= null){
				if(orgOrgsMap!= null && orgOrgsMap.containsKey(parentOrg.getPlasOrgId())){
					List<VoOrg> newList = new ArrayList<VoOrg>();
					for (VoOrg tVo : orgOrgsMap.get(parentOrg.getPlasOrgId())) {
						if( sonCdList.contains(tVo.getOrgCd())){
							newList.add(tVo);
						}
					}
					orgOrgsMap.put(parentOrg.getPlasOrgId(),newList);
				}
			}
		}
		
		//遍历所辖机构
		for (int i = 0; i < authOrgs.size(); i++) {
			node = getTreePanelOrgNoChild(authOrgs.get(i));
			node.setChildren(getChildrenNode(node, orgOrgsMap, orgPosMap, (NODE_CHECKED_UNDEFINED.equals(nodeCheck)?null:new ArrayList<String>()), nodeCheck)); //必须null
			rootNode.getChildren().add(node);
		}
	}
	
	/**
	 * 获取区域对应的项目
	 * @param appDictTypeCd
	 * @return
	 */
	public static List<String> getProjectCds(String appDictTypeCd){
		List<WsAppDictData> datas = getDictTypeMgr().getWsAllData(appDictTypeCd);
		List<String> list = new ArrayList<String>();
		for(int i=0; i<datas.size();i++){
			list.add(datas.get(i).getDictCd());
		}
		return list;
	}
	
	//南区
	public static boolean isSouth(List<String> bobList){
		return isArea(bobList, RoleUtil.AREA_ORG_SOUTH);
	}
	//北区
	public static boolean isNorth(List<String> bobList){
		return isArea(bobList, RoleUtil.AREA_ORG_NORTH);
	}
	//判断区域
	private static boolean isArea(List<String> bobList, String typeCd){
		if(bobList == null) 
			return false;
		List<String> areaCds = TreePanelUtil2.getProjectCds(typeCd);
		for (String tCd : bobList) {
			if(StringUtils.isNotBlank(tCd) && areaCds.contains(tCd))
				return true;
		}
		return false;
	}
	
	public static boolean isContainNode(String nodeId, TreePanelNode rootNode){
		if(rootNode == null||StringUtils.isBlank(nodeId))
			return false;
		if(nodeId.equals(rootNode.getId()))
			return true;
		
		for (TreePanelNode sonNode : rootNode.getChildren()) {
			if(isContainNode(nodeId, sonNode))
				return true;
		}
		return false;
	}

	/**
	 * 空职位树
	 * @param uiid
	 * @param acctId  需要限制该账号占有的职位时，传入.
	 * @param orgId
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelHrPosEmpty(String uiid, String acctId, String orgId) {
		List<WsPlasRole> roleList = getRoleMgr().getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);
		PlasUser user = getUserMgr().getPlasUserByUiid(uiid);

		List<VoOrg> orgList = getDimeOrgRelMgr().getVoOrgList(TREE_TYPE_PHYSICAL,true);//所有机构
		List<VoSysPosition> voPosList = getSysPosMgr().searchPositionList(null, false, true, orgId);

		List<String> checkedIdList = new ArrayList<String>();
		List<VoSysPosition> selPosList = getSysPosMgr().searchPositionListByAcctId(acctId);
		if(selPosList!= null){
			for (VoSysPosition pos :selPosList) {
				checkedIdList.add(pos.getPlasSysPositionId());
			}
			voPosList.addAll(selPosList);
		}
		
		TreePanelNode rootNode = getPosTree(roleList, orgList, voPosList, user, TreePanelUtil2.NODE_CHECKED_NONE);
		
		//删除未挂职位的机构
		refreshNodeCheckedNoType(rootNode, NODE_TYPE_SYSP);
		
		return rootNode;
	} 

	/**
	 * 职位树
	 * @param uiid
	 * @param isPosFlg
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelHrPos(String uiid, boolean isPosFlg) {
 
		List<WsPlasRole> roleList = getRoleMgr().getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);
		PlasUser user = getUserMgr().getPlasUserByUiid(uiid);

		List<VoOrg> orgList = getDimeOrgRelMgr().getVoOrgList(TREE_TYPE_PHYSICAL,true);//所有机构
		List<VoSysPosition> posList = getSysPosMgr().searchPositionListByFlg(isPosFlg);
		return getPosTree(roleList, orgList, posList, user, TreePanelUtil2.NODE_CHECKED_UNDEFINED);
	} 
	private static TreePanelNode getPosTree(List<WsPlasRole> roleList,List<VoOrg> orgList ,List<VoSysPosition> posList,PlasUser user, String nodeCheck){

		
		TreePanelNode rootNode = getRootTreeNodeOrg("职位树"); 
//		//总管理员/总管理员/人事管理员
		if(RoleUtil.isHrGroup(roleList) || RoleUtil.isHrBizCenter(roleList) ||RoleUtil.isHrAdmin(roleList)
				||RoleUtil.isAdmin()||RoleUtil.isAdminSupser()){
			buildOrgPosTree(rootNode, orgList, posList, (NODE_CHECKED_UNDEFINED.equals(nodeCheck)?null:new ArrayList<String>()) ,nodeCheck,false);//必须null
		}else{

			boolean dcFlg = false;
			//南区人事(区域)
			//北区人事(区域)
			if(RoleUtil.isHrDcQy(roleList)){
				List<String> list = new ArrayList<String>();
				List<String> sonList = new ArrayList<String>();
				
				list.add(RoleUtil.ORG_DC_GDCGS);
				if(RoleUtil.isHrNq(roleList)){
					list.add(RoleUtil.ORG_DC_NQ);
					sonList.addAll(getProjectCds(RoleUtil.AREA_ORG_SOUTH));
				}
				if(RoleUtil.isHrBq(roleList)){
					list.add(RoleUtil.ORG_DC_BQ);
					sonList.addAll(getProjectCds(RoleUtil.AREA_ORG_NORTH));
				}
				getPosTree(rootNode, list, orgList, posList, RoleUtil.ORG_DC_GDCGS, sonList, nodeCheck);
				dcFlg = true;
			}
			

			//上海城市公司,管理3个机构
			boolean ShcsFlg = false;
			//查询授权管理的机构
			List<String> authOrgIdList = new ArrayList<String>();
			List<VoOrg> authOrgs =  getOrgMgr().getVoOrgList(TREE_TYPE_PHYSICAL, SpringSecurityUtils.getCurUiid(),false);
			for (VoOrg voOrg : authOrgs) {
//				System.out.println(">>>>>>>>>>>>>> " + voOrg.getOrgCd() + ", " + voOrg.getOrgName());
				authOrgIdList.add(voOrg.getOrgId());
				if(RoleUtil.ORG_DC_SHCS.equals(voOrg.getOrgCd())){
					ShcsFlg = true;
				}
			}
			
			if(!dcFlg){
				//上海城市公司
				if(ShcsFlg){
					getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_DC_NQ, orgList, posList, nodeCheck);
				}
				//地产公司人事(项目)
				if(RoleUtil.isHrDcgs(roleList)){
					getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_DC_GDCGS, orgList, posList, nodeCheck);
				}
			}
			
			//宝龙华康
			if(RoleUtil.isHrBlhk(roleList)){
				getPosTree(rootNode, RoleUtil.ORG_BLHK, orgList, posList, nodeCheck);
			}
			
			//商业人事(区域)
			if(RoleUtil.isHrSy(roleList)){
				getPosTree(rootNode, RoleUtil.ORG_SY, orgList, posList, nodeCheck);
			}else{
				//商业公司人事(项目)
				if(RoleUtil.isHrSygs(roleList)){
					getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_SY_GSYGS, orgList, posList, nodeCheck);
				}
				//各百货公司
				if(RoleUtil.isHrBhgs(roleList)){
					getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_SY_GBHGS, orgList, posList, nodeCheck);
				}
			}
			//各KTV公司
			if(RoleUtil.isHrKtvgs(roleList)){
				getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_SY_GKTVGS, orgList, posList, nodeCheck);
			}
	 
			//事业人事(区域)
			if(RoleUtil.isHrYS(roleList)){
				getPosTree(rootNode, RoleUtil.ORG_YS, orgList, posList, nodeCheck);
			}else{
				//事业公司人事(项目)
				if(RoleUtil.isHrYsgs(roleList)){
					getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_YS, orgList, posList, nodeCheck);
				}
				//行业人事(区域)
//				if(RoleUtil.isHrDw(roleList)){
//					getPosTree(rootNode, RoleUtil.ORG_HY_DWGLZX, orgList, posList, nodeCheck);
//				}else{
					//电玩(项目)
					if(RoleUtil.isHrDwgs(roleList)){
						getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_HY_GDWMD, orgList, posList, nodeCheck);
					}
//				}
			}
			//酒店人事(区域)
			if(RoleUtil.isHrJd(roleList)){
				getPosTree(rootNode, RoleUtil.ORG_DC_JD, orgList, posList, nodeCheck);
			}else{
			}
			//酒店公司人事(项目)
			if(RoleUtil.isHrJdgs(roleList)){
				getPosTree(rootNode, authOrgIdList, RoleUtil.ORG_DC_JD_GJDGS, orgList, posList, nodeCheck);
			}
		}
		return rootNode;
	}

//	private static void getPosTsree(TreePanelNode rootNode, String deptId, String relOrgCd, List<VoOrg> orgList, List<VoSysPosition> posList,String nodeCheck){
//		List<String> deptIdList = new ArrayList<String>();
//		deptIdList.add(deptId);
//		getPosTree(rootNode, deptIdList, relOrgCd, orgList, posList, nodeCheck);
//	}
	private static void getPosTree(TreePanelNode rootNode, List<String> deptIdList, String relOrgCd, List<VoOrg> orgList, List<VoSysPosition> posList,String nodeCheck){
		

		if(StringUtils.isBlank(relOrgCd))
			return;
		
		
		List<PlasOrg> tOrgList = null;
		for (String tDeptId : deptIdList) {

			String preOrgCd = null;
			boolean findFlg = false;
			
			//跟当前节点比对
			PlasOrg tt = getOrgMgr().getPlasOrgById(tDeptId);
			tOrgList = getOrgMgr().getBubbleOrgsByOrgId(tDeptId, TREE_TYPE_PHYSICAL);
			if(tt != null){
//				System.out.println("======== " +tt.getOrgCd() + "," + tt.getOrgName() + " <=>" + relOrgCd );
				if(tt.getOrgCd().equals(relOrgCd)){
					findFlg = true;
					if(tOrgList == null || tOrgList.size() == 0){
						preOrgCd = "0";
					}else{
						preOrgCd = ((PlasOrg)tOrgList.get(0)).getOrgCd();
					}
				}
			}
			
			if(!findFlg){
				for (PlasOrg tOrg : tOrgList) {
//					System.out.println(tOrg.getOrgName());
					if(relOrgCd.equals(tOrg.getOrgCd())){
						findFlg = true;
						break;
					}
					preOrgCd = tOrg.getOrgCd();
				}
			}
			if(findFlg){
				//项目公司
				if(StringUtils.isNotBlank(preOrgCd)){
					if(tOrgList.contains(RoleUtil.ORG_DC_SHCS)){
						//上海城市公司的，可以管理3个机构
						List<String> orgCdList = new ArrayList<String>();
						orgCdList.add(preOrgCd);
						orgCdList.add(RoleUtil.ORG_DC_SHHX);
						orgCdList.add(RoleUtil.ORG_DC_SHXL);
						orgCdList.add(RoleUtil.ORG_DC_SHGFL);
						getPosTree(rootNode, preOrgCd, orgList, posList,nodeCheck); 
					}else{
						getPosTree(rootNode, preOrgCd, orgList, posList,nodeCheck); 
					}
				}
			}
		}
//		
//		if(StringUtils.isBlank(relOrgCd))
//			return;
//		
//		for (String tDeptId : deptIdList) {
//			//获取地产公司
//			List<PlasOrg> tOrgList = getOrgMgr().getBubbleOrgsByOrgId(tDeptId, TREE_TYPE_PHYSICAL);
//			String preOrgCd = null;
//			for (PlasOrg tOrg : tOrgList) {
////				System.out.println(tOrg.getOrgName());
//				if(relOrgCd.equals(tOrg.getOrgCd())){
//					break;
//				}
//				preOrgCd = tOrg.getOrgCd();
//			}
//			//项目公司
//			if(StringUtils.isNotBlank(preOrgCd)){
//				getPosTree(rootNode, preOrgCd, orgList, posList,nodeCheck); 
//			}
//		}
	}
	
	

	/**
	 * @param bubleCdList 从小到大排列
	 * @param relOrgCd
	 * @return
	 */
	private static String getPreBubleOrgCd(List<String> bubleCdList,String relOrgCd){
		String preOrgCd = null;
		boolean findFlg = false;
		for (String tOrgCd : bubleCdList) {
//			System.out.println("被操作的操作员所在机构(小一级区域): >>>>>>>>>>> " + tOrgCd);
			if(relOrgCd.equals(tOrgCd)){
				findFlg = true;
				break;
			}
			preOrgCd = tOrgCd;
		}
		if(findFlg)
			return preOrgCd;
//		System.out.println("发起操作的操作员所在中心: 没找到!");
		return null;
	}

	/**
	 * 获取中心机构CD
	 * @param orgId
	 * @return
	 */
	public static Map<String,String> getCornerOrgCd(String orgId){

		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isBlank(orgId))
			return map;
		
		List<String> bubleList = new ArrayList<String>();
		Map<String,String> mapOrg = new HashMap<String,String>();
		mapOrg.put("0", "集团");
		List<PlasOrg> list = getOrgMgr().getBubbleOrgsPhysical(orgId);
		if(list != null){
			for (PlasOrg tOrg: list) {
				bubleList.add(tOrg.getOrgCd());
				mapOrg.put(tOrg.getOrgCd(),tOrg.getOrgName());
			}
		}

		String corderOrgCd = null;
		String roleCd = null;
		String orgName = null;
		String owerOrgCd = null;
		String findFlg = null;
		
		//北区
		if(bubleList.contains(RoleUtil.ORG_DC_BQ) || TreePanelUtil2.isNorth(bubleList)) {
			corderOrgCd = RoleUtil.ORG_DC_BQ;
			roleCd = GlobalConstants.A_HR_BQ;
			//北区机构
			owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_DC_GDCGS);
			if(StringUtils.isBlank(owerOrgCd)){
				owerOrgCd = RoleUtil.ORG_DC_BQ;
			}else{
				findFlg = "level3";
			}
		} 
		//南区
		else if(bubleList.contains(RoleUtil.ORG_DC_NQ) || TreePanelUtil2.isSouth(bubleList)) {
			corderOrgCd = RoleUtil.ORG_DC_NQ;
			roleCd = GlobalConstants.A_HR_NQ;
			//南区机构
			owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_DC_GDCGS);
			if(StringUtils.isBlank(owerOrgCd)){
				owerOrgCd = RoleUtil.ORG_DC_NQ;
			}else{
				findFlg = "level3";
			}
		} 
		//商业
		else if(bubleList.contains(RoleUtil.ORG_SY)) {
			corderOrgCd = RoleUtil.ORG_SY;
			roleCd = GlobalConstants.A_HR_SY;

			if(bubleList.contains(RoleUtil.ORG_SY_GBHGS)){
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_SY_GBHGS);
			}
			else if(bubleList.contains(RoleUtil.ORG_SY_GKTVGS)){
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_SY_GKTVGS);
			}
			else if(bubleList.contains(RoleUtil.ORG_DC_GDCGS)){
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_DC_GDCGS);
			}
			if(StringUtils.isBlank(owerOrgCd)){
				owerOrgCd = RoleUtil.ORG_SY;
			}else{
				findFlg = "level3";
			}
//		} else if(bubleList.contains(RoleUtil.ORG_HY)) {
//			corderOrgCd = RoleUtil.ORG_HY;
//			roleCd = GlobalConstants.A_HR_HY;
//			
//			if(bubleList.contains(RoleUtil.ORG_HY_GDWMD)){
//				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_HY_GDWMD);
//			} 
//			if(StringUtils.isBlank(owerOrgCd)){
//				owerOrgCd = RoleUtil.ORG_HY;
//			}else{
//				findFlg = "level3";
//			}
		} else if(bubleList.contains(RoleUtil.ORG_YS)) {
			corderOrgCd = RoleUtil.ORG_YS;
			roleCd = GlobalConstants.A_HR_YS;

			if(bubleList.contains(RoleUtil.ORG_HY_DWGLZX)){
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_HY_DWGLZX);
			}
			else if(bubleList.contains(RoleUtil.ORG_HY_GDWMD)){
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_HY_GDWMD);
			}else{
				owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_YS);
			}
			if(StringUtils.isBlank(owerOrgCd)){
				owerOrgCd = RoleUtil.ORG_YS;
			}else{
				findFlg = "level3";
			}
		} else if(bubleList.contains(RoleUtil.ORG_DC_JD)) {
			corderOrgCd = RoleUtil.ORG_DC_JD;
			roleCd = GlobalConstants.A_HR_JD;
			owerOrgCd = getPreBubleOrgCd(bubleList, RoleUtil.ORG_DC_JD_GJDGS);
			if(StringUtils.isBlank(owerOrgCd)){
				owerOrgCd = RoleUtil.ORG_DC_JD;
			}else{
				findFlg = "level3";
			}
		}
		if(StringUtils.isNotBlank(corderOrgCd)){
			PlasOrg org = getOrgMgr().getPlasOrgByCd(corderOrgCd);
			orgName = org.getOrgName();
		}else{
			corderOrgCd = "0";//总部
			orgName = "总部";
			roleCd = GlobalConstants.A_HR_GROUP;
			owerOrgCd = "0";
		}
		map.put("orgCd", corderOrgCd);
		map.put("orgName", orgName);
		map.put("roleCd", roleCd);
		map.put("relOrgCd", owerOrgCd);
		map.put("findFlg", findFlg);
		return map;
	}
	
	
	//系统职位（编制）:机构-系统职位树

	public static TreePanelNode buildHrTree(TreePanelNode rootNode,
			List<VoOrg> orgList, List<VoUser> userList) {
		return buildHrTree(TREE_TYPE_PHYSICAL, rootNode, orgList, userList, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode buildHrTree(String treeType, TreePanelNode rootNode,
			List<VoOrg> orgList,
			List<VoUser> userList, String checked) {

		Map<String, List<VoUser>> orgUsersMap = getOrgUsersMap(treeType, userList);
		Map<String, List<VoOrg>> orgOrgsMap = getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		rootNode.setChildren(getHrChildrenNode(treeType, rootNode, orgOrgsMap, orgUsersMap, checked));
 
		return rootNode;
	}

	private static List<TreePanelNode> getHrChildrenNode(String treeType, TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoUser>> orgUsersMap,
			String checked) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<VoUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {
			TreePanelNode userNode = null;
			for (VoUser user : users) {
				userNode = getHrNodeNoChild(user);
				userNode.setChecked(checked);
				children.add(userNode);
			}
		}

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			TreePanelNode orgNode = null;
			for (VoOrg org : orgs) {
				orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getHrChildrenNode(treeType, orgNode, orgOrgsMap, orgUsersMap, checked));
				//System.out.println(">>>>>>>>>>>>>> " + checked + " | " + orgNode.getEntityName());
				if(NODE_CHECKED_NONE.equals(checked)){//人员调动使用
					refreshNodeChecked(orgNode);
				}
				children.add(orgNode);
			}
		}
		return children;
	}
	// 构造人员
	private static TreePanelNode getHrNodeNoChild(VoUser user) {
		return getHrNodeNoChild(user, NODE_CHECKED_UNDEFINED);
	}

	private static TreePanelNode getHrNodeNoChild(VoUser user, String checked) {

		TreePanelNode node = new TreePanelNode();
		node.setId(user.getUserId());
		
		//增加职责字段 setResponsibility 至 username处：如 许健康【总裁】
		if(StringUtils.isNotBlank(user.getResponsibility())){
//			node.setText(user.getUserName()+"["+user.getResponsibility()+"]");
			node.setText(user.getWorkDutyDesc() +"(" + user.getUiid() + "," + user.getUserName() + ")" +"["+user.getResponsibility()+"]");
		}else{
			node.setText(user.getWorkDutyDesc() +"(" + user.getUiid() + "," + user.getUserName() + ")");
		}
		node.setSexCd(user.getSexCd());
		node.setExtParam(user.getUiid());
		
		node.setEntityStatusCd(user.getServiceCd());
		node.setEntityId(user.getUserId());
		node.setEntityCd(user.getUserCd());
		node.setEntityBizCd(user.getUserBizCd());
		node.setEntityName(user.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_USER);
		node.setNodeType(NODE_TYPE_USER);
		node.setChecked(checked); 
		return node;
	}
	//职位头衔(huangbj，黄碧锦): 机构-人员
	

	/**
	 * 刷新机构节点： 编制/人员
	 * 编制(正常编制)：机构下的所有职位，去除超编的编制和临时编制
	 * 人员：机构下的所有落位编制,不包含空缺编制(包括 超编的编制和临时编制)
	 * @param node
	 */
	
//	数字格式严格要求如下：（实际在岗人数/编制人数+临时在岗人数/临时岗位数）
//	比如一个中心，编制12，临时岗位4，在岗14人（编制内11人，临时3人），那就是（11/12+3/4），储备人员也是临时岗位
//	如果超编，都显示红色，比如（13/12+1/4）


	public static int[] refreshPosOrg(TreePanelNode node){

		int locatedCount = 0;//实际在岗人数
		int uniformCount = 0;//编制人数
		int tmpLocatedCount = 0;// 临时在岗人数
		int tmpCount = 0;//临时岗位数
		
		int[] tmp = null;
		TreePanelNode tNode = null;
		
		List<TreePanelNode> childList = node.getChildren();
		if(childList != null){
			//机构
			if(NODE_TYPE_ORG.equals(node.getNodeType()) || NODE_TYPE_ROOT.equals(node.getNodeType())){
				for(int i=0; i<childList.size(); i++){
					tNode = childList.get(i);

					//注意:一定要在排除前面执行
					tmp = refreshPosOrg(tNode);

					//排除"各地产公司"
					if("135".equals(tNode.getEntityCd())){
						continue;
					}
					//排除"各商业公司"
					if("512".equals(tNode.getEntityCd())){
						continue;
					}
					//排除"各百货公司"
					if("459".equals(tNode.getEntityCd())){
						continue;
					}
					//排除"各KTV公司"
					if("1035".equals(tNode.getEntityCd())){
						continue;
					}
					//排除"各电玩门店"
					if("751".equals(tNode.getEntityCd())){
						continue;
					}
					if(tmp != null){
						locatedCount += tmp[0];
						uniformCount += tmp[1];
						tmpLocatedCount += tmp[2];
						tmpCount += tmp[3];
					}
				}
				
				//根节点不显示数量
				if(!"0".equals(node.getEntityCd())){
					// 有效职位总数/正常职位数
					String tmpText = StringUtils.isBlank(node.getText())?"":(node.getText());
					
					//node.setText(tmpText + "  ("+ uniformCount +"/"+ locatedCount +")");
					//node.setTitle(tmpText + "  (编制-"+ uniformCount +"/人员-"+ locatedCount +")");
					
					StringBuffer sb = new StringBuffer();
					if(locatedCount > uniformCount ){
						node.setText (tmpText + "  (");
						node.setText1(locatedCount + "/"+ uniformCount);//红色
						if(tmpCount >0){
							sb.append(" " + tmpLocatedCount +"/"+tmpCount);
						}
						sb.append(")");
						node.setText2(sb.toString());
					}else{
						sb.append(tmpText)
							.append("  (")
							.append(locatedCount)
							.append("/")
							.append(uniformCount);
						if(tmpCount > 0){
						sb.append("  ")
							.append(tmpLocatedCount)
							.append("/")
							.append(tmpCount);
							}
						sb.append(")");
						node.setText (sb.toString());
					}
					node.setTitle(tmpText + "  (实际在岗人数"+ locatedCount +"/编制人数"+ uniformCount +"   临时在岗人数"+tmpLocatedCount+"/临时岗位数"+tmpCount+")");
				}
			}
			//<span class="TreeNode" style="" title="宝龙地产  (实际-322/编制-328)">宝龙地产  <font color='red'>(322/328)</font></span>
			//系统职位
			if(NODE_TYPE_SYSP.equals(node.getNodeType())){
				
				// 是否落位
				boolean locatedFlg = StringUtils.isNotBlank(node.getText()) && (node.getText().indexOf("(空缺)") == -1);
				// 1-超编, mobile必须有值
				boolean beyondFlg = "1".equals(node.getMobile());
				// 1-临时, orgOrUser必须有值
				boolean tmpFlg = "1".equals(node.getOrgOrUser());
				// 1-是否超编
//				boolean outStatFlg = "1".equals(node.getC1());

				// 实际在岗人数/编制人数 + 临时在岗人数/临时岗位数

				// 1.实际在岗人数
				if (locatedFlg) {
					locatedCount = 1;
				}

				// 2.编制人数
				if (beyondFlg) {
					uniformCount = 0;
				}
				else if (tmpFlg) {
					uniformCount = 0;
				} else {
					//add by huangbijin 2012-02-01 不统计不在"实际人数"内
//					if(!outStatFlg){//兼职也算编制，所以这里屏蔽代码  hidden by huangbijin 2012-02-10
						uniformCount = 1;
//					}
				}
				// 3.临时在岗人数/临时岗位数
				if(tmpFlg){
					if(locatedFlg){
						tmpLocatedCount = 1;
					}
					tmpCount = 1;
				}
			}
		}
		return new int[]{locatedCount, uniformCount, tmpLocatedCount, tmpCount};
	}
	
	//不显示其他邮箱
	public static void refreshMoveOrgOtherEmailGroup(TreePanelNode node){
		List<TreePanelNode> childList = node.getChildren();
		if(childList != null){
			TreePanelNode tNode = null;
			for(int i=0; i<childList.size(); i++){
				tNode = childList.get(i);
				if("1".equals(tNode.getId())){
					childList.remove(tNode);
					break;
				}
			}
		}
	}
	 
	/**
	 * @param node
	 * @param exceptAdminFlg true-管理员可以看到全部  false-管理员也看不到全部
	 */
	public static void refreshVisable(TreePanelNode node, boolean exceptAdminFlg){
		
		if((exceptAdminFlg) && RoleUtil.isAdmin()){
			//若管理员,则不处理!
		}else{
			List<TreePanelNode> childList = node.getChildren();
			if(childList != null){
				TreePanelNode tNode = null;
				for (Iterator<TreePanelNode> iterator = childList.iterator(); iterator.hasNext();) {
					tNode =  iterator.next();
					if("0".equals(tNode.getV())){//0-不显示
						iterator.remove();
					}else{
						refreshVisable(tNode, exceptAdminFlg);
					}
				}
			}
		}
	}
}
