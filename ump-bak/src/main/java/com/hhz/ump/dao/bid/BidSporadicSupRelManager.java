package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidSporadicSupRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidSporadicSupRelManager extends BaseService<BidSporadicSupRel, String> {
	@Autowired
	private BidSporadicSupRelDao bidSporadicSupRelDao;

	public void saveBidSporadicSupRel(BidSporadicSupRel bidSporadicSupRel) {
		PowerUtils.setEmptyStr2Null(bidSporadicSupRel);
		bidSporadicSupRelDao.save(bidSporadicSupRel);
	}

	public void deleteBidSporadicSupRel(String id) {
		bidSporadicSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidSporadicSupRel, String> getDao() {
		return bidSporadicSupRelDao;
	}
	
}

