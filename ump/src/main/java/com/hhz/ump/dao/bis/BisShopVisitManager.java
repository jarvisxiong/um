package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisShopVisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisShopVisitManager extends BaseService<BisShopVisit, String> {
	@Autowired
	private BisShopVisitDao bisShopVisitDao;

	public void saveBisShopVisit(BisShopVisit bisShopVisit) {
		PowerUtils.setEmptyStr2Null(bisShopVisit);
		bisShopVisitDao.save(bisShopVisit);
	}

	public void deleteBisShopVisit(String id) {
		bisShopVisitDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopVisit, String> getDao() {
		return bisShopVisitDao;
	}
	
}

