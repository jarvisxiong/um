package com.hhz.ump.dao.bid;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidLedgAttaRel;
import com.hhz.ump.entity.bid.BidLedger;

@Service
@Transactional
public class BidLedgAttaRelManager extends BaseService<BidLedgAttaRel, String> {
	@Autowired
	private BidLedgAttaRelDao bidLedgAttaRelDao;
	@Autowired
	private BidLedgerDao bidLedgerDao;

	public void saveBidLedgAttaRel(BidLedgAttaRel bidLedgAttaRel) {
		PowerUtils.setEmptyStr2Null(bidLedgAttaRel);
		bidLedgAttaRelDao.save(bidLedgAttaRel);
	}

	public void deleteBidLedgAttaRel(String id) {
		bidLedgAttaRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidLedgAttaRel, String> getDao() {
		return bidLedgAttaRelDao;
	}
	
	/**
	 * 若未查到，则默认产生一条关联记录。
	 * @param bidLedgerId
	 * @param batchNo
	 * @return
	 */
	public BidLedgAttaRel getByBidLedgIdAndBatchNo(String bidLedgerId,Long batchNo){
		
		String hql = "from BidLedgAttaRel a where a.bidLedger.bidLedgerId=:bidLedgerId and a.batchNo=:batchNo";
	
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put("bidLedgerId", bidLedgerId);
		values.put("batchNo",BigDecimal.valueOf(batchNo));
		
		BidLedgAttaRel attaRel = bidLedgAttaRelDao.findUnique(hql, values);
		if(attaRel==null){
			attaRel = new BidLedgAttaRel();
			
			BidLedger bid = bidLedgerDao.get(bidLedgerId);
			
				attaRel.setBidLedger(bid);
				attaRel.setBatchNo(BigDecimal.valueOf(batchNo));
				saveBidLedgAttaRel(attaRel);
			
		}
		
		return attaRel;
	}
	
	
}

