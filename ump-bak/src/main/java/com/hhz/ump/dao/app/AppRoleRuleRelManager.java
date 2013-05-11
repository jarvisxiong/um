package com.hhz.ump.dao.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppRoleRuleRel;

@Service
@Transactional
public class AppRoleRuleRelManager extends BaseService<AppRoleRuleRel, String> {
	@Autowired
	private AppRoleRuleRelDao appRoleRuleRelDao;

	public void saveAppRoleRuleRel(AppRoleRuleRel appRoleRuleRel) {
		PowerUtils.setEmptyStr2Null(appRoleRuleRel);
		appRoleRuleRelDao.save(appRoleRuleRel);
	}

	public void deleteAppRoleRuleRel(String id) {
		appRoleRuleRelDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppRoleRuleRel, String> getDao() {
		return appRoleRuleRelDao;
	}
	
}

