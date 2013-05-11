package com.hhz.uums.dao.app;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppRoleResourceRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class AppRoleResourceRelManager extends BaseService<AppRoleResourceRel, String> {
	@Autowired
	private AppRoleResourceRelDao appRoleResourceRelDao;

	public void saveAppRoleResourceRel(AppRoleResourceRel appRoleResourceRel) {
		PowerUtils.setEmptyStr2Null(appRoleResourceRel);
		appRoleResourceRelDao.save(appRoleResourceRel);
	}

	public void deleteAppRoleResourceRel(String id) {
		appRoleResourceRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppRoleResourceRel, String> getDao() {
		return appRoleResourceRelDao;
	}
	
}

