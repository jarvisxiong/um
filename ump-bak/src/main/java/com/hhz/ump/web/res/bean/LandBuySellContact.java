package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//土地买卖租赁合同审批表
public class LandBuySellContact extends BaseTemplate {
	private String titleName;
    private String partyA;//甲方
	
	private String partyASigning;//甲方签约人
	
	private String partyB;//乙方
	
	private String partyBSigning;//乙方签约人
	
	private String partyC;//丙方-Add for part C by zhuxj on 2012.3.31
	
	private String partyCSigning;//丙方签约人-Add for part C by zhuxj on 2012.3.31
	
	private String bidding;//招标
	
	private String biddingNum;//标书编号
	
	private String entrust;//直接委托
	
	private String entrustReason;//委托理由
	
	private String mainContents;//主要内容
	
	private String contractPrice;//合同价款
	
	private String priceWay;//付款方式 

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return titleName;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyASigning() {
		return partyASigning;
	}

	public void setPartyASigning(String partyASigning) {
		this.partyASigning = partyASigning;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getPartyBSigning() {
		return partyBSigning;
	}

	public String getPartyC() {
		return partyC;
	}

	public void setPartyC(String partyC) {
		this.partyC = partyC;
	}

	public String getPartyCSigning() {
		return partyCSigning;
	}

	public void setPartyCSigning(String partyCSigning) {
		this.partyCSigning = partyCSigning;
	}

	public void setPartyBSigning(String partyBSigning) {
		this.partyBSigning = partyBSigning;
	}

	public String getBidding() {
		return bidding;
	}

	public void setBidding(String bidding) {
		this.bidding = bidding;
	}

	public String getBiddingNum() {
		return biddingNum;
	}

	public void setBiddingNum(String biddingNum) {
		this.biddingNum = biddingNum;
	}

	public String getEntrust() {
		return entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public String getEntrustReason() {
		return entrustReason;
	}

	public void setEntrustReason(String entrustReason) {
		this.entrustReason = entrustReason;
	}

	public String getMainContents() {
		return mainContents;
	}

	public void setMainContents(String mainContents) {
		this.mainContents = mainContents;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getPriceWay() {
		return priceWay;
	}

	public void setPriceWay(String priceWay) {
		this.priceWay = priceWay;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
    
}
