package com.hhz.ump.dao.app;

import java.util.List;

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
public class AppDictDataManager extends BaseService<AppDictData, String> {
	@Autowired
	private AppDictDataDao appDictDataDao;
	
	@Autowired
	private AppDictTypeDao appDictTypeDao;

	/**
	 * 根据数据字典类型CD获取数据字典数据
	 * @param dictTypeCd
	 * @return
	 */
//	public List<AppDictData> getDictDataPerDictType(String dictTypeCd) {
//	    if (StringUtils.isBlank(dictTypeCd))
//			throw new IllegalArgumentException("请传入数据字典类型CD");
//	    
//	    List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//	    filters.add(new PropertyFilter("EQS_appDictType.dictTypeCd", dictTypeCd));
//	    return this.find(filters);
//	}
	
	public void saveAppDictData(AppDictData appDictData) {
		PowerUtils.setEmptyStr2Null(appDictData);
		appDictDataDao.save(appDictData);
	}

	public void deleteAppDictData(String id) {
		appDictDataDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<AppDictData, String> getDao() {
		return appDictDataDao;
	}
	
	/**
	 * 重新设置数据字典数据
	 * @param dictTypeCd
	 * @param list
	 */
	public void saveAppDictData(String dictTypeCd,List<AppDictData> list){
		AppDictType appDictType = appDictTypeDao.findUniqueBy("dictTypeCd", dictTypeCd);
		this.delete(appDictType.getAppDictDatas());
		for(AppDictData data : list ){
			data.setAppDictType(appDictType);
			this.saveAppDictData(data);
		}
		PdCache.cleanCache(AppDictType.class.getName());
	}
	
}

