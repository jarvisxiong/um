package com.hhz.ump.dao.bis;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.bis.BisPay;

@Repository
public class BisPayDao extends HibernateDao<BisPay, String> {
}

