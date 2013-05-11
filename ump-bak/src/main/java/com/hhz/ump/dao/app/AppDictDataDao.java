package com.hhz.ump.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.app.AppDictData;

@Repository
public class AppDictDataDao extends HibernateDao<AppDictData, String> {
}

