package com.hhz.ump.dao.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanExecutionPlan;

@Service
@Transactional
public class PlanExecutionPlanManager extends BaseService<PlanExecutionPlan, String> {
	private static final Logger logger = Logger.getLogger(PlanExecutionPlanManager.class);

	@Autowired
	private PlanExecutionPlanDao planExecutionPlanDao;

	// PlanExecutionPlan Manager //
	@Transactional(readOnly = true)
	public PlanExecutionPlan getPlanExecutionPlan(String id) {
		return planExecutionPlanDao.get(id);
	}

	public List<PlanExecutionPlan> getAllPlanExecutionPlan() {
		return planExecutionPlanDao.getAll();
	}

	public void savePlanExecutionPlan(PlanExecutionPlan planExecutionPlan) {
		PowerUtils.setEmptyStr2Null(planExecutionPlan);
		planExecutionPlanDao.save(planExecutionPlan);
	}

	public void deletePlanExecutionPlan(String id) {
		planExecutionPlanDao.delete(id);
	}

	@Override
	public HibernateDao<PlanExecutionPlan, String> getDao() {
		return planExecutionPlanDao;
	}

	/**
	 * 功能: 获取某项目的业态模块
	 * @param projCd
	 * @param planTypeCd
	 * @param isActive : 是否可用
	 * @return
	 */
	public List<PlanExecutionPlan> getPlansPerProjCd(String projCd, String planTypeCd, boolean isActive) {
		if (StringUtils.isBlank(projCd))
			throw new IllegalArgumentException("项目CD不能为空.");

		String hql = " from PlanExecutionPlan where projectCd = :projectCd ";
		if (isActive) {
			hql += " and activeFlg = '1'";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projCd);

		return this.find(hql, params);
	}

	/**
	 * 功能: 保存项目计划配置信息
	 * @param projPlanId
	 * @param projCd
	 * @param planTypeCd
	 * @param planName
	 * @return
	 * @throws Exception 
	 */
	public boolean saveProjPlanInfo(String projPlanId, String projCd, String planTypeCd, String planName) throws Exception {
		if (StringUtils.isBlank(projCd)) {
			logger.warn("传入的项目CD不能为空!");
			return false;
		}

		PlanExecutionPlan p = null;
		if (StringUtils.isBlank(projPlanId)) {
			p = new PlanExecutionPlan();
			p.setProjectCd(projCd);
			Criteria c = planExecutionPlanDao.getSession().createCriteria(PlanExecutionPlan.class);
			int count = planExecutionPlanDao.countCriteriaResult(c);
			p.setExecutionPlanCd(String.valueOf(count + 1));
			p.setActiveFlg("1");
			p.setPlanTypeCd(planTypeCd);
		} else {
			p = this.getEntity(projPlanId);
			if (p == null || !projCd.equals(p.getProjectCd())) {
				logger.warn("传入的业态ID不合法!");
				return false;
			}
		}

		p.setExecutionPlanName(planName);
		savePlanExecutionPlan(p);
		
		return true;
	}

	/**
	 * 功能: 改变计划的状态
	 * @param projPlanId
	 * @param state
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean changePlanState(String projPlanId, String state) throws Exception {
		if (StringUtils.isBlank(projPlanId)) {
			logger.warn("传入的PLAN_ID不能为空.");
			return false;
		}
		PlanExecutionPlan p = this.getEntity(projPlanId);
		p.setActiveFlg(state);
		savePlanExecutionPlan(p);

		return true;
	}

	/**
	 * 功能: 根据计划CD获取计划名称
	 * @param planCd
	 * 
	 * @return
	 */
	public String getPlanNameByPlanCd(String planCd) {
		String planName = "";

		String hql = "from PlanExecutionPlan where executionPlanCd=:planCd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planCd", planCd);

		List<PlanExecutionPlan> result = this.find(hql, params);
		if (result.size() > 0) {
			if (result.size() > 1)
				throw new RuntimeException("一个PlanCd只能对应一条记录");
			PlanExecutionPlan p = result.get(0);
			planName = p.getExecutionPlanName();
		}

		return planName;
	}
}
