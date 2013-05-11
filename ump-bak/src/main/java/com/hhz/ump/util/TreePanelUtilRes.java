package com.hhz.ump.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.xwork.StringUtils;

import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.entity.res.ResTypeUserRel;

public class TreePanelUtilRes {

	// 内容
	public static String NODE_RES_MODULE = "module";
	public static String NODE_RES_AUTH = "authType";

	public static TreePanelNode buildProjectModuleTree(List<ResModule> moduleList, List<ResAuthType> authList, String rootTitle) {
		return buildProjectModuleTree(moduleList, authList, rootTitle, TreePanelUtil.NODE_CHECKED_UNDEFINED, null);
	}

	public static TreePanelNode buildProjectModuleTree(List<ResModule> moduleList, List<ResAuthType> authList, String rootTitle, String checked,
			Map<String, String> mapCd2Id) {

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText(rootTitle);
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setEntityCd("0");// 特殊处理
		rootNode.setOrgOrUser(NODE_RES_MODULE);
		rootNode.setNodeType(NODE_RES_MODULE);

		// 模块与分类
		Map<String, List<ResAuthType>> moduleAuthsMap = getModuleAuthsMap(authList);

		// 模块与模块关系
		Map<String, List<ResModule>> moduleModulesMap = getModuleModulesMap(moduleList);

		// 设置子孙节点
		rootNode.setChildren(getChildrenNode(rootNode, moduleModulesMap, moduleAuthsMap, checked, mapCd2Id));
		if (mapCd2Id!=null){
			refreshChecked(rootNode);
		}
		return rootNode;
	}

	private static Object[] refreshChecked(TreePanelNode rootNode) {
		
		if (rootNode.getNodeType().equals(NODE_RES_AUTH))
			return new Object[]{rootNode.getChecked(),false};
		String checkType = rootNode.getChecked();
		int index=0;
		boolean isLeafModule_r=true;
		for (Iterator<TreePanelNode> it=rootNode.getChildren().iterator();it.hasNext();) {
			boolean isLeafModule=true;
			TreePanelNode node =it.next();
			if (node.getText().equals("合同审批")){
				node.getText();
			}
			Object[] rtn= refreshChecked(node);
			String childCheckType =(String)rtn[0];
			boolean isLeafModule_c=(Boolean)rtn[1];
			if(isLeafModule){
				if (!isLeafModule_c){
					isLeafModule=false;
				}
			}
			if(isLeafModule_r){
				if (!isLeafModule){
					isLeafModule_r=false;
				}
			}
			
			
			if (checkType.equals(TreePanelUtil.NODE_CHECKED_CHECKED)) {
				if (childCheckType.equals(TreePanelUtil.NODE_CHECKED_HALF)||childCheckType.equals(TreePanelUtil.NODE_CHECKED_NONE)) {
					checkType = TreePanelUtil.NODE_CHECKED_HALF;
				}
			} else if (checkType.equals(TreePanelUtil.NODE_CHECKED_NONE)) {
				if (index==0){
					checkType = childCheckType;
				}else if (!childCheckType.equals(TreePanelUtil.NODE_CHECKED_NONE)){
					checkType = TreePanelUtil.NODE_CHECKED_HALF;
				}
			}else if (checkType.equals(TreePanelUtil.NODE_CHECKED_HALF)) {
				checkType = TreePanelUtil.NODE_CHECKED_HALF;
			}
			index++;
			if (isLeafModule){
				it.remove();
			}
		}
		
		rootNode.setChecked(checkType);
		return new Object[]{checkType,isLeafModule_r};
	}

	// 遍历人员,设置模块与分类关系
	private static Map<String, List<ResAuthType>> getModuleAuthsMap(List<ResAuthType> auths) {

		Map<String, List<ResAuthType>> orgUsersMap = new HashMap<String, List<ResAuthType>>();
		for (ResAuthType auth : auths) {
			String parentId = auth.getResModule().getResModuleId();

			if (StringUtils.isNotBlank(parentId)) {
				if (orgUsersMap.containsKey(parentId)) {
					orgUsersMap.get(parentId).add(auth);
				} else {
					List<ResAuthType> newUserList = new ArrayList<ResAuthType>();
					newUserList.add(auth);
					orgUsersMap.put(parentId, newUserList);
				}
			}
		}
		return orgUsersMap;
	}

