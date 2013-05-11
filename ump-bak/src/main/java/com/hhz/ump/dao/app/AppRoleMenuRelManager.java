package com.hhz.ump.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.entity.app.AppRoleFunctionRel;
import com.hhz.ump.entity.app.AppRoleMenuRel;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasRole;

@Service
@Transactional
public class AppRoleMenuRelManager extends BaseService<AppRoleMenuRel, String> {
	@Autowired
	private AppRoleMenuRelDao appRoleMenuRelDao;

	@Autowired
	private AppRoleFunctionRelDao appRoleFunctionRelDao;

	@Autowired
	private AppMenuManager appMenuManager;

	// AppRoleMenuRel Manager //

	/**
	 * 判断是否拥有菜单权限
	 * 
	 * @param menuCd
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean hasMenuPermission(String menuCd) {

		Criterion cRoleCd = Restrictions.in("roleCd", getRoleCdList());
		Criteria criteria = appRoleMenuRelDao.createCriteria(cRoleCd);
		Criterion cMenuCd = Restrictions.eq("menuCd", menuCd);
		criteria.createCriteria("appMenu").add(cMenuCd);
		int count = countCriteriaResult(criteria);
		return count > 0;
	}

	public List<String> getRoleCdList() {
		List<String> roleCds = new ArrayList<String>();
		List<WsPlasRole> list = Util.getPlasService().getRoleListByUiid(SpringSecurityUtils.getCurrentUiid());
		if (list != null) {
			for (WsPlasRole role : list) {
				roleCds.add(role.getRoleCd());
			}
		}
		return roleCds;
	}

	/**
	 * 进行菜单排序
	 * 
	 * @return
	 */
	public List<AppModuleMenuRel> searchMenu(String roleCd) {
		if (roleCd != null) {
			String[] roleCds = roleCd.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer(
					"select rel from AppModuleMenuRel rel , AppMenu menu,AppRoleMenuRel mrel where mrel.appMenu.appMenuId= menu.appMenuId and rel.appMenu.appMenuId  = menu.appMenuId");
			if (roleCds.length > 0) {
				hql.append(" and mrel.roleCd in (:roleCd) ");
				map.put("roleCd", roleCds);
			}
			hql.append("order by menu.dispOrderNo");
			List<AppModuleMenuRel> menuRels = appRoleMenuRelDao.find(hql.toString(), map);
			return menuRels;
		}
		return new ArrayList<AppModuleMenuRel>(0);

	}

