package com.hhz.ump.web.bid;

import java.util.List;

public class BidSupAttachRelVo   implements java.io.Serializable {
	 private String bidSupId;
     private String batchNo;
     private String attaBizFlg;
     private String attaTechFlg;
     private String bidSupAttachRelId;
     private List<AppAttachFileVo> attachFiles;


	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getAttaBizFlg() {
		return attaBizFlg;
	}
	public void setAttaBizFlg(String attaBizFlg) {
		this.attaBizFlg = attaBizFlg;
	}
	public String getAttaTechFlg() {
		return attaTechFlg;
	}
	public void setAttaTechFlg(String attaTechFlg) {
		this.attaTechFlg = attaTechFlg;
	}
	public String getBidSupAttachRelId() {
		return bidSupAttachRelId;
	}
	public void setBidSupAttachRelId(String bidSupAttachRelId) {
		this.bidSupAttachRelId = bidSupAttachRelId;
	}
	public List<AppAttachFileVo> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<AppAttachFileVo> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getBidSupId() {
		return bidSupId;
	}
	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}


     
     
	
     

}
