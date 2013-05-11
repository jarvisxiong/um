package com.hhz.ump.dao.cost;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostContPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostContPlanManager extends BaseService<CostContPlan, String> {
	@Autowired
	private CostContPlanDao costContPlanDao;

	public void saveCostContPlan(CostContPlan costContPlan) {
		PowerUtils.setEmptyStr2Null(costContPlan);
		costContPlanDao.save(costContPlan);
	}

	public void deleteCostContPlan(String id) {
		costContPlanDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlan, String> getDao() {
		return costContPlanDao;
	}
	
}

