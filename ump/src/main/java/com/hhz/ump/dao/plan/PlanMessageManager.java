package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanMessage;

@Service
@Transactional
public class PlanMessageManager extends BaseService<PlanMessage, String> {
	@Autowired
	private PlanMessageDao planMessageDao;

	public void savePlanMessage(PlanMessage planMessage) {
		PowerUtils.setEmptyStr2Null(planMessage);
		planMessageDao.save(planMessage);
	}

	public void deletePlanMessage(String id) {
		planMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanMessage, String> getDao() {
		return planMessageDao;
	}
	
}

