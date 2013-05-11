package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWork2Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlanWork2StatusManager extends BaseService<PlanWork2Status, String> {
	@Autowired
	private PlanWork2StatusDao planWork2StatusDao;

	public void savePlanWork2Status(PlanWork2Status planWork2Status) {
		PowerUtils.setEmptyStr2Null(planWork2Status);
		planWork2StatusDao.save(planWork2Status);
	}

	public void deletePlanWork2Status(String id) {
		planWork2StatusDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWork2Status, String> getDao() {
		return planWork2StatusDao;
	}
	
}

