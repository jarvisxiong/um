package com.hhz.ump.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.web.vo.ExAppModuleMenuRelVo;
import com.hhz.ump.web.vo.VoModuleMenuPage;

@Service
@Transactional
public class AppModuleMenuRelManager extends BaseService<AppModuleMenuRel, String> {
	@Autowired
	private AppModuleMenuRelDao appModuleMenuRelDao;
	@Autowired
	private AppMenuManager appMenuManager;
	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;
	// AppModuleMenuRel Manager //
	
	public void saveAppModuleMenuRel(AppModuleMenuRel appModuleMenuRel) {
		PowerUtils.setEmptyStr2Null(appModuleMenuRel);
		// same transaction
		AppMenu menu = appModuleMenuRel.getAppMenu();
		// 很重要
		appMenuManager.saveAppMenu(menu);
		appModuleMenuRelDao.save(appModuleMenuRel);
	}

	public void deleteAppModuleMenuRel(String id) {
		// same transaction
		String menuId = getEntity(id).getAppMenu().getAppMenuId();
		appRoleMenuRelManager.deleteAppRoleMenuRelByMenu(menuId);
		appModuleMenuRelDao.delete(id);
		appMenuManager.deleteAppMenu(menuId);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppModuleMenuRel, String> getDao() {
		return appModuleMenuRelDao;
	}

	/**
	 * 根据模块ID,菜单ID,获取模块菜单关系信息
	 * 
	 * @param moduleId
	 * @param menuId
	 * @return
	 */
	public AppModuleMenuRel getRelByModuleIdAndMenuId(String moduleId,
			String menuId) {

		// 不能用hql搜索,否则带不出父表
		// String hql = " from AppModuleMenuRel t "
		// + " where t.appModule.appModuleId = '" + moduleId + "'"
		// + " and t.appMenu.appMenuId = '" + menuId + "' ";

		// List<AppModuleMenuRel> list =
		// getDao().getSession().createQuery(hql).list();
		// List list = getDao().getAll();
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("appModule.appModuleId", moduleId));
		filters.add(new PropertyFilter("appMenu.appMenuId", menuId));
		List<AppModuleMenuRel> list =find(filters);
//		List<AppModuleMenuRel> list = getDao()
//		.getSession().createCriteria(
//						AppModuleMenuRel.class).add(
//						Restrictions
//						.and(
//								Restrictions.eq("appModule.appModuleId",
//										moduleId), Restrictions.eq(
//										"appMenu.appMenuId", menuId))).list();

		if (list.size() == 0)
			return null;
		else
			return (AppModuleMenuRel) list.get(0);
	}

	/**
	 * 根据角色列表,搜索所有模块菜单关系.
	 * 
	 * @param roleList
	 * @return
	 */
	public List<ExAppModuleMenuRelVo> getModuleMenuRelListByRoles(
			List<String> roleCdList) {

		StringBuffer sb = new StringBuffer();
		for (String roleCd : roleCdList) {
			sb.append("'").append(roleCd).append("',");
		}
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("roleCdList",sb.toString().substring(0,sb.length()-1));
		
		String hql = "    select distinct t1.appModuleId, t4.appMenuId,t4.menuName,t5.pagePath "
				+ "  from AppModule t1,AppModuleMenuRel t2,AppRoleMenuRel t3,AppMenu t4, AppPage t5"
				+ " where t1.appModuleId = t2.appModule.appModuleId "
				+ "   and t2.appMenu.appMenuId = t3.appMenu.appMenuId "
				+ "   and t4.appMenuId = t3.appMenu.appMenuId "
				+ "   and t4.pageCd = t5.pageCd "
				+ "   and t3.roleCd in ( :roleCdList )";

		List resultList = find(hql,values);
		
		List<ExAppModuleMenuRelVo> list = new ArrayList<ExAppModuleMenuRelVo>();
	
		for (int i = 0; i < resultList.size(); i++) {
			Object[] object = (Object[]) resultList.get(i);
			String appModuleId = (String) object[0];
			String appMenuId = (String) object[1];
			String appMenuName = (String) object[2];
			String pagePath = (String) object[3];
			
			ExAppModuleMenuRelVo vo = new ExAppModuleMenuRelVo();
			vo.setVoAppModuleId(appModuleId);
			vo.setVoAppMenuId(appMenuId);
			vo.setVoAppMenuName(appMenuName);
			vo.setVoPagePath(pagePath);
			list.add(vo);
		}

		return list;
		/*
		 * List appModuleList = new ArrayList(); for (int i=0;
		 * i<resultList.size(); i++) { Object[] object = (Object[])
		 * resultList.get(i); AppModule appModule = (AppModule) object[0];
		 * appModuleList.add(appModule); }
		 */
	}
	/**
	 * 构建以moduleId为key，List<AppMenu>为value的Map
	 */
	
