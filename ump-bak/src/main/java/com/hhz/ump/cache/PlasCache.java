package com.hhz.ump.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsAcctSysposRel;
import com.hhz.uums.entity.ws.WsAppDictData;
import com.hhz.uums.entity.ws.WsAppDictType;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasNodeSysPosRel;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasOrgDime;
import com.hhz.uums.entity.ws.WsPlasOrgRel;
import com.hhz.uums.entity.ws.WsPlasRealPosition;
import com.hhz.uums.entity.ws.WsPlasRole;
import com.hhz.uums.entity.ws.WsPlasSysPosition;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * @author RS
 *
 */
/**
 * @author RS
 *
 */
@Service
public class PlasCache {
	
	private static Log log = LogFactory.getLog(PlasCache.class);
 
	/* ******************** 定时 ********************** */
	// 维度
	private static List<WsPlasOrgDime> dimeList = null;

	// 机构
	private static List<WsPlasOrg> orgEnableList = null;
	private static List<WsPlasOrg> orgAllList = null;
	private static Map<String, WsPlasAcct> uiidAcctMap = null;
	private static Map<String, WsPlasAcct> acctIdAcctMap = null;
//	private static Map<String, WsPlasAcct> userTypeCdAcctMap = null;


	// 系统职位
	private static List<WsPlasSysPosition> sysPosList = null;
	private static Map<String, WsPlasSysPosition> sysIdPosMap = null;

	// 实际职位
	private static List<WsPlasRealPosition> realPosList = null;

	// 账号
	private static List<WsPlasAcct> acctList = null;
	private static List<WsPlasAcct> acctActiveList = null;

	// 人员
	private static List<WsPlasUser> userList = null;
	private static List<WsPlasUser> userActiveList = null;

	// PD对应的角色列表
	private static List<WsPlasRole> roleList = null;
	private static Map<String, WsPlasRole> roleCdMap = null;
//	private static Map<String, WsPlasRole> roleNameMap = null;

	// PLAS数据字典
	private static List<WsAppDictType> dictTypeList = null;
	private static List<WsAppDictData> dictDataList = null;
	private static Map<String, List<WsAppDictData>> dictTypeDataMap = null;

	// 机构关系
	private static List<WsPlasOrgRel> orgRelList = null;
	// 网批节点和系统职务对应关系表
	private static List<WsPlasNodeSysPosRel> plasNodeSysPosRels = null;

	// 只读用户
	private static Map<String, String> readOnlyUserMap = new HashMap<String, String>();

	/* ******************** 定时 ********************** */
	// 机构映射人员列表
	private static Map<String, WsPlasUser> idUserMap = new HashMap<String, WsPlasUser>();
//	private static Map<String, WsPlasUser> cdUserMap = new HashMap<String, WsPlasUser>();
	private static Map<String, WsPlasUser> uiidUserMap = new HashMap<String, WsPlasUser>();
	private static Map<String, WsPlasUser> bizCdUserMap = new HashMap<String, WsPlasUser>();

	private static Map<String, WsPlasOrg> idOrgMap = new HashMap<String, WsPlasOrg>();
	private static Map<String, WsPlasOrg> cdOrgMap = new HashMap<String, WsPlasOrg>();
	private static Map<String, WsPlasOrg> bizCdOrgMap = new HashMap<String, WsPlasOrg>();
	private static Map<String, List<WsPlasOrg>> orgTypeCdListMap = new HashMap<String, List<WsPlasOrg>>();

	private static Map<String, List<WsPlasOrg>> uiidMgrOrgMap = new HashMap<String, List<WsPlasOrg>>();

	private static Map<String, Map<String, List<WsPlasOrg>>> mapDimeOrgOrgsMap = new HashMap<String, Map<String,List<WsPlasOrg>>>();
	private static Map<String, String> mapDimeOrgId2PId = new HashMap<String, String>();
	private static Map<String, String> mapDimePid2OrgId = new HashMap<String, String>();

	private static Map<String, List<WsPlasUser>> mapOrgIdUserList = new HashMap<String, List<WsPlasUser>>();
	private static Map<String, List<WsPlasUser>> mapOrgCdUserList = new HashMap<String, List<WsPlasUser>>();
//	private static Map<String, List<WsPlasAcct>> mapOrgAcctList   = new HashMap<String, List<WsPlasAcct>>();
	private static Map<String, List<WsPlasSysPosition>> mapOrgPosList = new HashMap<String, List<WsPlasSysPosition>>();
	
	
	private static Map<String, List<WsAcctSysposRel>> mapUiidPosList = new HashMap<String, List<WsAcctSysposRel>>();
	
	private static Map<String, List<WsPlasNodeSysPosRel>> mapNodeCdSysPosList=new HashMap<String, List<WsPlasNodeSysPosRel>>();
	
	//职级
	private static Map<String,String> mapRank = new LinkedHashMap<String, String>();
	private static Map<String,List<WsPlasUser>> mapRankUserList = new HashMap<String, List<WsPlasUser>>();
	

	// 首页机构用户树
	private static TreePanelNode phyOrgUserTree = null;
	private static TreePanelNode phyOrgPosTree = null;
	
