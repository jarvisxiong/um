package com.hhz.ump.dao.app;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.util.PdCache;

@Service
@Transactional
public class AppDictTypeManager extends BaseService<AppDictType, String> {
	@Autowired
	private AppDictTypeDao appDictTypeDao;

	@Autowired
	private AppDictDataDao appDictDataDao;

	public void saveEntity(AppDictType appDictType) {
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


	/**
	 * 根据dictTypeCd搜索对应的字典数据;
	 * dictTypeCd从DictContants类中取得，如：DictContants.DLY_BIZ_MODULE
	 * 
	 * @param dictTypeCd
	 * @return Map<String,String>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCd(String dictTypeCd) {
		return getDictDataByTypeCd(dictTypeCd,false);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDictNameByCd(String dictTypeCd,String dictCd){
		AppDictData appDictData=getDictDataByCd(dictCd,dictTypeCd);
		if (appDictData!=null)
			return appDictData.getDictName();
		return null;
	}
	public AppDictData getDictDataByCd(String dictCd,String dictTypeCd){
		//BIZ_AUTH_ORG
		AppDictData appDictDataTmp=null;
		AppDictType appDictType=findAppDictTypeByCd(dictTypeCd);
		if (appDictType != null) {
			for (AppDictData appDictData : appDictType.getAppDictDatas()) {
				if (appDictData.getDictCd().equals(dictCd)){
					appDictDataTmp=appDictData;
					break;
				}
			}
		}
		return appDictDataTmp;
	}
	public Map<String, String> getDictDataByTypeCd(String dictTypeCd, boolean search) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--全部--");
		AppDictType appDictType;
		if (search) {
			appDictType = loadAppDictTypeByCd(dictTypeCd);
		} else {
			appDictType = findAppDictTypeByCd(dictTypeCd);
		}
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

	// 去掉value为空的键值对
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCdA(String dictTypeCd) {
		Map<String, String> map = new LinkedHashMap<String, String>();
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

	// 加载键值对为空的记录
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getDictDataByTypeCdB(String dictTypeCd) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
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

	@Transactional(propagation = Propagation.SUPPORTS)
	public AppDictType findAppDictTypeByCd(String dictTypeCd) {
		// AppDictType appDictType = appDictTypeDao.findUniqueBy("dictTypeCd",
		// dictTypeCd);
		// AppDictType appDictType = UaapCache.getAppDictTypeByCd(dictTypeCd);
		AppDictType appDictType = (AppDictType) PdCache.getRefEntity(AppDictType.class, "dictTypeCd", dictTypeCd);
		return appDictType;
	}

	public AppDictType loadAppDictTypeByCd(String dictTypeCd) {
		AppDictType appDictType = appDictTypeDao.findUniqueBy("dictTypeCd", dictTypeCd);
		return appDictType;
	}
	// ----end
}
