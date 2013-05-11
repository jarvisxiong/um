package com.hhz.ump.dao.bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidOpenRecord;
import com.hhz.ump.entity.bid.BidOpenRecordSup;

@Service
@Transactional
public class BidOpenRecordManager extends BaseService<BidOpenRecord, String> {
	@Autowired
	private BidOpenRecordDao bidOpenRecordDao;
	@Autowired
	private BidOpenRecordSupDao bidOpenRecordSupDao;

	public void saveBidOpenRecord(BidOpenRecord bidOpenRecord) {
		PowerUtils.setEmptyStr2Null(bidOpenRecord);
		bidOpenRecordDao.save(bidOpenRecord);
		List<BidOpenRecordSup> boslist =  bidOpenRecord.getBidOpenRecordSups();
		for (BidOpenRecordSup bidOpenRecordSup : boslist) {
			bidOpenRecordSup.setBidOpenRecord(bidOpenRecord);
			bidOpenRecordSupDao.save(bidOpenRecordSup);
		}
	}

	public void deleteBidOpenRecord(String id) {
		bidOpenRecordDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidOpenRecord, String> getDao() {
		return bidOpenRecordDao;
	}
	
	public  BidOpenRecord getByBidLedgerIdAndBatchNo(String bidLedgerId,Long batchNo){
		String hql = "from BidOpenRecord t where t.bidLedger.bidLedgerId = :bidLedgerId and t.batchNo = :batchNo";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bidLedgerId", bidLedgerId);
		values.put("batchNo", batchNo);
		BidOpenRecord bidOpenRecord = bidOpenRecordDao.findUnique(hql,values);
		return bidOpenRecord;
	}
}