	/**
	 * 刷新cache,控制台调用
	 */ 
	public boolean reloadCache() {
		
		try{
			BisCache.reloadCache();
			boolean flag = PlasCacheRun.refreshPlasCache();
			
			if(flag){
				
				// 维度
				dimeList = PlasCacheRun.getDimeList();
		
				// 机构
				orgEnableList = PlasCacheRun.getOrgEnableList();
				orgAllList = PlasCacheRun.getOrgAllList();
				uiidAcctMap = PlasCacheRun.getUiidAcctMap();
				acctIdAcctMap = PlasCacheRun.getAcctIdAcctMap();
				// 系统职位
				sysPosList = PlasCacheRun.getSysPosList();
				sysIdPosMap = PlasCacheRun.getSysIdPosMap();
		
				// 账号
				acctList = PlasCacheRun.getAcctList();
				acctActiveList = PlasCacheRun.getAcctActiveList();
		
				// 人员
				userList = PlasCacheRun.getUserList();
				userActiveList = PlasCacheRun.getUserActiveList();
		
				// PD对应的角色列表
				roleList = PlasCacheRun.getRoleList();
				roleCdMap = PlasCacheRun.getRoleCdMap();
//				roleNameMap = PlasCacheRun.getRoleNameMap();
		
				// PLAS数据字典
				dictTypeList = PlasCacheRun.getDictTypeList();
				dictDataList = PlasCacheRun.getDictDataList();
				dictTypeDataMap = PlasCacheRun.getDictTypeDataMap();
				readOnlyUserMap = PlasCacheRun.getReadOnlyUserMap();
		
				// 机构关系
				orgRelList = PlasCacheRun.getOrgRelList();
		
				// 网批节点和系统职务对应关系表
				plasNodeSysPosRels=PlasCacheRun.getPlasNodeSysPosRels();
				
				idUserMap = PlasCacheRun.getIdUserMap();
//				cdUserMap = PlasCacheRun.getCdUserMap();
				uiidUserMap = PlasCacheRun.getUiidUserMap();
				bizCdUserMap = PlasCacheRun.getBizCdUserMap();
		
				idOrgMap = PlasCacheRun.getIdOrgMap();
				cdOrgMap = PlasCacheRun.getCdOrgMap();
				bizCdOrgMap = PlasCacheRun.getBizCdOrgMap();
				orgTypeCdListMap = PlasCacheRun.getOrgTypeCdListMap();
		
				uiidMgrOrgMap = PlasCacheRun.getUiidMgrOrgMap();
		
				mapDimeOrgOrgsMap = PlasCacheRun.getMapDimeOrgOrgsMap();
				mapDimeOrgId2PId = PlasCacheRun.getMapDimeOrgId2PId();
				mapDimePid2OrgId = PlasCacheRun.getMapDimePid2OrgId();
		
				mapOrgIdUserList = PlasCacheRun.getMapOrgIdUserList();
				mapOrgCdUserList = PlasCacheRun.getMapOrgCdUserList();
//				mapOrgAcctList   = PlasCacheRun.getMapOrgAcctList();
				mapOrgPosList = PlasCacheRun.getMapOrgPosList();
				
				mapUiidPosList = PlasCacheRun.getMapUiidPosList();
				
				mapNodeCdSysPosList = PlasCacheRun.getMapNodeCdSysPosList();
				
				//职级
				mapRank = PlasCacheRun.getMapRank();
				mapRankUserList = PlasCacheRun.getMapRankUserList();
				
				//机构和人员树(注意: 最后执行)
				phyOrgUserTree = PlasCacheRun.getPhyOrgUserTree();
				phyOrgPosTree = PlasCacheRun.getPhyOrgPosTree();
				
				//清空缓存
				PlasCacheRun.cleanCache();
				
				return true;
			} else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.reloadCache() exception! " ,e);
			return false;
		}
	}
	 
	public static Map<String, WsPlasUser> getIdUserMap() {
		return idUserMap;
	}

	public static Map<String, List<WsPlasOrg>> getOrgOrgsMap(String dimeCd) {
		try{
			return mapDimeOrgOrgsMap.get(dimeCd);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getOrgOrgsMap() exception! " ,e);
			return new HashMap<String, List<WsPlasOrg>>();
		}
	}

	/**
	 * 遍历机构,设置机构与机构关系
	 * 
	 * @param dimeCd
	 * @param orgRelList
	 * @param orgMap
	 * @return Map<String, Map<String, List<WsPlasOrg>>>
	 */
	public static void processDimeOrgRel() {

		try{
			// Map<String,String>
			mapDimeOrgId2PId = new HashMap<String, String>();
			mapDimePid2OrgId = new HashMap<String, String>();
	
			// map(dimeCd,map(parentId,wsPlasOrg))
			mapDimeOrgOrgsMap = new HashMap<String, Map<String, List<WsPlasOrg>>>();
	
			// map(parentId,wsPlasOrg)
			Map<String, List<WsPlasOrg>> orgOrgsMap = new HashMap<String, List<WsPlasOrg>>();
			String dimeCd = null;
			String orgId = null;
			String parentId = null;
	
			if(orgRelList!= null){
				for (WsPlasOrgRel rel : orgRelList) {
					// 维度
					dimeCd = rel.getDimeCd();
					orgId = rel.getOrgId();
					parentId = rel.getParentId();
					
					if (StringUtils.isBlank(dimeCd)) {
						log.error("维度为空!" + rel.getPlasDimeOrgRelId());
						continue;
					} else {
		
						mapDimeOrgId2PId.put(getKey(dimeCd, orgId), parentId);
						mapDimePid2OrgId.put(getKey(dimeCd, parentId), orgId);
		
						if (!mapDimeOrgOrgsMap.keySet().contains(dimeCd)) {
							mapDimeOrgOrgsMap.put(dimeCd, new HashMap<String, List<WsPlasOrg>>());
						}
		
						orgOrgsMap = mapDimeOrgOrgsMap.get(dimeCd);
						// 上级
						parentId = rel.getParentId();
						if (StringUtils.isBlank(parentId)) {
							log.error("未找到上级机构! " + rel.getOrgName() + "[" + rel.getOrgCd() + "]");
							parentId = PlasCacheUtil.DEFAULT_ROOT_ORG_ID;
						}
		
						if (orgOrgsMap.containsKey(parentId)) {
							orgOrgsMap.get(parentId).add(getOrgById(rel.getOrgId()));
						} else {
							List<WsPlasOrg> newOrgList = new ArrayList<WsPlasOrg>();
							newOrgList.add(getOrgById(rel.getOrgId()));
							orgOrgsMap.put(parentId, newOrgList);
						}
					}
				}
			}
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.processDimeOrgRel exception! " ,e);
		}
	}

