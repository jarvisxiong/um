package com.hhz.ump.dao.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppRoelResourceRel;

@Service
@Transactional
public class AppRoelResourceRelManager extends BaseService<AppRoelResourceRel, String> {
	@Autowired
	private AppRoelResourceRelDao appRoelResourceRelDao;
	
	public void saveAppRoelResourceRel(AppRoelResourceRel appRoelResourceRel) {
		PowerUtils.setEmptyStr2Null(appRoelResourceRel);
		appRoelResourceRelDao.save(appRoelResourceRel);
	}

	public void deleteAppRoelResourceRel(String id) {
		appRoelResourceRelDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppRoelResourceRel, String> getDao() {
		return appRoelResourceRelDao;
	}
	
}

