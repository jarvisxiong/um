/**
 * 
 */
package com.hhz.uums.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppMenuManager;
import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.vo.vw.OrgTreeVo;
import com.hhz.uums.vo.vw.UserTreeVo;

/**
 * @author huangj 2010-11-16
 */
public class EasyTreeUtil {

	public static String ROOT_ORG_ID = "0";
	public static String POWERLONG_NAME = "宝龙集团发展有限公司";
	public static Map moduleMap = new HashMap();
	public static Map orgMap = new HashMap();
	public static Map rootMap = new HashMap();
	public static Map roleMap = new HashMap();
	static {
		moduleMap.put("nodeType", "module");
		orgMap.put("nodeType","org");
		rootMap.put("nodeType", "root");
		roleMap.put("nodeType", "role");
	}
	//根节点
	
	public static  EasyTree getRootNode(){
		EasyTree rootNode = new EasyTree();
		rootNode.setId(DictContants.ORG_ROOT_CD);
		rootNode.setText(GlobalConstants.UAAP_ORG_NAME);
		rootNode.setAttributes(roleMap);
		return rootNode;
	}
	/**
	 * 模块角色树(无复选框)
	 * 
	 * @param roleModules
	 * @return
	 */
	public static EasyTree createRoleTree(List<PlasRoleGroup> roleModules) {
		return createRoleTree(roleModules, null);
	}

	/**
	 * 模块角色树(带复选框)
	 * 
	 * @param roleModules
	 * @param checkedRoles
	 * @return
	 */
	public static EasyTree createRoleTree(List<PlasRoleGroup> roleModules,
			List<PlasRole> checkedRoles) {

		EasyTree treeRoot = new EasyTree(ROOT_ORG_ID, "角色树");
		treeRoot.setState(EasyTree.STATE_OPEN);
		Map<String, String> attrRootMap = new HashMap<String, String>();
		attrRootMap.put("nodeType", "root");
		treeRoot.setAttributes(attrRootMap);


		EasyTree moduleNode = null;
		Map<String, String> attrMapRole = new HashMap<String, String>();
		attrMapRole.put("nodeType", "role");
		EasyTree roleNode = null;

		for (PlasRoleGroup appRoleModule : roleModules) {

			moduleNode = new EasyTree(appRoleModule.getPlasRoleGroupId(),
					appRoleModule.getRoleGroupName(), moduleMap);
			moduleNode.setIconCls("icon-module");
			addTreeNode(treeRoot, moduleNode, appRoleModule.getParentId());

			for (PlasRole role : appRoleModule.getPlasRoles()) {
				
				attrMapRole.put("roleCd", role.getRoleCd());
				roleNode = new EasyTree(role.getPlasRoleId(), role
						.getRoleName(), attrMapRole);
				if (checkedRoles != null && checkedRoles.size() > 0) {
					boolean isExist = checkedRoles.contains(role);
					roleNode.setChecked(isExist);
				}
				roleNode.setIconCls("icon-role");
				moduleNode.addChild(roleNode);
			}
		}
		return treeRoot;
	}

	/**
	 * 模块菜单树(无复选框)
	 * 
	 * @param moduleList
	 * @return
	 */
	public static EasyTree createMenuTree() {
		return createMenuTree(null);
	}

