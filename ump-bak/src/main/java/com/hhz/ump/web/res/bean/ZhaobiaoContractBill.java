package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseContractTemplate;

/**招标合同
 * @author RS
 *
 */
public class ZhaobiaoContractBill extends BaseContractTemplate {

	private String sideA;// 甲方

	private String sideB;// 乙方
	
	private String sideC;// 丙方 -Add for part C by zhuxj on 2012.3.31 

	private String signerA;// 甲方签约人

	private String signerB;// 乙方签约人
	
	private String signerC;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31 

	private String isInvite;// 招标

	private String isDirect;// 直接委托

	private String isCompetitive;// 竞争性谈判

	private String inviteNo;// 标书编号

	private String directReason;// 理由

	private String conContent;// 备注

	private String conPrice;// 合同价款

	private String conPayMode;// 付款方式

	private String section;// 标段

	private String timeLimit;// 工期

	private String scope;// 范围/数量

	public String getSideA() {
		return sideA;
	}

	public void setSideA(String sideA) {
		this.sideA = sideA;
	}

	public String getSideB() {
		return sideB;
	}

	public void setSideB(String sideB) {
		this.sideB = sideB;
	}

	public String getSignerA() {
		return signerA;
	}

	public void setSignerA(String signerA) {
		this.signerA = signerA;
	}

	public String getSignerB() {
		return signerB;
	}

	public void setSignerB(String signerB) {
		this.signerB = signerB;
	}

	public String getSideC() {
		return sideC;
	}

	public void setSideC(String sideC) {
		this.sideC = sideC;
	}

	public String getSignerC() {
		return signerC;
	}

	public void setSignerC(String signerC) {
		this.signerC = signerC;
	}

	public String getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}

	public String getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

	public String getInviteNo() {
		return inviteNo;
	}

	public void setInviteNo(String inviteNo) {
		this.inviteNo = inviteNo;
	}

	public String getDirectReason() {
		return directReason;
	}

	public void setDirectReason(String directReason) {
		this.directReason = directReason;
	}

	public String getConContent() {
		return conContent;
	}

	public void setConContent(String conContent) {
		this.conContent = conContent;
	}

	public String getConPrice() {
		return conPrice;
	}

	public void setConPrice(String conPrice) {
		this.conPrice = conPrice;
	}

	public String getConPayMode() {
		return conPayMode;
	}

	public void setConPayMode(String conPayMode) {
		this.conPayMode = conPayMode;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getIsCompetitive() {
		return isCompetitive;
	}

	public void setIsCompetitive(String isCompetitive) {
		this.isCompetitive = isCompetitive;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return getProjectCd();
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return getProjectName();
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		if (getContactName()!=null)
			return getContactName()+"合同";
		return null;
	}

}
