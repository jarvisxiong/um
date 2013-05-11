package com.hhz.ump.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.app.AppDictType;

@Repository
public class AppDictTypeDao extends HibernateDao<AppDictType, String> {

	// @Override
	// public Criteria createCriteria(final Criterion... criterions) {
	// Criteria criteria = getSession().createCriteria(entityClass);
	// criteria.setCacheable(true);
	// for (Criterion c : criterions) {
	// criteria.add(c);
	// }
	// return criteria;
	// }
}

