package com.hhz.uums.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppRoleFunctionRel;
import com.hhz.uums.entity.app.AppRoleMenuRel;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.service.impl.AcegiUser;

@Service
@Transactional
public class AppRoleMenuRelManager extends BaseService<AppRoleMenuRel, String> {
	@Autowired
	private AppRoleMenuRelDao appRoleMenuRelDao;

	@Autowired
	private AppMenuManager appMenuManager;

	@Autowired
	private PlasRoleManager plasRoleManager;
	
	@Autowired
	private AppRoleFunctionRelDao appRoleFunctionRelDao;
	public void saveAppRoleMenuRel(AppRoleMenuRel appRoleMenuRel) {
		PowerUtils.setEmptyStr2Null(appRoleMenuRel);
		appRoleMenuRelDao.save(appRoleMenuRel);
	}

	public void deleteAppRoleMenuRel(String id) {
		appRoleMenuRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<AppRoleMenuRel, String> getDao() {
		return appRoleMenuRelDao;
	}
	/**
	 * 根据角色查询菜单
	 * 
	 * @return
	 */
	public List<AppMenu> searchMenu(String roleCd) {
		String[] roleCds = roleCd.split(",");
		Map<String,Object> map=new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select distinct menu from AppMenu menu,AppRoleMenuRel mrel where ");
		hql.append("mrel.appMenu.appMenuId= menu.appMenuId ");
		if (roleCds.length > 0) {
			hql.append(" and mrel.roleCd in (:roleCd) ");
			map.put("roleCd", roleCds);
		}
		hql.append("order by menu.sequenceNo asc");
		
		List<AppMenu> menus = appMenuManager.find(hql.toString(), map);
		
		return menus;

	}
	public boolean isPermission(String muneCd) {
		AcegiUser loginUser = SpringSecurityUtils.getCurrentUser();
		StringBuffer hql = new StringBuffer(
				"from AppRoleMenuRel afr where afr.roleCd in (?) and afr.appMenu.menuCd=? ");
		long cnt=countHqlResult(hql.toString(), loginUser.getRoleCds(), muneCd);
		if (cnt>0)
			return true;
		return false;
	}
	public void saveRoleRel(List<AppRoleMenuRel> lstAppRoleMenuRel,
			List<String> lstDelete,
			List<AppRoleFunctionRel> lstAppRoleFunctionRel,
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
	public void saveBatch(String roleCd,String addMenuIds,String delMenuIds){
		
		String[] addArray = addMenuIds.split(",");
		String[] delArray = delMenuIds.split(",");
		AppRoleMenuRel obj = null;
		AppMenu menu = null;

			//配置系统职位与角色的对应关系
			if(addMenuIds!= null){
				for(int i = 0;i<addArray.length; i++){
					if(StringUtils.isNotBlank(addArray[i])){
						obj = new AppRoleMenuRel();
						menu = new AppMenu();
						menu.setAppMenuId(addArray[i]);
						obj.setAppMenu(menu);
						obj.setRoleCd(roleCd);
						try{
							
							saveAppRoleMenuRel(obj);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
/*					// 保存操作日志
					String operUiid = SpringSecurityUtils.getLoginCode();
					String operUserName = SpringSecurityUtils.getCurUserName();
					
						plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
								new StringBuffer("[").append(entity.getPlasSysPosition().getSysPosName()).append(",").append(entity.getPlasRole().getRoleName()).append(
										"]新增系统职位与角色关系成功!").toString());*/
				}
			}
			//收回系统职位和角色的对应关系
				if(delMenuIds != null){
					for(int i = 0;i<delArray.length; i++){
						if(StringUtils.isNotBlank(delArray[i])){
							List<AppRoleMenuRel> result = this.findByRoleCdAMenuId(roleCd,delArray[i]);
							this.delete(result);
						}
/*						
						// 保存操作日志
						String operUiid = SpringSecurityUtils.getLoginCode();
						String operUserName = SpringSecurityUtils.getCurUserName();
						
							plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.DEL,
									new StringBuffer("[").append(entity.getPlasSysPosition().getSysPosName()).append(",").append(entity.getPlasRole().getRoleName()).append(
											"]删除系统职位与角色关系成功!").toString());*/
					}
				}
		}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppRoleMenuRel> findByRoleCdAMenuId(String roleCd,String menuId) {
		String[] roleCds = roleCd.split(",");
		Criterion criterion;
		if (roleCds.length > 1) {
			criterion = Restrictions.in("roleCd", roleCds);
		} else {
			criterion = Restrictions.eq("roleCd", roleCd);
		}
		Criterion criterion1 = Restrictions.eq("appMenu.appMenuId", menuId);
		return findBy(criterion,criterion1);
	}

	/**
	 * 根据菜单编号,查询所有角色列表.
	 * @param tMenuId
	 * @return
	 */
	public List<String> getRoleList(String tMenuId) {
		String hql = "select t2.plasRoleId from PlasRole t2 where exists(select t.appRoleMenuRelId from AppRoleMenuRel t where t.roleCd = t2.roleCd and t.appMenu.appMenuId = ? ) ";
		List list = this.find(hql, tMenuId);
		if(list == null )
			return new ArrayList<String>();
		else
			return list;
	}
	
	/**
	 * 查询菜单角色关系
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public AppRoleMenuRel getRoleMenuRel(String menuId, String roleId){
		String hql = "from AppRoleMenuRel t where t.appMenu.appMenuId = ? and t.roleCd in(select t2.roleCd from PlasRole t2 where t2.plasRoleId = ?)";
		List list = this.find(hql, menuId, roleId);
		if(list == null || list.size() == 0)
			return null;
		else
			return (AppRoleMenuRel)list.get(0);
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
		
		PlasRole role = null;
		
		//增加菜单的角色；
		if(addArray != null){
			for(int i=0; i<addArray.length; i++){
				if(StringUtils.isNotBlank(addArray[i])){
					rel = getRoleMenuRel(menuId, addArray[i]);
					if(rel == null){
						role = plasRoleManager.getEntity(addArray[i]);
						rel = new AppRoleMenuRel();
						rel.setAppMenu(menu);
						rel.setRoleCd(role.getRoleCd());
						saveAppRoleMenuRel(rel);
					}
				}
			}
		}

		//删除菜单的角色；
		if(delArray != null){
			for(int i=0; i<delArray.length; i++){
				if(StringUtils.isNotBlank(delArray[i])){
					rel = getRoleMenuRel(menuId, delArray[i]);
					if(rel != null){
						delete(rel);
					}
				}
			}
		}
	}
}

