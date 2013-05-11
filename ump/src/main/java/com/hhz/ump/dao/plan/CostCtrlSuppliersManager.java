package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCtrlSuppliers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class CostCtrlSuppliersManager extends BaseService<CostCtrlSuppliers, String> {
	@Autowired
	private CostCtrlSuppliersDao costCtrlSuppliersDao;

	public void saveCostCtrlSuppliers(CostCtrlSuppliers costCtrlSuppliers) {
		PowerUtils.setEmptyStr2Null(costCtrlSuppliers);
		costCtrlSuppliersDao.save(costCtrlSuppliers);
	}

	public void deleteCostCtrlSuppliers(String id) {
		costCtrlSuppliersDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCtrlSuppliers, String> getDao() {
		return costCtrlSuppliersDao;
	}
	
}

