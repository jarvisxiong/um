package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidCountryDivisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidCountryDivisitManager extends BaseService<BidCountryDivisit, String> {
	@Autowired
	private BidCountryDivisitDao bidCountryDivisitDao;

	public void saveBidCountryDivisit(BidCountryDivisit bidCountryDivisit) {
		PowerUtils.setEmptyStr2Null(bidCountryDivisit);
		bidCountryDivisitDao.save(bidCountryDivisit);
	}

	public void deleteBidCountryDivisit(String id) {
		bidCountryDivisitDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidCountryDivisit, String> getDao() {
		return bidCountryDivisitDao;
	}
	
}

