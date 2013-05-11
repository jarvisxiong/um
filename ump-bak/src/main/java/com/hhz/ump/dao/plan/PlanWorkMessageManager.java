package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWorkMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlanWorkMessageManager extends BaseService<PlanWorkMessage, String> {
	@Autowired
	private PlanWorkMessageDao planWorkMessageDao;

	public void savePlanWorkMessage(PlanWorkMessage planWorkMessage) {
		PowerUtils.setEmptyStr2Null(planWorkMessage);
		planWorkMessageDao.save(planWorkMessage);
	}

	public void deletePlanWorkMessage(String id) {
		planWorkMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWorkMessage, String> getDao() {
		return planWorkMessageDao;
	}
	
}

