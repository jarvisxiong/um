package com.hhz.ump.web.bid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisitonCustManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;

@Namespace("/bid")
public class BidDivisitonAction extends CrudActionSupport<BidDivisiton> {

	private static final long serialVersionUID = 2963507814147599888L;

	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidDivisitonCustManager bidDivisitonCustManager;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	/**
	 * 分部分项列表
	 */
	protected Page<BidDivisiton> bidDivisitonRs;
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
	 * 供应商,工程,批次搜索汇总值
	 */
	private List <PerSupTotalVo> perSupTotalVoList;
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
	 * 根据轮次和工程搜索工程清单
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {
		bidDivisitonRs = new Page<BidDivisiton>();
		//顺序
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		//页码
		String pageNo = Struts2Utils.getParameter("page");
		//每页行数
		String rows = Struts2Utils.getParameter("rows");
		//轮次
		String batchNo = Struts2Utils.getParameter("batchno");
		//搜索条件
		List<PropertyFilter> criterions = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter filter;
		// 工程
		if (StringUtils.isNotBlank(this.getProjectId())) {
			filter = new PropertyFilter("EQA_bidProject.bidProjectId", this.getProjectId());
			criterions.add(filter);
		}
		if(StringUtils.isNotBlank(batchNo)){
			filter = new PropertyFilter("EQA_batchNo", Long.parseLong(batchNo));
			criterions.add(filter);
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
		//执行搜索
		bidDivisitonRs = bidDivisitonManager.findPage(page, criterions);

		return "list";
	}

	/**
	 * 
	 * 导入工程汇总搜索
	 */
	public String importTotal() {

		//获取搜索条件
		//轮次
		String batchNo = Struts2Utils.getParameter("batchno");
		//供应商
		String bidSupId = Struts2Utils.getParameter("bidSupId");
		//工程
		String bidProjectId = Struts2Utils.getParameter("projectId");
		//搜索供应商
		BidSup bidSup=bidSupManager.getEntity(bidSupId);
		//准备统计搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidSupId", bidSupId);
		map.put("bidProjectId", bidProjectId);
		map.put("batchNo", batchNo);
		//执行搜索
		List<Object[]> rs = bidDivisitonManager.getTotalBySupAndProject(map);
		if (rs != null && rs.size() > 0) {
			Object[] o = rs.get(0);
			PerSupTotalVo perSupTotalVo;
			perSupTotalVoList=new ArrayList<PerSupTotalVo>();
			//根据结果循环构建显示VO
			for (int i = 0; i < 8; i++) {
				switch (i) {
				case 0:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("分部分项");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					//perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					//			.add(bidDivisitonCustManager.getTotalCust(bidSupId, bidProjectId, batchNo)));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 1:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("措施项目1");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 2:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("措施项目2");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 3:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("其他项目");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 4:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("零星工程");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 5:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("规费项目");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 6:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("税金项目");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				case 7:
					perSupTotalVo = new PerSupTotalVo();
					perSupTotalVo.setLineNo(i + 1);
					perSupTotalVo.setTableName("标段其他费用");
					perSupTotalVo.setTotalValue(((BigDecimal) o[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
					perSupTotalVoList.add(perSupTotalVo);
					continue;
				}
			}
		}
		//搜索标段信息
		bidLedger=bidLedgerManager.getBidLedgerByBidSup(this.getBidSupId());
		return "total";
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
	public BidDivisiton getModel() {
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

	public Page<BidDivisiton> getBidDivisitonRs() {
		return bidDivisitonRs;
	}

	public void setBidDivisitonRs(Page<BidDivisiton> bidDivisitonRs) {
		this.bidDivisitonRs = bidDivisitonRs;
	}

	public List<PerSupTotalVo> getPerSupTotalVoList() {
		return perSupTotalVoList;
	}

	public void setPerSupTotalVoList(List<PerSupTotalVo> perSupTotalVoList) {
		this.perSupTotalVoList = perSupTotalVoList;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
	
	

}
