/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 证照办理确认单
 * 
 */
public class CertTranReqConfirmSheet extends BaseTemplatePay {
	
	//	项目,如下
	//	设立 selectSetUp
	//	基本信息变更(名称、地址、法人或负责人、类型、注册资金、经营范围、其他)selectBaseChange
	//	注销 selectRevoke
	//	任职变更(董事长、总经理、股东、董事、监事)   selectPositionChange
	//	资质(新办、年检、升级) selectAptitude
	//	项目手册(新办、年检) selectProjectManual

	//	申请内容(详细内容附后)reqDesc
	//	签名：reqSignName,reqSignCd
	//	日期： 年 月 日  reqSignDate
		
	//	需明确的事项,如下
	//	公司名称  companyName 公司性质 内资 domesticFund 外资foreignFund	
	//	备用名称1	backupName1	注册资本       regFuncAmt
	//	备用名称2	backupName2	法定代表人 legalReprDesc
	//	经营范围	 	bizScopDesc
	//	股东及持股比例	sotckHolderRate
	//	董事会成员或董事成员(6个)	
	//	boardMember1Name,boardMember2Name,boardMember3Name,boardMember4Name,boardMember5Name,boardMember6Name
	//	boardMember1Cd,boardMember2Cd,boardMember3Cd,boardMember4Cd,boardMember5Cd,boardMember6Cd
	//	监事	chiefSupervisorName
	//	注册地址	regAddrDesc
	//	其他要求	otherReqDesc
	

	private String selectSetUp;
	private String selectBaseChange;
	private String selectRevoke;
	private String selectPositionChange;
	private String selectAptitude;
	private String selectProjectManual;
 

	private String reqDesc;
	private String reqSignName;
	private String reqSignCd;
	private String reqSignDate;
	
	private String companyName;
	private String domesticFund;
	private String foreignFund;
	private String backupName1;
	private String regFuncAmt;
	private String backupName2;
	private String legalReprDesc;
	private String bizScopDesc;
	private String sotckHolderRate;
	
	private String boardMember1Name;
	private String boardMember2Name;
	private String boardMember3Name;
	private String boardMember4Name;
	private String boardMember5Name;
	private String boardMember6Name;
	private String boardMember1Cd;
	private String boardMember2Cd;
	private String boardMember3Cd;
	private String boardMember4Cd;
	private String boardMember5Cd;
	private String boardMember6Cd;
	
	private String chiefSupervisorName;
	private String regAddrDesc;
	private String otherReqDesc;
	
