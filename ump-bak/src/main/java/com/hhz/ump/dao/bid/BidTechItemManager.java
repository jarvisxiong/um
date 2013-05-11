package com.hhz.ump.dao.bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidTechItem;

@Service
@Transactional
public class BidTechItemManager extends BaseService<BidTechItem, String> {
	@Autowired
	private BidTechItemDao bidTechItemDao;

	public void saveBidTechItem(BidTechItem bidTechItem) {
		PowerUtils.setEmptyStr2Null(bidTechItem);
		bidTechItemDao.save(bidTechItem);
	}
	
	public void batchSaveBidTechItem(List<BidTechItem> itemList){
		if(itemList!=null&&itemList.size()>0){
			for(BidTechItem bidTechItem:itemList){
				saveBidTechItem(bidTechItem);
			}
		}
	}

	public void deleteBidTechItem(String id) {
		bidTechItemDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidTechItem, String> getDao() {
		return bidTechItemDao;
	}
	
	public List<BidTechItem> queryBidTechItem(String bidLedgerId){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer("select a from BidTechItem a where 1=1");
		if(StringUtils.isNotBlank(bidLedgerId)){
			hql.append(" and a.bidLedger.bidLedgerId=:bidLedgerId");
			param.put("bidLedgerId", bidLedgerId);
		}
		return bidTechItemDao.find(hql.toString(), param);
	}
	
}

