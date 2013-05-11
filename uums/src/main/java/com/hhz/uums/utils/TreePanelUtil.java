package com.hhz.uums.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppFunctionManager;
import com.hhz.uums.dao.app.AppMenuManager;
import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOrgCheckRelManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.entity.app.AppPage;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.vo.vw.OrgTreeVo;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.UserTreeVo;
import com.hhz.uums.vo.vw.VoAcct;
import com.hhz.uums.vo.vw.VoOrg;

/**
 * @author waveflat
 * 
 */
public class TreePanelUtil {

	// 节点状态 : 9-根节点 1-机构 0-用户
	public static String NODE_TYPE_ROOT = "9";
	public static String NODE_TYPE_ORG = "1";
	public static String NODE_TYPE_USER = "0";
	public static String NODE_TYPE_ACCT = "0";
	public static String NODE_TYPE_SYSP = "3";
	public static String NODE_TYPE_ROLE_MODULE = "2";
	public static String NODE_TYPE_APP = "1";
	public static String NODE_TYPE_ROLE = "0";
	public static String NODE_TYPE_ROLE_GROUP = "1";

	// 选择状态 : 0-不选中 1-选中 2-半选
	public static String NODE_CHECKED_UNDEFINED = "undefined";
	public static String NODE_CHECKED_NONE = "0";
	public static String NODE_CHECKED_CHECKED = "1";
	public static String NODE_CHECKED_HALF = "2";

	//复选框
	public static boolean CHECKED = true;
	public static boolean UN_CHECKED = false;


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