	// private
	public static String getKey(String dimeCd, String orgId) {
		return dimeCd + "_" + orgId;
	}

	/* ************************************************************************************ */

	/**
	 * 获取特定orgId的树节点(含子孙)
	 * @param orgId
	 * @return
	 */
	public static TreePanelNode getTreeNodeByOrgId(String orgId){
		return getTreeNodeByEntityId(getPhysicalOrgTree(), orgId);
	}

	private static TreePanelNode getTreeNodeByEntityId(TreePanelNode node,String findEntityId){
		if(node == null)
			return null;
		for (TreePanelNode tNode : node.getChildren()) {
			if(findEntityId.equals(tNode.getEntityId()))
				return tNode;
		}
		for (TreePanelNode tNode : node.getChildren())
			return getTreeNodeByEntityId(tNode, findEntityId);
		return null;
	}
	
	public static TreePanelNode getPhysicalOrgTree() {
		return phyOrgUserTree;
	}
	public static List<WsPlasOrgDime> getDimeList() {
		return dimeList;
	}

	public static List<WsPlasOrg> getOrgAllList() {
		return orgAllList;
	}

	public static List<WsPlasOrg> getOrgEnableList() {
		return orgEnableList;
	}

	public static List<WsPlasAcct> getAcctList() {
		return acctList;
	}

	public static List<WsPlasAcct> getAcctActiveList() {
		return acctActiveList;
	}

	public static List<WsPlasUser> getUserList() {
		return userList;
	}

	public static List<WsPlasUser> getUserActiveList() {
		return userActiveList;
	}

	public static List<WsPlasRole> getRoleList() {
		return roleList;
	}

	public static List<WsPlasRealPosition> getRealPosList() {
		return realPosList;
	}

	public static List<WsPlasSysPosition> getSysPosList() {
		return sysPosList;
	}

	public static List<WsAppDictType> getDictTypeList() {
		return dictTypeList;
	}

	public static List<WsAppDictData> getDictDataList() {
		return dictDataList;
	}

	public static Map<String, String> getReadOnlyUserMap() {
		return readOnlyUserMap;
	}
	/**
	 * 根据数据字典的CD获得字典对象
	 * @param dictTypeCd
	 * @return
	 */
	public static List<WsAppDictData> getDictDataList(String dictTypeCd) {
		return dictTypeDataMap.get(dictTypeCd);
	}
	
	/**
	 * 根据数据字典的CD获得字典
	 * @param dictTypeCd
	 * @return
	 */
	public static Map<String, String> getDictDataMap(String dictTypeCd) {
		try{
			Map<String, String> values = new LinkedHashMap<String, String>();
			if(dictTypeDataMap != null){
				List<WsAppDictData> datas = dictTypeDataMap.get(dictTypeCd);
				if(datas != null){
					for (WsAppDictData o : datas) {
						values.put(o.getDictCd(), o.getDictName());
					}
				}
			}
			return values;
		}catch (Exception e) {
			log.error("//////////////////////////////////// PlasCache.getDictDataMap() exception! " ,e);
			return new HashMap<String, String>();
		}
	}
	
	/**
	 * 根据数据字典的CD获得字典子项的cd的str组合，用逗号分开。例如用于区域公司对应的项目的sql语句的组合
	 * @param dictTypeCd
	 * @return
	 */
	public static String getDictDataSplit(String dictTypeCd){
		StringBuffer  sb = new StringBuffer();
		try{
			Map<String, String> values = new LinkedHashMap<String, String>();
			List<WsAppDictData> datas = dictTypeDataMap.get(dictTypeCd);
			if(datas != null){
				for (WsAppDictData o : datas) {
					sb.append(",").append(o.getDictCd());
				}
			}
			
			if(StringUtils.isNotBlank(sb.toString()))
				return sb.substring(1);
			else
				return "";
		}catch (Exception e) {
			log.error("//////////////////////////////////// PlasCache.getDictDataSplit() exception! " ,e);
			return "";
		}
	}
	/**
	 * 检查是否含该项目
	 * @param dictTypeCd
	 * @param projectCd
	 * @return
	 */
	public static boolean validateContain(String dictTypeCd, String projectCd){
		if(StringUtils.isBlank(projectCd))
			return false;
		try{
			List<String> list = new ArrayList<String>();
			Map<String, String> values = new LinkedHashMap<String, String>();
			List<WsAppDictData> datas = dictTypeDataMap.get(dictTypeCd);
			for (WsAppDictData o : datas) {
				if(projectCd.equals(o.getDictCd()))
					return true;
			}
			return false;
		}catch (Exception e) {
			log.error("//////////////////////////////////// PlasCache.getDictDataSplit() exception! " ,e);
			return false;
		}
	}

	// 获取用户
	public static WsPlasUser getUserById(String userId) {
		return idUserMap.get(userId);
	}

	public static WsPlasUser getUserByUiid(String uiid) {
		return uiidUserMap.get(uiid);
	}

	public static WsPlasUser getUserByBizCd(String userBizCd) {
		return bizCdUserMap.get(userBizCd);
	}

	// 获取账号
	public static WsPlasAcct getAcctById(String acctId) {
		return acctIdAcctMap.get(acctId);
	}

	// 获取账号
	public static WsPlasAcct getAcctByUiid(String uiid) {
		return uiidAcctMap.get(uiid);
	}

	// 获取机构
	public static WsPlasOrg getOrgById(String orgId) {
		return idOrgMap.get(orgId);
	}

