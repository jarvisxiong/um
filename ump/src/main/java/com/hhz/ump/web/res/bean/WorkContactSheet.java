/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 *  工作联系单
 * 
 */
public class WorkContactSheet extends BaseTemplatePay {
		
	//	发单中心		签发人		签发日期	
	//	接单中心		接单人		接单日期	
	//	工作联系内容
	//	完成情况	

	private String signCenterOrgName;
	private String signCenterOrgCd;
	private String signDate;
	private String receCenterOrgName;
	private String receDate;
	private String workContactDesc;
	private String completeDesc;
	public String getSignCenterOrgName() {
		return signCenterOrgName;
	}
	public void setSignCenterOrgName(String signCenterOrgName) {
		this.signCenterOrgName = signCenterOrgName;
	}
	public String getSignCenterOrgCd() {
		return signCenterOrgCd;
	}
	public void setSignCenterOrgCd(String signCenterOrgCd) {
		this.signCenterOrgCd = signCenterOrgCd;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getReceCenterOrgName() {
		return receCenterOrgName;
	}
	public void setReceCenterOrgName(String receCenterOrgName) {
		this.receCenterOrgName = receCenterOrgName;
	}
	public String getReceDate() {
		return receDate;
	}
	public void setReceDate(String receDate) {
		this.receDate = receDate;
	}
	public String getWorkContactDesc() {
		return workContactDesc;
	}
	public void setWorkContactDesc(String workContactDesc) {
		this.workContactDesc = workContactDesc;
	}
	public String getCompleteDesc() {
		return completeDesc;
	}
	public void setCompleteDesc(String completeDesc) {
		this.completeDesc = completeDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.signCenterOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return signCenterOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return receCenterOrgName;
	}
	 
}
