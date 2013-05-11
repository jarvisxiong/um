package com.hhz.ump.dao.bid;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidDivisitonCust;

@Service
@Transactional
public class BidDivisitonCustManager extends BaseService<BidDivisitonCust, String> {
	@Autowired
	private BidDivisitonCustDao bidDivisitonCustDao;

	public void saveBidDivisitonCust(BidDivisitonCust bidDivisitonCust) {
		PowerUtils.setEmptyStr2Null(bidDivisitonCust);
		bidDivisitonCustDao.save(bidDivisitonCust);
	}

	public void deleteBidDivisitonCust(String id) {
		bidDivisitonCustDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidDivisitonCust, String> getDao() {
		return bidDivisitonCustDao;
	}
	
	public BigDecimal getTotalCust(String bidSupId,String bidProjectId,String batchno){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidSupId", bidSupId); 
		map.put("bidProjectId", bidProjectId); 
		map.put("batchNo", Long.parseLong(batchno)); 
		
		StringBuffer sb=new StringBuffer()
		.append("select case ")
		.append(" when sum(bdc.total_amt) is null then ")
		.append("  0  else   sum(bdc.total_amt)  end total,")
		.append("   max(bdc.bid_divisiton_cust_id) ")
		.append(" from bid_divisiton_cust bdc ")		
		.append(" where bdc.bid_sup_id = :bidSupId")		
		.append("   and bdc.bid_project_id = :bidProjectId")	
		.append("   and bdc.batch_no = :batchNo");
		List<Object[]> list=this.getDao().findBySql(sb.toString(), map);
		if(list!=null&&list.size()>0){
			Object [] o=list.get(0);
			return (BigDecimal)o[0];
		}
		return BigDecimal.ZERO;
	}
	
}

