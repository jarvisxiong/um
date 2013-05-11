/**
 * 
 */
package com.hhz.uums.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.plas.PlasOrgDimeManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgDime;

/**
 * @author huangj 2011-1-6
 */
public class DictMapUtil {
	private static AppParamManager appParamManager = SpringContextHolder.getBean("appParamManager");
	private static AppDictTypeManager appDictTypeManager = SpringContextHolder.getBean("appDictTypeManager");
	private static PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
	private static PlasOrgDimeManager plasOrgDimeManager = SpringContextHolder.getBean("plasOrgDimeManager");

	// 逻辑机构列表
	public static Map<String, String> getMapPlasOrgLogical() {
		List<PlasOrg> list = plasOrgManager.getAllPlasOrg();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");

		for (int i = 0; i < list.size(); i++) {
			PlasOrg org = list.get(i);
			map.put(org.getPlasOrgId(), org.getOrgName());
		}
		return map;
	}

	// 逻辑机构列表
	public static Map<String, String> getMapPlasOrgPhisical() {
		List<PlasOrg> list = plasOrgManager.getAllPlasOrg();
		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("", "");

		// 树根
		String rootOrgCd = appParamManager.getAppOrgTreeRootCd();
		String rootOrgName = appParamManager.getAppOrgTreeRootName();
		map.put(rootOrgCd, rootOrgName);

		String orgCd = "";
		String orgBizCd = "";
		String orgName = "";
		for (int i = 0; i < list.size(); i++) {
			PlasOrg org = list.get(i);
			orgCd = org.getOrgCd();
			orgBizCd = org.getOrgBizCd();
			orgName = org.getOrgName();
			map.put(orgCd, "[" + orgBizCd + "]" + orgName);
		}
		return map;
	}

	// 是否可用
	public static Map<Boolean, String> getMapEnableFlg() {
		Map<Boolean, String> map = new HashMap<Boolean, String>();
		map.put(null, "");
		map.put(new Boolean(true), "是");
		map.put(new Boolean(false), "否");
		return map;
	}

