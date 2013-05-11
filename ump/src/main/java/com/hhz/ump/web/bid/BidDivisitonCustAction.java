/**  
 * BidDivisitonCustAction.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-10-24        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.bid;  

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisitonCustManager;
import com.hhz.ump.entity.bid.BidDivisitonCust;

/**  
 * ClassName:BidDivisitonCustAction  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-10-24        下午12:53:28  
 *  
 *  
 */
@Namespace("/bid")
public class BidDivisitonCustAction extends CrudActionSupport<BidDivisitonCust>  {

	/**  
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）  
	 *  
	 * @since Ver 1.1  
	 */  
	
	private static final long serialVersionUID = -3016929305662488532L;
	
	@Autowired
	protected BidDivisitonCustManager bidDivisitonCustManager;

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
		  
		// TODO Auto-generated method stub  
		return null;  
		
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
	public BidDivisitonCust getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}
	
	/**
	 * 
	 * totalDifference:(增添项偏差汇总表)
	 *  
	 * @param  @return    设定文件  
	 * @return String    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public String totalDifference(){
		String bidProjectId=Struts2Utils.getParameter("projectId");
		String bidSupId=Struts2Utils.getParameter("bidSupId");
		String batchno=Struts2Utils.getParameter("batchno");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidSupId", bidSupId); 
		map.put("bidProjectId", bidProjectId); 
		map.put("batchNo", Long.parseLong(batchno)); 
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		StringBuffer sbSql=new StringBuffer()
		.append(" from BidDivisitonCust bdc where bdc.bidSup.bidSupId = :bidSupId ")
		.append(" and bdc.bidProject.bidProjectId = :bidProjectId")
		.append(" and bdc.batchNo = :batchNo");		
		
		page.setOrderBy("rowNo");
		page.setOrder(Page.ASC);
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} else {
			page.setPageSize(10);
		}
		page=bidDivisitonCustManager.findPage(page, sbSql.toString(), map);//.find(sbSql.toString(), map);
		return "totalDifference";
	}

}
  
