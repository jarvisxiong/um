package com.hhz.ump.web.bid;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidItemSupRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.entity.bid.BidItemSupRel;
import com.hhz.ump.entity.bid.BidLedger;


public class BidItemSupRelAction  extends CrudActionSupport<BidItemSupRel>{

	
	private static final long serialVersionUID = 9202679406600789841L;
	@Autowired
	protected  BidItemSupRelManager bidItemSupRelManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;
	
	private BidItemSupRel entity; 
	/**
	 * 
	 */
	private Page<BidItemSupRel> bidItemSupRelRs;	
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String projectId;
	/**
	 * 投标台帐ID
	 */
	private String bidLedgerId;
	
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
		bidItemSupRelRs=new Page<BidItemSupRel>();
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");		
		
		List<PropertyFilter> criterions = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter filter;			
		
		//工程
		if(StringUtils.isNotBlank(this.getProjectId())){
			filter=new PropertyFilter("EQA_bidProject.bidProjectId",this.getProjectId());				
			criterions.add(filter);
		}
		//供应商
		if(StringUtils.isNotBlank(this.getBidSupId())){
			filter=new PropertyFilter("EQA_bidSup.bidSupId",this.getBidSupId());
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
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		}
		bidItemSupRelRs = bidItemSupRelManager.findPage(page, criterions);
		return "list";
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity =bidItemSupRelManager.getEntity(getId());
		}else{
			entity = new BidItemSupRel();
		}
	}
	
	@Override
	public void prepareSave() {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = bidItemSupRelManager.getEntity(getId());

		} else {
			entity = new BidItemSupRel();
		}
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
			bidItemSupRelManager.saveBidItemSupRel(entity);
			BidLedger bidLedger = bidLedgerManager.getEntity(bidLedgerId);
			//如果修改的人不在评审人里，则添加
			if(bidLedger.getJudgeCd()==null||bidLedger.getJudgeCd().indexOf(SpringSecurityUtils.getCurrentUiid()+",")<0){
				String judgeCd;
				if(bidLedger.getJudgeCd()==null){
					judgeCd=SpringSecurityUtils.getCurrentUiid()+",";
				}else{
					judgeCd=bidLedger.getJudgeCd()+SpringSecurityUtils.getCurrentUiid()+",";
				}
				bidLedger.setJudgeCd(judgeCd);
			}
			bidLedgerManager.saveBidLedger(bidLedger);
			Struts2Utils.renderText("success");
		return null;
	}
	
	public void prepareModify() {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = bidItemSupRelManager.getEntity(getId());

		} else {
			entity = new BidItemSupRel();
		}
	}
	public String modify() throws Exception {
		bidItemSupRelManager.saveBidItemSupRel(entity);
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public BidItemSupRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public Page<BidItemSupRel> getBidItemSupRelRs() {
		return bidItemSupRelRs;
	}

	public void setBidItemSupRelRs(Page<BidItemSupRel> bidItemSupRelRs) {
		this.bidItemSupRelRs = bidItemSupRelRs;
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

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}
	
	
	

}
