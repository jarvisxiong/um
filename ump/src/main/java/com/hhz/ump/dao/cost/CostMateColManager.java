package com.hhz.ump.dao.cost;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostMateCol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostMateColManager extends BaseService<CostMateCol, String> {
	@Autowired
	private CostMateColDao costMateColDao;

	public void saveCostMateCol(CostMateCol costMateCol) {
		PowerUtils.setEmptyStr2Null(costMateCol);
		costMateColDao.save(costMateCol);
	}

	public void deleteCostMateCol(String id) {
		costMateColDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostMateCol, String> getDao() {
		return costMateColDao;
	}
	
}

