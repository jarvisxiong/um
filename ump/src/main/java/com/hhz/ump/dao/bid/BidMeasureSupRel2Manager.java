package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidMeasureSupRel2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidMeasureSupRel2Manager extends BaseService<BidMeasureSupRel2, String> {
	@Autowired
	private BidMeasureSupRel2Dao bidMeasureSupRel2Dao;

	public void saveBidMeasureSupRel2(BidMeasureSupRel2 bidMeasureSupRel2) {
		PowerUtils.setEmptyStr2Null(bidMeasureSupRel2);
		bidMeasureSupRel2Dao.save(bidMeasureSupRel2);
	}

	public void deleteBidMeasureSupRel2(String id) {
		bidMeasureSupRel2Dao.delete(id);
	}
	
	@Override
	public HibernateDao<BidMeasureSupRel2, String> getDao() {
		return bidMeasureSupRel2Dao;
	}
	
}

