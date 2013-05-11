package com.hhz.ump.dao.bid;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidSupOtherRel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BidSupOtherRelManager extends BaseService<BidSupOtherRel, String> {
	@Autowired
	private BidSupOtherRelDao bidSupOtherRelDao;

	public void saveBidSupOtherRel(BidSupOtherRel bidSupOtherRel) {
		PowerUtils.setEmptyStr2Null(bidSupOtherRel);
		bidSupOtherRelDao.save(bidSupOtherRel);
	}

	public void deleteBidSupOtherRel(String id) {
		bidSupOtherRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidSupOtherRel, String> getDao() {
		return bidSupOtherRelDao;
	}
	
}

