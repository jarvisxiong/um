package com.hhz.ump.web.bid;

import java.util.Date;
import java.util.List;

import com.hhz.ump.entity.bid.BidSupAttachRel;


public class BidSupVo  implements java.io.Serializable {
 
	private String bidLedgerId;//标段ID
	private String refBidSupId;//refBidSupId
	private String bidSupId;// 投标单位Id
	private String bidSupName;// 投标单位名称
	private String linkMan;// 联系人
	private String linkDesc;// 联系方式
	private String email;//电子邮件
	
	private String supLevelCd;// 供方级别
	private String totalBatchCount;// 报价次数
	private String lastItemAmt;// 分部分项报价
	private String lastMeasureAmt;// 措施项目报价
	private String lastTotalAmt;// 合计

	private String supAttachFileFlg;// "投标单位"附件
	private String guranteeStatusCd;// 是否上传保证金
	private String techAttachFlg;// "技术标"附件
	private String receiveStatusCd;// 应标状态
	private String typeCd;//是否标底1、供应商2、标底单位
	private String bidVisibleFlg;//邀请类型
	private String supUserCd;//官网[供应商登录账号]

	
	private String refundStatusCd;//是否退款 
	private Date refundDate;//退款日期
	private String supBidStatusCd;//是否中标
	private Date bidDate;//中标日期
	
	

	private String supAttachFileFlgName;
	private String guranteeStatusCdName;
	private String techAttachFlgName;
	private String receiveStatusCdName;
	private String displayNo;//显示号
	private List<ContactorVo> contactors;
	//投标轮次明细列表
	private List<BidSupAttachRel> bussinessBids;
	
	 private String guarAttaConfFlg;
     private String guarAttaConfName;
     private String guarAttaConfUiid;
     private Date guarAttaConfDate;
     private Date receiveDate;
     

	public String getBidSupId() {
		return bidSupId;
	}
	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}
	public String getBidSupName() {
		return bidSupName;
	}
	public void setBidSupName(String bidSupName) {
		this.bidSupName = bidSupName;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkDesc() {
		return linkDesc;
	}
	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSupLevelCd() {
		return supLevelCd;
	}
	public void setSupLevelCd(String supLevelCd) {
		this.supLevelCd = supLevelCd;
	}
	public String getTotalBatchCount() {
		return totalBatchCount;
	}
	public void setTotalBatchCount(String totalBatchCount) {
		this.totalBatchCount = totalBatchCount;
	}
	public String getLastItemAmt() {
		return lastItemAmt;
	}
	public void setLastItemAmt(String lastItemAmt) {
		this.lastItemAmt = lastItemAmt;
	}
	public String getLastMeasureAmt() {
		return lastMeasureAmt;
	}
	public void setLastMeasureAmt(String lastMeasureAmt) {
		this.lastMeasureAmt = lastMeasureAmt;
	}
	public String getLastTotalAmt() {
		return lastTotalAmt;
	}
	public void setLastTotalAmt(String lastTotalAmt) {
		this.lastTotalAmt = lastTotalAmt;
	}
	public String getGuranteeStatusCd() {
		return guranteeStatusCd;
	}
	public void setGuranteeStatusCd(String guranteeStatusCd) {
		this.guranteeStatusCd = guranteeStatusCd;
	}
	public String getReceiveStatusCd() {
		return receiveStatusCd;
	}
	public void setReceiveStatusCd(String receiveStatusCd) {
		this.receiveStatusCd = receiveStatusCd;
	}
	public String getSupAttachFileFlg() {
		return supAttachFileFlg;
	}
	public void setSupAttachFileFlg(String supAttachFileFlg) {
		this.supAttachFileFlg = supAttachFileFlg;
	}
	public String getTechAttachFlg() {
		return techAttachFlg;
	}
	public void setTechAttachFlg(String techAttachFlg) {
		this.techAttachFlg = techAttachFlg;
	}
	public String getBidLedgerId() {
		return bidLedgerId;
	}
	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}
	public String getSupAttachFileFlgName() {
		return supAttachFileFlgName;
	}
	public void setSupAttachFileFlgName(String supAttachFileFlgName) {
		this.supAttachFileFlgName = supAttachFileFlgName;
	}
	public String getGuranteeStatusCdName() {
		return guranteeStatusCdName;
	}
	public void setGuranteeStatusCdName(String guranteeStatusCdName) {
		this.guranteeStatusCdName = guranteeStatusCdName;
	}
	public String getTechAttachFlgName() {
		return techAttachFlgName;
	}
	public void setTechAttachFlgName(String techAttachFlgName) {
		this.techAttachFlgName = techAttachFlgName;
	}
	public String getReceiveStatusCdName() {
		return receiveStatusCdName;
	}
	public void setReceiveStatusCdName(String receiveStatusCdName) {
		this.receiveStatusCdName = receiveStatusCdName;
	}
	public String getBidVisibleFlg() {
		return bidVisibleFlg;
	}
	public void setBidVisibleFlg(String bidVisibleFlg) {
		this.bidVisibleFlg = bidVisibleFlg;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getSupUserCd() {
		return supUserCd;
	}
	public void setSupUserCd(String supUserCd) {
		this.supUserCd = supUserCd;
	}
	public String getRefundStatusCd() {
		return refundStatusCd;
	}
	public void setRefundStatusCd(String refundStatusCd) {
		this.refundStatusCd = refundStatusCd;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getSupBidStatusCd() {
		return supBidStatusCd;
	}
	public void setSupBidStatusCd(String supBidStatusCd) {
		this.supBidStatusCd = supBidStatusCd;
	}
	public Date getBidDate() {
		return bidDate;
	}
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
	public String getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(String displayNo) {
		this.displayNo = displayNo;
	}
	public List<ContactorVo> getContactors() {
		return contactors;
	}
	public void setContactors(List<ContactorVo> contactors) {
		this.contactors = contactors;
	}
	public String getRefBidSupId() {
		return refBidSupId;
	}
	public void setRefBidSupId(String refBidSupId) {
		this.refBidSupId = refBidSupId;
	}
	public List<BidSupAttachRel> getBussinessBids() {
		return bussinessBids;
	}
	public void setBussinessBids(List<BidSupAttachRel> bussinessBids) {
		this.bussinessBids = bussinessBids;
	}
	public String getGuarAttaConfFlg() {
		return guarAttaConfFlg;
	}
	public void setGuarAttaConfFlg(String guarAttaConfFlg) {
		this.guarAttaConfFlg = guarAttaConfFlg;
	}
	public String getGuarAttaConfName() {
		return guarAttaConfName;
	}
	public void setGuarAttaConfName(String guarAttaConfName) {
		this.guarAttaConfName = guarAttaConfName;
	}
	public String getGuarAttaConfUiid() {
		return guarAttaConfUiid;
	}
	public void setGuarAttaConfUiid(String guarAttaConfUiid) {
		this.guarAttaConfUiid = guarAttaConfUiid;
	}
	public Date getGuarAttaConfDate() {
		return guarAttaConfDate;
	}
	public void setGuarAttaConfDate(Date guarAttaConfDate) {
		this.guarAttaConfDate = guarAttaConfDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	
	
}
