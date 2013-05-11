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
import com.hhz.ump.entity.bid.BidDivisionSupRel;
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidSup;

@Service
@Transactional
public class BidDivisionSupRelManager extends BaseService<BidDivisionSupRel, String> {
	@Autowired
	private BidDivisionSupRelDao bidDivisionSupRelDao;

	public void saveBidDivisionSupRel(BidDivisionSupRel bidDivisionSupRel) {
		PowerUtils.setEmptyStr2Null(bidDivisionSupRel);
		bidDivisionSupRelDao.save(bidDivisionSupRel);
	}

	public void deleteBidDivisionSupRel(String id) {
		bidDivisionSupRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidDivisionSupRel, String> getDao() {
		return bidDivisionSupRelDao;
	}
	
	/**
	 * 
	 * 根据供应商及投标轮次搜索定制的分部分项总值
	 */
	public BigDecimal fetchBidDivisitonCustTotal(BidSup sup,String batNo){
			//搜索条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bidSupId", sup.getBidSupId());
			map.put("batchNo", batNo);
			
			//搜索语句
			StringBuffer sbSql=new StringBuffer()
			.append("select  case when sum(bdc.total_amt) is null then 0 else sum(bdc.total_amt) end as total_amt")
			.append(",max(bdc.bid_divisiton_cust_id) from bid_divisiton_cust bdc ")
			.append(" where bdc.bid_sup_id = :bidSupId and bdc.batch_no = :batchNo");
			
			//执行搜索
			List<Object[]> rs =this.findBySql(sbSql.toString(), map);
			//如果结果不为空,则返回total
			if(rs!=null&&rs.size()>0){
				Object[] o = rs.get(0);
				return (BigDecimal)o[0];
			} else
				//否则返回0
				return BigDecimal.ZERO;	
		}
	
	/**
	 * 
	 * 如果是末轮，则解析相应供应商的末轮次数
	 */
	public Map<String,String> parseBidSupLatestBatchNo(List<BidSup> bidSups,String batchNo){
		Map<String,String> bidSupBatchNos = new HashMap<String,String>();
		//搜索语句
		StringBuffer sbSql;
		//搜索结果集
		List<Object[]> rs ;
		//末轮分析,构建供应商轮次
		//如果是末轮,则获取供应商的最后轮次		
		if("-1".equals(batchNo)){			
			Map<String, Object> map =null;
			for(BidSup bidSup:bidSups){
				//如果不是标底公司
				if(!BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())){
					map= new HashMap<String, Object>();
					map.put("bidSupId", bidSup.getBidSupId());
					//根据分部分项获取最后一轮次
					 sbSql=new StringBuffer()
					.append("select max(bdsr.batch_no),max(bdsr.bid_sup_id) from bid_division_sup_rel bdsr")
					.append(" where bdsr.bid_sup_id = :bidSupId");
					//执行搜索
					rs = this.findBySql(sbSql.toString(), map);
					//获取供应商的最后投标轮次
					if(rs != null && rs.size()>0){
						Object[] o = rs.get(0);
						//如果没有,则直接给-1轮次
						if(o[0]==null){
							bidSupBatchNos.put(bidSup.getBidSupId(),"-1");
						}else{
							bidSupBatchNos.put(bidSup.getBidSupId(),((BigDecimal)o[0]).toString());
						}
						
					}
				}
			}
		}else{
			for(BidSup bidSup:bidSups){
				bidSupBatchNos.put(bidSup.getBidSupId(),batchNo);
			}
		}
		return bidSupBatchNos;
	}
	
	/**
	 * 搜索为导入的投标数据
	 *
	 */
	public  List<BidDivisiton>  findUnimportedData(Map<String, Object> map){		
		
		//搜索SQL
		StringBuffer hql=new StringBuffer();		
		hql.append(" from BidDivisiton bd")
		.append(" where bd.bidProject.bidProjectId = :projectId")
		.append(" and bd.batchNo = :batchno")
		.append(" and not exists ( ")
		.append(" select t.itemNo from BidDivisionSupRel  t ")
		.append(" where t.bidProject.bidProjectId =  :projectId and t.bidSup.bidSupId = :bidSupId and t.batchNo = :batchno and t.itemNo=bd.itemNo ")
		.append(" )");
		//执行搜索
		List<BidDivisiton> rs = bidDivisionSupRelDao.find(hql.toString(), map);
		return rs;
	}
	
	/**
	 * 
	 * 计算为导入的投标数据的条数
	 */
	public Long  countUnimportedData(Map<String, Object> map){		
		
		//搜索SQL
		StringBuffer hql=new StringBuffer();		
		hql.append(" from BidDivisiton bd")
		.append(" where bd.bidProject.bidProjectId = :projectId")
		.append(" and bd.batchNo = :batchno")
		.append(" and   not exists ( ")
		.append(" select t.bidDivisionSupRelId from BidDivisionSupRel  t ")
		.append(" where t.bidProject.bidProjectId =  :projectId and t.bidSup.bidSupId = :bidSupId and t.batchNo = :batchno and t.itemNo=bd.itemNo ")
		.append(" )");
		//执行搜索
		Long cnt = bidDivisionSupRelDao.countHqlResult(hql.toString(), map);
		return cnt;
	}
}



