package com.hhz.uums.dao.plas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRolePack;
import com.hhz.uums.entity.plas.PlasRolePackRel;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class PlasRolePackRelManager extends BaseService<PlasRolePackRel, String> {
	@Autowired
	private PlasRolePackRelDao plasRolePackRelDao;
	@Autowired
	private PlasRolePackDao plasRolePackDao;
	@Autowired
	private PlasRoleDao plasRoleDao;

	public void savePlasRolePackRel(PlasRolePackRel plasRolePackRel) {
		PowerUtils.setEmptyStr2Null(plasRolePackRel);
		plasRolePackRelDao.save(plasRolePackRel);
	}

	public void deletePlasRolePackRel(String id) {
		plasRolePackRelDao.delete(id);
	}
	
	public List<PlasRolePackRel> getCurRolePackRelList(String plasRolePackId) {
		String hql = " from PlasRolePackRel t where t.plasRolePack.plasRolePackId = ? ";
		return getDao().createQuery(hql,plasRolePackId).list();
	}
	
	/**
	 * 批量保存角色包对应的角色
	 * @param plasRolePackId
	 * @param addRoleIds
	 * @param delRoleIds
	 */
	public void saveBatch(String plasRolePackId, String addRoleIds, String delRoleIds){
		String[] addArray = addRoleIds.split(",");
		String[] delArray = delRoleIds.split(",");
		
		PlasRolePack rolePack = plasRolePackDao.get(plasRolePackId);
		//配置角色包与角色的对应关系
		PlasRole role = null;
		if(StringUtils.isNotBlank(addRoleIds)){
			PlasRolePackRel plasRolePackRel = null;
			for(int i = 0;i < addArray.length; i++){
				role = plasRoleDao.get(addArray[i]);
				plasRolePackRel = new PlasRolePackRel();
				plasRolePackRel.setPlasRolePack(rolePack);
				plasRolePackRel.setPlasRole(role);
				savePlasRolePackRel(plasRolePackRel);
			}
		}
		//收回角色包和角色的对应关系
		if(StringUtils.isNotBlank(delRoleIds)){
			for(int i = 0;i < delArray.length; i++){
				deleteRolePackRelById(plasRolePackId,delArray[i]);
			}
		}
	}
	/**
	 * 删除角色与角色包的关系
	 * @param plasRolePackId
	 * @param roleId
	 * @return
	 */
	public boolean deleteRolePackRelById(String plasRolePackId, String roleId){
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("plasRolePackId", plasRolePackId);
		values.put("roleId", roleId);
		String hql = "delete from PlasRolePackRel t where t.plasRolePack.plasRolePackId = :plasRolePackId and t.plasRole.plasRoleId = :roleId ";
		getDao().batchExecute(hql, values);
		return true;
	}
	
	@Override
	public HibernateDao<PlasRolePackRel, String> getDao() {
		return plasRolePackRelDao;
	}
	
}

