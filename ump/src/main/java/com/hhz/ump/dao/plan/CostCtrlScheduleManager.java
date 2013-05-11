package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCtrlSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostCtrlScheduleManager extends BaseService<CostCtrlSchedule, String> {
	@Autowired
	private CostCtrlScheduleDao costCtrlScheduleDao;

	public void saveCostCtrlSchedule(CostCtrlSchedule costCtrlSchedule) {
		PowerUtils.setEmptyStr2Null(costCtrlSchedule);
		costCtrlScheduleDao.save(costCtrlSchedule);
	}

	public void deleteCostCtrlSchedule(String id) {
		costCtrlScheduleDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCtrlSchedule, String> getDao() {
		return costCtrlScheduleDao;
	}
	
}

