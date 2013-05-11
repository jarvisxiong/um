/**
 * 
 */
package com.hhz.ump.util;

import org.apache.commons.lang.xwork.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResBillTemplet;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * 用于部门、基础代码Code转Name
 * 
 * @author huangj 2010-2-6
 */
public class CodeNameUtil {

	private static AppDictTypeManager appDictTypeManager = SpringContextHolder.getBean("appDictTypeManager");

	/**
	 * 根据机构CD获取机构名称
	 * 
	 * @param deptCd
	 * @return
	 */
	public static String getDeptNameByCd(String deptCd) {
		WsPlasOrg org = PlasCache.getOrgByCd(deptCd);
		if (org == null)
			return "";
		else
			return org.getOrgName();
	}

	/**
	 * 根据机构CD获取机构简称
	 * 
	 * @param deptCd
	 * @return
	 */
	public static String getDeptShortNameByCd(String deptCd) {
		WsPlasOrg org = PlasCache.getOrgByCd(deptCd);
		if (org == null)
			return "";
		else
			return org.getShortOrgName();
	}

	public static String getUserNameByCd(String uiid) {
		String value=null;
		WsPlasUser user = PlasCache.getUserByUiid(uiid);
		if (user == null) {
			value= uiid;
		} else{
			WsPlasAcct plasAcct= PlasCache.getAcctByUiid(uiid);
			if (plasAcct.getStatusCd().equals("4")){
				value=user.getUserName()+"(离职)";
			}else{
				value= user.getUserName();
			}
		}
		return value;
	}

	public static WsPlasUser getUserByUiid(String uiid) {
		return PlasCache.getUserByUiid(uiid);
	}

	public static String getPositionNameByCd(String cd) {
		return cd;
	}

	public static String getCenterOrgNameByOrgId(String orgId) {
		WsPlasOrg org = PlasCache.getCenterOrgById(orgId);
		if (org == null)
			return "";
		else
			return org.getOrgName();
	}

	public static String getCenterOrgNameByOrgCd(String orgCd) {
		WsPlasOrg o = PlasCache.getOrgByCd(orgCd);
		return getCenterOrgNameByOrgId(o.getPlasOrgId());
	}

	/**
	 * 对于由分隔符隔开的UIIDs，计算出中文名并返回
	 * 
	 * @param uiid
	 * @return
	 */
	public static String getUserNamesByUiids(String uiids, String delimiter) {
		if (StringUtils.isBlank(uiids) || StringUtils.isBlank(delimiter))
			return " ";
		StringBuilder names = new StringBuilder();
		String[] idArr = uiids.split(delimiter);
		String name = "";
		for (int i=0; i<idArr.length;i++) {
			 String uiid=idArr[i];
			names.append(CodeNameUtil.getUserNameByCd(uiid));
			if (i<idArr.length-1) {
				names.append(delimiter);
			}
		}
		// 如果不为空则返回配合人名字列表，否则返回一个空字符串
		name = names.toString();
		return StringUtils.isNotBlank(name) ? name : " ";
	}

	public static String getDictNameByCd(String dictTypeCd, String dictCd) {
		String dictNameRtn = "";

		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(dictTypeCd);
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			if (appDictData.getDictCd().equals(dictCd)) {
				dictNameRtn = appDictData.getDictName();
				break;
			}
		}
		return dictNameRtn;
	}

	/**
	 * 根据多个code(以逗号分隔)获取Name
	 * 
	 * @param dictTypeCd
	 * @param dictCds
	 * @return
	 */
	public static String getDictNameByCds(String dictTypeCd, String dictCds) {
		String[] cds = dictCds.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cds.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(getDictNameByCd(dictTypeCd, cds[i]));
		}
		return sb.toString();
	}

	public static String getDictCdByName(String dictTypeCd, String dictName) {
		String dictCdRtn = "";
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(dictTypeCd);

		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			if (appDictData.getDictName().equals(dictName)) {
				dictCdRtn = appDictData.getDictCd();
				break;
			}
		}
		return dictCdRtn;
	}

	public static String getResAuthTypeNameByCd(String authTypeCd) {
		return PdCache.getRefString(ResAuthType.class, "authTypeCd", authTypeCd, "displayName");
	}

	public static String getTempletNameByCd(String templetCd) {
		return PdCache.getRefString(ResBillTemplet.class, "templetCd", templetCd, "templetName");
	}

	public static String getResModuleNameByCd(String moduleCd) {
		return PdCache.getRefString(ResModule.class, "moduleCd", moduleCd, "moduleName");
	}

	public static String getResNodeNameByCd(String nodeCd) {
		String rtnName=nodeCd;
		String nodeName=getDictNameByCd(DictContants.RES_NODE_CD_2_NAME,nodeCd);
		if (StringUtils.isNotBlank(nodeName)) {
			rtnName= nodeName;
		} else{
			nodeName= PdCache.getRefString(ResNode.class, "nodeCd", nodeCd, "nodeName");
			if (StringUtils.isNotBlank(nodeName)){
				rtnName= nodeName;
			}
		}
		return rtnName;
	}
	/**
	 * 根据项目编号获取项目名称
	 * @param bisProjectId
	 * @return
	 */
	public static String getProjectName(String bisProjectId) {
		BisProjectManager bisProjectManager = SpringContextHolder.getBean("bisProjectManager");
		BisProject obj = bisProjectManager.getEntity(bisProjectId);
		if (obj == null)
			return "";
		else
			return obj.getProjectName();
	}
	/**
	 * 获得所属系统名字
	 * @param belongToCd
	 * @return
	 */
	public static String getEquipBelongToName(String belongToCd){
		return getDictNameByCd(DictContants.EQUIP_BELONGTO_CD,belongToCd);
	}
	/**
	 * 获得业态名称
	 * @param layoutCd
	 * @return
	 */
	public static String getEquipLayoutName(String layoutCd){
		return getDictNameByCd(DictContants.EQUIP_LAYOUT_CD,layoutCd);
	}
	/**
	 * 获得设备类型
	 * @param layoutCd
	 * @return
	 */
	public static String getEquipTypeCdName(String typeCd){
		return getDictNameByCd(DictContants.EQUIP_TYPE_CD,typeCd);
	}
	
	public static String getProjectCity(String bisProjectId) {
		BisProjectManager bisProjectManager = SpringContextHolder.getBean("bisProjectManager");
		BisProject obj = bisProjectManager.getEntity(bisProjectId);
		if (obj == null)
			return "";
		else
			return obj.getCity();
	}
}
