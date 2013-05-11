package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidOtherItemRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidOtherItemRelManager extends BaseService<BidOtherItemRel, String> {
	@Autowired
	private BidOtherItemRelDao bidOtherItemRelDao;

	public void saveBidOtherItemRel(BidOtherItemRel bidOtherItemRel) {
		PowerUtils.setEmptyStr2Null(bidOtherItemRel);
		bidOtherItemRelDao.save(bidOtherItemRel);
	}

	public void deleteBidOtherItemRel(String id) {
		bidOtherItemRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidOtherItemRel, String> getDao() {
		return bidOtherItemRelDao;
	}
	
}