	/**
	 * 模块菜单树(带复选框)
	 * 
	 * @param moduleList
	 * @param appMenus
	 * @return
	 */
	public static EasyTree createMenuTree(List<AppMenu> checks) {
		AppModuleManager appModuleManager = SpringContextHolder.getBean("appModuleManager");
		AppMenuManager appMenuManager = SpringContextHolder.getBean("appMenuManager");
		List<AppModule> moduleList = appModuleManager.getAll("sequenceNo", true);
		List<AppMenu> menuList = appMenuManager.getAll("sequenceNo",true);
		
		Map<String,List<AppMenu>> menuMap = new HashMap<String, List<AppMenu>>();
		List<AppMenu> temp = null;
		for(AppMenu obj : menuList){
			temp = menuMap.get(obj.getAppModule().getAppModuleId());
			if(null==temp){
				temp = new ArrayList<AppMenu>();
			}
			temp.add(obj);
			menuMap.put(obj.getAppModule().getAppModuleId(), temp);
		}
		
		EasyTree treeRoot = new EasyTree(ROOT_ORG_ID, "菜单树");
		treeRoot.setState(EasyTree.STATE_OPEN);
		Map<String, String> attrRootMap = new HashMap<String, String>();
		attrRootMap.put("nodeType", "root");
		treeRoot.setAttributes(attrRootMap);
		
		Map<String, String> attrMapMenu = null;
		for (AppModule appModule : moduleList) {

			EasyTree moduleNode = new EasyTree(appModule.getAppModuleId(),
					appModule.getModuleName(), moduleMap);
			moduleNode.setIconCls("icon-module");
			addTreeNode(treeRoot, moduleNode, appModule.getParentId());
			
			temp = menuMap.get(moduleNode.getId());
			if(null==temp) {
				continue;
			}
			for (AppMenu menu : temp) {
				attrMapMenu = new HashMap<String, String>();
				attrMapMenu.put("nodeType", "menu");
				attrMapMenu.put("menuCd", menu.getMenuCd());
				EasyTree menuNode = new EasyTree(menu.getAppMenuId(), menu
						.getMenuName(), attrMapMenu);
				if (checks != null ) {
					boolean isExist = checks.contains(menu);
					menuNode.setChecked(isExist);
				}
				menuNode.setIconCls("icon-menu");
				moduleNode.addChild(menuNode);
			}
		}
		return treeRoot;
	}

	/**
	 * 机构人员树(无复选框)
	 * 
	 * @param roleModules
	 * @return
	 */
	public static EasyTree createOrgUserTree(List<PlasDimeOrgRel> appOrgRels, String dimeTypeCd) {
		return createOrgUserTree(appOrgRels, dimeTypeCd);
	}

	/**
	 * 机构人员树(带复选框)
	 * 
	 * @param roleModules
	 * @param checkedRoles
	 * @return
	 */
	public static EasyTree createOrgUserTree(List<PlasDimeOrgRel> appOrgRels,
			List<PlasUser> usersChecked) {
		return createOrgUserTree(appOrgRels, usersChecked);
	}
	public static EasyTree createOrgUserTreePhysical(List<PlasUser> userChecked){
		return createOrgUserTree(DictContants.TREE_DIME_PHYSICAL,userChecked);
	}
	public static EasyTree creatOrgUserTreeLogical(List<PlasUser> userChecked){
		return createOrgUserTree(DictContants.TREE_DIME_LOGICAL,userChecked);
	}
	public static EasyTree createOrgUserTree(String dimeCd,List<PlasUser> userChecked) {

		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasUserManager plasUser = SpringContextHolder.getBean("plasUserManager");
		
		EasyTree rootNode = new EasyTree(ROOT_ORG_ID,POWERLONG_NAME);
		Map<String,String> rootMap = new HashMap<String, String>();
		rootMap.put("nodeType", "root");
		rootNode.setAttributes(rootMap);
		
		List<OrgTreeVo> orgs = plasDimeOrgRel.getOrgTreeVoList(dimeCd);
		Map<String,List<UserTreeVo>> users = plasUser.getAlls();
		//机构--人员


		rootNode = setOrgUserTree(rootNode,orgs,users,userChecked);
		

		return rootNode;
	}
	
