package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidOpenRecordSup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidOpenRecordSupManager extends BaseService<BidOpenRecordSup, String> {
	@Autowired
	private BidOpenRecordSupDao bidOpenRecordSupDao;

	public void saveBidOpenRecordSup(BidOpenRecordSup bidOpenRecordSup) {
		PowerUtils.setEmptyStr2Null(bidOpenRecordSup);
		bidOpenRecordSupDao.save(bidOpenRecordSup);
	}

	public void deleteBidOpenRecordSup(String id) {
		bidOpenRecordSupDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidOpenRecordSup, String> getDao() {
		return bidOpenRecordSupDao;
	}
	
}

