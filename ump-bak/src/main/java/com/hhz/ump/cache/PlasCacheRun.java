package com.hhz.ump.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
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
import com.hhz.uums.service.WSPlasService;

/**
 * @author huangbj
 * 后台缓存程序
 */
@Service
public class PlasCacheRun {
	
	private static Log log = LogFactory.getLog(PlasCacheRun.class);

	private static PlasCacheRun instance;
 
	/* ******************** 定时 ********************** */
	// 维度
	private static List<WsPlasOrgDime> dimeList = null;

	// 机构
	private static List<WsPlasOrg> orgEnableList = null;
	private static List<WsPlasOrg> orgAllList = null;
	private static Map<String, WsPlasAcct> uiidAcctMap = null;
	private static Map<String, WsPlasAcct> acctIdAcctMap = null;

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
	private static Map<String, WsPlasRole> roleNameMap = null;

	// PLAS数据字典
	private static List<WsAppDictType> dictTypeList = null;
	private static List<WsAppDictData> dictDataList = null;
	private static Map<String, List<WsAppDictData>> dictTypeDataMap = null;
	
	// 只读用户
	private static Map<String, String> readOnlyUserMap = new HashMap<String, String>();

	// 机构关系
	private static List<WsPlasOrgRel> orgRelList = null;

	// 网批节点和系统职务对应关系表
	private static List<WsPlasNodeSysPosRel> plasNodeSysPosRels = null;
	/* ******************** 定时 ********************** */
	// 机构映射人员列表
	private static Map<String, WsPlasUser> idUserMap = new HashMap<String, WsPlasUser>();
	private static Map<String, WsPlasUser> cdUserMap = new HashMap<String, WsPlasUser>();
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
	private static Map<String, List<WsPlasAcct>> mapOrgAcctList   = new HashMap<String, List<WsPlasAcct>>();
	private static Map<String, List<WsPlasSysPosition>> mapOrgPosList = new HashMap<String, List<WsPlasSysPosition>>();
	
	
	private static Map<String, List<WsAcctSysposRel>> mapUiidPosList = new HashMap<String, List<WsAcctSysposRel>>();

	private static Map<String, List<WsPlasNodeSysPosRel>> mapNodeCdSysPosList=new HashMap<String, List<WsPlasNodeSysPosRel>>();
	//职级
	private static Map<String,String> mapRank = new LinkedHashMap<String, String>();
	private static Map<String,List<WsPlasUser>> mapRankUserList = new HashMap<String, List<WsPlasUser>>();
	

	// 首页机构用户树
	private static TreePanelNode phyOrgUserTree = null;
	private static TreePanelNode phyOrgPosTree = null;
	
	public static PlasCacheRun getInstance() {
		if (instance == null) {
			instance = new PlasCacheRun();
		}
		return instance;
	}
	