	public static Map<String, String> getMapEnableFlgNum() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		map.put("1", "是");
		map.put("0", "否");
		return map;
	}

	public static Map<String, String> getMapEnabledFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}

	/**
	 * 密码策略
	 * 
	 * @return
	 */
	public static Map<String, String> getMapPwdStrategyCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_PWD_STRATEGY);
	}

	// 是否锁定mac地址
	public static Map<String, String> getMapMacLockedFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_MAC_LOCKED_FLG);
	}

	// 外部邮箱同步标识
	public static Map<String, String> getMapEmailFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_EMAIL_FLG);
	}

	// EAS用户同步标识
	public static Map<String, String> getMapEasFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_EAS_FLG);
	}

	// Mysoft用户同步标识
	public static Map<String, String> getMapMysoftFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_MYSOFT_FLG);
	}

	// 菜单类型
	public static Map<String, String> getMapAppMenuType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APP_MENU_TYPE);
	}

	// 员工状态
	public static Map<String, String> getMapServiceStatus() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SERVICE_STATUS);
		return map;
	}

	// 婚姻状况
	public static Map<String, String> getMapMarrigeStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_MARRIGE_STATUS);
	}

	// 用户类型
	public static Map<String, String> getMapMemberType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_MEMBER_TYPE);
	}

	// 民族
	public static Map<String, String> getMapNation() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_NATION);
	}

	// 机构类型
	public static Map<String, String> getMapOrgType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ORG_TYPE);
	}

	// 其他
	public static Map<String, String> getMapOtherType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_OTHER_TYPE);
	}

	// 政治面貌
	public static Map<String, String> getMapPolitics() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_POLITICS);
	}

	// 职位
	public static Map<String, String> getMapPosition() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_POSITION_TYPE);
		return map;
	}

	// 职称
	public static Map<String, String> getMapProfessionType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_PROFESSION_TYPE);
	}

	// 学历
	public static Map<String, String> getMapSchoolRecord() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SCHOOL_RECORD);
	}

	// 性别
	public static Map<String, String> getMapSex() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SEX);
	}

	// 账户状态
	public static Map<String, String> getMapAcctStatus() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_USER_STATUS);
		return map;
	}

	public static String getMapAcctStatus(String status) {
		return getMapAcctStatus().get(status);
	}

	public static Map<String, String> getMapAcctAuditStatus() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ACCT_AUDIT);
		return map;
	}

	public static String getMapAcctAuditStatus(String status) {
		return getMapAcctAuditStatus().get(status);
	}

	// 证件类型
	public static Map<String, String> getMapIdCardType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ID_CARD_TYPE);
	}

	// 特殊用户标识
	public static Map<String, String> getMapSpecialUserFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SPECIAL_USER_FLG);
	}

	// 用户类型
	public static Map<String, String> getMapUserType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_USER_TYPE);
	}

	// 用户信息来源
	public static Map<String, String> getMapSourceType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SOURCE_TYPE);
	}

	// 认证类型
	public static Map<String, String> getMapAuthenticType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_AUTHENTIC_TYPE);
	}

	// 职级
	public static Map<String, String> getMapPermLevel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_PERM_LEVEL, true);// 这里一定要升序,专员在前,总裁在后
	}

	public static Map<String, String> getMapPermLevelEmpty() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_PERM_LEVEL, true, true);// 这里一定要升序,专员在前,总裁在后; 允许为空
	}

	// 逻辑机构
	public static Map<String, String> getMapLogicalPlasOrg() {
		List<PlasOrg> list = plasOrgManager.getAllPlasOrg();
		Map<String, String> map = new LinkedHashMap<String, String>();

		// 树根
		String rootOrgCd = appParamManager.getAppOrgTreeRootCd();
		String rootOrgName = appParamManager.getAppOrgTreeRootName();
		// 逻辑视图一定要选择机构
		map.put(rootOrgCd, rootOrgName);

		String orgCd = "";
		String orgBizCd = "";
		String orgName = "";
		for (int i = 0; i < list.size(); i++) {
			PlasOrg org = list.get(i);
			orgCd = org.getOrgCd();
			orgBizCd = org.getOrgBizCd();
			orgName = org.getOrgName();
			map.put(orgCd, "[" + orgBizCd + "]" + orgName);
		}
		return map;
	}

	// 物理机构
	public static Map<String, String> getMapPhysicalPlasOrg() {
		List<PlasOrg> list = plasOrgManager.getAllPlasOrg();
		Map<String, String> map = new LinkedHashMap<String, String>();

		// 树根
		String rootOrgCd = appParamManager.getAppOrgTreeRootCd();
		String rootOrgName = appParamManager.getAppOrgTreeRootName();
		// 物理视图可以不选择机构
		map.put("", "");
		map.put(rootOrgCd, rootOrgName);

		String orgCd = "";
		String orgBizCd = "";
		String orgName = "";
		for (int i = 0; i < list.size(); i++) {
			PlasOrg org = list.get(i);
			orgCd = org.getOrgCd();
			orgBizCd = org.getOrgBizCd();
			orgName = org.getOrgName();
			map.put(orgCd, "[" + orgBizCd + "]" + orgName);
		}
		return map;
	}

	public static Map<String, String> getMapRoleType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ROLE_TYPE);
	}

	// 是否固化:1-是0-否
	public static Map<String, String> getMapFixedFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}

	// 标签组
	public static Map<String, String> getMapUaapTagType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_TAG_TYPE);
	}

	// email密码是否重置
	public static Map<String, String> getMapEmailPasswordSetFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG);
	}

	// eas密码是否重置
	public static Map<String, String> getMapEasPasswordSetFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_EAS_PASSWORD_SET_FLG);
	}

	// 页面状态
	public static Map<String, String> getMapPageStatus() {
		return getMapEnableFlgNum();
	}

	// 账号状态
	public static Map<String, String> getMapUserStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_USER_STATUS);
	}

	// 获取操作列表
	public static Map<String, String> getMapOperCd() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_LOG_OPER_TYPE);
		Map<String, String> rtnMap = new LinkedHashMap<String, String>();
		rtnMap.put("", "");
		for (String key : map.keySet()) {
			rtnMap.put(key, map.get(key));
		}
		return rtnMap;
	}

	// 获取模块列表
	public static Map<String, String> getMapModuleCd() {
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("", "");
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_LOG_MODULE_TYPE);
		String tmpKey = null;
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			tmpKey = iterator.next();
			retMap.put(map.get(tmpKey), map.get(tmpKey));
		}
		return retMap;
	}

	// PLAS_APP_TYPE_CD 接入应用类型
	public static Map<String, String> getMapAppType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_APP_TYPE);
	}

	// PLAS_OS_PLATFORM_CD 操作系统平台
	public static Map<String, String> getMapOsPlatform() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_OS_PLATFORM);
	}

	// PLAS_WEB_APP_SERVER_TYPE_CD WEB服务器
	public static Map<String, String> getMapWebAppServerType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_WEB_APP_SERVER_TYPE);
	}

	// PLAS_J2EE_SERVER_TYPE_CD J2EE服务器
	public static Map<String, String> getMapJ2eeServerType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_J2EE_SERVER_TYPE);
	}

	// PLAS_BASE_PROD_FLG 是否基于某产品
	public static Map<String, String> getMapBaseProdFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_BASE_PROD_FLG);
	}

	// PLAS_SUPPORT_LDAP_FLG 是否支持LDAP
	public static Map<String, String> getMapSupportLdapFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SUPPORT_LDAP_FLG);
	}

	// PLAS_SUPPORT_LDAP_CDS 支持LDAP(多选)
	public static Map<String, String> getMapSupportLdapType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SUPPORT_LDAP_TYPE);
	}

	// PLAS_SUPPORT_SSO_FLG 是否实现单点登录
	public static Map<String, String> getMapSupportSsoFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_SUPPORT_SSO_FLG);
	}

	// PLAS_JOIN_LOGIN_DOMAIN_FLG 是否加入登录域 (即与其他系统实现单点登录)
	public static Map<String, String> getMapJoinLoginDomainFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_JOIN_LOGIN_DOMAIN_FLG);
	}

	public static Map<String, String> getMapAcctAuditFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ACCT_AUDIT_FLG);
	}

	public static Map<String, String> getMapAcctType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_ACCT_TYPE);
	}

	// 机构维度
	public static Map<String, String> getMapDimeType() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<PlasOrgDime> list = plasOrgDimeManager.getAll("sequenceNo", true);
		for (PlasOrgDime tmpDime : list) {
			map.put(tmpDime.getDimeCd(), tmpDime.getDimeName());
		}
		return map;
	}

	/**
	 * 审批状态
	 */
	public static Map<String, String> getMapApproveStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APPROVE_STATUS);
	}

	/**
	 * 审批申请类别
	 */
	public static Map<String, String> getMapApproveApplyType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APPROVE_APPLY_TYPE, true, true);
	}

	/**
	 * 审批角色名称
	 */
	public static Map<String, String> getMapPermType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PERM_TYPE);
	}


	//(网批) 机构节点类型/机构种类
	public static Map<String, String> getMapNodeLevelCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.NODE_LEVEL_CD,true);
	}

	public static Map<String, String> getMapOrgKindCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.ORG_KIND_CD,true);
	}

}