	public static WsPlasOrg getOrgByCd(String orgCd) {
		return cdOrgMap.get(orgCd);
	}

	public static WsPlasOrg getOrgByBizCd(String orgBizCd) {
		return bizCdOrgMap.get(orgBizCd);
	}

	public static List<WsPlasOrg> getOrgListByTypeCd(String orgTypeCd) {
		return orgTypeCdListMap.get(orgTypeCd);
	}

	public static List<WsPlasOrg> getMgrOrgListByUid(String uiid) {
		return uiidMgrOrgMap.get(uiid);
	}

	public static Map<String, List<WsPlasUser>> getMapOrgIdUserList() {
		return mapOrgIdUserList;
	}
	public static Map<String, List<WsPlasUser>> getMapOrgCdUserList() {
		return mapOrgCdUserList;
	}
	public static Map<String, List<WsAcctSysposRel>> getMapUiidPosList() {
		return mapUiidPosList;
	}
	
	/* ************************************************************************************ */
	// 根据机构,获取员工列表（直接\维度+瀑布）
	public static List<WsPlasUser> getDirectPosUserListByCd(String orgCd) {
		List<WsPlasUser> rtnList = new ArrayList<WsPlasUser>() ;
		WsPlasUser tmpUser = null;
		try{
			if (StringUtils.isBlank(orgCd))
				return rtnList;
			WsPlasOrg tmp = getOrgByCd(orgCd);
			if(tmp == null)
				return rtnList;
			else{
				TreePanelNode node = TreePanelUtil.findTreeNode(phyOrgPosTree, tmp.getPlasOrgId(), null, TreePanelUtil.NODE_TYPE_USER);
				if(node == null)
					return new ArrayList<WsPlasUser>();
				else{
					if(node.getChildren()!= null){
						for (TreePanelNode t : node.getChildren()) {
							tmpUser = getUserByUiid(t.getExtParam());
							if(tmpUser!= null){
								rtnList.add(tmpUser);
							}
						}
					}
				}
			}
			return rtnList;
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDirectUserListByCd() exception! " ,e);
			return new ArrayList<WsPlasUser>();
		}
	}

	// 根据机构,获取员工列表（直接\维度+瀑布）
	public static List<WsPlasUser> getDirectUserListByCd(String orgCd) {
		try{
			if (StringUtils.isBlank(orgCd))
				return new ArrayList<WsPlasUser>();
			WsPlasOrg tmp = getOrgByCd(orgCd);
			if(tmp == null)
				return new ArrayList<WsPlasUser>();
			else
				return mapOrgIdUserList.get(tmp.getPlasOrgId());
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDirectUserListByCd() exception! " ,e);
			return new ArrayList<WsPlasUser>();
		}
	}

	public static List<WsPlasUser> getDirectUserList(String orgId) {
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasUser>();
			return mapOrgIdUserList.get(orgId);
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDirectUserList() exception! " ,e);
			return new ArrayList<WsPlasUser>();
		}
	}

	public static List<WsPlasUser> getLogicalDecsantUserList(String orgId) {
		return getDecsantUserList(PlasCacheUtil.TREE_TYPE_LOGICAL, orgId);
	}

	public static List<WsPlasUser> getPhysicalDecsantUserList(String orgId) {
		return getDecsantUserList(PlasCacheUtil.TREE_TYPE_PHYSICAL, orgId);
	}

	public static List<WsPlasUser> getDecsantUserList(String dimeCd, String orgId) {
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasUser>();
	
			List<WsPlasUser> rtnList = new ArrayList<WsPlasUser>();
			rtnList.addAll(getDirectUserList(orgId));
	
			Map<String, List<WsPlasOrg>> map = mapDimeOrgOrgsMap.get(dimeCd);
			List<WsPlasOrg> list = map.get(orgId);
			for (WsPlasOrg o : list) {
				rtnList.addAll(getDecsantUserList(dimeCd, o.getPlasOrgId()));
			}
			return rtnList;
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDecsantUserList() exception! " ,e);
			return new ArrayList<WsPlasUser>();
		}
	}

	// 角色查找员工
	public static List<WsPlasUser> getUserListByRoleCd(String roleCd) {
		try{
			WsPlasRole role = roleCdMap.get(roleCd);
			return Util.getPlasService().getUserListByRoleId(role.getPlasRoleId());
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getUserListByRoleCd("+roleCd+") exception! " ,e);
			return new ArrayList<WsPlasUser>();
		}
	}