	//刷新缓存
	public static boolean  refreshPlasCache(){
		try{
 
			long start = System.currentTimeMillis();
			
			WSPlasService service = Util.getPlasService();
			
			// 维度列表
			dimeList = service.getDimeList();
			if(dimeList == null){
				dimeList = new ArrayList<WsPlasOrgDime>();
			}
			long end_dime = System.currentTimeMillis();
			log.info("//////////////////////////// 维度列表! 用时：" + (end_dime - start)/1000.00 +" 秒!");
			
			
			// 机构列表
			orgAllList = service.getAllOrgList();
			if(orgAllList == null){
				orgAllList = new ArrayList<WsPlasOrg>();
			}
			long end_org = System.currentTimeMillis();
			log.info("//////////////////////////// 机构列表! 用时：" + (end_org - end_dime)/1000.00 +" 秒!");
			
			idOrgMap = new HashMap<String, WsPlasOrg>();
			cdOrgMap = new HashMap<String, WsPlasOrg>();
			bizCdOrgMap = new HashMap<String, WsPlasOrg>();
			orgTypeCdListMap = new HashMap<String, List<WsPlasOrg>>();
			uiidMgrOrgMap = new HashMap<String, List<WsPlasOrg>>();
			orgEnableList = new ArrayList<WsPlasOrg>();
	
			String orgTypeCd = null;
			String orgMgrUiid = null;
			
			if(orgAllList!= null){
				List<WsPlasOrg> orgs = null;
				for (WsPlasOrg o : orgAllList) {
					
					if(o == null){
						continue;
					}
					if(StringUtils.isNotBlank(o.getPlasOrgId())){
						idOrgMap.put(o.getPlasOrgId(), o);
					}
					if(StringUtils.isNotBlank(o.getOrgCd())){
						cdOrgMap.put(o.getOrgCd(), o);
					}
					if(StringUtils.isNotBlank(o.getOrgBizCd())){
						bizCdOrgMap.put(o.getOrgBizCd(), o);
					}
		
					if (o.getActiveBl()!= null && o.getActiveBl().booleanValue()
							&& o.getVisableFlg()!= null && o.getVisableFlg().booleanValue()) {//add by huangbijin 2012-05-31 chenjj1
						orgEnableList.add(o);
					}
		
					orgTypeCd = o.getOrgTypeCd();
					if (StringUtils.isNotBlank(orgTypeCd)) {
						if (orgTypeCdListMap.containsKey(orgTypeCd)) {
							orgTypeCdListMap.get(orgTypeCd).add(o);
						} else {
							orgs = new ArrayList<WsPlasOrg>();
							orgs.add(o);
							orgTypeCdListMap.put(orgTypeCd, orgs);
						}
					}
		
					orgMgrUiid = o.getOrgMgrId();
					if (StringUtils.isNotBlank(orgMgrUiid)) {
						if (orgTypeCdListMap.containsKey(orgMgrUiid)) {
							uiidMgrOrgMap.get(orgMgrUiid).add(o);
						} else {
							orgs = new ArrayList<WsPlasOrg>();
							orgs.add(o);
							uiidMgrOrgMap.put(orgMgrUiid, orgs);
						}
					}
				}
			}
			long end_org_process = System.currentTimeMillis();
			log.info("//////////////////////////// 处理机构列表! 用时：" + (end_org_process - end_org)/1000.00 +" 秒!");

			// 字典
			dictTypeList = service.getAllTypeList();
			if(dictTypeList == null){
				dictTypeList = new ArrayList<WsAppDictType>();
			}
			long end_dict = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索字典列表! 用时：" + (end_dict - end_org_process)/1000.00 +" 秒!");
			
			dictDataList = service.getAllDataList();
			if(dictDataList == null){
				dictDataList = new ArrayList<WsAppDictData>();
			}
			dictTypeDataMap = new HashMap<String, List<WsAppDictData>>();
			if(dictDataList!= null){
				String dictTypeCd = null;
				for (WsAppDictData o : dictDataList) {
					dictTypeCd = o.getDictTypeCd();
					if (StringUtils.isNotBlank(dictTypeCd)) {
						if (dictTypeDataMap.containsKey(dictTypeCd)) {
							dictTypeDataMap.get(dictTypeCd).add(o);
						} else {
							List<WsAppDictData> datas = new ArrayList<WsAppDictData>();
							datas.add(o);
							dictTypeDataMap.put(dictTypeCd, datas);
						}
					}
				}
			}
			long end_dict_process = System.currentTimeMillis();
			log.info("//////////////////////////// 处理字典列表! 用时：" + (end_dict_process - end_dict)/1000.00 +" 秒!");

			//过滤账号
			readOnlyUserMap = getDictDataMap(DictContants.PLAS_READ_ONLY_USER);
			if(readOnlyUserMap == null){
				readOnlyUserMap = new HashMap<String, String>();
			}
			
			//用户列表
			userList = service.getAllUserList();
			if(userList == null){
				userList = new ArrayList<WsPlasUser>();
			}
			long end_user = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索用户列表! 用时：" + (end_user - end_dict)/1000.00 +" 秒!");
			
			userActiveList = new ArrayList<WsPlasUser>();
			idUserMap = new HashMap<String, WsPlasUser>();
			cdUserMap = new HashMap<String, WsPlasUser>();
			uiidUserMap = new HashMap<String, WsPlasUser>();
			bizCdUserMap = new HashMap<String, WsPlasUser>();
			mapOrgIdUserList = new HashMap<String, List<WsPlasUser>>();
			mapOrgCdUserList = new HashMap<String, List<WsPlasUser>>();
			mapRankUserList = new LinkedHashMap<String, List<WsPlasUser>>();
			
			if(userList!= null){
				String orgId = null;
				String orgCd = null;
				String permisionCd = null;
				List<WsPlasUser> tmpList = null;
				List<WsPlasUser> newUserList = null;
				for (WsPlasUser o : userList) {
					
					if(o == null){
						continue;
					}
					
					//职级
					permisionCd = o.getPermissionLevelCd();
					if(StringUtils.isNotBlank(permisionCd)){
						if(mapRankUserList.keySet().contains(permisionCd)){
							mapRankUserList.get(permisionCd).add(o);
						}else{
							tmpList = new ArrayList<WsPlasUser>();
							tmpList.add(o);
							mapRankUserList.put(permisionCd, tmpList);
						}
					}
					
					if(StringUtils.isNotBlank(o.getPlasUserId())){
						idUserMap.put(o.getPlasUserId(), o);
					}
					if(StringUtils.isNotBlank(o.getUserCd())){
						cdUserMap.put(o.getUserCd(), o);
					}
					if(StringUtils.isNotBlank(o.getUiid())){
						uiidUserMap.put(o.getUiid(), o);
					}
					if(StringUtils.isNotBlank(o.getUserBizCd())){
						bizCdUserMap.put(o.getUserBizCd(), o);
					}
					
					if (readOnlyUserMap!= null && readOnlyUserMap.keySet().contains(o.getUiid())){
						//排除过滤账号
						log.info("---------------- 排除过滤账号：" + o.getUiid());
					}else{
						if (DictContants.PLAS_SERVICE_STATUS_ON.equals(o.getServiceStatusCd())) {
							userActiveList.add(o);
							orgId = o.getOrgId();
							if (StringUtils.isBlank(orgId)) {
								log.info("未找到上级机构! " + o.getUserName() + "[" + o.getUiid() + "]");
							} else {
								if (mapOrgIdUserList.containsKey(orgId)) {
									mapOrgIdUserList.get(orgId).add(o);
								} else {
									newUserList = new ArrayList<WsPlasUser>();
									newUserList.add(o);
									mapOrgIdUserList.put(orgId, newUserList);
								}
							}
							orgCd = o.getOrgCd();
							if (StringUtils.isBlank(orgCd)) {
								log.info("未找到上级机构! " + o.getUserName() + "[" + o.getUiid() + "]");
							} else {
								if (mapOrgCdUserList.containsKey(orgCd)) {
									mapOrgCdUserList.get(orgCd).add(o);
								} else {
									newUserList = new ArrayList<WsPlasUser>();
									newUserList.add(o);
									mapOrgCdUserList.put(orgCd, newUserList);
								}
							}
						}
					}
				}
			}
			long end_user_process = System.currentTimeMillis();
			log.info("//////////////////////////// 处理用户列表! 用时：" + (end_user_process - end_user)/1000.00 +" 秒!");
	
			//账号列表
			acctList = service.getAllAcctList();
			long end_acct = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索账号列表! 用时：" + (end_acct - end_user_process)/1000.00 +" 秒!");
			
			acctActiveList = new ArrayList<WsPlasAcct>();
			uiidAcctMap = new HashMap<String, WsPlasAcct>();
			acctIdAcctMap = new HashMap<String, WsPlasAcct>();
			
			if(acctList != null){
				for (WsPlasAcct acct : acctList) {
					acctIdAcctMap.put(acct.getPlasAcctId(), acct);
					uiidAcctMap.put(acct.getUiid(), acct);
					if (DictContants.UAAP_USER_STATUS_CREATE.equals(acct.getStatusCd())
						||DictContants.UAAP_USER_STATUS_FREEZE.equals(acct.getStatusCd())
							|| DictContants.UAAP_USER_STATUS_UNFREEZE.equals(acct.getStatusCd())) {
						acctActiveList.add(acct);
					}
				}
			}else{
				acctList = new ArrayList<WsPlasAcct>();
			}
			long end_acct_process = System.currentTimeMillis();
			log.info("//////////////////////////// 处理账号列表! 用时：" + (end_acct_process - end_acct)/1000.00 +" 秒!");
			
			//角色列表
			roleList = service.getRoleList();
			long end_role = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索角色列表! 用时：" + (end_role - end_acct_process)/1000.00 +" 秒!");
			
			if(roleList == null){
				roleList = new ArrayList<WsPlasRole>();
			}
			roleCdMap = new HashMap<String, WsPlasRole>();
			roleNameMap = new HashMap<String, WsPlasRole>();
			if(roleList != null){
				for (WsPlasRole role : roleList) {
					roleCdMap.put(role.getRoleCd(), role);
					roleNameMap.put(role.getRoleName(), role);
				}
			}
			long end_role_process = System.currentTimeMillis();
			log.info("//////////////////////////// 处理角色列表! 用时：" + (end_role_process - end_role)/1000.00 +" 秒!");
	
			
			orgRelList = service.getOrgRelList();
			if(orgRelList == null){
				orgRelList = new ArrayList<WsPlasOrgRel>();
			}
			long end_org_rel = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索机构关系列表! 用时：" + (end_org_rel - end_role_process)/1000.00 +" 秒!");
			
			
			sysPosList = service.getSysPositionList();
			
			if(sysPosList == null){
				sysPosList = new ArrayList<WsPlasSysPosition>();
			} 
			long end_sys_pos = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索系统职位列表! 用时：" + (end_sys_pos - end_org_rel)/1000.00 +" 秒!");
			
			
			sysIdPosMap = new HashMap<String, WsPlasSysPosition>();
			for (WsPlasSysPosition t: sysPosList) {
				sysIdPosMap.put(t.getPlasSysPositionId(), t);
			}
			
			// 机构与职位
			mapOrgPosList = PlasCacheUtil.getOrgSysPosMap(sysPosList);
			long end_org_pos = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索机构与职位关系列表! 用时：" + (end_org_pos - end_sys_pos)/1000.00 +" 秒!");
			
			
			List<WsAcctSysposRel> relList = service.getAcctSysposRelList();
			if(relList == null){
				relList = new ArrayList<WsAcctSysposRel>(); 
			}
			long end_rel = System.currentTimeMillis();
			log.info("//////////////////////////// 搜索账号与职位关系列表! 用时：" + (end_rel - end_org_pos)/1000.00 +" 秒!");
			
			
			
			mapUiidPosList = new HashMap<String, List<WsAcctSysposRel>>();
			if(relList != null){
				String uiid = null;
				List<WsAcctSysposRel> newList = null;
				for (WsAcctSysposRel rel : relList) {
					uiid = rel.getUiid();
					if(mapUiidPosList.keySet().contains(uiid)){
						mapUiidPosList.get(uiid).add(rel);
					}else{
						newList = new ArrayList<WsAcctSysposRel>();
						newList.add(rel);
						mapUiidPosList.put(uiid, newList);
					}
				}
			}
	
			// 要先初始化orgMap
			processDimeOrgRel();
			long end_dime_org_rel = System.currentTimeMillis();
			log.info("//////////////////////////// 处理维度+机构间关系/职位列表! 用时：" + (end_dime_org_rel - end_rel)/1000.00 +" 秒!");
	
			// 物理树
			// 机构-人员（职务树)
			phyOrgUserTree = PlasCacheUtil.buildOrgUserTree(
					PlasCacheUtil.getRootTreeNodeOrg(),
					mapDimeOrgOrgsMap.get(PlasCacheUtil.TREE_TYPE_PHYSICAL), 
					mapOrgIdUserList,
					PlasCacheUtil.NODE_CHECKED_UNDEFINED);
			
			// 机构-职位（职位树）
			phyOrgPosTree = PlasCacheUtil.buildOrgPosTreeNoCheck(getSysPosList(), getAcctIdAcctMap(), getIdUserMap());
			
			
			long  end_tree = System.currentTimeMillis();
			log.info("//////////////////////////// 处理物理树! 用时：" + ( end_tree - end_dime_org_rel)/1000.00 +" 秒!");
			
	
			//职级
			mapRank = getDictDataMap(DictContants.PLAS_POSITION_LEVEL);
			long end_pos_level = System.currentTimeMillis();
			log.info("//////////////////////////// 处理职级! 用时：" + (end_pos_level - end_tree)/1000.00 +" 秒!");
			
			plasNodeSysPosRels=service.getPlasNodeSysPosRel();
			if (plasNodeSysPosRels == null){
				plasNodeSysPosRels=new ArrayList<WsPlasNodeSysPosRel>();
			}
			
			mapNodeCdSysPosList=new HashMap<String, List<WsPlasNodeSysPosRel>>();
			for(WsPlasNodeSysPosRel nodeSysPosRel: plasNodeSysPosRels){
				String nodeCd=nodeSysPosRel.getNodeCd();
				if (!mapNodeCdSysPosList.keySet().contains(nodeCd)){
					List<WsPlasNodeSysPosRel> sysPosRels=new ArrayList<WsPlasNodeSysPosRel>();
					mapNodeCdSysPosList.put(nodeCd, sysPosRels);
				}
				mapNodeCdSysPosList.get(nodeCd).add(nodeSysPosRel);
			}
			
			long end_node_rel = System.currentTimeMillis();
			log.info("//////////////////////////// 网批节点和系统职务关系! 用时：" + (end_node_rel - end_pos_level)/1000.00 +" 秒!");

			long end = System.currentTimeMillis();
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> 一共用时：" + (end - start)/1000.00 +" 秒!");
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCacheRun.refreshPlasCache() exception! " + e.getCause());
			return false;
		}
	}
	
	
	public static void cleanCache(){
		// 维度
		dimeList = null;

		// 机构
		orgEnableList = null;
		orgAllList = null;
		uiidAcctMap = null;
		
		// 系统职位
		sysPosList = null;
		sysIdPosMap = null;

		// 账号
		acctList = null;
		acctActiveList = null;

		// 人员
		userList = null;
		userActiveList = null;

		// PD对应的角色列表
		roleList = null;
		roleCdMap = null;
		roleNameMap = null;

		// PLAS数据字典
		dictTypeList = null;
		dictDataList = null;
		dictTypeDataMap = null;

		// 机构关系
		orgRelList = null;

		idUserMap = null;
		cdUserMap = null;
		uiidUserMap = null;
		bizCdUserMap = null;
		acctIdAcctMap = null;
		idOrgMap = null;
		cdOrgMap = null;
		bizCdOrgMap = null;
		orgTypeCdListMap = null;

		uiidMgrOrgMap = null;

		mapDimeOrgOrgsMap = null;
		mapDimeOrgId2PId = null;
		mapDimePid2OrgId = null;

		mapOrgIdUserList = null;
		mapOrgCdUserList = null;
		mapOrgAcctList = null;
		mapOrgPosList = null;
		
		mapUiidPosList = null;
		
		//职级
		mapRank = null;
		mapRankUserList = null;
		
		//机构-人员树/ 机构-职位树
		phyOrgUserTree = null;
		phyOrgPosTree = null;
	}

