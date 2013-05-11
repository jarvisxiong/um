package com.hhz.ump.web.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateParser;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bid.BidItemSupRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.bid.BidTechItemManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.bid.BidItemSupRel;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.bid.BidTechItem;
import com.hhz.ump.util.DictContants;

@Namespace("/bid")
@Results( { @Result(name = "matrix2", location = "/WEB-INF/content/bid/bid-tech-item-matrix.ftl", type = "freemarker") })
public class BidTechItemAction extends CrudActionSupport<BidTechItem>{

	private static final long serialVersionUID = 1019682453456214751L;
	
	private BidTechItem entity; 

	@Autowired
	private BidTechItemManager bidTechItemManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;
	@Autowired
	private BidSupManager bisSupManager;
	@Autowired
	protected  BidItemSupRelManager bidItemSupRelManager;
	
	private String bidLedgerId;
	private List<BidSup> bidSupList;
	private String totalRankDesc;
	
	
	//投标台账信息
	private BidLedger bidLedger;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		//搜索item数
		List<BidTechItem> techItemList =bidTechItemManager.queryBidTechItem(bidLedgerId);
		bidLedger = bidLedgerManager.getEntity(bidLedgerId);
		
		if(bidLedger == null){
			Struts2Utils.renderText("bidLedger is not founded!");
			return null;
		}
		//得到供应商横轴数据
		List<BidSup> supList =getBidSupList();
		//批量新增supRel值
		List<BidItemSupRel> relList =new ArrayList<BidItemSupRel>();
		//如果没有结果，则初始化评审内容数据
		if(techItemList==null||techItemList.size()<=0){
			List<BidTechItem> itemList =new ArrayList<BidTechItem>();
			AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.BID_EXAM_CONT);
			for(AppDictData appDictData :appDictType.getAppDictDatas()){
				BidTechItem item =new BidTechItem();
				item.setItemCd(appDictData.getDictCd());
				item.setItemName(appDictData.getDictName());
				item.setDispOrderNo(appDictData.getDispOrderNo().longValue());
				item.setModuleName(appDictData.getRemark());
				item.setBidLedger(bidLedger);
				itemList.add(item);
			}
			//批量保存评审内容
			bidTechItemManager.batchSaveBidTechItem(itemList);
			for(BidTechItem item:itemList){
				for(BidSup sup:supList){
					BidItemSupRel supRel = new BidItemSupRel();
					supRel.setBidSup(sup);
					supRel.setBidTechItem(item);
					relList.add(supRel);
				}
			}
		}else{
			//对supList进行遍历，
			for(BidSup sup:supList){
				BidTechItem item=techItemList.get(0);
				boolean haveData=false;
				for(BidItemSupRel rel:item.getBidItemSupRels()){
					if(rel.getBidSup().getBidSupId().equals(sup.getBidSupId())){
						haveData =true;
						break;
					}
				}
				if(!haveData){
					for(BidTechItem techItem:techItemList){
						BidItemSupRel supRel = new BidItemSupRel();
						supRel.setBidSup(sup);
						supRel.setBidTechItem(techItem);
						relList.add(supRel);
					}
				}
			}
		}
		if(relList!=null&&relList.size()>0){
			//批量保存数据
			bidItemSupRelManager.batchSaveBidItemSupRel(relList);
		}
		return SUCCESS;
	}
	/**
	 * 评审表格，用freemarker形式展示
	 * @return
	 */
	public String matrix(){
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter filter = null;
		filter = new PropertyFilter("EQS_bidLedger.bidLedgerId",bidLedgerId);
		filters.add(filter);
		page.setPageSize(50);
		page.setOrderBy("dispOrderNo");
		page.setOrder("asc");
		page = bidTechItemManager.findPage(page, filters);
		 bidLedger = bidLedgerManager.getEntity(bidLedgerId);
		totalRankDesc =bidLedger.getTotalRankDesc();
		return "matrix";
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveLedger(){
		BidLedger tmpBid = bidLedgerManager.getEntity(bidLedgerId);
		tmpBid.setTotalRankDesc(totalRankDesc);
		
		/*
		//如果修改的人不在评审人里，则添加
		if(tmpBid.getJudgeCd()==null||tmpBid.getJudgeCd().indexOf(SpringSecurityUtils.getCurrentUiid()+";")<0){
			String judgeCd;
			if(tmpBid.getJudgeCd()==null){
				judgeCd=SpringSecurityUtils.getCurrentUiid()+";";
			}else{
				judgeCd=tmpBid.getJudgeCd()+SpringSecurityUtils.getCurrentUiid()+";";
			}
			tmpBid.setJudgeCd(judgeCd);
		}
		*/
		bidLedgerManager.saveBidLedger(tmpBid);
		Struts2Utils.renderText("success");
	}
	
	/**
	 * 更新标段评审信息
	 * @param bidLedgerId
	 * @param judgeName
	 * @param judgeCd
	 * @param judgeDate
	 * @return
	 */
	public String saveJudge(){
		String tmpBidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		String tmpJudgeName = Struts2Utils.getParameter("judgeName");
		String tmpJudgeCd = Struts2Utils.getParameter("judgeCd");
		String judgeDate = Struts2Utils.getParameter("judgeDate");
		BidLedger tmpBid = bidLedgerManager.getEntity(tmpBidLedgerId);
		if(tmpBid == null){
			Struts2Utils.renderText("bidLedgerId is blank!");
			return null;
		}
		tmpBid.setJudgeName(tmpJudgeName);
		tmpBid.setJudgeCd(tmpJudgeCd);
		tmpBid.setJudgeDate(DateParser.parse(judgeDate,"yyyy-MM-dd"));
		bidLedgerManager.saveBidLedger(tmpBid);
		Struts2Utils.renderText("success");
		
		return null;
	}

	@Override
	public BidTechItem getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}
	//搜索符合条件的供应商信息
	public List<BidSup> getBidSupList() {
		List<BidSup> sups=new ArrayList<BidSup>();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql=" from BidSup a where a.bidLedger.bidLedgerId =:bidLedgerId and a.receiveStatusCd = :receiveStatusCd ";
		param.put("bidLedgerId", bidLedgerId);
		param.put("receiveStatusCd", BidSupManager.RECEIVE_STATUS_YES);//1-接受
		List<BidSup> supList = bisSupManager.find(hql, param);
		if(supList!=null){
			//过滤标底单位
			for( BidSup sup : supList){
				if(!BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())){
					sups.add(sup);
				}
			}
			return sups;
		}
		
			
		else
			return sups;
	}

	public void setBidSupList(List<BidSup> bidSupList) {
		this.bidSupList = bidSupList;
	}

	public String getTotalRankDesc() {
		return totalRankDesc;
	}

	public void setTotalRankDesc(String totalRankDesc) {
		this.totalRankDesc = totalRankDesc;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
}
