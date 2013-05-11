package com.hhz.uums.dao.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppModule;

@Service
@Transactional
public class AppModuleManager extends BaseService<AppModule, String> {
	@Autowired
	private AppModuleDao appModuleDao;

	public void saveAppModule(AppModule appModule) {
		PowerUtils.setEmptyStr2Null(appModule);
		appModuleDao.save(appModule);
	}

	public void deleteAppModule(String id) {
		appModuleDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppModule, String> getDao() {
		return appModuleDao;
	}
	public List<AppModule> loadAllModule(){
		return loadAllModuleByRole(null);
	}
	public List<AppModule> loadAllModuleByRole(String appRoleId){
		Page<AppModule> pageMenu = new Page<AppModule>(1000);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		// 设置默认排序方式
		if (!pageMenu.isOrderBySetted()) {
			pageMenu.setOrderBy("treeLevel,dispOrderNo");
			pageMenu.setOrder(Page.ASC + "," + Page.ASC);
		}
		if (StringUtils.isNotEmpty(appRoleId)){
			filters.add(new PropertyFilter("EQS_appRole.appRoleId", appRoleId));
		}
		pageMenu.setPageSize(1000);
		pageMenu = findPage(pageMenu, filters);
		return pageMenu.getResult();
	}
}

