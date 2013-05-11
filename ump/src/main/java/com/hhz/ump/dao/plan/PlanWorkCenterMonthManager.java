package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWorkCenterMonth;

@Service
@Transactional
public class PlanWorkCenterMonthManager extends BaseService<PlanWorkCenterMonth, String> {
	@Autowired
	private PlanWorkCenterMonthDao planWorkCenterMonthDao;

	public void savePlanWorkCenterMonth(PlanWorkCenterMonth planWorkCenterMonth) {
		PowerUtils.setEmptyStr2Null(planWorkCenterMonth);
		planWorkCenterMonthDao.save(planWorkCenterMonth);
	}

	public void deletePlanWorkCenterMonth(String id) {
		planWorkCenterMonthDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWorkCenterMonth, String> getDao() {
		return planWorkCenterMonthDao;
	}
	
}

