package com.hhz.ump.dao.cost;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostBudgetYear;

@Service
@Transactional
public class CostBudgetYearManager extends BaseService<CostBudgetYear, String> {
	@Autowired
	private CostBudgetYearDao costBudgetYearDao;

	public void saveCostBudgetYear(CostBudgetYear costBudgetYear) {
		PowerUtils.setEmptyStr2Null(costBudgetYear);
		costBudgetYearDao.save(costBudgetYear);
	}

	public void deleteCostBudgetYear(String id) {
		costBudgetYearDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostBudgetYear, String> getDao() {
		return costBudgetYearDao;
	}

	public void saveCostBudgetYears(List<CostBudgetYear> byears) {
		for(CostBudgetYear entity:byears){
			costBudgetYearDao.save(entity);
		}
		
	}
	
	/**
	 * 判断是否存在相应年份，项目的年计划
	 * @param values
	 * @return
	 */
	public boolean hasCreatedYearBudget(Map<String,Object> values){
		boolean flag = false;
		StringBuffer hql = new StringBuffer()
		.append("  from CostBudgetYear y ")
		.append(" where y.budgetYear = :budgetYear")
		.append("   and y.costProjectSection.costProjectSectionId = :costProjectSectionId");
		List list = costBudgetYearDao.find(hql.toString(), values);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 搜索总条数
	 * @param map
	 * @param hql
	 * @return
	 */
	public Long countTotal(Map<String, Object> map,StringBuffer hql) {
		
		Long total=0L;
		total=costBudgetYearDao.countHqlResult(hql.toString(), map);
		return total;
	}
	
}

