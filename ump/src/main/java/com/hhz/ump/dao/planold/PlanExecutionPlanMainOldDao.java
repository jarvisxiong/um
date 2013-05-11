package com.hhz.ump.dao.planold;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.planold.PlanExecutionPlanMainOld;

@Repository
public class PlanExecutionPlanMainOldDao extends HibernateDao<PlanExecutionPlanMainOld, String> {

	/**
	 * 根据项目CD获取PlanExecutionPlanMain列表
	 * 
	 * @param projCd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanExecutionPlanMainOld> getExecutionPlanMainByProjCd(String projCd) {
		return createCriteria(PlanExecutionPlanMainOld.class).add(Restrictions.eq("projectCd", projCd)).list();
	}
}
