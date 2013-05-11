package com.hhz.ump.dao.biz;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.biz.BizHtlPurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BizHtlPurchaseManager extends BaseService<BizHtlPurchase, String> {
	@Autowired
	private BizHtlPurchaseDao bizHtlPurchaseDao;

	public void saveBizHtlPurchase(BizHtlPurchase bizHtlPurchase) {
		PowerUtils.setEmptyStr2Null(bizHtlPurchase);
		bizHtlPurchaseDao.save(bizHtlPurchase);
	}

	public void deleteBizHtlPurchase(String id) {
		bizHtlPurchaseDao.delete(id);
	}
	
	@Override
	public HibernateDao<BizHtlPurchase, String> getDao() {
		return bizHtlPurchaseDao;
	}
	
}