	/**
	 * 判断是否拥有功能权限
	 * 
	 * @param functionCd
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean hasFuncPermission(String functionCd) {
		// List<String> roleCds =
		// Util.getUaapService().getRoleCdsByUiid(SpringSecurityUtils.getCurrentUiid());
		List<String> roleCds = getRoleCdList();
		Criterion cRoleCd = Restrictions.in("roleCd", roleCds);
		Criteria criteria = appRoleFunctionRelDao.createCriteria(cRoleCd);
		Criterion cFunctionCd = Restrictions.eq("functionCd", functionCd);
		criteria.createCriteria("appFunction").add(cFunctionCd);
		int count = appRoleFunctionRelDao.countCriteriaResult(criteria);
		return count > 0;
	}

	public void saveAppRoleMenuRel(AppRoleMenuRel appRoleMenuRel) {
		PowerUtils.setEmptyStr2Null(appRoleMenuRel);
		appRoleMenuRelDao.save(appRoleMenuRel);
		// appRoleMenuRelDao.getSession().save(appRoleMenuRel);
		// appRoleMenuRelDao.getSession().save("2", appRoleMenuRel);
	}

	public void saveRoleRel(List<AppRoleMenuRel> lstAppRoleMenuRel, List<String> lstDelete, List<AppRoleFunctionRel> lstAppRoleFunctionRel,
			List<String> lstDeleteFunc) {
		for (AppRoleMenuRel appRoleMenuRel : lstAppRoleMenuRel) {
			saveAppRoleMenuRel(appRoleMenuRel);
		}
		String[] ids = new String[lstDelete.size()];
		lstDelete.toArray(ids);
		deleteBatch(ids);
		for (AppRoleFunctionRel appRoleFunctionRel : lstAppRoleFunctionRel) {
			appRoleFunctionRelDao.save(appRoleFunctionRel);
		}
		for (String funcId : lstDeleteFunc) {
			appRoleFunctionRelDao.delete(funcId);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppRoleMenuRel> findByRoleCd(String roleCd) {
		String[] roleCds = roleCd.split(",");
		Criterion criterion;
		if (roleCds.length > 1) {
			criterion = Restrictions.in("roleCd", roleCds);
		} else {
			criterion = Restrictions.eq("roleCd", roleCd);
		}
		return findBy(criterion);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppRoleMenuRel> findByRoleCdAMenuId(String roleCd, String menuId) {
		String[] roleCds = roleCd.split(",");
		Criterion criterion;
		if (roleCds.length > 1) {
			criterion = Restrictions.in("roleCd", roleCds);
		} else {
			criterion = Restrictions.eq("roleCd", roleCd);
		}
		Criterion criterion1 = Restrictions.eq("appMenu.appMenuId", menuId);
		return findBy(criterion, criterion1);
	}

	public void deleteAppRoleMenuRel(String id) {
		appRoleMenuRelDao.delete(id);
	}
	public void deleteAppRoleMenuRelByMenu(String menuId) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("delete  from AppRoleMenuRel rel where  appMenu.appMenuId =:menuId");

		values.put("menuId", menuId);
		appRoleMenuRelDao.batchExecute(hql.toString(), values);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppRoleMenuRel, String> getDao() {
		return appRoleMenuRelDao;
	}

	public void deleteRoleRel(String roleCd) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("delete  from AppRoleMenuRel rel where  roleCd =:roleCd");

		values.put("roleCd", roleCd);
		appRoleMenuRelDao.batchExecute(hql.toString(), values);
	}

	public void saveEntity(AppRoleMenuRel entity) {
		this.getDao().save(entity);
	}

	/**
	 * 根据菜单编号,搜索所有角色列表.
	 * @param tMenuId
	 * @return
	 */
	public List<String> getRoleList(String tMenuId) {
		String hql = "select t.roleCd from AppRoleMenuRel t where t.appMenu.appMenuId = ?";
		List list = this.find(hql, tMenuId);
		if(list == null )
			return new ArrayList<String>();
		else
			return list;
	}
	/**
	 * 搜索菜单角色关系
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public AppRoleMenuRel getRoleMenuRel(String menuId, String roleCd){
		String hql = "from AppRoleMenuRel t where t.appMenu.appMenuId = ? and t.roleCd = ? ";
		List list = this.find(hql, menuId, roleCd);
		if(list == null || list.size() == 0)
			return null;
		else
			return (AppRoleMenuRel)list.get(0);
	}

	public void saveBatch(String roleCd, String addMenuIds, String delMenuIds) {

		AppRoleMenuRel obj = null;
		AppMenu menu = null;

		// 配置系统职位与角色的对应关系
		if (StringUtils.isNotBlank(addMenuIds)) {
			String[] addArray = addMenuIds.split(",");
			String tmpId = null;
			for (int i = 0; i < addArray.length; i++) {
				tmpId = addArray[i];
				if(StringUtils.isNotBlank(tmpId)){
					obj = new AppRoleMenuRel();
					menu = new AppMenu();
					menu.setAppMenuId(tmpId);
					obj.setAppMenu(menu);
					obj.setRoleCd(roleCd);
					this.saveEntity(obj);
				}
			}
		}
		// 收回系统职位和角色的对应关系
		if (StringUtils.isNotBlank(delMenuIds)) {
			String[] delArray = delMenuIds.split(",");
			List<AppRoleMenuRel> result = null;
			String tmpId = null;
			for (int i = 0; i < delArray.length; i++) {
				tmpId = delArray[i];
				if(StringUtils.isNotBlank(tmpId)){
					result = this.findByRoleCdAMenuId(roleCd,tmpId);
					if(result!= null){
						this.delete(result);
					}
				}
			}
		}
	}
	/**
	 * 保存菜单与角色关系
	 * @param menuId
	 * @param addRoleIds
	 * @param delRoleIds
	 */
	public void saveBatchRole(String menuId, String addRoleIds, String delRoleIds) {

		String[] addArray = addRoleIds.split(",");
		String[] delArray = delRoleIds.split(",");
		
		AppRoleMenuRel rel = null;
		
		if(StringUtils.isBlank(menuId))
			return;
		AppMenu menu = appMenuManager.getEntity(menuId);
		if(menu == null)
			return;
		
		WsPlasRole wsRole = null;
		
		//增加菜单的角色；
		if(addArray != null){
			String tmpRoleId = null;
			for(int i=0; i<addArray.length; i++){
				tmpRoleId= addArray[i];
				if(StringUtils.isNotBlank(tmpRoleId)){
					wsRole = getWsEntity(tmpRoleId);
					if(wsRole != null){
						rel = getRoleMenuRel(menuId, wsRole.getRoleCd());
						if(rel == null){
							if(wsRole != null){
								rel = new AppRoleMenuRel();
								rel.setAppMenu(menu);
								rel.setRoleCd(wsRole.getRoleCd());
								saveAppRoleMenuRel(rel);
							}
						}
					}
				}
			}
		}

		//删除菜单的角色；
		if(delArray != null){
			String tmpRoleId = null;
			for(int i=0; i<delArray.length; i++){
				tmpRoleId= delArray[i];
				if(StringUtils.isNotBlank(tmpRoleId)){
					wsRole = getWsEntity(tmpRoleId);
					if(wsRole != null){
						rel = getRoleMenuRel(menuId, wsRole.getRoleCd());
						if(rel != null){
							delete(rel);
						}
					}
				}
			}
		}
	}

	/**
	 * 从缓存获取角色
	 * @param roleId
	 * @return
	 */
	private WsPlasRole getWsEntity(String roleId) {
		if(StringUtils.isBlank(roleId))
			return null;
		List<WsPlasRole> list = PlasCache.getRoleList();
		for (WsPlasRole t : list) {
			if(roleId.equals(t.getPlasRoleId()))
				return t;
		}
		return null;
	}
}
