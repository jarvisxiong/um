package com.hhz.ump.dao.plan;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.plan.PlanExecutionPlan;

@Repository
public class PlanExecutionPlanDao extends HibernateDao<PlanExecutionPlan, String> {
}

