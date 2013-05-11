package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCheckMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostCheckMessageManager extends BaseService<CostCheckMessage, String> {
	@Autowired
	private CostCheckMessageDao costCheckMessageDao;

	public void saveCostCheckMessage(CostCheckMessage costCheckMessage) {
		PowerUtils.setEmptyStr2Null(costCheckMessage);
		costCheckMessageDao.save(costCheckMessage);
	}

	public void deleteCostCheckMessage(String id) {
		costCheckMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCheckMessage, String> getDao() {
		return costCheckMessageDao;
	}
	
}

