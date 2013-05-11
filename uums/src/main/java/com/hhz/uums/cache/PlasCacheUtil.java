package com.hhz.uums.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;

import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.vo.ws.WsPlasOrg;
import com.hhz.uums.vo.ws.WsPlasSysPosition;
import com.hhz.uums.vo.ws.WsPlasUser;


public class PlasCacheUtil {

	// 节点状态 : 9-根节点 1-机构 0-用户
	public static String NODE_TYPE_ROOT = "9";
	public static String NODE_TYPE_ORG = "1";
	public static String NODE_TYPE_USER = "0";
	public static String NODE_TYPE_ACCT = "0";
	
	//常量
	public static String DEFAULT_ROOT_ORG_ID = "0"; 
	public static String DEFAULT_ROOT_ORG_CD = "0"; 
	public static String DEFAULT_ROOT_ORG_NAME = "根机构"; 

	// 选择状态 : 0-不选中 1-选中 2-半选
	public static String NODE_CHECKED_UNDEFINED = "undefined";
	public static String NODE_CHECKED_NONE = "0";
	public static String NODE_CHECKED_CHECKED = "1";
	public static String NODE_CHECKED_HALF = "2";

	// 逻辑物理视图1-物理 2-逻辑
	public static String TREE_TYPE_PHYSICAL = "1";
	public static String TREE_TYPE_LOGICAL = "2";
	
	/**
	 * 遍历人员,获取机构与人员关系
	 * 
	 * @param userList
	 * @return Map<String, List<WsPlasUser>>{orgId,wsUserList}
	 */
	public static Map<String, List<WsPlasUser>> getOrgUsersMap(List<WsPlasUser> userList) {
		Map<String, List<WsPlasUser>> orgUsersMap = new HashMap<String, List<WsPlasUser>>();
		String tmpOrgId = null;
		for (WsPlasUser user : userList) {
			tmpOrgId = user.getOrgId();
			if(StringUtils.isBlank(tmpOrgId)){
				System.out.println("未找到上级机构! " + user.getUserName()+"["+user.getUiid()+"]");
			}
			else{
				if (orgUsersMap.containsKey(tmpOrgId)) {
					orgUsersMap.get(tmpOrgId).add(user);
				} else {
					List<WsPlasUser> newUserList = new ArrayList<WsPlasUser>();
					newUserList.add(user);
					orgUsersMap.put(tmpOrgId, newUserList);
				}
			}
		}
		return orgUsersMap;
	}
	/**
	 * 遍历账号,设置机构与账号关系
	 * 
	 * @param acctList
	 * @return Map<String, List<WsPlasAcct>>{orgId, wsAcctList}
	 */
	public static Map<String, List<WsPlasAcct>> getOrgAcctsMap(List<WsPlasAcct> acctList) {

		Map<String, List<WsPlasAcct>> orgAcctsMap = new HashMap<String, List<WsPlasAcct>>();
		String orgId = null;
		
		for (WsPlasAcct acct : acctList) {
			orgId = acct.getOrgId();
			if(StringUtils.isBlank(orgId)){
				System.out.println("未找到上级机构! " + acct.getUserName()+"["+acct.getUiid()+"]");
				orgId = DEFAULT_ROOT_ORG_ID;
			}
			
			if (orgAcctsMap.containsKey(orgId)) {
				orgAcctsMap.get(orgId).add(acct);
			} else {
				List<WsPlasAcct> newUserList = new ArrayList<WsPlasAcct>();
				newUserList.add(acct);
				orgAcctsMap.put(orgId, newUserList);
			}
		}
		return orgAcctsMap;
	}
	
	/**
	 * 遍历账号,设置机构与职位关系
	 * @param posList 
	 * @return Map<String, List<WsPlasSysPosition>>{orgId, wsSysPositionList}
	 */
	public static Map<String, List<WsPlasSysPosition>> getOrgSysPosMap(List<WsPlasSysPosition> posList) {

		Map<String, List<WsPlasSysPosition>> orgPosMap = new HashMap<String, List<WsPlasSysPosition>>();
		String tmpOrgId = null;
		
		for (WsPlasSysPosition pos : posList) {
			tmpOrgId = pos.getOrgId();
			if(StringUtils.isBlank(tmpOrgId)){
				System.out.println("未找到上级机构! " + pos.getSysPosName()+"["+pos.getSysPosCd()+"]");
				tmpOrgId = DEFAULT_ROOT_ORG_ID;
			}
			
			if (orgPosMap.containsKey(tmpOrgId)) {
				orgPosMap.get(tmpOrgId).add(pos);
			} else {
				List<WsPlasSysPosition> newPosList = new ArrayList<WsPlasSysPosition>();
				newPosList.add(pos);
				orgPosMap.put(tmpOrgId, newPosList);
			}
		}
		return orgPosMap;
	}
	
	/* ************************************** 计算 **************************************/

	//构造机构根节点
	public static TreePanelNode getRootTreeNodeOrg() {
		TreePanelNode node = new TreePanelNode();
		node.setId(DEFAULT_ROOT_ORG_ID);
		node.setText(DEFAULT_ROOT_ORG_NAME);
		
		node.setEntityId(DEFAULT_ROOT_ORG_ID);
		node.setEntityCd(DEFAULT_ROOT_ORG_CD);
		node.setEntityName(DEFAULT_ROOT_ORG_NAME);
		
		node.setOrgOrUser(NODE_TYPE_ROOT);
		node.setNodeType(NODE_TYPE_ROOT);
		node.setChecked(NODE_CHECKED_UNDEFINED);
		return node;
	} 
	
