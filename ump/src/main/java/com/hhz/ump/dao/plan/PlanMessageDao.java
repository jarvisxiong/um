package com.hhz.ump.dao.plan;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.plan.PlanMessage;

@Repository
public class PlanMessageDao extends HibernateDao<PlanMessage, String> {
}

