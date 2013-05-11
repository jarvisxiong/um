package com.hhz.ump.dao.bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFactReduce;

@Service
@Transactional
public class BisFactReduceManager extends BaseService<BisFactReduce, String> {
	@Autowired
	private BisFactReduceDao bisFactReduceDao;

	public void saveBisFactReduce(BisFactReduce bisFactReduce) {
		PowerUtils.setEmptyStr2Null(bisFactReduce);
		bisFactReduceDao.save(bisFactReduce);
	}

	public void deleteBisFactReduce(String id) {
		bisFactReduceDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFactReduce, String> getDao() {
		return bisFactReduceDao;
	}
	

	
}

