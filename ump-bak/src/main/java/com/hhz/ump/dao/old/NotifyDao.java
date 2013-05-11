package com.hhz.ump.dao.old;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.old.Notify;

@Repository
public class NotifyDao extends HibernateDao<Notify, String> {
}

