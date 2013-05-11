package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanExecDetailMess;

@Service
@Transactional
public class PlanExecDetailMessManager extends BaseService<PlanExecDetailMess, String> {
	@Autowired
	private PlanExecDetailMessDao planExecDetailMessDao;

	public void savePlanExecDetailMess(PlanExecDetailMess planExecDetailMess) {
		PowerUtils.setEmptyStr2Null(planExecDetailMess);
		planExecDetailMessDao.save(planExecDetailMess);
	}

	public void deletePlanExecDetailMess(String id) {
		planExecDetailMessDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanExecDetailMess, String> getDao() {
		return planExecDetailMessDao;
	}
	
}

