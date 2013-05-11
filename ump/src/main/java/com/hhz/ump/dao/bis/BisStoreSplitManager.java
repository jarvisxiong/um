package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisStoreSplit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisStoreSplitManager extends BaseService<BisStoreSplit, String> {
	@Autowired
	private BisStoreSplitDao bisStoreSplitDao;

	public void saveBisStoreSplit(BisStoreSplit bisStoreSplit) {
		PowerUtils.setEmptyStr2Null(bisStoreSplit);
		bisStoreSplitDao.save(bisStoreSplit);
	}

	public void deleteBisStoreSplit(String id) {
		bisStoreSplitDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisStoreSplit, String> getDao() {
		return bisStoreSplitDao;
	}
	
}

