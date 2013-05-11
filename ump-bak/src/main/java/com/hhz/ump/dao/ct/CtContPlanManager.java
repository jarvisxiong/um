package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtContPlan;

@Service
@Transactional
public class CtContPlanManager extends BaseService<CtContPlan, String> {
	private CtContPlan entity;
	@Autowired
	private CtContPlanDao ctContPlanDao;

	public void saveCtContPlan(CtContPlan ctContPlan) {
		PowerUtils.setEmptyStr2Null(ctContPlan);
		ctContPlanDao.save(ctContPlan);
	}

	public void deleteCtContPlan(String id) {
		ctContPlanDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtContPlan, String> getDao() {
		return ctContPlanDao;
	}
}