	//根节点
	public static  TreePanelNode getRootNode(){
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(DictContants.ORG_ROOT_CD);
		rootNode.setText(GlobalConstants.UAAP_ORG_NAME);
		rootNode.setEntityId(DictContants.ORG_ROOT_CD);// 特殊处理
		rootNode.setEntityCd(DictContants.ORG_ROOT_CD);
		rootNode.setEntityName(GlobalConstants.UAAP_ORG_NAME);
		rootNode.setNodeType(NODE_TYPE_ROOT);
		return rootNode;
	}
	/**
	 * 逻辑机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode, List<OrgTreeVo> orgList) {
		return buildOrgUserTree(DictContants.TREE_DIME_LOGICAL, rootNode, orgList, new ArrayList<PlasUser>(),CHECKED,null);
	}

	/**
	 * 物理机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode, List<OrgTreeVo> orgList) {
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode, orgList, new ArrayList<PlasUser>(),CHECKED,null);
	}

	/**
	 * 公用机构树
	 * 
	 * @param rootNode
	 * @param orgList
	 * @return
	 */
	public static TreePanelNode buildOrgTree(String treeType, TreePanelNode rootNode, List<OrgTreeVo> orgList) {
		return buildOrgUserTree(treeType, rootNode, orgList, new ArrayList<PlasUser>(), CHECKED,null);
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
			List<OrgTreeVo> orgList, List<PlasUser> userList,List<String> checkUserIdList,boolean check) {
		return buildOrgUserTree(DictContants.TREE_DIME_LOGICAL, rootNode, orgList, userList, check,checkUserIdList);
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
			List<OrgTreeVo> orgList, List<PlasUser> userList,List<String> checkUserIdList,boolean check) {
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode, orgList, userList, check,checkUserIdList);
	}
	public static TreePanelNode buildPhysicalOrgUserTreeNoCheck(TreePanelNode rootNode,
			List<OrgTreeVo> orgList, List<PlasUser> userList) {
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode, orgList, userList, UN_CHECKED,null);
	} 
	public static TreePanelNode buildPhysicalOrgUserTreeUndefined(TreePanelNode rootNode,
			List<OrgTreeVo> orgList, List<PlasUser> userList) {
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode, orgList, userList, UN_CHECKED,null);
	}
	
	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode) {
		return buildLogicalOrgTree(rootNode, false);
	}
	public static TreePanelNode buildLogicalOrgTree(TreePanelNode rootNode,  boolean isOneLevel) {
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		List<VoOrg> orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_LOGICAL,false);//有效机构
		return buildOrgUserTree(DictContants.TREE_DIME_LOGICAL, rootNode,orgs, new ArrayList<PlasUser>(), NODE_CHECKED_UNDEFINED, isOneLevel);
	}
	/**
	 * 人员选择框使用
	 */
	public static TreePanelNode buildLogicalOrgTreeNoChecked(TreePanelNode rootNode) {
		return buildLogicalOrgTreeNoChecked(rootNode, false);
	}
	public static TreePanelNode buildLogicalOrgTreeNoChecked(TreePanelNode rootNode,  boolean isOneLevel) {
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		List<VoOrg> orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_LOGICAL,false);//有效机构
		return buildOrgUserTree(DictContants.TREE_DIME_LOGICAL, rootNode, orgs, new ArrayList<PlasUser>(), NODE_CHECKED_NONE, isOneLevel);
	}
	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode) {
		return buildPhysicalOrgTree(rootNode, false);
	}
	public static TreePanelNode buildPhysicalOrgTree(TreePanelNode rootNode,  boolean isOneLevel) {
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		List<VoOrg> orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,false);//有效机构
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode,orgs, new ArrayList<PlasUser>(), NODE_CHECKED_UNDEFINED, isOneLevel);
	}
	public static TreePanelNode buildPhysicalOrgTreeNoChecked(TreePanelNode rootNode) {
		return buildLogicalOrgTreeNoChecked(rootNode, false);
	}
	public static TreePanelNode buildPhysicalOrgTreeNoChecked(TreePanelNode rootNode,  boolean isOneLevel) {
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		List<VoOrg> orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,false);//有效机构
		return buildOrgUserTree(DictContants.TREE_DIME_PHYSICAL, rootNode, orgs, new ArrayList<PlasUser>(), NODE_CHECKED_NONE, isOneLevel);
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
	public static TreePanelNode buildOrgUserTree(String dimeCd, TreePanelNode rootNode, List<VoOrg> orgList, List<PlasUser> userList,
			String checked, boolean isOneLevel) {

		Map<String, List<PlasUser>> orgUsersMap = getOrgUsersMap(dimeCd,userList);
		Map<String, List<VoOrg>> orgOrgsMap = TreePanelUtil2.getOrgOrgsMap(dimeCd,orgList);// getOrgOrgsMap(treeType, orgList);
		
		buildOrgUserTree(rootNode, orgOrgsMap, orgUsersMap, checked, isOneLevel);
		return rootNode;
	}
	public static TreePanelNode buildOrgUserTree(
			TreePanelNode rootNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<PlasUser>> orgUsersMap,//可空
			String checked,
			boolean isOneNodeFlg) {
		 
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode3(rootNode, orgOrgsMap, orgUsersMap));
		return rootNode;
	}
	/**
	 * 机构与人员关系树(逻辑/物理)（无复选框）
	 * 
	 * @param treeType
	 * @param rootNode
	 * @param orgList
	 * @param checkUserIdList
	 * @return
	 */
	public static TreePanelNode buildOrgUserTree(String treeType, TreePanelNode rootNode,
			List<OrgTreeVo> orgList,
			List<PlasUser> userList, boolean checked,List<String> checkUserIdList) {

		Map<String, List<PlasUser>> orgUsersMap = getOrgUsersMap(treeType, userList);
		Map<String, List<OrgTreeVo>> orgOrgsMap = getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		if(!checked) {
			rootNode.setChildren(getChildrenNode(treeType, rootNode, orgOrgsMap, orgUsersMap));
		}else {
			rootNode.setChildren(getChildrenNode(treeType, rootNode, orgOrgsMap, orgUsersMap, checkUserIdList));
		
		}
		return rootNode;
	}
	public static TreePanelNode setOrgUserTree(TreePanelNode node,List<OrgTreeVo> orgs,Map<String,List<PlasUser>> users){

		TreePanelNode orgNode = null;
		
		List<PlasUser> tmpUsers = null;
		TreePanelNode userNode= null;
		
		for(OrgTreeVo obj : orgs){
			orgNode = getTreePanelOrgNoChild(obj);		
			if(null==obj.getParentId()||"".equals(obj.getParentId())){
				obj.setParentId(DictContants.ORG_ROOT_CD);
			}
			//插入用户
			tmpUsers = users.get(obj.getOrgId());
			if(tmpUsers!=null&&tmpUsers.size()>0){
				for(PlasUser user:tmpUsers){
					userNode = getTreePanelUserNoChild(user);
					orgNode.addChild(userNode);
				}
			}
			addTreeNode(node, orgNode, obj.getParentId(),false);
			
		}
		return node;
	}

	private static void setOrgNodeCheck(TreePanelNode orgNode,int num,int childSize,boolean check){
		if(check){
			if(childSize==0){
				orgNode.setChecked(NODE_CHECKED_NONE);
			}else {
				if(num==0){
					orgNode.setChecked(NODE_CHECKED_NONE);
				}else if(num<childSize){
					orgNode.setChecked(NODE_CHECKED_HALF);
				}else if(num==childSize){
					orgNode.setChecked(NODE_CHECKED_CHECKED);
				}
			}
		}
	}

	/**
	 *构建机构人员树(无复选框)
	 */
	private static List<TreePanelNode> getChildrenNode3( TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<PlasUser>> orgUsersMap) {
		
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();
		
		// 先人员
		List<PlasUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {
			for (PlasUser user : users) {
				TreePanelNode userNode = getTreePanelUserNoChild(user);
				children.add(userNode);
			}
		}
		
		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (VoOrg org : orgs) {
				TreePanelNode orgNode = TreePanelUtil2.getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode3( orgNode, orgOrgsMap, orgUsersMap));
				children.add(orgNode);
			}
		}
		return children;
	}
	private static List<TreePanelNode> getChildrenNode(String treeType, TreePanelNode treeNode,
			Map<String, List<OrgTreeVo>> orgOrgsMap,
			Map<String, List<PlasUser>> orgUsersMap) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<PlasUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {
			for (PlasUser user : users) {
				TreePanelNode userNode = getTreePanelUserNoChild(user);
				children.add(userNode);
			}
		}

		// 后机构
		List<OrgTreeVo> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (OrgTreeVo org : orgs) {
				TreePanelNode orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode(treeType, orgNode, orgOrgsMap, orgUsersMap));
				children.add(orgNode);
			}
		}
		return children;
	}
	/**
	 * 构建机构人员树(有复选框)
	 */
	private static List<TreePanelNode> getChildrenNode(String treeType, TreePanelNode treeNode,
			Map<String, List<OrgTreeVo>> orgOrgsMap,
			Map<String, List<PlasUser>> orgUsersMap,
			List<String> checkUserIdList) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<PlasUser> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {
			for (PlasUser user : users) {
				TreePanelNode userNode = getTreePanelUserNoChild(user);
				if(checkUserIdList.contains(user.getPlasUserId())){
					userNode.setChecked(NODE_CHECKED_CHECKED);
				}else {
					userNode.setChecked(NODE_CHECKED_NONE);
				}
				children.add(userNode);
			}
		}

		// 后机构
		List<OrgTreeVo> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (OrgTreeVo org : orgs) {
				TreePanelNode orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode(treeType, orgNode, orgOrgsMap, orgUsersMap,checkUserIdList));
				refreshNodeChecked(orgNode);
				children.add(orgNode);
			}
		}
		return children;
	}

	// 遍历人员,设置机构与人员关系
	public static Map<String, List<PlasUser>> getOrgUsersMap(String treeType, List<PlasUser> userList) {

		Map<String, List<PlasUser>> orgUsersMap = new HashMap<String, List<PlasUser>>();
		String prentOrgCd = "";
		for (PlasUser user : userList) {
			// 默认:物理
			if(null==user.getPlasOrg()){
				continue;//用户无机构
			}
			prentOrgCd = user.getPlasOrg().getPlasOrgId();
		

			if (StringUtils.isNotBlank(prentOrgCd)) {
				if (orgUsersMap.containsKey(prentOrgCd)) {
					orgUsersMap.get(prentOrgCd).add(user);
				} else {
					List<PlasUser> newUserList = new ArrayList<PlasUser>();
					newUserList.add(user);
					orgUsersMap.put(prentOrgCd, newUserList);
				}
			}
		}
		return orgUsersMap;
	}

	// 遍历机构,设置机构与机构关系
	public static Map<String, List<OrgTreeVo>> getOrgOrgsMap(String treeType, List<OrgTreeVo> orgList) {

		Map<String, List<OrgTreeVo>> orgOrgsMap = new HashMap<String, List<OrgTreeVo>>();
		String prentOrgCd = "";
		for (OrgTreeVo org : orgList) {
			prentOrgCd = org.getParentId();
			// 默认:物理
//			if (DictContants.TREE_DIME_LOGICAL.equals(treeType)) {
//				prentOrgCd = org.getParentOrgCdLogical();
//			} else {
//				prentOrgCd = org.getParentOrgCdPhysical();
//			}

			if (StringUtils.isNotBlank(prentOrgCd)) {
				if (orgOrgsMap.containsKey(prentOrgCd)) {
					orgOrgsMap.get(prentOrgCd).add(org);
				} else {
					List<OrgTreeVo> newOrgList = new ArrayList<OrgTreeVo>();
					newOrgList.add(org);
					orgOrgsMap.put(prentOrgCd, newOrgList);
				}
			}
		}
		return orgOrgsMap;
	}
	public static List<PlasApp> getApps(){
		PlasAppManager plasAppManager = SpringContextHolder.getBean("plasAppManager");
		return plasAppManager.getAllOrderedApps();
	}
	public static List<PlasRoleGroup> getRoleGroups(){
		PlasRoleGroupManager plasRoleGroupManager = SpringContextHolder.getBean("plasRoleGroupManager");
		return plasRoleGroupManager.getAllOrderedGroups();
	}
	public static List<PlasRole> getRoles(){
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		return plasRoleManager.getAllOrderedRoles();
	}
	/**
	 * 应用与角色关系树
	 * 
	 * @param appList
	 * @param roleList
	 * @return
	 */
	public static TreePanelNode createAppRoleTree() {
		return createAppRoleTree(getApps(),getRoleGroups(), getRoles(), null);
	}
	public static TreePanelNode createAppRoleTree(List<PlasApp> appList, List<PlasRoleGroup> moduleList, List<PlasRole> roleList,List<String> roleIdList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("应用与角色关系树");
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setNodeType(NODE_TYPE_ROOT);

		Map<String, List<PlasRoleGroup>> appModulesMap = getAppModulesMap(moduleList);
		Map<String, List<PlasRole>> moduleRolesMap = getModuleRolesMap(roleList);
		Map<String, List<PlasRole>> appRolesMap = getAppRolesMap(roleList, true);
		
		// 构造应用
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			for (PlasApp app : appList) {
				TreePanelNode appNode = new TreePanelNode();
				appNode.setId("app_" + app.getAppCd());
				appNode.setText(app.getAppChnName());
				appNode.setNodeType(NODE_TYPE_APP);
				appNode.setEntityId(app.getPlasAppId());
				appNode.setEntityCd(app.getAppCd());
				appNode.setEntityBizCd(app.getAppBizCd());
				appNode.setEntityName(app.getAppChnName());

				List<TreePanelNode> child = new ArrayList<TreePanelNode>();
				// 模块
				List<TreePanelNode> modules = getChildrenModuleNode(appNode, appModulesMap, moduleRolesMap, roleIdList);
				// 忽略没有关联角色的模块
				for (int i = 0; i < modules.size(); i++) {
					TreePanelNode module = modules.get(i);
					if (module.getChildren().size() == 0) {
						continue;
					}
					child.add(module);
				}
				// 角色
				child.addAll(getChildrenAppRoleNode(appNode, appRolesMap, true, roleIdList));

				appNode.setChildren(child);
				children.add(appNode);
			}
		}
		rootNode.setChildren(children);
		
		return rootNode;
	}

