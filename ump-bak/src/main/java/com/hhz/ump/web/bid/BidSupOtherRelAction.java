package com.hhz.ump.web.bid;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupOtherRelManager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSupOtherRel;

public class BidSupOtherRelAction extends CrudActionSupport<BidSupOtherRel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5275954598379983843L;
	@Autowired
	protected BidSupOtherRelManager bidSupOtherRelManager;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	
	private Page<BidSupOtherRel> bidSupOtherRelRs;
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 标段
	 */
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
		bidSupOtherRelRs = new Page<BidSupOtherRel>();
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		List<PropertyFilter> criterions = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter filter;

		// 供应商
		if (StringUtils.isNotBlank(this.getBidSupId())) {
			filter = new PropertyFilter("EQA_bidSup.bidSupId", this.getBidSupId());
			criterions.add(filter);
		}
		//轮次号
		String batchNo=Struts2Utils.getParameter("batchno");
		if(StringUtils.isNotBlank(batchNo)){
			if(StringUtils.isNumeric(batchNo)){
				filter=new PropertyFilter("EQA_batchNo",Long.parseLong(batchNo));
				criterions.add(filter);
			}
		}
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("rowNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",rowNo");
			page.setOrder(order + "," + Page.ASC);
		}
		bidSupOtherRelRs = bidSupOtherRelManager.findPage(page, criterions);
		//搜索标段信息
		bidLedger=bidLedgerManager.getBidLedgerByBidSup(this.getBidSupId());
		return "list";
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

	@Override
	public BidSupOtherRel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<BidSupOtherRel> getBidSupOtherRelRs() {
		return bidSupOtherRelRs;
	}

	public void setBidSupOtherRelRs(Page<BidSupOtherRel> bidSupOtherRelRs) {
		this.bidSupOtherRelRs = bidSupOtherRelRs;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
	

}
