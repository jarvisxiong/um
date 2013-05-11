package com.hhz.ump.dao.bid;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.pl.PlAppAttachFileManager;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidDivisionSupRel;
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidDivisitonCust;
import com.hhz.ump.entity.bid.BidFeesSupRel;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidMeasureSupRel;
import com.hhz.ump.entity.bid.BidMeasureSupRel2;
import com.hhz.ump.entity.bid.BidOtherItemRel;
import com.hhz.ump.entity.bid.BidProject;
import com.hhz.ump.entity.bid.BidSporadicSupRel;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.bid.BidSupOtherRel;
import com.hhz.ump.entity.bid.BidTaxsSupRel;
import com.hhz.ump.entity.bid.pl.PlAppAttachFile;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.res.bean.StrageInviteUnitBill;
import com.hhz.ump.web.res.bean.StrageInviteUnitProperty;

@Service
@Transactional
public class BidLedgerManager extends BaseService<BidLedger, String> {
	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(BidLedgerManager.class);
	@Autowired
	private BidLedgerDao bidLedgerDao;
	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidDivisionSupRelManager bidDivisionSupRelManager;
	@Autowired
	protected BidMeasureSupRelManager bidMeasureSupRelManager;
	@Autowired
	protected BidMeasureSupRel2Manager bidMeasureSupRel2Manager;
	@Autowired
	protected BidOtherItemRelManager bidOtherItemRelManager;
	@Autowired
	protected BidSporadicSupRelManager bidSporadicSupRelManager;
	@Autowired
	protected BidFeesSupRelManager bidFeesSupRelManager;
	@Autowired
	protected BidTaxsSupRelManager bidTaxsSupRelManager;
	@Autowired
	protected  BidSupOtherRelManager bidSupOtherRelManager;
	@Autowired
	protected  BidDivisitonCustManager bidDivisitonCustManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private PlAppAttachFileManager plAppAttachFileManager;
	
	//邀请类型:9-未知/1-明标/2-暗标
	public static final String VISIBLE_FLG_UNKNOWN = "1";
	public static final String VISIBLE_FLG_SHOW = "1";
	public static final String VISIBLE_FLG_HIDE = "2";
	
	//预算类型:1-选中/0-不选中
	public static final String BUDGET_FLG_YES = "1";
	public static final String BUDGET_FLG_NO = "0";
	
	//投标状态(0-导入 1-邀标、2-投标、3-评标、4-推荐中标、5-中标） 9-关闭
	public static final String BID_STATUS_IMPORT = "0";
	public static final String BID_STATUS_INVITE = "1";
	public static final String BID_STATUS_BIDDING  = "2";
	public static final String BID_STATUS_MARKING = "3";
	public static final String BID_STATUS_POP = "-1";
	public static final String BID_STATUS_WIN = "4";//2012-6-27 取消推荐中标状态 4 为中标
	public static final String BID_STATUS_CLOSE = "9";

	public static final String TRUE = "1";
	public static final String FALSE = "0";
	
	//战略，非战略
	public static final String SRC_TYPE_FZL = "0";
	public static final String SRC_TYPE_ZL = "1";

	public void saveBidLedger(BidLedger bidLedger) {
		PowerUtils.setEmptyStr2Null(bidLedger);
		bidLedgerDao.save(bidLedger);
	}

	public void deleteBidLedger(String id) {
		bidLedgerDao.delete(id);
	}

	@Override
	public HibernateDao<BidLedger, String> getDao() {
		return bidLedgerDao;
	}

