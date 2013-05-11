package com.hhz.uums.dao.plas;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasRoleGroup;

@Service
@Transactional
public class PlasRoleGroupManager extends BaseService<PlasRoleGroup, String> {
	@Autowired
	private PlasRoleGroupDao plasRoleGroupDao;

	public void savePlasRoleGroup(PlasRoleGroup plasRoleGroup) {
		PowerUtils.setEmptyStr2Null(plasRoleGroup);
		plasRoleGroupDao.save(plasRoleGroup);
	}

	public void deletePlasRoleGroup(String id) {
		plasRoleGroupDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasRoleGroup, String> getDao() {
		return plasRoleGroupDao;
	}
	/**
	 * 查询应用角色组
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRoleGroup> getAppRoleGroup(String appId) {

		String hql = " from PlasRoleGroup t where t.parentId = ? order by t.roleGroupName asc  ";
		return getDao().createQuery(hql, appId).list();
	}
	/**
	 * 查询应用角色组
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRoleGroup> getAppRoleGroup() {

		String hql = " from PlasRoleGroup t order by t.roleGroupName asc  ";
		return getDao().createQuery(hql, new HashMap<String,Object>()).list();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRoleGroup> getAllOrderedGroups() {
		String hql = " from PlasRoleGroup t order by t.sequenceNo asc ";
		return getDao().createQuery(hql).list();
	}
}

