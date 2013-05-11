package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidMeasureSupRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidMeasureSupRelManager extends BaseService<BidMeasureSupRel, String> {
	@Autowired
	private BidMeasureSupRelDao bidMeasureSupRelDao;

	public void saveBidMeasureSupRel(BidMeasureSupRel bidMeasureSupRel) {
		PowerUtils.setEmptyStr2Null(bidMeasureSupRel);
		bidMeasureSupRelDao.save(bidMeasureSupRel);
	}

	public void deleteBidMeasureSupRel(String id) {
		bidMeasureSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidMeasureSupRel, String> getDao() {
		return bidMeasureSupRelDao;
	}
	
}