	public static EasyTree setOrgUserTree(EasyTree node,List<OrgTreeVo> orgs,Map<String,List<UserTreeVo>> users,List<PlasUser> checkList){
		Map<String,String> orgMap = null;
		EasyTree orgNode = null;
		
		List<UserTreeVo> tmpUsers = null;
		EasyTree userNode= null;
		Map<String,String> userMap =null;
		
		for(OrgTreeVo obj : orgs){
			orgNode = new EasyTree(obj.getOrgId(),obj.getOrgName());
/*			orgMap = new HashMap<String,String>();
			orgMap.put("nodeType", "org");
			orgNode.setIconCls("icon-module");
			orgNode.setAttributes(orgMap);*/
			
			if(null==obj.getParentId()||"".equals(obj.getParentId())){
				obj.setParentId(ROOT_ORG_ID);
			}
			//插入用户
			tmpUsers = users.get(obj.getOrgId());
			if(tmpUsers!=null&&tmpUsers.size()>0){
				for(UserTreeVo user:tmpUsers){
					userNode = new EasyTree(user.getPlasUserId(),user.getUserName());
/*					userMap = new HashMap<String,String>();
					userMap.put("nodeType", "user");
					userNode.setAttributes(userMap);*/
					orgNode.addChild(userNode);
				}
			}
				
			
			
			addTreeNode(node, orgNode, obj.getParentId());
		}
		return node;
	}
	/**
	 * 
	 * Description:加载机构职位树：（默认逻辑机构，无复选框）
	 * author:jiaoxiaofeng  2011-4-15
	 * @return
	 * EasyTree
	 */
	public static EasyTree createOrgPosition(){
		return creatOrgPosition(null);
	}
	/**
	 * 
	 * Description:加载机构职位树：（默认逻辑机构，有复选框）
	 * author:jiaoxiaofeng  2011-4-15
	 * @return
	 * EasyTree
	 */
	public static EasyTree createOrgPositionCheck(){
		PlasSysPositionManager plasSysManager = SpringContextHolder.getBean("plasSysPositionManager");
		return creatOrgPosition(plasSysManager.getAll());
	}
	public static EasyTree creatOrgPosition(List<PlasSysPosition> checkList){
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		PlasSysPositionManager plasSysManager = SpringContextHolder.getBean("plasSysPositionManager");
		
		EasyTree rootNode = new EasyTree(ROOT_ORG_ID,POWERLONG_NAME);
		Map<String,String> rootMap = new HashMap<String, String>();
		rootMap.put("nodeType", "root");
		rootNode.setAttributes(rootMap);
		
		List<OrgTreeVo> orgs = plasDimeOrgRel.getOrgTreeVoList(DictContants.TREE_DIME_LOGICAL);
		List<PlasSysPosition> sysPos = plasSysManager.getAll();
		//先机构
		rootNode = setOrgTree(rootNode,orgs);
		//后人员系统职位
		rootNode = setOrgSysTree(rootNode,sysPos,checkList);
		
		return rootNode;
	}
	private static List<EasyTree> getChildrenNode(EasyTree treeNode,
			Map<String, List<OrgTreeVo>> orgOrgsMap
			) {
 
		List<EasyTree> children = new ArrayList<EasyTree>();
		String parentId = treeNode.getId();

		EasyTree orgNode = null;
		// 后机构
		List<OrgTreeVo> orgs = orgOrgsMap.get(parentId);// 根据机构CD获取
		if (orgs != null && orgs.size() > 0) {
			for (OrgTreeVo obj : orgs) {
				orgNode = new EasyTree();
				orgNode.setId(obj.getOrgId());
				orgNode.setText(obj.getOrgName());				
				orgNode.setIconCls("icon-module");
				orgNode.setAttributes(orgMap);
				
				if(null==obj.getParentId()||"".equals(obj.getParentId())){
					obj.setParentId(DictContants.ORG_ROOT_CD);
				}
				orgNode.setChildren(getChildrenNode( orgNode, orgOrgsMap));
				children.add(orgNode);
			}
		}
		return children;
	}
	public static EasyTree setOrgTree(EasyTree node,List<OrgTreeVo> orgs){
		Map<String,String> orgMap = null;
		EasyTree orgNode = null;
		for(OrgTreeVo obj : orgs){
			orgNode = new EasyTree();
			orgNode.setId(obj.getOrgId());
			orgNode.setText(obj.getOrgName());
			orgMap = new HashMap<String,String>();
			orgMap.put("nodeType", "org");
			orgNode.setIconCls("icon-module");
			orgNode.setAttributes(orgMap);
			
			if(null==obj.getParentId()||"".equals(obj.getParentId())){
				obj.setParentId(DictContants.ORG_ROOT_CD);
			}
			addTreeNode(node, orgNode, obj.getParentId());
		}
		return node;
	}
	public static EasyTree setOrgSysTree(EasyTree node,List<PlasSysPosition> sys,List<PlasSysPosition> checkList){
		Map<String,String> positionMap = null;
		EasyTree positionNode = null;
		String parentId = "";
		for(PlasSysPosition obj:sys){
			positionNode = new EasyTree(obj.getPlasSysPositionId(),obj.getSysPosName());
			positionMap = new HashMap<String,String>();
			positionMap.put("nodeType", "position");
			positionNode.setIconCls("icon-role");
			positionNode.setAttributes(positionMap);
			if(null!=checkList&&checkList.size()>0){
				if(checkList.contains(obj)){
					positionNode.setChecked(true);
				}
			}
			addTreeNode(node, positionNode, obj.getPlasOrg().getPlasOrgId());
		}
		return node;
	}
	

