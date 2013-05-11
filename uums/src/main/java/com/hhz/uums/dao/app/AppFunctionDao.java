package com.hhz.uums.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.app.AppFunction;

@Repository
public class AppFunctionDao extends HibernateDao<AppFunction, String> {
}

