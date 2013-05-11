package com.hhz.ump.dao.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanProcessing;

@Service
@Transactional
public class ExecPlanLayoutManager extends BaseService<ExecPlanLayout, String> {
	@Autowired
	private ExecPlanLayoutDao execPlanLayoutDao;
	@Autowired
	private ExecPlanProcessingDao execPlanProcessingDao;

	public void saveExecPlanLayout(ExecPlanLayout execPlanLayout) {
		PowerUtils.setEmptyStr2Null(execPlanLayout);
		execPlanLayoutDao.save(execPlanLayout);
	}

	public void deleteExecPlanLayout(String id) {
		execPlanLayoutDao.delete(id);
	}
	
	@Override
	public HibernateDao<ExecPlanLayout, String> getDao() {
		return execPlanLayoutDao;
	}
	
	

	public void deleteExecPlanProcessing(String projectCd,String resOrgName, String startDate, String endDate) {
		
		if(StringUtils.isNotEmpty(projectCd)&&StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder hql = new StringBuilder("delete from ExecPlanProcessing where");
			hql.append(" projectCd=:projectCd and resOrgName=:resOrgName and createdDate >= to_date(:startDate,'YYYY/MM/DD')");
		    params.put("projectCd",projectCd);
		    params.put("resOrgName", resOrgName);
		    params.put("startDate", startDate);
			execPlanProcessingDao.batchExecute(hql.toString(), params);
			  // params.put("endDate", endDate);
			/*	List<ExecPlanProcessing> arr = execPlanProcessingDao.find(sb.toString(), params);
				for (ExecPlanProcessing entity : arr) {
					execPlanProcessingDao.delete(entity);
				}*/
		}
		
	}
	public void saveExecPlanProcessing(ExecPlanProcessing execPlanProcessing) {
		PowerUtils.setEmptyStr2Null(execPlanProcessing);
		execPlanProcessingDao.save(execPlanProcessing);
	}

	/**
	 * 功能: 获取某项目的业态模块
	 * @param projCd
	 * @param planTypeCd
	 * @param isActive : 是否可用
	 * @return
	 */
	public List<ExecPlanLayout> getLayouts(String projCd, String planTypeCd, boolean isActive,boolean if_bis) {
		if (StringUtils.isBlank(projCd))
			throw new IllegalArgumentException("项目CD不能为空.");

		String hql = " from ExecPlanLayout where projectCd = :projectCd ";
		if (isActive) {
			hql += " and activeFlg = '1'";
		}
		if (if_bis) {
			hql += " and ifBis = '1'";
		}
		hql += " order by sequenceNo asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projCd);

		return this.find(hql, params);
	}

	/**
	 * 功能: 获取某项目的业态模块
	 * @param projCd
	 * @param planTypeCd
	 * @param isActive : 是否可用
	 * @return
	 */
	public List<ExecPlanLayout> getPlansPerProjCd(String projCd, String planTypeCd, boolean isActive) {
		if (StringUtils.isBlank(projCd))
			throw new IllegalArgumentException("项目CD不能为空.");

		String hql = " from ExecPlanLayout where projectCd = :projectCd ";
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
	public boolean saveProjPlanInfo(String projPlanId, String projCd, String planTypeCd, String planName, Long sequenceNo) throws Exception {
		if (StringUtils.isBlank(projCd))
			return false;

		ExecPlanLayout p = null;
		if (StringUtils.isBlank(projPlanId)) {
			p = new ExecPlanLayout();
			p.setProjectCd(projCd);
			Criteria c = execPlanLayoutDao.getSession().createCriteria(ExecPlanLayout.class);
			int count = execPlanLayoutDao.countCriteriaResult(c);
			p.setExecutionPlanCd(String.valueOf(count + 1));
			p.setActiveFlg("1");
			p.setPlanTypeCd(planTypeCd);
		} else {
			p = this.getEntity(projPlanId);
			if (p == null || !projCd.equals(p.getProjectCd()))
				return false;
		}

		if(null!=planName && !"".equalsIgnoreCase(planName)){
			p.setExecutionPlanName(planName);
		}
		if(null!=planName){
			p.setSequenceNo(sequenceNo);
		}
		saveExecPlanLayout(p);
		
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
		if (StringUtils.isBlank(projPlanId))
			return false;
		ExecPlanLayout p = this.getEntity(projPlanId);
		p.setActiveFlg(state);
		saveExecPlanLayout(p);
		return true;
	}

	/**
	 * 功能: 根据计划CD获取计划名称
	 * @param planCd
	 * @return
	 */
	public String getLayoutNameById(String layoutId) {
		String planName = "";
		String hql = "from ExecPlanLayout where execPlanLayoutId=:layoutId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", layoutId);

		List<ExecPlanLayout> result = this.find(hql, params);
		if (result.size() > 0) {
			if (result.size() > 1)
				throw new RuntimeException("一个PlanCd只能对应一条记录");
			ExecPlanLayout p = result.get(0);
			planName = p.getExecutionPlanName();
		}

		return planName;
	}
}

