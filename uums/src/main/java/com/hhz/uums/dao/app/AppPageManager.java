package com.hhz.uums.dao.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppPage;

@Service
@Transactional
public class AppPageManager extends BaseService<AppPage, String> {
	@Autowired
	private AppPageDao appPageDao;
	@Autowired
	private AppFunctionManager appFunctionManager;

	public void saveAppPage(AppPage appPage) {
		PowerUtils.setEmptyStr2Null(appPage);
		appPageDao.save(appPage);
	}
	public void saveAppPage(AppPage appPage,List<AppFunction> datasInsert,List<AppFunction> datasUpdate) {
		saveAppPage(appPage);
		for(AppFunction appFunction : datasInsert){
			appFunction.setAppPage(appPage);
			appFunctionManager.saveAppFunction(appFunction);
		}
		for(AppFunction appFunction : datasUpdate){
			appFunction.setAppPage(appPage);
			appFunctionManager.saveAppFunction(appFunction);
		}
		
	}

	public void deleteAppPage(String id) {
		AppPage appPage = appPageDao.get(id);
		for(AppFunction appFunction : appPage.getAppFunctions()){
			appFunctionManager.delete(appFunction);
		}
		appPageDao.delete(id);
		
	}
	
	@Override
	public HibernateDao<AppPage, String> getDao() {
		return appPageDao;
	}
	
}

