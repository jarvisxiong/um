package com.hhz.ump.dao.plan;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.plan.CostExecutionPlanMain;

@Repository
public class CostExecutionPlanMainDao extends HibernateDao<CostExecutionPlanMain, String> {

	/**
	 * 根据项目CD获取CostExecutionPlanMain列表
	 * 
	 * @param projCd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CostExecutionPlanMain> getExecutionPlanMainByProjCd(String projCd) {
		return createCriteria(CostExecutionPlanMain.class).add(Restrictions.eq("projectCd", projCd)).list();
	}
}