	public Map<String,List<AppMenu>> getMenuMap(){
		String hql = "select t1.appModule.appModuleId,t1.appMenu from AppModuleMenuRel t1 " ;
		Map<String,Object> values = new HashMap<String,Object>();
		List list = find(hql,values );
		AppMenu tmp = null;
		List<AppMenu> result =null;
		String tmpParentId = null;
		Map<String,List<AppMenu>> vos = new HashMap<String,List<AppMenu>>();
		for(Object obj : list){
			Object[] o = (Object[]) obj;
			tmp = (AppMenu)o[1];
		
			tmpParentId = (String)o[0];
			result = vos.get(tmpParentId);
			if(null==result){
				result =   new ArrayList<AppMenu>();
			}
			result.add(tmp);
		
			vos.put(tmpParentId, result);
			
		}
		return vos;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<VoModuleMenuPage> getVoModuleMenuPageList(String menuOrpageName , Integer iMaxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuOrpageName", "%" + menuOrpageName + "%");
		StringBuffer hql = new StringBuffer();
		hql.append("    select distinct t.appModule.appModuleId, ")
		.append("     t.appModule.moduleName,")
		.append("     t.appMenu.appMenuId,")
		.append("     t.appMenu.menuName,")
		.append("     t.appMenu.appPage.appPageId,")
		.append("     t.appMenu.appPage.pageName")
		.append("    from AppModuleMenuRel t")
		.append("    where t.appMenu.menuName like :menuOrpageName")
		.append("    	or t.appMenu.appPage.pageName like :menuOrpageName")
		;
		List<VoModuleMenuPage> result = new ArrayList<VoModuleMenuPage>();
		List resultList = find(hql.toString(),map);
		Object[] o = null;
		VoModuleMenuPage vo = null;
		for(Object tmp : resultList){
			o = (Object[])tmp;
			vo = new VoModuleMenuPage();
			vo.setModuleId((String)o[0]);
			vo.setModuleName((String)o[1]);
			vo.setMenuId((String)o[2]);
			vo.setMenuName((String)o[3]);
			vo.setPageId((String)o[4]);
			vo.setPageName((String)o[5]);
			result.add(vo);
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<VoModuleMenuPage> getVoModuleMenuList(String menuName , Integer iMaxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuName", "%" + menuName + "%");
		StringBuffer hql = new StringBuffer();
		hql.append("    select distinct t.appModule.appModuleId, ")
		.append("     t.appModule.moduleName,")
		.append("     t.appMenu.appMenuId,")
		.append("     t.appMenu.menuName")
		.append("    from AppModuleMenuRel t")
		.append("    where t.appMenu.menuName like :menuName")
		;
		List<VoModuleMenuPage> result = new ArrayList<VoModuleMenuPage>();
		List resultList = find(hql.toString(),map);
		Object[] o = null;
		VoModuleMenuPage vo = null;
		for(Object tmp : resultList){
			o = (Object[])tmp;
			vo = new VoModuleMenuPage();
			vo.setModuleId((String)o[0]);
			vo.setModuleName((String)o[1]);
			vo.setMenuId((String)o[2]);
			vo.setMenuName((String)o[3]);
			result.add(vo);
		}
		return result;
	}
	/**
	 * 获取排序后的模块与菜单关系
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppModuleMenuRel> getAllOrdered(){
		String hql = " select t, t.appModule.dispOrderNo, t.appMenu.dispOrderNo from AppModuleMenuRel t order by t.appModule.dispOrderNo asc , t.appMenu.dispOrderNo asc ";
		List list = appModuleMenuRelDao.createQuery(hql, new HashMap<String,Object>()).list();
		if(list == null)
			return new ArrayList<AppModuleMenuRel>();
		
		List rtnList = new ArrayList<AppModuleMenuRel>();
		Object[] tmp = null;
		for (Object tRel : list) {
			tmp = (Object[])tRel;
			rtnList.add(tmp[0]);
		}
		return rtnList;
	}
}

