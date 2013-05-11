package com.hhz.ump.dao.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppPrintHis;

@Service
@Transactional
public class AppPrintHisManager extends BaseService<AppPrintHis, String> {
	@Autowired
	private AppPrintHisDao appPrintHisDao;

	public void saveAppPrintHis(AppPrintHis appPrintHis) {
		PowerUtils.setEmptyStr2Null(appPrintHis);
		appPrintHis.setPrintCount(genCount(appPrintHis.getBizEntityId()));
		appPrintHisDao.save(appPrintHis);
	}

	private Long genCount(String bizEntityId) {
		long cnt = 0;
		if (bizEntityId != null) {
			StringBuffer hql = new StringBuffer("from AppPrintHis h where h.bizEntityId=:bizEntityId ");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("bizEntityId", bizEntityId);
			cnt = countHqlResult(hql.toString(), values);
		}
		return ++cnt;
	}

	public void deleteAppPrintHis(String id) {
		appPrintHisDao.delete(id);
	}

	@Override
	public HibernateDao<AppPrintHis, String> getDao() {
		return appPrintHisDao;
	}

}
