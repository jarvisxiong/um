package com.hhz.ump.dao.bid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidItemSupRel;

@Service
@Transactional
public class BidItemSupRelManager extends BaseService<BidItemSupRel, String> {
	@Autowired
	private BidItemSupRelDao bidItemSupRelDao;

	public void saveBidItemSupRel(BidItemSupRel bidItemSupRel) {
		PowerUtils.setEmptyStr2Null(bidItemSupRel);
		bidItemSupRelDao.save(bidItemSupRel);
	}
	
	public void batchSaveBidItemSupRel(List<BidItemSupRel> supRelList) {
		if(supRelList!=null&&supRelList.size()>0){
			for(BidItemSupRel supRel:supRelList){
				saveBidItemSupRel(supRel);
			}
		}
	}

	public void deleteBidItemSupRel(String id) {
		bidItemSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidItemSupRel, String> getDao() {
		return bidItemSupRelDao;
	}
	
}

