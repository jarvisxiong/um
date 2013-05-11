package com.hhz.ump.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppModuleMenuRel;

public class TreePanelUtilMenu {

	// 内容
	public static String NODE_RES_MODULE = "module";
	public static String NODE_RES_MENU = "menu";

	
	
	//菜单前缀
	public static String PRE_MENU = "PRE_MENU_";
	
	public static TreePanelNode buildProjectModuleTree(List<AppModule> moduleList, List<AppModuleMenuRel> rels, List<AppMenu> menuList) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("模块和菜单关系");
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setEntityCd("0");// 特殊处理
		rootNode.setOrgOrUser(NODE_RES_MODULE);
		rootNode.setNodeType(NODE_RES_MODULE);

		// 模块与菜单
		Map<String, List<AppMenu>> moduleMenusMap = getModuleMenusMap(rels);

		// 菜单与菜单关系
		Map<String, List<AppMenu>> menuMenusMap = getMenuMenusMap(menuList);

		// 设置子孙节点
		rootNode.setChildren(getChildrenNode(rootNode, menuMenusMap, moduleMenusMap));

		return rootNode;
	}

	// 遍历人员,设置模块与模块关系
	public static Map<String, List<AppMenu>> getModuleModulesMap(List<AppModuleMenuRel> rels) {

		AppMenu   menu = null;
		Map<String, List<AppMenu>> moduleMenus = new HashMap<String, List<AppMenu>>();
		for (AppModuleMenuRel rel : rels) {
			menu = rel.getAppMenu();
			String moduleId = rel.getAppModule().getAppModuleId();
			
			if (StringUtils.isNotBlank(moduleId) && StringUtils.isBlank(menu.getParentMenuCd())) {
				if (moduleMenus.containsKey(moduleId)) {
					moduleMenus.get(moduleId).add(rel.getAppMenu());
				} else {
					List<AppMenu> newMenuList = new ArrayList<AppMenu>();
					newMenuList.add(rel.getAppMenu());
					moduleMenus.put(moduleId, newMenuList);
				}
			}
		}
		return moduleMenus;
	}
	// 遍历人员,设置模块与菜单关系
	public static Map<String, List<AppMenu>> getModuleMenusMap(List<AppModuleMenuRel> rels) {

		AppMenu   menu = null;
		Map<String, List<AppMenu>> moduleMenus = new HashMap<String, List<AppMenu>>();
		for (AppModuleMenuRel rel : rels) {
			menu = rel.getAppMenu();
			String moduleId = rel.getAppModule().getAppModuleId();
			
			if (StringUtils.isNotBlank(moduleId) && StringUtils.isBlank(menu.getParentMenuCd())) {
				if (moduleMenus.containsKey(moduleId)) {
					moduleMenus.get(moduleId).add(rel.getAppMenu());
				} else {
					List<AppMenu> newMenuList = new ArrayList<AppMenu>();
					newMenuList.add(rel.getAppMenu());
					moduleMenus.put(moduleId, newMenuList);
				}
			}
		}
		return moduleMenus;
	}

	// 遍历机构,设置菜单与菜单关系
	public static Map<String, List<AppMenu>> getMenuMenusMap(List<AppMenu> menus) {

		Map<String, List<AppMenu>> menuMenusMap = new HashMap<String, List<AppMenu>>();
		for (AppMenu menu : menus) {
			String parentMenuCd = menu.getParentMenuCd();
			if (StringUtils.isNotBlank(parentMenuCd)) {
				if (menuMenusMap.containsKey(parentMenuCd)) {
					menuMenusMap.get(parentMenuCd).add(menu);
				} else {
					List<AppMenu> newMenuList = new ArrayList<AppMenu>();
					newMenuList.add(menu);
					menuMenusMap.put(parentMenuCd, newMenuList);
				}
			}
		}
		return menuMenusMap;
	}
	private static List<TreePanelNode> getChildrenNode(TreePanelNode treeNode,
			Map<String, List<AppMenu>> moduleMenusMap, Map<String, List<AppMenu>> menuMenusMap) {

		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();
		String parentCd = treeNode.getEntityCd();
		
		

		// 模块菜单
		List<AppMenu> menus = moduleMenusMap.get(parentId);
		if (menus != null && menus.size() > 0) {
			for (AppMenu menu : menus) {
				children.add(getTreePanelMenuNoChild(menu));
			}
		}
		// 菜单菜单
		List<AppMenu> menus2 = menuMenusMap.get(parentCd);
		if (menus2 != null && menus2.size() > 0) {
			for (AppMenu menu : menus2) {
				TreePanelNode menuNode = getTreePanelMenuNoChild(menu);
				menuNode.setChildren(getChildrenNode(menuNode, menuMenusMap, moduleMenusMap));
				children.add(menuNode);
			}
		}


		return children;
	}


	public static TreePanelNode getTreePanelMenuNoChild(AppMenu menu) {

		TreePanelNode userNode = new TreePanelNode();
		userNode.setId(menu.getAppMenuId());
		userNode.setText(menu.getMenuName());
		userNode.setEntityId(menu.getAppMenuId());
		userNode.setEntityCd(menu.getMenuTypeCd());
		userNode.setEntityBizCd(menu.getMenuTypeCd());
		userNode.setEntityName(menu.getMenuName());
		userNode.setOrgOrUser(NODE_RES_MENU);
		userNode.setNodeType(NODE_RES_MENU);
		return userNode;
	}

	public static TreePanelNode getTreePanelModuleNoChild(AppModule module) {
		TreePanelNode node = new TreePanelNode();
		node.setId(module.getAppModuleId());
		node.setText(module.getModuleName());
		node.setEntityId(module.getAppModuleId());
		node.setEntityCd(module.getModuleCd());
		node.setEntityBizCd(module.getModuleCd());
		node.setEntityName(module.getModuleName());
		node.setOrgOrUser(NODE_RES_MODULE);
		node.setNodeType(NODE_RES_MODULE);
		return node;
	}
}
