package com.hhz.ump.dao.fin;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.fin.FinItem;

@Repository
public class FinItemDao extends HibernateDao<FinItem, String> {
}

