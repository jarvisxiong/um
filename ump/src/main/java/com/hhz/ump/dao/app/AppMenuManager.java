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
import com.hhz.ump.entity.app.AppMenu;

@Service
@Transactional
public class AppMenuManager extends BaseService<AppMenu, String> {
	@Autowired
	private AppMenuDao appMenuDao;

	
	public void saveAppMenu(AppMenu appMenu) {
		PowerUtils.setEmptyStr2Null(appMenu);
		appMenuDao.save(appMenu);
	}

	public void deleteAppMenu(String id) {
		appMenuDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<AppMenu, String> getDao() {
		return appMenuDao;
	}
	/**
	 * 模糊搜索模块
	 * 
	 * @param positionCd
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppMenu> getModuleList(String menuName, Integer iMaxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuName", "%" + menuName + "%");
		Page<AppMenu> page = new Page<AppMenu>(iMaxNum);
		page = findPage(page," from AppMenu where menuName like :menuName ",map);
		if (page.getResult() == null)
			return new ArrayList<AppMenu>();
		else
			return page.getResult();
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

