package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWorkCenterMessage;

@Service
@Transactional
public class PlanWorkCenterMessageManager extends BaseService<PlanWorkCenterMessage, String> {
	@Autowired
	private PlanWorkCenterMessageDao planWorkCenterMessageDao;

	public void savePlanWorkCenterMessage(PlanWorkCenterMessage planWorkCenterMessage) {
		PowerUtils.setEmptyStr2Null(planWorkCenterMessage);
		planWorkCenterMessageDao.save(planWorkCenterMessage);
	}

	public void deletePlanWorkCenterMessage(String id) {
		planWorkCenterMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWorkCenterMessage, String> getDao() {
		return planWorkCenterMessageDao;
	}
	
}

