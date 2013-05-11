package com.hhz.uums.dao.app;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppDictData;

@Service
@Transactional
public class AppDictDataManager extends BaseService<AppDictData, String> {
	@Autowired
	private AppDictDataDao appDictDataDao;

	/**
	 * 根据数据字典类型CD获取数据字典数据
	 * 
	 * @param dictTypeCd
	 * @return
	 */
	public List<AppDictData> getDictDataPerDictType(String dictTypeCd) {
		if (StringUtils.isBlank(dictTypeCd))
			throw new IllegalArgumentException("请传入数据字典类型CD");

		return find("from AppDictData d where d.appDictType.dictTypeCd =?  order by sequenceNo asc  ",dictTypeCd);
	}
	public void saveAppDictData(AppDictData appDictData) {
		PowerUtils.setEmptyStr2Null(appDictData);
		appDictDataDao.save(appDictData);
	}

	public void deleteAppDictData(String id) {
		appDictDataDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppDictData, String> getDao() {
		return appDictDataDao;
	}
	
}

