package com.hhz.ump.dao.old;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.old.News;

@Repository
public class NewsDao extends HibernateDao<News, String> {
}

