package com.hhz.uums.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.vo.ws.WsAppDictData;
import com.hhz.uums.vo.ws.WsAppDictType;
import com.hhz.uums.web.PdCache;

@Service
@Transactional
public class AppDictTypeManager extends BaseService<AppDictType, String> {
	@Autowired
	private AppDictTypeDao appDictTypeDao;

	@Autowired
	private AppDictDataDao appDictDataDao;

	public void saveEntity(AppDictType appDictType){
		PowerUtils.setEmptyStr2Null(appDictType);
		appDictTypeDao.save(appDictType);
	}
	public void saveAppDictType(AppDictType appDictType) {
		saveEntity(appDictType);
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			PowerUtils.setEmptyStr2Null(appDictData);
			appDictDataDao.save(appDictData);
		}
	}
	public void saveAppDictTypes(List<AppDictType> appDictTypes) {
		for(AppDictType appDictType:appDictTypes){
			saveAppDictType(appDictType);
		}
	}

	public void deleteAppDictType(String id) {
		AppDictType appDictType = appDictTypeDao.get(id);
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			appDictDataDao.delete(appDictData);
		}
		appDictTypeDao.delete(appDictType);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppDictType, String> getDao() {
		return appDictTypeDao;
	}

	// ----以下为公用方法，用于Cd转Name
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDictNameByCd(String dictTypeCd, String dictCd) {
		String dictNameRtn = null;

		AppDictType appDictType = findAppDictTypeByCd(dictTypeCd);
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			if (appDictData.getDictCd().equals(dictCd)) {
				dictNameRtn = appDictData.getDictName();
				break;
			}
		}
		return dictNameRtn;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDictCdByName(String dictTypeCd, String dictName) {
		String dictCdRtn = null;
		AppDictType appDictType = findAppDictTypeByCd(dictTypeCd);

		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			if (appDictData.getDictName().equals(dictName)) {
				dictCdRtn = appDictData.getDictCd();
				break;
			}
		}
		return dictCdRtn;
	}

	/**
	 * 根据dictTypeCd查询对应的字典数据;
	 * dictTypeCd从DictContants类中取得，如：DictContants.DLY_BIZ_MODULE
	 * 
	 * @param dictTypeCd
	 * @return Map<String,String>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCd(String dictTypeCd) {
		Map<String, String> map = new LinkedHashMap<String, String>();
//		add by huangbijin 2011-04-25
		map.put("", "-");//hidden by huangbijin 2011-11-25  chenjj1要求
//		map.put("", "--选择--");
		AppDictType appDictType = findAppDictTypeByCd(dictTypeCd);
		if (appDictType != null) {
			// CollectionHelper.sortList(appDictType.getAppDictDatas(),
			// "dispOrderNo");
			for (AppDictData appDictData : appDictType.getAppDictDatas()) {
				map.put(appDictData.getDictCd(), appDictData.getDictName());
			}
			// appDictTypeDao.evict(appDictType);
		}
		return map;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCd(String dictTypeCd,boolean asc) {
		return getDictDataByTypeCd(dictTypeCd, asc, true);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCd(String dictTypeCd,boolean asc, boolean enableEmptyFlg) {
		
		String hql = " from AppDictData t where t.appDictType.dictTypeCd = ? order by t.sequenceNo " + (asc?" asc ": " desc ");
		List<AppDictData> list = appDictDataDao.find(hql, dictTypeCd);
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(enableEmptyFlg){
			map.put("", "");
		}
		if(list != null && list.size() > 0){
			for (AppDictData tmp : list) {
				map.put(tmp.getDictCd(), tmp.getDictName());
			}
		}
		
		return map;
		
	}
	public List<Map<String, String>> getDictList(String dictTypeCd){
		AppDictType appDictType = findAppDictTypeByCd(dictTypeCd);
		List<AppDictData> dicts = appDictType.getAppDictDatas();
		List<Map<String, String>> lstDict = new ArrayList<Map<String, String>>();
		
		Map<String, String> map = null;
		for (AppDictData dict : dicts) {
			map = new LinkedHashMap<String, String>();
			map.put("id", dict.getAppDictDataId());
			map.put("cd", dict.getDictCd());
			map.put("name", dict.getDictName());
			lstDict.add(map);
		}
		return lstDict;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public AppDictType findAppDictTypeByCd(String dictTypeCd) {
		// AppDictType appDictType = appDictTypeDao.findUniqueBy("dictTypeCd",
		// dictTypeCd);
		// AppDictType appDictType = UaapCache.getAppDictTypeByCd(dictTypeCd);
		AppDictType appDictType = (AppDictType) PdCache.getRefEntity(AppDictType.class, "dictTypeCd", dictTypeCd);
		return appDictType;
	}
	// ----end

	/* *********************************************************************************/

	public List<WsAppDictType> getWsAll(){
		return getWsAll(null);
	}
	public List<WsAppDictType> getWsAll(String dictTypeCd) {

		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" t.appDictTypeId, ")
			.append(" t.dictTypeCd, ")
			.append(" t.dictTypeName, ")
			.append(" t.defaultFlg, ")
			.append(" t.sequenceNo, ")
			.append(" t.remark ")
			.append(" from AppDictType t ");
		
		Map<String,Object> values = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(dictTypeCd)){
			sb.append(" where t.dictTypeCd = :dictTypeCd ");
			values.put("dictTypeCd", dictTypeCd);
		}
		
		
		List list = this.getDao().find(sb.toString(),values);
		
		List<WsAppDictType> rtnList = new ArrayList<WsAppDictType>();
		if(list != null){
			Object[] t = null;
			WsAppDictType tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsAppDictType();
				
				tmp.setAppDictTypeId((String)t[0]);
				tmp.setDictTypeCd((String)t[1]);
				tmp.setDictTypeName((String)t[2]);
				tmp.setDefaultFlg((Boolean)t[3]);
				tmp.setSequenceNo((Long)t[4]);
				tmp.setRemark((String)t[5]);
				 
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	public List<WsAppDictData> getWsAllData(){
		return getWsAllData(null);
	}
	public List<WsAppDictData> getWsAllData(String dictTypeCd) {

		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" t.appDictDataId, ")
				    
			//appDictType
			.append(" t.appDictType.appDictTypeId, ")
			.append(" t.appDictType.dictTypeCd, ")
			.append(" t.appDictType.dictTypeName, ")
			    
			.append(" t.dictCd, ")
			.append(" t.dictName, ")
			.append(" t.dictLevelNum, ")
			.append(" t.defaultFlg, ")
			.append(" t.sequenceNo, ")
			.append(" t.remark ")
			.append(" from AppDictData t ");
		
		Map<String,Object> values = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(dictTypeCd)){
			sb.append(" where t.appDictType.dictTypeCd = :dictTypeCd ");
			values.put("dictTypeCd", dictTypeCd);
		}
		sb.append(" order by t.sequenceNo asc");
		
		List list = this.getDao().find(sb.toString(),values);
		
		List<WsAppDictData> rtnList = new ArrayList<WsAppDictData>();
		if(list != null){
			Object[] t = null;
			WsAppDictData tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsAppDictData();

				tmp.setAppDictDataId((String)t[0]);
				    
				//appDictType
				tmp.setDictTypeId((String)t[1]);
				tmp.setDictTypeCd((String)t[2]);
				tmp.setDictTypeName((String)t[3]);
				    
				tmp.setDictCd((String)t[4]);
				tmp.setDictName((String)t[5]);
				tmp.setDictLevelNum((Long)t[6]);
				tmp.setDefaultFlg((Boolean)t[7]);
				tmp.setSequenceNo((Long)t[8]);
				tmp.setRemark((String)t[9]);
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}
	
}

