package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWork2Weight;

@Service
@Transactional
public class PlanWork2WeightManager extends BaseService<PlanWork2Weight, String> {
	@Autowired
	private PlanWork2WeightDao planWork2WeightDao;

	public void savePlanWork2Weight(PlanWork2Weight planWork2Weight) {
		PowerUtils.setEmptyStr2Null(planWork2Weight);
		planWork2WeightDao.save(planWork2Weight);
	}

	public void deletePlanWork2Weight(String id) {
		planWork2WeightDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWork2Weight, String> getDao() {
		return planWork2WeightDao;
	}
	
}

