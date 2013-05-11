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
import com.hhz.ump.dao.bid.BidMeasureSupRel2Manager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidMeasureSupRel2;

public class BidMeasureSupRel2Action extends
		CrudActionSupport<BidMeasureSupRel2> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6491605141679107367L;

	@Autowired
	protected BidMeasureSupRel2Manager bidMeasureSupRel2Manager;
	private Page<BidMeasureSupRel2> bidMeasureSupRel2Rs;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String projectId;
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
		bidMeasureSupRel2Rs = new Page<BidMeasureSupRel2>();
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		List<PropertyFilter> criterions = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter filter;

		// 工程
		if (StringUtils.isNotBlank(this.getProjectId())) {
			filter = new PropertyFilter("EQA_bidProject.bidProjectId", this
					.getProjectId());
			criterions.add(filter);
		}
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
		bidMeasureSupRel2Rs = bidMeasureSupRel2Manager.findPage(page, criterions);
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
	public BidMeasureSupRel2 getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<BidMeasureSupRel2> getBidMeasureSupRel2Rs() {
		return bidMeasureSupRel2Rs;
	}

	public void setBidMeasureSupRel2Rs(Page<BidMeasureSupRel2> bidMeasureSupRel2Rs) {
		this.bidMeasureSupRel2Rs = bidMeasureSupRel2Rs;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
	
	

}
