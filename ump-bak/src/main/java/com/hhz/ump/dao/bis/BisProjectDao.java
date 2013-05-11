package com.hhz.ump.dao.bis;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.bis.BisProject;

@Repository
public class BisProjectDao extends HibernateDao<BisProject, String> {
}