	/**
	 * 物理机构树(有根)
	 * @param plasOrgList
	 * @return
	 */
	public static EasyTree createOrgTreePhysical() {
		return createOrgTree(DictContants.TREE_DIME_PHYSICAL);
	}
	
	/**
	 * 逻辑机构树(有根)
	 * @param plasOrgList
	 * @return
	 */
	public static EasyTree createOrgTreeLogical() {
		return createOrgTree(DictContants.TREE_DIME_LOGICAL);
	}

	public static EasyTree createOrgTree(String dimeCd){
		PlasDimeOrgRelManager plasDimeOrgRel = SpringContextHolder.getBean("plasDimeOrgRelManager");
		
		String rootTitle = POWERLONG_NAME;
		if(DictContants.TREE_DIME_PHYSICAL.equals(dimeCd)){
			rootTitle = "物理机构树";
		}
		else if(DictContants.TREE_DIME_LOGICAL.equals(dimeCd)){
			rootTitle = "逻辑机构树";
		}
		
		EasyTree treeRoot = new EasyTree(DictContants.ORG_ROOT_CD, rootTitle);
		treeRoot.setState(EasyTree.STATE_OPEN);
		Map<String, String> attrRootMap = new HashMap<String, String>();
		attrRootMap.put("nodeType", "root");
		treeRoot.setAttributes(attrRootMap);
		treeRoot.setIconCls("icon-root");

		Map<String,List<OrgTreeVo>> orgs = plasDimeOrgRel.getOrgTreeVoMap(dimeCd);
		
		// 设置子孙节点
		treeRoot.setChildren(getChildrenNode(treeRoot, orgs));
		
	/*	treeRoot = setOrgTree(treeRoot,orgs);*/
		return treeRoot;
	}

	/**
	 * 用户树(无复选框)
	 */
	public static EasyTree createUserTree(List<PlasUser> userList) {
		return createUserTree(userList, null);
	}

	/**
	 * 用户树(带复选框)
	 */
	public static EasyTree createUserTree(List<PlasUser> userList,List<PlasUser> checkedUsers) {
		EasyTree treeRoot = new EasyTree(ROOT_ORG_ID, "用户树");
		treeRoot.setState(EasyTree.STATE_OPEN);
		for (PlasUser appUser : userList) {
			// 若已经删除,继续
			if (appUser.getActiveBl()) {
				continue;
			}
			String text = appUser.getUserName() + "(" + appUser.getUiid() + ")";
			EasyTree userNode = new EasyTree(appUser.getPlasUserId(), text);
			if (checkedUsers != null && checkedUsers.size() > 0) {
				boolean isExist = checkedUsers.contains(appUser);
				userNode.setChecked(isExist);
			}
			addTreeNode(treeRoot, userNode, null);
		}
		return treeRoot;
	}
	//非常好用
	private static void addTreeNode(EasyTree treeRoot, EasyTree treeChild,
			String parentId) {
		if (StringUtils.isEmpty(parentId) || treeRoot.getId().equals(parentId)) {
			treeRoot.addChild(treeChild);
		} else {
			addTreeNode(treeChild, treeRoot.getChildren(), parentId);
		}
	}

	/**
	 * 增加树节点
	 * 
	 * @param treeChild
	 * @param list
	 * @param parentId
	 */
	private static void addTreeNode(EasyTree treeChild, List<EasyTree> list,
			String parentId) {
		boolean found = false;
		for (EasyTree easyTree : list) {
			if(null==easyTree.getId()){
				//插入日志 数据异常：账号没有Id 

				continue;
			}
			if (easyTree.getId().equals(parentId)) {
				easyTree.addChild(treeChild);
				found = true;
				break;
			}
		}
		if (!found) {
			for (EasyTree easyTree : list) {
				addTreeNode(treeChild, easyTree.getChildren(), parentId);
			}
		}
	}
	/**
	 * 
	 * author:jiaoxiaofeng  2011-2-10
	 * Descrption：应用角色树
	 * @param plasApps
	 * @param roleModules
	 * @return
	 * EasyTree
	 */
	public static EasyTree createRoleGroupTree(List<PlasRole> checkList) {
		return createRoleGroupTree(false,null,checkList);
	}
	public static EasyTree createRoleGroupTree( ) {
		return createRoleGroupTree(true,null,null);
	}