/*	public static TreePanelNode buildAppRoleTree(List<PlasApp> appList, List<PlasRole> roleList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("应用与角色关系树");
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setNodeType(NODE_TYPE_APP);
		 
		Map<String, List<PlasRole>> appRolesMap = getAppRolesMap(roleList);
		
		// 构造应用
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			for (PlasApp app : appList) {
				TreePanelNode appNode = new TreePanelNode();
				appNode.setId("app_" + app.getAppCd());
				appNode.setText(app.getAppChnName());
				appNode.setNodeType(NODE_TYPE_APP);
				appNode.setEntityId(app.getPlasAppId());
				appNode.setEntityCd(app.getAppCd());
				appNode.setEntityName(app.getAppChnName());
				// 角色
				appNode.setChildren(getChildrenAppRoleNode(appNode, appRolesMap));
				children.add(appNode);
			}
		}
		rootNode.setChildren(children);
		return rootNode;
	}*/
	public static List<TreePanelNode> getChildrenAppRoleNode(TreePanelNode treeNode,
			Map<String, List<PlasRole>> appRolesMap, boolean bModule,List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getEntityId();// 很重要

		List<PlasRole> roles = appRolesMap.get(parentId);// 根据appCD获取
		if (roles != null && roles.size() > 0) {
			for (PlasRole role : roles) {

				if (bModule && role.getPlasRoleGroup() != null) {
					continue;
				}
				TreePanelNode roleNode = new TreePanelNode();
				roleNode.setId(role.getPlasRoleId());
				roleNode.setText(role.getRoleName());
				roleNode.setNodeType(NODE_TYPE_ROLE);
				roleNode.setEntityId(role.getPlasRoleId());
				roleNode.setEntityCd(role.getRoleCd());
				roleNode.setEntityName(role.getRoleName());
				if(roleIdList!= null){
					if(roleIdList.contains(role.getPlasRoleId())){
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
			Map<String, List<PlasRole>> appRolesMap) {
		return getChildrenAppRoleNode(treeNode, appRolesMap, false, null);
	}

	public static List<TreePanelNode> getChildrenModuleRoleNode(TreePanelNode treeNode,
			Map<String, List<PlasRole>> moduleRolesMap, List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getEntityId();// 很重要

		List<PlasRole> roles = moduleRolesMap.get(parentId);// 根据appCD获取
		if (roles != null && roles.size() > 0) {
			for (PlasRole role : roles) {
				TreePanelNode roleNode = new TreePanelNode();
				roleNode.setId(role.getPlasRoleId());
				roleNode.setText(role.getRoleName());
				roleNode.setNodeType(NODE_TYPE_ROLE);
				roleNode.setEntityId(role.getPlasRoleId());
				roleNode.setEntityCd(role.getRoleCd());
				roleNode.setEntityName(role.getRoleName());
				
				if(roleIdList!= null){
					if(roleIdList.contains(role.getPlasRoleId())){
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

	/**
	 * 
	 * @param roleList
	 * @param bModule
	 *            是否包含角色模块
	 * @return
	 */
	public static Map<String, List<PlasRole>> getAppRolesMap(List<PlasRole> roleList) {
		return getAppRolesMap(roleList, false);
	}
	public static Map<String, List<PlasRole>> getAppRolesMap(List<PlasRole> roleList,boolean bModule) {

		Map<String, List<PlasRole>> appRolesMap = new HashMap<String, List<PlasRole>>();
		for (PlasRole role : roleList) {
			PlasApp app = role.getPlasApp();
			PlasRoleGroup module = role.getPlasRoleGroup();
			if(app == null) {
				continue;
			}
			String uaapAppId = app.getPlasAppId();
			if (StringUtils.isNotBlank(uaapAppId)) {
				if (appRolesMap.containsKey(uaapAppId)) {
					if(bModule && module!= null){
						continue;
					}
					appRolesMap.get(uaapAppId).add(role);
				} else {
					List<PlasRole> newRolesList = new ArrayList<PlasRole>();
					newRolesList.add(role);
					appRolesMap.put(uaapAppId, newRolesList);
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
		rootNode.setId("0");
		rootNode.setText("页面与功能关系树");
		rootNode.setNodeType(NODE_TYPE_PAGE);
		rootNode.setEntityId("0");// 特殊处理
		 
		Map<String, List<AppFunction>> pageFuncsMap = getPageFunctionMap(funcList);
		
		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (pageList != null && pageList.size() > 0) {
			for (AppPage page : pageList) {
				TreePanelNode pageNode = new TreePanelNode();
				pageNode.setId(page.getAppPageId());// app_xxx
				pageNode.setText(page.getPageName());
				pageNode.setNodeType(NODE_TYPE_PAGE);//
				pageNode.setEntityId(page.getAppPageId());
				// 功能点
				pageNode.setChildren(getChildrenFuncNode(pageNode, pageFuncsMap));
				children.add(pageNode);
			}
		}
		rootNode.setChildren(children);
		
		return rootNode;
	}

	private static List<TreePanelNode> getChildrenFuncNode(TreePanelNode pageNode,
			Map<String, List<AppFunction>> pageFuncsMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = pageNode.getEntityId();// 很重要

		List<AppFunction> funcs = pageFuncsMap.get(parentId);// 根据appCD获取
		if (funcs != null && funcs.size() > 0) {
			for (AppFunction func : funcs) {
				TreePanelNode funcNode = new TreePanelNode();
				funcNode.setId(func.getAppFunctionId());
				funcNode.setText(func.getFunctionName());
				funcNode.setNodeType(NODE_TYPE_FUNCTION);//
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
	public static TreePanelNode buildModuleMenuTree(List<AppModule> moduleList, List<AppMenu> relList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("模块与菜单关系树");
		rootNode.setNodeType(NODE_TYPE_PAGE);
		rootNode.setEntityId("0");// 特殊处理

		Map<String, List<AppMenu>> moduleRelMap = getModuleMenuMap(relList);

		// 构造页面
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (moduleList != null && moduleList.size() > 0) {
			for (AppModule module : moduleList) {
				TreePanelNode pageNode = new TreePanelNode();
				pageNode.setId(module.getAppModuleId());
				pageNode.setText(module.getModuleName());
				pageNode.setNodeType(NODE_TYPE_MODULE);//
				pageNode.setEntityId(module.getAppModuleId());
				// 功能点
				pageNode.setChildren(getChildrenRelNode(pageNode, moduleRelMap));
				children.add(pageNode);
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
			for (AppMenu rel : rels) {
				TreePanelNode funcNode = new TreePanelNode();
				funcNode.setId(rel.getAppMenuId());// 关系ID
				funcNode.setText(rel.getMenuName());
				funcNode.setNodeType(NODE_TYPE_MENU);
				funcNode.setEntityId(rel.getAppMenuId());
				children.add(funcNode);
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
	public static TreePanelNode getTreePanelOrgNoChild(OrgTreeVo org) {
		return getTreePanelOrgNoChild(org, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelOrgNoChild(OrgTreeVo org, String checked) {
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
		orgNode.setExtParam(org.getOrgTypeCd());
		return orgNode;
	}

	// 构造人员
	public static TreePanelNode getTreePanelUserNoChild(PlasUser user) {
		return getTreePanelUserNoChild(user, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelUserNoChild(PlasUser user, String checked) {

		TreePanelNode userNode = new TreePanelNode();
		userNode.setId(NODE_USER_PRE_ID + user.getUiid());// usertreenode_xxx(userCd)千万不能改,方便首页机构树刷新人员状态使用
		userNode.setText(user.getUserName());
		userNode.setSexCd(user.getSexCd());
		// userNode.setExtParam(user.getUiid() + "," + user.getUserStatusCd());
		userNode.setExtParam(user.getUiid());
//		TODO:BEGIN
		userNode.setEntityStatusCd(user.getServiceStatusCd());
//		TODO:END
		userNode.setEntityId(user.getPlasUserId());
		userNode.setEntityCd(user.getUiid());
		userNode.setEntityBizCd(user.getUserBizCd());
		userNode.setEntityName(user.getUserName());
		userNode.setNodeType(NODE_TYPE_USER);
		userNode.setOrgOrUser(NODE_TYPE_USER);
		userNode.setChecked(checked);
		return userNode;
	}

	/**
	 * 生成指定类型(treePanelType)的机构权限树
	 * 
	 * @param nodeType
	 * @return
	 */
	public static TreePanelNode getTreeNodePanelOrgLogical(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_LOGICAL, uiid, false);
	}

	public static TreePanelNode getTreeNodePanelOrgPhysical(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_PHYSICAL, uiid, false);
	}

	// 分公司或其他业务界面显示的机构用户树
	public static TreePanelNode getTreeNodePanelUserLogical(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_LOGICAL, uiid, true);
	}

	public static TreePanelNode getTreeNodePanelUserPhysical(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_PHYSICAL, uiid, true);
	}

	// plas界面显示的机构用户树
	public static TreePanelNode getTreeNodePanelUserLogicalAll(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_LOGICAL, uiid, true, true,null,false);
	}

	public static TreePanelNode getTreeNodePanelUserPhysicalAll(String uiid) {
		return getTreeNodePanel(DictContants.TREE_DIME_PHYSICAL, uiid, true, true,null,false);
	}
	public static TreePanelNode getTreeNodePanelUserPhysicalCheck(String uiid,List<String> checkUserIdList,boolean check){
		return getTreeNodePanel(DictContants.TREE_DIME_PHYSICAL, uiid, true, true,checkUserIdList,check);
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

	/**
	 * Description:	// 提供机构管理员在维护"机构和用户列表"时使用
	 * @param treePanelType  类型：逻辑/物理
	 * @param uiid			 用户： uiid
	 * @param bIncludeUser	是否包含用户
	 * @param bEnableAll	是否所有用户
	 * @param checkList		需要选中的用户
	 * @param check			是否有复选框
	 * @return
	 * TreePanelNode
	 */
	public static TreePanelNode getTreeNodePanel(String treePanelType, String uiid, 
			boolean bIncludeUser, boolean bEnableAll,List<String> checkUserIdList,boolean check) {


		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasUserManager uaapUserManager = SpringContextHolder.getBean("plasUserManager");
		PlasRoleManager uaapRoleManager = SpringContextHolder.getBean("plasRoleManager");
		PlasSysPosRoleRelManager uaapUserRoleOrgRelManager = SpringContextHolder.getBean("plasSysPosRoleRelManager");

		String uaapUserId = uaapUserManager.getPlasUserByUiid(uiid).getPlasUserId();
		List<PlasRole> roles =  uaapRoleManager.getUserRoles( uiid);

		// 1.是否"超级管理员"(A_ADMIN_SUPER/A_ADMIN)

		// 2.是否"机构管理员"(A_ADMIN_UAAP_ORG)

		boolean isAdminSuper = RoleUtil.isAdminSupser(roles);
		boolean isAdmin = RoleUtil.isAdmin(roles);
		boolean isAdminPlasOrg = RoleUtil.isAdminPlasOrg(roles);



		TreePanelNode rootNode = getRootNode();

		List<OrgTreeVo> orgs = plasDimeOrgRel.getOrgTreeVoList(treePanelType);
		List<PlasUser> users = new ArrayList<PlasUser>();
		if (bIncludeUser) {
			if (bEnableAll) {
				users = uaapUserManager.getAllUsers();
			} else {
				users = uaapUserManager.getAllPlasUser();
			}
		}
		if (isAdminSuper || isAdmin) {
			if (DictContants.TREE_DIME_LOGICAL.equals(treePanelType)) {
				buildLogicalOrgUserTree(rootNode, orgs, users,checkUserIdList,check);
			}
			if (DictContants.TREE_DIME_PHYSICAL.equals(treePanelType)) {
				buildPhysicalOrgUserTree(rootNode, orgs, users,checkUserIdList,check);
			}
		} else if (isAdminPlasOrg) {
			List<OrgTreeVo> authOrgs = uaapUserRoleOrgRelManager.getOrgTreeVosByUserId(uaapUserId);
			for (int i = 0; i < authOrgs.size(); i++) {
				TreePanelNode node = getTreePanelOrgNoChild(authOrgs.get(i));
				if (DictContants.TREE_DIME_LOGICAL.equals(treePanelType)) {
					buildLogicalOrgUserTree(node, orgs, users,checkUserIdList,check);
				}
				if (DictContants.TREE_DIME_PHYSICAL.equals(treePanelType)) {
					buildPhysicalOrgUserTree(node, orgs, users,checkUserIdList,check);
				}
				rootNode.getChildren().add(node);
			}
		} else {
			// 不作处理
		}
		return rootNode;
	}

	public static TreePanelNode buildOrgTree(Map<String,List<OrgTreeVo>>  orgMap){
		return buildOrgTree(DictContants.TREE_DIME_PHYSICAL,orgMap);
	}
	public static TreePanelNode buildOrgTree(String dimeCd){
		return buildOrgTree(dimeCd,null);
	}
	public static TreePanelNode buildOrgTree(String dimeCd,Map<String,List<OrgTreeVo>>  orgMap){
	//TODU
		TreePanelNode rootNode = getRootNode();
		// 设置子孙节点
		Map<String,List<OrgTreeVo>> tmpOrgMap = null;
		if(orgMap==null){
			tmpOrgMap = getOrgMap(dimeCd);
		}else {
			tmpOrgMap = orgMap;
		}
		rootNode.setChildren(getChildrenNodeNoCheck(dimeCd, rootNode, orgMap, new HashMap<String,List<UserTreeVo>>()));

		return rootNode;
	}
	public static TreePanelNode buildOrgUserTree(String dimeCd){
		TreePanelNode rootNode = getRootNode();
		// 设置子孙节点
		rootNode.setChildren(getChildrenNodeNoCheck(dimeCd, rootNode, getOrgMap(dimeCd), getUserMap()));
		return rootNode;
	}
	/**
	 * 构建人员机构树(无复选框)
	 */
	private static List<TreePanelNode> getChildrenNodeNoCheck(String treeType, TreePanelNode treeNode, Map<String, List<OrgTreeVo>> orgOrgsMap,
			Map<String, List<UserTreeVo>> orgUsersMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先人员
		List<UserTreeVo> users = orgUsersMap.get(parentId);// 根据机构CD获取
		if (users != null && users.size() > 0) {

			for (UserTreeVo user : users) {
				TreePanelNode userNode = getTreePanelUserNoChild(user, NODE_CHECKED_UNDEFINED);

				children.add(userNode);

			}
		}

		// 后机构
		List<OrgTreeVo> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (OrgTreeVo org : orgs) {
				TreePanelNode orgNode = getTreePanelOrgNoChild(org, NODE_CHECKED_UNDEFINED);
				orgNode.setChildren(getChildrenNodeNoCheck(treeType, orgNode, orgOrgsMap, orgUsersMap));
				children.add(orgNode);
			}
		}

		return children;
	}


	/**
	 * 构建以机构id为key，下属机构清单为value的map
	 */
	public static Map<String,List<OrgTreeVo>> getOrgMap(String dimeCd){
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		return plasDimeOrgRel.getOrgTreeVoMap(dimeCd);
	}
	/**
	 * 构建以机构id为key，用户清单为value的Map
	 */
	public static Map<String,List<UserTreeVo>> getUserMap(){
		PlasUserManager plasUserManager = SpringContextHolder.getBean("plasUserManager");
		return plasUserManager.getAlls();
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
	 * 获取根节点
	 * 
	 * @return
	 */
	public static TreePanelNode getRootTreeNode() {

		AppParamManager appParamManager = SpringContextHolder.getBean("appParamManager");
		String rootCd = appParamManager.getAppOrgTreeRootCd();
		String rootName = appParamManager.getAppOrgTreeRootName();

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(rootCd);
		rootNode.setText(rootName);
		rootNode.setEntityId(rootCd);// 特殊处理
		rootNode.setEntityCd(rootCd);
		rootNode.setEntityName(rootName);
		rootNode.setNodeType(NODE_TYPE_ORG);
		rootNode.setChecked(NODE_CHECKED_UNDEFINED);
		return rootNode;
	}

	public static TreePanelNode buildAppModuleTree(List<PlasApp> appList,
			List<PlasRoleGroup> moduleList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("应用与角色模块关系树");
		rootNode.setEntityId("0");// 特殊处理

		Map<String, List<PlasRoleGroup>> appModulesMap = getAppModulesMap(moduleList);
		Map<String, List<PlasRole>> moduleRolesMap = new HashMap<String, List<PlasRole>>();

		// 构造应用
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (appList != null && appList.size() > 0) {
			for (PlasApp app : appList) {
				TreePanelNode appNode = new TreePanelNode();
				appNode.setId("app_" + app.getAppCd());
				appNode.setText(app.getAppChnName());
				appNode.setNodeType(NODE_TYPE_APP);
				appNode.setEntityId(app.getPlasAppId());
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

	private static List<TreePanelNode> getChildrenModuleNode(TreePanelNode node,Map<String, List<PlasRoleGroup>> appModulesMap,Map<String,List<PlasRole>> moduleRolesMap) {
		return getChildrenModuleNode(node, appModulesMap, moduleRolesMap, null);
	}
	private static List<TreePanelNode> getChildrenModuleNode(TreePanelNode node,Map<String, List<PlasRoleGroup>> appModulesMap,Map<String,List<PlasRole>> moduleRolesMap, List<String> roleIdList) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = node.getEntityId();// 很重要

		List<PlasRoleGroup> modules = appModulesMap.get(parentId);
		if (modules != null && modules.size() > 0) {
			for (PlasRoleGroup module : modules) {
				TreePanelNode moduleNode = new TreePanelNode();
				moduleNode.setId(module.getPlasRoleGroupId());
				moduleNode.setText(module.getRoleGroupName());
				moduleNode.setNodeType(NODE_TYPE_ROLE_MODULE);
				moduleNode.setEntityId(module.getPlasRoleGroupId());
				moduleNode.setEntityCd(module.getPlasRoleGroupId());
				moduleNode.setEntityName(module.getRoleGroupName());
				moduleNode.setChildren(getChildrenModuleRoleNode(moduleNode, moduleRolesMap, roleIdList));
				
				if(roleIdList!= null){ 
					int checkedCount = 0;
					for (TreePanelNode roleNode : moduleNode.getChildren()) {
						if(NODE_CHECKED_CHECKED.equals(roleNode.getChecked())){
							checkedCount ++;
							continue;
						}
					}
					if(checkedCount == 0){
						moduleNode.setChecked(NODE_CHECKED_NONE);
					}else{
						if(checkedCount == moduleNode.getChildren().size()){
							moduleNode.setChecked(NODE_CHECKED_CHECKED);
						}else{
							moduleNode.setChecked(NODE_CHECKED_HALF);
						}
					}
				}
				children.add(moduleNode);
			}
		}
		return children;
	}

	private static Map<String, List<PlasRoleGroup>> getAppModulesMap(List<PlasRoleGroup> moduleList) {
		Map<String, List<PlasRoleGroup>> appModulesMap = new HashMap<String, List<PlasRoleGroup>>();
		for (PlasRoleGroup module : moduleList) {

			String parentId = module.getParentId();
			if (StringUtils.isNotBlank(parentId)) {
				if (appModulesMap.containsKey(parentId)) {
					appModulesMap.get(parentId).add(module);
				} else {
					List<PlasRoleGroup> newRolesList = new ArrayList<PlasRoleGroup>();
					newRolesList.add(module);
					appModulesMap.put(parentId, newRolesList);
				}
			}
		}
		return appModulesMap;
	}

	private static Map<String, List<PlasRole>> getModuleRolesMap(List<PlasRole> roleList) {
		Map<String, List<PlasRole>> appModulesMap = new HashMap<String, List<PlasRole>>();
		for (PlasRole role : roleList) {
			PlasRoleGroup module = role.getPlasRoleGroup();
			if (module == null) {
				continue;
			}
			String moduleId = module.getPlasRoleGroupId();
			if (StringUtils.isNotBlank(moduleId)) {
				if (appModulesMap.containsKey(moduleId)) {
					appModulesMap.get(moduleId).add(role);
				} else {
					List<PlasRole> newRolesList = new ArrayList<PlasRole>();
					newRolesList.add(role);
					appModulesMap.put(moduleId, newRolesList);
				}
			}
		}
		return appModulesMap;
	}


	//非常好用
	private static void addTreeNode(TreePanelNode treeRoot, TreePanelNode treeChild,
			String parentId,boolean checked) {
		if (StringUtils.isEmpty(parentId) || treeRoot.getId().equals(parentId)) {
			treeRoot.addChild(treeChild);
		} else {
			addTreeNode(treeChild, treeRoot.getChildren(), parentId,checked);
		}
		
	}
	private static void setNodeCheck(TreePanelNode treeRoot){
		int num = 0;
		for(TreePanelNode node:treeRoot.getChildren()){
			if(node.getChecked().equals(NODE_CHECKED_CHECKED)||node.getChecked().equals(NODE_CHECKED_HALF)){
				num++;
			}
		}
		int childsize = treeRoot.getChildren().size();
		if(childsize==0){
			treeRoot.setChecked(NODE_CHECKED_NONE);
		}else {
			if(num==0){
				treeRoot.setChecked(NODE_CHECKED_NONE);
			}else if(num <childsize){
				treeRoot.setChecked(NODE_CHECKED_HALF);
			}else if(num==childsize){
				treeRoot.setChecked(NODE_CHECKED_CHECKED);
			}
		}
	}
	/**
	 * 增加树节点
	 * 
	 * @param treeChild
	 * @param list
	 * @param parentId
	 */
	private static void addTreeNode(TreePanelNode treeChild, List<TreePanelNode> list,
			String parentId,boolean checked) {
		boolean found = false;
		for (TreePanelNode TreePanelNode : list) {
			if(null==TreePanelNode.getId()){
				//插入日志 数据异常：账号没有Id 

				continue;
			}
			if (TreePanelNode.getId().equals(parentId)) {
				TreePanelNode.addChild(treeChild);
				found = true;
				
				//设置复选框
				if(checked){
					setNodeCheck(TreePanelNode);
				}
				break;
			}
		}
		if (!found) {
			for (TreePanelNode TreePanelNode : list) {
				addTreeNode(treeChild, TreePanelNode.getChildren(), parentId,checked);
			}
		}
	}
	/**
	 * 
	 * Description:加载机构职位树：（默认逻辑机构，无复选框）
	 * author:jiaoxiaofeng  2011-4-15
	 * @return
	 * EasyTree
	 */
	public static TreePanelNode createOrgPositionEnable(boolean isAllFlag){
		return createOrgPosition(DictContants.TREE_DIME_LOGICAL,null,false,isAllFlag);
	}
	public static TreePanelNode createOrgPosition(){
		return createOrgPosition(DictContants.TREE_DIME_LOGICAL,null,false, true);
	}
	public static TreePanelNode createOrgPositionTreePhysical(){
		return createOrgPosition(DictContants.TREE_DIME_PHYSICAL,null,false, true);
	}
	/**
	 * 
	 * Description:加载机构职位树：（默认逻辑机构，有复选框）
	 * author:jiaoxiaofeng  2011-4-15
	 * @return
	 * TreePanelNode
	 */

	public static TreePanelNode createOrgPosition(String treeType,List<PlasSysPosition> checkList,boolean check){
		return createOrgPosition(treeType, checkList, check, false);
	}
	public static TreePanelNode createOrgPosition(String treeType,List<PlasSysPosition> checkList,boolean check, boolean isAllFlag){
		
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasSysPositionManager plasSysManager = SpringContextHolder.getBean("plasSysPositionManager");
		TreePanelNode rootNode = getRootNode();
		List<OrgTreeVo> orgs = plasDimeOrgRel.getOrgTreeVoList(treeType);
		Map<String, List<OrgTreeVo>> orgOrgsMap = getOrgOrgsMap(treeType, orgs);
		Map<String,List<PlasSysPosition>> sysPos = plasSysManager.getAlls(isAllFlag);
		if(check) {
			rootNode.setChildren(setOrgSysTreeCheck(rootNode,orgOrgsMap,sysPos,checkList,check));
		} else{
			rootNode.setChildren(setOrgSysTreeCheck(rootNode,orgOrgsMap,sysPos,null,false));
		}
		return rootNode;
	}
	/**
	 * 
	 * Description:构建系统职位树（有复选框）

	 */
	public static List<TreePanelNode> setOrgSysTreeCheck(TreePanelNode node,Map<String,List<OrgTreeVo>> orgMap,
			Map<String,List<PlasSysPosition>> sysMap,List<PlasSysPosition> checkList,boolean check){
		TreePanelNode orgNode = null;

		
		 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = node.getId();
		
		TreePanelNode positionNode= null;
		// 先人员
		List<PlasSysPosition> syss = sysMap.get(parentId);// 根据机构CD获取
		//插入系统职位
		if(syss!=null&&syss.size()>0){
			for(PlasSysPosition obj:syss){
				positionNode = getSysPosNode(obj);
				children.add(positionNode);
				if(check){
					if(checkList.contains(obj)){
						positionNode.setChecked(NODE_CHECKED_CHECKED);
					}else{
						positionNode.setChecked(NODE_CHECKED_NONE);
					}
				}
			}

		}

		// 后机构
		List<OrgTreeVo> orgs = orgMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for(OrgTreeVo org : orgs){
				orgNode = getTreePanelOrgNoChild(org);			
				orgNode.setChildren(setOrgSysTreeCheck(orgNode, orgMap, sysMap,checkList,check));
				if(check){
					refreshNodeChecked(orgNode);
				}
				children.add(orgNode);
			}
			
		}
		return children;
	}
	/**
	 * 
	 * Description:构建系统职位节点
	 */
	public static TreePanelNode getSysPosNode(PlasSysPosition obj){
		TreePanelNode node = new TreePanelNode();
		node.setId(obj.getPlasSysPositionId());
		node.setEntityId(obj.getPlasSysPositionId());
		node.setText(obj.getSysPosName());
		node.setNodeType(NODE_TYPE_SYSP);

		if( obj.getActiveBl() == null){
			node.setEntityStatusCd("0");
		}else{
			if(obj.getActiveBl().booleanValue()){
				node.setEntityStatusCd("1");
			}else{
				node.setEntityStatusCd("0");
			}
		}
		return node;
	}
	/**
	 * 
	 * Description:构建角色树(无复选框)
	 */
	public static TreePanelNode createRoleGroupTree(String appBizCd){
		return createRoleGroupTree(false,appBizCd,null);
	}
	/**
	 * 
	 * Description:构建角色树(有复选框）
	 */
	public static TreePanelNode createRoleGroupTreeCheck(List<PlasRole> checkList){
		return createRoleGroupTree(true,null,checkList);
	} 
	
	/**
	 * Description 构建(去除未选中的)角色树(有复选框)
	 * @param checkList
	 * @return
	 */
	public static TreePanelNode createRolePackTreeChecked(List<PlasRole> checkList){
		TreePanelNode node = createRoleGroupTree(true, null, checkList);
		removeNoCheckedLeave(node, NODE_TYPE_ROLE);
		TreePanelUtil2.refreshNodeStatus(node);
		return node;
	} 
	
	public static TreePanelNode createRoleGroupTree(boolean isAll,String appBizCd,List<PlasRole> checkList){
		PlasRoleGroupManager plasRoleGroupManager = SpringContextHolder.getBean("plasRoleGroupManager");
		PlasAppManager plasAppManager = SpringContextHolder.getBean("plasAppManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");

		TreePanelNode rootNode = getRootNode();

		List<PlasApp> appList = null;
		List<PlasRoleGroup> roleGroupList = plasRoleGroupManager.getAppRoleGroup();
		Map<String,List<PlasRole>> roles = plasRoleManager.getAlls();
		if(isAll){
			appList = plasAppManager.getAll();
			//1应用
			rootNode = setAppTree(rootNode,appList);
			//否则查询显示所有的角色组和角色
			//roleGroupList = plasRoleGroupManager.getAllOrderedGroups();
		}else {
			rootNode = new TreePanelNode();
			getAppNode(rootNode,plasAppManager.getEntityByAppBizCd(appBizCd));
		}
		
		//2角色组-角色
		rootNode = setRoleGroupTree(rootNode,roleGroupList,roles,checkList);

		TreePanelUtil2.refreshNodeStatus(rootNode);
		return rootNode;
	}
	
	/**
	 * 删除未选中的叶子节点以及没有叶子节点的祖先节点.
	 * 
	 * @param node
	 * @param leaveNodeTypeCd
	 * @return
	 */
	public static TreePanelNode removeNoCheckedLeave(TreePanelNode node,String leaveNodeTypeCd){
		if(node == null)
			return null;

		List<TreePanelNode> childList = node.getChildren();

		List<TreePanelNode> childList2 = new ArrayList<TreePanelNode> ();
		TreePanelNode tNode = null;
		for (TreePanelNode t : childList) {
			//叶子节点
			if(t.getChildren().size() == 0){
				if(t.getNodeType() == null){
					;
				}
				else if(t.getNodeType().equals(leaveNodeTypeCd)){
					if(t.getChecked().equals(NODE_CHECKED_CHECKED)){
						childList2.add(t);
					}
				} 
			}else{
				tNode = removeNoCheckedLeave(t,leaveNodeTypeCd);
				if(tNode.getChildren().size()>0) {
					childList2.add(tNode);
				}
			}
		}
		node.setChildren(childList2);
		return node;
	}
	/**
		构建应用树形目录
	 */
	public static TreePanelNode setAppTree(TreePanelNode node,List<PlasApp> appList){
		TreePanelNode appNode = null;
		for(PlasApp obj : appList){
			appNode = new TreePanelNode();
			getAppNode(appNode,obj);
			addTreeNode(node, appNode, DictContants.ORG_ROOT_CD,false);
		}
		return node;
	}
	/**
	 * 
	 * Description:构建应用节点
	 */
	public static void getAppNode(TreePanelNode appNode,PlasApp obj){
		appNode.setId(obj.getPlasAppId());
		appNode.setText(obj.getAppChnName());
		appNode.setNodeType(NODE_TYPE_APP);
		appNode.setEntityName(obj.getAppChnName());
	}
	public static TreePanelNode setRoleGroupTree(TreePanelNode node,List<PlasRoleGroup> groupList,Map<String,List<PlasRole>> roles,List<PlasRole> checkList){
		boolean checked;
		if(null==checkList) {
			checked = false;
		} else {
			checked = true;
		}
		List<PlasRole> tmp = null;
		TreePanelNode roleGroupNode = null;
		for(PlasRoleGroup obj : groupList){
			roleGroupNode = getRoleGroupNode(obj,roleGroupNode);
			
			//加载子节点 角色

			tmp = roles.get(roleGroupNode.getId());
			setRoleTree(roleGroupNode,tmp,checkList);
			
			
			addTreeNode(node, roleGroupNode, obj.getParentId(),checked);
		}
		return node;
	}
	/**
	 * 
	 * Description:构建角色树
	 */
	public static TreePanelNode setRoleTree(TreePanelNode node,List<PlasRole> roleList,List<PlasRole> checkList){
		TreePanelNode roleNode = null;
		int num = 0;
		for(PlasRole obj : roleList){
			roleNode = getRoleNode(obj,roleNode);
			if(null!=checkList){
				if(checkList.contains(obj)) {
					roleNode.setChecked(NODE_CHECKED_CHECKED);
					num++;
				}else{
					roleNode.setChecked(NODE_CHECKED_NONE);
				}
			}
			node.addChild(roleNode);
		}
		//构建带复选框的角色组节点
		if(null!=checkList){
			 if(num==0){
				node.setChecked(NODE_CHECKED_NONE);
			}else if(num<roleList.size()){
				node.setChecked(NODE_CHECKED_HALF);
			}else if(num==roleList.size()){
				node.setChecked(NODE_CHECKED_CHECKED);
			}
		}
		return node;
	}
	/**
	 * 构建角色节点
	 */
	public static TreePanelNode getRoleNode(PlasRole obj,TreePanelNode roleNode){
		roleNode = new TreePanelNode();
		roleNode.setId(obj.getPlasRoleId());
		roleNode.setEntityId(obj.getPlasRoleId());
		roleNode.setEntityCd(obj.getRoleCd());
		roleNode.setText(obj.getRoleName());
		roleNode.setNodeType(NODE_TYPE_ROLE);
		roleNode.setEntityName(obj.getRoleName());
		return roleNode;
	}
	/**
	 * 构建角色组节点
	 */
	public static TreePanelNode getRoleGroupNode(PlasRoleGroup obj,TreePanelNode roleGroupNode){
		roleGroupNode = new TreePanelNode();
		roleGroupNode.setId(obj.getPlasRoleGroupId());
		roleGroupNode.setEntityId(obj.getPlasRoleGroupId());
		roleGroupNode.setText(obj.getRoleGroupName());
		roleGroupNode.setNodeType(NODE_TYPE_ROLE_GROUP);
		roleGroupNode.setEntityName(obj.getRoleGroupName());
		return roleGroupNode;
	}
	/**
	 * 
	 * Description:构建模块菜单树（带复选框）
	 */
	public static TreePanelNode createAppModuleMenuTree(List<AppMenu> checks){
		AppModuleManager appModuleManager = SpringContextHolder.getBean("appModuleManager");
		List<AppModule> moduleList = appModuleManager.getAll();
		TreePanelNode treeRoot = new TreePanelNode();
		treeRoot.setId("0");
		treeRoot.setText("模块与菜单关系树");
		treeRoot.setEntityId("0");
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
	public static void getMenuNode(TreePanelNode menuNode,AppMenu menu){
		menuNode.setId(menu.getAppMenuId());
		menuNode.setText(menu.getMenuName());
		menuNode.setEntityId(menuNode.getId());
		menuNode.setEntityCd(menu.getMenuCd());
		menuNode.setEntityName(menu.getMenuName());
		menuNode.setNodeType(NODE_TYPE_MENU);
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
	public static void getModuleNode(TreePanelNode moduleNode,AppModule module){
		moduleNode.setId(module.getAppModuleId());
		moduleNode.setText(module.getModuleName());
		moduleNode.setEntityId(moduleNode.getId());
		moduleNode.setEntityCd(module.getModuleCd());
		moduleNode.setNodeType(NODE_TYPE_MODULE);
	}
	//构建菜单Map(以moduleId为key）
	public static Map<String,List<AppMenu>> getMenuMap(){
		AppMenuManager appMenuManager = SpringContextHolder.getBean("appMenuManager");
		List<AppMenu> menuList = appMenuManager.getAll();
		Map<String,List<AppMenu>> result = new HashMap<String,List<AppMenu>>();
		List<AppMenu> tmp = null;
		for(AppMenu obj : menuList){
			tmp = result.get(obj.getAppModule().getAppModuleId());
			if(null==tmp) {
				tmp = new ArrayList<AppMenu>();
			}
			
			tmp.add(obj);
			result.put(obj.getAppModule().getAppModuleId(), tmp);
		}
		return result;

	}
	/**
	 * 构建功能Map（以pageId为key）
	 */
	public static Map<String,List<AppFunction>> getFunctionMap(){
		AppFunctionManager appFunctionManager = SpringContextHolder.getBean("appFunctionManager");
		return appFunctionManager.getFunctionMap();
	}
	/**
	 * 更新节点状态(未测试)
	 * 
	 * @param node
	 * @return
	 */
	public static TreePanelNode refreshNodeStatus(TreePanelNode node) {

		int totalCount = 0;
		int checkedCount = 0;

		if (node != null && node.getChildren() != null) {
			totalCount = node.getChildren().size();
			for (int i = 0; i < totalCount; i++) {
				TreePanelNode tNode = node.getChildren().get(i);
				if (tNode == null) {
					continue;
				}
				tNode = refreshNodeStatus(tNode);
				if (NODE_CHECKED_CHECKED.equals(tNode.getChecked())) {
					checkedCount++;
				}
			}
		}

		if (totalCount == 0)
			return node;

		String nodeStatus = NODE_CHECKED_NONE;
		if (checkedCount == 0) {
			nodeStatus = NODE_CHECKED_NONE;
		} else if (totalCount == checkedCount) {
			nodeStatus = NODE_CHECKED_CHECKED;
		} else if (totalCount > checkedCount && checkedCount > 0) {
			nodeStatus = NODE_CHECKED_HALF;
		}

		node.setChecked(nodeStatus);
		return node;
	}
	
	/**
	 * 移除未选中的节点
	 * 
	 * @param node
	 * @return
	 */
	public static TreePanelNode removeNodeNoChecked(TreePanelNode node) {

//		List<TreePanelNode> children = node.getChildren();
//		int childSize = children.size();
//		boolean containFlg = false;
//		TreePanelNode tmpSon = null;
////		for (TreePanelNode treePanelNode : children) {
////			if(!NODE_CHECKED_CHECKED.equals(treePanelNode.getChecked()) || !NODE_CHECKED_HALF.equals(treePanelNode.getChecked())){
////				children.remove(treePanelNode);
////			}
////		}
//		for(int i=0; i > childSize; i++){
//			tmpSon = children.get(i);
//			if(!NODE_CHECKED_CHECKED.equals(tmpSon.getChecked()) || !NODE_CHECKED_HALF.equals(tmpSon.getChecked())){
//				children.remove(i);
//			}
//		}
//		return containFlg;
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
				if (!NODE_CHECKED_CHECKED.equals(tNode.getChecked()) || !NODE_CHECKED_HALF.equals(tNode.getChecked())) {
					checkedSonCount++;
					node.getChildren().remove(i);
				}
			}
		}

//		if (totalSonCount == 0)
//			return node;
//
//		String pNodeStatusCd = NODE_CHECKED_NONE;
//		if (checkedSonCount == 0) {
//			pNodeStatusCd = NODE_CHECKED_NONE;
//		} else if (totalSonCount == checkedSonCount) {
//			pNodeStatusCd = NODE_CHECKED_CHECKED;
//		} else if (totalSonCount > checkedSonCount && checkedSonCount > 0) {
//			pNodeStatusCd = NODE_CHECKED_HALF;
//		}
//		
//		if(node!= null){
//			node.setChecked(pNodeStatusCd);
//		}
		return node;
	}
	
	/**
	 * 机构与账户树 
	 * @param orgList
	 * @param acctList
	 * @param checked
	 * @return
	 */
	public static TreePanelNode buildOrgAcctTreeNoChecked(String uiid ,boolean bEnableAllOrg,boolean bEnableAllAcct){
		return buildOrgAcctTreeNoChecked(uiid,bEnableAllOrg,bEnableAllAcct,false);
	}
	public static TreePanelNode buildOrgAcctTreeNoChecked(String uiid ,boolean bEnableAllOrg,boolean bEnableAllAcct,boolean isWaitingAcct){
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasAcctManager plasAcctManager = SpringContextHolder.getBean("plasAcctManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
		
		List<PlasRole>  roles = plasRoleManager.getUserRoles( uiid);
			
		// 1.是否"超级管理员"(A_ADMIN_SUPER/A_ADMIN)
		 boolean isAdminSuper = RoleUtil.isAdminSupser(roles);
		 boolean isAdmin = RoleUtil.isAdmin(roles);

		// 2.是否"机构管理员"(A_ADMIN_UAAP_ORG)
		boolean isAdminPlasOrg = RoleUtil.isAdminPlasOrg(roles);


		TreePanelNode rootNode = getRootNode();

		List<VoOrg> orgs = null;

		
		List<VoAcct> accts = null;

		if (isAdminSuper || isAdmin) {
			orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,true);//是否所有机构
			accts = plasAcctManager.getVoAcctList(bEnableAllAcct);//是否所有账号

				buildOrgAcctTree(rootNode, orgs, accts);//,checkList,check);//TODO
		} else if (isAdminPlasOrg) {
			orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,false);//有效有机构
			accts = plasAcctManager.getVoAcctList(false);

			List<VoOrg> authOrgs =  plasOrgManager.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,uiid,false);
			for (int i = 0; i < authOrgs.size(); i++) {
				TreePanelNode node = getTreePanelOrgNoChild(authOrgs.get(i),NODE_CHECKED_UNDEFINED);
					buildOrgAcctTree(node, orgs, accts);//,checkList,check);//TODO
				rootNode.getChildren().add(node);
			}
		} 
		return rootNode;
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
		return orgNode;
	}
	public static TreePanelNode buildOrgAcctTree(TreePanelNode rootNode,List<VoOrg> orgList,List<VoAcct> acctList){

		
		Map<String,List<VoOrg>> orgsMap = TreePanelUtil2.getOrgOrgsMap(DictContants.TREE_DIME_PHYSICAL, orgList);
		Map<String,List<VoAcct>> acctsMap = TreePanelUtil2.getOrgAcctsMap(DictContants.TREE_DIME_PHYSICAL, acctList);
		
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode2(rootNode, orgsMap, acctsMap));

		return rootNode;
	}

	private static List<TreePanelNode> getChildrenNode2( TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String, List<VoAcct>> orgAccts) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先账号
		List<VoAcct> accts = orgAccts.get(parentId);// 根据机构CD获取
		if (accts != null && accts.size() > 0) {
			TreePanelNode acctNode = null;
			for (VoAcct acct : accts) {
				acctNode = getTreePanelAcctNoChild(acct);
				children.add(acctNode);
			}
		}

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			TreePanelNode orgNode  = null;
			for (VoOrg org : orgs) {
				orgNode = TreePanelUtil2.getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode2( orgNode, orgOrgsMap, orgAccts));
				children.add(orgNode);
			}
		}
		return children;
	}

	public static TreePanelNode getTreePanelAcctNoChild(VoAcct acct) {

		TreePanelNode node = new TreePanelNode();
		node.setId(acct.getAcctId());
		node.setText(acct.getUserName()+"("+acct.getUiid()+")");
		node.setExtParam(acct.getUiid());
		
		node.setEntityId(acct.getAcctId());
		node.setEntityName(acct.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_ACCT);
		node.setNodeType(NODE_TYPE_ACCT);
		
		node.setSexCd(acct.getSexCd());
		node.setEntityStatusCd(acct.getStatusCd());
		return node;
	}
	

	public static TreePanelNode buildOrgAcctApplyTree(String sysPosId ){
		PlasAcctManager plasAcctManager = SpringContextHolder.getBean("plasAcctManager");
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasOrgCheckRelManager plasOrgCheckRel = SpringContextHolder.getBean("plasOrgCheckRelManager");
		TreePanelNode rootNode = getRootNode();
		
		List<VoOrg>	orgs =null;
		List<VoAcct>	acctList =new ArrayList<VoAcct>();// plasAcctManager.getVoWaitingAcctList();//是否所有机构

		if(RoleUtil.isAdmin()||RoleUtil.isAdminSupser()){
			orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,false);//有效机构
			buildOrgAcctApplyTree(DictContants.TREE_DIME_PHYSICAL,rootNode, orgs, acctList);
		}else if(RoleUtil.validateRole(GlobalConstants.A_ADMIN_HR_CHIEF)){
			orgs = plasDimeOrgRel.getVoOrgList(DictContants.TREE_DIME_PHYSICAL,false);//有效机构
			List<VoOrg> authOrgs = plasOrgCheckRel.getCheckOrgs(sysPosId);
			for (int i = 0; i < authOrgs.size(); i++) {
				TreePanelNode node = TreePanelUtil2.getTreePanelOrgNoChild(authOrgs.get(i));
				// 设置子孙节点
				buildOrgAcctApplyTree(DictContants.TREE_DIME_PHYSICAL,node, orgs, acctList);
				rootNode.getChildren().add(node);
			}
		}

		return rootNode;
	}
	public static TreePanelNode buildOrgAcctApplyTree(String treeType, TreePanelNode rootNode,
			List<VoOrg> orgList,
			List<VoAcct> acctList) {

		Map<String, List<VoAcct>> orgUsersMap = TreePanelUtil2.getOrgAcctsMap(treeType, acctList);
		Map<String, List<VoOrg>> orgOrgsMap = TreePanelUtil2.getOrgOrgsMap(treeType, orgList);
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode3(treeType, rootNode, orgOrgsMap, orgUsersMap));
 
		return rootNode;
	}
	private static List<TreePanelNode> getChildrenNode3(String treeType, TreePanelNode treeNode,
			Map<String, List<VoOrg>> orgOrgsMap,
			Map<String,List<VoAcct>> acctsMap) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 后机构
		List<VoOrg> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (VoOrg org : orgs) {
				TreePanelNode orgNode = TreePanelUtil2.getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode3(treeType, orgNode, orgOrgsMap,acctsMap));
				refreshNodeCount(orgNode,acctsMap);
				children.add(orgNode);
			}
		}
		return children;
	}
	// 获取节点的状态
	public static void refreshNodeCount(TreePanelNode node,Map<String,List<VoAcct>> acctsMap) {

		List<TreePanelNode> children = node.getChildren();
		int childSize = children.size();
		int childCheckSize = 0;
		int selfCheckSize = 0;
		if (acctsMap.containsKey(node.getId())) {
			selfCheckSize+=acctsMap.get(node.getId()).size();
		}
		for (TreePanelNode tmpNode : children) {
			childCheckSize  = childCheckSize+ (Integer.valueOf(tmpNode.getExtParam()));
		}
		node.setExtParam(String.valueOf(selfCheckSize+childCheckSize));
		if(childSize==0){
			if(selfCheckSize==0)
				return ;
			else {
				node.setText(node.getText()+"["+selfCheckSize+"]");
				return ;
			}
		}else if(selfCheckSize==0&&childCheckSize==0)
			return ;
		else{
			node.setText(node.getText()+"["+selfCheckSize+","+childCheckSize+"]");
		}
	}
}
