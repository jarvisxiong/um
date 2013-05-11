package com.hhz.uums.dao.app;

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
import com.hhz.uums.entity.app.AppFunction;
import com.hhz.uums.entity.app.AppRoleFunctionRel;
import com.hhz.uums.service.impl.AcegiUser;

@Service
@Transactional
public class AppRoleFunctionRelManager extends
		BaseService<AppRoleFunctionRel, String> {
	@Autowired
	private AppRoleFunctionRelDao appRoleFunctionRelDao;

	public void saveAppRoleFunctionRel(AppRoleFunctionRel appRoleFunctionRel) {
		PowerUtils.setEmptyStr2Null(appRoleFunctionRel);
		appRoleFunctionRelDao.save(appRoleFunctionRel);
	}

	public void deleteAppRoleFunctionRel(String id) {
		appRoleFunctionRelDao.delete(id);
	}

	@Override
	public HibernateDao<AppRoleFunctionRel, String> getDao() {
		return appRoleFunctionRelDao;
	}

	public boolean isPermission(String funCd) {
		AcegiUser loginUser = SpringSecurityUtils.getCurrentUser();
		StringBuffer hql = new StringBuffer(
				"from AppRoleFunctionRel afr where afr.roleCd in (?) and afr.appFunction.functionCd=? ");
		long cnt=countHqlResult(hql.toString(), loginUser.getRoleCds(), funCd);
		if (cnt>0)
			return true;
		return false;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppRoleFunctionRel> findByRoleCd(String roleCd) {
		Criterion criterion = Restrictions.eq("roleCd", roleCd);
		return findBy(criterion);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveRoleRel(String roleCd,String menuCd,String addFuncIds,String delFuncIds){
		
		String[] addArray = addFuncIds.split(",");
		String[] delArray = delFuncIds.split(",");
		AppRoleFunctionRel obj = null;
//		AppMenu menu = null;

			//配置系统职位与角色的对应关系
			if(addFuncIds!=null){
				for(int i = 0;i<addArray.length; i++){
					if(StringUtils.isNotBlank(addArray[i])){
						obj = new AppRoleFunctionRel();
						obj.setRoleCd(roleCd);
						obj.setMenuCd(menuCd);
						AppFunction appFunction = new AppFunction();
						appFunction.setAppFunctionId(addArray[i]);
						obj.setAppFunction(appFunction);
						obj.setPermisionStatusCd("");
						appRoleFunctionRelDao.save(obj);
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
			if( delFuncIds!= null){
				for(int i = 0;i<delArray.length; i++){
					if(StringUtils.isNotBlank(delArray[i])){
					this.delByRoleMenuAFunc(roleCd,menuCd,delArray[i]);
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
	public void delByRoleMenuAFunc(String roleCd,String menuCd,String funcId){
		Map<String,Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("delete  from AppRoleFunctionRel rel where  roleCd =:roleCd and menuCd := menuCd and rel.appFunction.appFunctionId := funcId ");
		
		values.put("roleCd", roleCd);
		values.put("menuCd", menuCd);
		values.put("funcId",funcId);
		appRoleFunctionRelDao.batchExecute(hql.toString(), values);
	}
}
