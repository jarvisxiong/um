package com.hhz.ump.dao.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppRoleFunctionRel;

@Service
@Transactional
public class AppRoleFunctionRelManager extends BaseService<AppRoleFunctionRel, String> {
	@Autowired
	private AppRoleFunctionRelDao appRoleFunctionRelDao;

	public void delByRoleMenuAFunc(String roleCd,String menuCd,String funcId){
		Map<String,Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("delete  from AppRoleFunctionRel rel where  roleCd =:roleCd and menuCd := menuCd and rel.appFunction.appFunctionId := funcId ");
		
		values.put("roleCd", roleCd);
		values.put("menuCd", menuCd);
		values.put("funcId",funcId);
		appRoleFunctionRelDao.batchExecute(hql.toString(), values);
	}
	
	public void saveAppRoleFunctionRel(AppRoleFunctionRel appRoleFunctionRel) {
		PowerUtils.setEmptyStr2Null(appRoleFunctionRel);
		appRoleFunctionRelDao.save(appRoleFunctionRel);
	}

	public void saveRoleRel(List<AppRoleFunctionRel> lstAppRoleFunctionRel, List<String> lstDelete) {
		for (AppRoleFunctionRel appRoleFunctionRel : lstAppRoleFunctionRel) {
			saveAppRoleFunctionRel(appRoleFunctionRel);
		}
		String[] ids = new String[lstDelete.size()];
		lstDelete.toArray(ids);
		deleteBatch(ids);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveRoleRel(String roleCd,String menuCd,String addFuncIds,String delFuncIds){
		
		String[] addArray = addFuncIds.split(",");
		String[] delArray = delFuncIds.split(",");
		AppRoleFunctionRel obj = null;
		AppMenu menu = null;

			//配置系统职位与角色的对应关系
			if(addFuncIds!=null&&!"".equals(addFuncIds)){
				for(int i = 0;i<addArray.length; i++){
					obj = new AppRoleFunctionRel();
					obj.setRoleCd(roleCd);
					obj.setMenuCd(menuCd);
					AppFunction appFunction = new AppFunction();
					appFunction.setAppFunctionId(addArray[i]);
					obj.setAppFunction(appFunction);
					obj.setPermisionStatusCd("");
					appRoleFunctionRelDao.save(obj);
					
/*					// 保存操作日志
					String operUiid = SpringSecurityUtils.getLoginCode();
					String operUserName = SpringSecurityUtils.getCurUserName();
					
						plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
								new StringBuffer("[").append(entity.getPlasSysPosition().getSysPosName()).append(",").append(entity.getPlasRole().getRoleName()).append(
										"]新增系统职位与角色关系成功!").toString());*/
				}
			}
			//收回系统职位和角色的对应关系
				if(null!=delFuncIds&&!"".equals(delFuncIds)){
					for(int i = 0;i<delArray.length; i++){
				
						this.delByRoleMenuAFunc(roleCd,menuCd,delArray[i]);
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
	public void deleteAppRoleFunctionRel(String id) {
		appRoleFunctionRelDao.delete(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppRoleFunctionRel> findByRoleCd(String roleCd) {
		String[] arr = roleCd.split(",");
		Criterion criterion = Restrictions.in("roleCd", arr);
		return findBy(criterion);
	}
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppRoleFunctionRel, String> getDao() {
		return appRoleFunctionRelDao;
	}
	
}

