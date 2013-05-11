package com.hhz.ump.dao.sup;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.sup.SupItem;

@Repository
public class SupItemDao extends HibernateDao<SupItem, String> {
}

