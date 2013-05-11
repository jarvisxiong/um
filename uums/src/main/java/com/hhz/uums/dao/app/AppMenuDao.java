package com.hhz.uums.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.app.AppMenu;

@Repository
public class AppMenuDao extends HibernateDao<AppMenu, String> {
}

