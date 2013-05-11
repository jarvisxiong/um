package com.hhz.ump.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppPage;

@Service
@Transactional
public class AppPageManager extends BaseService<AppPage, String> {
	@Autowired
	private AppPageDao appPageDao;

	public void saveAppPage(AppPage appPage) {
		PowerUtils.setEmptyStr2Null(appPage);
		appPageDao.save(appPage);
	}

	public void deleteAppPage(String id) {
		appPageDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppPage, String> getDao() {
		return appPageDao;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public AppPage getEntityByPageCd(String pageCd) {
		List<AppPage> result = appPageDao.createCriteria(AppPage.class).add(Restrictions.eq("pageCd", pageCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (AppPage) result.get(0);
	}

	/**
	 * 模糊搜索页面
	 * 
	 * @param positionCd
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppPage> getPageList(String pageName, Integer iMaxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageName", "%" + pageName + "%");
		Page<AppPage> page = new Page<AppPage>(new Integer(iMaxNum));
		page = findPage(page," from AppPage where pageName like :pageName ",map);
		if (page.getResult() == null)
			return new ArrayList<AppPage>();
		else
			return page.getResult();
	}
	/**
	 * 搜索不属于任何memu的page
	 * 
	 * @param positionCd
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppPage> getPageListNotInMenu() {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql
		.append("select distinct p from AppPage p where p.appPageId not in ( ")
		.append(" select p.appPageId from AppMenu m where m.appPage.appPageId = p.appPageId")
		.append(" )")
		
		;
		return this.appPageDao.find(hql.toString(), map);
	}
	
}