	public String getSelectSetUp() {
		return selectSetUp;
	}
	public void setSelectSetUp(String selectSetUp) {
		this.selectSetUp = selectSetUp;
	}
	public String getSelectBaseChange() {
		return selectBaseChange;
	}
	public void setSelectBaseChange(String selectBaseChange) {
		this.selectBaseChange = selectBaseChange;
	}
	public String getSelectRevoke() {
		return selectRevoke;
	}
	public void setSelectRevoke(String selectRevoke) {
		this.selectRevoke = selectRevoke;
	}
	public String getSelectPositionChange() {
		return selectPositionChange;
	}
	public void setSelectPositionChange(String selectPositionChange) {
		this.selectPositionChange = selectPositionChange;
	}
	public String getSelectAptitude() {
		return selectAptitude;
	}
	public void setSelectAptitude(String selectAptitude) {
		this.selectAptitude = selectAptitude;
	}
	public String getSelectProjectManual() {
		return selectProjectManual;
	}
	public void setSelectProjectManual(String selectProjectManual) {
		this.selectProjectManual = selectProjectManual;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getReqSignName() {
		return reqSignName;
	}
	public void setReqSignName(String reqSignName) {
		this.reqSignName = reqSignName;
	}
	public String getReqSignCd() {
		return reqSignCd;
	}
	public void setReqSignCd(String reqSignCd) {
		this.reqSignCd = reqSignCd;
	}
	public String getReqSignDate() {
		return reqSignDate;
	}
	public void setReqSignDate(String reqSignDate) {
		this.reqSignDate = reqSignDate;
	}
	@Override
	public String getCompanyName() {
		return companyName;
	}
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDomesticFund() {
		return domesticFund;
	}
	public void setDomesticFund(String domesticFund) {
		this.domesticFund = domesticFund;
	}
	public String getForeignFund() {
		return foreignFund;
	}
	public void setForeignFund(String foreignFund) {
		this.foreignFund = foreignFund;
	}
	public String getBackupName1() {
		return backupName1;
	}
	public void setBackupName1(String backupName1) {
		this.backupName1 = backupName1;
	}
	public String getRegFuncAmt() {
		return regFuncAmt;
	}
	public void setRegFuncAmt(String regFuncAmt) {
		this.regFuncAmt = regFuncAmt;
	}
	public String getBackupName2() {
		return backupName2;
	}
	public void setBackupName2(String backupName2) {
		this.backupName2 = backupName2;
	}
	public String getLegalReprDesc() {
		return legalReprDesc;
	}
	public void setLegalReprDesc(String legalReprDesc) {
		this.legalReprDesc = legalReprDesc;
	}
	public String getBizScopDesc() {
		return bizScopDesc;
	}
	public void setBizScopDesc(String bizScopDesc) {
		this.bizScopDesc = bizScopDesc;
	}
	public String getSotckHolderRate() {
		return sotckHolderRate;
	}
	public void setSotckHolderRate(String sotckHolderRate) {
		this.sotckHolderRate = sotckHolderRate;
	}
	public String getBoardMember1Name() {
		return boardMember1Name;
	}
	public void setBoardMember1Name(String boardMember1Name) {
		this.boardMember1Name = boardMember1Name;
	}
	public String getBoardMember2Name() {
		return boardMember2Name;
	}
	public void setBoardMember2Name(String boardMember2Name) {
		this.boardMember2Name = boardMember2Name;
	}
	public String getBoardMember3Name() {
		return boardMember3Name;
	}
	public void setBoardMember3Name(String boardMember3Name) {
		this.boardMember3Name = boardMember3Name;
	}
	public String getBoardMember4Name() {
		return boardMember4Name;
	}
	public void setBoardMember4Name(String boardMember4Name) {
		this.boardMember4Name = boardMember4Name;
	}
	public String getBoardMember5Name() {
		return boardMember5Name;
	}
	public void setBoardMember5Name(String boardMember5Name) {
		this.boardMember5Name = boardMember5Name;
	}
	public String getBoardMember6Name() {
		return boardMember6Name;
	}
	public void setBoardMember6Name(String boardMember6Name) {
		this.boardMember6Name = boardMember6Name;
	}
	public String getBoardMember1Cd() {
		return boardMember1Cd;
	}
	public void setBoardMember1Cd(String boardMember1Cd) {
		this.boardMember1Cd = boardMember1Cd;
	}
	public String getBoardMember2Cd() {
		return boardMember2Cd;
	}
	public void setBoardMember2Cd(String boardMember2Cd) {
		this.boardMember2Cd = boardMember2Cd;
	}
	public String getBoardMember3Cd() {
		return boardMember3Cd;
	}
	public void setBoardMember3Cd(String boardMember3Cd) {
		this.boardMember3Cd = boardMember3Cd;
	}
	public String getBoardMember4Cd() {
		return boardMember4Cd;
	}
	public void setBoardMember4Cd(String boardMember4Cd) {
		this.boardMember4Cd = boardMember4Cd;
	}
	public String getBoardMember5Cd() {
		return boardMember5Cd;
	}
	public void setBoardMember5Cd(String boardMember5Cd) {
		this.boardMember5Cd = boardMember5Cd;
	}
	public String getBoardMember6Cd() {
		return boardMember6Cd;
	}
	public void setBoardMember6Cd(String boardMember6Cd) {
		this.boardMember6Cd = boardMember6Cd;
	}
	public String getChiefSupervisorName() {
		return chiefSupervisorName;
	}
	public void setChiefSupervisorName(String chiefSupervisorName) {
		this.chiefSupervisorName = chiefSupervisorName;
	}
	public String getRegAddrDesc() {
		return regAddrDesc;
	}
	public void setRegAddrDesc(String regAddrDesc) {
		this.regAddrDesc = regAddrDesc;
	}
	public String getOtherReqDesc() {
		return otherReqDesc;
	}
	public void setOtherReqDesc(String otherReqDesc) {
		this.otherReqDesc = otherReqDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
