package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidFeesSupRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidFeesSupRelManager extends BaseService<BidFeesSupRel, String> {
	@Autowired
	private BidFeesSupRelDao bidFeesSupRelDao;

	public void saveBidFeesSupRel(BidFeesSupRel bidFeesSupRel) {
		PowerUtils.setEmptyStr2Null(bidFeesSupRel);
		bidFeesSupRelDao.save(bidFeesSupRel);
	}

	public void deleteBidFeesSupRel(String id) {
		bidFeesSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidFeesSupRel, String> getDao() {
		return bidFeesSupRelDao;
	}
	
}