	/**
	 * 机构与人员关系树
	 * @param rootNode
	 * @param orgOrgsMap
	 * @param orgUsersMap
	 * @param userList
	 * @param checked
	 * @return
	 */
	public static TreePanelNode buildOrgUserTree(
			TreePanelNode rootNode,
			Map<String, List<WsPlasOrg>> orgOrgsMap,
			Map<String, List<WsPlasUser>> orgUsersMap,//可空
			String checked) {
		return buildOrgUserTree(rootNode, orgOrgsMap, orgUsersMap, checked, false);
	}
	/**
	 * @param rootNode
	 * @param orgOrgsMap
	 * @param orgUsersMap
	 * @param checked
	 * @return
	 */
	public static TreePanelNode buildOrgUserTree(
			TreePanelNode rootNode,
			Map<String, List<WsPlasOrg>> orgOrgsMap,
			Map<String, List<WsPlasUser>> orgUsersMap,//可空
			String checked,
			boolean isOneNodeFlg) {
		 
		// 设置子孙节点
		rootNode.setChildren(getChildrenNode(rootNode, orgOrgsMap, orgUsersMap, checked,isOneNodeFlg));
		return rootNode;
	}

	/**
	 * @param treeNode
	 * @param orgOrgsMap
	 * @param orgUsersMap
	 * @param checked
	 * @return
	 */
	private static List<TreePanelNode> getChildrenNode(
			TreePanelNode treeNode,
			Map<String, List<WsPlasOrg>> orgOrgsMap,//{orgId, wsOrgList}
			Map<String, List<WsPlasUser>> orgUsersMap,//{orgId, wsUserList}
			String checked) {
		return getChildrenNode(treeNode, orgOrgsMap, orgUsersMap, checked, false);
	}
	private static List<TreePanelNode> getChildrenNode(
			TreePanelNode treeNode,
			Map<String, List<WsPlasOrg>> orgOrgsMap,//{orgId, wsOrgList}
			Map<String, List<WsPlasUser>> orgUsersMap,//{orgId, wsUserList}
			String checked,
			boolean isOneNodeFlg) {//TODO:请小勇处理
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();
		
		// 先人员
		if(orgUsersMap != null){
			List<WsPlasUser> users = orgUsersMap.get(parentId);
			if (users != null && users.size() > 0) {
				TreePanelNode tmpNode = null;
				for (WsPlasUser user : users) {
					tmpNode = getTreePanelUserNoChild(user,checked);
					children.add(tmpNode);
				}
			}
		}

		// 后机构
		if(orgOrgsMap != null){
			List<WsPlasOrg> orgs = orgOrgsMap.get(parentId);
			if (orgs != null && orgs.size() > 0) {
				TreePanelNode tmpNode = null;
				for (WsPlasOrg org : orgs) {
					tmpNode = getTreePanelOrgNoChild(org,checked);
					tmpNode.setChildren(getChildrenNode(tmpNode, orgOrgsMap, orgUsersMap, checked));
					children.add(tmpNode);
				}
			}
		}
		return children;
	}

	// 构造机构
	public static TreePanelNode getTreePanelOrgNoChild(WsPlasOrg org) {
		return getTreePanelOrgNoChild(org, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelOrgNoChild(WsPlasOrg org, String checked) {
		
		TreePanelNode orgNode = new TreePanelNode();
		orgNode.setId(org.getPlasOrgId());
		orgNode.setText(org.getOrgName());
		
		orgNode.setEntityId(org.getPlasOrgId());
		orgNode.setEntityCd(org.getOrgCd());
		orgNode.setEntityBizCd(org.getOrgBizCd());
		orgNode.setEntityName(org.getOrgName());
		
		orgNode.setOrgOrUser(NODE_TYPE_ORG);
		orgNode.setNodeType(NODE_TYPE_ORG);
		orgNode.setChecked(checked);
		return orgNode;
	}
	

	// 构造人员
	public static TreePanelNode getTreePanelUserNoChild(WsPlasUser user) {
		return getTreePanelUserNoChild(user, NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelUserNoChild(WsPlasUser user, String checked) {

		TreePanelNode node = new TreePanelNode();
		node.setId(user.getPlasUserId());
		node.setText(user.getUserName());
		node.setSexCd(user.getSexCd());
		node.setExtParam(user.getUiid());
		
		node.setEntityStatusCd(user.getServiceStatusCd());
		node.setEntityId(user.getPlasUserId());
		node.setEntityCd(user.getUserCd());
		node.setEntityBizCd(user.getUserBizCd());
		node.setEntityName(user.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_USER);
		node.setNodeType(NODE_TYPE_USER);
		node.setChecked(checked);
		return node;
	}
	
	public static String ONLINE_ON = "1";//是否在线
	public static String ONLINE_OFF = "0";//是否在线
	
	
	
	/**
	 * 刷新用户状态
	 * @param node
	 * @param userCdSet
	 * @return
	 */
	public static TreePanelNode refreshOnlineFlg(TreePanelNode node,Set<String> userCdSet){
		if(node == null ||  userCdSet == null || userCdSet.size() == 0)
			return node;
		
		if(userCdSet.contains(node.getEntityCd())){
			node.setEntityStatusCd(ONLINE_ON);
		}else{
			node.setEntityStatusCd(ONLINE_OFF);
		}
		
		for (TreePanelNode tNode : node.getChildren()) {
			refreshOnlineFlg(tNode, userCdSet);
		}
		return node;
	}
}
