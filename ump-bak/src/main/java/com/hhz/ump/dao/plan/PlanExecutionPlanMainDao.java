package com.hhz.ump.dao.plan;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.plan.PlanExecutionPlanMain;

@Repository
public class PlanExecutionPlanMainDao extends HibernateDao<PlanExecutionPlanMain, String> {

    /**
     * 根据项目CD获取PlanExecutionPlanMain列表
     * 
     * @param projCd
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PlanExecutionPlanMain> getExecutionPlanMainByProjCd(String projCd) {
	return createCriteria(PlanExecutionPlanMain.class).add(Restrictions.eq("projectCd", projCd)).list();
    }
}
