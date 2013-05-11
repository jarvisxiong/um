package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWork2Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlanWork2MonthManager extends BaseService<PlanWork2Month, String> {
	@Autowired
	private PlanWork2MonthDao planWork2MonthDao;

	public void savePlanWork2Month(PlanWork2Month planWork2Month) {
		PowerUtils.setEmptyStr2Null(planWork2Month);
		planWork2MonthDao.save(planWork2Month);
	}

	public void deletePlanWork2Month(String id) {
		planWork2MonthDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWork2Month, String> getDao() {
		return planWork2MonthDao;
	}
	
}

