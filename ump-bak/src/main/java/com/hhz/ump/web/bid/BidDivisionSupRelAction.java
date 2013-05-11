package com.hhz.ump.web.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisionSupRelManager;
import com.hhz.ump.dao.bid.BidDivisitonCustManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.entity.bid.BidDivisionSupRel;
import com.hhz.ump.entity.bid.BidLedger;

@Namespace("/bid")
public class BidDivisionSupRelAction extends CrudActionSupport<BidDivisionSupRel> {

	private static final long serialVersionUID = -4604660464526916561L;

	@Autowired
	protected BidDivisionSupRelManager bidDivisionSupRelManager;
	@Autowired
	protected BidDivisitonCustManager bidDivisitonCustManager;
	@Autowired
	protected BidLedgerManager bidLedgerManager;

	/**
	 * 分部分项列表
	 */
	protected List<BidDivisitonVo> bidDivisionSupRelRs;
	/**
	 * 标段ID
	 */
	private String bidLedgerId;
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String projectId;
	/**
	 * 批号
	 */
	private String batchno;
	/**
	 * 分页搜索
	 */
	private Page voPage = new Page(10);
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
	
	/**
	 * 按照供应商、工程、轮次搜索分部分项
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {		
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * if(this.getBatchno()!=null){ map.put("batchNo", this.getBatchno()); }
		 * if(this.getBidSupId()!=null){ map.put("bidSupId",
		 * this.getBidSupId()); } if(this.getProjectId()!=null){
		 * map.put("bidProjectId", this.getProjectId()); }
		 */
		
		StringBuffer sql=new StringBuffer();
		/*sql.append("select tmp.id,tmp.item_no,tmp.item_name,tmp.item_desc,tmp.unit_desc,tmp.quantitie,tmp.comp_unit_amt,tmp.total_amt,tmp.row_no from ")
	    .append(" (select bdsr.bid_division_sup_rel_id as id, bd.item_no,bd.item_name,bd.item_desc,bd.unit_desc,bdsr.quantitie,")
	    .append(" bdsr.comp_unit_amt,bdsr.total_amt,bdsr.row_no from bid_division_sup_rel bdsr join ")
	    .append(" bid_divisiton bd on bd.item_no=bdsr.item_no and bd.bid_project_id = :bidProjectId and bd.batch_no = :batchNo")
	    .append(" join bid_project bp on bp.bid_project_id=bd.bid_project_id")
	    .append(" where bdsr.bid_sup_id = :bidSupId")
	    .append(" and bp.bid_project_id = :bidProjectId and bdsr.batch_no = :batchNo")
	    .append(" union ")
	    .append(" select  bdc.bid_divisiton_cust_id as id ,bdc.item_no,bdc.item_name,bdc.item_desc,bdc.unit_desc,bdc.quantitie,")
	    .append(" bdc.comp_unit_amt,bdc.total_amt,bdc.row_no from ")
	    .append("  bid_divisiton_cust bdc where bdc.bid_sup_id = :bidSupId and bdc.bid_project_id =  :bidProjectId ")
	    .append(" and bdc.batch_no = :batchNo) tmp order by tmp.row_no asc");*/
		sql.append("select tmp.id,tmp.item_no,tmp.item_name,tmp.item_desc,tmp.unit_desc,tmp.quantitie,tmp.comp_unit_amt,tmp.total_amt,tmp.row_no from ")
		.append(" (select bdsr.bid_division_sup_rel_id as id, bd.item_no,bd.item_name,bd.item_desc,bd.unit_desc,bd.quantitie,")
		.append(" bdsr.comp_unit_amt,bdsr.total_amt,bdsr.row_no from bid_division_sup_rel bdsr join ")
		.append(" bid_divisiton bd on bd.item_no=bdsr.item_no and bd.bid_project_id = :bidProjectId and bd.batch_no = :batchNo")
		.append(" /**join bid_project bp on bp.bid_project_id=bd.bid_project_id**/")
		.append(" where bdsr.bid_sup_id = :bidSupId")
		.append(" and bdsr.bid_project_id = :bidProjectId and bdsr.batch_no = :batchNo")
		.append(" union ")
		.append(" select  bdc.bid_divisiton_cust_id as id ,bdc.item_no,bdc.item_name,bdc.item_desc,bdc.unit_desc,bdc.quantitie,")
		.append(" bdc.comp_unit_amt,bdc.total_amt,bdc.row_no from ")
		.append("  bid_divisiton_cust bdc where bdc.bid_sup_id = :bidSupId and bdc.bid_project_id =  :bidProjectId ")
		.append(" and bdc.batch_no = :batchNo) tmp order by tmp.row_no asc");

	    // 工程
		map.put("bidProjectId", this.getProjectId());
		// 供应商
		map.put("bidSupId", this.getBidSupId());
		// 轮次号
		String batchNo = Struts2Utils.getParameter("map");
		map.put("batchNo", Long.parseLong(this.getBatchno()));
		
		/*StringBuffer hql = new StringBuffer();
		hql.append(" from BidDivisionSupRel as bdsr");
		hql.append(" where  1=1 ");

		// 工程
		map.put("bidProjectId", this.getProjectId());
		hql.append(" and bdsr.bidDivisiton.bidProject.bidProjectId =:bidProjectId");

		// 供应商
		map.put("bidSupId", this.getBidSupId());
		hql.append(" and bdsr.bidSup.bidSupId = :bidSupId");

		// 轮次号
		String batchNo = Struts2Utils.getParameter("map");
		map.put("batchNo", Long.parseLong(this.getBatchno()));
		hql.append(" and bdsr.batchNo = :batchNo");*/

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		}
		
		voPage = bidDivisitonCustManager.findPageSql(voPage, sql.toString(), map,  new HashMap<String,Class>());
		Object[] obj = null;
		BidDivisitonVo bidDivisitonVo;	
		List<Object[] > rs = voPage.getResult();
		//搜索结果转换为vo
		bidDivisionSupRelRs=new ArrayList<BidDivisitonVo>();
		for(int i=0;i<rs.size();i++){
			obj=rs.get(i);
			bidDivisitonVo=new BidDivisitonVo();
			bidDivisitonVo.setItemNo((String)obj[1]);
			bidDivisitonVo.setItemName((String)obj[2]);
			bidDivisitonVo.setItemDesc((String)obj[3]);
			bidDivisitonVo.setMeasurement((String)obj[4]);
			bidDivisitonVo.setQuantitie((String)obj[5]);
			bidDivisitonVo.setCompUnitAmt((String)obj[6]);
			bidDivisitonVo.setTotalAmt((String)obj[7]);
			bidDivisionSupRelRs.add(bidDivisitonVo);
		}
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
	public BidDivisionSupRel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
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

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public List<BidDivisitonVo> getBidDivisionSupRelRs() {
		return bidDivisionSupRelRs;
	}

	public void setBidDivisionSupRelRs(List<BidDivisitonVo> bidDivisionSupRelRs) {
		this.bidDivisionSupRelRs = bidDivisionSupRelRs;
	}

	public Page getVoPage() {
		return voPage;
	}

	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}


	
	
	
	
	

}
