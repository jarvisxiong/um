package com.hhz.ump.dao.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.bid.BidSupAttachRel;
import com.hhz.ump.web.bid.AppAttachFileVo;
import com.hhz.ump.web.bid.BidSupVo;

@Service
@Transactional
public class BidSupAttachRelManager extends BaseService<BidSupAttachRel, String> {
	@Autowired
	private BidSupAttachRelDao bidSupAttachRelDao;

	public void saveBidSupAttachRel(BidSupAttachRel bidSupAttachRel) {
		PowerUtils.setEmptyStr2Null(bidSupAttachRel);
		bidSupAttachRelDao.save(bidSupAttachRel);
	}

	public void deleteBidSupAttachRel(String id) {
		bidSupAttachRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidSupAttachRel, String> getDao() {
		return bidSupAttachRelDao;
	}
	
	
	public List findSupAttachList(BidSupVo bidSupVo){
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("bidSupId", bidSupVo.getBidSupId());
		StringBuffer hql=new StringBuffer()
		.append(" from BidSupAttachRel t where t.bidSup.bidSupId = :bidSupId order by t.batchNo asc");
		List rs=bidSupAttachRelDao.find(hql.toString(), values);
		return rs;	
	}
	
	/**
	 * 产讯轮次列表
	 * @param listBidSupVo
	 * @return
	 */
	public List findSupAttachList(List<BidSupVo> listBidSupVo){
		List<BidSupVo> rs=new ArrayList<BidSupVo>();
		if(listBidSupVo!=null&&listBidSupVo.size()>0){
			for(BidSupVo vo:listBidSupVo){
				List<BidSupAttachRel> bussinessBids=findSupAttachList(vo);
				vo.setBussinessBids(bussinessBids);
				rs.add(vo);
			}
		}
		return rs;
	}
	
	/**
	 * 生成轮次信息
	 * @param bidLedger
	 */
	public void generateBatch(BidLedger bidLedger){
		BidSupAttachRel bidSupAttachRel=null;
		if(bidLedger.getBidSups()!=null&&bidLedger.getBidSups().size()>0){
			for(BidSup sup:bidLedger.getBidSups()){
				bidSupAttachRel=new BidSupAttachRel();
				bidSupAttachRel.setBidSup(sup);
				bidSupAttachRel.setBatchNo((String.valueOf(bidLedger.getBatchNo().intValue())));
				bidSupAttachRel.setAttaBizFlg("0");//1-是0-否(默认)
				bidSupAttachRel.setAttaTechFlg("0");//1-是0-否(默认)
				bidSupAttachRelDao.save(bidSupAttachRel);
			}
		}
	}
	
	
	public Map <String,List> findAttachList(String bidLedgerId){
		//key:supID-标类型-轮次
		Map <String,List> rs=new HashMap<String,List>();
		StringBuffer sql=new StringBuffer()
		.append("select t.bid_sup_attach_rel_id as atsid,t.batch_no batchNo,t.bid_sup_id supid ,f.app_attach_file_id appattachId,")
		.append(" f.biz_module_cd module,f.real_file_name realName,f.biz_field_name fieldName,f.file_name fname")
		.append(" from app_attach_file f join ")
		.append(" (select * from ( ")
		.append(" select ar.bid_sup_attach_rel_id,ar.batch_no,ar.bid_sup_id from bid_ledger bl , bid_sup bs,bid_sup_attach_rel ar ")
		.append(" where bs.bid_ledger_id=bl.bid_ledger_id ")
		.append("  and ar.bid_sup_id=bs.bid_sup_id ")
		.append("  and bl.bid_ledger_id=:bidLedgerId )) t ")
		.append(" on f.biz_entity_id=t.bid_sup_attach_rel_id ");
		
		Map <String,Object>values = new HashMap<String,Object>();
		values.put("bidLedgerId", bidLedgerId);
		
		List<Object[]> l = bidSupAttachRelDao.findBySql(sql.toString(), values);
		if(l!=null){
			AppAttachFile file=null;
			String key="";
			List<AppAttachFileVo> vos=null;
			for(int i=0;i<l.size();i++){
				Object[] o=l.get(i);
				key = (String)o[2]
				                +"-"+(String)o[6]
				                               +"-"+(Character)o[1];
				if(rs.get(key)==null){
					vos=new ArrayList<AppAttachFileVo>();
				}else{
					vos=rs.get(key);
				}
				//构建附件列表
				makeBidSupAttachRelVo(o,vos);
				rs.put(key,vos);
			}
		}
		return rs;
	}

	/**
	 * 构建附件列表
	 * @param o
	 * @param vo
	 */
	private void makeBidSupAttachRelVo(Object[] o, List<AppAttachFileVo> vos) {
		AppAttachFileVo attachVo=new AppAttachFileVo();
		attachVo.setBizFieldName((String)o[6]);
		attachVo.setBizModuleCd((String)o[4]);
		attachVo.setFieldName((String)o[6]);
		attachVo.setFileName((String)o[5]);
		attachVo.setAppAttachFileId((String)o[3]);
		attachVo.setRealFileName((String)o[5]);
		attachVo.setFileName((String)o[7]);
		
		vos.add(attachVo);
	}
	
}

