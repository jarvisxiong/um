package com.hhz.uums.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.app.AppRule;

@Repository
public class AppRuleDao extends HibernateDao<AppRule, String> {
}

