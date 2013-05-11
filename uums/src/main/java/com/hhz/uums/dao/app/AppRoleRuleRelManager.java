package com.hhz.uums.dao.app;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppRoleRuleRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


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
	public HibernateDao<AppRoleRuleRel, String> getDao() {
		return appRoleRuleRelDao;
	}
	
}

