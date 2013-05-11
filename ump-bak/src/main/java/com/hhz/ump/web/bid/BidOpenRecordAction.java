package com.hhz.ump.web.bid;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidOpenRecordManager;
import com.hhz.ump.dao.bid.BidOpenRecordSupManager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidOpenRecord;

@Namespace("/bid")
public class BidOpenRecordAction extends CrudActionSupport<BidOpenRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BidOpenRecordManager bidOpenRecordManager;
	@Autowired
	private BidOpenRecordSupManager bidOpenRecordSupManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;
	
	

	private String id;
	private BidOpenRecord entity;
	
	private String bidLedgerId;
	
	private Long inputBatchNo;
	
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
		if(StringUtils.isBlank(id)) {
			entity = new BidOpenRecord();	
			
		}else{
			entity = bidOpenRecordManager.getEntity(id);
		}
		
	}

	@Override
	public String save() throws Exception {
		try{
		bidOpenRecordManager.saveBidOpenRecord(entity);
		Struts2Utils.renderText("success");
		}catch (Exception e) {
			Struts2Utils.renderText(e.getMessage());
		}
		return null;
	}

	@Override
	public BidOpenRecord getModel() {
		return entity;
	}
	
	public String openRecordView(){
		entity = bidOpenRecordManager.getByBidLedgerIdAndBatchNo(bidLedgerId, inputBatchNo);
		return "openRecordView";
	}
   
	public String openRecord(){
		BidLedger bidLedger = bidLedgerManager.getEntity(bidLedgerId);
		inputBatchNo = bidLedger.getBatchNo();
		return "openRecord";
	}
	

	public Long getInputBatchNo() {
		return inputBatchNo;
	}

	public void setInputBatchNo(Long inputBatchNo) {
		this.inputBatchNo = inputBatchNo;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public BidOpenRecord getEntity() {
		return entity;
	}

	public void setEntity(BidOpenRecord entity) {
		this.entity = entity;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	
}
