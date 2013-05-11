package com.hhz.ump.dao.app;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppParam;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.PdCache;

@Service
@Transactional
public class AppParamManager extends BaseService<AppParam, String> {
	@Autowired
	private AppParamDao appParamDao;
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppParam> getAllAppParam() {
		// return appParamDao.getAll();
		List<AppParam> list = appParamDao.getAll();
		CollectionHelper.sortList(list, "dispOrderNo");// 排序
		return list;
	}
	
	public void saveAppParam(AppParam appParam) {
		PowerUtils.setEmptyStr2Null(appParam);
		appParamDao.save(appParam);
	}

	public void deleteAppParam(String id) {
		appParamDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppParam, String> getDao() {
		return appParamDao;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getAppParamList() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		List appParamList = appParamDao.getAll();
		if (appParamList != null) {
			CollectionHelper.sortList(appParamList, "dispOrderNo");
			for (int i = 0; i < appParamList.size(); i++) {
				AppParam appParam = (AppParam) appParamList.get(i);
				map.put(appParam.getParamCd(), appParam.getParamValue());
			}
		}
		return map;
	}

	private String findAppParamValueByCd(String paramCd) {
		AppParam appParam =	PdCache.getRefEntity(AppParam.class, "paramCd", paramCd);
//		AppParam appParam = appParamDao.findUniqueBy("paramCd", paramCd);
		if (appParam == null)
			return "";
		return appParam.getParamValue();
	}

	// 机构根节点CD
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootCd() {
		return findAppParamValueByCd(GlobalConstants.APP_ORG_TREE_ROOT_CD);
	}

	// 机构根业务cd
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootBizCd() {
		return findAppParamValueByCd(GlobalConstants.APP_ORG_TREE_ROOT_BIZ_CD);
	}
	
	// 获取各地产公司的根机构节点
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgAllProjectsRootCd() {
	    return findAppParamValueByCd(GlobalConstants.APP_ALL_PROJS_ROOT);
	}

	// 机构根节点名称
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootName() {
		return findAppParamValueByCd(GlobalConstants.APP_ORG_TREE_ROOT_NAME);
	}

	// 当前应用编号
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getCurrentSystemAppBizCd() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_CURRENT_APP_CD);
	}

	// 在线MSN消息服务器
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getMsnPreUrl() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_WEBIM_PRE_URL);
	}

	// 在线MSN应用上下文
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getMsnContext() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_WEBIM_CONTEXT);
	}

	// 定义UAAP应用地址
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getUaapUrl() {
		return findAppParamValueByCd(GlobalConstants.APP_UAAP_URL);
	}

	// 是否启用mac地址过滤
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultMacLockedFlg() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG);
	}
	//是否发送短信
	public String getSmsFlg() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_SMS_FLG);
	}
	//网批节点配置(1=生效，0=失效)
	public String getResPlasNodeSet() {
		return findAppParamValueByCd(GlobalConstants.RES_PLAS_NODE_SET);
	}
	//周二的【执行总裁例会】事务开关
	public String getCeoMeetingNotify() {
		return findAppParamValueByCd(GlobalConstants.APP_CEO_MEETING_NOTIFY);
	}

	// 1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultMacLoced() {
		String defualtMacLockedFlg = getDefaultMacLockedFlg();
		if (DictContants.UAAP_MAC_LOCKED_FLG_LOCKED.equals(defualtMacLockedFlg))
			return true;
		else if (DictContants.UAAP_MAC_LOCKED_FLG_UNLOCKED.equals(defualtMacLockedFlg))
			return false;
		else
			return false;
	}
	/**
	 * 批量保存
	 * @param insertedRecords
	 * @param updatedRecords
	 * @param deletedRecords
	 */
	public void saveOrUpdateAppParams(List<AppParam> insertedRecords,List<AppParam> updatedRecords,List<AppParam> deletedRecords){
		for(AppParam appParam : insertedRecords){
			this.saveAppParam(appParam);
		}
		for(AppParam appParam : updatedRecords){
			this.saveAppParam(appParam);
		}
		for(AppParam appParam : deletedRecords){
			this.delete(appParam);
		}
		
	}
}