	public static Map<String, List<WsPlasOrg>> getOrgOrgsMap(String dimeCd) {
		try{
			return mapDimeOrgOrgsMap.get(dimeCd);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCacheRun.getOrgOrgsMap() exception! " + e.getCause());
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
	public static boolean processDimeOrgRel() {

		try{
			// Map<String,String>
			mapDimeOrgId2PId = new HashMap<String, String>();
			mapDimePid2OrgId = new HashMap<String, String>();
	
			// map(dimeCd,map(parentId,wsPlasOrg))
			mapDimeOrgOrgsMap = new HashMap<String, Map<String, List<WsPlasOrg>>>();
	
			// map(parentId,wsPlasOrg)
			Map<String, List<WsPlasOrg>> orgOrgsMap = new HashMap<String, List<WsPlasOrg>>();
			
			if(orgRelList!= null){
				String dimeCd = null;
				String orgId = null;
				String parentId = null;
				List<WsPlasOrg> newOrgList = null;
				for (WsPlasOrgRel rel : orgRelList) {
					// 维度
					dimeCd = rel.getDimeCd();
					orgId = rel.getOrgId();
					parentId = rel.getParentId();
					
					if (StringUtils.isBlank(dimeCd)) {
						log.info("维度为空!" + rel.getPlasDimeOrgRelId());
						continue;
					} else {
		
						//这里，不管机构是什么状态(即是否可用，是否显示)
						mapDimeOrgId2PId.put(getKey(dimeCd, orgId), parentId);
						mapDimePid2OrgId.put(getKey(dimeCd, parentId), orgId);
		
						if (!mapDimeOrgOrgsMap.keySet().contains(dimeCd)) {
							mapDimeOrgOrgsMap.put(dimeCd, new HashMap<String, List<WsPlasOrg>>());
						}
		
						orgOrgsMap = mapDimeOrgOrgsMap.get(dimeCd);
						// 上级
						parentId = rel.getParentId();
						if (StringUtils.isBlank(parentId)) {
							log.info("未找到上级机构! " + rel.getOrgName() + "[" + rel.getOrgCd() + "]");
							parentId = PlasCacheUtil.DEFAULT_ROOT_ORG_ID;
						}
		
						if (orgOrgsMap.containsKey(parentId)) {
							orgOrgsMap.get(parentId).add(getOrgById(rel.getOrgId()));
						} else {
							newOrgList = new ArrayList<WsPlasOrg>();
							newOrgList.add(getOrgById(rel.getOrgId()));
							orgOrgsMap.put(parentId, newOrgList);
						}
					}
				}
			}
			return true;
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCacheRun.processDimeOrgRel exception! " + e.getCause());
			return false;
		}
	}

	// private
	public static String getKey(String dimeCd, String orgId) {
		return dimeCd + "_" + orgId;
	}

	/* ************************************************************************************ */

	
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

	public static Map<String, String> getDictDataMap(String dictTypeCd) {
		try{
			Map<String, String> values = new LinkedHashMap<String, String>();
			if(dictTypeDataMap != null){
				List<WsAppDictData> datas = dictTypeDataMap.get(dictTypeCd);
				if(datas != null){
					for (WsAppDictData o : datas) {
						if(o!= null){
							values.put(o.getDictCd(), o.getDictName());
						}
					}
				}
			}
			return values;
		}catch (Exception e) {
//			e.printStackTrace();
			log.error("//////////////////////////////////// PlasCacheRun.getDictDataMap() exception! " + e.getCause());
			return new HashMap<String, String>();
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

	// 获取acct
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

	public static Log getLog() {
		return log;
	}

	public static Map<String, WsPlasAcct> getUiidAcctMap() {
		return uiidAcctMap;
	}

	public static Map<String, WsPlasRole> getRoleCdMap() {
		return roleCdMap;
	}

	public static Map<String, WsPlasRole> getRoleNameMap() {
		return roleNameMap;
	}

	public static Map<String, List<WsAppDictData>> getDictTypeDataMap() {
		return dictTypeDataMap;
	}

	public static List<WsPlasOrgRel> getOrgRelList() {
		return orgRelList;
	}

	public static Map<String, WsPlasUser> getIdUserMap() {
		return idUserMap;
	}

	public static Map<String, WsPlasUser> getCdUserMap() {
		return cdUserMap;
	}

	public static Map<String, WsPlasUser> getUiidUserMap() {
		return uiidUserMap;
	}

	public static Map<String, WsPlasUser> getBizCdUserMap() {
		return bizCdUserMap;
	}

	public static Map<String, WsPlasOrg> getIdOrgMap() {
		return idOrgMap;
	}

	public static Map<String, WsPlasOrg> getCdOrgMap() {
		return cdOrgMap;
	}

	public static Map<String, WsPlasOrg> getBizCdOrgMap() {
		return bizCdOrgMap;
	}

	public static Map<String, List<WsPlasOrg>> getOrgTypeCdListMap() {
		return orgTypeCdListMap;
	}

	public static Map<String, List<WsPlasOrg>> getUiidMgrOrgMap() {
		return uiidMgrOrgMap;
	}

	public static Map<String, Map<String, List<WsPlasOrg>>> getMapDimeOrgOrgsMap() {
		return mapDimeOrgOrgsMap;
	}

	public static Map<String, String> getMapDimeOrgId2PId() {
		return mapDimeOrgId2PId;
	}

	public static Map<String, String> getMapDimePid2OrgId() {
		return mapDimePid2OrgId;
	}

	public static Map<String, List<WsPlasAcct>> getMapOrgAcctList() {
		return mapOrgAcctList;
	}

	public static Map<String, List<WsPlasSysPosition>> getMapOrgPosList() {
		return mapOrgPosList;
	}

	public static Map<String, String> getMapRank() {
		return mapRank;
	}

	public static Map<String, List<WsPlasUser>> getMapRankUserList() {
		return mapRankUserList;
	}

	public static TreePanelNode getPhyOrgUserTree() {
		return phyOrgUserTree;
	}
	 

	public static Map<String, WsPlasAcct> getAcctIdAcctMap() {
		return acctIdAcctMap;
	}

	public static TreePanelNode getPhyOrgPosTree() {
		return phyOrgPosTree;
	}

	public static Map<String, String> getReadOnlyUserMap() {
		return readOnlyUserMap;
	}

	public static Map<String, WsPlasSysPosition> getSysIdPosMap() {
		return sysIdPosMap;
	}

	public static List<WsPlasNodeSysPosRel> getPlasNodeSysPosRels() {
		return plasNodeSysPosRels;
	}

	public static Map<String, List<WsPlasNodeSysPosRel>> getMapNodeCdSysPosList() {
		return mapNodeCdSysPosList;
	}

	/* ******************************************************************/
	// get/set
	/* ******************************************************************/
	
}