	/**
	 * 
	 * saveImportTables:(批量删除和保存数据)
	 *  
	 * @param  @param bidDivisitonList
	 * @param  @param bidDivisionSupRelList
	 * @param  @param bidMeasureSupRelList
	 * @param  @param bidMeasureSupRel2List
	 * @param  @param bidOtherItemRelList
	 * @param  @param bidSporadicSupRelList
	 * @param  @param bidFeesSupRelList
	 * @param  @param bidTaxsSupRelList
	 * @param  @param bidSupOtherRelList
	 * @param  @param bidDivisitonCustList
	 * @param  @param bidSup
	 * @param  @param bidProject
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean saveImportTables(List<BidDivisiton> bidDivisitonList,
			List<BidDivisionSupRel> bidDivisionSupRelList,
			List<BidMeasureSupRel> bidMeasureSupRelList,
			List<BidMeasureSupRel2> bidMeasureSupRel2List,
			List<BidOtherItemRel> bidOtherItemRelList,
			List<BidSporadicSupRel> bidSporadicSupRelList,
			List<BidFeesSupRel> bidFeesSupRelList,			
			List<BidTaxsSupRel> bidTaxsSupRelList,
			List<BidSupOtherRel> bidSupOtherRelList,List<BidDivisitonCust>bidDivisitonCustList,BidSup bidSup,BidProject bidProject) {
		
		//先清除原有数据
		if(BidSupManager.SUP_PROVIDOR.equals(bidSup.getTypeCd())){
			 //获取标段
			BidLedger bidLedger = bidProject.getBidLedger();
			 //根据工程轮次号及供应商搜索 是否已经导入数据,且处于投标状态,则提示已经存在数据,是否清除数据重导入
			if(supHasImportedData(bidSup,bidProject,bidLedger.getBatchNo())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())){
				//批量删除
				batchDeleteData(bidSup, bidProject, bidProject.getBidLedger().getBatchNo());	
			}
		}//如果是标底公司
		else{
			//根据工程轮次号及供应商搜索 是否已经导入数据,且处于邀标状态,则提示已经存在数据,是否清除数据重导入
			//因为标底单位的轮次默认为0
			if(supHasImportedData(bidSup,bidProject,Long.parseLong("0"))
					&&BidLedgerManager.BID_STATUS_INVITE.equals(bidProject.getBidLedger().getBidStatusCd())){
				//批量删除
				batchDeleteData(bidSup, bidProject, Long.parseLong("0"));
			}
		}
		
		//批量导入,如果是标底公司、投标状态、且有新的数据则全部删除,再导入
		for(BidDivisiton bidDivisiton:bidDivisitonList){
			bidDivisitonManager.saveBidDivisiton(bidDivisiton);
		}
		for(BidDivisionSupRel bidDivisionSupRel:bidDivisionSupRelList){
			bidDivisionSupRelManager.saveBidDivisionSupRel(bidDivisionSupRel);
		}
		//措施1
		if(bidMeasureSupRelList.size()>0){
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidMeasureSupRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidMeasureSupRel bidMeasureSupRel:bidMeasureSupRelList){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidMeasureSupRel.setBatchNo(Long.parseLong("0"));
				}
				bidMeasureSupRelManager.saveBidMeasureSupRel(bidMeasureSupRel);
			}
		}
		
		//措施2
		if(bidMeasureSupRel2List.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidMeasureSupRel2(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidMeasureSupRel2 bidMeasureSupRel2:bidMeasureSupRel2List){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidMeasureSupRel2.setBatchNo(Long.parseLong("0"));
				}
				bidMeasureSupRel2Manager.saveBidMeasureSupRel2(bidMeasureSupRel2);
			}
		}
		//其他
		if(bidOtherItemRelList.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidOtherItemRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidOtherItemRel bidOtherItemRel:bidOtherItemRelList){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidOtherItemRel.setBatchNo(Long.parseLong("0"));
				}
				bidOtherItemRelManager.saveBidOtherItemRel(bidOtherItemRel);
			}
		}
		
		//零星
		if(bidSporadicSupRelList.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidSporadicSupRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidSporadicSupRel bidSporadicSupRel:bidSporadicSupRelList){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidSporadicSupRel.setBatchNo(Long.parseLong("0"));
				}
				bidSporadicSupRelManager.saveBidSporadicSupRel(bidSporadicSupRel);
			}
		}
		//费用
		if(bidFeesSupRelList.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidFeesSupRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidFeesSupRel bidFeesSupRel:bidFeesSupRelList){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidFeesSupRel.setBatchNo(Long.parseLong("0"));
				}
				bidFeesSupRelManager.saveBidFeesSupRel(bidFeesSupRel);
			}
		}
		
		//税金
		if(bidTaxsSupRelList.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidTaxsSupRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidTaxsSupRel bidTaxsSupRel:bidTaxsSupRelList){	
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
					bidTaxsSupRel.setBatchNo(Long.parseLong("0"));
				}
					bidTaxsSupRelManager.saveBidTaxsSupRel(bidTaxsSupRel);
				}			
		}
		//标段其他
		if(bidSupOtherRelList.size()>0) {
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())
					&&BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				this.deleteBidSupOtherRel(bidSup, bidProject, Long.parseLong("0"));
			}
			for(BidSupOtherRel bidSupOtherRel:bidSupOtherRelList){
				if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())){
					bidSupOtherRel.setBatchNo(Long.parseLong("0"));
				}						
				bidSupOtherRelManager.saveBidSupOtherRel(bidSupOtherRel);
			}
		}
		//定制分布
		if(bidDivisitonCustList.size()>0){			
			for(BidDivisitonCust bidDivisitonCust : bidDivisitonCustList){				
				bidDivisitonCustManager.saveBidDivisitonCust(bidDivisitonCust);
			}
		}
		
		
		return true;

	}

	/**
	 * @param orgCd  项目公司cd
	 */
	public List<BidLedger> getLedgerListByOrgCd(String orgCd) {
		String hql = " from BidLedger t where t.orgCd = ? ";
		List<BidLedger> list = this.find(hql, orgCd);
		if(list == null)
			return new ArrayList<BidLedger>();
		else
			return list;
	}
	
	
	/**
	 * 工程级别
	 * supHasImportedData:(搜索供应商是否在某轮次已经导入数据)
	 *  
	 * @param  @param sup
	 * @param  @param project
	 * @param  @param batchNo
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public boolean supHasImportedData(BidSup sup,BidProject project ,Long batchNo){
		boolean flag=false;
		//供应商
		if(BidSupManager.SUP_PROVIDOR.equals(sup.getTypeCd())){
			if(supHasImportedData(sup,project ,batchNo,"BidDivisionSupRel")||
					supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel2")||	
					supHasImportedData(sup,project ,batchNo,"BidOtherItemRel")||	
					supHasImportedData(sup,project ,batchNo,"BidSporadicSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidFeesSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidTaxsSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidSupOtherRel")	
					){
				flag=true;
			}
			//标底公司
		}else{
			if(supHasImportedData(sup,project ,batchNo,"BidDivisiton")||
					supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel2")||	
					supHasImportedData(sup,project ,batchNo,"BidOtherItemRel")||	
					supHasImportedData(sup,project ,batchNo,"BidSporadicSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidFeesSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidTaxsSupRel")||	
					supHasImportedData(sup,project ,batchNo,"BidSupOtherRel")	
					){
				flag=true;
			}
		}
		
		
		return flag;
	}
	
	
	public boolean supHasImportedDataVersion2(BidSup sup,BidProject project ,Long batchNo){
		boolean flag=false;
		if(supHasImportedData(sup,project ,batchNo,"BidDivisionSupRel")||
				supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel")||	
				supHasImportedData(sup,project ,batchNo,"BidMeasureSupRel2")||	
				supHasImportedData(sup,project ,batchNo,"BidOtherItemRel")||	
				supHasImportedData(sup,project ,batchNo,"BidSporadicSupRel")||	
				supHasImportedData(sup,project ,batchNo,"BidFeesSupRel")||	
				supHasImportedData(sup,project ,batchNo,"BidTaxsSupRel")||	
				supHasImportedData(sup,project ,batchNo,"BidSupOtherRel")	
				){
			flag=true;
		}
		return flag;
	}

	/**
	 * 工程级别
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
	public boolean supHasImportedData(BidSup sup, BidProject project, Long batchNo, String table) {
		boolean flag=false;
		StringBuffer hql=new StringBuffer();
		Map<String, Object> map=new HashMap<String, Object>();
		//如果是分部分项(标底)
		if("BidDivisiton".equals(table)){
			 map.put("bidProjectId", project.getBidProjectId());
			 map.put("batchNo", batchNo);
			 
			 hql.append("from BidDivisiton bds ")
			 	.append(" where bds.bidProject.bidProjectId = :bidProjectId")
			 	.append(" and bds.batchNo = :batchNo");
		}
		//如果是分部分项(供应商)
		else if("BidDivisionSupRel".equals(table)){
			 map.put("batchNo", batchNo);
			 map.put("bidProjectId", project.getBidProjectId());
			 map.put("bidSupId", sup.getBidSupId());
			 
			 hql.append("from BidDivisionSupRel bds where bds.batchNo = :batchNo")
			 	.append(" and bds.bidProject.bidProjectId = :bidProjectId")
			 	.append(" and bds.bidSup.bidSupId = :bidSupId ");
			
			 
			 //如果是其他
		 }else if("BidSupOtherRel".equals(table)){
			 map.put("batchNo", batchNo);
			 map.put("bidSupId", sup.getBidSupId());
			 
			 hql.append("from "+table+" bds where bds.batchNo = :batchNo")
			 	.append(" and bds.bidSup.bidSupId = :bidSupId ");
			 //除以上两种表
		 }else{
			 map.put("batchNo", batchNo);
			 map.put("bidProjectId", project.getBidProjectId());
			 map.put("bidSupId", sup.getBidSupId());
			 
			 hql.append(" from "+table+" bds where bds.batchNo = :batchNo")
			 	.append(" and bds.bidProject.bidProjectId = :bidProjectId")
			 	.append(" and bds.bidSup.bidSupId = :bidSupId ");			
			 
		 }
		if(this.countHqlResult(hql.toString(), map)>0){
			 flag=true; 
		 }
		 
		 return flag;		
		
	}
	
	
	
	
	
	/**
	 * 
	 * batchDeleteData:(批量删除指定业务数据)
	 *  
	 * @param  @param sup
	 * @param  @param project
	 * @param  @param batchNo
	 * @param  @param table    设定文件  
	 * @return void    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public void batchDeleteData(BidSup sup, BidProject project, Long batchNo){
		
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer hql=null;
		//标底
		if(BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())){
			 //如果是分部分项(标底)			
			deleteBidDivisiton(sup,project,batchNo);
			
			}else{
			 //供应商	
			 //如果是分部分项(供应商)
				deleteBidDivisionSupRel(sup,project,batchNo);
			}
		//措施项目清单与计价表(一)
		deleteBidMeasureSupRel(sup,project,batchNo);
		//措施项目清单与计价表(二)
		deleteBidMeasureSupRel2(sup,project,batchNo);
		//其他项目清单与计价汇总表
		deleteBidOtherItemRel(sup,project,batchNo);	
		//[零星工程费用表]
		deleteBidSporadicSupRel(sup,project,batchNo);
		//规费项目清单与计价表
		deleteBidFeesSupRel(sup,project,batchNo);	
		//税金项目清单与计价表
		deleteBidTaxsSupRel(sup,project,batchNo);	
		//自定义分部分项
		deleteBidDivisitonCust(sup,project,batchNo);	
		//标段其他费用
		deleteBidSupOtherRel(sup,project,batchNo);	
			
			
			
					   
			
		
			
			
		}
	
	public void deleteBidDivisiton(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer hql=null;
		
		 //如果是分部分项(标底)			
		 map=new HashMap<String, Object>();
		 map.put("bidProjectId", project.getBidProjectId());
		 map.put("batchNo", batchNo);
		 
		 hql=new StringBuffer();
		 hql.append(" delete from BidDivisiton bds where bds.bidProject.bidProjectId = :bidProjectId")
		 .append(" and bds.batchNo =:batchNo ");
		 this.getDao().batchExecute(hql.toString(), map);		
		
	}
	
	public void deleteBidDivisionSupRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		 map.put("bidProjectId", project.getBidProjectId());
		 map.put("bidSupId", sup.getBidSupId());
		 map.put("batchNo", batchNo);
		 StringBuffer hql=new StringBuffer();
		 hql.append(" delete from BidDivisionSupRel bdsr where bdsr.bidProject.bidProjectId = :bidProjectId ")
		     .append(" and bdsr.bidSup.bidSupId = :bidSupId ")
		     .append(" and bdsr.batchNo = :batchNo ");
		 this.getDao().batchExecute(hql.toString(), map);	
	}
	
	public void deleteBidDivisionSupRel(BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		 map.put("bidProjectId", project.getBidProjectId());		 
		 map.put("batchNo", batchNo);
		 StringBuffer hql=new StringBuffer();
		 hql.append(" delete from BidDivisionSupRel bdsr where  bdsr.bidProject.bidProjectId = :bidProjectId ")		     
		     .append(" and bdsr.batchNo = :batchNo ");
		 this.getDao().batchExecute(hql.toString(), map);	
	}
	
	public void deleteBidMeasureSupRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer();
		//措施项目清单与计价表(一)
		map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		hql=new StringBuffer();
		hql.append(" delete from BidMeasureSupRel bmsr where bmsr.bidSup.bidSupId = :bidSupId ")
		 	.append(" and bmsr.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and bmsr.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
	}
	

	
	public void deleteBidMeasureSupRel2(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		//措施项目清单与计价表(二)
		hql=new StringBuffer();
		hql.append(" delete from BidMeasureSupRel2 bmsr2 where bmsr2.bidSup.bidSupId = :bidSupId ")
		 	.append(" and bmsr2.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and bmsr2.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
	}
	public void deleteBidOtherItemRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		//其他项目清单与计价汇总表
		hql=new StringBuffer();
		hql.append(" delete from BidOtherItemRel boir where boir.bidSup.bidSupId = :bidSupId ")
		 	.append(" and boir.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and boir.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
	}
	public void deleteBidSporadicSupRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		// [零星工程费用表]
		hql=new StringBuffer();
		hql.append(" delete from BidSporadicSupRel bssr where bssr.bidSup.bidSupId = :bidSupId ")
		 	.append(" and bssr.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and bssr.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
		
	}
	public void deleteBidFeesSupRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		//规费项目清单与计价表
		hql=new StringBuffer();
		hql.append(" delete from BidFeesSupRel bfsr where bfsr.bidSup.bidSupId = :bidSupId ")
		 	.append(" and bfsr.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and bfsr.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
	}
	public void deleteBidTaxsSupRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		//税金项目清单与计价表
		hql=new StringBuffer();
		hql.append(" delete from BidTaxsSupRel btsr where btsr.bidSup.bidSupId = :bidSupId ")
		 	.append(" and btsr.bidProject.bidProjectId = :bidProjectId ")
		 	.append(" and btsr.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);
	}
	public void deleteBidDivisitonCust(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());
		map.put("bidProjectId", project.getBidProjectId());			
		map.put("batchNo", batchNo);
		StringBuffer hql=new StringBuffer();
		//自定义分部分项
		hql=new StringBuffer();
		hql.append(" delete from BidDivisitonCust bdc where bdc.bidSup.bidSupId = :bidSupId ")
		   .append(" and bdc.bidProject.bidProjectId = :bidProjectId")
		   .append(" and bdc.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);	
	}
	public void deleteBidSupOtherRel(BidSup sup, BidProject project, Long batchNo){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer();
		//标段其他费用
		hql=new StringBuffer();
		map=new HashMap<String, Object>();
		map.put("bidSupId", sup.getBidSupId());		
		map.put("batchNo", batchNo);
		hql.append(" delete from BidSupOtherRel bsor where bsor.bidSup.bidSupId = :bidSupId ")
	 		.append(" and bsor.batchNo = :batchNo");
		this.getDao().batchExecute(hql.toString(), map);	
	}
	
	
	
	/**
	 * 
	 * makePriceTotalSql:(构建搜索总价统计搜索,用于总占总价比例分析和平方米指标)  
	 *  
	 * @param  @param bidLegerId
	 * @param  @param bidSupId
	 * @param  @param batchNo
	 * @param  @return    设定文件  
	 * @return StringBuffer    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public StringBuffer makePriceTotalSql(String bidLegerId,BidSup bidSup1,BidSup bidSup2,String batchNo){
		StringBuffer sbPriceTotal = new StringBuffer();		
		sbPriceTotal.append(" select ");
		//参考价
		sbPriceTotal.append(" /** --参考价**/ ")
		.append(" ( select  ")
		/*.append(" (select case when sum( bd.total_amt) is null then 0 else sum( bd.total_amt) end  as bd_total_amt") 
		.append(" from bid_divisiton bd ")
		.append(" join bid_project bp on bd.bid_project_id=bp.bid_project_id")
		.append(" join bid_ledger bl on bl.bid_ledger_id=bp.bid_ledger_id")
		.append(" where bl.bid_ledger_id='"+bidLegerId+"' and bl.batch_no=0)+")*/
		.append(" (select nvl( sum( bdsr.total_amt) , 0 )  as bdsr_total_amt")
		.append("  from bid_division_sup_rel bdsr  ")
		.append("  where bdsr.bid_sup_id='"+bidSup2.getBidSupId()+"'")
		.append("  and bdsr.batch_no="+batchNo+")+")		
		.append(" (select nvl(sum(bmsr.amt) ,0 ) as bmsr_total_amt")
		.append(" from bid_measure_sup_rel bmsr")
		.append(" where bmsr.bid_sup_id='"+bidSup2.getBidSupId()+"' and bmsr.batch_no=0)+")
		.append(" /** -- **/")
		.append(" (select nvl(sum(bmsr2.total_amt), 0 )  as bmsr2_total_amt")
		.append(" from bid_measure_sup_rel2 bmsr2 ")
		.append(" where bmsr2.bid_sup_id='"+bidSup2.getBidSupId()+"' and bmsr2.batch_no=0)+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(boir.amt) ,0 )  as boir_total_amt")
		.append("  from bid_other_item_rel boir ")
		.append(" where boir.bid_sup_id='"+bidSup2.getBidSupId()+"' and boir.batch_no=0)+")
		.append(" /** -- **/")
		.append(" (select nvl(sum(bssr.total_amt), 0 )  as bssr_total_amt from")
		.append(" bid_sporadic_sup_rel bssr ")
		.append(" where bssr.bid_sup_id='"+bidSup2.getBidSupId()+"' and bssr.batch_no=0)+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(bfsr.amt), 0)   as bfsr_total_amt")
		.append("  from bid_fees_sup_rel bfsr ")
		.append(" where bfsr.bid_sup_id='"+bidSup2.getBidSupId()+"' and bfsr.batch_no=0)+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(btsr.amt) , 0 )  as btsr_total_amt")
		.append(" from bid_taxs_sup_rel btsr ")
		.append(" where btsr.bid_sup_id='"+bidSup2.getBidSupId()+"' and btsr.batch_no=0)+")
		.append(" (select nvl(sum(bsor.fee_amt) , 0 ) as bsor_total_amt")
		.append(" from bid_sup_other_rel bsor ")
		.append(" where bsor.bid_sup_id='"+bidSup2.getBidSupId()+"' and bsor.batch_no=0)")
		.append(" from dual ) as entityTotal_ref ,")
		//总报价
		.append(" /** --总报价**/ ")
		.append(" ( select  ")
		.append(" /** -- **/")
		.append(" (select nvl( sum(bdsr.total_amt) ,0 ) as bdsr_total_amt")
		.append(" from bid_division_sup_rel bdsr ")
		.append(" where bdsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bdsr.batch_no="+batchNo+")+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(bmsr.amt) , 0 )as bmsr_total_amt")
		.append(" from bid_measure_sup_rel bmsr ")
		.append(" where bmsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bmsr.batch_no="+batchNo+")+")
		.append(" /** -- **/")
		.append(" (select nvl(sum(bmsr2.total_amt), 0)  as bmsr2_total_amt")
		.append("  from bid_measure_sup_rel2 bmsr2 ")
		.append(" where bmsr2.bid_sup_id='"+bidSup1.getBidSupId()+"' and bmsr2.batch_no="+batchNo+")+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(boir.amt), 0) as boir_total_amt")
		.append(" from bid_other_item_rel boir ")
		.append(" where boir.bid_sup_id='"+bidSup1.getBidSupId()+"' and boir.batch_no="+batchNo+")+")
		.append(" /** -- **/")
		.append(" (select nvl(sum(bssr.total_amt) , 0 )  as bssr_total_amt")
		.append("  from bid_sporadic_sup_rel bssr ")
		.append(" where bssr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bssr.batch_no="+batchNo+")+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(bfsr.amt),0 ) as bfsr_total_amt")
		.append("  from bid_fees_sup_rel bfsr ")
		.append(" where bfsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bfsr.batch_no="+batchNo+")+")
		.append(" /** --**/ ")
		.append(" (select nvl(sum(btsr.amt), 0 )  as btsr_total_amt")
		.append(" from bid_taxs_sup_rel btsr ")
		.append(" where btsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and btsr.batch_no="+batchNo+")+")
		.append(" (select nvl(sum(bsor.fee_amt), 0) as bsor_total_amt")
		.append(" from bid_sup_other_rel bsor ")
		.append(" where bsor.bid_sup_id='"+bidSup1.getBidSupId()+"' and bsor.batch_no="+batchNo+")")
		.append(" from dual ) as entityTotal ,")
		 //---其他搜索字段
		.append("  /** --总面积**/ ")
		.append(" (select nvl( bl.section_total_area, 99999999) as section_total_area")
		.append("  from bid_ledger bl where bl.bid_ledger_id='"+bidLegerId+"')  as bid_area,")
		.append(" /** --分部分项**/ ")
		.append(" (select nvl(sum(bdsr.total_amt),0)  as bdsr_total_amt")
		.append(" from bid_division_sup_rel bdsr ")
		.append(" where bdsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bdsr.batch_no="+batchNo)
		.append(") as fenbufenxiang,")
		.append("  /**--措施报价**/ ")
		.append(" ((select nvl( sum(bmsr.amt) , 0 )  as bmsr_total_amt")
		.append("  from bid_measure_sup_rel bmsr")
		.append(" where bmsr.bid_sup_id='"+bidSup1.getBidSupId()+"' and bmsr.batch_no="+batchNo)	
		.append(")+(select nvl(sum(bmsr2.total_amt) , 0 )  as bmsr2_total_amt")
		.append("   from bid_measure_sup_rel2 bmsr2 ")
		.append(" where bmsr2.bid_sup_id='"+bidSup1.getBidSupId()+"' and bmsr2.batch_no="+batchNo)
		.append(")) as cuoshibaojia,")
		.append("  /**--其他项目清单报价**/ ")
		.append(" (select nvl(sum(boir.amt) ,0 )as boir_total_amt")
		.append(" from bid_other_item_rel boir")
		.append(" where boir.bid_sup_id='"+bidSup1.getBidSupId()+"' and boir.batch_no="+batchNo)
		.append(") qitaxmbaojia")
		.append(" /** --**/ ")
		.append(" from dual ");
		return sbPriceTotal;
		
	}
	
	/**
	 * 
	 * constructSummaryAnalysisSql:(构建汇总评优统计搜索SQL)
	 *  
	 * @param  @param table
	 * @param  @param batchNo
	 * @param  @param listBidSup
	 * @param  @return    设定文件  
	 * @return StringBuffer    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public StringBuffer constructSummaryAnalysisSql(String table,List <BidSup> listBidSup,
			Map<String,String> latestBatchNo,String bidLedgerId,BidSup biaodiSup) {
		StringBuffer sql = new StringBuffer();
		StringBuffer selectedCols = new StringBuffer();		
		String tableAlias = "";
		String strSum = "";
		
		// 开始构建Sql	
		//如果是分部分项表
		if ("bid_division_sup_rel".equals(table)) {			
			makeBidDivisionTotal(sql,listBidSup,latestBatchNo);
			/*//第一列为标底的分部分项统计
			sql.append(" select  nvl(sum(bdsrd.total_amt), 0 ) as total_amt");
			sql.append(" [selectedCols]  from  bid_divisiton bd ");
			sql.append(" join bid_project bp on bd.bid_project_id = bp.bid_project_id and bd.batch_no="+didiBatchNo+" and bp.bid_ledger_id='"+bidLedgerId+"' ");
			sql.append(" join bid_ledger bl on bl.bid_ledger_id = bp.bid_ledger_id ");
			//标底搜索统计字段
			tableAlias="bdsrd";
			// 构建标底join语句					
			makeJoinSql(sql, table, tableAlias,  biaodiSup.getBidSupId(),latestBatchNo.get(biaodiSup.getBidSupId()));
			//根据供应商依次统计字段
			int i = 0;
			for (BidSup sup : listBidSup) {
				// 如果为标底，则返回继续
				if (BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
					continue;
				}				
				// 构建选择列
				tableAlias = "bdsr" + i;
				strSum = makeSumSql(tableAlias, "total_amt", i);
				selectedCols.append(strSum);					
				// 构建join语句					
				makeJoinSql(sql, table, tableAlias,  sup.getBidSupId(),latestBatchNo.get(sup.getBidSupId()));
				i++;
			}
			//替换符号计算
			Integer start = sql.indexOf("[selectedCols]");
			Integer end = "[selectedCols]".length() + start;
			// 替换选择列
			sql.replace(start, end, selectedCols.toString());
			//标段ID
			sql.append(" where bl.bid_ledger_id='"+bidLedgerId+"'");*/
		} else {
			// 措施项1
			if ("bid_measure_sup_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "amt", listBidSup,latestBatchNo,biaodiSup);

			}else
			// 措施项2
			if ("bid_measure_sup_rel2".equals(table)) {
				sql = makeOneTableJoinSql(table, "total_amt", listBidSup,latestBatchNo,biaodiSup);
			}else
			// 其他项目清单与计价汇总表
			if ("bid_other_item_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "amt", listBidSup,latestBatchNo,biaodiSup);

			}else
			// 零星工程费用表
			if ("bid_sporadic_sup_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "total_amt",listBidSup,latestBatchNo,biaodiSup);
			}else
			// 规费项目清单与计价表
			if ("bid_fees_sup_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "amt", listBidSup,latestBatchNo,biaodiSup);
			}else
			// 税金项目清单与计价表
			if ("bid_taxs_sup_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "amt", listBidSup,latestBatchNo,biaodiSup);
			}else
			// 标段其他费用
			if ("bid_sup_other_rel".equals(table)) {
				sql = makeOneTableJoinSql(table, "fee_amt", listBidSup,latestBatchNo,biaodiSup);
			}
		}
		return sql;
	}
	
	private void  makeBidDivisionTotal(StringBuffer sb,List<BidSup> listBidSup,Map<String,String> latestBatchNo){
		StringBuffer tmpSb=new StringBuffer();
		BidSup tmpBiaodiSup=null;
		//获取标底公司，批号
		for (BidSup sup : listBidSup) {
			if (BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
				tmpBiaodiSup=sup;
				break;
			}		
		}
		//构建标底统计搜索
		tmpSb.append("(select nvl(sum(bdsr.total_amt),0) from bid_division_sup_rel bdsr  where bdsr.bid_sup_id='"+tmpBiaodiSup.getBidSupId())
		.append("' and bdsr.batch_no="+latestBatchNo.get(tmpBiaodiSup.getBidSupId())+") as s1");
		Integer indx=0;
		for (BidSup sup : listBidSup) {
			if (BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {
				tmpBiaodiSup=sup;
				continue;
			}else{				
				tmpSb.append(",(select  nvl(sum(bdsr.total_amt),0) from bid_division_sup_rel bdsr  where bdsr.bid_sup_id='"+sup.getBidSupId())
				.append("' and bdsr.batch_no="+latestBatchNo.get(sup.getBidSupId())+") as s_"+indx);
				indx++;
			}		
		}
		sb.append("select ");
		sb.append(tmpSb);
		sb.append(" from dual");
	}
	
	/**
	 * 
	 * makeOneTableJoinSql:(非分部分项表在总价汇总比较时候的SQL构建)
	 *  
	 * @param  @param table
	 * @param  @param col
	 * @param  @param batchNo
	 * @param  @param listBidSup
	 * @param  @return    设定文件  
	 * @return StringBuffer    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public StringBuffer makeOneTableJoinSql(String table, String col, List <BidSup> listBidSup,Map<String,String> latestBatchNo,BidSup biaodiSup) {		
		//搜索语句
		StringBuffer sql = new StringBuffer();
		//选择列,标底公司选择列字符串,供应商选择列字符串共同组建选择列
		StringBuffer selectedCols = new StringBuffer();
		//标底公司选择列字符串
		StringBuffer strBiaodiCompany = new StringBuffer();
		//供应商选择列字符串
		StringBuffer strUnbiaodiCompany = new StringBuffer();
		//[colStarter]为占位符号
		strUnbiaodiCompany.append("[colStarter] ");
		//搜索语句起头 ,[replacedCol] 为占位符号
		sql.append("select [replacedCol] ");
		//表别名
		String tableAlias = "";
		//供应商数量
		int i = 0;		
		for (; i < listBidSup.size(); i++) {
			BidSup sup = listBidSup.get(i);
			tableAlias = table + i;				
			// 构造标底公司选择列字符串
			if (BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())) {					
				 //搜索列
				 // ( select sum(bid_fees_sup_rel.amt) from bid_fees_sup_rel 
				 //	where bid_fees_sup_rel.batch_no=1 
				 //	and bid_fees_sup_rel.bid_sup_id='40282996323d6ea301323d707636000c' ) 				 
				String sqlFisrt = "( select sum(" + table + "." + col + ") from " + table ;
					   sqlFisrt +=  " where " + table+".batch_no="+latestBatchNo.get(sup.getBidSupId());
					   sqlFisrt += " and " + table + ".bid_sup_id='" + sup.getBidSupId() + "' )  ";
				//搜索语句加入case语句	   
				strBiaodiCompany.append(" case when " + sqlFisrt + " is null then 0 else ");
				strBiaodiCompany.append(sqlFisrt + " end   as "+ col + i);
			}
			//构造供应商选择列字符串
			else {
				String sqlFisrt =  "( select sum(" + table + "." + col + ") from " + table;
					   sqlFisrt += " where " + table+ ".batch_no="+latestBatchNo.get(sup.getBidSupId());
					   sqlFisrt += " and " + table + ".bid_sup_id='" + sup.getBidSupId() + "' ) ";
					   //[colStarter]占位符
				strUnbiaodiCompany.append(",case when " + sqlFisrt + " is null then 0 else " + sqlFisrt + " end   as "
						+ col + i);
			}
		}
		sql.append(" from dual");
		//截取标底公司的搜索字段
		int start1=strUnbiaodiCompany.indexOf("[colStarter]");
		//将标底公司的列替换入所有 搜索语句
		strUnbiaodiCompany.replace(start1, start1+"[colStarter]".length(), strBiaodiCompany.toString());
		//截取供应商的搜索字段
		Integer start = sql.indexOf("[replacedCol]");
		Integer end = "[replacedCol]".length() + start;		
		//所有的选择列
		String strAllCol=strUnbiaodiCompany.toString();
		//将所有的选择列替换入搜索语句
		sql.replace(start, end, strAllCol);
		return sql;
	}
	
	/**
	 * 
	 * makeJoinSql:(汇总评优统计搜索构建joinSQL)
	 * 
	 * @param @param sb
	 * @param @param table
	 * @param @param tableAlias
	 * @param @param i
	 * @param @param bidSupId
	 * @param @return 设定文件
	 * @return StringBuffer DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	private StringBuffer makeJoinSql(StringBuffer sb, String table, String tableAlias, String bidSupId,String bNo) {
		// 构建join语句,如果为表bid_division_sup_rel,别名bdsr0
		//  left join bid_division_sup_rel bdsr0 
		sb.append(" left join " + table + "  " + tableAlias);
		//如果是分部分项
		if ("bid_division_sup_rel".equals(table)) {
			sb.append(" on " + tableAlias + ".item_no=bd.item_no");
			sb.append(" and " + tableAlias + ".bid_sup_id='" + bidSupId + "' ");
			sb.append(" and " + tableAlias + ".batch_no=bd.batch_no");
			sb.append(" and " + tableAlias + ".batch_no=" + bNo + " ");
		} 
		//如果不是分部分项
		else {
			sb.append(" on " + tableAlias + ".bid_sup_id='" + bidSupId + "' ");
			sb.append(" and " + tableAlias + ".batch_no=" + bNo + " ");
		}
		return sb;
	}
	
	/**
	 * 
	 * makeSumSql:(构建求和SQL)
	 * 
	 * @param @param tableAlias
	 * @param @param col
	 * @param @param i
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	private String makeSumSql(String tableAlias, String col, Integer i) {
		return ",nvl (sum(" + tableAlias + "." + col + ")  ,0) as " + col + i;
	}
	/**
	 * 同步附件到PL服务器
	 * 
	 * @param supInfo
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public void syncAttaFile(String bidLedgerId, String fileName) throws IllegalAccessException,
			InvocationTargetException, IOException {
		List<AppAttachFile> list = new ArrayList<AppAttachFile>();
		String uniqueUiid = bidLedgerId;
		List<AppAttachFile> attaBaseList = appAttachFileManager.getAttachFilesByEntityIdAndFileName(uniqueUiid,
				fileName);
		list.addAll(attaBaseList);
		for (AppAttachFile appAttachFile : list) {
			PlAppAttachFile appFile = new PlAppAttachFile();
			BeanUtils.copyProperties(appAttachFile, appFile);
			appFile.setAppAttachFileId(null);
			// 保存附件到数据库
			plAppAttachFileManager.savePlAppAttachFile(appFile);
			// 移动文件到PD服务器
			// this.syncAtta(appAttachFile);

			// 另起一个线程来执行文件的拷贝工作，减少前台操作人员的等待时间
			Thread copyThread = new CopyFileThread(appAttachFile);
			copyThread.start();
		}
	}

	/**
	 * 拷贝文件线程
	 * 
	 * @author shixy
	 * 
	 *         2011-4-27
	 */
	public class CopyFileThread extends Thread {

		private Logger logger = LoggerFactory.getLogger(CopyFileThread.class);

		private AppAttachFile appAttachFile;

		public CopyFileThread(AppAttachFile appAttachFile) {
			this.appAttachFile = appAttachFile;
			try {
				String filePath = appAttachFile.getFilePath();
				String fileName = appAttachFile.getFileName();
				// 如果目标服务器在域中，需要配置smb.client
//				Config.setProperty("jcifs.smb.client.username", SHARE_USER_NAME);
//				Config.setProperty("jcifs.smb.client.password", SHARE_PASSWORD);
//				Config.setProperty("jcifs.smb.client.domain", SHARE_DOMAIN);

				// 读取本地文件(官网服务器)
				FileInputStream fis = new FileInputStream(filePath + File.separator + fileName);

				// 输出文件到目标机器共享目录(PD服务器)
				SmbFile file = new SmbFile("smb://pd2pl:pd2pl@192.168.173.180/"+appAttachFile.getBizModuleCd()+"/" + fileName);
				SmbFileOutputStream sfos = new SmbFileOutputStream(file);
				OutputStream fout = new BufferedOutputStream(sfos);
				// 缓冲数组
				byte b[] = new byte[8 * 1024];
				while ((fis.read(b)) != -1) {
					fout.write(b);
				}
				// 关闭输出流
				fout.flush();
				fout.close();
				fis.close();
			} catch (IOException e) {
				logger.error("拷贝文件失败" + e.getMessage());
			}

	}
	}
	
	/**
	 * 
	 *按照'计划开标时间'自动计入评标状态
	 */
	public void autoChangeToAnalyse(){
		try {
			log.info("开始自动将超出计划时间的状态置为评标状态");
			System.out.println("开始自动将超出计划时间的状态置为评标状态");
			//当前时间
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			//搜索条件
			Map<String, Object> map = new HashMap<String, Object>();
			//投标状态
			map.put("bidStatusCd", BID_STATUS_BIDDING);
			//搜索语句
			String hql = " from BidLedger bl where bl.bidStatusCd = :bidStatusCd and bl.bid_open_plan_date< to_date('"
					+ f.format(now) + "','yyyy-MM-dd')";
			//执行搜索
			List<BidLedger> bls = bidLedgerDao.find(hql, map);
			//如果列表不为空
			if (bls != null && bls.size() > 0) {
				for (BidLedger bl : bls) {
					log.info("满足自动更新的标段ID:" + bl.getBidLedgerId() + " 标段名:"
							+ bl.getBidSectionName());
				}
			}
			//开始执行自动调度操作
			log.info("开始自动将超出计划时间的状态置为评标状态");
			//执行更新操作
			updateOpenPlanDate(now, f);
			log.info("结束自动将超出计划时间的状态置为评标状态");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * '计划开标时间'自动计入评标状态
	 * 
	 */
	public void updateOpenPlanDate(Date now,SimpleDateFormat f){
		try {
			//搜索超过'计划开标时间'且属于投标状态的的数据,更新为评标状态
			String sql = "update bid_ledger t set t.bid_status_cd = :bid_status_cd  where  t.bid_open_plan_date< to_date('"
					+ f.format(now)
					+ "','yyyy-MM-dd') and t.bid_status_cd = :bid_status_cd2";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bid_status_cd", BID_STATUS_MARKING);
			map.put("bid_status_cd2", BID_STATUS_BIDDING);
			bidLedgerDao.batchExecuteSql(sql, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据标段供应商搜索标段信息
	 * @return
	 */
	public BidLedger getBidLedgerByBidSup(String bidSupId){
		try{
			//搜索语句
			StringBuffer hql=new StringBuffer()
			.append("select bl from BidLedger bl,BidSup bs where bs.bidLedger.bidLedgerId=bl.bidLedgerId and bs.bidSupId = :bidSupId");
			//构造搜索条件
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("bidSupId", bidSupId);
			//按照条件执行搜索
			List <BidLedger> bidLedgerList = bidLedgerDao.find(hql.toString(), map);
			if(bidLedgerList!=null&&bidLedgerList.size()>0)
				return bidLedgerList.get(0);
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	

	//------------------以下为上传附件需用到的	
	
	/**
	 * 保存附件信息
	 */
	public void saveAppAttachFile(AppAttachFile appAttachFile) {
		PowerUtils.setEmptyStr2Null(appAttachFile);
		appAttachFileManager.saveAppAttachFile(appAttachFile);
	}
	
	/**
	 * 更新附件标记
	 * @param entityName
	 * @param bizEntityId
	 * @param fieldName
	 */
	public void updateAttachFlg(String entityName, String bizEntityId,String fieldName) {
		if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			String attachFlg = null;
			if (isUpload(bizEntityId,fieldName)) {
				attachFlg = "1";
			} else {
				attachFlg = "0";
			}
			String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
			StringBuilder hql = new StringBuilder("update ");
			hql.append(entityName).append(" set "+fieldName+"=? where ").append(entityId).append("=?");
			appAttachFileManager.getDao().batchExecute(hql.toString(), attachFlg, bizEntityId);
		}
	}
	
	/**
	 * 是否已经上传
	 * @param bizEntityId
	 * @param bizFieldName
	 * @return
	 */
	private boolean isUpload(String bizEntityId,String bizFieldName){
		StringBuffer hql=new StringBuffer();
		hql.append(" from AppAttachFile t where t.bizEntityId = :bizEntityId and t.bizFieldName = :bizFieldName and t.statusCd = :statusCd");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("bizEntityId", bizEntityId);
		map.put("bizFieldName", bizFieldName);
		map.put("statusCd", "1");
		List rs=appAttachFileManager.find(hql.toString(), map);
		if(rs!=null&&rs.size()>0)
			return true;
		else
			return false;
		
		
	}
	
	/**
	 * 保存附件
	 * @param lstAppAttachFile
	 * @param entityName
	 * @param bizEntityId
	 * @param fieldName
	 */
	public void saveAppAttachFiles(List<AppAttachFile> lstAppAttachFile, String entityName, String bizEntityId,String fieldName) {
		for (AppAttachFile appAttachFile : lstAppAttachFile) {
			saveAppAttachFile(appAttachFile);
		}
		this.getDao().getSession().flush();
		if (entityName != null && bizEntityId != null) {
			updateAttachFlg(entityName, bizEntityId, fieldName);
		}
	}
	
	/**
	 * 将对应的附件设置为无效状态
	 * @param bizModuleCd
	 * @param bizEntityId
	 */
	public void invalidModuleData(String bizModuleCd, String bizEntityId,String bizFieldName) {
		Map<String,Object>  map=new HashMap();
		 map.put("statusCd", "-1");
		 map.put("bizEntityId", bizEntityId);
		 map.put("bizModuleCd", bizModuleCd);
		 map.put("bizFieldName", bizFieldName);
		StringBuffer hql=new StringBuffer()
		.append("update AppAttachFile set statusCd = :statusCd  where bizEntityId = :bizEntityId and bizModuleCd = :bizModuleCd and bizFieldName = :bizFieldName");
		appAttachFileManager.getDao().batchExecute(hql.toString(), map);
	}
	
	/**
	 * 搜索附件
	 * @param bizEntityId
	 * @param bizModuleCd
	 * @param bizFieldName
	 * @return
	 * @throws Exception
	 */
	public AppAttachFile listAttach(String bizEntityId,String bizModuleCd,String bizFieldName) throws Exception {
		StringBuffer hql = new StringBuffer().append(" from AppAttachFile a where a.statusCd = :statusCd ")
		.append(" and a.bizModuleCd = :bizModuleCd")
		.append(" and a.bizFieldName = :bizFieldName")
		.append(" and a.bizEntityId = :bizEntityId");

		Map<String, Object> map = new HashMap();
		map.put("statusCd", "1");// 正常的，非删除状态的
		map.put("bizEntityId", bizEntityId);
		map.put("bizModuleCd", bizModuleCd);
		map.put("bizFieldName", bizFieldName);
		List<AppAttachFile> attList = appAttachFileManager.find(hql.toString(), map);
		if (attList != null && attList.size() > 0)
			return attList.get(0);
		else
			return null;
	}
	
	/**
	 * 搜索附件(多个文件的情况)
	 * @param bizEntityId
	 * @param bizModuleCd
	 * @param bizFieldName
	 * @return
	 * @throws Exception
	 */
	public List<AppAttachFile> listAttachs(String bizEntityId,String bizModuleCd,String bizFieldName) throws Exception {
		StringBuffer hql = new StringBuffer().append(" from AppAttachFile a where a.statusCd = :statusCd ")
		.append(" and a.bizModuleCd = :bizModuleCd")
		.append(" and a.bizFieldName = :bizFieldName")
		.append(" and a.bizEntityId = :bizEntityId");

		Map<String, Object> map = new HashMap();
		map.put("statusCd", "1");// 正常的，非删除状态的
		map.put("bizEntityId", bizEntityId);
		map.put("bizModuleCd", bizModuleCd);
		map.put("bizFieldName", bizFieldName);
		List<AppAttachFile> attList = appAttachFileManager.find(hql.toString(), map);
		return attList;
	}
	
	/**
	 * 删除附件
	 * @param attachFileTmp
	 * @param entityName
	 * @param bizEntityId
	 * @param onlyFile
	 */
	public void deleteAppAttachFile(AppAttachFile attachFileTmp, String entityName, String bizEntityId, boolean onlyFile) {
		String strPath = attachFileTmp.getFilePath();
		deleteFile(strPath, attachFileTmp.getFileName(), attachFileTmp.getCreator());
		if (StringUtils.isNotEmpty(attachFileTmp.getSmallPicName())) {
			deleteFile(strPath, attachFileTmp.getSmallPicName(), attachFileTmp.getCreator());
		}
		if (!onlyFile) {
			// 不保留记录
			appAttachFileManager.delete(attachFileTmp);
			updateAttachFlg(entityName, bizEntityId,attachFileTmp.getBizFieldName());
		}
		//搜索并删除官网的附件
		List<PlAppAttachFile> plAttachs=plAttachFilesByFileName(bizEntityId,attachFileTmp.getBizModuleCd(),attachFileTmp.getBizFieldName(),attachFileTmp.getFileName());
		if(plAttachs!=null&&plAttachs.size()>0){
			plAppAttachFileManager.delete(plAttachs);
		}
	}
	
	/**
	 * 删除旧文件
	 * 
	 * @param dir
	 */
	private void deleteFile(String filePath, String fileName, String uiid) {
		File file = new File(filePath + "\\" + fileName);
		if (!file.exists()) {
			file = new File(filePath + "\\" + uiid + "\\" + fileName);
		}
		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				//logger.info("删除成功:" + file.getPath());
			}
		}
	}
	
	/**
	 * 第二中同步文件的方法
	 * @param bizEntityId
	 * @param fieldName
	 */
	public void syncAttaFileTwo(String bizEntityId, String fieldName) {
		List<AppAttachFile> list = new ArrayList<AppAttachFile>();
		List<AppAttachFile> attaBaseList = getAttachFilesByEntityIdAndFieldName(bizEntityId,fieldName);
		list.addAll(attaBaseList);
		for (AppAttachFile appAttachFile : list) {
			//搜索是否已经存在本文件，有则不同不
			List l=plAttachFilesByFileName(bizEntityId,appAttachFile.getBizModuleCd(),appAttachFile.getBizFieldName(),appAttachFile.getFileName());
			if(l!=null&&l.size()>0){
				continue;
			}
			PlAppAttachFile appFile = new PlAppAttachFile();
			BeanUtils.copyProperties(appAttachFile, appFile);
			appFile.setAppAttachFileId(null);
			// 保存附件到数据库
			plAppAttachFileManager.savePlAppAttachFile(appFile);
			// 移动文件到PD服务器

			// 另起一个线程来执行文件的拷贝工作，减少前台操作人员的等待时间
			Thread copyThread = new CopyFileThread(appAttachFile);
			copyThread.start();
		}
		
	}
	
	/**
	 * 搜索PD端附件
	 * @param bizEntityId
	 * @param fieldName
	 * @return
	 */
	private List<AppAttachFile> getAttachFilesByEntityIdAndFieldName(String bizEntityId, String fieldName) {
		StringBuffer hql = new StringBuffer().append(" from AppAttachFile a where a.statusCd = :statusCd ")
		.append(" and a.bizFieldName = :bizFieldName")
		.append(" and a.bizEntityId = :bizEntityId");

		Map<String, Object> map = new HashMap();
		map.put("statusCd", "1");// 正常的，非删除状态的
		map.put("bizEntityId", bizEntityId);
		map.put("bizFieldName", fieldName);
		List<AppAttachFile> attList = appAttachFileManager.find(hql.toString(), map);
		return attList;
	}
	
/*	*//**
	 * 搜索PL端附件
	 * @param uniqueUiid
	 * @param fieldName
	 * @return
	 *//*
	private List<PlAppAttachFile> plAttachFilesByEntityIdAndFieldName(String bizEntityId,String bizModuleCd, String fieldName,String fileName) {
		StringBuffer hql = new StringBuffer().append(" from PlAppAttachFile a where a.statusCd = :statusCd ")
		.append(" and a.bizFieldName = :bizFieldName")
		.append(" and a.bizModuleCd = :bizModuleCd")
		.append(" and a.bizEntityId = :bizEntityId")
		.append(" and a.fileName = :fileName");

		Map<String, Object> map = new HashMap();
		map.put("statusCd", "1");// 正常的，非删除状态的
		map.put("bizEntityId", bizEntityId);
		map.put("bizFieldName", fieldName);
		map.put("bizModuleCd", bizModuleCd);
		map.put("fileName", fileName);
		List<PlAppAttachFile> attList = plAppAttachFileManager.find(hql.toString(), map);
		return attList;
	}*/
	
	
	/**
	 * 搜索特定名称文件PL端附件
	 * @param uniqueUiid
	 * @param fieldName
	 * @return
	 */
	public List<PlAppAttachFile> plAttachFilesByFileName(String bizEntityId,String bizModuleCd, String fieldName,String fileName) {
		StringBuffer hql = new StringBuffer().append(" from PlAppAttachFile a where a.statusCd = :statusCd ");
		if(StringUtils.isNotBlank(fieldName)) {
			hql.append(" and a.bizFieldName = :bizFieldName");
		}
		hql.append(" and a.bizModuleCd = :bizModuleCd")
		.append(" and a.bizEntityId = :bizEntityId");
		if(StringUtils.isNotBlank(fileName)) {
			hql.append(" and a.fileName = :fileName");
		}

		Map<String, Object> map = new HashMap();
		map.put("statusCd", "1");// 正常的，非删除状态的
		map.put("bizEntityId", bizEntityId);
		if(StringUtils.isNotBlank(fieldName)) {
			map.put("bizFieldName", fieldName);
		}
		map.put("bizModuleCd", bizModuleCd);
		if(StringUtils.isNotBlank(fileName)) {
			map.put("fileName", fileName);
		}
		List<PlAppAttachFile> attList = plAppAttachFileManager.find(hql.toString(), map);
		return attList;
	}

	/**
	 * 导入合同台账
	 * @param strageInviteUnitBill
	 */
	public void importStrageBean(StrageInviteUnitBill bill) {

		BidSupManager bidSupManager = SpringContextHolder.getBean("bidSupManager");
		SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
//		BidConsultManager bidConsultManager = SpringContextHolder.getBean("bidConsultManager");

		String resAppId = bill.getResApproveInfo().getResApproveInfoId();
		try{
			/* 1.投标台帐信息
			 * 导入字段有 "标段名称"/"邀请类型"/"备注"/"网批ID"
			 */
			BidLedger bidLedger = new BidLedger();
			//项目名称Cd
			bidLedger.setOrgCd(bill.getProjectCd());
			//项目名称
			bidLedger.setOrgName(bill.getProjectName());
			//期数
			bidLedger.setPeriodNum(bill.getPeriodNum());
			//工程
			bidLedger.setConstruction(bill.getConstruction());
			//导入类型： 战略
			bidLedger.setSrcTypeCd(BidLedgerManager.SRC_TYPE_ZL);
			//标段名称
			bidLedger.setBidSectionName(bill.getStrageName());
			//邀请类型:明标/暗标(默认:9-未知)
			bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_UNKNOWN);
			if("true".equals(bill.getShowFlag())){
				bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_SHOW);
			} 
			if("true".equals(bill.getHideFlag())){
				bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_HIDE);
			}  
			//预算类型
			//投标状态
			bidLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_IMPORT);
			//网批ID		
			bidLedger.setResApproveInfoId(resAppId);
			//网批编号
			bidLedger.setResApproveCd(bill.getResApproveInfo().getApproveCd()+bill.getResApproveInfo().getSerialNo());

			//默认系统创建
			bidLedger.setCreator("system");
			bidLedger.setCreatedDate(new Date());
			//默认批次号
			bidLedger.setBatchNo(0L);
			//1-模拟清单 0-标底单位
			bidLedger.setConsultFlg("0");
			
			// updatedCenterCd设空  add by liuzhihui 2012-04-10
			bidLedger.setUpdatedCenterCd("");

			
			//导入招标计划ID add by huangbijin 2012-06-27
			//bidLedger.setCcbpId(bill.getCcbpId());
			//bidLedger.setCcbpNo(bill.getCcbpNo());
			
			// 自动生成ccbpNo规则: ZBPT-PROJECT-ORDERNO
			bidLedger.setSerialType(getSerialType(bill.getProjectCd()));
			bidLedger.setSerialNo(getNextSerialNo(bill.getProjectCd()));
			
			saveBidLedger(bidLedger);
			
			
//			AUTHOR:WANGMING MODIFYDATE:2012.5.21  
//			REASON:需求变更 （标底单位暂不处理）
//			
//			// 2.标底单位/投标单位
//			// 2.1构造标底单位(序号/关联供应商ID/供应商名称)
//			
//			//搜索模拟单位
//			BidConsult bidConsult = bidConsultManager.getBiaodi();
//			//咨询公司
//			BidSup sup = new BidSup();
//			sup.setDispOrderNo(new Long(0));//默认0
//			if(	bidConsult == null){
////				sup.setRelSupBasicId(null);
//				sup.setSupName("标底单位");
//			}else{
//				sup.setSupName("标底单位");
//				bidLedger.setConsultFlg("0");//1-模拟清单，1-标底单位
//				sup.setRelSupBasicId(bidConsult.getBidConsultId());
//				sup.setSupName(bidConsult.getFullName());
//			}
//			sup.setTypeCd(BidSupManager.SUP_CONSULTING);
//			sup.setCreator("system");
//			sup.setCreatedDate(new Date());
//			sup.setBidLedger(bidLedger);
//			bidSupManager.saveBidSup(sup);
//			
			
			
			
			
			/* 构造《供应商信息》
			 * 序号/关联供应商ID/供应商名称/
			 */

			List<String> tmpIdList = new ArrayList<String>();//用于过滤重复的单位
			BidSup bidSup = null;
			int i = 1;
			SupBasic supBasic = null;
//			List<SupContactor> contactors = null;
//			SupContactor contactor = null;
			
			List<StrageInviteUnitProperty> otherProperties  = bill.getOtherProperties();
			if(otherProperties != null){
				for (StrageInviteUnitProperty tmpProp : otherProperties) {
					
					//供应商重复，跳过;
					if(tmpIdList.contains(tmpProp.getSupBasicId())){
						continue;
					}
					
					tmpIdList.add(tmpProp.getSupBasicId());
					
					bidSup = new BidSup();
					//对应台账主表
					bidSup.setBidLedger(bidLedger);
					//显示顺序号
					bidSup.setDispOrderNo(Long.parseLong(Integer.toString(i++)));
					//关联供应商ID(供应商台账库）
					bidSup.setRelSupBasicId(tmpProp.getSupBasicId());
					//供应商名称
					bidSup.setSupName(tmpProp.getUnitName());
					//是否接受(接受/拒绝）
					bidSup.setReceiveStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
					//是否提交保证金
					bidSup.setSubmitStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
					//是否退款 
					bidSup.setRefundStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
					//是否中标(未中标/中标)
					bidSup.setSupBidStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);	
					//是否有附件
					bidSup.setAttachFlg(BidSupManager.ATTACH_FILE_NO);
					//供应商
					bidSup.setTypeCd(BidSupManager.SUP_PROVIDOR);
					
					supBasic = supBasicManager.getEntity(tmpProp.getSupBasicId());
					if(supBasic != null){	
						//供方级别
						if(StringUtils.isNotBlank(supBasic.getSupManaEval())){						
							bidSup.setProvideLevelCd(supBasic.getSupManaEval());
						}
						//获取第一个联系人信息
						//标段列表中用户自动选择，不需要初始化
						/*contactors = supBasic.getSupContactors();
						if(contactors != null && contactors.size()>0){
							contactor = contactors.get(0);
							if(contactor!= null){
								//联系人
								bidSup.setLinkMan(contactor.getName());
								//联系方式
								bidSup.setLinkDesc(contactor.getTelephone());
								//电子邮件
								bidSup.setEmail(contactor.getEMail());
							}
						}*/
					}
					
					//默认系统创建
					bidSup.setCreator("system");
					bidSup.setCreatedDate(new Date());
					
					bidSup.setBidLedger(bidLedger);
					bidSupManager.saveBidSup(bidSup);
				}

				log.info(new StringBuffer()
							.append("导入投标台帐,网批ID[").append(resAppId)
							.append("]转存投标台帐ID[").append(bidLedger.getBidLedgerId()).append("]供应商数[")
							.append(otherProperties.size())
							.toString()
				);
			}
		}catch (Exception e) {
			log.error(new StringBuffer()
			.append("导入投标台帐异常,网批ID[").append(resAppId).append("]").toString(),e);
		}
	}

	/**
	 * 战略投标是否已经存在相应的材料设备
	 * @param bidSectionName 材料设备名称
	 * @return
	 */
	public boolean strategyHasExsit(String bidSectionName) {
		boolean flg=false;
		
		StringBuffer hql=new StringBuffer()
		.append(" from BidLedger bl where bl.srcTypeCd = :srcTypeCd ")
		.append(" and bl.bidSectionName = :bidSectionName");
		
		//搜索条件MAP
		Map<String, Object> values = new HashMap<String, Object>();
		//战略材料投标类型
		values.put("srcTypeCd", "1");
		values.put("bidSectionName", bidSectionName);
		
		List rs=bidLedgerDao.find(hql.toString(), values);
		if(rs!=null&&rs.size()>0){
			flg=true;
		}		
		return flg;
	}

	/**
	 * 查应标但未上传投标文件的请情况
	 * @param bidLedger
	 */
	public boolean hasNotUploadAttach(BidLedger bidLedger) {
		
		StringBuffer sql=new StringBuffer()
		.append("select atta.bid_sup_attach_rel_id,atta.batch_no from bid_sup_attach_rel atta ,bid_sup bs,bid_ledger bl ")
		.append(" where bs.bid_sup_id=atta.bid_sup_id ")
		.append(" and bl.bid_ledger_id=bs.bid_ledger_id ")
		.append(" and bl.batch_no=atta.batch_no ")
		.append(" and bs.receive_status_cd = :receiveStatusCd ")
		.append(" and bl.bid_ledger_id = :bidLedgerId ")
		.append(" and atta.batch_no = :batchNo and (atta.atta_biz_flg = :attaBizFlg or atta.atta_tech_flg = :attaTechFlg) ");
		
		//搜索条件MAP
		Map<String, Object> values = new HashMap<String, Object>();
		//战略材料投标类型
		values.put("bidLedgerId", bidLedger.getBidLedgerId());
		values.put("batchNo", bidLedger.getBatchNo());
		values.put("receiveStatusCd", "1");
		values.put("attaBizFlg", "0");
		values.put("attaTechFlg", "0");
		
		
		List l=bidLedgerDao.findBySql(sql.toString(), values);
		if(l!=null&&l.size()>0)
			return true;
		else
			return false;
		
	}

	/**
	 * 查询 对应的网上投标台账信息
	 * @param ccbpNo 招标采购编号
	 * @return
	 */
	public BidLedger getLedgerByCcbpNo(String ccbpId) {
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("ccbpId", ccbpId);
		String hql = "from BidLedger t where t.ccbpId = :ccbpId ";
		List list = this.find(hql, values);
		if(list == null)
			return null;
		else
			return (BidLedger)list.get(0);
	}
	
	
	/**
	 * 获取下一个序号
	 * @return
	 */
	public long getNextSerialNo(String projectCd){
		String type = this.getSerialType(projectCd);
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("serialType", type);
		List result = findBySql("select max(serial_no) from bid_ledger where serial_type = :serialType ", values);
		if (result.size() > 0 && result.get(0) != null)
			return ((BigDecimal) result.get(0)).longValue() + 1;
		return 1L;
	}

	/**
	 * 获取序号战略平台序号前缀
	 * @param prjCd
	 * @return
	 */
	public String getSerialType(String prjCd){
		StringBuilder sb = new StringBuilder("ZLPT-");
		String pyCode = CodeNameUtil.getDeptShortNameByCd(prjCd);
		if(StringUtils.isNotBlank(pyCode)){
			sb.append(pyCode).append("-");
		} 
		return sb.toString();
	}
	
	
	/**
	 * 查询项目
	 */
	public List findOrgList(String user){
		
		List orgList = findBySql("select distinct t.project_cd   from ump.bid_project_role t where t.user_cd like '"+user+";%' or t.user_cd like '%;"+user+";%'", null);
		return orgList;
	}
	
}
