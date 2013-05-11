/**
 * 
 */
package com.hhz.uums.utils;

import java.lang.reflect.Method;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.web.PdCache;

/**
 * @author huangj 2010-12-20
 */
public class CodeNameUtil {
	/**
	 * 根据机构CD获取机构名称
	 * 
	 * @param deptCd
	 * @return
	 */
	public static String getDeptNameByCd(String deptCd) {
		PlasOrgManager plasOrgManager = SpringContextHolder	.getBean("plasOrgManager");
		PlasOrg org = plasOrgManager.getPlasOrgByCd(deptCd);
		if(org == null)
			return "";
		else
			return org.getOrgName();
	}
	private static AppDictTypeManager appDictTypeManager = SpringContextHolder
			.getBean("appDictTypeManager");

	public static String getUserNameByCode(String userCode) {
		String userName = PdCache.getRefString(PlasUser.class, "userCode",
				userCode, "userName");
		return userName;
	}

	public static String getUserNameById(String userId) {
		String userName = PdCache.getRefString(PlasUser.class, "appUserId",
				userId, "userName");
		return userName;
	}

	public static PlasUser getAppUserByUiid(String uiid) {
		PlasUser user = (PlasUser) PdCache.getRefEntity(PlasUser.class, "uiid",
				uiid);
		return user;
	}

	public static String getDictNameByCd(String dictTypeCd, String dictCd) {
		String dictNameRtn = "";

		AppDictType appDictType = appDictTypeManager
				.findAppDictTypeByCd(dictTypeCd);
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			if (appDictData.getDictCd().equals(dictCd)) {
				dictNameRtn = appDictData.getDictName();
				break;
			}
		}
		return dictNameRtn;
	}

	public static Method getDictMethod() {
		Method m = null;
		try {
			m = CodeNameUtil.class.getMethod("getDictNameByCd", new Class[] {
					String.class, String.class });
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return m;
	}
}
