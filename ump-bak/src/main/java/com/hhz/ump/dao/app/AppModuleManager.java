package com.hhz.ump.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppModule;

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
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<AppModule, String> getDao() {
		return appModuleDao;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Map<String, String> getModuleData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		
		List<AppModule> list = appModuleDao.getAll();
		CollectionHelper.sortList(list, "dispOrderNo");
		for (AppModule appModule : list) {
			map.put(appModule.getAppModuleId(), appModule.getModuleName());
		}
		return map;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Map<String, AppModule> getModuleDataMap() {
		Map<String, AppModule> map = new LinkedHashMap<String, AppModule>();

		
		List<AppModule> list = appModuleDao.getAll();
		CollectionHelper.sortList(list, "dispOrderNo");
		for (AppModule appModule : list) {
			map.put(appModule.getAppModuleId(), appModule);
		}
		return map;
	}
	/**
	 * 搜索当前用户拥有的所有模块
	 * 
	 * @param roleList
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AppModule> getModuleListByRoles(List<String> roleCdList) {

		StringBuffer sb = new StringBuffer();
		for (String roleCd : roleCdList) {
			sb.append("'").append(roleCd).append("',");
		}
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("roleCdList",sb.toString().substring(0,sb.length()-1));
		
		StringBuffer hqlBuf = new StringBuffer()
		.append(" select distinct(t1) from AppModule t1,AppModuleMenuRel t2,AppRoleMenuRel t3")
		.append(" where t1.appModuleId = t2.appModule.appModuleId ")
		.append("   and t2.appMenu.appMenuId = t3.appMenu.appMenuId ")
		.append("   and t3.roleCd in ( :roleCdList )");

		return find(hqlBuf.toString(),values);

		/*
		 * List appModuleList = new ArrayList(); for (int i=0;
		 * i<resultList.size(); i++) { Object[] object = (Object[])
		 * resultList.get(i); AppModule appModule = (AppModule) object[0];
		 * appModuleList.add(appModule); }
		 */
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public AppModule getEntityByModuleCd(String moduleCd) {
		List<AppModule> result = appModuleDao.createCriteria(AppModule.class).add(Restrictions.eq("moduleCd", moduleCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (AppModule) result.get(0);
	}
	
	/**
	 * 模糊搜索模块
	 * 
	 * @param positionCd
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppModule> getModuleList(String moduleName, Integer iMaxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleName", "%" + moduleName + "%");
		Page<AppModule> page = new Page<AppModule>(iMaxNum);
		page = findPage(page," from AppModule where moduleName like :moduleName ",map);
		if (page.getResult() == null)
			return new ArrayList<AppModule>();
		else
			return page.getResult();
	}
	
	/**
	 * 获取排序后的模块
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppModule> getAllOrdered(){
		String hql = " from AppModule t order by t.dispOrderNo asc ";
		List list = appModuleDao.createQuery(hql, new HashMap<String,Object>()).list();
		if(list == null)
			return new ArrayList<AppModule>();
		return list;
	}
}

