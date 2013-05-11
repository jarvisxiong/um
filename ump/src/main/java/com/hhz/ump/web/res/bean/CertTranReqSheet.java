/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 证照办理需求单
 * 
 */
public class CertTranReqSheet extends BaseTemplatePay {
 
	//需求提出中心 reqCenterOrgCd,reqCenterOrgName
	//申请办理日期applyDate
	//类别选择,如下
	// 设立公司 selectSetUp
	// 注销公司 selectRevoke
	// 资质(新办、年检、升级) selectAptitude
	// 基本信息变更(名称、地址、法人或负责人、类型、注册资金、经营范围、其他) selectBaseChange
	// 任职变更(董事长、总经理、股东、董事、监事) selectPositionChange
	// 项目手册(新办、年检) selectProjectManual
	
	//证照办理需求说明(需列明相关会议决议、领导指示/批复等，可复印后作为附件提供) reqDesc

	
	private String titleName;
	private String reqCenterOrgCd;
	private String reqCenterOrgName;
	private String applyDate;
	private String selectSetUp;
	private String selectRevoke;
	private String selectAptitude;
	private String selectBaseChange;
	private String selectPositionChange;
	private String selectProjectManual;
	private String reqDesc;
	
	public String getReqCenterOrgCd() {
		return reqCenterOrgCd;
	}
	public void setReqCenterOrgCd(String reqCenterOrgCd) {
		this.reqCenterOrgCd = reqCenterOrgCd;
	}
	public String getReqCenterOrgName() {
		return reqCenterOrgName;
	}
	public void setReqCenterOrgName(String reqCenterOrgName) {
		this.reqCenterOrgName = reqCenterOrgName;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getSelectSetUp() {
		return selectSetUp;
	}
	public void setSelectSetUp(String selectSetUp) {
		this.selectSetUp = selectSetUp;
	}
	public String getSelectRevoke() {
		return selectRevoke;
	}
	public void setSelectRevoke(String selectRevoke) {
		this.selectRevoke = selectRevoke;
	}
	public String getSelectAptitude() {
		return selectAptitude;
	}
	public void setSelectAptitude(String selectAptitude) {
		this.selectAptitude = selectAptitude;
	}
	public String getSelectBaseChange() {
		return selectBaseChange;
	}
	public void setSelectBaseChange(String selectBaseChange) {
		this.selectBaseChange = selectBaseChange;
	}
	public String getSelectPositionChange() {
		return selectPositionChange;
	}
	public void setSelectPositionChange(String selectPositionChange) {
		this.selectPositionChange = selectPositionChange;
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
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return reqCenterOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return reqCenterOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return titleName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	  
	
}
