package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWork2Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlanWork2MessageManager extends BaseService<PlanWork2Message, String> {
	@Autowired
	private PlanWork2MessageDao planWork2MessageDao;

	public void savePlanWork2Message(PlanWork2Message planWork2Message) {
		PowerUtils.setEmptyStr2Null(planWork2Message);
		planWork2MessageDao.save(planWork2Message);
	}

	public void deletePlanWork2Message(String id) {
		planWork2MessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWork2Message, String> getDao() {
		return planWork2MessageDao;
	}
	
}

