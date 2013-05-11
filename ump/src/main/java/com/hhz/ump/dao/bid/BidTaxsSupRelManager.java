package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidTaxsSupRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidTaxsSupRelManager extends BaseService<BidTaxsSupRel, String> {
	@Autowired
	private BidTaxsSupRelDao bidTaxsSupRelDao;

	public void saveBidTaxsSupRel(BidTaxsSupRel bidTaxsSupRel) {
		PowerUtils.setEmptyStr2Null(bidTaxsSupRel);
		bidTaxsSupRelDao.save(bidTaxsSupRel);
	}

	public void deleteBidTaxsSupRel(String id) {
		bidTaxsSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidTaxsSupRel, String> getDao() {
		return bidTaxsSupRelDao;
	}
	
}

