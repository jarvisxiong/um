package com.hhz.ump.dao.cost;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostContPlanDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostContPlanDetailManager extends BaseService<CostContPlanDetail, String> {
	@Autowired
	private CostContPlanDetailDao costContPlanDetailDao;

	public void saveCostContPlanDetail(CostContPlanDetail costContPlanDetail) {
		PowerUtils.setEmptyStr2Null(costContPlanDetail);
		costContPlanDetailDao.save(costContPlanDetail);
	}

	public void deleteCostContPlanDetail(String id) {
		costContPlanDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlanDetail, String> getDao() {
		return costContPlanDetailDao;
	}
	
}

