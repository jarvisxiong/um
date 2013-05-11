package com.hhz.ump.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.app.UserPool;

@Repository
public class UserPoolDao extends HibernateDao<UserPool, String> {
}

