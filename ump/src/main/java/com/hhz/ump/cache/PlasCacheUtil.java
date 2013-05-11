package com.hhz.ump.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasNodeSysPosRel;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasSysPosition;
import com.hhz.uums.entity.ws.WsPlasUser;


public class PlasCacheUtil {

	// 节点状态 : 9-根节点 1-机构 0-用户
	public static String NODE_TYPE_ROOT = "9";
	public static String NODE_TYPE_ORG = "1";
	public static String NODE_TYPE_USER = "0";
	public static String NODE_TYPE_ACCT = "0";
	public static String NODE_TYPE_SYSP = "3";
	
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
	
	// 邮箱用户
	public static String MAIL_ORG = "70";
	public static String MAIL_ORG_OTHER = "7001";
	
	//区域公司
	public static final String ORG_DC_NORTH = "707";//区域公司-北区
	public static final String ORG_DC_SOUTH = "706";//区域公司-南区
	private static Log logger = LogFactory.getLog(PlasCacheUtil.class);
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
		
		List<WsPlasAcct> newUserList = null;
		
		String orgId = null;
		for (WsPlasAcct acct : acctList) {
			orgId = acct.getOrgId();
			if(StringUtils.isBlank(orgId)){
				//System.out.println("未找到上级机构! " + acct.getUserName()+"["+acct.getUiid()+"]");
				orgId = DEFAULT_ROOT_ORG_ID;
			}
			
			if (orgAcctsMap.containsKey(orgId)) {
				orgAcctsMap.get(orgId).add(acct);
			} else {
				newUserList = new ArrayList<WsPlasAcct>();
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
		
		List<WsPlasSysPosition> newPosList = null;
		for (WsPlasSysPosition pos : posList) {
			tmpOrgId = pos.getOrgId();
			if(StringUtils.isBlank(tmpOrgId)){
				//System.out.println("未找到上级机构! " + pos.getSysPosName()+"["+pos.getSysPosCd()+"]");
				tmpOrgId = DEFAULT_ROOT_ORG_ID;
			}
			
			//注意:若职位上没有人员或职位失效,则将职位忽略
			if(StringUtils.isNotBlank(pos.getAcctId()) 
					&& (pos.getActiveBl()!= null && pos.getActiveBl().booleanValue())){
				if (orgPosMap.containsKey(tmpOrgId)) {
					orgPosMap.get(tmpOrgId).add(pos);
				} else {
					newPosList = new ArrayList<WsPlasSysPosition>();
					newPosList.add(pos);
					orgPosMap.put(tmpOrgId, newPosList);
				}
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

		// 移除隐藏的机构
		TreePanelUtil.refreshMoveOrgHidden(rootNode); 
		// 移除其他邮箱
		TreePanelUtil.refreshMoveOrgOtherEmailGroup(rootNode);
		
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
					
					//过滤邮件用户
					if(MAIL_ORG.equals(org.getOrgBizCd())){
						continue;
					}
					
					tmpNode = getTreePanelOrgNoChild(org,checked);
					if (!isOneNodeFlg){
					tmpNode.setChildren(getChildrenNode(tmpNode, orgOrgsMap, orgUsersMap, checked));
					}
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
		node.setId(user.getPlasUserId());//查看名片使用
		node.setText(user.getUserName());
		node.setSexCd(user.getSexCd());
		node.setExtParam(user.getUiid());//很重要
		
		node.setEntityStatusCd(user.getServiceStatusCd());
		node.setEntityId(user.getPlasUserId());
		node.setEntityCd(user.getUserCd());
		node.setEntityBizCd(user.getUserBizCd());
		node.setEntityName(user.getUserName());
		
		node.setOrgOrUser(NODE_TYPE_USER);
		node.setNodeType(NODE_TYPE_USER);
		node.setChecked(checked);
		
		node.setLevelCd(user.getPermissionLevelCd());//职级
		
		//增加职责字段 setResponsibility 至 username处：如 许健康【总裁】
		if(StringUtils.isNotBlank(user.getResponsibility())){
			node.setText(user.getUserName()+"["+user.getResponsibility()+"]");
		}
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
	public static TreePanelNode refreshOnlineFlg(TreePanelNode node,Set<String> uiidSet){
		if(node == null ||  uiidSet == null || uiidSet.size() == 0)
			return node;
		
		// 注意: param 对应 uiid 
		if(uiidSet.contains(node.getExtParam())){
			node.setEntityStatusCd(ONLINE_ON);
		}else{
			node.setEntityStatusCd(ONLINE_OFF);
		}
		
		for (TreePanelNode tNode : node.getChildren()) {
			refreshOnlineFlg(tNode, uiidSet);
		}
		return node;
	}
	
	public static Map<String, List<WsPlasOrg>> getOrgOrgsMap(){
		return PlasCacheRun.getMapDimeOrgOrgsMap().get(PlasCacheUtil.TREE_TYPE_PHYSICAL);
	}
	public static Map<String, String> getReadOnlyMap(){
		return PlasCacheRun.getDictDataMap("PLAS_READ_ONLY_USER");
	}
	
	
	
	//构造机构职位树,用于通讯录展示
	//add by huangbijin 2011-12-29
	public static TreePanelNode buildOrgPosTreeNoCheck(List<WsPlasSysPosition> posList,Map<String, WsPlasAcct> acctIdAcctMap,Map<String, WsPlasUser> userIdUserMap) {
		TreePanelNode rootNode = getRootTreeNodeOrg();
		Map<String, List<WsPlasOrg>> orgOrgsMap = getOrgOrgsMap();
		Map<String, List<WsPlasSysPosition>> orgPossMap = getOrgSysPosMap(posList);
		rootNode.setChildren(getChildrenNode(rootNode, orgOrgsMap, orgPossMap, null,acctIdAcctMap,userIdUserMap,NODE_CHECKED_UNDEFINED));
		

		//移除非不显示机构  add by huangbijin 2012-5-31
		TreePanelUtil.refreshMoveOrgHidden(rootNode);
		// 移除邮箱
		TreePanelUtil.refreshMoveOrgOtherEmailGroup(rootNode);
		// 解决一个人在机构下出现两次的情况
		TreePanelUtil.refreshMoveDumpPosNode(rootNode);
		
		
		return rootNode;
	}
	private static List<TreePanelNode> getChildrenNode(
			TreePanelNode treeNode,
			Map<String, List<WsPlasOrg>> orgOrgsMap,
			Map<String, List<WsPlasSysPosition>> orgPosMap,
			List<String> checkedPosIdList,
			Map<String, WsPlasAcct> acctIdAcctMap,
			Map<String, WsPlasUser> userIdUserMap,
			String checked) {
 
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();

		// 先职位
		List<WsPlasSysPosition> posList = orgPosMap.get(parentId);
		TreePanelNode posNode = null;
		if (posList != null && posList.size() > 0) {
			
			WsPlasAcct tmpAcct = null;
			String tmpUiid = null;
			String tmpAcctStatusCd = null;
			Map<String, String> readMap = getReadOnlyMap();
			
			for (WsPlasSysPosition pos : posList) {
				
//				System.out.println(">>" + pos.getVisableFlg());
				
				//注意:若职位失效或未分配人员,则将忽略职位.
				if(StringUtils.isNotBlank(pos.getAcctId()) 
						//可用
						&& (pos.getActiveBl()!= null && pos.getActiveBl().booleanValue())
							//是否可见 add by huangbijin 2012-03-31
							&& ((pos.getVisableFlg() == null) || (pos.getVisableFlg()!= null && pos.getVisableFlg().booleanValue()))){

					//这里一定要从plasCahceRun里取
					tmpAcct = PlasCacheRun.getAcctById(pos.getAcctId());
					if(tmpAcct == null){
						continue;
					}
					tmpUiid = tmpAcct.getUiid();
					tmpAcctStatusCd = tmpAcct.getStatusCd();
//					System.out.println(">>>> " + tmpUiid + ", " + tmpAcct.getUserName());
					//未入职 +离职人员,不显示
					if(DictContants.UAAP_USER_STATUS_NOENTER.equals(tmpAcctStatusCd)
							|| DictContants.UAAP_USER_STATUS_CLOSED.equals(tmpAcctStatusCd)) {
						logger.info(" 未入职 或离职人员： " + tmpUiid + " - " + tmpAcctStatusCd);
						continue;
					}
					//隐藏人员,不显示
					if(readMap.containsKey(tmpUiid)){
						logger.info(" 隐藏人员,不显示： " + tmpUiid);
						continue;
					}
					
					posNode = getTreePanelPosNoChild(pos,acctIdAcctMap,userIdUserMap);
					if(posNode!=null){
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
			}
		}

		// 后机构
		TreePanelNode orgNode = null;
		List<WsPlasOrg> orgs = orgOrgsMap.get(parentId);
		if (orgs != null && orgs.size() > 0) {
			for (WsPlasOrg org : orgs) {
				orgNode = getTreePanelOrgNoChild(org);
				orgNode.setChildren(getChildrenNode(orgNode, orgOrgsMap, orgPosMap, checkedPosIdList,acctIdAcctMap, userIdUserMap, checked));
//				if(NODE_CHECKED_UNDEFINED!=checked){
//					//refreshNodeChecked(orgNode);
//				}
				children.add(orgNode);
			}
		}
		return children;
	}
	// 构造职位
	public static TreePanelNode getTreePanelPosNoChild(WsPlasSysPosition pos, Map<String, WsPlasAcct> acctIdAcctMap,Map<String, WsPlasUser> userIdUserMap) {
		return getTreePanelPosNoChild(pos, NODE_CHECKED_UNDEFINED, acctIdAcctMap, userIdUserMap);
	}

	public static TreePanelNode getTreePanelPosNoChild(WsPlasSysPosition pos, String checked,Map<String, WsPlasAcct> acctIdAcctMap,Map<String, WsPlasUser> userIdUserMap) {

		WsPlasAcct acct = acctIdAcctMap.get(pos.getAcctId());
		if(acct == null){
			System.out.println("职位未分配人员! plasSysPositionId:" + pos.getPlasSysPositionId() + ",posName:" + pos.getSysPosName()+",acctId:" + pos.getAcctId());
		}else{
//			node.setText(pos.getSysPosName() +"["+acct.getUiid() +"," + acct.getUserName() +"]");
			WsPlasUser user = userIdUserMap.get(acct.getUserId());
			
			//隐藏用户，不显示
			if(DictContants.UAAP_USER_STATUS_NOENTER.equals(acct.getStatusCd())
					 	|| DictContants.UAAP_USER_STATUS_CLOSED.equals(acct.getStatusCd()))
				return null;
			
			//只读用户，不显示
			if(getReadOnlyMap()!= null && getReadOnlyMap().keySet().contains(acct.getUiid()))
				return null;


			TreePanelNode node = new TreePanelNode();
			node.setId(pos.getPlasSysPositionId());//查看名片使用
			node.setText(pos.getSysPosName());
			
			//node.setId(user.getPlasUserId());//为了避免一棵树上同时有多个人员,不采用人员ID作key.
			node.setSexCd(user.getSexCd());
			
			//增加职责字段 setResponsibility 至 username处：如 许健康【总裁】
			if(StringUtils.isNotBlank(user.getResponsibility())){
				node.setText(user.getUserName()+"["+user.getResponsibility()+"]");
			}else{
				node.setText(user.getUserName());
			}
			node.setExtParam(user.getUiid());//很重要,前台：显示名片;后台: 查看名片明细 使用;

			node.setEntityStatusCd(user.getServiceStatusCd());
			node.setEntityId(user.getPlasUserId());
			node.setEntityCd(user.getUserCd());
			node.setEntityBizCd(user.getUserBizCd());
			node.setEntityName(user.getUserName());
			node.setLevelCd(user.getPermissionLevelCd());//职级
			

			node.setOrgOrUser(NODE_TYPE_USER);
			node.setNodeType(NODE_TYPE_USER);
			node.setChecked(checked);
			return node;
		}
		return null;
	}

	//	1、审批节点表，增加3个字段，
	//	 1 机构节点级别（地产总部、商业总部、行业总部、事业总部、区域公司、地产公司、行业公司、商业公司、通用）
	//	 2 机构种类（项目、人力、营销、投资、技术、成本、储备、总部），
	//	 3 抓取人员类型（1-机构负责人、2-名称），
	//
	//	node -> 找机构（按类型：） -> 找人 
	//
	//	工作：
	//	cache
	//	1、增加方法：根据（机构节点级别、人员类型、机构种类）取得对应的系统职位和人
	//	2、增加方法：根据 当前登录用户要带出(系统职位)所在机构级别（包括：地产总部、商业总部、行业总部、事业总部、区域公司、地产公司、下属公司、商业公司），
		
	//	3、机构(小)增加字段：机构种类（项目、人力、营销、投资、技术、成本、储备、总部）
	//	       (大)增加字段：机构节点级别（地产总部、商业总部、行业总部、事业总部、南区、北区、南区地产公司、北区地产公司、商业公司、行业公司） 
	
	/**
	 * 根据nodeCd取得对应的审批人uiid
	 * @param nodeCd
	 * @return
	 */
	public static Set<String> getResUiidByNodeCd(String nodeCd) {
		AppParamManager appParamManager=SpringContextHolder.getBean("appParamManager");
		if (!appParamManager.getResPlasNodeSet().equals("1"))
			return null;
		String uiid = "";
		String centerOrgId = null;// 中心ID
		String qyOrgId = null;// 区域ID
		String centerCd = SpringSecurityUtils.getCurrentCenterCd();
		if (StringUtils.isNotBlank(centerCd)) {
			WsPlasOrg plasOrg = PlasCache.getOrgByCd(centerCd);
			centerOrgId = plasOrg.getPlasOrgId();
		}
		String qyOrgCd = SpringSecurityUtils.getCurrentAreaOrg();// 区域公司
		if (StringUtils.isNotBlank(qyOrgCd)) {
			WsPlasOrg plasOrg_qy = PlasCache.getOrgByCd(qyOrgCd);
			qyOrgId = plasOrg_qy.getPlasOrgId();
		}
		//根据nodeCd取得，其对应的所有系统职位关系
		//然后，再遍历这些记录，取出配置的中心机构Id和当前用户的中心机构Id相对应的记录
		//返回对应的所有人员Cd
		Set<String>	 uiids=new HashSet<String>();
		List<WsPlasNodeSysPosRel> plasNodeSysPosRels = PlasCache.getPlasNodeSysPosRels(nodeCd);
		if (plasNodeSysPosRels != null) {
			for (WsPlasNodeSysPosRel wsPlasNodeSysPosRel : plasNodeSysPosRels) {
				if (wsPlasNodeSysPosRel.getPlasOrgId() != null) {
					if (wsPlasNodeSysPosRel.getPlasOrgId().equals(centerOrgId)
							|| wsPlasNodeSysPosRel.getPlasOrgId().equals(qyOrgId)) {
						WsPlasSysPosition sysPosition = PlasCache.getSysIdPosMap().get(wsPlasNodeSysPosRel.getPlasSysPositionId());
						if (sysPosition!=null){
							String acctId = sysPosition.getAcctId();
							WsPlasAcct acct= PlasCache.getAcctById(acctId);
							uiid=acct.getUiid();
							uiids.add(uiid);
						}
					}
				}
			}
		}
		return uiids;
	}
	/**
	 * 根据nodeCd取得唯一的审批人
	 * @param nodeCd
	 * @return
	 */
	public static String getResUiidOneByNodeCd(String nodeCd) {
		Set<String> uiids=getResUiidByNodeCd(nodeCd);
		if (uiids!=null && uiids.size()==1)
			return uiids.iterator().next();
		return null;
	}
	/**
	 * 根据（机构节点级别、机构种类、人员类型）取得对应的系统职位的人
	 * @param nodeLevelCd
	 * @param orgKindCd
	 * @param userTypeCd
	 * @return
	 */
	public static List<String> getVoSysPositionList(String nodeLevelCd, String orgKindCd, String userTypeCd){
		return getVoSysPositionList(nodeLevelCd, orgKindCd, userTypeCd, null);
	}
	/**
	 * @param nodeLevelCd
	 * @param orgKindCd
	 * @param userKindCd
	 * @param aliaName  成本副总经理
	 * @return
	 */
	public static List<String> getVoSysPositionList(String nodeLevelCd, String orgKindCd, String userKindCd,String aliaName){
		//返回对象
		List<String> rtnList = new ArrayList<String>();
		boolean fzcFlg =false;
		
		//4-节点名称匹配 , 若不合法,直接返回
		if(DictContants.USER_KIND_CD_JD.equals(userKindCd) && StringUtils.isBlank(aliaName))
			return rtnList;
		
		//用户种类
		AppDictTypeManager appDictTypeManager = (AppDictTypeManager) SpringContextHolder.getBean("appDictTypeManager");
		Map<String, String> mapUserKind = appDictTypeManager.getDictDataByTypeCdA((DictContants.USER_KIND_CD));//注: pd代码表获取，非plasCache
		String tAliaName = null;
		if(StringUtils.isNotBlank(userKindCd)){
			if(mapUserKind.containsKey(userKindCd)){
				//成本/财务/工程/总工
				if(DictContants.USER_KIND_CD_CB.equals(userKindCd) ||
						(DictContants.USER_KIND_CD_CW.equals(userKindCd)) ||
							(DictContants.USER_KIND_CD_GC.equals(userKindCd)) ||
								(DictContants.USER_KIND_CD_ZG.equals(userKindCd))){
					tAliaName = mapUserKind.get(userKindCd);
				}
				//名称匹配
				else if(DictContants.USER_KIND_CD_JD.equals(userKindCd)){
					tAliaName = aliaName;
				}
				//副总裁
				else if(DictContants.USER_KIND_CD_FZC.equals(userKindCd)){
					tAliaName = "副总裁";
					fzcFlg = true;
				}
			} else
				return rtnList;
		}
		
		//当前用户uiid
		String uiid =  SpringSecurityUtils.getCurrentUiid();
		
		//获取遍历的机构列表
		List<WsPlasOrg> tOrgList = new ArrayList<WsPlasOrg>();
		
		//如果部门种类:orgKindCd=8(部门)，则取出当前用户所属部门的负责人
		if(StringUtils.isNotBlank(orgKindCd) && DictContants.ORG_KIND_CD_BM.equals(orgKindCd)){
			WsPlasUser user = PlasCache.getUserByUiid(uiid);
			WsPlasOrg tOrg= PlasCache.getOrgById(user.getOrgId());
			if(tOrg!=null){
				tOrgList.add(tOrg);
			}
		}
		//如果部门种类:orgKindCd=11(中心)，则取出当前用户所属中心(中心为部门的上级)的负责人
		else if(StringUtils.isNotBlank(orgKindCd) && DictContants.ORG_KIND_CD_ZX.equals(orgKindCd)){
			WsPlasOrg tOrg = PlasCache.getCenterOrgByUiid(uiid);
			if(tOrg!=null){
				tOrgList.add(tOrg);
			}
		}else{
			//北区
			Map<String, String> mapNorth = PlasCache.getDictDataMap(DictContants.AREA_ORG_NORTH);
			//南区
			Map<String, String> mapSouth = PlasCache.getDictDataMap(DictContants.AREA_ORG_SOUTH);
			
			//获取当前用户所属大机构列表
			List<WsPlasOrg> findOrgList = getPlasOrglList(uiid);
			
			List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
			for (WsPlasOrg tOrg : findOrgList) {
				orgList.add(tOrg);
				//若是项目公司,手动添加南北区;上海城市公司在这里不需要特殊处理;
				if(mapNorth.containsKey(tOrg.getOrgCd())){
					orgList.add(PlasCache.getOrgByCd(ORG_DC_NORTH));
				}
				if(mapSouth.containsKey(tOrg.getOrgCd())){
					orgList.add(PlasCache.getOrgByCd(ORG_DC_SOUTH));
				}
			}
			
			//因为一个人同时归属多个区域,需要定义列表
			List<WsPlasOrg> targetOrgList = new ArrayList<WsPlasOrg>();
			//如果找不到相应的大类机构，就直接返回
			for (WsPlasOrg tOrg : orgList) {
				if(StringUtils.isNotBlank(tOrg.getNodeLevelCd())){
					//如果是项目公司，将项目公司所属区域公司负责人也要找出来
					if(tOrg.getNodeLevelCd().equals(DictContants.ORG_NODE_LEVEL_QUGS) && !StringUtils.contains(nodeLevelCd, DictContants.ORG_NODE_LEVEL_QUGS)){
						tOrgList.add(tOrg);
						targetOrgList.add(tOrg);
					}else{
						//只取大机构
						if(tOrg.getNodeLevelCd().equals(nodeLevelCd)){
							targetOrgList.add(tOrg);
						}
					}
				}
			}
			if(targetOrgList == null || targetOrgList.size() == 0)
				return rtnList;
	
			List<WsPlasOrg> tmpOrgList = null;
			
			//若要获取机构负责人,则可能为空
			if(StringUtils.isBlank(orgKindCd)){
				tOrgList = targetOrgList;
			}else{
				//瀑布获取小机构
				for (WsPlasOrg tmpOrg : targetOrgList) {
					if(DictContants.USER_KIND_CD_FZC.equals(userKindCd)){
						//如果是副总裁，查逻辑视图
						tmpOrgList = PlasCache.getDecsantOrgListByProp(TREE_TYPE_LOGICAL, tmpOrg.getPlasOrgId(), orgKindCd, "orgKindCd");
					}else{
						tmpOrgList = PlasCache.getDecsantOrgListByProp(TREE_TYPE_PHYSICAL, tmpOrg.getPlasOrgId(), orgKindCd, "orgKindCd");
					}
					if(tmpOrgList != null){
						tOrgList.addAll(tmpOrgList);
					}
				}
			}
		}
		//遍历机构列表
		String orgMgrUiid = null;
		for (WsPlasOrg tPlasOrg : tOrgList) {
			//0-机构负责人/6-副总裁
			if(DictContants.USER_KIND_CD_JGFZR.equals(userKindCd)||
					DictContants.USER_KIND_CD_FZC.equals(userKindCd)){
				orgMgrUiid = tPlasOrg.getOrgMgrId();//uiid
				if(StringUtils.isNotBlank(orgMgrUiid)){
					WsPlasUser tUser = PlasCache.getUserByUiid(orgMgrUiid);
					if(tUser != null){
						//若 只取副总裁
						if(fzcFlg){
							if(DictContants.PERM_FZC.equals(tUser.getPermissionLevelCd())){
								if(!rtnList.contains(orgMgrUiid)){//去除重复元素
									rtnList.add(orgMgrUiid);
								}
							}
						}else{
							if(!rtnList.contains(orgMgrUiid)){
								rtnList.add(orgMgrUiid);
							}
						}
					}
				}
			}else{
				Map<String, List<WsPlasSysPosition>>  tmpMap = PlasCache.getMapOrgPosList();
				if(tmpMap.containsKey(tPlasOrg.getPlasOrgId())){
					List<WsPlasSysPosition> tList = tmpMap.get(tPlasOrg.getPlasOrgId());
					for (WsPlasSysPosition tPos : tList) {
						//职位上必须有人
						if(StringUtils.isNotBlank(tPos.getAcctId())){
							//名称完全匹配
							if(tPos != null && (tPos.getSysPosName().equals(tAliaName))){
								String rtnUiid = PlasCache.getAcctById(tPos.getAcctId()).getUiid();
								if(!rtnList.contains(rtnUiid)){
									rtnList.add(rtnUiid);
								}
							}
						}
					}
				}
			}
		}
		return rtnList;
	}
	
	

	/**
	 * 根据当前用户取得(系统职位)所在机构节点级别
	 * （机构节点级别包括：地产总部、商业总部、行业总部、事业总部、区域公司、地产公司、下属公司、商业公司、通用）
	 * @param uiid
	 * @return
	 */
	public static List<String> getCurNodeLevelList(String uiid) {
		
		List<String> list = new ArrayList<String>();
		String orgId =null;
		WsPlasOrg tOrg = null;
		List<WsPlasSysPosition> sysPosList = PlasCache.getPosListByUiid2(uiid);
		for (WsPlasSysPosition tmpPos : sysPosList) {
			orgId = tmpPos.getOrgId();
			tOrg= PlasCache.getOrgById(orgId);
			if(tOrg != null){
				List<WsPlasOrg> orgList =  PlasCache.getPhysicalBubbleOrgListByOrgCd(tOrg.getOrgCd());
//				orgList.add(tOrg);
				String  nodeLevelCd = null;
				for (WsPlasOrg tmpOrg : orgList) {
					nodeLevelCd = tmpOrg.getNodeLevelCd();
					//如果节点级别为 区域公司、地产公司、下属公司、商业公司、 直接add常量值
					if(DictContants.ORG_NODE_LEVEL_QUGS.equals(nodeLevelCd) 
							|| DictContants.ORG_NODE_LEVEL_DCGD.equals(nodeLevelCd)
							|| DictContants.ORG_NODE_LEVEL_HYGS.equals(nodeLevelCd) 
							|| DictContants.ORG_NODE_LEVEL_SYGS.equals(nodeLevelCd)){
						list.add(nodeLevelCd);
						continue;
					}else{
						if(DictContants.ORG_NODE_LEVEL_DCZB.equals(nodeLevelCd) 
								|| DictContants.ORG_NODE_LEVEL_SYZB.equals(nodeLevelCd)
								|| DictContants.ORG_NODE_LEVEL_HYZB.equals(nodeLevelCd) 
								|| DictContants.ORG_NODE_LEVEL_YSZB.equals(nodeLevelCd)){
							list.add(nodeLevelCd);
							continue;
						}
					}
				}
			}
		}
		return list;
	}
	
	public static List<WsPlasOrg> getPlasOrglList(String uiid) {
		List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
		String orgId =null;
		WsPlasOrg tOrg = null;
		List<WsPlasSysPosition> sysPosList = PlasCache.getPosListByUiid2(uiid);
		for (WsPlasSysPosition tmpPos : sysPosList) {
			orgId = tmpPos.getOrgId();
			tOrg= PlasCache.getOrgById(orgId);
			if(tOrg != null){
				List<WsPlasOrg> orgList =  PlasCache.getPhysicalBubbleOrgListByOrgCd(tOrg.getOrgCd());
//				orgList.add(tOrg);
				String  nodeLevelCd = null;
				for (WsPlasOrg tmpOrg : orgList) {
					nodeLevelCd = tmpOrg.getNodeLevelCd();
					//如果节点级别为 区域公司、地产公司、下属公司、商业公司、通用 直接add常量值
					if(DictContants.ORG_NODE_LEVEL_QUGS.equals(nodeLevelCd) 
							|| DictContants.ORG_NODE_LEVEL_DCGD.equals(nodeLevelCd)
							|| DictContants.ORG_NODE_LEVEL_HYGS.equals(nodeLevelCd) 
							|| DictContants.ORG_NODE_LEVEL_SYGS.equals(nodeLevelCd)
							|| DictContants.ORG_NODE_LEVEL_TY.equals(nodeLevelCd)){
						list.add(tmpOrg);
						continue;
					}else{
						if(DictContants.ORG_NODE_LEVEL_DCZB.equals(nodeLevelCd) 
								|| DictContants.ORG_NODE_LEVEL_SYZB.equals(nodeLevelCd)
								|| DictContants.ORG_NODE_LEVEL_HYZB.equals(nodeLevelCd) 
								|| DictContants.ORG_NODE_LEVEL_YSZB.equals(nodeLevelCd)){
							list.add(tmpOrg);
							continue;
						}
					}
				}
			}
		}
		return list;
	}
}
