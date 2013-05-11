package com.hhz.ump.dao.cost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostContPlanStat;

@Service
@Transactional
public class CostContPlanStatManager extends BaseService<CostContPlanStat, String> {
	@Autowired
	private CostContPlanStatDao costContPlanStatDao;

	public void saveCostContPlanStat(CostContPlanStat costContPlanStat) {
		PowerUtils.setEmptyStr2Null(costContPlanStat);
		costContPlanStatDao.save(costContPlanStat);
	}

	public void deleteCostContPlanStat(String id) {
		costContPlanStatDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlanStat, String> getDao() {
		return costContPlanStatDao;
	}
	

}

