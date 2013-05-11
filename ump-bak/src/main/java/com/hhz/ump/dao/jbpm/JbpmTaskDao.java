package com.hhz.ump.dao.jbpm;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.jbpm.JbpmTask;

@Repository
public class JbpmTaskDao extends HibernateDao<JbpmTask, String> {
}