	// 遍历机构,设置模块与模块关系
	private static Map<String, List<ResModule>> getModuleModulesMap(List<ResModule> modules) {

		Map<String, List<ResModule>> moduleModulesMap = new HashMap<String, List<ResModule>>();
		for (ResModule module : modules) {
			String parentCd = module.getParentModuleCd();
			if (StringUtils.isNotBlank(parentCd)) {
				if (moduleModulesMap.containsKey(parentCd)) {
					moduleModulesMap.get(parentCd).add(module);
				} else {
					List<ResModule> newModuleList = new ArrayList<ResModule>();
					newModuleList.add(module);
					moduleModulesMap.put(parentCd, newModuleList);
				}
			}
		}
		return moduleModulesMap;
	}

	private static ResTypeUserRel getTypeFromRel(List<ResTypeUserRel> authTypes, String authTypeCd) {
		ResTypeUserRel relResult = null;
		for (ResTypeUserRel rel : authTypes) {
			if (rel.getAuthTypeCd().equals(authTypeCd)) {
				relResult = rel;
				break;
			}
		}
		return relResult;
	}

	private static List<TreePanelNode> getChildrenNode(TreePanelNode treeNode, Map<String, List<ResModule>> moduleModulesMap,
			Map<String, List<ResAuthType>> moduleAuthsMap, String checked, Map<String, String> mapCd2Id) {
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();
		String parentCd = treeNode.getEntityCd();
		// 先分类
		List<ResAuthType> auths = moduleAuthsMap.get(parentId);
		if (auths != null && auths.size() > 0) {
			for (ResAuthType auth : auths) {
				TreePanelNode authNodes = getTreePanelAuthNoChild(auth, checked);
				if (mapCd2Id != null && mapCd2Id.size() > 0) {
					String relId = mapCd2Id.get(auth.getAuthTypeCd());
					if (relId != null) {
						authNodes.setChecked(TreePanelUtil.NODE_CHECKED_CHECKED);
						authNodes.setEntityId(relId);
					}
				}
				children.add(authNodes);
			}
		}else{
			
		}
		// 后模块
		List<ResModule> modules = moduleModulesMap.get(parentCd);
		if (modules != null && modules.size() > 0) {
			for (ResModule module : modules) {
				TreePanelNode moduleNode = getTreePanelModuleNoChild(module, checked);
				moduleNode.setChildren(getChildrenNode(moduleNode, moduleModulesMap, moduleAuthsMap, checked, mapCd2Id));
				children.add(moduleNode);
			}
		}
		return children;
	}

	private static TreePanelNode getTreePanelAuthNoChild(ResAuthType auth, String checked) {

		TreePanelNode userNode = new TreePanelNode();
		userNode.setId(auth.getResAuthTypeId());
		userNode.setText(auth.getAuthTypeName());
		userNode.setEntityCd(auth.getAuthTypeCd());
		userNode.setEntityName(auth.getDisplayName());
		userNode.setOrgOrUser(NODE_RES_AUTH);
		userNode.setNodeType(NODE_RES_AUTH);
		userNode.setChecked(checked);
		// TODO 如果当前用户不能发起该表单，就将此属性设置成false
		userNode.setEntityStatusCd(BooleanUtils.toStringTrueFalse(auth.getPublish()));//
		// 表单是否上线
		if (null == auth.getPublish()) {
			userNode.setExtParam("0");
		} else {
			userNode.setExtParam(BooleanUtils.toString(auth.getPublish(), "1", "0"));
		}
		return userNode;
	}

	private static TreePanelNode getTreePanelModuleNoChild(ResModule module, String checked) {
		TreePanelNode node = new TreePanelNode();
		node.setId(module.getResModuleId());
		node.setText(module.getModuleName());
		node.setEntityCd(module.getModuleCd());
		node.setEntityName(module.getModuleName());
		node.setChecked(checked);
		node.setOrgOrUser(NODE_RES_MODULE);
		node.setNodeType(NODE_RES_MODULE);
		return node;
	}
}
