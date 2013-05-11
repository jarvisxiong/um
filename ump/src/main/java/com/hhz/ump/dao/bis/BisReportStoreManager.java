package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisReportStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisReportStoreManager extends BaseService<BisReportStore, String> {
	@Autowired
	private BisReportStoreDao bisReportStoreDao;

	public void saveBisReportStore(BisReportStore bisReportStore) {
		PowerUtils.setEmptyStr2Null(bisReportStore);
		bisReportStoreDao.save(bisReportStore);
	}

	public void deleteBisReportStore(String id) {
		bisReportStoreDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisReportStore, String> getDao() {
		return bisReportStoreDao;
	}
	
}

