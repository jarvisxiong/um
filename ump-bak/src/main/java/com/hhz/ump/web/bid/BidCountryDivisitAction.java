package com.hhz.ump.web.bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidCountryDivisitManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.entity.bid.BidCountryDivisit;

public class BidCountryDivisitAction extends CrudActionSupport<BidCountryDivisit> {

	private String bidProjectId;
	private String itemId;
	private String itemIdsTemp;
	private String itemNamesTemp;
	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidCountryDivisitManager bidCountryDivisitManager;
	private List<BidCountryDivisit> bidCountryDivisitList;
	/**
	 * 国标
	 */
	private BidCountryDivisit entity;
	@Override
	public BidCountryDivisit getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		String projectItemNo=null;
		if(StringUtils.isNotBlank(bidProjectId)){
			 projectItemNo=bidDivisitonManager.getItemNos(bidProjectId);
		}
		//搜索国标大类
		Map <String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(projectItemNo)){
			map.put("projectItemNo", projectItemNo.split(","));
		}else{
			map.put("projectItemNo", "00");
		}
		StringBuffer hql=new StringBuffer().append(" from BidCountryDivisit where 1=1 ");
		hql.append(" and parentItemId in (:projectItemNo) and parentItemId=itemId order by itemId,bidCountryDivisitId ");
		bidCountryDivisitList=bidCountryDivisitManager.find(hql.toString(), map);
		return "list";
	}
	public String detail() throws Exception{
		Map <String,Object> map=new HashMap<String,Object>();
		map.put("itemId", itemId);
		StringBuffer hql=new StringBuffer().append(" from BidCountryDivisit where 1=1 ");
		hql.append(" and parentItemId =:itemId and  parentItemId<>itemId  order by itemId,bidCountryDivisitId");
		bidCountryDivisitList=bidCountryDivisitManager.find(hql.toString(), map);
		return "detail";
	}
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getBidProjectId() {
		return bidProjectId;
	}

	public void setBidProjectId(String bidProjectId) {
		this.bidProjectId = bidProjectId;
	}

	public List<BidCountryDivisit> getBidCountryDivisitList() {
		return bidCountryDivisitList;
	}

	public void setBidCountryDivisitList(List<BidCountryDivisit> bidCountryDivisitList) {
		this.bidCountryDivisitList = bidCountryDivisitList;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemIdsTemp() {
		return itemIdsTemp;
	}

	public String getItemNamesTemp() {
		return itemNamesTemp;
	}

	public void setItemIdsTemp(String itemIdsTemp) {
		this.itemIdsTemp = itemIdsTemp;
	}

	public void setItemNamesTemp(String itemNamesTemp) {
		this.itemNamesTemp = itemNamesTemp;
	}

}
