package com.hhz.uums.dao.plas;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.plas.PlasRole;

@Repository
public class PlasRoleDao extends HibernateDao<PlasRole, String> {
	/**
	 * 功能: 查询用户所有角色
	 * 
	 * @param roleCd
	 *            : 用户统一登录名
	 */

	public PlasRole getPlasRoleByRoleCd(String roleCd) {

		List result = createCriteria(PlasRole.class).add(Restrictions.eq("roleCd", roleCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasRole) result.get(0);
	} 
}

