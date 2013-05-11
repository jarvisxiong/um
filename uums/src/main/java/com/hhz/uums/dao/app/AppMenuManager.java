package com.hhz.uums.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppMenu;

@Service
@Transactional
public class AppMenuManager extends BaseService<AppMenu, String> {
	@Autowired
	private AppMenuDao appMenuDao;

	public void saveAppMenu(AppMenu appMenu) {
		PowerUtils.setEmptyStr2Null(appMenu);
		appMenuDao.save(appMenu);
	}
	
	public void saveOrUpdateAppMenus(List<AppMenu> insertedRecords,List<AppMenu> updatedRecords,List<AppMenu> deletedRecords){
		for(AppMenu appMenu : insertedRecords){
			this.saveAppMenu(appMenu);
		}
		for(AppMenu appMenu : updatedRecords){
			this.saveAppMenu(appMenu);
		}
		for(AppMenu appMenu : deletedRecords){
			this.delete(appMenu);
		}
		
	}
	public List<AppMenu> loadAppMenuByRole(String appRoleId){
		StringBuffer hql=new StringBuffer();
		hql.append("select t1 from AppMenu as t1,AppRoleMenuRel as t2 where t1.appMenuId=t2.appMenu.appMenuId ");
		hql.append("and t2.appRole.appRoleId=:appRoleId ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("appRoleId", appRoleId);
		List<AppMenu> appMenus = find(hql.toString(), values);
		if (appMenus == null)
			return new ArrayList<AppMenu>();
		else
			return appMenus;
	}
	public void deleteAppMenu(String id) {
		appMenuDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppMenu, String> getDao() {
		return appMenuDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public AppMenu getEntityByMenuCd(String menuCd) {
		List<AppMenu> result = appMenuDao.createCriteria(AppMenu.class).add(Restrictions.eq("menuCd", menuCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (AppMenu) result.get(0);
	}
}

