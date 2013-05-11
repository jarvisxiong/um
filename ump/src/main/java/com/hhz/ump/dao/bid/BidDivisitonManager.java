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
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidProject;

@Service
@Transactional
public class BidDivisitonManager extends BaseService<BidDivisiton, String> {
	@Autowired
	private BidDivisitonDao bidDivisitonDao;

	public void saveBidDivisiton(BidDivisiton bidDivisiton) {
		PowerUtils.setEmptyStr2Null(bidDivisiton);
		bidDivisitonDao.save(bidDivisiton);
	}

	public void deleteBidDivisiton(String id) {
		bidDivisitonDao.delete(id);
	}
	
	
	public BidDivisiton getBidDivisitonByBidDivisitonId(String bidDivisitonId){
		Map <String,Object> map=new HashMap<String,Object>();		
		map.put("bidDivisitonId", bidDivisitonId);
		String hql=" from BidDivisiton bd where bd.bidDivisitonId = :bidDivisitonId";
		List <BidDivisiton>l=this.bidDivisitonDao.find(hql, map);
		if(l==null||l.size()<1)
			return null;
		else
			return	l.get(0);
	}
	
	@Override
	public HibernateDao<BidDivisiton, String> getDao() {
		return bidDivisitonDao;
	}

	/**
	 * 
	 * 搜索分布分项中是否已经导入相应工程的数据
	 */
	public boolean hasImportedBiaodiData(String bidProjectId,String batchNo){
		boolean flag=false;
		//搜索分布分项中是否已经导入相应工程的数据
		String hql=" from BidDivisiton bd where bd.bidProject.bidProjectId = :bidProjectId and bd.batchNo = :batchNo";
		Map <String,Object> map=new HashMap<String,Object>();		
			map.put("bidProjectId", bidProjectId);
			map.put("batchNo", Long.parseLong(batchNo));
		
		//如果结果存在则返回true	
		if(countHqlResult(hql, map)>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 根据工程搜索工程包含的国标大类
	 * @param bidProjectId
	 * @return
	 */
	public String getItemNos(String bidProjectId){
		Map <String,Object> map=new HashMap<String,Object>();
		String sql="select distinct(substr(item_no,1,4)) from Bid_Divisiton where bid_Project_Id = :bidProjectId";
		map.put("bidProjectId", bidProjectId);
		String itemNos= null;
		List<Object[]> listObj = getDao().findBySql(sql, map);
		if (listObj == null || listObj.size() == 0)
			return null;
		else {
			itemNos=listObj.toString().replaceAll(" ", "")
			.substring(1, listObj.toString().replaceAll(" ", "").length() - 1);
		}
			return itemNos;
	}
	/**
	 * 
	 * hasImportedBiaodiData:(根据投标搜索是否存在标底数据)
	 *  
	 * @param  @param bidLedgerId
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean hasImportedBiaodiData(String bidLedgerId){
		Long batchNo=Long.parseLong("0");
		if(hasImportedDataLedgerLevel(bidLedgerId,batchNo,"BidDivisiton")||	
			hasImportedDataLedgerLevel(bidLedgerId,batchNo,"BidMeasureSupRel")||	
			hasImportedDataLedgerLevel(bidLedgerId ,batchNo,"BidMeasureSupRel2")||	
			hasImportedDataLedgerLevel(bidLedgerId,batchNo,"BidOtherItemRel")||	
			hasImportedDataLedgerLevel(bidLedgerId ,batchNo,"BidSporadicSupRel")||	
			hasImportedDataLedgerLevel(bidLedgerId ,batchNo,"BidFeesSupRel")||	
			hasImportedDataLedgerLevel(bidLedgerId ,batchNo,"BidTaxsSupRel")||	
			hasImportedDataLedgerLevel(bidLedgerId ,batchNo,"BidSupOtherRel"))
			return true;
		else
			return false;
	}
	
	/**
	 * 标段级别
	 * supHasImportedData:(搜索是否已经导入过数据) 
	 *  
	 * @param  @param sup
	 * @param  @param project
	 * @param  @param batchNo
	 * @param  @param table
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean hasImportedDataLedgerLevel(String bidLedgerId, Long batchNo, String table) {
		boolean flag=false;
		StringBuffer hql=new StringBuffer();
		Map<String, Object> map=new HashMap<String, Object>();
		
		//如果是分部分项(标底)
		if("BidDivisiton".equals(table)){
			 map.put("bidLedgerId", bidLedgerId);
			 //this.getDao().countSqlResult("from  BidProject bp where bp.bidLedger.bidLedgerId = :bidLedgerId", map);
			 hql.append(" from BidDivisiton bd ")
			 	.append(" where bd.bidProject.bidProjectId in (select bp.bidProjectId from  BidProject bp where bp.bidLedger.bidLedgerId = :bidLedgerId)");
		}
		//如果是分部分项(供应商)
		else if("BidDivisionSupRel".equals(table)){
			 map.put("batchNo", batchNo);
			 map.put("bidLedgerId", bidLedgerId);
			 
			 hql.append(" from BidDivisionSupRel bdsr where bdsr.batchNo = :batchNo")
			 	.append(" and bdsr.bidDivisiton.bidDivisitonId in( ")
			 	.append(" select bd.bidDivisitonId from BidDivisiton bd where bd.bidProject.bidProjectId in (" +
			 			"select bp.bidProjectId from BidProject bp where bp.bidLedger.bidLedgerId = :bidLedgerId)")
			 	.append(" )");
			 			 
			 //如果是其他
		 }else if("BidSupOtherRel".equals(table)){
			 map.put("batchNo", batchNo);
			 map.put("bidLedgerId", bidLedgerId);
			 
			 hql.append(" from "+table+" dbs where dbs.batchNo = :batchNo")
			 .append(" and dbs.bidSup.bidSupId in (")
			 .append(" select bs.bidSupId from BidSup bs where bs.bidLedger.bidLedgerId = :bidLedgerId")
			 .append(")");
			 //除以上两种表
		 }else{
			 map.put("batchNo", batchNo);
			 map.put("bidLedgerId", bidLedgerId);
			 
			 hql.append(" from "+table+" dbs where dbs.batchNo = :batchNo")
			 .append(" and dbs.bidProject.bidProjectId in( ")
			 .append("select bp.bidProjectId from  BidProject bp where bp.bidLedger.bidLedgerId = :bidLedgerId")
			 .append(")");
			 
		 }
		if(this.getDao().countHqlResult(hql.toString(), map)>0){
			 flag=true; 
		 }
		 
		 return flag;		
		
	}
	
	/**
	 * 
	 * getTotalBySupAndProject:(根据供应商,工程,轮次进行搜索)  
	 *  map.put("bidSupId", value);
	 *	map.put("bidProjectId", value);
	 *	map.put("batchNo", value); 
	 *  
	 * @param  @param map
	 * @param  @return    设定文件  
	 * @return List    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List getTotalBySupAndProject(Map<String, Object> map){
		
		
		StringBuffer sbSql =new StringBuffer()
		  .append("select ")
		  .append(" /**分部分项**/")
		  .append(" (select nvl(sum(bdsr.total_amt),0) as fbfx from bid_division_sup_rel bdsr")
		  //.append(" join bid_divisiton bd on bd.bid_divisiton_id=bdsr.bid_divisiton_id ")
		  .append(" join bid_project bp on bp.bid_project_id=bdsr.bid_project_id")
		  .append(" where bdsr.bid_sup_id=:bidSupId and bp.bid_project_id=:bidProjectId and bdsr.batch_no=:batchNo) as fbxf, ")
		  .append(" /**措施1**/")
		  .append(" (select case when sum(bmsr.amt) is null then 0 else sum(bmsr.amt) end as  bmsr1t from bid_measure_sup_rel bmsr") 
		  .append(" where bmsr.bid_sup_id=:bidSupId and bmsr.bid_project_id=:bidProjectId  and bmsr.batch_no=:batchNo) as cuoshi1,")
		  .append(" /**措施2**/")
		  .append(" (select case when sum(bmsr2.total_amt) is null then 0 else sum(bmsr2.total_amt) end as bmsr2t from bid_measure_sup_rel2 bmsr2 ") 
		  .append(" where   bmsr2.bid_sup_id=:bidSupId and  bmsr2.bid_project_id=:bidProjectId  and  bmsr2.batch_no=:batchNo) as cuoshi2,")
		  .append(" /**其他项目**/")
		  .append(" (select  case when sum(boir.amt)  is null then 0 else sum(boir.amt) end as qita  from bid_other_item_rel boir ")
		  .append(" where boir.bid_sup_id=:bidSupId and  boir.bid_project_id=:bidProjectId  and  boir.batch_no=:batchNo) as qitaxm,")
		  .append(" /**零星工程**/")
		  .append(" (select case when sum(bssr.total_amt) is null then 0 else  sum(bssr.total_amt) end as lxgc  from bid_sporadic_sup_rel bssr")
		  .append(" where bssr.bid_sup_id=:bidSupId and  bssr.bid_project_id=:bidProjectId  and  bssr.batch_no=:batchNo) as lxgc,")
		  .append(" /**规费项目**/")
		  .append(" (select case when sum(bfsr.amt) is null then 0 else  sum(bfsr.amt) end as gfxm from bid_fees_sup_rel bfsr ")
		  .append(" where bfsr.bid_sup_id=:bidSupId and  bfsr.bid_project_id=:bidProjectId and  bfsr.batch_no=:batchNo) as gfxm,")
		  .append(" /**税金项目**/")
		  .append(" (select case when sum(btsr.amt) is null then 0 else  sum(btsr.amt) end as sjxm from bid_taxs_sup_rel btsr" )
		  .append(" where  btsr.bid_sup_id=:bidSupId and  btsr.bid_project_id=:bidProjectId  and  btsr.batch_no=:batchNo) as sjxm,")
		  .append(" /**标段其他费用**/")
		  .append(" (select case when sum(bsor.fee_amt) is null then 0 else sum(bsor.fee_amt) end as bdqtfy from bid_sup_other_rel bsor ")
		  .append(" where bsor.bid_sup_id=:bidSupId and bsor.batch_no=:batchNo) as bdqtfy")
		  .append(" from ")
		  .append(" dual");
		
		return this.getDao().findBySql(sbSql.toString(), map);
	}
	
	/**
	 * 
	 * getBidDivisionTotal:(按工程统计) 
	 *  
	 * @param  @param bidProjectId
	 * @param  @return    设定文件  
	 * @return BigDecimal    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public BigDecimal getBidDivisionTotal(String bidProjectId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidProjectId", bidProjectId);
		
		StringBuffer sbSql =new StringBuffer();
		sbSql.append("select case when sum(bd.total_amt) is null then 0 else sum(bd.total_amt) end as total,")
			 .append(" max(bd.bid_divisiton_id) from bid_divisiton bd where bd.bid_project_id=:bidProjectId");
		List <Object []>rs=this.getDao().findBySql(sbSql.toString(), map);
		if(rs!=null&& rs.size()>0){
			Object[] o=rs.get(0);
			return (BigDecimal)o[0];
		} else
			return BigDecimal.ZERO;
		
		
	}
	
	public BigDecimal getBidDivisionTotalByBidLedger(String bidLedgerId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidLedgerId", bidLedgerId);
		
		StringBuffer sbSql =new StringBuffer();
		sbSql.append("select case when sum(bd.total_amt) is null then 0 else sum(bd.total_amt) end as total,")
			 .append(" max(bd.bid_divisiton_id) from bid_divisiton bd join bid_project bp on bp.bid_project_id=bd.bid_project_id")
			 .append(" where bp.bid_ledger_id = :bidLedgerId");
		List <Object []>rs=this.getDao().findBySql(sbSql.toString(), map);
		if(rs!=null&& rs.size()>0){
			Object[] o=rs.get(0);
			return (BigDecimal)o[0];
		} else
			return BigDecimal.ZERO;
		
		
	}
	
	/**
	 * 
	 *根据工程,轮次搜索所有清单项
	 */
	public List<BidDivisiton> getBidDivisitons(BidProject bidProject, String batchNo) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidProjectId", bidProject.getBidProjectId());
		map.put("batchNo", Long.parseLong(batchNo));
		  StringBuffer sbStr=new StringBuffer()
		  .append("from BidDivisiton bds where bds.bidProject.bidProjectId = :bidProjectId and bds.batchNo = :batchNo order by bds.rowNo asc");
		 return  bidDivisitonDao.find(sbStr.toString(), map) ;
		
	}
	
	/**
	 * 
	 *根据工程,轮次搜索所有清单项
	 */
	public Long countBidDivisitons(String bidProjectId, String batchNo) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidProjectId", bidProjectId);
		map.put("batchNo", Long.parseLong(batchNo));
		  StringBuffer sbStr=new StringBuffer()
		  .append("from BidDivisiton bds where bds.bidProject.bidProjectId = :bidProjectId and bds.batchNo = :batchNo");
		 return  bidDivisitonDao.countHqlResult(sbStr.toString(), map);
		
	}
}

