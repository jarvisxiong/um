package com.hhz.ump.dao.planold;

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
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.entity.planold.PlanExecutionPlanOld;

@Service
@Transactional
public class PlanExecutionPlanOldManager extends BaseService<PlanExecutionPlanOld, String> {
	private static final Logger logger = Logger.getLogger(PlanExecutionPlanOldManager.class);

	@Autowired
	private PlanExecutionPlanOldDao planExecutionPlanDao;

	@Autowired
	private PlanOperationLogOldManager planOperationLogManager;

	// PlanExecutionPlanOld Manager //
	@Transactional(readOnly = true)
	public PlanExecutionPlanOld getPlanExecutionPlan(String id) {
		return planExecutionPlanDao.get(id);
	}

	public List<PlanExecutionPlanOld> getAllPlanExecutionPlan() {
		return planExecutionPlanDao.getAll();
	}

	public void savePlanExecutionPlan(PlanExecutionPlanOld planExecutionPlan) {
		PowerUtils.setEmptyStr2Null(planExecutionPlan);
		planExecutionPlanDao.save(planExecutionPlan);
	}

	public void deletePlanExecutionPlan(String id) {
		planExecutionPlanDao.delete(id);
	}

	@Override
	public HibernateDao<PlanExecutionPlanOld, String> getDao() {
		return planExecutionPlanDao;
	}

	/**
	 * 功能: 获取某项目的业态模块
	 * @param projCd
	 * @param planTypeCd
	 * @param isActive : 是否可用
	 * 
	 * @return
	 */
	public List<PlanExecutionPlanOld> getPlansPerProjCd(String projCd, String planTypeCd, boolean isActive) {
		if (StringUtils.isBlank(projCd))
			throw new IllegalArgumentException("项目CD不能为空.");

		String hql = " from PlanExecutionPlanOld where projectCd = :projectCd and planTypeCd = :planTypeCd ";
		if (isActive) {
			hql += " and activeFlg = '1'";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projCd);
		params.put("planTypeCd", planTypeCd);

		return this.find(hql, params);
	}

	/**
	 * 功能: 保存项目计划配置信息
	 * @param projPlanId
	 * @param projCd
	 * @param planTypeCd
	 * @param planName
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean saveProjPlanInfo(String projPlanId, String projCd, String planTypeCd, String planName) throws Exception {
		if (StringUtils.isBlank(projCd)) {
			logger.warn("传入的项目CD不能为空!");
			return false;
		}

		String operationType = "";
		String operationLog = "";

		PlanExecutionPlanOld p = null;
		if (StringUtils.isBlank(projPlanId)) {
			p = new PlanExecutionPlanOld();
			p.setProjectCd(projCd);
			Criteria c = planExecutionPlanDao.getSession().createCriteria(PlanExecutionPlanOld.class);
			int count = planExecutionPlanDao.countCriteriaResult(c);
			p.setExecutionPlanCd(String.valueOf(count + 1));
			p.setActiveFlg("1");
			p.setPlanTypeCd(planTypeCd);

			operationType = PlanOperationLogManager.OPERATION_TYPE_ADD;
			operationLog = "新增业态:" + planName;
		} else {
			p = this.getEntity(projPlanId);
			if (p == null || !projCd.equals(p.getProjectCd())) {
				logger.warn("传入的业态ID不合法!");
				return false;
			}
			operationType = PlanOperationLogManager.OPERATION_TYPE_UPDATE;
			String oriPlanName = p.getExecutionPlanName();
			operationLog = "业态\"" + oriPlanName + "\"重命名为\"" + planName + "\"";
		}

		p.setExecutionPlanName(planName);
		savePlanExecutionPlan(p);
		
		planOperationLogManager.addPlanLog(p.getPlanTypeCd(), projPlanId, "配置业态", operationType, projCd, operationLog);

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
		PlanExecutionPlanOld p = this.getEntity(projPlanId);
		p.setActiveFlg(state);
		savePlanExecutionPlan(p);

		String projCd = p.getProjectCd();
		String planName = p.getExecutionPlanName();
		String operationType = PlanOperationLogManager.OPERATION_TYPE_UPDATE;
		String operationLog = "";
		if (state.equals("1")) {
			operationLog = "业态\"" + planName + "\"被激活";
		} else {
			operationLog = "业态\"" + planName + "\"被禁用";
		}
		planOperationLogManager.addPlanLog(p.getPlanTypeCd(), projPlanId, "业态:" + planName, operationType, projCd, operationLog);

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

		String hql = "from PlanExecutionPlanOld where executionPlanCd=:planCd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planCd", planCd);

		List<PlanExecutionPlanOld> result = this.find(hql, params);
		if (result.size() > 0) {
			if (result.size() > 1)
				throw new RuntimeException("一个PlanCd只能对应一条记录");
			PlanExecutionPlanOld p = result.get(0);
			planName = p.getExecutionPlanName();
		}

		return planName;
	}
}
