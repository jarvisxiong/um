package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisCollectionRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisCollectionRateManager extends BaseService<BisCollectionRate, String> {
	@Autowired
	private BisCollectionRateDao bisCollectionRateDao;

	public void saveBisCollectionRate(BisCollectionRate bisCollectionRate) {
		PowerUtils.setEmptyStr2Null(bisCollectionRate);
		bisCollectionRateDao.save(bisCollectionRate);
	}

	public void deleteBisCollectionRate(String id) {
		bisCollectionRateDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisCollectionRate, String> getDao() {
		return bisCollectionRateDao;
	}
	
}

