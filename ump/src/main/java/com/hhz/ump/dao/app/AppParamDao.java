package com.hhz.ump.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.app.AppParam;

@Repository
public class AppParamDao extends HibernateDao<AppParam, String> {
}

