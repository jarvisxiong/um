package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCtrlMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostCtrlMessageManager extends BaseService<CostCtrlMessage, String> {
	@Autowired
	private CostCtrlMessageDao costCtrlMessageDao;

	public void saveCostCtrlMessage(CostCtrlMessage costCtrlMessage) {
		PowerUtils.setEmptyStr2Null(costCtrlMessage);
		costCtrlMessageDao.save(costCtrlMessage);
	}

	public void deleteCostCtrlMessage(String id) {
		costCtrlMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCtrlMessage, String> getDao() {
		return costCtrlMessageDao;
	}
	
}

