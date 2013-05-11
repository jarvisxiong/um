package com.hhz.uums.dao.app;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class AppRuleManager extends BaseService<AppRule, String> {
	@Autowired
	private AppRuleDao appRuleDao;

	public void saveAppRule(AppRule appRule) {
		PowerUtils.setEmptyStr2Null(appRule);
		appRuleDao.save(appRule);
	}

	public void deleteAppRule(String id) {
		appRuleDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppRule, String> getDao() {
		return appRuleDao;
	}
	
}

