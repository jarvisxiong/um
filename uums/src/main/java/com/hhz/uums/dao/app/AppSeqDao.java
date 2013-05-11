package com.hhz.uums.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.app.AppSeq;

@Repository
public class AppSeqDao extends HibernateDao<AppSeq, String> {
}

