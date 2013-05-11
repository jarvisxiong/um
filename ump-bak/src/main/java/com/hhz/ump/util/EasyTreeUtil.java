/**
 * 
 */
package com.hhz.ump.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author huangj 2010-11-16
 */
public class EasyTreeUtil {

	public static String ROOT_ID = "0";
	public static String POWERLONG_NAME = "宝龙集团发展有限公司";

	//非常好用
	private static void addTreeNode(EasyTree treeRoot, EasyTree treeChild,
			String parentId) {
		if (StringUtils.isEmpty(parentId) || treeRoot.getId().equals(parentId)) {
			//如果节点已经存在，退出
			for(EasyTree child:treeRoot.getChildren()){
				if(child.getId().equals(treeChild.getId()))
					return;
			}
			treeRoot.addChild(treeChild);
			System.out.println(parentId);
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
	 * 角色树(无根 带复选框)
	 */
	public static EasyTree createAppRoleTree(List<WsPlasRole> roleList,List<WsPlasRole> checkedRoles) {
		EasyTree treeRoot = new EasyTree(ROOT_ID,"角色");

		for (WsPlasRole appRole : roleList) {
			EasyTree roleNode = new EasyTree();
			roleNode.setId(appRole.getRoleCd());
			String text = appRole.getRoleName();
			Map<String,String> roleMap = new HashMap<String,String>();
			roleMap.put("nodeType", "role");
			roleMap.put("cd", appRole.getRoleCd());
			roleNode.setAttributes(roleMap);
			roleNode.setText(text);
			roleNode.setIconCls("icon-role");
			if(null!=checkedRoles&&checkedRoles.size()>0){
				boolean isExist = checkedRoles.contains(appRole);
				roleNode.setChecked(isExist);
			}
			treeRoot.addChild(roleNode);
		}
		return treeRoot;
	}
	/**
	 * 
	 * Description:构建模块菜单树（带复选框）
	 * author:jiaoxiaofeng  2011-3-30
	 * @param moduleMenus
	 * @param checks
	 * @return
	 * EasyTree
	 */
	public static EasyTree createAppModuleMenuTree(List<AppModuleMenuRel> moduleMenus,List<AppModuleMenuRel> checks){
		EasyTree treeRoot = new EasyTree(ROOT_ID,"模块");
		Map<String,String> rootMap = new HashMap<String,String>();
		rootMap.put("nodeType", "root");
		rootMap.put("cd", "");
		treeRoot.setAttributes(rootMap);
		
		for (AppModuleMenuRel moduleMenu : moduleMenus) {
			EasyTree moduleNode = new EasyTree();
			Map<String, String> moduleMap = new HashMap<String, String>();
			moduleMap.put("nodeType", "module");
			moduleMap.put("cd", moduleMenu.getAppModule().getModuleCd());
			moduleNode.setAttributes(moduleMap);
			moduleNode.setId(moduleMenu.getAppModule().getAppModuleId());
			moduleNode.setText(moduleMenu.getAppModule().getModuleName());
			addTreeNode(treeRoot, moduleNode, ROOT_ID);
			
			EasyTree menuNode = new EasyTree();
			Map<String, String> menuMap = new HashMap<String, String>();
			menuMap.put("nodeType", "menu");
			menuMap.put("cd", moduleMenu.getAppMenu().getMenuCd());
			menuNode.setAttributes(menuMap);
			menuNode.setId(moduleMenu.getAppMenu().getAppMenuId());
			menuNode.setText(moduleMenu.getAppMenu().getMenuName());
			if(null!=checks &&checks.size()>0){
				if(checks.contains(moduleMenu)){
					menuNode.setChecked(true);
				}
			}
			
			addTreeNode(treeRoot, menuNode, moduleMenu.getAppModule().getAppModuleId());
	
		}
		return treeRoot;
	}

	public static EasyTree createModulePageFuncTree(List<AppModuleMenuRel> appModuleMenuList){
		EasyTree treeRoot = new EasyTree(ROOT_ID,"模块功能树");
		Map<String,String> rootMap = new HashMap<String,String>();
		rootMap.put("nodeType", "root");
		treeRoot.setAttributes(rootMap);
		
		List<String> temp = new ArrayList<String>();
		for(AppModuleMenuRel moduleMenu : appModuleMenuList){
			EasyTree moduleNode = new EasyTree();
			Map<String, String> moduleMap = new HashMap<String, String>();
			moduleMap.put("nodeType", "module");
			moduleMap.put("cd", moduleMenu.getAppModule().getModuleCd());
			moduleNode.setAttributes(moduleMap);
			moduleNode.setId(moduleMenu.getAppModule().getAppModuleId());
			moduleNode.setText(moduleMenu.getAppModule().getModuleName());
			addTreeNode(treeRoot, moduleNode, ROOT_ID);
			
			EasyTree menuNode = new EasyTree();
			Map<String, String> menuMap = new HashMap<String, String>();
			menuMap.put("nodeType", "menu");
			menuMap.put("cd", moduleMenu.getAppMenu().getMenuCd());

			if(null!= moduleMenu.getAppMenu().getAppPage()) {
				menuMap.put("pageId", moduleMenu.getAppMenu().getAppPage().getAppPageId());
			}	else{
				menuMap.put("pageId","");
			}
			menuNode.setAttributes(menuMap);
			menuNode.setId(moduleMenu.getAppMenu().getAppMenuId());
			menuNode.setText(moduleMenu.getAppMenu().getMenuName());	
			
			if(null!=moduleMenu.getAppMenu().getAppPage()&&null!=moduleMenu.getAppMenu().getAppPage().getAppFunctions()){
				List<AppFunction> appFuncList = moduleMenu.getAppMenu().getAppPage().getAppFunctions();
				for(AppFunction func : appFuncList){
		
						
						EasyTree funcNode = new EasyTree();
						funcNode.setId(func.getAppFunctionId());
						funcNode.setText(func.getFunctionName());
						Map<String,String> funcMap = new HashMap<String,String>();
						funcMap.put("nodeType", "func");
						funcNode.setAttributes(funcMap);
						menuNode.addChild(funcNode);
					
				}
			}
			addTreeNode(treeRoot, menuNode, moduleMenu.getAppModule().getAppModuleId());
		}
		return treeRoot;
	}
	public static EasyTree createModuleMenuPageTree(){
		AppModuleManager appModuleManager = SpringContextHolder.getBean("appModuleManager");
		AppModuleMenuRelManager appModuleMenuRelManager =  SpringContextHolder.getBean("appModuleMenuRelManager");
		AppPageManager appPageManager = SpringContextHolder.getBean("appPageManager");
		List<AppModule> modules = appModuleManager.getAll();
		Map<String,List<AppMenu>> menuMaps = appModuleMenuRelManager.getMenuMap();
		List<AppPage> pages = appPageManager.getAll();
		
		EasyTree treeRoot = new EasyTree();
		treeRoot.setId("0");
		treeRoot.setText("模块菜单页面树");
		Map<String,String> attributMap = null;
		attributMap = new HashMap<String,String>();
		attributMap.put("nodeType", "root");
		treeRoot.setIconCls("icon-root");
		
		Map<String,List<EasyTree>> menuPagsTree = getMenuPageTree(menuMaps,getPageTree(pages));
		treeRoot.setChildren(getModuleTree(modules,menuPagsTree));
		
		//处理无上级的page
		List<AppPage> tmpPages = appPageManager.getPageListNotInMenu();
		Map<String,String> 	attributsMap = new HashMap<String,String>();
		attributsMap.put("nodeType", "page");
		EasyTree tmp = null;
		for(AppPage page : tmpPages){
			tmp = new EasyTree(page.getAppPageId(),page.getPageName());
			tmp.setAttributes(attributsMap);
			treeRoot.addChild(tmp);
		}
		return treeRoot;
	}
	public static List<EasyTree> getModuleTree(List<AppModule> childs,Map<String,List<EasyTree>> menuPages){
		EasyTree moduleNode = null;
		Map<String,String>  tmpMap = new HashMap<String,String>();
		List<EasyTree> tmpTree = null;
		tmpMap.put("nodeType", "module");
		List<EasyTree> children = new ArrayList<EasyTree>();
		for(AppModule module : childs){
			moduleNode = new EasyTree(module.getAppModuleId(),module.getModuleName());
			moduleNode.setAttributes(tmpMap);
			tmpTree = menuPages.get(module.getAppModuleId());
			if(null!=tmpTree){
				moduleNode.setChildren(tmpTree);
				moduleNode.setIconCls("icon-folder");
			}
			
			children.add(moduleNode);
		}
		return children;
	}
	public static Map<String,List<EasyTree>> getMenuPageTree(Map<String,List<AppMenu>> menuMaps,Map<String,EasyTree> pages){
		List<EasyTree> tmpTrees = null;
		List<AppMenu> menus = null;
		Map<String,List<EasyTree>> result = new HashMap<String,List<EasyTree>>();
		Iterator i = menuMaps.entrySet().iterator();
		while(i.hasNext()){
			 Map.Entry<String,List<AppMenu>> en = (Map.Entry<String,List<AppMenu>>)i.next();
			 String key = en.getKey();
			 menus = en.getValue();
			 tmpTrees = getMenuTree(menus,pages);
			 result.put(key,tmpTrees);
		}
		return result;
	}
	public static List<EasyTree> getMenuTree(List<AppMenu> menus,Map<String,EasyTree> pages ){
		EasyTree tmpTree = null;
		AppPage tmpPage = null;
		List<EasyTree> result = new ArrayList<EasyTree>();
		Map<String,String> attributes =  new HashMap<String,String>();
		attributes.put("nodeType", "menu");
		for(AppMenu menu: menus ){
			tmpTree = new EasyTree(menu.getAppMenuId(),menu.getMenuName());
			tmpPage = menu.getAppPage();
			tmpTree.setAttributes(attributes);
			
			if(null == tmpPage) {
				continue;
			}
			tmpTree.addChild(pages.get(tmpPage.getAppPageId()));
			result.add(tmpTree);
		}
		return result;
	}
	public static Map<String,EasyTree> getPageTree(List<AppPage> pages){
		Map<String,EasyTree> result= new HashMap<String,EasyTree>();
		Map<String,String> 	attributsMap = new HashMap<String,String>();
		attributsMap.put("nodeType", "page");
		EasyTree tmp = null;
		for(AppPage page : pages){
			tmp = new EasyTree(page.getAppPageId(),page.getPageName());
		
			tmp.setAttributes(attributsMap);
			result.put(page.getAppPageId(), tmp);
			
		}
		return result;
	}
}
