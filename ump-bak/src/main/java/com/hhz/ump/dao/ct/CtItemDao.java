package com.hhz.ump.dao.ct;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.ct.CtItem;

@Repository
public class CtItemDao extends HibernateDao<CtItem, String> {
}

