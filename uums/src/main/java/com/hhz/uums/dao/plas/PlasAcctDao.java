package com.hhz.uums.dao.plas;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasUser;

@Repository
public class PlasAcctDao extends HibernateDao<PlasAcct, String> {

	public PlasUser attemptLogin(String loginName, String password) {

		List resultList = (List) getSession().createCriteria(PlasUser.class)
				.add(Restrictions.eq("uiid", loginName))
				.add(Restrictions.eq("loginInPassword", password)).list();

		if (resultList == null || resultList.size() == 0)
			return null;

		return (PlasUser) resultList.get(0);
	}
}