	/**
	 * Descrption：应用于角色关系树
	 */
	public static EasyTree createRoleGroupTree(boolean isAll,String appBizCd,List<PlasRole> checkList) {
		PlasRoleGroupManager plasRoleGroupManager = SpringContextHolder.getBean("plasRoleGroupManager");
		PlasAppManager plasAppManager = SpringContextHolder.getBean("plasAppManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");

		EasyTree rootNode = getRootNode();

		List<PlasApp> appList = null;
		List<PlasRoleGroup> roleGroupList =plasRoleGroupManager.getAll("roleGroupName",true);
		Map<String,List<PlasRole>> roles = plasRoleManager.getAlls();
		if(isAll){
			appList = plasAppManager.getAll();
			
			//1应用
			rootNode = setAppTree(rootNode,appList);
		}else {
			rootNode = new EasyTree();
			getAppNode(rootNode,plasAppManager.getEntityByAppBizCd(appBizCd));
		}
		
		//2角色组-角色
		rootNode = setRoleGroupTree(rootNode,roleGroupList,roles,checkList);
		return rootNode;
	}
	/**
	 * 获取角色节点
	 */
	public static EasyTree getRoleNode(PlasRole role,EasyTree roleNode,Map<String,String> roleMap){
		roleMap = new HashMap<String, String>();
		roleMap.put("nodeType", "role");
		roleMap.put("roleCd", role.getRoleCd());
		roleNode = new EasyTree(role.getPlasRoleId(), role
				.getRoleName(), roleMap);
		roleNode.setIconCls("icon-role");
		return roleNode;
	}
	/**
	构建应用树形目录
 */
	public static EasyTree setAppTree(EasyTree node,List<PlasApp> appList){
		EasyTree appNode = null;
		for(PlasApp obj : appList){
			appNode = new EasyTree();
			getAppNode(appNode,obj);
			addTreeNode(node, appNode, DictContants.ORG_ROOT_CD);
		}
		return node;
	}
	public static void getAppNode(EasyTree appNode,PlasApp obj){
		Map<String, String> attrMapApp = new HashMap<String, String>();
		attrMapApp.put("nodeType", "app");
		appNode.setIconCls("icon-folder");
		appNode.setId(obj.getPlasAppId());
		appNode.setText(obj.getAppChnName());
		appNode.setAttributes(attrMapApp);
	}
	public static EasyTree setRoleGroupTree(EasyTree node,List<PlasRoleGroup> groupList,Map<String,List<PlasRole>> roles,List<PlasRole> checkList){
		boolean checked;
		if(null==checkList) {
			checked = false;
		} else {
			checked = true;
		}
		List<PlasRole> tmp = null;
		EasyTree roleGroupNode = null;
		for(PlasRoleGroup obj : groupList){
			roleGroupNode = getRoleGroupNode(obj,roleGroupNode);
			
			//加载子节点 角色

			tmp = roles.get(roleGroupNode.getId());
			setRoleTree(roleGroupNode,tmp,checkList);
			
			
			addTreeNode(node, roleGroupNode, obj.getParentId());
		}
		return node;
	}
	public static EasyTree getRoleGroupNode(PlasRoleGroup obj,EasyTree roleGroupNode){
		roleGroupNode = new EasyTree();
		roleGroupNode.setId(obj.getPlasRoleGroupId());
		roleGroupNode.setText(obj.getRoleGroupName());
		roleGroupNode.setIconCls("icon-module");
		roleGroupNode.setAttributes(moduleMap);
		return roleGroupNode;
	}
	/**
	 * 
	 * Description:构建角色树
	 */
	public static EasyTree setRoleTree(EasyTree node,List<PlasRole> roleList,List<PlasRole> checkList){
		int num = 0;
		if(roleList != null){
			EasyTree roleNode = null;
			for(PlasRole obj : roleList){
				roleNode = getRoleNode(obj,roleNode);
				if(null!=checkList){
					if(checkList.contains(obj)) {
						roleNode.setChecked(true);
						num++;
					}
				}
				node.addChild(roleNode);
			}
		}
		return node;
	}
	/**
	 * 构建角色节点
	 */
	public static EasyTree getRoleNode(PlasRole obj,EasyTree roleNode){
		roleNode = new EasyTree(obj.getPlasRoleId(), obj
				.getRoleName(), roleMap);
		roleNode.setIconCls("icon-role");
		return roleNode;
	}
}