//	public static List<WsPlasUser> getUserListByRoleName(String roleName) {
//		try{
//			WsPlasRole role = roleNameMap.get(roleName);
//			return Util.getPlasService().getUserListByRoleId(role.getPlasRoleId());
//		}catch (Exception e) {
//	//		e.printStackTrace();
//			log.error("//////////////////////////////////// PlasCache.getUserListByRoleName("+roleName+") exception! " ,e);
//			return new ArrayList<WsPlasUser>();
//		}
//	}
	
	// 搜索职位列表
	public static List<WsAcctSysposRel> getPosListByUiid(String uiid) {
		try{
			List<WsAcctSysposRel> relList = mapUiidPosList.get(uiid);
			if(relList == null)
				return new ArrayList<WsAcctSysposRel>();
			else
				return relList;
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getPosListByUiid("+uiid+") exception! " ,e);
			return new ArrayList<WsAcctSysposRel>();
		}
	}
	
	public static List<WsPlasSysPosition> getPosListByUiid2(String uiid) {
		try{
			List<WsPlasSysPosition> rtnList = new ArrayList<WsPlasSysPosition>();
			List<WsAcctSysposRel> relList = mapUiidPosList.get(uiid);
			WsPlasSysPosition tPos = null;
			if(relList != null && relList.size() >0){
				for (WsAcctSysposRel t : relList) {
					if(t != null){
						tPos = getSysIdPosMap().get(t.getSysPosId());
						if(tPos != null){
							rtnList.add(tPos);
						}
					}
				}
			}
			if(relList == null)
				return rtnList;
			else
				return rtnList;
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getPosListByUiid2("+uiid+") exception! " ,e);
			return new ArrayList<WsPlasSysPosition>();
		}
	}


	// 根据机构,获取机构列表（维度+直接\维度+瀑布）
	public static List<WsPlasOrg> getPhysicalDirectOrgList(String orgId) {
		return getDirectOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, orgId);
	}

	public static List<WsPlasOrg> getLogicalDirectOrgList(String orgId) {
		return getDirectOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, orgId);
	}

	public static List<WsPlasOrg> getDirectOrgList(String dimeCd, String orgId) {
		return getDirectOrgList(dimeCd, orgId, null);
	}
	public static List<WsPlasOrg> getDirectOrgListPhy(String orgId) {
		return getDirectOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, orgId);
	}

	//搜索直接下属的机构
	public static List<WsPlasOrg> getDirectOrgList(String dimeCd, String orgId, String orgTypeCd) {
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasOrg>();
			if (StringUtils.isBlank(orgTypeCd)){
				List<WsPlasOrg> tList = mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
				if( tList == null){
					tList = new ArrayList<WsPlasOrg>();
				}
				return tList;
			}
			else {
				List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
				List<WsPlasOrg> aList = mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
				
				if(aList != null){
					for (WsPlasOrg t : aList) {
						if (orgTypeCd.equals(t.getOrgTypeCd())) {
							list.add(t);
						}
					}
				}
				return list;
			}
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDirectOrgList() exception! " ,e);
			return new ArrayList<WsPlasOrg>();
		}
	
	}

	public static List<WsPlasOrg> getDecsantOrgList(String dimeCd, String orgId, String orgTypeCd) {
		
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasOrg>();
	
			List<WsPlasOrg> rtnList = new ArrayList<WsPlasOrg>();
			List<WsPlasOrg> tList = getDirectOrgList(dimeCd, orgId, orgTypeCd);
			if( tList != null){
				rtnList.addAll(tList);
			}
	
			List<WsPlasOrg> list = mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
			if(list != null){
				for (WsPlasOrg o : list) {
					if (StringUtils.isNotBlank(orgTypeCd)) {
						if (orgTypeCd.equals(o.getOrgTypeCd())) {
							rtnList.addAll(getDecsantOrgList(dimeCd, o.getPlasOrgId(), orgTypeCd));
						}
					} else {
						rtnList.addAll(getDecsantOrgList(dimeCd, o.getPlasOrgId(), orgTypeCd));
					}
				}
			}
			return rtnList;
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDecsantOrgList() exception! dimeCd:"+dimeCd +",orgId:"+orgId+",orgTypeCd:"+orgTypeCd ,e);
			return new ArrayList<WsPlasOrg>();
		}
	}
	

	/**
	 * add by liuzhihuil 2012-01-12
	 * 根据fieldName,过滤,瀑布搜索 
	 * @param dimeCd
	 * @param orgId
	 * @param fieldValue
	 * @param fieldName
	 * @return
	 */
	public static List<WsPlasOrg> getDirectOrgListByProp(String dimeCd, String orgId, String fieldValue, String fieldName) {
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasOrg>();
			if (StringUtils.isBlank(fieldValue))
				return mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
			else {
				List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
				List<WsPlasOrg> aList = mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
				
				if(aList != null){
					for (WsPlasOrg t : aList) {
						//大机构找到立即返回
						if("nodeLevelCd".equals(fieldName)){
							if (fieldValue.equals(t.getNodeLevelCd())) {
								list.add(t);
								break;
							}
						}
						//小机构，继续找
						else if("orgKindCd".equals(fieldName)){
							if (fieldValue.equals(t.getOrgKindCd())) {
								list.add(t);
							}
						}
					}
				}
				return list;
			}
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDirectOrgListByProp() exception! " ,e);
			return new ArrayList<WsPlasOrg>();
		}
	}
	
	/**
	 * add by liuzhihuil 2012-01-12
	 * 根据fieldName,过滤,瀑布搜索 
	 * @param dimeCd
	 * @param orgId
	 * @param fieldValue
	 * @param fieldName
	 * @return
	 */
	public static List<WsPlasOrg> getDecsantOrgListByProp(String dimeCd, String orgId, String fieldValue,String fieldName) {
		
		try{
			if (StringUtils.isBlank(orgId))
				return new ArrayList<WsPlasOrg>();
	
			List<WsPlasOrg> rtnList = new ArrayList<WsPlasOrg>();
			rtnList.addAll(getDirectOrgListByProp(dimeCd, orgId, fieldValue, fieldName));
	
			List<WsPlasOrg> list = mapDimeOrgOrgsMap.get(dimeCd).get(orgId);
			if(list != null && list.size() > 0){
				for (WsPlasOrg o : list) {
					if (StringUtils.isNotBlank(fieldValue)) {
						if (fieldValue.equals(o.getOrgKindCd())) {
							rtnList.addAll(getDecsantOrgListByProp(dimeCd, o.getPlasOrgId(), fieldValue, fieldName));
						}
					} else {
						rtnList.addAll(getDecsantOrgListByProp(dimeCd, o.getPlasOrgId(), fieldValue, fieldName));
					}
				}
			}
			return rtnList;
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getDecsantOrgListByProp() exception! " ,e);
			return new ArrayList<WsPlasOrg>();
		}
	}

	// 冒泡查找机构 (人员，机构，机构类型)
	public static List<WsPlasOrg> getBubbleOrgList(String dimeCd, String orgId) {
		return getBubbleOrgList(dimeCd, orgId, null);
	}

	public static List<WsPlasOrg> getBubbleOrgList(String dimeCd, String orgId, String orgTypeCd) {
		try{

			List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
			if (StringUtils.isBlank(dimeCd))
				return list;
	
			//先判断当前机构是否所需的机构类型
			WsPlasOrg srcOrg = getOrgById(orgId);
			if(srcOrg == null)
				return list;
			
			if(StringUtils.isNotBlank(orgId) && StringUtils.isNotBlank(orgTypeCd) && orgTypeCd.equals(srcOrg.getOrgTypeCd())){
				if(!list.contains(srcOrg)){
					list.add(srcOrg);
				}
			}

			String parentId = mapDimeOrgId2PId.get(getKey(dimeCd, orgId));
	
			WsPlasOrg t = getOrgById(parentId);
			if (t != null) {
				if (StringUtils.isNotBlank(orgTypeCd)) {
					if (orgTypeCd.equals(t.getOrgTypeCd())) {
						list.add(t);
						list.addAll(getBubbleOrgList(dimeCd, parentId, orgTypeCd));
					}else{
						list.addAll(getBubbleOrgList(dimeCd, parentId, orgTypeCd));
					}
				} else {
					list.add(t);
					list.addAll(getBubbleOrgList(dimeCd, parentId, orgTypeCd));
				}
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getBubbleOrgList() exception! " ,e);
			return new ArrayList<WsPlasOrg>();
		}
	}

	// ***************************** 逻辑 *****************************
	// 上级冒泡
	public static List<WsPlasOrg> getLogicalBubbleOrgListByUiid(String uiid) {
		return PlasCache.getBubbleOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getUserByUiid(uiid).getOrgId());
	}

	public static List<WsPlasOrg> getLogicalBubbleOrgListByOrgCd(String orgCd) {
		return PlasCache.getBubbleOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getOrgByCd(orgCd).getPlasOrgId());
	}

	public static List<WsPlasOrg> getLogicalBubbleOrgListByOrgId(String orgId, String orgTypeCd) {
		return PlasCache.getBubbleOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, orgId, orgTypeCd);
	}

	// 直接下级
	public static List<WsPlasOrg> getLogicalDirectOrgListByOrgCd(String orgCd) {
		return PlasCache.getDirectOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getOrgByCd(orgCd).getPlasOrgId());
	}

	public static List<WsPlasOrg> getLogicalDirectOrgListByOrgCd(String orgCd, String orgTypeCd) {
		return PlasCache.getDirectOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getOrgByCd(orgCd).getPlasOrgId(), orgTypeCd);
	}

	// 子孙下级
	public static List<WsPlasOrg> getLogicalDesantOrgListByOrgCd(String orgCd) {
		return PlasCache.getDecsantOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getOrgByCd(orgCd).getPlasOrgId(), null);
	}

	public static List<WsPlasOrg> getLogicalDesantOrgListByOrgCd(String orgCd, String orgTypeCd) {
		return PlasCache.getDecsantOrgList(PlasCacheUtil.TREE_TYPE_LOGICAL, getOrgByCd(orgCd).getPlasOrgId(), orgTypeCd);
	}

	// ***************************** 物理 *****************************
	// 上级冒泡
	public static List<WsPlasOrg> getPhysicalBubbleOrgListByUiid(String uiid) {
		return PlasCache.getBubbleOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, getUserByUiid(uiid).getOrgId());
	}

	public static List<WsPlasOrg> getPhysicalBubbleOrgListByOrgCd(String orgCd) {
		return PlasCache.getBubbleOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, getOrgByCd(orgCd).getPlasOrgId());
	}

	// 直接下级
	public static List<WsPlasOrg> getPhysicalDirectOrgListByOrgCd(String orgCd) {
		return PlasCache.getDirectOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, getOrgByCd(orgCd).getPlasOrgId());
	}

	public static List<WsPlasOrg> getPhysicalDirectOrgListByOrgCd(String orgCd, String orgTypeCd) {
		return PlasCache.getDirectOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, getOrgByCd(orgCd).getPlasOrgId(), orgTypeCd);
	}

	public static List<WsPlasOrg> getPhysicalDesantOrgListByOrgCd(String orgCd) {
		return PlasCache.getDecsantOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, getOrgByCd(orgCd).getPlasOrgId(), null);
	}

	// 搜索员工涉及的中心列表
	public static List<WsPlasOrg> getRelationCenterOrgs(String uiid) {
	
		if (StringUtils.isBlank(uiid))
			return new ArrayList<WsPlasOrg>();

		List<WsPlasOrg> rtnList = new ArrayList<WsPlasOrg>();
		
		//负责机构
		List<WsPlasOrg> list = getMgrOrgListByUid(uiid);
		if (list != null){
			rtnList.addAll(list);
		}
		
		//所在中心
		WsPlasUser user = getUserByUiid(uiid);
		if(user != null){
			WsPlasOrg org = getCenterOrgById(user.getOrgId());
			if(org != null){
				rtnList.add(org);
			}
		}
		return rtnList;
	}

	public static String getParentId(String dimeCd, String orgId) {
		try{
			return mapDimeOrgId2PId.get(getKey(dimeCd, orgId));
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getParentId() exception! " ,e);
			return "";
		}
	}

	// 获取中心
	public static WsPlasOrg getCenterOrgById(String orgId) {
		try{
			String dimeCd = PlasCacheUtil.TREE_TYPE_LOGICAL;
			WsPlasOrg org = getOrgById(orgId);
			if (org == null)
				return null;
			if (DictContants.UAAP_ORG_TYPE_CENTER.equals(org.getOrgTypeCd()))
				return org;
			else {
				String parentId = getParentId(dimeCd, orgId);
				return getCenterOrgById(parentId);
			}
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getCenterOrgById() exception! " ,e);
			return null;
		}
	}
	
	// 获取分管
	public static WsPlasOrg getBranchOrgById(String orgId) {
		try{
			String dimeCd = PlasCacheUtil.TREE_TYPE_LOGICAL;
			WsPlasOrg org = getOrgById(orgId);
			if (org == null)
				return null;
			if (DictContants.UAAP_ORG_TYPE_BRANCH.equals(org.getOrgTypeCd()))
				return org;
			else {
				String parentId = getParentId(dimeCd, orgId);
				return getBranchOrgById(parentId);
			}
		}catch (Exception e) {
			//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getBranchOrgById() exception! " ,e);
			return null;
		}
	}

	public static String getCenterOrgIdById(String orgId) {
		try{
			WsPlasOrg org = getCenterOrgById(orgId);
			if (org == null)
				return "";
			else
				return org.getPlasOrgId();
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getCenterOrgIdById() exception! " ,e);
			return "";
		}
	}

	public static String getCenterOrgNameByOrgId(String orgId) {
		try{
			WsPlasOrg org = getCenterOrgById(orgId);
			if (org == null)
				return "";
			else
				return org.getOrgName();
		}catch (Exception e) {
	//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getCenterOrgNameByOrgId() exception! " ,e);
			return "";
		}
	}
	public static String getBranchOrgNameByOrgId(String orgId) {
		try{
			WsPlasOrg org = getBranchOrgById(orgId);
			if (org == null)
				return "";
			else
				return org.getOrgName();
		}catch (Exception e) {
			//		e.printStackTrace();
			log.error("//////////////////////////////////// PlasCache.getCenterOrgNameByOrgId() exception! " ,e);
			return "";
		}
	}

	/* ************************************************************** */
	// 在线人员
	public synchronized static void addOnlineCount() {
		Util.getPlasService().addOnlineCount(SpringSecurityUtils.getCurrentUiid());
	}

	public synchronized static void reduceOnlineCount() {
		Util.getPlasService().reduceOnlineCount(SpringSecurityUtils.getCurrentUiid());
	}

	public static long getUserOnlineCount() {
		return Util.getPlasService().getUserOnlineCount();
	}

	public static Set<String> getOnlineUiidSet() {
		return Util.getPlasService().getOnlineUiidSet();
	}
 
	/* ************************************************************** */
	// 取用户的上级负责人,以及上级所在机构
	public static Object[] getSuperior(String uiid) {
		return getSuperiorByOrgTypeCd(uiid, null);
	}

	public static Object[] getSuperiorByOrgTypeCd(String uiid, String orgTypeCd) {
		return getSuperior(uiid, orgTypeCd);
	}

	public static Object[] getSuperior(String uiid, String orgTypeCd) {

		// 1.取当前用户所在机构负责人,如果是本人,跳过
		// 2.取上级机构负责人

		// 取用户
		WsPlasUser user = getUserByUiid(uiid);
		WsPlasOrg org = getOrgById(user.getOrgId());
		return getParentUser(uiid, org, orgTypeCd);
	}

	/**
	 * 查找用户uiid的上级负责人
	 * 
	 * 注意:可能负责上级两级或多级机构。
	 * 
	 * @param uiid
	 * @param org
	 * @param orgTypeCd
	 * @return
	 */
	private static Object[] getParentUser(String uiid, WsPlasOrg org, String orgTypeCd) {
		String orgMgrId = org.getOrgMgrId();
		String parentOrgId = getLogicalParentId(org.getPlasOrgId());
		String targetUiid = "";
		WsPlasOrg targetOrg = org;
		if (uiid.equals(orgMgrId) || (StringUtils.isNotBlank(orgTypeCd) && !orgTypeCd.equals(org.getOrgTypeCd()))) {
			WsPlasOrg parentOrg = getOrgById(parentOrgId);
			String t = parentOrg.getOrgMgrId();
			if (uiid.equals(t) || (orgTypeCd != null && !orgTypeCd.equals(org.getOrgTypeCd())))
				// 这里可能出现 userCd = targetUserCd
				return getParentUser(uiid, parentOrg, orgTypeCd);
			else {
				targetOrg = parentOrg;
				targetUiid = t;
			}
		} else {
			targetUiid = orgMgrId;
		}
		return new Object[] { getUserByUiid(targetUiid), targetOrg };
	}

	public static String getLogicalParentId(String orgId) {
		return getParentId(PlasCacheUtil.TREE_TYPE_LOGICAL, orgId);
	}

	public static String getPhysicalParentId(String orgId) {
		return getParentId(PlasCacheUtil.TREE_TYPE_PHYSICAL, orgId);
	}
	
	//根据用户cd获取所在中心机构cd/Name
	public static WsPlasOrg getCenterOrgByUserId(String userId) {
		
	WsPlasUser user = getUserById(userId);
		if(user == null)
			return null;
		return getOrgById(getCenterOrgIdById(user.getOrgId()));
	}
	
	public static WsPlasOrg getCenterOrgByUiid(String uiid) {
		WsPlasUser user = getUserByUiid(uiid);
		if(user == null)
			return null;
		return getOrgById(getCenterOrgIdById(user.getOrgId()));
	}
	public static String getCenterOrgCdByUserId(String userId){
		WsPlasOrg org = getCenterOrgByUserId(userId);
		if(org!= null)
			return org.getOrgCd();
		else
			return "";
	}
	public static String getCenterOrgCdByUiid(String uiid){
		WsPlasOrg org = getCenterOrgByUiid(uiid);
		if(org!= null)
			return org.getOrgCd();
		else
			return "";
	}
	
	public static String getCenterOrgNameByUserId(String userId) {
		WsPlasOrg org = getCenterOrgByUserId(userId);
		if(org!= null)
			return org.getOrgName();
		else
			return "";
	}
	
	//职级
	public static Map<String,String> getMapRankList(){
		if(mapRank == null) {
			mapRank = new LinkedHashMap<String, String>();
		}
		return mapRank;
	}
	public static List<WsPlasUser> getUserListByRankCd(String rankCd){
		
		if(mapRankUserList == null)
			return new ArrayList<WsPlasUser>();
		List<WsPlasUser> list =  mapRankUserList.get(rankCd);
		if(list != null)
			return list;
		else
			return new ArrayList<WsPlasUser>();
	}
	
	
	//获取账号对应职位的相关机构(不考虑人员所在机构)
	//思路: acctId -> postionIds -> posOrgIds
	public static List<String> getRelPosOrgIdList(String acctId){
		return Util.getPlasService().getRelPosOrgIdList(acctId);
	}
	
	//获取账号对应的系统职位所属的上级部门的org列表
	//思路: acctId -> postionIds -> posOrgIds -> Bubble
	public static List<WsPlasOrg> getRelPosBubbleOrgCdList(String acctId){
		return getRelPosBubbleOrgCdList(acctId,null);
	}
	public static List<WsPlasOrg> getRelPosBubbleOrgCdList(String acctId,String treeType){
		if(StringUtils.isBlank(treeType)){
			treeType = PlasCacheUtil.TREE_TYPE_LOGICAL;
		}
		List<WsPlasOrg> returnOrgCds = new ArrayList();
		List<String> userOrgIds = PlasCache.getRelPosOrgIdList(SpringSecurityUtils.getCurrentAcctId());

		String userOrgId = null;
		List<WsPlasOrg> tempOrgs = null;
		for(int i=0;null!=userOrgIds && i<userOrgIds.size();i++){
			userOrgId = userOrgIds.get(i);
			returnOrgCds.add(PlasCache.getOrgById(userOrgId));
			tempOrgs = PlasCache.getBubbleOrgList(treeType, userOrgId);
			returnOrgCds.addAll(tempOrgs);
		}
		return returnOrgCds;
	}
	
	//判断某项目是否在当前用户所属的区域公司内
	public static boolean ifMyAreaByProjectCd(String project_cd, String currentAcctId, String treeType, String mapName){
		boolean returnValue = false;
        List<WsPlasOrg> userOrgs = PlasCache.getRelPosBubbleOrgCdList(currentAcctId, treeType);

		Map<String, String> mapArea = PlasCache.getDictDataMap(mapName);
		Set<Map.Entry<String, String>> set = mapArea.entrySet();
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
        	//遍历数据字典中的区域公司列表
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String org_cd = entry.getKey();
            String org_name = entry.getValue();
            
            //判断是否在当前的区域内 
            boolean if_in_this_area = false;
			String tempPosOrgCd = null;
			for(int i=0;null!=userOrgs&&i<userOrgs.size();i++){
				tempPosOrgCd = ((WsPlasOrg)(userOrgs.get(i))).getOrgCd();
				if (org_cd.equals(tempPosOrgCd)) {
					if_in_this_area = true;
					break;
				}
			}
			if(if_in_this_area){
				//如果在当前区域内，判断当前节点所属的项目是否这个区域下属的项目
				Map<String, String> map = PlasCache.getDictDataMap(org_name);
				Set<Map.Entry<String, String>> set2 = map.entrySet();
		        for (Iterator<Map.Entry<String, String>> it2 = set2.iterator(); it2.hasNext();) {
		            Map.Entry<String, String> entry2 = (Map.Entry<String, String>) it2.next();
		            String compare_project_cd = entry2.getKey();
					if(compare_project_cd.equalsIgnoreCase(project_cd)){
						returnValue = true;
						break;
					}
		        }
			}
			if(returnValue){
				break;
			}
        }
        return returnValue;
	}
	
	//获取字典类型
	public static WsAppDictType getDictType(String dictTypeCd){
		if(StringUtils.isBlank(dictTypeCd) || dictTypeList == null || dictTypeList.size() == 0)
			return null;
		
		for (WsAppDictType tDictType : dictTypeList) {
			if(dictTypeCd.equals(tDictType.getDictTypeCd()))
				return tDictType;
		}
		return null;
	}
	

	/**
	 * 构造区域机构树(根-区域-项目)
	 * @return
	 */
	public static TreePanelNode getAreaProjectTree(boolean multiFlg){
		
		TreePanelNode rootNode = TreePanelUtil.getRootOrg(); 
		rootNode.getChildren().add(TreePanelUtil.getDictNode(DictContants.AREA_ORG_NORTH, multiFlg));
		rootNode.getChildren().add(TreePanelUtil.getDictNode(DictContants.AREA_ORG_SOUTH, multiFlg));
//		rootNode.getChildren().add(TreePanelUtil.getDictNode(DictContants.AREA_ORG_SH, multiFlg));
		
		return rootNode;
	}

	public static Map<String, WsPlasAcct> getAcctIdAcctMap() {
		return acctIdAcctMap;
	}
	public static TreePanelNode getPhyOrgPosTree() {
		return phyOrgPosTree;
	}

	public static Map<String, WsPlasSysPosition> getSysIdPosMap() {
		return sysIdPosMap;
	}

	public static Map<String, List<WsPlasSysPosition>> getMapOrgPosList() {
		return mapOrgPosList;
	}

	public static Map<String, List<WsPlasNodeSysPosRel>> getMapNodeCdSysPosList() {
		return mapNodeCdSysPosList;
	}
	public static List<WsPlasNodeSysPosRel> getPlasNodeSysPosRels(String nodeCd){
		return mapNodeCdSysPosList.get(nodeCd);
	}
}
