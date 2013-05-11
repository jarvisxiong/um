package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFactStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisFactStoreManager extends BaseService<BisFactStore, String> {
	@Autowired
	private BisFactStoreDao bisFactStoreDao;

	public void saveBisFactStore(BisFactStore bisFactStore) {
		PowerUtils.setEmptyStr2Null(bisFactStore);
		bisFactStoreDao.save(bisFactStore);
	}

	public void deleteBisFactStore(String id) {
		bisFactStoreDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFactStore, String> getDao() {
		return bisFactStoreDao;
	}
	
}

